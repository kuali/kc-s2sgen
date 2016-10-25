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

import gov.grants.apply.forms.nsfCoverPage16V16.NSFCoverPage16Document;
import gov.grants.apply.forms.nsfCoverPage16V16.NSFCoverPage16Document.NSFCoverPage16;
import gov.grants.apply.forms.nsfCoverPage16V16.NSFCoverPage16Document.NSFCoverPage16.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin1Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@FormGenerator("NSFCoverPageV1_6Generator")
public class NSFCoverPageV1_6Generator extends NSFCoverPageBaseGenerator {

    private static final int MENTORING_PLAN = 147;
    private static final int DATA_MANAGEMENT_PLAN = 146;
    private static final int LOBBYING_ACTIVITIES_QUESTION = 11;

    @Value("http://apply.grants.gov/forms/NSF_CoverPage_1_6-V1.6")
    private String namespace;

    @Value("NSF_CoverPage_1_6-V1.6")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/NSF_CoverPage-V1.6.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.nsfCoverPage16V16")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    private NSFCoverPage16Document getNSFCoverPage16() {
        NSFCoverPage16Document nsfCoverPage16Document = NSFCoverPage16Document.Factory.newInstance();
        NSFCoverPage16 nsfCoverPage16 = NSFCoverPage16.Factory.newInstance();
        nsfCoverPage16.setFormVersion(FormVersion.v1_6.getVersion());
        setFundingOpportunityNumber(nsfCoverPage16);

        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getClosingDate() != null) {
            nsfCoverPage16.setDueDate(pdDoc.getDevelopmentProposal().getS2sOpportunity().getClosingDate());
        }
        nsfCoverPage16.setNSFUnitConsideration(getNSFUnitConsideration());
        setOtherInfo(nsfCoverPage16);
        AttachmentGroupMin1Max100DataType attachmentGroup = AttachmentGroupMin1Max100DataType.Factory.newInstance();
        attachmentGroup.setAttachedFileArray(getAttachedFileDataTypes());
        if (attachmentGroup.getAttachedFileArray().length > 0) {
            nsfCoverPage16.setSingleCopyDocuments(attachmentGroup);
        }
        final AttachedFileDataType dataManagementPlan = getAttachedNarrativeFile(DATA_MANAGEMENT_PLAN);
        if (dataManagementPlan != null) {
            nsfCoverPage16.setDataManagementPlan(dataManagementPlan);
        }

        final AttachedFileDataType mentoringPlan = getAttachedNarrativeFile(MENTORING_PLAN);
        if (mentoringPlan != null) {
            nsfCoverPage16.setMentoringPlan(mentoringPlan);
        }
        nsfCoverPage16Document.setNSFCoverPage16(nsfCoverPage16);
        return nsfCoverPage16Document;
    }


    private void setFundingOpportunityNumber(NSFCoverPage16 nsfCoverPage16) {
        nsfCoverPage16.setFundingOpportunityNumber(StringUtils.substring(pdDoc.getDevelopmentProposal().getProgramAnnouncementNumber(), 0, PROGRAM_ANNOUNCEMENT_NUMBER_MAX_LENGTH));
    }

    private void setOtherInfo(NSFCoverPage16 nsfCoverPage16) {
        OtherInfo otherInfo = OtherInfo.Factory.newInstance();
        PIInfo pInfo = PIInfo.Factory.newInstance();
        for (AnswerContract questionnaireAnswer : getPropDevQuestionAnswerService().getQuestionnaireAnswers(pdDoc.getDevelopmentProposal().getProposalNumber(), getNamespace(), getFormName())) {
            String answer = questionnaireAnswer.getAnswer();
            int questionId = questionnaireAnswer.getQuestionNumber();

            if (answer != null) {
                switch (questionId) {
                    case QUESTION_CURRENT_PI:
                        pInfo.setIsCurrentPI(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        break;
                    case QUESTION_BEGIN_INVESTIGATOR:
                        otherInfo.setIsBeginInvestigator(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        break;
                    case QUESTION_ACCOMPLISHMENT_RENEWAL:
                        otherInfo.setIsAccomplishmentRenewal(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        break;
                    case FUNDING_MECHANISM_QUESTION:
                        setFundingMechanism(nsfCoverPage16, answer);
                        break;
                    case LOBBYING_ACTIVITIES_QUESTION:
                        otherInfo.setIsDisclosureLobbyingActivities(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                    default:
                        break;
                }
            }
        }
        nsfCoverPage16.setPIInfo(pInfo);
        nsfCoverPage16.setOtherInfo(otherInfo);
    }

    private void setFundingMechanism(NSFCoverPage16 nsfCoverPage16, String answer) {
        FundingMechanism.Enum fundingMechanism = FundingMechanism.RAPID;
        if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.CONFERENCE.toString())) {
            fundingMechanism = FundingMechanism.CONFERENCE;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.EAGER.toString())) {
            fundingMechanism = FundingMechanism.EAGER;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.EQUIPMENT.toString())) {
            fundingMechanism = FundingMechanism.EQUIPMENT;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.FACILITY_CENTER.toString())) {
            fundingMechanism = FundingMechanism.FACILITY_CENTER;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.FELLOWSHIP.toString())) {
            fundingMechanism = FundingMechanism.FELLOWSHIP;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.IDEAS_LAB.toString())) {
            fundingMechanism = FundingMechanism.IDEAS_LAB;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.INTERNATIONAL_TRAVEL.toString())) {
            fundingMechanism = FundingMechanism.INTERNATIONAL_TRAVEL;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.RESEARCH_OTHER_THAN_RAPID_OR_EAGER.toString())) {
            fundingMechanism = FundingMechanism.RESEARCH_OTHER_THAN_RAPID_OR_EAGER;
        }
        nsfCoverPage16.setFundingMechanism(fundingMechanism);
    }

    private NSFUnitConsideration getNSFUnitConsideration() {
        NSFUnitConsideration nsfConsideration = NSFUnitConsideration.Factory.newInstance();
        nsfConsideration.setDivisionCode(pdDoc.getDevelopmentProposal().getAgencyDivisionCode());
        nsfConsideration.setProgramCode(pdDoc.getDevelopmentProposal().getAgencyProgramCode());
        return nsfConsideration;
    }

    private AttachedFileDataType[] getAttachedFileDataTypes() {
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<>();
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null) {
                int narrativeTypeCode = Integer.parseInt(narrative.getNarrativeType().getCode());
                if (narrativeTypeCode == PERSONAL_DATA || narrativeTypeCode == PROPRIETARY_INFORMATION || narrativeTypeCode == SINGLE_COPY_DOCUMENT) {
                    AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        attachedFileDataTypeList.add(attachedFileDataType);
                    }
                }
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[attachedFileDataTypeList.size()]);
    }

    private AttachedFileDataType getAttachedNarrativeFile(int narrativeCode) {
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null && Integer.parseInt(narrative.getNarrativeType().getCode()) == narrativeCode) {
                AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    return attachedFileDataType;
                }
            }
        }
        return null;
    }

    @Override
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getNSFCoverPage16();
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
