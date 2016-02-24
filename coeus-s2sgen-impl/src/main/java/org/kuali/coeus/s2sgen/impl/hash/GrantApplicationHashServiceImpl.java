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
package org.kuali.coeus.s2sgen.impl.hash;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.s2sgen.api.hash.GrantApplicationHashService;
import org.springframework.stereotype.Component;

@Component("grantApplicationHashService")
public class GrantApplicationHashServiceImpl implements GrantApplicationHashService {
    @Override
    public String computeGrantFormsHash(String xml) {
        if (StringUtils.isBlank(xml)) {
            throw new IllegalArgumentException("xml is blank");
        }

        return GrantApplicationHash.computeGrantFormsHash(xml);
    }

    @Override
    public String computeAttachmentHash(byte[] attachment) {
        if (ArrayUtils.isEmpty(attachment)) {
            throw new IllegalArgumentException("attachment is null or empty");
        }

        return GrantApplicationHash.computeAttachmentHash(attachment);
    }
}
