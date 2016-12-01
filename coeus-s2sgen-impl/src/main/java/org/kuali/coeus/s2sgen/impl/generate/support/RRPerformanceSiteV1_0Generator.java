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

import gov.grants.apply.forms.rrPerformanceSiteV10.RRPerformanceSiteDocument;
import gov.grants.apply.forms.rrPerformanceSiteV10.RRPerformanceSiteDocument.RRPerformanceSite;
import gov.grants.apply.forms.rrPerformanceSiteV10.SiteLocationDataType;
import gov.grants.apply.forms.rrPerformanceSiteV10.SiteLocationDataType.Address;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.universalCodesV10.CountryCodeType;
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
 * This Class is used to generate XML object for grants.gov RRPerformanceSiteV1.0. This form is generated using XMLBean classes and
 * is based on RRPerformanceSite schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RRPerformanceSiteV1_0Generator")
public class RRPerformanceSiteV1_0Generator extends RRPerformanceSiteBaseGenerator {

    @Value("http://apply.grants.gov/forms/RR_PerformanceSite-V1.0")
    private String namespace;

    @Value("RR_PerformanceSite-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_PerformanceSite-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrPerformanceSiteV10")
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
        RRPerformanceSite rrPerformanceSite = rrPerformanceSiteDocument.addNewRRPerformanceSite();
        rrPerformanceSite.setFormVersion(FormVersion.v1_0.getVersion());
        
        List<? extends ProposalSiteContract> propsoalSites = pdDoc.getDevelopmentProposal().getProposalSites();
        SiteLocationDataType siteLocation = null;
        OrganizationContract organization = null;
        RolodexContract rolodex = null;
        
        for (ProposalSiteContract proposalSite : propsoalSites) {
            switch(proposalSite.getLocationTypeCode()){
                case(PERFORMING_ORG_LOCATION_TYPE_CODE):
                    siteLocation = rrPerformanceSite.addNewPrimarySite();
                    organization = proposalSite.getOrganization();
                    rolodex = rolodexService.getRolodex(organization.getContactAddressId());
                    break;
                case(OTHER_ORG_LOCATION_TYPE_CODE):
                    siteLocation = rrPerformanceSite.addNewOtherSite();
                    organization = proposalSite.getOrganization();
                    rolodex = rolodexService.getRolodex(organization.getContactAddressId());
                    break;
                case(PERFORMANCE_SITE_LOCATION_TYPE_CODE):
                    siteLocation = rrPerformanceSite.addNewOtherSite();
                    rolodex = proposalSite.getRolodex();
                    break;
            }
            if(siteLocation!=null){
                Address addressType = siteLocation.addNewAddress();
                setAddress(addressType,rolodex);
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
        return rrPerformanceSiteDocument;
    }

    /**
     * This method is to set the rolodex details to AddressType
     */
    private void setAddress(Address address, RolodexContract rolodex) {
        if (rolodex != null) {
            address.setStreet1(rolodex.getAddressLine1());
            address.setStreet2(rolodex.getAddressLine2());
            address.setCity(checkNull(rolodex.getCity()));
            address.setCounty(rolodex.getCounty());
            address.setState(rolodex.getState());
            address.setZipCode(rolodex.getPostalCode());
            if (rolodex.getCountryCode() != null) {
                CountryCodeType.Enum country = CountryCodeType.Enum.forString(rolodex.getCountryCode());
                address.setCountry(country);
            }
        }else {
            address.setStreet1("");
            address.setCity("");
        }
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRPerformanceSiteDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    @Override
    public RRPerformanceSiteDocument getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
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
