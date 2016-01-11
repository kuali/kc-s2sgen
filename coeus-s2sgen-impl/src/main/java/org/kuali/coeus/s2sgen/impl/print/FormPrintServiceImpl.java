/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.s2sgen.impl.print;

import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication.Forms;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xpath.XPathAPI;
import org.kuali.coeus.common.budget.api.core.BudgetContract;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardAttachmentContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.person.attachment.ProposalPersonBiographyContract;
import org.kuali.coeus.propdev.api.s2s.*;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.kuali.coeus.s2sgen.api.print.FormPrintService;
import org.kuali.coeus.s2sgen.api.print.FormPrintResult;
import org.kuali.coeus.s2sgen.impl.budget.S2SCommonBudgetService;
import org.kuali.coeus.s2sgen.impl.generate.S2SFormGenerator;
import org.kuali.coeus.s2sgen.impl.util.ClassLoaderUtils;
import org.kuali.coeus.s2sgen.impl.util.XPathExecutor;
import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeService;
import org.kuali.coeus.s2sgen.impl.generate.S2SFormGeneratorRetrievalService;
import org.kuali.coeus.s2sgen.impl.validate.S2SValidatorService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeService;
import org.kuali.coeus.s2sgen.api.generate.AttachmentData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This class is implementation of PrintService. It provides the functionality
 * to generate PDF for all forms along with their attachments
 */
@Component("formPrintService")
public class FormPrintServiceImpl implements FormPrintService {
	private static final Logger LOG = LoggerFactory.getLogger(FormPrintServiceImpl.class);
    private static final String PDF_FILE_EXTENSION = ".pdf";
    private static final String NARRATIVE_CONTENT_ID_PREFIX = "N";
    private static final String BIOGRAPHY_CONTENT_ID_PREFIX = "B";
    private static final String NARRATIVE_LEGACY_DATA_CONTENT_ID_PREFIX = "M";
    private static final String BIOGRAPHY_LEGACY_DATA_CONTENT_ID_PREFIX = "ID";
    private static final String BIOGRAPHY_LEGACY_DATA_CONTENT_ID_ELEMENT = "BN";
	private static final String HYPHEN = "-";
	private static final String UNDERSCORE = "_";

	private static final List<String> ATT_PREFIXES = Stream.of(NARRATIVE_CONTENT_ID_PREFIX + HYPHEN,
			BIOGRAPHY_CONTENT_ID_PREFIX + HYPHEN,
			NARRATIVE_LEGACY_DATA_CONTENT_ID_PREFIX + HYPHEN,
			BIOGRAPHY_LEGACY_DATA_CONTENT_ID_PREFIX + HYPHEN,
			BIOGRAPHY_LEGACY_DATA_CONTENT_ID_ELEMENT + HYPHEN).collect(Collectors.toList());

	@Autowired
    @Qualifier("s2sApplicationService")
	private S2sApplicationService s2sApplicationService;

    @Autowired
    @Qualifier("s2SFormGeneratorRetrievalService")
    private S2SFormGeneratorRetrievalService s2SFormGeneratorService;

    @Autowired
    @Qualifier("s2SValidatorService")
    private S2SValidatorService s2SValidatorService;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    @Autowired
    @Qualifier("s2SDateTimeService")
    private S2SDateTimeService s2SDateTimeService;

    @Autowired
    @Qualifier("narrativeService")
    private NarrativeService narrativeService;

    @Autowired
    @Qualifier("s2SPrintingService")
    private S2SPrintingService s2SPrintingService;

    @Autowired
    @Qualifier("formMappingService")
    private FormMappingService formMappingService;

    @Autowired
    @Qualifier("userAttachedFormService")
    private UserAttachedFormService userAttachedFormService;

	@Autowired
	@Qualifier("s2SCommonBudgetService")
	protected S2SCommonBudgetService s2SCommonBudgetService;

