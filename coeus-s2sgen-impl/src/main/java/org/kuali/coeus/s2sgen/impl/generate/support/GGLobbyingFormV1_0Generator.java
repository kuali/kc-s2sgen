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

import gov.grants.apply.forms.ggLobbyingFormV10.LobbyingFormDocument;
import gov.grants.apply.forms.ggLobbyingFormV10.LobbyingFormDocument.LobbyingForm;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.Calendar;

/**
 * Class for generating the XML object for grants.gov GGLobbyingFormV1_0. Form is generated using XMLBean classes and is based on
 * GGLobbyingForm schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("GGLobbyingFormV1_0Generator")
public class GGLobbyingFormV1_0Generator extends GGLobbyingFormBaseGenerator {

    @Value("http://apply.grants.gov/forms/GG_LobbyingForm-V1.0")
    private String namespace;

    @Value("GG_LobbyingForm-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/GG_LobbyingForm-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.ggLobbyingFormV10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    /**
     * 
     * This method is used to get Lobbying form information such as Applicant Name, Authorized Representative
     * Title,Name,Signature,SubmittedDate.
     * 
     * @return lobbyingFormDocument(LobbyingFormDocument) {@link XmlObject} of type LobbyingFormDocument.
     */
    private LobbyingFormDocument getLobbyingForm() {

        LobbyingFormDocument lobbyingFormDocument = LobbyingFormDocument.Factory.newInstance();
        LobbyingForm lobbyingForm = LobbyingForm.Factory.newInstance();
        lobbyingForm.setFormVersion(FormVersion.v1_0.getVersion());
        OrganizationContract organization = null;
        String applicantName = EMPTY_STRING;
        String authorizedRepresentativeTitle = EMPTY_STRING;
        String authorizedRepresentativeSignature = EMPTY_STRING;

        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        }
        if (organization != null && organization.getOrganizationName() != null) {
            if (organization.getOrganizationName().length() > ORGANIZATON_NAME_MAX_LENGTH) {
                applicantName = organization.getOrganizationName().substring(0, 120);
            }
            else {
                applicantName = organization.getOrganizationName();
            }
        }

        DepartmentalPersonDto departmentalPerson = departmentalPersonService.getDepartmentalPerson(pdDoc);
        if (departmentalPerson != null) {
            if (departmentalPerson.getPrimaryTitle() != null) {
                if (departmentalPerson.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
                    authorizedRepresentativeTitle = departmentalPerson.getPrimaryTitle().substring(0, PRIMARY_TITLE_MAX_LENGTH);
                }
                else {
                    authorizedRepresentativeTitle = departmentalPerson.getPrimaryTitle();
                }
            }
            if (departmentalPerson.getFullName() != null) {
                authorizedRepresentativeSignature = departmentalPerson.getFullName();
            }
        }

        lobbyingForm.setApplicantName(applicantName);
        lobbyingForm.setAuthorizedRepresentativeTitle(authorizedRepresentativeTitle);
        lobbyingForm.setAuthorizedRepresentativeSignature(authorizedRepresentativeSignature);
        lobbyingForm.setAuthorizedRepresentativeName(globLibV10Generator.getHumanNameDataType(departmentalPerson));
        lobbyingForm.setSubmittedDate(Calendar.getInstance());
        lobbyingFormDocument.setLobbyingForm(lobbyingForm);
        return lobbyingFormDocument;
    }

    /**
     * This method creates {@link XmlObject} of type {@link LobbyingFormDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    @Override
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getLobbyingForm();
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
