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

import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeService;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This abstract class has methods that are common to all the versions of
 * RROtherProjectInfo form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RROtherProjectInfoBaseGenerator extends
		S2SBaseFormGenerator {

	// Special Review Id's
	public static final int HUMAN_SUBJECT_SUPPLEMENT = 1;
	public static final int VERTEBRATE_ANIMALS_SUPPLEMENT = 2;
	// Its an YnQ fields that are required for RROtherProjectInfo
	public static final Integer PROPRIETARY_INFORMATION_INDICATOR = 122;
	public static final Integer ENVIRONMENTAL_IMPACT_YNQ = 123;
	public static final Integer ENVIRONMENTAL_EXEMPTION_YNQ = 124;
	public static final Integer INTERNATIONAL_ACTIVITIES_YNQ = 126;
	public static final Integer INTERNATIONAL_ACTIVITIES_EXPL = 127;
	public static final Integer EXPLANATION = 107;
	// Attachments
	public static final int EQUIPMENT_ATTACHMENT = 3;
	public static final int FACILITIES_ATTACHMENT = 2;
	public static final int NARRATIVE_ATTACHMENT = 1;
	public static final int BIBLIOGRAPHY_ATTACHMENT = 4;
	public static final int ABSTRACT_PROJECT_SUMMARY_ATTACHMENT = 5;
	public static final int OTHER_ATTACHMENT = 8;
	public static final int SUPPLIMENTARY_ATTACHMENT = 15;
	public static final String SPECIAL_REVIEW_HUMAN_SUBJECTS = "1";
	public static final String SPECIAL_REVIEW_ANIMAL_USAGE = "1";
	public static final int APPROVAL_TYPE_EXCEMPT = 4;
	protected static final int EXPLANATION_MAX_LENGTH = 55;
	
	public static final String NOT_ANSWERED = "No";

    @Autowired
    @Qualifier("s2SDateTimeService")
    protected S2SDateTimeService s2SDateTimeService;

    public S2SDateTimeService getS2SDateTimeService() {
        return s2SDateTimeService;
    }

    public void setS2SDateTimeService(S2SDateTimeService s2SDateTimeService) {
        this.s2SDateTimeService = s2SDateTimeService;
    }
}
