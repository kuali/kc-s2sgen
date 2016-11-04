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
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class is used as a service implementation that is used to create instances of opportunity form generator classes. It
 * provides an abstraction level over the different generator class implementations.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("s2SFormGeneratorRetrievalService")
public class S2SFormGeneratorRetrievalServiceImpl implements S2SFormGeneratorRetrievalService {
    private static final Logger LOG = LoggerFactory.getLogger(S2SFormGeneratorRetrievalServiceImpl.class);

    @Autowired
    @Qualifier("formMappingService")
    private FormMappingService formMappingService;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     *
     * This method is used to create and return a form generator instance. Based on the namespace provided as parameter, it
     * instantiates the respective generator class and returns it.
     * 
     * @param namespace name space of the generator.
     * @return S2SFormGenerator form generator instances corresponding to the name space.
     * @throws S2SException if generator could not be loaded
     * @throws org.kuali.coeus.s2sgen.api.core.S2SException if form generator for given namespace is not available
     */
    @Override
    public final S2SFormGenerator getS2SGenerator(String proposalNumber, String namespace) throws S2SException {
        FormMappingInfo formInfo = formMappingService.getFormInfo(namespace,proposalNumber);
        S2SFormGenerator formGenerator = (S2SFormGenerator) applicationContext.getBean(formInfo.getGeneratorName());

        if (formGenerator == null) {
            throw new S2SException("Generator not found with name: " + formInfo.getGeneratorName());
        }

        if (formGenerator instanceof DynamicNamespace) {
            ((DynamicNamespace) formGenerator).setNamespace(namespace);
        }

        return formGenerator;
    }


    @Override
    public XmlOptions getXmlOptionsPrefixes() {
        XmlOptions xmlOptions = new XmlOptions();
        Map<String, String> prefixMap = new HashMap<>();
        prefixMap.put("http://apply.grants.gov/system/MetaGrantApplication", "");
        xmlOptions.setSaveSuggestedPrefixes(prefixMap);
        return xmlOptions;
    }

    public FormMappingService getFormMappingService() {
        return formMappingService;
    }

    public void setFormMappingService(FormMappingService formMappingService) {
        this.formMappingService = formMappingService;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
