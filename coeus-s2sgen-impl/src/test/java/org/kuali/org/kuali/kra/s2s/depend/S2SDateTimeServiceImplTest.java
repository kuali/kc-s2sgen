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
package org.kuali.org.kuali.kra.s2s.depend;


import org.junit.Test;


import static org.junit.Assert.*;

import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeServiceImpl;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class S2SDateTimeServiceImplTest {


    @Test()
    public void test_removeTimezoneFactorNow() {
        final S2SDateTimeServiceImpl s2SDateTimeService = new S2SDateTimeServiceImpl();
        Calendar cal = Calendar.getInstance();

        int zoneOffset = cal.get(Calendar.ZONE_OFFSET)/(1000*60*60) * -1;

        String dateFirst = "2017-09-30-" + (zoneOffset - 1 < 10 ? "0" : "") + (zoneOffset  - 1)   + ":00";
        String dateSecond = "2017-01-01-" + (zoneOffset < 10 ? "0" : "") + (zoneOffset) + ":00";
        String dateThird = "2020-12-31-" + (zoneOffset < 10 ? "0" : "")+ (zoneOffset) + ":00";

        String allDates = dateFirst + " " + dateSecond + " " + dateThird;

        String dateFirstRemoved = s2SDateTimeService.removeTimezoneFactor(dateFirst);
        String dateSecondRemoved = s2SDateTimeService.removeTimezoneFactor(dateSecond);
        String dateThirdRemoved = s2SDateTimeService.removeTimezoneFactor(dateThird);
        String allDatesRemoved = s2SDateTimeService.removeTimezoneFactor(allDates);

        assertEquals("2017-09-30", dateFirstRemoved);
        assertEquals("2017-01-01", dateSecondRemoved);
        assertEquals("2020-12-31", dateThirdRemoved);
        assertEquals(dateFirstRemoved + " " + dateSecondRemoved + " " + dateThirdRemoved, allDatesRemoved);
    }

    @Test()
    public void test_removeTimezoneFactorJuly1() {
        final S2SDateTimeServiceImpl s2SDateTimeService = new S2SDateTimeServiceImpl();
        Calendar cal = new GregorianCalendar(2016, Calendar.JULY, 1);
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET)/(1000*60*60) * -1;

        String dateFirst = "2017-09-30-" + (zoneOffset - 1 < 10  ? "0" : "") + (zoneOffset  - 1)   + ":00";
        String dateSecond = "2017-01-01-" + (zoneOffset < 10 ? "0" : "") + (zoneOffset) + ":00";
        String dateThird = "2020-12-31-" + (zoneOffset < 10 ? "0" : "")+ (zoneOffset) + ":00";

        String allDates = dateFirst + " " + dateSecond + " " + dateThird;

        String dateFirstRemoved = s2SDateTimeService.removeTimezoneFactor(dateFirst, cal);
        String dateSecondRemoved = s2SDateTimeService.removeTimezoneFactor(dateSecond, cal);
        String dateThirdRemoved = s2SDateTimeService.removeTimezoneFactor(dateThird, cal);
        String allDatesRemoved = s2SDateTimeService.removeTimezoneFactor(allDates, cal);

        assertEquals("2017-09-30", dateFirstRemoved);
        assertEquals("2017-01-01", dateSecondRemoved);
        assertEquals("2020-12-31", dateThirdRemoved);
        assertEquals(dateFirstRemoved + " " + dateSecondRemoved + " " + dateThirdRemoved, allDatesRemoved);
    }

    @Test()
    public void test_removeTimezoneFactorJan1() {
        final S2SDateTimeServiceImpl s2SDateTimeService = new S2SDateTimeServiceImpl();
        Calendar cal = new GregorianCalendar(2016, Calendar.JANUARY, 1);
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET)/(1000*60*60) * -1;

        String dateFirst = "2017-09-30-" + (zoneOffset - 1 < 10 ? "0" : "") + (zoneOffset  - 1)   + ":00";
        String dateSecond = "2017-01-01-" + (zoneOffset < 10 ? "0" : "") + (zoneOffset) + ":00";
        String dateThird = "2020-12-31-" + (zoneOffset < 10 ? "0" : "")+ (zoneOffset) + ":00";

        String allDates = dateFirst + " " + dateSecond + " " + dateThird;

        String dateFirstRemoved = s2SDateTimeService.removeTimezoneFactor(dateFirst, cal);
        String dateSecondRemoved = s2SDateTimeService.removeTimezoneFactor(dateSecond, cal);
        String dateThirdRemoved = s2SDateTimeService.removeTimezoneFactor(dateThird, cal);
        String allDatesRemoved = s2SDateTimeService.removeTimezoneFactor(allDates, cal);

        assertEquals("2017-09-30", dateFirstRemoved);
        assertEquals("2017-01-01", dateSecondRemoved);
        assertEquals("2020-12-31", dateThirdRemoved);
        assertEquals(dateFirstRemoved + " " + dateSecondRemoved + " " + dateThirdRemoved, allDatesRemoved);

    }

}
