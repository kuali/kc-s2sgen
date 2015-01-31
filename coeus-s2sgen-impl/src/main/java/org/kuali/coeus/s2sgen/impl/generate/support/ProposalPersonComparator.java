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

import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Used to order <code>{@link ProposalPersonContract}</code> instances by the role.
 */
public class ProposalPersonComparator implements Comparator<ProposalPersonContract> {
    private static final Logger LOG = LoggerFactory.getLogger(ProposalPersonComparator.class);


    /**
     * compare one <code>{@link ProposalPersonContract}</code> instance to another. Sort by the role of the
     *  <code>{@link ProposalPersonContract}</code>
     * 
     * @param person1
     * @param person2
     * @return int
     */
    public int compare(ProposalPersonContract person1, ProposalPersonContract person2) {
        int retval = 0;
               
        if (person1.isInvestigator() || person2.isInvestigator()) {
            if (person1.isPrincipalInvestigator() 
                    || person2.isPrincipalInvestigator()) {
               if (person1.isPrincipalInvestigator()) {
                   retval--;
               }
               
               if (person2.isPrincipalInvestigator()) {
                   retval++;
               }
            }
        }
        
        if (retval == 0) {
            retval = massageOrdinalNumber(person1).compareTo(massageOrdinalNumber(person2));
        }
        
        if (retval == 0) {
            if (isNotBlank(person1.getFullName())) {
                retval = person1.getLastName().compareTo(person2.getLastName());
            }
            else if (isNotBlank(person2.getLastName())) {
                retval--; 
            }
        }
        
        LOG.info("retval = " + retval);

        return retval;
    }

    private Integer massageOrdinalNumber(ProposalPersonContract person) {
        return person.getOrdinalPosition() != null ? person.getOrdinalPosition() : -1;
    }
    
}
