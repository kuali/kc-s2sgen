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

import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;

import java.util.Objects;

/**
 * This abstract class has methods that are common to all the versions of PHS398ResearchPlan form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398ResearchPlanBaseGenerator extends S2SBaseFormGenerator {

    public static final int INTRODUCTION_TO_APPLICATION = 20;
    public static final int SPECIFIC_AIMS = 21;
    public static final int BACKGROUND_SIGNIFICANCE = 22;
    public static final int PROGRESS_REPORT = 23;
    public static final int RESEARCH_DESIGN_METHODS = 24;
    public static final int PROTECTION_OF_HUMAN_SUBJECTS = 25;
    public static final int INCLUSION_OF_WOMEN_AND_MINORITIES = 26;
    public static final int TARGETED_PLANNED_ENROLLMENT_TABLE = 27;
    public static final int INCLUSION_OF_CHILDREN = 28;
    public static final int DATA_AND_SAFETY_MONITORING_PLAN = 29;
    public static final int VERTEBRATE_ANIMALS = 30;
    public static final int CONSORTIUM_CONTRACTUAL_ARRANGEMENTS = 31;
    public static final int LETTERS_OF_SUPPORT = 32;
    public static final int RESOURCE_SHARING_PLANS = 33;
    public static final int APPENDIX = 34;

    public static final int INCLUSION_ENROLLMENT_REPORT = 43;
    public static final int PROGRESS_REPORT_PUBLICATION_LIST = 44;
    public static final int SELECT_AGENT_RESEARCH = 45;
    public static final int MULTIPLE_PI_LEADERSHIP_PLAN = 46;
    public static final int RESEARCH_STRATEGY = 111;
    
    protected static final int PROPOSAL_TYPE_CODE_6 = 6;

    /**
     * This method is used to get List of appendix attachments from
     * NarrativeAttachment
     *
     * @return AttachedFileDataType[] array of attachments for the corresponding
     * narrative type code APPENDIX.
     */
    protected AttachedFileDataType[] getAppendixAttachedFileDataTypes() {
        return pdDoc.getDevelopmentProposal().getNarratives().stream()
                .filter(narrative -> narrative.getNarrativeType().getCode() != null && Integer.parseInt(narrative.getNarrativeType().getCode()) == APPENDIX)
                .map(this::getAttachedFileType)
                .filter(Objects::nonNull)
                .toArray(AttachedFileDataType[]::new);
    }
}
