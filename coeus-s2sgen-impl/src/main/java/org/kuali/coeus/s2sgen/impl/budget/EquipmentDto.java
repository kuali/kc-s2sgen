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

public class EquipmentDto {

    private List<CostDto> cvEquipmentList;
    private ScaleTwoDecimal totalFund;
    private List<CostDto> cvExtraEquipmentList;
    private ScaleTwoDecimal totalExtraFund = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal totalNonFund = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal totalExtraNonFund = ScaleTwoDecimal.ZERO;


    public EquipmentDto() {
        cvEquipmentList = new ArrayList<CostDto>();
        cvExtraEquipmentList = new ArrayList<CostDto>();

    }


    /**
     * Getter for property cvEquipmentList.
     * 
     * @return Value of property cvEquipmentList.
     */
    public List<CostDto> getEquipmentList() {
        return cvEquipmentList;
    }

    /**
     * Setter for property cvEquipmentList.
     * 
     * @param cvEquipmentList New value of property cvEquipmentList.
     */
    public void setEquipmentList(List<CostDto> cvEquipmentList) {
        this.cvEquipmentList = cvEquipmentList;
    }


    /**
     * Getter for property totalFund.
     * 
     * @return Value of property totalFund.
     */
    public ScaleTwoDecimal getTotalFund() {
        return totalFund;
    }

    /**
     * Setter for property totalFund.
     * 
     * @param totalFund New value of property totalFund.
     */
    public void setTotalFund(ScaleTwoDecimal totalFund) {
        this.totalFund = totalFund;
    }


    public List<CostDto> getExtraEquipmentList() {
        return cvExtraEquipmentList;
    }

    /**
     * Setter for property cvExtraEquipmentList.
     * 
     * @param cvExtraEquipmentList New value of property cvExtraEquipmentList.
     */
    public void setExtraEquipmentList(List<CostDto> cvExtraEquipmentList) {
        this.cvExtraEquipmentList = cvExtraEquipmentList;
    }

    /**
     * Getter for property totalExtraFund.
     * 
     * @return Value of property totalExtraFund.
     */
    public ScaleTwoDecimal getTotalExtraFund() {
        return totalExtraFund;
    }

    /**
     * Setter for property totalExtraFund.
     * 
     * @param totalExtraFund New value of property totalExtraFund.
     */
    public void setTotalExtraFund(ScaleTwoDecimal totalExtraFund) {
        this.totalExtraFund = totalExtraFund;
    }

    // start add costSaring for fedNonFedBudget repport
    /**
     * Getter for property totalNonFund.
     * 
     * @return Value of property totalNonFund.
     */
    public ScaleTwoDecimal getTotalNonFund() {
        return totalNonFund;
    }

    /**
     * Setter for property totalNonFund.
     * 
     * @param totalNonFund New value of property totalNonFund.
     */
    public void setTotalNonFund(ScaleTwoDecimal totalNonFund) {
        this.totalNonFund = totalNonFund;
    }

    /**
     * Getter for property totalExtraNonFund.
     * 
     * @return Value of property totalExtraNonFund.
     */
    public ScaleTwoDecimal getTotalExtraNonFund() {
        return totalExtraNonFund;
    }

    /**
     * Setter for property totalExtraNonFund.
     * 
     * @param totalExtraNonFund New value of property totalExtraNonFund.
     */
    public void setTotalExtraNonFund(ScaleTwoDecimal totalExtraNonFund) {
        this.totalExtraNonFund = totalExtraNonFund;
    }
}
