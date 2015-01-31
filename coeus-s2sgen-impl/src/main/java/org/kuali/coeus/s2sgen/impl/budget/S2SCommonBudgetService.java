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

import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;

/**
 * This service contains generic budget handling code for S2S.
 */
public interface S2SCommonBudgetService {

    /**
     * This method retrieves a Budget for use in S2S generators.  S2S has special rules, where
     * it first will look for a final budget and if none exists it will then look for the latest
     * budget.
     *
     * @param developmentProposal the development proposal, cannot be null.
     * @return a Budget, either a final one, the latest non-final one, or null
     */
    ProposalDevelopmentBudgetExtContract getBudget(DevelopmentProposalContract developmentProposal);
}
