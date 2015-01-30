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
package org.kuali.coeus.s2sgen.impl.validate;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.s2sgen.api.core.AuditError;

import java.util.List;


/**
 * 
 * This class Forms the base for all XML Beans based validations to be done.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface S2SValidatorService {
    
    String GRANTS_GOV_PREFIX = "/GrantApplication/Forms";


    /**
     * 
     * This method receives an XMLObject and validates it against its schema and returns the validation result. It also receives a
     * list in which upon validation failure, populates it with XPaths of the error nodes
     * 
     * @param formObject XML document as {@link}XMLObject
     * @param errors List list of XPaths of the error nodes.
     * @param formName is the FormName.
     * @return validation result true if valid false otherwise.
     */
    boolean validate(XmlObject formObject, List<AuditError> errors, String formName);
    
}
