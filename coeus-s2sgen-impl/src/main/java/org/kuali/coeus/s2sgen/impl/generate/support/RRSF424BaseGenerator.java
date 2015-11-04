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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.propdev.api.s2s.S2sOpportunityContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.questionnaire.api.core.QuestionAnswerService;
import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeService;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonService;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This abstract class has methods that are common to all the versions of RRSF424 form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRSF424BaseGenerator extends CommonSF424BaseGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(RRSF424BaseGenerator.class);

    protected static final String PRINCIPAL_INVESTIGATOR = "PI";
    protected static final int PRE_APPLICATION = 6;
    protected static final int ADDITIONAL_CONGRESSIONAL_DESTRICT = 59;
    private static final String CONTACT_TYPE_O = "O";
    protected static final String CONTACT_TYPE_I = "I";
    protected static final String STATE_REVIEW_YES = "Y";
    protected static final String STATE_REVIEW_NO = "N";
    protected static final int PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH = 120;
    protected static final String PROPOSAL_YNQ_OTHER_AGENCY_SUBMISSION = "15";
    protected static final String VALUE_YES = "Yes";
    protected static final int PRIMARY_TITLE_MAX_LENGTH = 45;
    protected static final int DIRECTORY_TITLE_MAX_LENGTH = 45;
    protected static final int DEPARTMENT_NAME_MAX_LENGTH = 30;
    protected static final int ANSWER_EXPLANATION_MAX_LENGTH = 20;
    protected static final int SFLLL_OTHEREXPLANATORY = 86;
    protected static final Integer ANSWER_128 = 128;
    protected static final Integer ANSWER_111 = 111;
    protected static final String NOT_ANSWERED = "No";
    protected static final String SPONSOR_GROUPS = "Sponsor Groups";
    protected static final String SPONSOR_NIH = "NIH";
    private static final String SUBMISSION_TYPE_CODE = "submissionTypeCode";
    private static final String SUBMISSION_TYPE_DESCRIPTION = "submissionTypeDescription";
    protected static final String KEY_REVISION_CODE = "revisionCode";
    protected static final String KEY_REVISION_OTHER_DESCRIPTION = "revisionOtherDescription";
    private static final int FEDERAL_ID_MAX_LENGTH = 30;

    @Autowired
    @Qualifier("s2SDateTimeService")
    protected S2SDateTimeService s2SDateTimeService;

    @Autowired
    @Qualifier("departmentalPersonService")
    protected DepartmentalPersonService departmentalPersonService;

    @Autowired
    @Qualifier("s2SConfigurationService")
    protected S2SConfigurationService s2SConfigurationService;

    @Autowired
    @Qualifier("sponsorHierarchyService")
    protected SponsorHierarchyService sponsorHierarchyService;

    @Autowired
    @Qualifier("questionAnswerService")
    protected QuestionAnswerService questionAnswerService;

    protected String getOtherAgencySubmissionExplanation() {
        Long answerId = getAnswerId(ANSWER_111, getAnswerHeaders());
        String submissionExplanation = null;
        if (questionAnswerService.isAnswerDescriptionRetrievalSupported(answerId)) {
            submissionExplanation = questionAnswerService.getAnswerDescription(answerId);
            submissionExplanation  = submissionExplanation != null ? submissionExplanation.substring(5) : null;
        } else {
            LOG.warn("answer description retrieval not supported for answer id: " + answerId);
        }

        if (submissionExplanation == null) {
            submissionExplanation = "Unknown";
        }

        if (submissionExplanation.length() > ANSWER_EXPLANATION_MAX_LENGTH) {
            return submissionExplanation.substring(0, ANSWER_EXPLANATION_MAX_LENGTH);
        } else {
            return submissionExplanation;
        }
    }

    /**
     * 
     * This method returns the type of contact person for a proposal
     * 
     * @return String contact type for the proposal
     */
    protected String getContactType() {
        String contactType = s2SConfigurationService.getValueAsString(ConfigurationConstants.PROPOSAL_CONTACT_TYPE);
        if (contactType == null || contactType.length() == 0) {
            contactType = CONTACT_TYPE_O;
        }
        return contactType;
    }

    protected String getFederalId() {
        final String federalId = pdDoc.getDevelopmentProposal().getSponsorProposalNumber();
        return StringUtils.substring(federalId, 0, FEDERAL_ID_MAX_LENGTH);
    }
    /**
     * 
     * This method is used to get the details of Contact person
     * 
     * @param pdDoc(ProposalDevelopmentDocumentContract)
     *            proposal development document.
     * @return depPerson(DepartmentalPerson) corresponding to the contact type.
     */
    protected DepartmentalPersonDto getContactPerson(
            ProposalDevelopmentDocumentContract pdDoc) {
        return departmentalPersonService.getContactPerson(pdDoc);
    }
    
    /**
     * This method tests whether a document's sponsor is in a given sponsor hierarchy.
     */
    public boolean isSponsorInHierarchy(DevelopmentProposalContract sponsorable, String sponsorHierarchy,String level1) {
        return sponsorHierarchyService.isSponsorInHierarchy(sponsorable.getSponsor().getSponsorCode(), sponsorHierarchy, 1, level1);
    }

    /**
     * This method creates and returns Map of submission details like submission type, description and Revision code
     *
     * @param pdDoc Proposal Development Document.
     * @return Map&lt;String, String&gt; Map of submission details.
     */
    public Map<String, String> getSubmissionType(ProposalDevelopmentDocumentContract pdDoc) {
        Map<String, String> submissionInfo = new HashMap<>();
        S2sOpportunityContract opportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
        if (opportunity != null) {
            if (opportunity.getS2sSubmissionType() != null) {
                String submissionTypeCode = opportunity.getS2sSubmissionType().getCode();
                String submissionTypeDescription = opportunity.getS2sSubmissionType().getDescription();
                submissionInfo.put(SUBMISSION_TYPE_CODE, submissionTypeCode);
                submissionInfo.put(SUBMISSION_TYPE_DESCRIPTION, submissionTypeDescription);
            }

            if (opportunity.getS2sRevisionType() != null) {
                String revisionCode = opportunity.getS2sRevisionType().getCode();
                submissionInfo.put(KEY_REVISION_CODE, revisionCode);
            }

            if (opportunity.getRevisionOtherDescription() != null) {
                submissionInfo.put(KEY_REVISION_OTHER_DESCRIPTION, opportunity.getRevisionOtherDescription());
            }
        }
        return submissionInfo;
    }

    protected abstract List<? extends AnswerHeaderContract> getAnswerHeaders();

    public S2SDateTimeService getS2SDateTimeService() {
        return s2SDateTimeService;
    }

    public void setS2SDateTimeService(S2SDateTimeService s2SDateTimeService) {
        this.s2SDateTimeService = s2SDateTimeService;
    }

    public DepartmentalPersonService getDepartmentalPersonService() {
        return departmentalPersonService;
    }

    public void setDepartmentalPersonService(DepartmentalPersonService departmentalPersonService) {
        this.departmentalPersonService = departmentalPersonService;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }

    public QuestionAnswerService getQuestionAnswerService() {
        return questionAnswerService;
    }

    public void setQuestionAnswerService(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }
}
