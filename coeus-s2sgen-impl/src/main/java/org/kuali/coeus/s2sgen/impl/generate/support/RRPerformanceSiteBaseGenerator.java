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

import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This abstract class has methods that are common to all the versions of RRPerformanceSite form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRPerformanceSiteBaseGenerator extends S2SBaseFormGenerator {

    public static final String EMPTY_STRING = "";
    public static final int PERFORMANCE_SITES_ATTACHMENT = 40;
    protected static final int PERFORMING_ORG_LOCATION_TYPE_CODE = 2;
    protected static final int OTHER_ORG_LOCATION_TYPE_CODE = 3;
    protected static final int PERFORMANCE_SITE_LOCATION_TYPE_CODE = 4;

    @Autowired
    @Qualifier("rolodexService")
    protected RolodexService rolodexService;

    /**
     * 
     * This method checks the string value passed and returns empty string if it is null, else returns string value
     * 
     * @param string (String) string to be checked for null.
     * @return string (String) empty string if sting value is null else string value.
     */
    public String checkNull(String string) {
        if (string == null) {
            return EMPTY_STRING;
        }
        else {
            return string;
        }
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }
}
