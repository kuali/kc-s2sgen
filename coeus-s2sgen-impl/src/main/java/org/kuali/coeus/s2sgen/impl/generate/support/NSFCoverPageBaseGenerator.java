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

import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Calendar;

/**
 * This abstract class has methods that are common to all the versions of
 * NSFCoverPage form
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public abstract class NSFCoverPageBaseGenerator extends S2SBaseFormGenerator {
	protected static final String QUESTION_ID_BEGIN_INVESTIGATOR = "12";
	protected static final String QUESTION_ID_EXPLORATORY_RESEARCH = "14";
	protected static final String QUESTION_ID_HISTORIC_PLACES = "G6";
	protected static final String PROPOSAL_YNQ_LOBBYING_ACTIVITIES = "10088";
	protected static final String QUESTION_ID_RESOLUTION_GRAPHICS = "20";
	protected static final String PRINCIPAL_INVESTIGATOR = "PI";
	protected static final String PI_C0_INVESTIGATOR = "COI";
	protected static final int PERSONAL_DATA = 13;
	protected static final int PROPRIETARY_INFORMATION = 14;

	protected static final int PROGRAM_ANNOUNCEMENT_NUMBER_MAX_LENGTH = 40;
	protected static final int QUESTION_CURRENT_PI = 1;
	protected static final int QUESTION_BEGIN_INVESTIGATOR = 2;
	protected static final int QUESTION_EARLY_CONCEPT_GRANT = 4;
	protected static final int QUESTION_RAPIDRESPONSE_GRANT = 3;
	protected static final int QUESTION_ACCOMPLISHMENT_RENEWAL = 5;
    protected static final int FUNDING_MECHANISM_QUESTION = 10;
    protected static final int QUESTION_RESOLUTION_GRAPHICS = 6;
	
	protected static final int SINGLE_COPY_DOCUMENT = 87;
	protected static final String LOBBYING_QUESTION_ID = "H0";
	protected static final String ANSWER_INDICATOR_VALUE="Y";

    @Autowired
    @Qualifier("s2SProposalPersonService")
    protected S2SProposalPersonService s2SProposalPersonService;

	/**
	 * 
	 * This method Converts the String that is passed to it into a calendar
	 * object. The argument will be set as the Year in the Calendar Object.
	 * 
	 * @param year -
	 *            The String value to be converted to Calendar Object
	 * @return calendar value corresponding to the year(String) passed.
	 */
	public Calendar getYearAsCalendar(String year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.DATE, 0);
		calendar.set(Calendar.MONTH, 0);
		try {
			calendar.set(Calendar.YEAR, Integer.parseInt(year));
		} catch (NumberFormatException ex) {
			calendar.set(Calendar.YEAR, 0);
		}
		return calendar;
	}

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }
}
