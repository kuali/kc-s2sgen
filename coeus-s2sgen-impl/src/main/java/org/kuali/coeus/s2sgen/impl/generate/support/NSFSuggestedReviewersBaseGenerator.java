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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.propdev.api.abstrct.ProposalAbstractContract;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;

/**
 * This abstract class has methods that are common to all the versions of NSFSuggestedReviewes form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public abstract class NSFSuggestedReviewersBaseGenerator extends S2SBaseFormGenerator {

    protected static final String SUGGESTED_REVIEWERS = "12";
    protected static final String REVIEWERS_NOT_TO_INCLUDE = "14";

    /**
     * 
     * This method returns the abstract Details based on the AbstractTypeCode from the ProposalAbstract
     * 
     * @param abstractType abstract type code.
     * @return abstractText(String) abstract details corresponding to the abstract type code.
     */
    protected String getAbstractText(String abstractType) {

        String abstractText = null;
        for (ProposalAbstractContract proposalAbstract : pdDoc.getDevelopmentProposal().getProposalAbstracts()) {
            if (proposalAbstract.getAbstractType() != null && proposalAbstract.getAbstractType().getCode().equals(abstractType)) {
                abstractText = proposalAbstract.getAbstractDetails();
                break;
            }
        }
        return abstractText;
    }
}
