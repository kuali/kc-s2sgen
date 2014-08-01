/*
 * Copyright 2005-2014 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.performanceSite12V12.PerformanceSite12Document;
import gov.grants.apply.forms.performanceSite12V12.PerformanceSite12Document.PerformanceSite12;
import gov.grants.apply.forms.performanceSite12V12.SiteLocationDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.org.OrganizationYnqContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.propdev.api.location.CongressionalDistrictContract;
import org.kuali.coeus.propdev.api.location.ProposalSiteContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * 
 * This class is used to generate XML Document object for grants.gov
 * PerformanceSiteV1.2. This form is generated using XMLBean API's generated by
 * compiling PerformanceSiteV1.2 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PerformanceSiteV1_2Generator")
public class PerformanceSiteV1_2Generator extends PerformanceSiteBaseGenerator {

	private static final String QUESTION_ID_FOR_INDIVIDUAL_YNQ = "30";
	private static final int PERFORMING_ORG_LOCATION_TYPE_CODE = 2;
    private static final int OTHER_ORG_LOCATION_TYPE_CODE = 3;
    private static final int PERFORMANCE_SITE_LOCATION_TYPE_CODE = 4;

    @Value("http://apply.grants.gov/forms/PerformanceSite_1_2-V1.2")
    private String namespace;

    @Value("PerformanceSite_1_2-V1.2")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PerformanceSite-V1.2.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.performanceSite12V12")
    private String packageName;

    @Value("130")
    private int sortIndex;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    private XmlObject getPerformanceSite() {
		PerformanceSite12Document performanceSite12Document = PerformanceSite12Document.Factory
				.newInstance();
		PerformanceSite12 performanceSite12 = PerformanceSite12.Factory
				.newInstance();
		performanceSite12.setFormVersion(FormVersion.v1_2.getVersion());
		setOtherSites(performanceSite12);
		performanceSite12Document.setPerformanceSite12(performanceSite12);
		return performanceSite12Document;
	}

	private void setSiteLocationDataType(
			SiteLocationDataType siteLocationDataType, OrganizationContract organization) {
		if (organization.getOrganizationYnqs() != null
				&& !organization.getOrganizationYnqs().isEmpty()) {
			for (OrganizationYnqContract organizationYnq : organization
					.getOrganizationYnqs()) {
				if (organizationYnq.getQuestionId().equals(
						QUESTION_ID_FOR_INDIVIDUAL_YNQ)) {
					YesNoDataType.Enum answer = organizationYnq.getAnswer()
							.equals("Y") ? YesNoDataType.Y_YES
							: YesNoDataType.N_NO;
					siteLocationDataType.setIndividual(answer);
				}
			}
		}
	}

	private void setOtherSites(PerformanceSite12  performanceSite) {
		List<? extends ProposalSiteContract> proposalSites = pdDoc.getDevelopmentProposal().getProposalSites();
		if (proposalSites != null) {
			OrganizationContract organization = null;
			RolodexContract rolodex = null;
			SiteLocationDataType siteLocationDataType = null;
			for (ProposalSiteContract proposalSite : proposalSites) {
			    switch(proposalSite.getLocationTypeCode()){
			        case(PERFORMING_ORG_LOCATION_TYPE_CODE):
		                siteLocationDataType = performanceSite.addNewPrimarySite();
                        organization = proposalSite.getOrganization();
                        setSiteLocationDataType(siteLocationDataType, organization);
                        rolodex = rolodexService.getRolodex(organization.getContactAddressId());
                        break;
			        case(OTHER_ORG_LOCATION_TYPE_CODE):
			            organization = proposalSite.getOrganization();
			            rolodex = rolodexService.getRolodex(organization.getContactAddressId());
			            siteLocationDataType = performanceSite.addNewOtherSite();
			            break;
			        case(PERFORMANCE_SITE_LOCATION_TYPE_CODE):
			            rolodex = proposalSite.getRolodex();
			            siteLocationDataType = performanceSite.addNewOtherSite();
			            break;
			    }
			    if(siteLocationDataType!=null){
                    siteLocationDataType.setOrganizationName(proposalSite.getLocationName());
                    siteLocationDataType.setAddress(globLibV20Generator.getAddressDataType(rolodex));
                    if (organization!=null && organization.getDunsNumber() != null) {
                        siteLocationDataType.setDUNSNumber(organization.getDunsNumber());
                    }
                    String congressionalDistrict = getCongressionalDistrict(proposalSite);
                    if (congressionalDistrict != null) {
                        siteLocationDataType.setCongressionalDistrictProgramProject(congressionalDistrict);
                    }
			    }
			}
		}
	}

	private String getCongressionalDistrict(ProposalSiteContract proposalSite) {
		String congDistrictProject = null;
		for (CongressionalDistrictContract congDistrict : proposalSite.getCongressionalDistricts()) {
		    congDistrictProject = congDistrict.getCongressionalDistrict();
            if (congDistrictProject != null && congDistrictProject.length() > CONGRESSIONAL_DISTRICT_MAX_LENGTH) {
                congDistrictProject = congDistrictProject.substring(0, CONGRESSIONAL_DISTRICT_MAX_LENGTH);
            }
        }
		return congDistrictProject;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PerformanceSite12Document} by populating data from the given
	 * {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getPerformanceSite();
	}

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
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
