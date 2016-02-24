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
package org.kuali.coeus.s2sgen.impl.person;


import org.kuali.coeus.common.api.person.attr.CitizenshipType;
import org.kuali.coeus.common.api.person.attr.CitizenshipTypeService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("s2SProposalPersonService")
public class S2SProposalPersonServiceImpl implements S2SProposalPersonService {

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    @Autowired
    @Qualifier("citizenshipTypeService")
    private CitizenshipTypeService citizenshipTypeService;

    /**
     * This method limits the number of key persons to n, returns list of key persons, first n in case firstN is true, or all other
     * than first n, in case of firstN being false
     *
     * @param proposalPersons list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     * @param firstN value that determines whether the returned list should contain first n persons or the rest of persons
     * @param n number of key persons that are considered as not extra persons
     * @return list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     */
    @Override
    public List<ProposalPersonContract> getNKeyPersons(List<? extends ProposalPersonContract> proposalPersons, boolean firstN, int n) {
        ProposalPersonContract proposalPerson, previousProposalPerson;
        int size = proposalPersons.size();

        for (int i = size - 1; i > 0; i--) {
            proposalPerson = proposalPersons.get(i);
            previousProposalPerson = proposalPersons.get(i - 1);
            if (proposalPerson.getPersonId() != null && previousProposalPerson.getPersonId() != null
                    && proposalPerson.getPersonId().equals(previousProposalPerson.getPersonId())) {
                proposalPersons.remove(i);
            }
            else if (proposalPerson.getRolodexId() != null && previousProposalPerson.getRolodexId() != null
                    && proposalPerson.getRolodexId().equals(previousProposalPerson.getRolodexId())) {
                proposalPersons.remove(i);
            }
        }

        size = proposalPersons.size();
        if (firstN) {
            List<ProposalPersonContract> firstNPersons = new ArrayList<ProposalPersonContract>();

            // Make sure we don't exceed the size of the list.
            if (size > n) {
                size = n;
            }
            // remove extras
            for (int i = 0; i < size; i++) {
                firstNPersons.add(proposalPersons.get(i));
            }
            return firstNPersons;
        }
        else {
            // return extra people
            List<ProposalPersonContract> extraPersons = new ArrayList<ProposalPersonContract>();
            for (int i = n; i < size; i++) {
                extraPersons.add(proposalPersons.get(i));
            }
            return extraPersons;
        }
    }

    /**
     * This method is to get PrincipalInvestigator from person list
     *
     * @param pdDoc Proposal development document.
     * @return ProposalPerson PrincipalInvestigator for the proposal.
     */
    @Override
    public ProposalPersonContract getPrincipalInvestigator(ProposalDevelopmentDocumentContract pdDoc) {
        ProposalPersonContract proposalPerson = null;
        if (pdDoc != null) {
            for (ProposalPersonContract person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                if (person.isPrincipalInvestigator()) {
                    proposalPerson = person;
                }
            }
        }
        return proposalPerson;
    }

    /**
     * Finds all the Investigators associated with the provided pdDoc.
     */
    @Override
    public List<ProposalPersonContract> getCoInvestigators(ProposalDevelopmentDocumentContract pdDoc) {
        List<ProposalPersonContract> investigators = new ArrayList<ProposalPersonContract>();
        if (pdDoc != null) {
            for (ProposalPersonContract person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                //multi-pis are still considered co-i within S2S.
                if (person.isCoInvestigator() || person.isMultiplePi()) {
                    investigators.add(person);
                }
            }
        }
        return investigators;
    }

    /**
     * Finds all the key Person associated with the provided pdDoc.
     */
    @Override
    public List<ProposalPersonContract> getKeyPersons(ProposalDevelopmentDocumentContract pdDoc) {
        List<ProposalPersonContract> keyPersons = new ArrayList<ProposalPersonContract>();
        if (pdDoc != null) {
            for (ProposalPersonContract person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                if (person.isKeyPerson()) {
                    keyPersons.add(person);
                }
            }
        }
        return keyPersons;
    }

    /**
     * Implementation should return one of the enums defined in PHS398CareerDevelopmentAwardSup11V11 form schema. For now, it
     * returns US RESIDENT as default
     */
    @Override
    public CitizenshipType getCitizenship(ProposalPersonContract proposalPerson) {
    	return citizenshipTypeService.getPersonCitizenshipType(proposalPerson);
    }
}
