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

import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13.ApplicationType;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13.ResearchPlanAttachments;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13.ResearchPlanAttachments.*;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13.ResearchPlanAttachments.HumanSubjectSection.InclusionOfChildren;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13.ResearchPlanAttachments.HumanSubjectSection.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13.ResearchPlanAttachments.HumanSubjectSection.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13.ResearchPlanAttachments.HumanSubjectSection.TargetedPlannedEnrollmentTable;
import gov.grants.apply.forms.phs398ResearchPlan13V13.PHS398ResearchPlan13Document.PHS398ResearchPlan13.ResearchPlanAttachments.OtherResearchPlanSections.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.io.ByteArrayInputStream;

/**
 * Class for generating the XML object for grants.gov PHS398ResearchPlanV1_2.
 * Form is generated using XMLBean classes and is based on
 * PHS398ResearchPlanV1_2 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PHS398ResearchPlanV1_3Generator")
public class PHS398ResearchPlanV1_3Generator extends
		PHS398ResearchPlanBaseGenerator {

    @Value("http://apply.grants.gov/forms/PHS398_ResearchPlan_1_3-V1.3")
    private String namespace;

    @Value("PHS398_ResearchPlan_1_3")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PHS398_ResearchPlan-V1.3.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398ResearchPlan13V13")
    private String packageName;

    @Value("195")
    private int sortIndex;

    /**
	 * 
	 * This method gives the list of attachments for PHS398ResearchPlan form
	 * 
	 * @return phsResearchPlanDocument {@link XmlObject} of type
	 *         PHS398ResearchPlan13Document.
	 */
	private PHS398ResearchPlan13Document getPHS398ResearchPlan() {
		PHS398ResearchPlan13Document phsResearchPlanDocument = PHS398ResearchPlan13Document.Factory
				.newInstance();
		PHS398ResearchPlan13 phsResearchPlan = PHS398ResearchPlan13.Factory
				.newInstance();
		phsResearchPlan.setFormVersion(FormVersion.v1_3.getVersion());
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
		phsResearchPlanDocument.setPHS398ResearchPlan13(phsResearchPlan);
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(phsResearchPlanDocument.toString().getBytes());            
		sortAttachments(byteArrayInputStream);		
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
					case RESEARCH_STRATEGY:
						ResearchStrategy researchStrategy = ResearchStrategy.Factory.newInstance();
						researchStrategy.setAttFile(attachedFileDataType);
						researchPlanAttachments.setResearchStrategy(researchStrategy);
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
			TypeOfApplication.Enum typeOfApplication = TypeOfApplication.Enum
					.forInt(Integer.parseInt(developmentProposal
							.getProposalType().getCode()));
			applicationType.setTypeOfApplication(typeOfApplication);
		}
		return applicationType;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398ResearchPlan13Document} by populating data from the given
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
