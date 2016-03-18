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

import gov.grants.apply.forms.phs398ResearchTrainingProgramPlan30V30.PHS398ResearchTrainingProgramPlan30Document;
import gov.grants.apply.forms.phs398ResearchTrainingProgramPlan30V30.PHS398ResearchTrainingProgramPlan30Document.PHS398ResearchTrainingProgramPlan30;
import gov.grants.apply.forms.phs398ResearchTrainingProgramPlan30V30.PHS398ResearchTrainingProgramPlan30Document.PHS398ResearchTrainingProgramPlan30.ResearchTrainingProgramPlanAttachments;
import gov.grants.apply.forms.phs398ResearchTrainingProgramPlan30V30.PHS398ResearchTrainingProgramPlan30Document.PHS398ResearchTrainingProgramPlan30.ResearchTrainingProgramPlanAttachments.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@FormGenerator("PHS398ResTrainProgPlanV3_0Generator")
public class PHS398ResTrainProgPlanV3_0Generator extends S2SBaseFormGenerator{
    private static final int PHS_RES_TRAINING_PLAN_INTRODUCTION_112 = 112;
    private static final int PHS_RES_TRAINING_PLAN_METHODS_ENHANCE_REPRO_151 = 151;
    private static final int PHS_RES_TRAINING_PLAN_PROGRAM_PLAN_114 = 114;
    private static final int PHS_RES_TRAINING_PLAN_DATA_SAFTEY_MONITOR_152 = 152;
    private static final int PHS_RES_TRAINING_PLAN_RESP_CONDUCT_SEARCH_116 = 116;
    private static final int PHS_RES_TRAINING_PLAN_PROGRESS_REPORT_117 = 117;
    private static final int PHS_RES_TRAINING_PLAN_HUMAN_SUBJECTS_118 = 118;
    private static final int PHS_RES_TRAINING_PLAN_SELECT_AGENT_SEARCH_120 = 120;
    private static final int PHS_RES_TRAINING_PLAN_VERT_ANIMALS_119 = 119;
    private static final int PHS_RES_TRAINING_PLAN_PI_LEADERSHIP_PLAN_121 = 121;
    private static final int PHS_RES_TRAINING_PLAN_CONS_CONTRACTUAL_122 = 122;
    private static final int PHS_RES_TRAINING_PLAN_FAC_BIOSKETCHES_123 = 123;
    private static final int PHS_RES_TRAINING_PLAN_DATA_TABLES_124 = 124;
    private static final int PHS_RES_TRAINING_PLAN_SUPPORT_LETTERS_125 = 125;
    private static final int PHS_RES_TRAINING_PLAN_APPENDIX_126 = 126;

    @Value("http://apply.grants.gov/forms/PHS398_ResearchTrainingProgramPlan_3_0-V3.0")
    private String namespace;

    @Value("PHS398_ResearchTrainingProgramPlan_3_0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PHS398_ResearchTrainProPlan-V3.0.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398ResearchTrainingProgramPlan30V30")
    private String packageName;

    @Value("220")
    private int sortIndex;

