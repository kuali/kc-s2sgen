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

import gov.grants.apply.forms.cd511V11.CD511Document;
import gov.grants.apply.forms.cd511V11.CD511Document.CD511;
import org.apache.xmlbeans.XmlObject;

import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.Calendar;

/**
 * 
 * This class is used to generate XML Document object for grants.gov CD511V1.1. This form is generated using XMLBean API's generated
 * by compiling CD511V1_1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("CD511V1_1Generator")
public class CD511V1_1Generator extends S2SBaseFormGenerator {

    @Value("http://apply.grants.gov/forms/CD511-V1.1")
    private String namespace;

    @Value("CD511-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/CD511-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.CD511V11")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    @Autowired
    @Qualifier("departmentalPersonService")
    private DepartmentalPersonService departmentalPersonService;

    private DepartmentalPersonDto aorInfo;

    /**
     * 
     * This method returns CD511Document object based on proposal development document which contains the CD511Document informations
     * OrganizationName,AwardNumber,ProjectName,ContactName,Title,Signature,SubmittedDate for a particular proposal
     * 
     * @return cd511Document(CD511Document){@link XmlObject} of type CD511Document.
     */
    private CD511Document getcd511Document() {
        CD511Document cd511Document = CD511Document.Factory.newInstance();
        CD511 cd511 = CD511.Factory.newInstance();
        cd511.setFormVersion(FormVersion.v1_1.getVersion());

        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            cd511.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
        }
        if (pdDoc.getDevelopmentProposal().getCurrentAwardNumber() != null && !pdDoc.getDevelopmentProposal().getCurrentAwardNumber().equals("")) {
            cd511.setAwardNumber(pdDoc.getDevelopmentProposal().getCurrentAwardNumber());
        }
        if (pdDoc.getDevelopmentProposal().getTitle() != null && !pdDoc.getDevelopmentProposal().getTitle().equals("")) {
            cd511.setProjectName(pdDoc.getDevelopmentProposal().getTitle());
        }

        String title = "";
        if (aorInfo != null) {
            cd511.setContactName(globLibV20Generator.getHumanNameDataType(aorInfo));
            if (aorInfo.getPrimaryTitle() != null && !aorInfo.getPrimaryTitle().equals("")) {
                title = aorInfo.getPrimaryTitle();
            }
        }
        cd511.setTitle(title);

        // As per Coeus,CD511-V1.1 data analysis file said:
        // if this application is submitted through Grants.gov leave signature to blank
        cd511.setSignature("  ");
        cd511.setSubmittedDate(Calendar.getInstance());
        cd511Document.setCD511(cd511);
        return cd511Document;
    }

    /**
     * This method creates {@link XmlObject} of type {@link CD511Document} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    @Override
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        aorInfo = departmentalPersonService.getDepartmentalPerson(pdDoc);
        return getcd511Document();
    }

    public DepartmentalPersonService getDepartmentalPersonService() {
        return departmentalPersonService;
    }

    public void setDepartmentalPersonService(DepartmentalPersonService departmentalPersonService) {
        this.departmentalPersonService = departmentalPersonService;
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
