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
package org.kuali.coeus.s2sgen.impl.person;


import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;

public interface DepartmentalPersonService {

    /**
     *
     * This method populates and returns the Departmental Person object for a
     * given proposal document
     *
     * @param pdDoc
     *            Proposal Development Document.
     * @return DepartmentalPerson departmental Person object for a given
     *         proposal document.
     */
    DepartmentalPersonDto getDepartmentalPerson(ProposalDevelopmentDocumentContract pdDoc);

    /**
     *
     * This method is used to get the details of Contact person
     *
     * @param pdDoc(ProposalDevelopmentDocument)
     *            proposal development document.
     *            for which the DepartmentalPerson has to be found.
     * @return depPerson(DepartmentalPerson) corresponding to the contact type.
     */
    DepartmentalPersonDto getContactPerson(ProposalDevelopmentDocumentContract pdDoc) ;
}
