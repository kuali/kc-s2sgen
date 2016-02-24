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

import org.kuali.coeus.propdev.api.s2s.S2sErrorContract;
import org.kuali.coeus.propdev.api.s2s.S2sErrorService;
import org.kuali.coeus.s2sgen.api.core.AuditError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("s2SErrorHandlerService")
public class S2SErrorHandlerServiceImpl implements S2SErrorHandlerService {

    @Autowired
    @Qualifier("s2sErrorService")
    private S2sErrorService s2sErrorService;

    @Override
    public AuditError getError(String key, String formName) {
        final S2sErrorContract s2sError = s2sErrorService.findS2sErrorByKey(key);
        return s2sError == null ? new AuditError(AuditError.NO_FIELD_ERROR_KEY, key + " is not valid in "+formName, AuditError.GG_LINK) :
                new AuditError(s2sError.getKey(), s2sError.getMessage(), s2sError.getLink());
    }
}
