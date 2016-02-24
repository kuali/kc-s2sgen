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
package org.kuali.coeus.s2sgen.api.print;

import org.kuali.coeus.s2sgen.api.core.S2SException;

/**
 *
 * This service allows s2s forms to be retrieved in pdf form.
 */
public interface FormPrintService {

    /**
     * This service method executes form generation and validation where the result is a pdf document in binary form and
     * any errors that were generated during this process. This happens for a given proposal development document.
     * The proposal development document cannot be null.
     *
     * @param pdDoc the proposal development document.  cannot be null.
     * @return the result of the pdf generation
     * @throws S2SException if unable to generate the pdf
     * @throws java.lang.IllegalArgumentException if the pdDoc is null
     */
    FormPrintResult printForm(Object pdDoc) throws S2SException;
}
