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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * This class holds information about form generation.  For example: what forms are supported,
 * the name of the generator, the location of the stylesheet.
 */
public class FormMappingInfo{
    private String nameSpace;
    private String generatorName;
    private String formName;
    private String stylesheet;
    private int sortIndex;
    private Boolean userAttachedForm = false;
    
    private static final String KEY_NAMESPACE = "nameSpace";
    private static final String KEY_MAIN_CLASS = "generatorName";
    private static final String KEY_FORM_NAME = "formName";
    private static final String KEY_STYLE_SHEET = "stylesheet";

    public FormMappingInfo() {
        super();
    }

    public FormMappingInfo(String nameSpace, String generatorName, String formName, String stylesheet, int sortIndex, Boolean userAttachedForm) {
        this.nameSpace = nameSpace;
        this.generatorName = generatorName;
        this.formName = formName;
        this.stylesheet = stylesheet;
        this.sortIndex = sortIndex;
        this.userAttachedForm = userAttachedForm;
    }

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getStyleSheet() {
        return stylesheet;
    }

    public void setStyleSheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Boolean getUserAttachedForm() {
        return userAttachedForm;
    }

    public void setUserAttachedForm(Boolean userAttachedForm) {
        this.userAttachedForm = userAttachedForm;
    }

    public String toString() {
        Map<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put(KEY_NAMESPACE, getNameSpace());
        hashMap.put(KEY_MAIN_CLASS, getGeneratorName());
        hashMap.put(KEY_FORM_NAME, getFormName());
        hashMap.put(KEY_STYLE_SHEET, getStyleSheet());
        return hashMap.toString();
    }
}
