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
package org.kuali.coeus.s2sgen.impl.generate;

import org.apache.xmlbeans.XmlOptions;
import org.kuali.coeus.s2sgen.api.core.S2SException;

/**
 * 
 * This interface defines the service that is used to create instances of opportunity form generator classes.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface S2SFormGeneratorRetrievalService {

    /**
     * 
     * This method is used to create and return a form generator instance. Based on the namespace provided as parameter, it
     * instantiates the respective generator class and returns it.
     * 
     * @param nameSpace {@link String}
     * @return S2SFormGenerator form generator instances corresponding to the name space.
     *
     */
    S2SFormGenerator getS2SGenerator(String proposalNumber,String nameSpace) throws S2SException;
    /**
     * This method is to get the namespace prefixes for some individual forms like, Subaward Budget and PHS398_ResearchTrainingProgramPlan
     */
    XmlOptions getXmlOptionsPrefixes();

}
