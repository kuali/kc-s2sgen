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
package org.kuali.coeus.s2sgen.impl.validate;

import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlValidationError;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * This class validates a XML document passed as XMLObject
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("s2SValidatorService")
public class S2SValidatorServiceImpl implements S2SValidatorService {

    private static final Logger LOG = LoggerFactory.getLogger(S2SValidatorServiceImpl.class);

    @Autowired
    @Qualifier("s2SErrorHandlerService")
    private S2SErrorHandlerService s2SErrorHandlerService;

    /**
     * This method receives an XMLObject and validates it against its schema and returns the validation result. It also receives a
     * list in which upon validation failure, populates it with XPaths of the error nodes.
     * 
     * @param formObject XML document as {@link}XMLObject
     * @param errors List list of XPaths of the error nodes.
     * @return validation result true if valid false otherwise.
     */
    @Override
    public boolean validate(XmlObject formObject, List<AuditError> errors, String formName) {

        List<String> formErrors = new ArrayList<>();
        boolean result = false;
        result = validateXml(formObject, formErrors);

        for (String validationError : formErrors) {
            errors.add(s2SErrorHandlerService.getError(GRANTS_GOV_PREFIX + validationError, formName));
        }

        return result;
    }
    
    /**
     * 
     * This method receives an XMLObject and validates it against its schema and returns the validation result. It also receives a
     * list in which upon validation failure, populates it with XPaths of the error nodes
     * 
     * @param formObject XML document as {@link}XMLObject
     * @param errors List list of XPaths of the error nodes.
     * @return validation result true if valid false otherwise.
     */
    protected boolean validateXml(XmlObject formObject, List<String> errors) {
        XmlOptions validationOptions = new XmlOptions();
        ArrayList<XmlValidationError> validationErrors = new ArrayList<>();
        validationOptions.setErrorListener(validationErrors);

        boolean isValid = formObject.validate(validationOptions);

        if (!isValid) {
            LOG.error("Errors occured during validation of XML from form generator" + validationErrors);
            for (XmlValidationError error : validationErrors) {
                LOG.info("Validation error:" + error);
                Node node = error.getCursorLocation().getDomNode();
                errors.add(getXPath(node));
            }
            LOG.debug("Error XPaths:" + errors);
        }
        return isValid;
    }

    /**
     * 
     * This method receives a node, fetches its name, and recurses up to its parent node, until it reaches the document node, thus
     * creating the XPath of the node passed and returns it as String
     * 
     * @param node for which Document node has to found.
     * @return String which represents XPath of the node
     */
    protected String getXPath(Node node) {
        if (node==null || node.getNodeType() == Node.DOCUMENT_NODE) {
            return "";
        }
        else {
            return getXPath(node.getParentNode()) + "/" + node.getNodeName();
        }
    }

    public S2SErrorHandlerService getS2SErrorHandlerService() {
        return s2SErrorHandlerService;
    }

    public void setS2SErrorHandlerService(S2SErrorHandlerService s2SErrorHandlerService) {
        this.s2SErrorHandlerService = s2SErrorHandlerService;
    }
}
