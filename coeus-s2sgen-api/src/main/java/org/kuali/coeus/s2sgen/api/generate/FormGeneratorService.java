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
package org.kuali.coeus.s2sgen.api.generate;

import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormFileContract;
import org.kuali.coeus.s2sgen.api.core.S2SException;

/**
 * 
 * This service allows s2s form generation or form validation.
 */
public interface FormGeneratorService {


    /**
     * This service method executes form validation for a given proposal development document.  The proposal development
     * document cannot be null.
     *
     * @param pdDoc the proposal development document.  cannot be null.
     * @return the result of the validation
     * @throws S2SException if unable to validate
     * @throws java.lang.IllegalArgumentException if the pdDoc is null
     */
	FormValidationResult validateForms(ProposalDevelopmentDocumentContract pdDoc) throws S2SException;

    /**
     * This service method executes form generation for a given proposal development document.  The proposal development
     * document cannot be null.  Note that validation is also executed as well.
     *
     * @param pdDoc the proposal development document.  cannot be null.
     * @return the result of the generation.
     * @throws S2SException if unable to validate
     * @throws java.lang.IllegalArgumentException if the pdDoc is null
     */
    FormGenerationResult generateAndValidateForms(ProposalDevelopmentDocumentContract pdDoc) throws S2SException;

    /**
     * This service method executes user attach form file validation.  The user attached from form file
     * cannot be null.
     *
     * @param s2sUserAttachedFormFile the user attached form file.  cannot be null.
     * @param formName is the formName. cannot be null.
     * @return the result of the validation
     * @throws S2SException if unable to validate
     * @throws java.lang.IllegalArgumentException if the s2sUserAttachedFormFile is null or if the formName is null.
     */

    FormValidationResult validateUserAttachedFormFile(S2sUserAttachedFormFileContract s2sUserAttachedFormFile,String formName) throws S2SException;
}
