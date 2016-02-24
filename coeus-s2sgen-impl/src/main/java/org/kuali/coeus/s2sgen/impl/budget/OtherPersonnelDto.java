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
package org.kuali.coeus.s2sgen.impl.budget;


public class OtherPersonnelDto {


    private String personnelType;
    private int numberPersonnel;
    private String role;
    private CompensationDto compensation;

    /**
     * Getter for property numberPersonnel.
     * 
     * @return Value of property numberPersonnel.
     */
    public int getNumberPersonnel() {
        return numberPersonnel;
    }

    /**
     * Setter for property numberPersonnel.
     * 
     * @param numberPersonnel New value of property numberPersonnel.
     */
    public void setNumberPersonnel(int numberPersonnel) {
        this.numberPersonnel = numberPersonnel;
    }

    /**
     * Getter for property personnelType.
     * 
     * @return Value of property personnelType.
     */
    public String getPersonnelType() {
        return personnelType;
    }

    /**
     * Setter for property personnelType.
     * 
     * @param personnelType New value of property personnelType.
     */
    public void setPersonnelType(String personnelType) {
        this.personnelType = personnelType;
    }


    /**
     * Getter for property role.
     * 
     * @return Value of property role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for property role.
     * 
     * @param role New value of property role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter for property compensation.
     * 
     * @return Value of property compensation.
     */
    public CompensationDto getCompensation() {
        return compensation;
    }

    /**
     * Setter for property compensation.
     * 
     * @param compensation New value of property compensation.
     */
    public void setCompensation(CompensationDto compensation) {
        this.compensation = compensation;
    }
}
