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
package org.kuali.coeus.s2sgen.api.generate;

import java.util.Map;
import java.util.Set;

/**
 * This service retrieves information about the currently configured form
 * generation.
 */
public interface FormMappingService {

    /**
     * Gets form generation info for a specific namespace. the namespace cannot be blank.
     *
     * @param namespace the namespace.  cannot be blank.
     * @return the form generation info or null if it cannot be found
     * @throws java.lang.IllegalArgumentException if the namespace is blank
     */
    FormMappingInfo getFormInfo(String namespace);

    /**
     * Gets form generation info for a specific namespace and proposal number.
     * the namespace and proposal number cannot be blank.
     *
     * @param namespace the namespace.  cannot be blank.
     * @param proposalNumber the proposal number for an existing proposal. cannot be blank.
     * @return the form generation info or null if it cannot be found
     * @throws java.lang.IllegalArgumentException if the namespace or proposalNumber is blank
     */
    FormMappingInfo getFormInfo(String namespace, String proposalNumber);

    /**
     * Gets form generation info for all namespaces, keyed by namespace.
     * @return returns a map.  always non-null
     */
    Map<String, FormMappingInfo> getBindings();

    /**
     * Gets form generation info for all namespaces, keyed by namespace.
     * @return returns a map.  always non-null
     */
    Map<Integer, Set<String>> getSortedNameSpaces();


    void registerForm(FormMappingInfo info);
}