    private PHS398ResearchTrainingProgramPlan30 getPHS398ResearchTrainingProgramPlan(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) throws S2SException {
        DevelopmentProposalContract developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        PHS398ResearchTrainingProgramPlan30 phs398ResTrainProgPlan = PHS398ResearchTrainingProgramPlan30.Factory.newInstance();

        ResearchTrainingProgramPlanAttachments researchTrainingProgramPlanAttachments = Factory.newInstance();
  
        phs398ResTrainProgPlan.setFormVersion(FormVersion.v3_0.getVersion());
        List<AttachedFileDataType> attachedFileDataTypes = new ArrayList<>();

        AttachedFileDataType attachedFileDataType;
        for (NarrativeContract narrative : developmentProposal.getNarratives()) {
             if (narrative.getNarrativeType().getCode() != null) {
                 switch(Integer.parseInt(narrative.getNarrativeType().getCode())){
                     case(PHS_RES_TRAINING_PLAN_INTRODUCTION_112):
                         attachedFileDataType = getAttachedFileType(narrative);
                         IntroductionToApplication introductionToApplication = researchTrainingProgramPlanAttachments.addNewIntroductionToApplication();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         introductionToApplication.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_METHODS_ENHANCE_REPRO_151):
                         attachedFileDataType = getAttachedFileType(narrative);
                         MethodsForEnhancingReproducibility reproducibility = researchTrainingProgramPlanAttachments.addNewMethodsForEnhancingReproducibility();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         reproducibility.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_PROGRAM_PLAN_114):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ProgramPlan programPlan = researchTrainingProgramPlanAttachments.addNewProgramPlan();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         programPlan.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_DATA_SAFTEY_MONITOR_152):
                         attachedFileDataType = getAttachedFileType(narrative);
                         DataSafetyMonitoringPlan dataSafetyMonitoringPlan = researchTrainingProgramPlanAttachments.addNewDataSafetyMonitoringPlan();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         dataSafetyMonitoringPlan.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_RESP_CONDUCT_SEARCH_116):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ResponsibleConductOfResearch responsibleConductOfResearch = researchTrainingProgramPlanAttachments.addNewResponsibleConductOfResearch();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         responsibleConductOfResearch.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_PROGRESS_REPORT_117):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ProgressReport progressReport = researchTrainingProgramPlanAttachments.addNewProgressReport();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         progressReport.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_HUMAN_SUBJECTS_118):
                         attachedFileDataType = getAttachedFileType(narrative);
                         HumanSubjects humanSubjects = researchTrainingProgramPlanAttachments.addNewHumanSubjects();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         humanSubjects.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_SELECT_AGENT_SEARCH_120):
                         attachedFileDataType = getAttachedFileType(narrative);
                         SelectAgentResearch selectAgentResearch = researchTrainingProgramPlanAttachments.addNewSelectAgentResearch();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         selectAgentResearch.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_VERT_ANIMALS_119):
                         attachedFileDataType = getAttachedFileType(narrative);
                         VertebrateAnimals vertebrateAnimals = researchTrainingProgramPlanAttachments.addNewVertebrateAnimals();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         vertebrateAnimals.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_PI_LEADERSHIP_PLAN_121):
                         attachedFileDataType = getAttachedFileType(narrative);
                         MultiplePDPILeadershipPlan multiplePDPILeadershipPlan = researchTrainingProgramPlanAttachments.addNewMultiplePDPILeadershipPlan();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         multiplePDPILeadershipPlan.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_CONS_CONTRACTUAL_122):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ConsortiumContractualArrangements consortiumContractualArrangements = researchTrainingProgramPlanAttachments.addNewConsortiumContractualArrangements();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         consortiumContractualArrangements.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_FAC_BIOSKETCHES_123):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ParticipatingFacultyBiosketches participatingFacultyBiosketches = researchTrainingProgramPlanAttachments.addNewParticipatingFacultyBiosketches();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         participatingFacultyBiosketches.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_DATA_TABLES_124):
                         attachedFileDataType = getAttachedFileType(narrative);
                         DataTables dataTables = researchTrainingProgramPlanAttachments.addNewDataTables();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         dataTables.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_SUPPORT_LETTERS_125):
                         attachedFileDataType = getAttachedFileType(narrative);
                         LettersOfSupport lettersOfSupport = researchTrainingProgramPlanAttachments.addNewLettersOfSupport();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         lettersOfSupport.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_APPENDIX_126):
                         attachedFileDataType = getAttachedFileType(narrative);
                         if(attachedFileDataType == null){
                             continue;
                         }
                         attachedFileDataTypes.add(attachedFileDataType);
                         break;
                 }
             }
         }
         if (!attachedFileDataTypes.isEmpty()){
             AttachmentGroupMin0Max100DataType attachmentGroup = researchTrainingProgramPlanAttachments.addNewAppendix();
             attachmentGroup.setAttachedFileArray(attachedFileDataTypes.toArray(new AttachedFileDataType[0]));
         }
        
         if (researchTrainingProgramPlanAttachments != null)   phs398ResTrainProgPlan.setResearchTrainingProgramPlanAttachments(researchTrainingProgramPlanAttachments);
        
         ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(phs398ResTrainProgPlan.toString().getBytes());            
         sortAttachments(byteArrayInputStream);
        return phs398ResTrainProgPlan;        
    }

    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) throws S2SException {
        PHS398ResearchTrainingProgramPlan30 phs398ResearchTrainingProgramPlan = getPHS398ResearchTrainingProgramPlan(proposalDevelopmentDocument);
        PHS398ResearchTrainingProgramPlan30Document pHS398ResearchTrainingProgramPlan20Document = PHS398ResearchTrainingProgramPlan30Document.Factory.newInstance();
        pHS398ResearchTrainingProgramPlan20Document.setPHS398ResearchTrainingProgramPlan30(phs398ResearchTrainingProgramPlan);
        return pHS398ResearchTrainingProgramPlan20Document;
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
