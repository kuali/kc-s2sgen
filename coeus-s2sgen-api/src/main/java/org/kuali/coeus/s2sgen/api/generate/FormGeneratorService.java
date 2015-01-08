/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
