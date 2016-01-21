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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.coeus.personProfile.PersonProfileListDocument;
import gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList;
import gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson;
import gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address.Country.Enum;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.common.api.unit.UnitRepositoryService;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.person.attachment.ProposalPersonBiographyContract;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.print.GenericPrintable;
import org.kuali.coeus.s2sgen.impl.print.S2SPrintingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.util.*;
import java.util.List;

public abstract class RRKeyPersonBase extends S2SBaseFormGenerator {
	
	private static final Logger LOG = LoggerFactory.getLogger(RRKeyPersonBase.class);
	protected List<ProposalPersonContract> extraPersons = null;
	
	protected static final int BIOSKETCH_DOC_TYPE = 16;
	protected static final int CURRENTPENDING_DOC_TYPE = 17;
	protected static final String BIOSKETCH_TYPE = "1";
	protected static final String CURRENT_PENDING_TYPE = "2";
	private static final String COMMENT = "Auto generated document for ";
	private static final String BIOSKETCH_COMMENT = "BIOSKETCH";
	private static final String CURRENT_PENDING_COMMENT = "CURRENTPENDING";
	protected static final String PROFILE_COMMENT = "PROFILE";
    protected static final int PROFILE_TYPE = 18;
	protected static final int DIVISION_NAME_MAX_LENGTH = 30;
	protected static final String NIH_CO_INVESTIGATOR = "Co-Investigator";
    protected static final String ERROR_ERA_COMMON_USER_NAME="eRA Commons User Name is missing for ";
    protected static final int DEPARTMENT_NAME_MAX_LENGTH = 30;
	protected static final int MAX_KEY_PERSON_COUNT = 8;
	protected static final int DIRECTORY_TITLE_MAX_LENGTH = 45;
	protected static final int ROLE_DESCRIPTION_MAX_LENGTH = 40;

	protected String pIPersonOrRolodexId = null;

    @Autowired
    @Qualifier("unitRepositoryService")
    private UnitRepositoryService unitRepositoryService;

    @Autowired
    @Qualifier("s2SPrintingService")
    private S2SPrintingService s2SPrintingService;

    @Autowired
    @Qualifier("s2SProposalPersonService")
    protected S2SProposalPersonService s2SProposalPersonService;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/additionalkeypersonprofiles.xsl")
    private Resource additionalkeypersonprofilesStyleSheet;

	protected void saveKeyPersonAttachmentsToProposal() {
	    if(extraPersons!=null && !extraPersons.isEmpty()){
	        saveKeyPersonAttachments();
	        saveKeypersonProfileObject();
	    }
	}

	private NarrativeContract[] saveKeyPersonAttachments() {
		List<String> bioSketchBookMarks = new ArrayList<String>();
		List<String> curPendBookMarks = new ArrayList<String>();
		List<byte[]> bioSketchDataList = new ArrayList<byte[]>();
		List<byte[]> curPendDataList = new ArrayList<byte[]>();

        NarrativeContract[] extraKeyPersonAttachments = new NarrativeContract[2];
		for (ProposalPersonContract proposalPerson : extraPersons) {
			setBookMarkAndData(bioSketchBookMarks, bioSketchDataList,
					proposalPerson, BIOSKETCH_TYPE);
			setBookMarkAndData(curPendBookMarks, curPendDataList,
					proposalPerson, CURRENT_PENDING_TYPE);
		}
		byte[] bioSketchData = null;
		byte[] curPendData = null;
		try {
			bioSketchData = s2SPrintingService.mergePdfBytes(bioSketchDataList, bioSketchBookMarks, true);
			curPendData = s2SPrintingService.mergePdfBytes(curPendDataList, curPendBookMarks, true);
			String fileName = null;
			if (bioSketchData != null && bioSketchData.length > 0) {
				fileName = pdDoc.getDevelopmentProposal().getProposalNumber()
						+ "_" + BIOSKETCH_COMMENT+".pdf";
				extraKeyPersonAttachments[0] = saveNarrative(bioSketchData, "" + BIOSKETCH_DOC_TYPE, fileName,
						COMMENT + BIOSKETCH_COMMENT);
			}
			if (curPendData != null && curPendData.length > 0) {
				fileName = pdDoc.getDevelopmentProposal().getProposalNumber()
						+ "_" + CURRENT_PENDING_COMMENT+".pdf";
				extraKeyPersonAttachments[1] = saveNarrative(curPendData, "" + CURRENTPENDING_DOC_TYPE,
						fileName, COMMENT + CURRENT_PENDING_COMMENT);
			}
		} catch (S2SException e) {
			LOG.error("Auto generation of Biosketch/Currend Pending report for extra Keypersons is failed", e);
		}
		return extraKeyPersonAttachments;
	}
	
