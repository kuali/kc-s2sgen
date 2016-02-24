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

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;


public class IndirectCostDetailsDto {


    private String costType;
    private ScaleTwoDecimal rate;
    private ScaleTwoDecimal base;
    private ScaleTwoDecimal funds;
    private ScaleTwoDecimal baseCostSharing;
    private ScaleTwoDecimal costSharing;

    /**
     * Getter for property costType.
     * 
     * @return Value of property costType.
     */
    public String getCostType() {
        return costType;
    }

    /**
     * Setter for property costType.
     * 
     * @param costType New value of property costType.
     */
    public void setCostType(String costType) {
        this.costType = costType;
    }

    /**
     * Getter for property rate.
     * 
     * @return Value of property rate.
     */
    public ScaleTwoDecimal getRate() {
        return rate;
    }

    /**
     * Setter for property rate.
     * 
     * @param rate New value of property rate.
     */
    public void setRate(ScaleTwoDecimal rate) {
        this.rate = rate;
    }

    /**
     * Getter for property base.
     * 
     * @return Value of property base.
     */
    public ScaleTwoDecimal getBase() {
        return base;
    }

    /**
     * Setter for property base.
     * 
     * @param base New value of property base.
     */
    public void setBase(ScaleTwoDecimal base) {
        this.base = base;
    }

    /**
     * Getter for property funds.
     * 
     * @return Value of property funds.
     */
    public ScaleTwoDecimal getFunds() {
        return funds;
    }

    /**
     * Setter for property funds.
     * 
     * @param funds New value of property funds.
     */
    public void setFunds(ScaleTwoDecimal funds) {
        this.funds = funds;
    }

    // start add costSaring for fedNonFedBudget repport

    public ScaleTwoDecimal getBaseCostSharing() {
        return baseCostSharing==null? ScaleTwoDecimal.ZERO:baseCostSharing;
    }

    public void setBaseCostSharing(ScaleTwoDecimal baseCostSharing) {
        this.baseCostSharing = baseCostSharing;
    }

    public ScaleTwoDecimal getCostSharing() {
        return costSharing==null? ScaleTwoDecimal.ZERO:costSharing;
    }

    public void setCostSharing(ScaleTwoDecimal costSharing) {
        this.costSharing = costSharing;
    }
}
