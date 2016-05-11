/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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

import gov.grants.apply.forms.phs398CoverPageSupplement14V14.PHS398CoverPageSupplement14Document;
import gov.grants.apply.forms.phs398CoverPageSupplement14V14.PHS398CoverPageSupplement14Document.PHS398CoverPageSupplement14;
import gov.grants.apply.forms.phs398CoverPageSupplement14V14.PHS398CoverPageSupplement14Document.PHS398CoverPageSupplement14.ClinicalTrial;
import gov.grants.apply.forms.phs398CoverPageSupplement14V14.PHS398CoverPageSupplement14Document.PHS398CoverPageSupplement14.ContactPersonInfo;
import gov.grants.apply.forms.phs398CoverPageSupplement14V14.PHS398CoverPageSupplement14Document.PHS398CoverPageSupplement14.PDPI;
import gov.grants.apply.forms.phs398CoverPageSupplement14V14.PHS398CoverPageSupplement14Document.PHS398CoverPageSupplement14.StemCells;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType.Enum;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;

import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.List;

/**
 * Class for generating the XML object for grants.gov
 * PHS398CoverPageSupplementV1_3. Form is generated using XMLBean classes and is
 * based on PHS398CoverPageSupplement schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PHS398CoverPageSupplementV1_4Generator")
public class PHS398CoverPageSupplementV1_4Generator extends
		PHS398CoverPageSupplementBaseGenerator {
    
    private List<? extends AnswerHeaderContract> answerHeaders;

    @Value("http://apply.grants.gov/forms/PHS398_CoverPageSupplement_1_4-V1.4")
    private String namespace;

    @Value("PHS398_CoverPageSupplement_1_4")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PHS398_CoverPageSupplement-V1.4.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398CoverPageSupplement14V14")
    private String packageName;

    @Value("185")
    private int sortIndex;

	/**
	 * 
	 * This method gives information of Cover Page Supplement such as PDPI
	 * details,Clinical Trail information,Contact person information.
	 * 
	 * @return coverPageSupplementDocument {@link XmlObject} of type
	 *         PHS398CoverPageSupplement14Document.
	 */
	private PHS398CoverPageSupplement14Document getCoverPageSupplement() {
		PHS398CoverPageSupplement14Document coverPageSupplementDocument = PHS398CoverPageSupplement14Document.Factory
				.newInstance();
		PHS398CoverPageSupplement14 coverPageSupplement = PHS398CoverPageSupplement14.Factory
				.newInstance();
		answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
		coverPageSupplement.setFormVersion(FormVersion.v1_4.getVersion());
		coverPageSupplement.setPDPI(getPDPI());
		coverPageSupplement.setClinicalTrial(getClinicalTrial());
		coverPageSupplement.setContactPersonInfo(getContactPersonInfo());
		StemCells stemCells = getStemCells();
		coverPageSupplement.setStemCells(stemCells);
		coverPageSupplementDocument
				.setPHS398CoverPageSupplement14(coverPageSupplement);
		return coverPageSupplementDocument;
	}

	/**
	 * 
	 * This method gives the personal details such as Name, New Investigator,
	 * Degrees of Principal Investigator
	 * 
	 * @return PDPI object containing Principal Investigator details.
	 */
	private PDPI getPDPI() {
		PDPI pdpi = PDPI.Factory.newInstance();
		ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
		pdpi.setPDPIName(globLibV20Generator.getHumanNameDataType(PI));
		return pdpi;
	}
	


	/**
	 * 
	 * This method is used to get Clinical Trial information
	 * 
	 * @return ClinicalTrial object containing Clinical Trail Details.
	 */
	private ClinicalTrial getClinicalTrial() {

        ClinicalTrial clinicalTrial = ClinicalTrial.Factory.newInstance();
		String answer = getAnswer(IS_CLINICAL_TRIAL,answerHeaders);
        if (answer != null) {
            if (!answer.equals(NOT_ANSWERED)) {
                if (YnqConstant.YES.code().equals(answer)) {
                    clinicalTrial.setIsClinicalTrial(YesNoDataType.Y_YES);
					String subAnswer = getAnswer(PHASE_III_CLINICAL_TRIAL,answerHeaders);
                        if (subAnswer != null && !subAnswer.equals(NOT_ANSWERED)) {
                            if (YnqConstant.YES.code().equals(subAnswer)) {
                                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.Y_YES);   
                            } else {
                                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.N_NO);   
                            }
                        }else{
                        	clinicalTrial.setIsPhaseIIIClinicalTrial(null);   
                        }
                } else {
                    clinicalTrial.setIsClinicalTrial(YesNoDataType.N_NO);
                }
            }
        }
        return clinicalTrial;
    }

	/**
	 * 
	 * This method gives the Contact person information such as contact
	 * Name,Phone,Fax,EmailAddress,Title,Address.
	 * 
	 * @return ContactPersonInfo object containing contact person details.
	 */
	private ContactPersonInfo getContactPersonInfo() {
		ContactPersonInfo contactPersonInfo = ContactPersonInfo.Factory
				.newInstance();
        DepartmentalPersonDto person = departmentalPersonService.getContactPerson(pdDoc);
        contactPersonInfo.setContactName(globLibV20Generator
                .getHumanNameDataType(person));
        contactPersonInfo.setContactPhone(person.getOfficePhone());
        if (StringUtils.isNotEmpty(person.getFaxNumber())) {
            contactPersonInfo.setContactFax(person.getFaxNumber());
        }
        if (person.getEmailAddress() != null
                && !person.getEmailAddress().equals("")) {
            contactPersonInfo.setContactEmail(person
                    .getEmailAddress());
        }
        contactPersonInfo.setContactTitle(person.getPrimaryTitle());
        contactPersonInfo.setContactAddress(globLibV20Generator
                .getAddressDataType(person));
		return contactPersonInfo;
	}

	/**
	 * 
	 * This method is used to get information of Stem Cells for the form
	 * PHS398CoverPage
	 * 
	 * @return StemCells object containing information about Human stem Cells
	 *         involvement.
	 */
	private StemCells getStemCells() {

	    StemCells stemCells = StemCells.Factory.newInstance();
	    String childAnswer = null;  
	    String answer = getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED,answerHeaders);
	    if (answer != null) {
	        if (!answer.equals(NOT_ANSWERED)) {
				Enum answers = YnqConstant.YES.code().equals(getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED, answerHeaders)) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
	            if (YnqConstant.YES.code().equals(answer)) {
	                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.Y_YES);
	                String subAnswer = getAnswer(SPECIFIC_STEM_CELL_LINE,answerHeaders);
	                if (subAnswer != null) {
	                    if(!subAnswer.equals(NOT_ANSWERED)) {
	                        if (YnqConstant.YES.code().equals(subAnswer)) {
	                            stemCells.setStemCellsIndicator(YesNoDataType.N_NO);
	                            childAnswer = getAnswers(REGISTRATION_NUMBER,answerHeaders);
	                        }
	                        else {
	                            stemCells.setStemCellsIndicator(YesNoDataType.Y_YES);
	                        }
	                    }
	                }
	                if (childAnswer != null) {
	                    if (FieldValueConstants.VALUE_UNKNOWN.equalsIgnoreCase(childAnswer)) {
	                        stemCells.setStemCellsIndicator(answers);
	                    } else {
	                        List<String> cellLines = getCellLines(childAnswer);
	                        if (cellLines.size() > 0) {
	                            stemCells.setCellLinesArray(cellLines.toArray(new String[0]));
	                        }
	                    }
	                }
	            } else {
	                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.N_NO); 
	            }
	        }
	    }
	    return stemCells;
	}
	
	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398CoverPageSupplement14Document} by populating data from the
	 * given {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	@Override
	public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getCoverPageSupplement();
	}

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