	private void setBookMarkAndData(List<String> bookMarksList,
			List<byte[]> dataList, ProposalPersonContract proposalPerson, String docType) {
		String personId = null;
		if (proposalPerson.getPersonId() != null
				&& proposalPerson.getPersonId().length() > 0) {
			personId = proposalPerson.getPersonId();
		} else {
			personId = "" + proposalPerson.getRolodexId();
		}

		for (ProposalPersonBiographyContract personBiography : getPernonnelAttachments(
				pdDoc, proposalPerson, docType)) {
			byte[] content = personBiography.getPersonnelAttachment().getData();
			if (content != null && content.length > 0) {
				dataList.add(content);
				bookMarksList.add(personId);
			}
		}
	}

	private List<ProposalPersonBiographyContract> getPernonnelAttachments(
			ProposalDevelopmentDocumentContract pdDoc, ProposalPersonContract proposalPerson,
			String documentType) {
		List<ProposalPersonBiographyContract> result = new ArrayList<ProposalPersonBiographyContract>();
		for (ProposalPersonBiographyContract proposalPersonBiography : pdDoc
				.getDevelopmentProposal().getPropPersonBios()) {
			String personId = proposalPerson.getPersonId();
			Integer rolodexId = proposalPerson.getRolodexId();
			if (personId != null
					&& proposalPersonBiography.getPersonId() != null
					&& proposalPersonBiography.getPersonId().equals(personId)
					&& documentType.equals(proposalPersonBiography.getPropPerDocType().getCode())) {
				result.add(proposalPersonBiography);
			} else if (rolodexId != null
					&& proposalPersonBiography.getRolodexId() != null
					&& proposalPersonBiography.getRolodexId().toString()
							.equals(rolodexId.toString())
					&& documentType.equals(proposalPersonBiography
							.getPropPerDocType().getCode())) {
				result.add(proposalPersonBiography);
			}
		}
		return result;
	}
	
	private String getWhitespaceString(int length) {
		StringBuffer sb = new StringBuffer();
		char[] whiteSpace = new char[length];
		Arrays.fill(whiteSpace, FieldValueConstants.SPACE_SEPARATOR);
		sb.append(whiteSpace);
		return sb.toString();
	}
	
