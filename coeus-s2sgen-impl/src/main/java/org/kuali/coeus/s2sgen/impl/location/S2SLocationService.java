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
package org.kuali.coeus.s2sgen.impl.location;

import org.kuali.coeus.common.api.country.CountryContract;
import org.kuali.coeus.common.api.state.StateContract;

public interface S2SLocationService {

    /**
     * Finds a Country object from the country code
     *
     * @param countryCode
     *            Country name
     * @return Country object matching the code
     */
    CountryContract getCountryFromCode(String countryCode);

    /**
     * Finds a State object from the state name
     * @param countryAlternateCode country 3-character code
     * @param stateName
     *            Name of the state (two-letter state code)
     * @return State object matching the name.
     */
    StateContract getStateFromName(String countryAlternateCode, String stateName);
}
