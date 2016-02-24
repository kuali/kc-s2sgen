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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.rrPersonalDataV10.DirectorType;
import gov.grants.apply.forms.rrPersonalDataV10.RRPersonalDataDocument;
import gov.grants.apply.forms.rrPersonalDataV10.RRPersonalDataDocument.RRPersonalData;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating the XML object for grants.gov RRPersonalDataV1.0. Form is generated using XMLBean classes and is based on
 * RRPersonalData schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RRPersonalDataV1_0Generator")
public class RRPersonalDataV1_0Generator extends RRPersonalDataBaseGenerator {

    @Value("http://apply.grants.gov/forms/RR_PersonalData-V1.0")
    private String namespace;

    @Value("RR_PersonalData-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_PersonalData-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrPersonalDataV10")
    private String packageName;

    @Value("157")
    private int sortIndex;

    /**
     * 
     * This method gives the personal information of ProjectDirector and CoProjectDirector
     * 
     * @return rrPersonalDataDocument {@link XmlObject} of type RRPersonalDataDocument.
     */
    private RRPersonalDataDocument getRRPersonalData() {
        RRPersonalDataDocument rrPersonalDataDocument = RRPersonalDataDocument.Factory.newInstance();
        RRPersonalData rrPersonalData = RRPersonalData.Factory.newInstance();
        rrPersonalData.setFormVersion(FormVersion.v1_0.getVersion());
        rrPersonalData.setProjectDirector(getProjectDirectorType());
        rrPersonalData.setCoProjectDirectorArray(getCoProjectDirectoryType());
        rrPersonalDataDocument.setRRPersonalData(rrPersonalData);
        return rrPersonalDataDocument;
    }

    /**
     * 
     * This method is used to get Personal details of Principal Investigator
     * 
     * @return DirectorType principal investigator details.
     */
    private DirectorType getProjectDirectorType() {

        DirectorType directorType = DirectorType.Factory.newInstance();
        ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
        if (PI != null) {
            directorType.setName(globLibV10Generator.getHumanNameDataType(PI));
        }
        return directorType;
    }

    /**
     * 
     * This method is used to get List of Personal details of Co-Investigator
     * 
     * @return DirectorType[] Array of director types.
     */
    private DirectorType[] getCoProjectDirectoryType() {
        DirectorType[] directorTypes = new DirectorType[0];
        List<DirectorType> directorTypeList = new ArrayList<DirectorType>();
        if (pdDoc.getDevelopmentProposal().getProposalPersons() != null) {
            ProposalPersonContract CoPI = null;
            for (ProposalPersonContract proposalPerson : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                DirectorType coDirectorType = DirectorType.Factory.newInstance();
                if (proposalPerson.getProposalPersonRoleId() != null) {
                    if (KEYPERSON_TYPE_C0_INVESTIGATOR.equals(proposalPerson.getProposalPersonRoleId())) {
                        CoPI = proposalPerson;
                        coDirectorType.setName(globLibV10Generator.getHumanNameDataType(CoPI));
                        directorTypeList.add(coDirectorType);
                    }
                }
            }
        }
        directorTypes = directorTypeList.toArray(directorTypes);
        return directorTypes;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRPersonalDataDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRPersonalData();
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
