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

import java.io.ByteArrayInputStream;


import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.HumanSubjectSection;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.IntroductionToApplication;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.ProgressReportPublicationList;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.ResearchStrategy;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.SpecificAims;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.HumanSubjectSection.InclusionOfChildren;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.HumanSubjectSection.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.HumanSubjectSection.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.ConsortiumContractualArrangements;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.LettersOfSupport;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.MultiplePDPILeadershipPlan;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.ResourceSharingPlans;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.SelectAgentResearch;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.VertebrateAnimals;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;

import org.apache.xmlbeans.XmlObject;
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
@FormGenerator("PHS398ResearchPlan_2_0V2_0Generator")
public class PHS398ResearchPlan_2_0V2_0Generator extends
		PHS398ResearchPlanBaseGenerator {

    @Value("http://apply.grants.gov/forms/PHS398_ResearchPlan_2_0-V2.0")
    private String namespace;

    @Value("PHS398_ResearchPlan_2_0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PHS398_ResearchPlan_2_0-V2.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398ResearchPlan20V20")
    private String packageName;

    @Value("195")
    private int sortIndex;

    /**
	 * 
	 * This method gives the list of attachments for PHS398ResearchPlan form
	 * 
	 * @return phsResearchPlanDocument {@link XmlObject} of type
	 *         PHS398ResearchPlan20Document.
	 */
	private PHS398ResearchPlan20Document getPHS398ResearchPlan() {
		PHS398ResearchPlan20Document phsResearchPlanDocument = PHS398ResearchPlan20Document.Factory
				.newInstance();
		PHS398ResearchPlan20 phsResearchPlan = PHS398ResearchPlan20.Factory
				.newInstance();
		phsResearchPlan.setFormVersion(FormVersion.v2_0.getVersion());
		ResearchPlanAttachments researchPlanAttachments = ResearchPlanAttachments.Factory
				.newInstance();
		getNarrativeAttachments(researchPlanAttachments);
		AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		attachmentGroupMin0Max100DataType
				.setAttachedFileArray(getAppendixAttachedFileDataTypes());
		researchPlanAttachments.setAppendix(attachmentGroupMin0Max100DataType);
		phsResearchPlan.setResearchPlanAttachments(researchPlanAttachments);
		phsResearchPlanDocument.setPHS398ResearchPlan20(phsResearchPlan);
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(phsResearchPlanDocument.toString().getBytes());            
		sortAttachments(byteArrayInputStream);		
		return phsResearchPlanDocument;
	}

	private void getNarrativeAttachments(ResearchPlanAttachments researchPlanAttachments) {
		final HumanSubjectSection humanSubjectSection = HumanSubjectSection.Factory.newInstance();
		final OtherResearchPlanSections otherResearchPlanSections = OtherResearchPlanSections.Factory.newInstance();

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
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398ResearchPlan20Document} by populating data from the given
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
