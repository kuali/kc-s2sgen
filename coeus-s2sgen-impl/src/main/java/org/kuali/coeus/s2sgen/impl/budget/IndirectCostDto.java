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
package org.kuali.coeus.s2sgen.impl.budget;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.ArrayList;
import java.util.List;

public class IndirectCostDto {


    private List<IndirectCostDetailsDto> indirectCostDetails;
    private ScaleTwoDecimal totalIndirectCosts;
    private ScaleTwoDecimal totalIndirectCostSharing;


    public IndirectCostDto() {
        indirectCostDetails = new ArrayList<IndirectCostDetailsDto>();

    }

    public List<IndirectCostDetailsDto> getIndirectCostDetails() {
        return indirectCostDetails;
    }

    /**
     * Setter for property indirectCostDetails.
     * 
     * @param indirectCostDetails New value of property indirectCostDetails.
     */
    public void setIndirectCostDetails(List<IndirectCostDetailsDto> indirectCostDetails) {
        this.indirectCostDetails = indirectCostDetails;
    }

    /**
     * Getter for property totalIndirectCosts.
     * 
     * @return Value of property totalIndirectCosts.
     */
    public ScaleTwoDecimal getTotalIndirectCosts() {
        return totalIndirectCosts;
    }

    /**
     * Setter for property totalIndirectCosts.
     * 
     * @param totalIndirectCosts New value of property totalIndirectCosts.
     */
    public void setTotalIndirectCosts(ScaleTwoDecimal totalIndirectCosts) {
        this.totalIndirectCosts = totalIndirectCosts;
    }

    // start add costSaring for fedNonFedBudget repport
    /**
     * Getter for property totalIndirectCostSharing.
     * 
     * @return Value of property totalIndirectCostSharing.
     */
    public ScaleTwoDecimal getTotalIndirectCostSharing() {
        return totalIndirectCostSharing;
    }

    /**
     * Setter for property totalIndirectCostSharing.
     * 
     * @param totalIndirectCostSharing New value of property totalIndirectCostSharing.
     */
    public void setTotalIndirectCostSharing(ScaleTwoDecimal totalIndirectCostSharing) {
        this.totalIndirectCostSharing = totalIndirectCostSharing;
    }
}
