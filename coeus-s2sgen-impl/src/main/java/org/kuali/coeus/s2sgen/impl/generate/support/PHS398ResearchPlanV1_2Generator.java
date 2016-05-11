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

import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ApplicationType;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.*;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.HumanSubjectSection.InclusionOfChildren;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.HumanSubjectSection.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.HumanSubjectSection.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.HumanSubjectSection.TargetedPlannedEnrollmentTable;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.OtherResearchPlanSections.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

/**
 * Class for generating the XML object for grants.gov PHS398ResearchPlanV1_2.
 * Form is generated using XMLBean classes and is based on
 * PHS398ResearchPlanV1_2 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PHS398ResearchPlanV1_2Generator")
public class PHS398ResearchPlanV1_2Generator extends
		PHS398ResearchPlanBaseGenerator {

    @Value("http://apply.grants.gov/forms/PHS398_ResearchPlan_1_2-V1.2")
    private String namespace;

    @Value("PHS398_ResearchPlan_1_2-V1.2")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PHS398_ResearchPlan-V1.2.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398ResearchPlan12V12")
    private String packageName;

    @Value("195")
    private int sortIndex;

	/**
	 * 
	 * This method gives the list of attachments for PHS398ResearchPlan form
	 * 
	 * @return phsResearchPlanDocument {@link XmlObject} of type
	 *         PHS398ResearchPlan12Document.
	 */
	private PHS398ResearchPlan12Document getPHS398ResearchPlan() {
		PHS398ResearchPlan12Document phsResearchPlanDocument = PHS398ResearchPlan12Document.Factory
				.newInstance();
		PHS398ResearchPlan12 phsResearchPlan = PHS398ResearchPlan12.Factory
				.newInstance();
		phsResearchPlan.setFormVersion(FormVersion.v1_2.getVersion());
		phsResearchPlan.setApplicationType(getApplicationType());
		ResearchPlanAttachments researchPlanAttachments = ResearchPlanAttachments.Factory
				.newInstance();
		getNarrativeAttachments(researchPlanAttachments);
		AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		attachmentGroupMin0Max100DataType
				.setAttachedFileArray(getAppendixAttachedFileDataTypes());
		researchPlanAttachments.setAppendix(attachmentGroupMin0Max100DataType);
		phsResearchPlan.setResearchPlanAttachments(researchPlanAttachments);
		phsResearchPlanDocument.setPHS398ResearchPlan12(phsResearchPlan);
		return phsResearchPlanDocument;
	}

	private void getNarrativeAttachments(ResearchPlanAttachments researchPlanAttachments) {
		HumanSubjectSection humanSubjectSection = HumanSubjectSection.Factory.newInstance();
		OtherResearchPlanSections otherResearchPlanSections = OtherResearchPlanSections.Factory.newInstance();

		pdDoc.getDevelopmentProposal().getNarratives().stream().forEach(narrative -> {
			final AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
			if (attachedFileDataType != null) {
				switch (Integer.parseInt(narrative.getNarrativeType().getCode())) {
					case INTRODUCTION_TO_APPLICATION:
						IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory.newInstance();
						introductionToApplication.setAttFile(attachedFileDataType);
						researchPlanAttachments.setIntroductionToApplication(introductionToApplication);
						break;
					case SPECIFIC_AIMS:
						SpecificAims specificAims = SpecificAims.Factory.newInstance();
						specificAims.setAttFile(attachedFileDataType);
						researchPlanAttachments.setSpecificAims(specificAims);
						break;
					case BACKGROUND_SIGNIFICANCE:
						BackgroundSignificance backgroundSignificance = BackgroundSignificance.Factory.newInstance();
						backgroundSignificance.setAttFile(attachedFileDataType);
						researchPlanAttachments.setBackgroundSignificance(backgroundSignificance);
						break;
					case PROGRESS_REPORT:
						ProgressReport progressReport = ProgressReport.Factory.newInstance();
						progressReport.setAttFile(attachedFileDataType);
						researchPlanAttachments.setProgressReport(progressReport);
						break;
					case RESEARCH_DESIGN_METHODS:
						ResearchDesignMethods researchDesignMethods = ResearchDesignMethods.Factory.newInstance();
						researchDesignMethods.setAttFile(attachedFileDataType);
						researchPlanAttachments.setResearchDesignMethods(researchDesignMethods);
						break;
					case INCLUSION_ENROLLMENT_REPORT:
						InclusionEnrollmentReport inclusionEnrollmentReport = InclusionEnrollmentReport.Factory.newInstance();
						inclusionEnrollmentReport.setAttFile(attachedFileDataType);
						researchPlanAttachments.setInclusionEnrollmentReport(inclusionEnrollmentReport);
						break;
					case PROGRESS_REPORT_PUBLICATION_LIST:
						ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory.newInstance();
						progressReportPublicationList.setAttFile(attachedFileDataType);
						researchPlanAttachments.setProgressReportPublicationList(progressReportPublicationList);
						break;
					case PROTECTION_OF_HUMAN_SUBJECTS:
						ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory.newInstance();
						protectionOfHumanSubjects.setAttFile(attachedFileDataType);
						humanSubjectSection.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
						break;
					case INCLUSION_OF_WOMEN_AND_MINORITIES:
						InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory.newInstance();
						inclusionOfWomenAndMinorities.setAttFile(attachedFileDataType);
						humanSubjectSection.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
						break;
					case TARGETED_PLANNED_ENROLLMENT_TABLE:
						TargetedPlannedEnrollmentTable tarPlannedEnrollmentTable = TargetedPlannedEnrollmentTable.Factory.newInstance();
						tarPlannedEnrollmentTable.setAttFile(attachedFileDataType);
						humanSubjectSection.setTargetedPlannedEnrollmentTable(tarPlannedEnrollmentTable);
						break;
					case INCLUSION_OF_CHILDREN:
						InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory.newInstance();
						inclusionOfChildren.setAttFile(attachedFileDataType);
						humanSubjectSection.setInclusionOfChildren(inclusionOfChildren);
						break;
					case VERTEBRATE_ANIMALS:
						VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
						vertebrateAnimals.setAttFile(attachedFileDataType);
						otherResearchPlanSections.setVertebrateAnimals(vertebrateAnimals);
						break;
					case SELECT_AGENT_RESEARCH:
						SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory.newInstance();
						selectAgentResearch.setAttFile(attachedFileDataType);
						otherResearchPlanSections.setSelectAgentResearch(selectAgentResearch);
						break;
					case MULTIPLE_PI_LEADERSHIP_PLAN:
						MultiplePDPILeadershipPlan multiplePILeadershipPlan = MultiplePDPILeadershipPlan.Factory.newInstance();
						multiplePILeadershipPlan.setAttFile(attachedFileDataType);
						otherResearchPlanSections.setMultiplePDPILeadershipPlan(multiplePILeadershipPlan);
						break;
					case CONSORTIUM_CONTRACTUAL_ARRANGEMENTS:
						ConsortiumContractualArrangements contractualArrangements = ConsortiumContractualArrangements.Factory.newInstance();
						contractualArrangements.setAttFile(attachedFileDataType);
						otherResearchPlanSections.setConsortiumContractualArrangements(contractualArrangements);
						break;
					case LETTERS_OF_SUPPORT:
						LettersOfSupport lettersOfSupport = LettersOfSupport.Factory.newInstance();
						lettersOfSupport.setAttFile(attachedFileDataType);
						otherResearchPlanSections.setLettersOfSupport(lettersOfSupport);
						break;
					case RESOURCE_SHARING_PLANS:
						ResourceSharingPlans resourceSharingPlans = ResourceSharingPlans.Factory.newInstance();
						resourceSharingPlans.setAttFile(attachedFileDataType);
						otherResearchPlanSections.setResourceSharingPlans(resourceSharingPlans);
						break;
				}
			}
		});
		researchPlanAttachments.setHumanSubjectSection(humanSubjectSection);
		researchPlanAttachments.setOtherResearchPlanSections(otherResearchPlanSections);
	}

	/**
	 * 
	 * This method is used to get ApplicationType from
	 * ProposalDevelopmentDocumentContract
	 * 
	 * @return ApplicationType corresponding to the proposal type code.
	 */
	private ApplicationType getApplicationType() {
		ApplicationType applicationType = ApplicationType.Factory.newInstance();
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		if (developmentProposal.getProposalType() != null
				&& Integer.parseInt(developmentProposal.getProposalType().getCode()) < PROPOSAL_TYPE_CODE_6) {
			// Check <6 to ensure that if proposalType='TASk ORDER", it must
			// not be
			// set. THis is because enum ApplicationType has no
			// entry for TASK ORDER
			TypeOfApplication.Enum typeOfApplication = TypeOfApplication.Enum
					.forInt(Integer.parseInt(developmentProposal
							.getProposalType().getCode()));
			applicationType.setTypeOfApplication(typeOfApplication);
		}
		return applicationType;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398ResearchPlan12Document} by populating data from the given
	 * {@link ProposalDevelopmentDocumentContract}
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
		return getPHS398ResearchPlan();
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
