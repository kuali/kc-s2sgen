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
package org.kuali.coeus.s2sgen.impl.citizenship;

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType.Enum;

import org.kuali.coeus.common.api.person.attr.CitizenshipTypeContract;
import org.springframework.stereotype.Component;

/**
 * 
 * This service has been made available for implementers who will be using an external source
 * for citizenship data. It hooks into S2SUtilService via the system parameter
 * PI_CITIZENSHIP_FROM_CUSTOM_DATA. Setting this to "0" will see that S2SUtilServiceImpl::getCitizenship receive a
 * CitizenshipTypes from this service, as opposed to KcPerson's extended attributes
 * 
 * Schools who need external citizenship data are expected to override this service with their own
 * implementation of "getCitizenshipDataFromExternalSource().
 * 
 * getEnumValueOfCitizenshipType has been included as a convenience method should it be needed.
 */
@Component("citizenshipTypeService")
public class CitizenshipTypeServiceImpl implements CitizenshipTypeService {
    
    @Override
    public Enum getEnumValueOfCitizenshipType(CitizenshipTypeContract citizenshipType) throws IllegalArgumentException {
        Enum retVal = null;
        switch(citizenshipType.getCode()){
            case CitizenshipDataType.INT_NON_U_S_CITIZEN_WITH_TEMPORARY_VISA : {
                retVal = CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA;
                break;
            }
            case CitizenshipDataType.INT_PERMANENT_RESIDENT_OF_U_S : {
                retVal = CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S;
                break;
            }
            case CitizenshipDataType.INT_U_S_CITIZEN_OR_NONCITIZEN_NATIONAL : {
                retVal = CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid citizenship type provided");
            }
        }
        return retVal;
    }

    @Override
	public CitizenshipType getCitizenshipDataFromExternalSource() {
		throw new UnsupportedOperationException("External Source Must be configured when system parameter PI_CITIZENSHIP_FROM_CUSTOM_DATA is set to '0'");
	}

}
