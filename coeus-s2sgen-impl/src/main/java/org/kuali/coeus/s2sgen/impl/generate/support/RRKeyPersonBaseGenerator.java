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
package org.kuali.coeus.s2sgen.impl.generate.support;

/**
 * This abstract class has methods that are common to all the versions of RRKeyPerson form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRKeyPersonBaseGenerator extends RRKeyPersonBase {

    protected static final String BIOSKETCH_TYPE = "1";
    protected static final String CURRENT_PENDING_TYPE = "2";
    protected static final int MAX_KEY_PERSON_COUNT = 8;
    protected static final int DIRECTORY_TITLE_MAX_LENGTH = 45;
    protected static final int ROLE_DESCRIPTION_MAX_LENGTH = 40;

    protected String pIPersonOrRolodexId = null;
}
