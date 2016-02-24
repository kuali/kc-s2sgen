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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.s2s.UserAttachedFormService;
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Component("formMappingService")
public class FormMappingServiceImpl implements FormMappingService {

    private static final String BASE_PACKAGE_PATH = "org/kuali/coeus/s2sgen/impl/generate";
    private static final Logger LOG = LoggerFactory.getLogger(FormMappingServiceImpl.class);


    private Map<String, FormMappingInfo> bindings = new ConcurrentHashMap<>();
    private Map<Integer, Set<String>> sortedNameSpaces = new ConcurrentHashMap<>();

    @Autowired
    @Qualifier("userAttachedFormService")
    private UserAttachedFormService userAttachedFormService;

    /**
     * 
     * This method is used to get the Form Information based on the given schema
     * 
     * @param namespace {@link String} namespace URL of the form
     * @return {@link FormMappingInfo}containing the namespace information
     */
    @Override
    public FormMappingInfo getFormInfo(String namespace) {

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

        return bindings.get(namespace);
    }

    @Override
    public FormMappingInfo getFormInfo(String namespace, String proposalNumber) {

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

        FormMappingInfo formMappingInfo = bindings.get(namespace);
        if (formMappingInfo != null) {
            return formMappingInfo;
        } else if (proposalNumber != null) {
            return getUserAttachedForm(namespace, proposalNumber);
        }

        return null;
    }

    protected FormMappingInfo getUserAttachedForm(String namespace, String proposalNumber) {
        final String formName = getUserAttachedFormService().findFormNameByProposalNumberAndNamespace(proposalNumber, namespace);

        if (formName != null) {
            FormMappingInfo mappingInfo = new FormMappingInfo();
            mappingInfo.setFormName(formName);
            mappingInfo.setGeneratorName("UserAttachedFormGenerator");
            mappingInfo.setNameSpace(namespace);
            mappingInfo.setSortIndex(999);
            mappingInfo.setStyleSheet(createStylesheetName(namespace));
            mappingInfo.setUserAttachedForm(true);
            return mappingInfo;
        } else {
            return null;
        }
        
    }

    protected String createStylesheetName(String namespace) {
        String[] tokens = namespace.split("/");
        String formname = tokens[tokens.length-1];

        String pathUrl = BASE_PACKAGE_PATH + "/support/stylesheet/" + formname + ".xsl";
        Resource resource = new ClassPathResource(pathUrl);
        String path;
        if (resource.exists()) {
            try {
                path = resource.getURL().toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            LOG.warn("No resource found at " + pathUrl);
            path = "";
        }
       return path;
    }

    @Override
    public Map<String, FormMappingInfo> getBindings() {
        return bindings == null ? Collections.<String, FormMappingInfo>emptyMap() : new HashMap<>(bindings);
    }

    @Override
    public Map<Integer, Set<String>> getSortedNameSpaces() {
        return sortedNameSpaces == null ? Collections.<Integer, Set<String>>emptyMap() : new HashMap<>(sortedNameSpaces);
    }

    @Override
    public void registerForm(FormMappingInfo info) {
        bindings.put(info.getNameSpace(), info);

        Set<String> list = sortedNameSpaces.get(info.getSortIndex());
        if (list == null) {
            list = new HashSet<>();
            sortedNameSpaces.put(info.getSortIndex(), list);
        }

        list.add(info.getNameSpace());
    }

    public UserAttachedFormService getUserAttachedFormService() {
        return userAttachedFormService;
    }

    public void setUserAttachedFormService(UserAttachedFormService userAttachedFormService) {
        this.userAttachedFormService = userAttachedFormService;
    }
}