	/**
	 * 
	 * This method is used for the printing of forms in PDF format. It generates
	 * PDF forms and puts it into {@link KcFile}
	 * 
	 * @param pdDoc (ProposalDevelopmentDocumentContract)
	 * @return {@link KcFile} which contains all information
	 *         related to the generated PDF
	 */
    @Override
	public FormPrintResult printForm(Object pdDoc) throws S2SException {

        if (pdDoc == null) {
            throw new IllegalArgumentException("pdDoc is null");
        }

        ProposalDevelopmentDocumentContract pdDocContract = (ProposalDevelopmentDocumentContract) pdDoc;

        PrintableResult pResult;
		S2sAppSubmissionContract s2sAppSubmission = getLatestS2SAppSubmission(pdDocContract);
		if (s2sAppSubmission != null
				&& s2sAppSubmission.getGgTrackingId() != null) {
            pResult = getSubmittedPDFStream(pdDocContract);
		} else {
            pResult = getPDFStream(pdDocContract);
		}
	    if(pdDocContract.getDevelopmentProposal().getGrantsGovSelectFlag()){
            FormPrintResult result = new FormPrintResult();
            result.setErrors(pResult.errors);
            return result;
	    }
        S2SFile attachmentDataSource = s2SPrintingService
        	.print(pResult.printables);
		attachmentDataSource.setName(getFileNameForFormPrinting(pdDocContract));

        FormPrintResult result = new FormPrintResult();
        result.setFile(attachmentDataSource);
        result.setErrors(pResult.errors);
        return result;
	}

	protected void saveGrantsGovXml(ProposalDevelopmentDocumentContract pdDoc, boolean formEntryFlag,XmlObject formObject,List<AttachmentData> attachmentList,List<? extends S2sAppAttachmentsContract> attachmentLists) throws Exception{
	    String loggingDirectory = s2SConfigurationService.getValueAsString(ConfigurationConstants.PRINT_XML_DIRECTORY);
        String opportunityId = pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityId();
        String proposalnumber = pdDoc.getDevelopmentProposal().getProposalNumber();
        String exportDate = StringUtils.replaceChars((pdDoc.getDevelopmentProposal().getUpdateTimestamp().toString()), ":", UNDERSCORE);
        exportDate = StringUtils.replaceChars(exportDate, " ", ".");

        File grantsGovXmlDirectoryFile = new File(loggingDirectory + opportunityId + "." + proposalnumber + "." + exportDate);
        if (!grantsGovXmlDirectoryFile.exists() || formEntryFlag) {
            grantsGovXmlDirectoryFile.mkdir();
        }

        for (AttachmentData attachmentData : attachmentList) {
            File attachmentFile = new File(grantsGovXmlDirectoryFile,"Attachments");
            attachmentFile.mkdir();
            File attachedFile = new File(attachmentFile,attachmentData.getFileName());
            try (FileOutputStream output = new FileOutputStream(attachedFile)) {
                output.write(attachmentData.getContent());
            }
        }
        for (S2sAppAttachmentsContract attAppAttachments : attachmentLists) {
            File attachmentFile = new File(grantsGovXmlDirectoryFile,"Attachments");   
            attachmentFile.mkdir();
            final KcFile ads = getAttributeContent(pdDoc,attAppAttachments.getContentId());
            if (ads != null) {
				File attachedFile = new File(attachmentFile, ads.getName());
				try (FileOutputStream output = new FileOutputStream(attachedFile)) {
					output.write(ads.getData());
				}
			}
        }
        File xmlFile= new File(grantsGovXmlDirectoryFile,opportunityId + "." + proposalnumber + "." + exportDate+".xml");
        try (BufferedWriter out = new BufferedWriter(new FileWriter(xmlFile))) {
            out.write(formObject.xmlText());
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(grantsGovXmlDirectoryFile+".zip"); ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
            addFolderToZip("", grantsGovXmlDirectoryFile.getPath(), zipOutputStream);
            zipOutputStream.flush();
        }

	}
	
    protected void addFolderToZip(String path, String sourceFolder, ZipOutputStream zipOutputStream) throws Exception {
        File proposalNumberfolder = new File(sourceFolder);
        for (String fileName : proposalNumberfolder.list()) {
            if (path.equals("")) {
                addFileToZip(proposalNumberfolder.getName(), sourceFolder + "/" + fileName, zipOutputStream);
            } else {
                addFileToZip(path + "/" + proposalNumberfolder.getName(), sourceFolder + "/" + fileName, zipOutputStream);
            }
        }
    }

