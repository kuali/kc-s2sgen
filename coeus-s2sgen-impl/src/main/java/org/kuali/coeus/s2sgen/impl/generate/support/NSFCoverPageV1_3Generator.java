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

import gov.grants.apply.forms.nsfCoverPage13V13.NSFCoverPage13Document;
import gov.grants.apply.forms.nsfCoverPage13V13.NSFCoverPage13Document.NSFCoverPage13;
import gov.grants.apply.forms.nsfCoverPage13V13.NSFCoverPage13Document.NSFCoverPage13.NSFUnitConsideration;
import gov.grants.apply.forms.nsfCoverPage13V13.NSFCoverPage13Document.NSFCoverPage13.OtherInfo;
import gov.grants.apply.forms.nsfCoverPage13V13.NSFCoverPage13Document.NSFCoverPage13.PIInfo;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin1Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.org.OrganizationYnqContract;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.location.ProposalSiteContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.ArrayList;
import java.util.List;


/**
 * 
 * This class is used to generate XML Document object for grants.gov
 * NSFCoverPageV1.2. This form is generated using XMLBean API's generated by
 * compiling NSFCoverPageV1.2 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("NSFCoverPageV1_3Generator")
public class NSFCoverPageV1_3Generator extends NSFCoverPageBaseGenerator {

    @Value("http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3")
    private String namespace;

    @Value("NSF_CoverPage_1_3-V1.3")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/NSF_CoverPage-V1.3.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.nsfCoverPage13V13")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

	/**
	 * 
	 * This method returns NSFCoverPage13Document object based on proposal
	 * development document which contains the NSFCoverPage13Document
	 * informations
	 * NSFUnitConsideration,FundingOpportunityNumber,PIInfo,CoPIInfo,OtherInfo,and
	 * SingleCopyDocuments for a particular proposal
	 * 
	 * @return nsfCoverPage13Document {@link XmlObject} of type
	 *         NSFCoverPage13Document.
	 */
	private NSFCoverPage13Document getNSFCoverPage13() {
		NSFCoverPage13Document nsfCoverPage13Document = NSFCoverPage13Document.Factory
				.newInstance();
		NSFCoverPage13 nsfCoverPage13 = NSFCoverPage13.Factory.newInstance();
		nsfCoverPage13.setFormVersion(FormVersion.v1_3.getVersion());
		setFundingOpportunityNumber(nsfCoverPage13);
		if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null
				&& pdDoc.getDevelopmentProposal().getS2sOpportunity()
						.getClosingDate() != null) {
			nsfCoverPage13.setDueDate(pdDoc
					.getDevelopmentProposal().getS2sOpportunity()
					.getClosingDate());
		}
		nsfCoverPage13.setNSFUnitConsideration(getNSFUnitConsideration());
		setOtherInfo(nsfCoverPage13);
		AttachmentGroupMin1Max100DataType attachmentGroup = AttachmentGroupMin1Max100DataType.Factory
				.newInstance();
		attachmentGroup.setAttachedFileArray(getAttachedFileDataTypes());
		if(attachmentGroup.getAttachedFileArray().length > 0)
			nsfCoverPage13.setSingleCopyDocuments(attachmentGroup);
		
		nsfCoverPage13Document.setNSFCoverPage13(nsfCoverPage13);
		return nsfCoverPage13Document;
	}

	private void setFundingOpportunityNumber(NSFCoverPage13 nsfCoverPage13) {
		if (pdDoc.getDevelopmentProposal().getProgramAnnouncementNumber() != null) {
			if (pdDoc.getDevelopmentProposal().getProgramAnnouncementNumber()
					.length() > PROGRAM_ANNOUNCEMENT_NUMBER_MAX_LENGTH) {
				nsfCoverPage13.setFundingOpportunityNumber(pdDoc
						.getDevelopmentProposal()
						.getProgramAnnouncementNumber().substring(0,
								PROGRAM_ANNOUNCEMENT_NUMBER_MAX_LENGTH));
			} else {
				nsfCoverPage13.setFundingOpportunityNumber(pdDoc
						.getDevelopmentProposal()
						.getProgramAnnouncementNumber());
			}
		}
	}

	/**
	 * 
	 * This method returns Investigator
	 * status,DisclosureLobbyingActivities,ExploratoryResearch,HistoricPlaces,
	 * HighResolutionGraphics and AccomplishmentRenewal information for the
	 * OtherInfo type.
	 */
	private void setOtherInfo(NSFCoverPage13 nsfCoverPage13) {
		OtherInfo otherInfo = OtherInfo.Factory.newInstance();
		PIInfo pInfo = PIInfo.Factory.newInstance();
		for (AnswerContract questionnaireAnswer : getPropDevQuestionAnswerService().getQuestionnaireAnswers(pdDoc.getDevelopmentProposal().getProposalNumber(),getNamespace(),getFormName())) {
			String answer = questionnaireAnswer.getAnswer();
			int questionId = questionnaireAnswer.getQuestionNumber();
			
			if (answer != null) {
				switch (questionId) {
				case QUESTION_CURRENT_PI:
					pInfo
							.setIsCurrentPI(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case QUESTION_BEGIN_INVESTIGATOR:
					otherInfo
							.setIsBeginInvestigator(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case QUESTION_EARLY_CONCEPT_GRANT:
					otherInfo
							.setIsEarlyConceptGrant(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case QUESTION_RAPIDRESPONSE_GRANT:
					otherInfo
							.setIsRapidResponseGrant(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case QUESTION_ACCOMPLISHMENT_RENEWAL:
					otherInfo
							.setIsAccomplishmentRenewal(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case QUESTION_RESOLUTION_GRAPHICS:
					otherInfo
							.setIsHighResolutionGraphics(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				default:
					break;
				}
			}
		}
		nsfCoverPage13.setPIInfo(pInfo);
		otherInfo.setIsDisclosureLobbyingActivities(getLobbyingAnswer());
		nsfCoverPage13.setOtherInfo(otherInfo);
	}

	/**
	 * 
	 * This method YesNo data type Lobbying answers based on the ProposalYnq
	 * QuestionId
	 * 
	 * @return answer (YesNoDataType.Enum) corresponding to Ynq question id.
	 */
	private YesNoDataType.Enum getLobbyingAnswer() {
		YesNoDataType.Enum answer = YesNoDataType.N_NO;
		
		for (ProposalPersonContract proposalPerson : pdDoc.getDevelopmentProposal()
				.getProposalPersons()) {
		  	if (proposalPerson.getProposalPersonRoleId() != null
					&& proposalPerson.getProposalPersonRoleId().equals(
							PRINCIPAL_INVESTIGATOR)
					|| PI_C0_INVESTIGATOR.equals(proposalPerson.getProposalPersonRoleId())) {
                List<? extends AnswerHeaderContract> headers=getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
                if (!headers.isEmpty()) {
                    AnswerHeaderContract answerHeader = headers.get(0);
                    List<? extends AnswerContract> certificationAnswers = answerHeader.getAnswers();

                    for (AnswerContract certificatonAnswer : certificationAnswers) {
                        if (certificatonAnswer != null
                                && PROPOSAL_YNQ_LOBBYING_ACTIVITIES
                                .equals(certificatonAnswer.getQuestionId().toString())
                                && YnqConstant.YES.code()
                                .equals(certificatonAnswer.getAnswer())) {
                            return YesNoDataType.Y_YES;
                        }
                    }
                }
			}
		}
		OrganizationContract organization = getOrganizationFromDevelopmentProposal(pdDoc.getDevelopmentProposal());
		if (organization != null) {
            for (OrganizationYnqContract organizationYnq : organization.getOrganizationYnqs()) {
                if (organizationYnq.getQuestionId().equals(LOBBYING_QUESTION_ID)) {
                    if(getAnswerFromOrganizationYnq(organizationYnq)){
                        return YesNoDataType.Y_YES;
                    }
                }
            }
        }

		return answer;
	}

    /*
     * This method return true if question is answered otherwise false .
     */
    protected boolean getAnswerFromOrganizationYnq(OrganizationYnqContract organizationYnq) {
        return organizationYnq.getAnswer().equals(ANSWER_INDICATOR_VALUE);
    }

	/*
     * This method will get the Organization from the Development proposal.
     */
	    private OrganizationContract getOrganizationFromDevelopmentProposal(DevelopmentProposalContract developmentProposal) {
	        OrganizationContract organization = null;
	        ProposalSiteContract proposalSite = developmentProposal.getApplicantOrganization();
	        if (proposalSite != null) {
	            organization = proposalSite.getOrganization();
	        }
	        return organization;
	    }
   
	/**
	 * 
	 * This method returns DivisionCode and ProgramCode information for the
	 * NSFUnitConsideration type.
	 * 
	 * @return NSFUnitConsideration object containing unit consideration
	 *         informations like Division Code and Program code.
	 */
	private NSFUnitConsideration getNSFUnitConsideration() {
		NSFUnitConsideration nsfConsideration = NSFUnitConsideration.Factory
				.newInstance();
		nsfConsideration.setDivisionCode(pdDoc.getDevelopmentProposal()
				.getAgencyDivisionCode());
		nsfConsideration.setProgramCode(pdDoc.getDevelopmentProposal()
				.getAgencyProgramCode());
		return nsfConsideration;
	}

	/**
	 * 
	 * This method returns attachment type for the form and it can be of type
	 * Personal Data or Proprietary Information.
	 * 
	 * @return AttachedFileDataType[] array of attachments based on the
	 *         narrative type code.
	 */
	private AttachedFileDataType[] getAttachedFileDataTypes() {
		List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<>();
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null) {
				int narrativeTypeCode = Integer.parseInt(narrative.getNarrativeType().getCode());
				if (narrativeTypeCode == PERSONAL_DATA
						|| narrativeTypeCode == PROPRIETARY_INFORMATION 
						|| narrativeTypeCode == SINGLE_COPY_DOCUMENT) {
                    AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
					if(attachedFileDataType != null){
						attachedFileDataTypeList.add(attachedFileDataType);
					}
				}
			}
		}
		return attachedFileDataTypeList
				.toArray(new AttachedFileDataType[attachedFileDataTypeList
						.size()]);
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link NSFCoverPage13Document} by populating data from the given
	 * {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getNSFCoverPage13();
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
