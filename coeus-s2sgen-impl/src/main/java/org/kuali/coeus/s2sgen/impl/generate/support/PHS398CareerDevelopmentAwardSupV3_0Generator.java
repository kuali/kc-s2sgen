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

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup30V30.NonUSCitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup30V30.PHS398CareerDevelopmentAwardSup30Document;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup30V30.PHS398CareerDevelopmentAwardSup30Document.PHS398CareerDevelopmentAwardSup30;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup30V30.PHS398CareerDevelopmentAwardSup30Document.PHS398CareerDevelopmentAwardSup30.CareerDevelopmentAwardAttachments;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup30V30.PHS398CareerDevelopmentAwardSup30Document.PHS398CareerDevelopmentAwardSup30.CareerDevelopmentAwardAttachments.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;

import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.person.attr.CitizenshipType;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;


@FormGenerator("PHS398CareerDevelopmentAwardSupV3_0Generator")
public class PHS398CareerDevelopmentAwardSupV3_0Generator extends
		PHS398CareerDevelopmentAwardSupBaseGenerator {

	private static final int NARRATIVE_TYPE_PHS_CAREER_CANDIDATE_INFO_AND_GOALS = 153;
	private static final int NARRATIVE_TYPE_PHS_CAREER_DATA_SAFTEY_MONITORING_PLAN = 154;
	private static final int NARRATIVE_TYPE_PHS_CAREER_KEY_BIO_CHEM_RESOURCES = 155;

    @Value("http://apply.grants.gov/forms/PHS398_CareerDevelopmentAwardSup_3_0-V3.0")
    private String namespace;

    @Value("PHS398_CareerDevelopmentAwardSup_3_0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PHS398_CareerDevelopmentAwardSup-V3.0.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398CareerDevelopmentAwardSup30V30")
    private String packageName;

    @Value("200")
    private int sortIndex;

    private XmlObject getPHS398CareerDevelopmentAwardSup() {
	    PHS398CareerDevelopmentAwardSup30Document phs398CareerDevelopmentAwardSupDocument = PHS398CareerDevelopmentAwardSup30Document.Factory.newInstance();
		PHS398CareerDevelopmentAwardSup30 phs398CareerDevelopmentAwardSup30 = PHS398CareerDevelopmentAwardSup30.Factory.newInstance();
		phs398CareerDevelopmentAwardSup30.setFormVersion(FormVersion.v3_0.getVersion());
		setCitizenshipInfo(phs398CareerDevelopmentAwardSup30);
		phs398CareerDevelopmentAwardSup30.setCareerDevelopmentAwardAttachments(getCareerDevelopmentAwardAttachments());
		phs398CareerDevelopmentAwardSupDocument.setPHS398CareerDevelopmentAwardSup30(phs398CareerDevelopmentAwardSup30);
		return phs398CareerDevelopmentAwardSupDocument;
	}

	private void setCitizenshipInfo(PHS398CareerDevelopmentAwardSup30 phs398CareerDevelopmentAwardSup30) {
		final CitizenshipType citizenShip = pdDoc.getDevelopmentProposal().getProposalPersons().stream()
				.filter(ProposalPersonContract::isInvestigator)
				.map(proposalPerson -> s2SProposalPersonService.getCitizenship(proposalPerson))
				.findFirst()
				.orElse(null);

		if(CitizenshipType.NON_US_CITIZEN_WITH_TEMPORARY_VISA.equals(citizenShip)) {
			phs398CareerDevelopmentAwardSup30.setIsNonUSCitizenship(NonUSCitizenshipDataType.TEMPORARY_U_S_VISA);
			phs398CareerDevelopmentAwardSup30.setCitizenshipIndicator(YesNoDataType.N_NO);
		} else if(CitizenshipType.PERMANENT_RESIDENT_OF_US.equals(citizenShip)) {
			phs398CareerDevelopmentAwardSup30.setIsNonUSCitizenship(NonUSCitizenshipDataType.PERMANENT_U_S_RESIDENT_VISA);
			phs398CareerDevelopmentAwardSup30.setCitizenshipIndicator(YesNoDataType.N_NO);
		} else if(CitizenshipType.NOT_RESIDING_IN_US.equals(citizenShip)) {
			phs398CareerDevelopmentAwardSup30.setIsNonUSCitizenship(NonUSCitizenshipDataType.NOT_RESIDING_IN_THE_U_S);
			phs398CareerDevelopmentAwardSup30.setCitizenshipIndicator(YesNoDataType.N_NO);
		} else if(CitizenshipType.TEMP_VISA_ALSO_APPLIED_FOR_PERM_RESIDENT_STATUS.equals(citizenShip)) {
			phs398CareerDevelopmentAwardSup30.setIsNonUSCitizenship(NonUSCitizenshipDataType.TEMPORARY_U_S_VISA);
			phs398CareerDevelopmentAwardSup30.setPermanentResidentByAwardIndicator(YesNoDataType.Y_YES);
			phs398CareerDevelopmentAwardSup30.setCitizenshipIndicator(YesNoDataType.N_NO);
		} else if(CitizenshipType.US_CITIZEN_OR_NONCITIZEN_NATIONAL.equals(citizenShip)) {
			phs398CareerDevelopmentAwardSup30.setCitizenshipIndicator(YesNoDataType.Y_YES);
		}
        if(phs398CareerDevelopmentAwardSup30.getCitizenshipIndicator() == null) {
            phs398CareerDevelopmentAwardSup30.setCitizenshipIndicator(YesNoDataType.N_NO);
            phs398CareerDevelopmentAwardSup30.setIsNonUSCitizenship(null);
        }
	}

	private CareerDevelopmentAwardAttachments getCareerDevelopmentAwardAttachments() {
		CareerDevelopmentAwardAttachments careerDevelopmentAwardAttachments = CareerDevelopmentAwardAttachments.Factory.newInstance();
		AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory.newInstance();
		List<AttachedFileDataType> attachedFileList = new ArrayList<>();
		AttachedFileDataType attachedFileDataType;
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
			int narrativeTypeCode = Integer.parseInt(narrative.getNarrativeType().getCode());
			switch (narrativeTypeCode) {
			case NARRATIVE_TYPE_INTRODUCTION_TO_APPLICATION:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory.newInstance();
				introductionToApplication.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setIntroductionToApplication(introductionToApplication);
				break;
			case NARRATIVE_TYPE_SPECIFIC_AIMS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SpecificAims specificAims = SpecificAims.Factory.newInstance();
				specificAims.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setSpecificAims(specificAims);
				break;			
			case NARRATIVE_TYPE_PROGRESS_REPORT_PUBLICATION_LIST:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory.newInstance();
				progressReportPublicationList.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setProgressReportPublicationList(progressReportPublicationList);
				break;
			case NARRATIVE_TYPE_PROTECTION_OF_HUMAN_SUBJECTS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null) {
	                continue;
	            }
				ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory.newInstance();
				protectionOfHumanSubjects.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
				break;
			case NARRATIVE_TYPE_INCLUSION_OF_WOMEN_AND_MINORITIES:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory.newInstance();
				inclusionOfWomenAndMinorities.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
				break;			
			case NARRATIVE_TYPE_INCLUSION_OF_CHILDREN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory.newInstance();
				inclusionOfChildren.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setInclusionOfChildren(inclusionOfChildren);
				break;
			case NARRATIVE_TYPE_VERTEBRATE_ANIMALS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
				vertebrateAnimals.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setVertebrateAnimals(vertebrateAnimals);
				break;
			case NARRATIVE_TYPE_SELECT_AGENT_RESEARCH:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory.newInstance();
				selectAgentResearch.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setSelectAgentResearch(selectAgentResearch);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_CONSORTIUM_CONTRACT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ConsortiumContractualArrangements consortiumContractualArrangements = ConsortiumContractualArrangements.Factory.newInstance();
				consortiumContractualArrangements.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setConsortiumContractualArrangements(consortiumContractualArrangements);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_RESOURCE_SHARING_PLAN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResourceSharingPlans resourceSharingPlans = ResourceSharingPlans.Factory.newInstance();
				resourceSharingPlans.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setResourceSharingPlans(resourceSharingPlans);
				break;
			case NARRATIVE_TYPE_RESPONSIBLE_CONDUCT_OF_RESEARCH:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResponsibleConductOfResearch responsibleConductOfResearch = ResponsibleConductOfResearch.Factory.newInstance();
				responsibleConductOfResearch.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setResponsibleConductOfResearch(responsibleConductOfResearch);
				break;
			case NARRATIVE_TYPE_PHS398_MENTORING_PLAN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				MentoringPlan mentoringPlan = MentoringPlan.Factory.newInstance();
				mentoringPlan.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setMentoringPlan(mentoringPlan);
				break;
			case NARRATIVE_TYPE_PHS398_MENTOR_STATEMENTS_LETTERS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				StatementsOfSupport statementsOfSupport = StatementsOfSupport.Factory.newInstance();
				statementsOfSupport.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setStatementsOfSupport(statementsOfSupport);
				break;
			case NARRATIVE_TYPE_PSH398_INSTITUTIONAL_ENVIRONMENT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InsitutionalEnvironment insitutionalEnvironment = InsitutionalEnvironment.Factory.newInstance();
				insitutionalEnvironment.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setInsitutionalEnvironment(insitutionalEnvironment);
				break;
			case NARRATIVE_TYPE_PHS398_INSTITUTIONAL_COMMITMENT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InstitutionalCommitment institutionalCommitment = InstitutionalCommitment.Factory.newInstance();
				institutionalCommitment.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setInstitutionalCommitment(institutionalCommitment);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_APPENDIX:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				attachedFileList.add(attachedFileDataType);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_REASEARCH_STRATEGY:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResearchStrategy researchStrategy = ResearchStrategy.Factory.newInstance();
				researchStrategy.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setResearchStrategy(researchStrategy);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_SUPPPORT_LTRS:
                attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType == null){
                    continue;
                }
                LettersOfSupport lettersOfSupport = LettersOfSupport.Factory.newInstance();
                lettersOfSupport.setAttFile(attachedFileDataType);
                careerDevelopmentAwardAttachments.setLettersOfSupport(lettersOfSupport);
                break;
			case NARRATIVE_TYPE_PHS_CAREER_CANDIDATE_INFO_AND_GOALS:
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType == null){
					continue;
				}
				CandidateInformationAndGoals candidateInformationAndGoals = CandidateInformationAndGoals.Factory.newInstance();
				candidateInformationAndGoals.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setCandidateInformationAndGoals(candidateInformationAndGoals);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_DATA_SAFTEY_MONITORING_PLAN:
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType == null){
					continue;
				}
				DataSafetyMonitoringPlan dataSafetyMonitoringPlan = DataSafetyMonitoringPlan.Factory.newInstance();
				dataSafetyMonitoringPlan.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setDataSafetyMonitoringPlan(dataSafetyMonitoringPlan);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_KEY_BIO_CHEM_RESOURCES:
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType == null){
					continue;
				}
				KeyBiologicalAndOrChemicalResources keyBiologicalAndOrChemicalResources = KeyBiologicalAndOrChemicalResources.Factory.newInstance();
				keyBiologicalAndOrChemicalResources.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setKeyBiologicalAndOrChemicalResources(keyBiologicalAndOrChemicalResources);
				break;
			}
		}
		attachmentGroupMin0Max100DataType.setAttachedFileArray(attachedFileList.toArray(new AttachedFileDataType[0]));
		careerDevelopmentAwardAttachments.setAppendix(attachmentGroupMin0Max100DataType);

		setMandatoryAttachments(careerDevelopmentAwardAttachments);
		return careerDevelopmentAwardAttachments;
	}

	/**
	 * This set any mandatory attachments that aren't set to blank objects so that validation errors can be more user friendly.
	 */
	private void setMandatoryAttachments(CareerDevelopmentAwardAttachments careerDevelopmentAwardAttachments) {
		if (careerDevelopmentAwardAttachments.getResearchStrategy() == null) {
			careerDevelopmentAwardAttachments.setResearchStrategy(ResearchStrategy.Factory.newInstance());
		}
	}
	
	@Override
	public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getPHS398CareerDevelopmentAwardSup();
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
