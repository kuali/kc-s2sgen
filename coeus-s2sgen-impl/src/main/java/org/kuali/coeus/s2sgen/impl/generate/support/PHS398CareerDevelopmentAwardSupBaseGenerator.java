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

import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class PHS398CareerDevelopmentAwardSupBaseGenerator extends S2SBaseFormGenerator {

	protected static final int NARRATIVE_TYPE_INTRODUCTION_TO_APPLICATION = 70;
	protected static final int NARRATIVE_TYPE_SPECIFIC_AIMS = 71;
	protected static final int NARRATIVE_TYPE_BACKGROUND_SIGNIFICANCE = 72;
	protected static final int NARRATIVE_TYPE_RESEARCH_DESIGN_METHODS = 73;
	protected static final int NARRATIVE_TYPE_INCLUSION_ENROLLMENT_REPORT = 79;
	protected static final int NARRATIVE_TYPE_PROGRESS_REPORT_PUBLICATION_LIST = 80;
	protected static final int NARRATIVE_TYPE_PROTECTION_OF_HUMAN_SUBJECTS = 74;
	protected static final int NARRATIVE_TYPE_INCLUSION_OF_WOMEN_AND_MINORITIES = 75;
	protected static final int NARRATIVE_TYPE_TARGETED_PLANNED_ENROLLMENT_TABLE = 76;
	protected static final int NARRATIVE_TYPE_INCLUSION_OF_CHILDREN = 77;
	protected static final int NARRATIVE_TYPE_VERTEBRATE_ANIMALS = 78;
	protected static final int NARRATIVE_TYPE_SELECT_AGENT_RESEARCH = 81;
	protected static final int NARRATIVE_TYPE_PHS_CAREER_PRELIM_STUDIES_PROGREP = 82;
	protected static final int NARRATIVE_TYPE_PHS_CAREER_CONSORTIUM_CONTRACT = 83;
	protected static final int NARRATIVE_TYPE_PHS_CAREER_RESOURCE_SHARING_PLAN = 84;
	protected static final int NARRATIVE_TYPE_CANDIDATE_BACKGROUND = 62;
	protected static final int NARRATIVE_TYPE_CAREER_GOALS_AND_OBJECTIVES = 63;
	protected static final int NARRATIVE_TYPE_CAREER_DEVELOPMENT_AND_TRAINING = 64;
	protected static final int NARRATIVE_TYPE_RESPONSIBLE_CONDUCT_OF_RESEARCH = 65;
	protected static final int NARRATIVE_TYPE_PHS398_MENTORING_PLAN = 66;
	protected static final int NARRATIVE_TYPE_PHS398_MENTOR_STATEMENTS_LETTERS = 67;
	protected static final int NARRATIVE_TYPE_PSH398_INSTITUTIONAL_ENVIRONMENT = 68;
	protected static final int NARRATIVE_TYPE_PHS398_INSTITUTIONAL_COMMITMENT = 69;
	protected static final int NARRATIVE_TYPE_PHS_CAREER_APPENDIX = 85;
	protected static final int NARRATIVE_TYPE_PHS_CAREER_REASEARCH_STRATEGY = 128;
	protected static final int NARRATIVE_TYPE_PHS_CAREER_SUPPPORT_LTRS = 144;
	
    @Autowired
    @Qualifier("s2SProposalPersonService")
    protected S2SProposalPersonService s2SProposalPersonService;

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }
}
