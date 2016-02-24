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
package org.kuali.coeus.s2sgen.impl.datetime;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Calendar;

public interface S2SDateTimeService {

    ScaleTwoDecimal getNumberOfMonths(java.util.Date dateStart, java.util.Date dateEnd);

    String removeTimezoneFactor(String applicationXmlText);

    /**
     *
     * This method returns a {@link java.util.Calendar} whose date matches the date passed
     * as {@link String}
     *
     * @param dateStr
     *            string for which the Calendar value has to be found.
     * @return Calendar calendar value corresponding to the date string.
     */
    Calendar convertDateStringToCalendar(String dateStr);

    /**
     *
     * This method is used to get Calendar date
     *
     * @param date(Date)
     *            date for which Calendar value has to be found.
     * @return cal(Calendar) calendar value corresponding to the date.
     */
    Calendar convertDateToCalendar(java.util.Date date);
}
