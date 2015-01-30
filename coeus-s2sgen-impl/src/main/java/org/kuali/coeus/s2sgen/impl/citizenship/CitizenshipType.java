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

/**
 * 
 * This class manages enums for the following CitizenshipTypes: US_CITIZEN_OR_NONCITIZEN_NATIONAL, 
 * PERMANENT_RESIDENT_OF_US, NON_US_CITIZEN_WITH_TEMPORARY_VISA, PERMANENT_RESIDENT_OF_US_PENDING.
 */
public enum CitizenshipType {
    US_CITIZEN_OR_NONCITIZEN_NATIONAL("U.S. Citizen or noncitizen national"), 
    PERMANENT_RESIDENT_OF_US("Permanent Resident of U.S."), 
    NON_US_CITIZEN_WITH_TEMPORARY_VISA("Non-U.S. Citizen with temporary visa"),
    PERMANENT_RESIDENT_OF_US_PENDING("Permanent Resident of U.S. Pending");

    private String citizenShip;

    private CitizenshipType(String citizenShip) {
        this.citizenShip = citizenShip;
    }

    public String getCitizenShip() {
        return citizenShip;
    }
}
