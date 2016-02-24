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

import gov.grants.apply.forms.rrPerformanceSiteV11.RRPerformanceSiteDocument;
import gov.grants.apply.forms.rrPerformanceSiteV11.RRPerformanceSiteDocument.RRPerformanceSite;
import gov.grants.apply.forms.rrPerformanceSiteV11.SiteLocationDataType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.propdev.api.location.ProposalSiteContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.List;

/**
 * This Class is used to generate XML object for grants.gov RRPerformanceSiteV1.1. This form is generated using XMLBean classes and
 * is based on RRPerformanceSitev1.1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RRPerformanceSiteV1_1Generator")
public class RRPerformanceSiteV1_1Generator extends RRPerformanceSiteBaseGenerator {

    @Value("http://apply.grants.gov/forms/RR_PerformanceSite-V1.1")
    private String namespace;

    @Value("RR_PerformanceSite-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_PerformanceSite-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrPerformanceSiteV11")
    private String packageName;

    @Value("130")
    private int sortIndex;

    /**
     * 
     * This method gets RRPerformanceSite informations like OrganizationName,Address,PerformanceSite Attachment based on the
     * proposal Development document.
     * 
     * @return rrPerformanceSite {@link XmlObject} of type RRPerformanceSiteDocument.
     */
    private RRPerformanceSiteDocument getRRPerformanceSite() {
        RRPerformanceSiteDocument rrPerformanceSiteDocument = RRPerformanceSiteDocument.Factory.newInstance();
        RRPerformanceSite rrPerformanceSite = RRPerformanceSite.Factory.newInstance();
        rrPerformanceSite.setFormVersion(FormVersion.v1_1.getVersion());
        
        
        List<? extends ProposalSiteContract> propsoalSites = pdDoc.getDevelopmentProposal().getProposalSites();
        SiteLocationDataType siteLocation = null;
        OrganizationContract organization = null;
        RolodexContract rolodex = null;
        
        for (ProposalSiteContract proposalSite : propsoalSites) {
            switch(proposalSite.getLocationTypeCode()){
                case(PERFORMING_ORG_LOCATION_TYPE_CODE):
                    siteLocation = rrPerformanceSite.addNewPrimarySite();
                    organization = proposalSite.getOrganization();
                    if(organization!=null){
                        rolodex = rolodexService.getRolodex(organization.getContactAddressId());
                    }
                    break;
                case(OTHER_ORG_LOCATION_TYPE_CODE):
                    siteLocation = rrPerformanceSite.addNewOtherSite();
                    organization = proposalSite.getOrganization();
                    if(organization!=null){
                        rolodex = rolodexService.getRolodex(organization.getContactAddressId());
                    }
                    break;
                case(PERFORMANCE_SITE_LOCATION_TYPE_CODE):
                    siteLocation = rrPerformanceSite.addNewOtherSite();
                    rolodex = proposalSite.getRolodex();
                    break;
            }
            if(siteLocation!=null){
                siteLocation.setAddress(globLibV20Generator.getAddressDataType(rolodex));
                siteLocation.setOrganizationName(proposalSite.getLocationName());
            }
        }
        
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && Integer.parseInt(narrative.getNarrativeType().getCode()) == PERFORMANCE_SITES_ATTACHMENT) {
            	AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
            	if(attachedFileDataType != null){
            		rrPerformanceSite.setAttachedFile(attachedFileDataType);
            		break;
            	}
            }
        }
        rrPerformanceSiteDocument.setRRPerformanceSite(rrPerformanceSite);
        return rrPerformanceSiteDocument;
    }


    /**
     * This method creates {@link XmlObject} of type {@link RRPerformanceSiteDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRPerformanceSite();
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