    protected void addFileToZip(String path, String sourceFile, ZipOutputStream zipOutputStream) throws Exception {
        File attachmentFile = new File(sourceFile);
        if (attachmentFile.isDirectory()) {
            addFolderToZip(path, sourceFile, zipOutputStream);
        } else {
            byte[] buffer = new byte[1024];
            int length;
            try (FileInputStream fileInputStream = new FileInputStream(attachmentFile)) {
                zipOutputStream.putNextEntry(new ZipEntry(path + "/" + attachmentFile.getName()));
                while ((length = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, length);
                }
            }
        }
    }
	protected String getFileNameForFormPrinting(ProposalDevelopmentDocumentContract pdDoc) {
		StringBuilder fileName = new StringBuilder();
		fileName.append(pdDoc.getDocumentNumber());
		fileName.append(pdDoc.getDevelopmentProposal()
				.getProgramAnnouncementNumber());
		fileName.append(PDF_FILE_EXTENSION);
		return fileName.toString();
	}

	/**
	 * 
	 * This method is to write the submitted application data to a pdfStream
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return ByteArrayOutputStream[] of the submitted application data.
	 */
	protected PrintableResult getSubmittedPDFStream(
			ProposalDevelopmentDocumentContract pdDoc) throws S2SException {
		GrantApplicationDocument submittedDocument;
		String frmXpath;
        String frmAttXpath;
		try {
		    S2sAppSubmissionContract s2sAppSubmission = getLatestS2SAppSubmission(pdDoc);
		    String submittedApplicationXml = findSubmittedXml(s2sAppSubmission);
		    String submittedApplication = s2SDateTimeService.removeTimezoneFactor(submittedApplicationXml);
			submittedDocument = GrantApplicationDocument.Factory.parse(submittedApplication);
		} catch (XmlException e) {
			LOG.error(e.getMessage(), e);
			throw new S2SException(e);
		}
		FormMappingInfo info;
		DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
        List<String> sortedNameSpaces = getSortedNameSpaces(developmentProposal.getProposalNumber(),developmentProposal.getS2sOppForms());
		boolean formEntryFlag = true;
		List<S2SPrintable> formPrintables = new ArrayList<>();
		for (String namespace : sortedNameSpaces) {
			XmlObject formFragment;
			info = formMappingService.getFormInfo(namespace);
			if(info==null) continue;
			if(StringUtils.isNotBlank(info.getStyleSheet())){
				formFragment = getFormObject(submittedDocument);
				frmXpath = "//*[namespace-uri(.) = '"+namespace+"']";               
				frmAttXpath = "//*[namespace-uri(.) = '"+namespace+"']//*[local-name(.) = 'FileLocation' and namespace-uri(.) = 'http://apply.grants.gov/system/Attachments-V1.0']";           

				byte[] formXmlBytes = formFragment.xmlText().getBytes();
				GenericPrintable formPrintable = new GenericPrintable();

				ArrayList<Source> templates = new ArrayList<>();
				DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
				Resource resource = resourceLoader.getResource(info.getStyleSheet());

				Source xsltSource;
				try {
					xsltSource = new StreamSource(resource.getInputStream());
				} catch (IOException e) {
					throw new S2SException(e);
				}
				templates.add(xsltSource);
				formPrintable.setXSLTemplates(templates);

				// Linkedhashmap is used to preserve the order of entry.
				Map<String, byte[]> formXmlDataMap = new LinkedHashMap<>();
				formXmlDataMap.put(info.getFormName(), formXmlBytes);
				formPrintable.setStreamMap(formXmlDataMap);
				S2sApplicationContract s2sApplciation = s2sApplicationService.findS2sApplicationByProposalNumber(pdDoc.getDevelopmentProposal().getProposalNumber());
				List<? extends S2sAppAttachmentsContract> attachmentList = s2sApplciation.getS2sAppAttachmentList();

				Map<String, byte[]> formAttachments = new LinkedHashMap<>();

				try{
					XPathExecutor executer = new XPathExecutor(formFragment.toString());
					org.w3c.dom.Node d = executer.getNode(frmXpath);
					org.w3c.dom.NodeList attList = XPathAPI.selectNodeList(d, frmAttXpath);
					int attLen = attList.getLength();

					for(int i=0;i<attLen;i++){
						org.w3c.dom.Node attNode = attList.item(i);
						String contentId = ((Element)attNode).getAttributeNS("http://apply.grants.gov/system/Attachments-V1.0","href");

						if (attachmentList != null && !attachmentList.isEmpty()) {
							for (S2sAppAttachmentsContract attAppAttachments : attachmentList) {
								if(attAppAttachments.getContentId().equals(contentId)) {
									KcFile file = getAttributeContent(pdDoc, contentId);
									if (file != null) {
										try (ByteArrayOutputStream attStream = new ByteArrayOutputStream()) {
											attStream.write(file.getData());
										} catch (IOException e) {
											LOG.error(e.getMessage(), e);
											throw new S2SException(e);
										}
										StringBuilder attachment = new StringBuilder();
										attachment.append("   ATT : ");
										attachment.append(attAppAttachments.getContentId());
										formAttachments.put(attachment.toString(),
												file.getData());
									}
								}
							}
						}
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				try {
					if(developmentProposal.getGrantsGovSelectFlag()){
						List<AttachmentData> attachmentLists = new ArrayList<>();
						saveGrantsGovXml(pdDoc,formEntryFlag,formFragment,attachmentLists,attachmentList);
						formEntryFlag = false;
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				formPrintable.setAttachments(formAttachments);
				formPrintables.add(formPrintable);
			}
		}
        PrintableResult result = new PrintableResult();
        result.printables = formPrintables;
		return result;
	}

	protected String findSubmittedXml(S2sAppSubmissionContract appSubmission) {
	    S2sApplicationContract s2sApplication = s2sApplicationService.findS2sApplicationByProposalNumber(appSubmission.getProposalNumber());
	    return s2sApplication.getApplication();
    }



    /**
	 * This method is used to generate byte stream of forms
	 * 
	 * @param pdDoc
	 *            ProposalDevelopmentDocumentContract
	 * @return ByteArrayOutputStream[] PDF byte Array
	 */
	protected PrintableResult getPDFStream(ProposalDevelopmentDocumentContract pdDoc)
			throws S2SException {

		List<AuditError> errors = new ArrayList<>();
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
        String proposalNumber = developmentProposal.getProposalNumber();
        List<String> sortedNameSpaces = getSortedNameSpaces(proposalNumber, developmentProposal.getS2sOppForms());

		List<S2SPrintable> formPrintables = new ArrayList<>();
		boolean formEntryFlag = true;
	    getNarrativeService().deleteSystemGeneratedNarratives(pdDoc.getDevelopmentProposal().getNarratives());
	    Forms forms = Forms.Factory.newInstance();
		for (String namespace : sortedNameSpaces) {
            FormMappingInfo info = formMappingService.getFormInfo(namespace,proposalNumber);
			if(info==null) continue;
            S2SFormGenerator s2sFormGenerator = s2SFormGeneratorService.getS2SGenerator(proposalNumber,info.getNameSpace());
			XmlObject formObject = s2sFormGenerator.getFormObject(pdDoc);
			errors.addAll(s2sFormGenerator.getAuditErrors());
			if (s2SValidatorService.validate(formObject, errors, info.getFormName()) && errors.isEmpty() && StringUtils.isNotBlank(info.getStyleSheet())) {
			    String applicationXml = formObject.xmlText(s2SFormGeneratorService.getXmlOptionsPrefixes());
			    String filteredApplicationXml = s2SDateTimeService.removeTimezoneFactor(applicationXml);
				byte[] formXmlBytes = filteredApplicationXml.getBytes();
                GenericPrintable formPrintable = new GenericPrintable();
				// Linkedhashmap is used to preserve the order of entry.
				Map<String, byte[]> formXmlDataMap = new LinkedHashMap<>();
				formXmlDataMap.put(info.getFormName(), formXmlBytes);
				formPrintable.setStreamMap(formXmlDataMap);

				ArrayList<Source> templates = new ArrayList<>();

                DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
                Resource resource = resourceLoader.getResource(info.getStyleSheet());

                Source xsltSource;
                try {
                    xsltSource = new StreamSource(resource.getInputStream());
                } catch (IOException e) {
                    throw new S2SException(e);
                }
                templates.add(xsltSource);
				formPrintable.setXSLTemplates(templates);

				List<AttachmentData> attachmentList = s2sFormGenerator.getAttachments();
				try {
				    if(developmentProposal.getGrantsGovSelectFlag()){
				    	List<S2sAppAttachmentsContract> attachmentLists = new ArrayList<>();
				    	setFormObject(forms, formObject);
                    	saveGrantsGovXml(pdDoc,formEntryFlag,forms,attachmentList,attachmentLists);
                    	formEntryFlag = false;
				    }
                }
                catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
				Map<String, byte[]> formAttachments = new LinkedHashMap<>();
				if (attachmentList != null && !attachmentList.isEmpty()) {
					for (AttachmentData attachmentData : attachmentList) {
						if (!isPdfType(attachmentData.getContent()))
							continue;
						StringBuilder attachment = new StringBuilder();
						attachment.append("   ATT : ");
						attachment.append(attachmentData.getContentId());
						formAttachments.put(attachment.toString(),
								attachmentData.getContent());
					}
				}
				if (formAttachments.size() > 0) {
					formPrintable.setAttachments(formAttachments);
				}
				formPrintables.add(formPrintable);
			}
		}
        final PrintableResult result = new PrintableResult();
        result.errors = errors;
        result.printables = formPrintables;
        return result;
	}

	/**
	 * 
	 * This method gets formObject from submitted Application
	 * 
	 * @param submittedXml
	 *            GrantApplicationDocument object of the submitted form.
	 * @return XmlObject form object corresponding to the
	 *         GrantApplicationDocument and FormMappingInfo objects.
	 * @throws S2SException
	 */

	protected XmlObject getFormObject(GrantApplicationDocument submittedXml) {
		Forms forms = submittedXml.getGrantApplication().getForms();
		return forms.newCursor().getObject();
	}

	protected KcFile getAttributeContent(ProposalDevelopmentDocumentContract pdDoc,
            String contentId) {
		final KcFile bsAtt = getBudgetSubawardAttachment(pdDoc, contentId);
		if (bsAtt != null) {
			return bsAtt;
		}

		final KcFile uafAtt = getUserAttachedFormAttachment(pdDoc, contentId);
		if (uafAtt != null) {
			return uafAtt;
		}

		if (ATT_PREFIXES.stream().anyMatch(contentId::startsWith)) {
			final String[] contentIds = contentId.split(HYPHEN);
			final String[] contentDesc = contentIds[1].split(UNDERSCORE);
			if (StringUtils.equals(contentIds[0], NARRATIVE_CONTENT_ID_PREFIX)) {
				for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
						.getNarratives()) {
					if (narrative.getModuleNumber().equals(Integer.valueOf(contentDesc[0]))) {
						return narrative.getNarrativeAttachment();
					}
				}
			} else if (StringUtils.equals(contentIds[0], BIOGRAPHY_CONTENT_ID_PREFIX)) {
				for (ProposalPersonBiographyContract biography : pdDoc.getDevelopmentProposal().getPropPersonBios()) {
					if (biography.getProposalPersonNumber().equals(Integer.valueOf(contentDesc[0]))
							&& biography.getBiographyNumber().equals(Integer.valueOf(contentDesc[1]))) {
						return biography.getPersonnelAttachment();
					}
				}
			} else {
				return getLegacyCoeusAttachmentContent(pdDoc, contentIds, contentDesc);
			}
		} else {
			LOG.warn("contentId: " + contentId + " has an invalid format");
		}
        return null;
    }

	protected KcFile getLegacyCoeusAttachmentContent(ProposalDevelopmentDocumentContract pdDoc, String[] contentIds, String[] contentDesc) {
		if (StringUtils.equals(contentIds[0], NARRATIVE_LEGACY_DATA_CONTENT_ID_PREFIX )) {
			return pdDoc.getDevelopmentProposal().getNarratives().stream().filter(narrative ->
					(narrative.getModuleNumber().equals(Integer.valueOf(contentDesc[0])))).findAny().get().getNarrativeAttachment();
		} else if (StringUtils.equals(contentIds[0], BIOGRAPHY_LEGACY_DATA_CONTENT_ID_PREFIX) && StringUtils.equals(contentDesc[1], BIOGRAPHY_LEGACY_DATA_CONTENT_ID_ELEMENT)) {
			String[] biographyDescrption = contentIds[2].split(UNDERSCORE);
			return pdDoc.getDevelopmentProposal().getPropPersonBios().stream().filter(biography ->
					(biography.getPersonId().equals(contentDesc[0]) && biography.getBiographyNumber().equals(Integer.valueOf(biographyDescrption[0])))).findAny().get().getPersonnelAttachment();
		}
		return null;
	}

	private KcFile getUserAttachedFormAttachment(ProposalDevelopmentDocumentContract pdDoc, String contentId) {
		final Optional<? extends S2sUserAttachedFormAttContract> uafAtt = pdDoc.getDevelopmentProposal().getS2sUserAttachedForms().stream()
				.flatMap(uaf -> uaf.getS2sUserAttachedFormAtts().stream())
				.filter(att -> contentId.equals(att.getContentId()))
				.findAny();
		if (uafAtt.isPresent()) {
			return uafAtt.get();
		}
		return null;
	}

	private KcFile getBudgetSubawardAttachment(ProposalDevelopmentDocumentContract pdDoc, String contentId) {
		BudgetContract budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());
		if (budget != null) {
			final Optional<? extends BudgetSubAwardAttachmentContract> att = budget.getBudgetSubAwards().stream()
					.flatMap(sa -> sa.getBudgetSubAwardAttachments().stream())
					.filter(attachment -> contentId.equals(attachment.getName()))
					.findAny();

			if (att.isPresent()) {
				return att.get();
			}
		}
		return null;
	}

	/**
	 * 
	 * This method gets the latest S2SAppSubmission record from the list of
	 * S2SAppSubmissions. It iterates through the list and returns the record
	 * that has highest SubmissionNo
	 * 
	 * @param pdDoc
	 *            {@link ProposalDevelopmentDocumentContract}
	 * @return {@link S2sAppSubmissionContract}
	 */
	protected S2sAppSubmissionContract getLatestS2SAppSubmission(
			ProposalDevelopmentDocumentContract pdDoc) {
		S2sAppSubmissionContract s2sSubmission = null;
		int submissionNo = 0;
		for (S2sAppSubmissionContract s2sAppSubmission : pdDoc.getDevelopmentProposal()
				.getS2sAppSubmission()) {
			if (s2sAppSubmission.getSubmissionNumber() != null
					&& s2sAppSubmission.getSubmissionNumber() > submissionNo) {
				s2sSubmission = s2sAppSubmission;
				submissionNo = s2sAppSubmission.getSubmissionNumber();
			}
		}
		return s2sSubmission;
	}



	/**
	 * 
	 * This method sorts all the forms in order as specified in
	 * S2sFormBinding.xml and returns the list of namespaces in sorted order.
	 * 
	 * @param s2sOppForms list of S2sOppForms.
	 * @return List&lt;String&gt; list of sorted name spaces.
	 */
	protected List<String> getSortedNameSpaces(String proposalNumber,List<? extends S2sOppFormsContract> s2sOppForms) {
		List<String> orderedNamespaces = new ArrayList<>();
		Set<String> namespaces;
        formMappingService.getBindings();
		Map<Integer, Set<String>> sortedNamespaces = formMappingService.getSortedNameSpaces();
		List<Integer> sortedIndices = new ArrayList<>(sortedNamespaces
				.keySet());
		int index = 0;
		for (Integer sortedIndex : sortedIndices) {
            for (S2sOppFormsContract oppForm : s2sOppForms) {
                namespaces = sortedNamespaces.get(sortedIndex);
                for (String namespace : namespaces) {
					if (namespace.equals(oppForm.getOppNameSpace())) {
						if (Boolean.TRUE.equals(oppForm.getSelectToPrint())) {
							orderedNamespaces.add(index++, namespace);
						}
					}
				}
			}
		}
		List<String> userAttachedFormNamespaces = findUserAttachedNamespaces(proposalNumber);
		orderedNamespaces.addAll(s2sOppForms.stream()
				.filter(oppForm -> userAttachedFormNamespaces.contains(oppForm.getOppNameSpace()))
				.filter(oppForm -> Boolean.TRUE.equals(oppForm.getSelectToPrint()))
				.map(S2sOppFormsContract::getOppNameSpace).collect(Collectors.toList()));
		return orderedNamespaces;
	}

    private List<String> findUserAttachedNamespaces(String proposalNumber) {
        return userAttachedFormService.findFormNamespaces(proposalNumber);
    }

	public void setS2SFormGeneratorService(
			S2SFormGeneratorRetrievalService s2SFormGeneratorService) {
		this.s2SFormGeneratorService = s2SFormGeneratorService;
	}

	public void setS2SValidatorService(S2SValidatorService s2SValidatorService) {
		this.s2SValidatorService = s2SValidatorService;
	}

	protected boolean isPdfType(byte[] data) {
		final int ATTRIBUTE_CHUNK_SIZE = 1200;// increased for ppt
		final String PRE_HEXA = "0x";

		boolean retValue = false;
		String str[] = { "25", "50", "44", "46" };
		byte byteCheckArr[] = new byte[4];
		byte byteDataArr[] = new byte[4];

		for (int byteIndex = 0; byteIndex < byteCheckArr.length; byteIndex++) {
			byteCheckArr[byteIndex] = Integer.decode(PRE_HEXA + str[byteIndex])
					.byteValue();
		}

		int startPoint, endPoint;

		startPoint = 0;
		endPoint = (ATTRIBUTE_CHUNK_SIZE > (data.length / 2)) ? data.length / 2
				: ATTRIBUTE_CHUNK_SIZE;

		for (int forwardIndex = startPoint; forwardIndex < endPoint
				- str.length; forwardIndex++) {
			if (forwardIndex == 0) {
				// Fill All Data
				for (int fillIndex = 0; fillIndex < str.length; fillIndex++) {
					byteDataArr[fillIndex] = toUnsignedByte(data[fillIndex]);
				}
			} else {
				// Push Data, Fill last index
				System.arraycopy(byteDataArr, 1, byteDataArr, 0, str.length - 1);
				byteDataArr[str.length - 1] = toUnsignedByte(data[str.length
						- 1 + forwardIndex]);
			}

			if (new String(byteCheckArr).equals(new String(byteDataArr))) {
				retValue = true;
			}
		}

		return retValue;
	}

	/**
	 * convert int to unsigned byte
	 */
	protected static byte toUnsignedByte(int intVal) {
		byte byteVal;
		if (intVal > 127) {
			int temp = intVal - 256;
			byteVal = (byte) temp;
		} else {
			byteVal = (byte) intVal;
		}
		return byteVal;
	}

    public NarrativeService getNarrativeService() {
        return narrativeService;
    }

    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public S2SPrintingService getS2SPrintingService() {
		return s2SPrintingService;
	}

	public void setS2SPrintingService(S2SPrintingService s2SPrintingService) {
		this.s2SPrintingService = s2SPrintingService;
	}
	protected void setFormObject(Forms forms, XmlObject formObject) {
        // Create a cursor from the grants.gov form
        XmlCursor formCursor = formObject.newCursor();
        formCursor.toStartDoc();
        formCursor.toNextToken();

        // Create a cursor from the Forms object
        XmlCursor metaGrantCursor = forms.newCursor();
        metaGrantCursor.toNextToken();

        // Add the form to the Forms object.
        formCursor.moveXml(metaGrantCursor);
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }

    public S2sApplicationService getS2sApplicationService() {
        return s2sApplicationService;
    }

    public void setS2sApplicationService(S2sApplicationService s2sApplicationService) {
        this.s2sApplicationService = s2sApplicationService;
    }

    public S2SFormGeneratorRetrievalService getS2SFormGeneratorService() {
        return s2SFormGeneratorService;
    }

    public S2SValidatorService getS2SValidatorService() {
        return s2SValidatorService;
    }

    public FormMappingService getFormMappingService() {
        return formMappingService;
    }

    public void setFormMappingService(FormMappingService formMappingService) {
        this.formMappingService = formMappingService;
    }

    public UserAttachedFormService getUserAttachedFormService() {
        return userAttachedFormService;
    }

    public void setUserAttachedFormService(UserAttachedFormService userAttachedFormService) {
        this.userAttachedFormService = userAttachedFormService;
    }

    public S2SDateTimeService getS2SDateTimeService() {
        return s2SDateTimeService;
    }

    public void setS2SDateTimeService(S2SDateTimeService s2SDateTimeService) {
        this.s2SDateTimeService = s2SDateTimeService;
    }

	public S2SCommonBudgetService getS2SCommonBudgetService() {
		return s2SCommonBudgetService;
	}

	public void setS2SCommonBudgetService(S2SCommonBudgetService s2SCommonBudgetService) {
		this.s2SCommonBudgetService = s2SCommonBudgetService;
	}

	protected static class PrintableResult {
        private List<S2SPrintable> printables;
        private List<AuditError> errors;
    }
}