	protected PersonProfileList.ExtraKeyPerson[] getExtraKeyPersons() {
		List<PersonProfileList.ExtraKeyPerson> extraPersonList = new ArrayList<PersonProfileList.ExtraKeyPerson>();

		for (ProposalPersonContract proposalPerson : extraPersons) {

			PersonProfileList.ExtraKeyPerson extraPerson = PersonProfileList.ExtraKeyPerson.Factory
					.newInstance();

			extraPerson.setName(getExtraPersonName(proposalPerson));
			extraPerson.setAddress(getExtraPersonAddress(proposalPerson));
			if (proposalPerson.getPrimaryTitle() != null
					&& proposalPerson.getPrimaryTitle().length() > 45)
				extraPerson.setTitle(proposalPerson.getPrimaryTitle()
						.substring(0, 45));
			else {
				extraPerson.setTitle(proposalPerson.getPrimaryTitle());
			}

			if (proposalPerson.getProposalPersonRoleId() != null) {
				if (proposalPerson.isPrincipalInvestigator()) {
					extraPerson
							.setProjectRole(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.PD_PI);
				} else if (proposalPerson.isCoInvestigator()) {
					if (isSponsorNIH(pdDoc)) {
						extraPerson
								.setProjectRole(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.OTHER_SPECIFY);
						extraPerson
								.setOtherProjectRoleCategory(NIH_CO_INVESTIGATOR);
					} else {
						extraPerson
								.setProjectRole(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.CO_PD_PI);
					}
				} else {
					String otherRole = "";
					if (proposalPerson.getProjectRole() != null
							&& proposalPerson.getProjectRole().length() > 40) {
						otherRole = proposalPerson.getProjectRole().substring(
								0, 40);
					} else {
						otherRole = proposalPerson.getProjectRole();
					}
					extraPerson.setOtherProjectRoleCategory(otherRole);
					extraPerson
							.setProjectRole(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.OTHER_SPECIFY);
				}
			}

			if (proposalPerson.getEraCommonsUserName() != null) {
				extraPerson.setCredential(proposalPerson
						.getEraCommonsUserName());
			}
			setDepartmentName(extraPerson, proposalPerson);
			setDivisionName(extraPerson, proposalPerson);
			if (proposalPerson.getEmailAddress() != null) {
				extraPerson.setEmail(proposalPerson.getEmailAddress());
			}
			if (StringUtils.isNotEmpty(proposalPerson.getFaxNumber())) {
				extraPerson.setFax(proposalPerson.getFaxNumber());
			}
            UnitContract unit = unitRepositoryService.findUnitByUnitNumber(proposalPerson.getHomeUnit());

			if (unit != null
					&& unit.getUnitName() != null) {
				extraPerson.setOrganizationName(unit.getUnitName());
			}
			if (proposalPerson.getOfficePhone() != null)
				extraPerson.setPhone(proposalPerson.getOfficePhone());

			// degree type and year added for version 1.2 - case 4337
			if (proposalPerson.getDegree() != null) {
				extraPerson.setDegreeType(proposalPerson.getDegree());
			}
			if (proposalPerson.getYearGraduated() != null) {
				extraPerson.setDegreeYear(proposalPerson.getYearGraduated());
			}
			AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(
					pdDoc, proposalPerson.getPersonId(), proposalPerson
							.getRolodexId(), BIOSKETCH_TYPE);
			if (bioSketchAttachment != null) {
				extraPerson
						.setBioSketchAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.BioSketchAttached.YES);
			}

			AttachedFileDataType curPendingAttachment = getPernonnelAttachments(
					pdDoc, proposalPerson.getPersonId(), proposalPerson
							.getRolodexId(), CURRENT_PENDING_TYPE);
			if (curPendingAttachment != null) {
				extraPerson
						.setSupportsAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.SupportsAttached.YES);
			}
			extraPersonList.add(extraPerson);
		}
		return extraPersonList.toArray(new PersonProfileList.ExtraKeyPerson[0]);
	}

	private PersonProfileList.ExtraKeyPerson.Address getExtraPersonAddress(
			ProposalPersonContract proposalPerson) {
		PersonProfileList.ExtraKeyPerson.Address address = PersonProfileList.ExtraKeyPerson.Address.Factory
				.newInstance();
		if (proposalPerson.getAddressLine1() != null) {
			if (proposalPerson.getAddressLine1().length() > 55)
				address.setStreet1(proposalPerson.getAddressLine1().substring(
						0, 55));
			else
				address.setStreet1(proposalPerson.getAddressLine1());
		}
		if (proposalPerson.getAddressLine2() != null) {
			if (proposalPerson.getAddressLine2().length() > 55)
				address.setStreet2(proposalPerson.getAddressLine2().substring(
						0, 55));
			else
				address.setStreet2(proposalPerson.getAddressLine2());
		}
		if (proposalPerson.getCity() != null)
			address.setCity(proposalPerson.getCity());
		if (proposalPerson.getCounty() != null)
			address.setCounty(proposalPerson.getCounty());

		if (proposalPerson.getPostalCode() != null) {
			address.setZipCode(proposalPerson.getPostalCode());
		}

		if (proposalPerson.getCountryCode() != null) {
			PersonProfileList.ExtraKeyPerson.Address.Country.Enum county = Enum
					.forString(proposalPerson.getCountryCode());
			address.setCountry(county);
		}
		if (proposalPerson.getState() != null) {
			address.setState(proposalPerson.getState());
		}
		return address;
	}

	private PersonProfileList.ExtraKeyPerson.Name getExtraPersonName(
			ProposalPersonContract proposalPerson) {
		PersonProfileList.ExtraKeyPerson.Name name = PersonProfileList.ExtraKeyPerson.Name.Factory
				.newInstance();
		if (proposalPerson.getFirstName() != null)
			name.setFirstName(proposalPerson.getFirstName());
		if (proposalPerson.getMiddleName() != null)
			name.setMiddleName(proposalPerson.getMiddleName());
		if (proposalPerson.getLastName() != null)
			name.setLastName(proposalPerson.getLastName());
		return name;
	}

	private void setDivisionName(ExtraKeyPerson extraPerson, ProposalPersonContract proposalPerson) {
		extraPerson.setDivisionName("");
	}

	private void setDepartmentName(ExtraKeyPerson extraPerson, ProposalPersonContract proposalPerson) {
		extraPerson.setDepartmentName("");
	}
	private NarrativeContract saveKeypersonProfileObject() {
        NarrativeContract narrative = null;
		if (extraPersons != null && !extraPersons.isEmpty()) {
			PersonProfileList extraPersonProfileList = PersonProfileList.Factory.newInstance();

			extraPersonProfileList.setProposalNumber(pdDoc
					.getDevelopmentProposal().getProposalNumber());
			extraPersonProfileList.setExtraKeyPersonArray(getExtraKeyPersons());

			PersonProfileListDocument  extraPersonDoc = PersonProfileListDocument.Factory.newInstance();
			extraPersonDoc.setPersonProfileList(extraPersonProfileList);
			String xmlData = extraPersonDoc.xmlText();
			Map<String, byte[]> streamMap = new HashMap<String, byte[]>();
			streamMap.put("", xmlData.getBytes());

            Source xsltSource = null;
            try {
                xsltSource =  new StreamSource(additionalkeypersonprofilesStyleSheet.getInputStream());
            } catch(IOException e) {
                throw new RuntimeException("the stream could not be opened",e);
            }
			Map<String, Source> xSLTemplateWithBookmarks = new HashMap<String, Source>();
			xSLTemplateWithBookmarks.put("", xsltSource);
			
			
			GenericPrintable printable = new GenericPrintable();
			printable.setXSLTemplateWithBookmarks(xSLTemplateWithBookmarks);
			printable.setStreamMap(streamMap);
			try {
				KcFile printData = s2SPrintingService.print(printable);
				String fileName = pdDoc.getDevelopmentProposal().getProposalNumber() +"_"+PROFILE_COMMENT+".pdf";
				narrative = saveNarrative(printData.getData(), ""+PROFILE_TYPE,fileName,COMMENT +PROFILE_COMMENT);
			} catch (S2SException e) {
				LOG.error("Auto generation of Profile attachment for extra Keypersons failed",e);
			}
		}
		return narrative;
	}

    public UnitRepositoryService getUnitRepositoryService() {
        return unitRepositoryService;
    }

    public void setUnitRepositoryService(UnitRepositoryService unitRepositoryService) {
        this.unitRepositoryService = unitRepositoryService;
    }

    public S2SPrintingService getS2SPrintingService() {
        return s2SPrintingService;
    }

    public void setS2SPrintingService(S2SPrintingService s2SPrintingService) {
        this.s2SPrintingService = s2SPrintingService;
    }

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }

    public Resource getAdditionalkeypersonprofilesStyleSheet() {
        return additionalkeypersonprofilesStyleSheet;
    }

    public void setAdditionalkeypersonprofilesStyleSheet(Resource additionalkeypersonprofilesStyleSheet) {
        this.additionalkeypersonprofilesStyleSheet = additionalkeypersonprofilesStyleSheet;
    }
}
