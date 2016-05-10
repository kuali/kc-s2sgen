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

import gov.grants.apply.forms.rrKeyPersonExpandedV10.PersonProfileDataType;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.PersonProfileDataType.Profile;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.PersonProfileDataType.Profile.OtherProjectRoleCategory;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.ProjectRoleDataType;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument.RRKeyPersonExpanded;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument.RRKeyPersonExpanded.AdditionalProfilesAttached;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument.RRKeyPersonExpanded.BioSketchsAttached;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument.RRKeyPersonExpanded.SupportsAttached;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@FormGenerator("RRKeyPersonExpandedV1_0Generator")
public class RRKeyPersonExpandedV1_0Generator extends RRKeyPersonExpandedBaseGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(RRKeyPersonExpandedV1_0Generator.class);

    @Value("http://apply.grants.gov/forms/RR_KeyPersonExpanded-V1.0")
    private String namespace;

    @Value("RR_KeyPersonExpanded-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_KeyPersonExpanded-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrKeyPersonExpandedV10")
    private String packageName;

    @Value("155")
    private int sortIndex;

    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;

    private RRKeyPersonExpandedDocument getRRKeyPersonExpanded() {

        LOG.info("Inside RRKeyPersonExpanded ");
        RRKeyPersonExpandedDocument rrKeyPersonExpandedDocument = RRKeyPersonExpandedDocument.Factory.newInstance();
        RRKeyPersonExpanded rrKeyPersonExpanded = RRKeyPersonExpanded.Factory.newInstance();
        rrKeyPersonExpanded.setFormVersion(FormVersion.v1_0.getVersion());
        rrKeyPersonExpanded.setPDPI(getPersonProfilePI());
        rrKeyPersonExpanded.setKeyPersonArray(getpersonProfileKeyPerson());

        if (extraPersons.size() > 0) {
            for (ProposalPersonContract extraPerson : extraPersons) {
                BioSketchsAttached personBioSketch = BioSketchsAttached.Factory.newInstance();
                AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson
                        .getRolodexId(), BIOSKETCH_TYPE);
                personBioSketch.setBioSketchAttached(bioSketchAttachment);
                rrKeyPersonExpanded.setBioSketchsAttached(personBioSketch);

                AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson
                        .getRolodexId(), CURRENT_PENDING_TYPE);
                if (supportAttachment != null) {
                    SupportsAttached supportsAttached = SupportsAttached.Factory.newInstance();
                    supportsAttached.setSupportAttached(supportAttachment);
                    rrKeyPersonExpanded.setSupportsAttached(supportsAttached);
                }
            }

            for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                if (narrative.getNarrativeType().getCode() != null) {
                    if (Integer.parseInt(narrative.getNarrativeType().getCode()) == PROFILE_TYPE) {
                        AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                    	if(attachedFileDataType != null){
	                        AdditionalProfilesAttached additionalProfilesAttached = AdditionalProfilesAttached.Factory.newInstance();
	                        additionalProfilesAttached.setAdditionalProfileAttached(attachedFileDataType);
	                        rrKeyPersonExpanded.setAdditionalProfilesAttached(additionalProfilesAttached);
	                        break;
                    	}
                    }
                }
            }
        }
        rrKeyPersonExpandedDocument.setRRKeyPersonExpanded(rrKeyPersonExpanded);
        return rrKeyPersonExpandedDocument;
    }

    private PersonProfileDataType getPersonProfilePI() {

        PersonProfileDataType profileDataType = PersonProfileDataType.Factory.newInstance();
        Profile profile = Profile.Factory.newInstance();
        ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
        if (PI != null) {
            if (PI.getPersonId() != null) {
                pIPersonOrRolodexId = PI.getPersonId();
            }
            else if (PI.getRolodexId() != null) {
                pIPersonOrRolodexId = PI.getRolodexId().toString();
            }
            profile.setName(globLibV10Generator.getHumanNameDataType(PI));
            if (PI.getDirectoryTitle() != null) {
                if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
                    profile.setTitle(PI.getDirectoryTitle().substring(0, DIRECTORY_TITLE_MAX_LENGTH));
                }
                else {
                    profile.setTitle(PI.getDirectoryTitle());
                }
            }
            profile.setAddress(globLibV10Generator.getAddressRequireCountryDataType(PI));
            profile.setPhone(PI.getOfficePhone());
            if (StringUtils.isNotEmpty(PI.getFaxNumber())) {
                profile.setFax(PI.getFaxNumber());
            }
            profile.setEmail(PI.getEmailAddress());
            if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
                profile.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
            }
            if(PI.getHomeUnit() != null) {
                KcPersonContract kcPerson = PI.getPerson();
                String departmentName =  kcPerson.getOrganizationIdentifier();
                profile.setDepartmentName(StringUtils.substring(departmentName, 0, DEPARTMENT_NAME_MAX_LENGTH));
            }
            else
            {
                DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
                profile.setDepartmentName(StringUtils.substring(developmentProposal.getOwnedByUnit().getUnitName(), 0, DEPARTMENT_NAME_MAX_LENGTH));
            }
            String divisionName = PI.getDivision();
            if (divisionName != null) {
                profile.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
            }
            if (PI.getEraCommonsUserName() != null) {
                profile.setCredential(PI.getEraCommonsUserName());
            }
            profile.setProjectRole(ProjectRoleDataType.PD_PI);

            PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
                    .newInstance();
            AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(),
                    BIOSKETCH_TYPE);
            personBioSketch.setBioSketchAttached(bioSketchAttachment);
            profile.setBioSketchsAttached(personBioSketch);

            AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(),
                    CURRENT_PENDING_TYPE);
            if (supportAttachment != null) {
                PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory
                        .newInstance();
                supportsAttached.setSupportAttached(supportAttachment);
                profile.setSupportsAttached(supportsAttached);
            }
            profileDataType.setProfile(profile);
        }
        return profileDataType;
    }

    private PersonProfileDataType[] getpersonProfileKeyPerson() {

        List<PersonProfileDataType> personProfileDataTypeList = new ArrayList<>();
        List<? extends ProposalPersonContract> keyPersons = pdDoc.getDevelopmentProposal().getProposalPersons();
        Collections.sort(keyPersons, new ProposalPersonComparator());
        List<ProposalPersonContract> nKeyPersons = s2SProposalPersonService.getNKeyPersons(keyPersons, MAX_KEY_PERSON_COUNT);
        extraPersons = keyPersons.stream()
                .filter(kp -> !nKeyPersons.contains(kp))
                .collect(Collectors.toList());
        if (nKeyPersons.size() > 0) {
            for (ProposalPersonContract keyPerson : nKeyPersons) {
                if (pIPersonOrRolodexId != null) {
                    // Don't add PI to keyperson list
                    if (keyPerson.getPersonId() != null && keyPerson.getPersonId().equals(pIPersonOrRolodexId)) {
                        continue;
                    }
                    else if ((keyPerson.getRolodexId() != null) && pIPersonOrRolodexId.equals(keyPerson.getRolodexId().toString())) {
                        continue;
                    }
                }
                Profile profileKeyPerson = Profile.Factory.newInstance();
                profileKeyPerson.setName(globLibV10Generator.getHumanNameDataType(keyPerson));
                if (keyPerson.getDirectoryTitle() != null) {
                    if (keyPerson.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
                        profileKeyPerson.setTitle(keyPerson.getDirectoryTitle().substring(0, DIRECTORY_TITLE_MAX_LENGTH));
                    }
                    else {
                        profileKeyPerson.setTitle(keyPerson.getDirectoryTitle());
                    }
                }
                profileKeyPerson.setAddress(globLibV10Generator.getAddressRequireCountryDataType(keyPerson));
                profileKeyPerson.setPhone(keyPerson.getOfficePhone());
                if (StringUtils.isNotEmpty(keyPerson.getFaxNumber())) {
                    profileKeyPerson.setFax(keyPerson.getFaxNumber());
                }
                profileKeyPerson.setEmail(keyPerson.getEmailAddress());
                profileKeyPerson.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());               
                if(keyPerson.getHomeUnit() != null) {
                    KcPersonContract kcPerson = keyPerson.getPerson();
                    String departmentName =  kcPerson.getOrganizationIdentifier();
                    profileKeyPerson.setDepartmentName(StringUtils.substring(departmentName, 0, DEPARTMENT_NAME_MAX_LENGTH));
                }
                else
                {
                    DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
                    profileKeyPerson.setDepartmentName(StringUtils.substring(developmentProposal.getOwnedByUnit().getUnitName(), 0, DEPARTMENT_NAME_MAX_LENGTH));
                }
                String divisionName = keyPerson.getDivision();
                if (divisionName != null) {
                    profileKeyPerson.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
                }
                if (keyPerson.getEraCommonsUserName() != null) {
                    profileKeyPerson.setCredential(keyPerson.getEraCommonsUserName());
                }
                if (keyPerson.isMultiplePi() || keyPerson.isCoInvestigator()) {
                    if(sponsorHierarchyService.isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())){
                        if (keyPerson.isMultiplePi()) {
                            profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
                        } else {
                            profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
                        }
                    }else{
                        profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
                    }
                } else {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.OTHER_SPECIFY);
                    OtherProjectRoleCategory otherProjectRole = OtherProjectRoleCategory.Factory.newInstance();
                    String otherRole;
                    if (keyPerson.getProjectRole() != null) {
                        if (keyPerson.getProjectRole().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
                            otherRole = keyPerson.getProjectRole().substring(0, ROLE_DESCRIPTION_MAX_LENGTH);
                        }
                        else {
                            otherRole = keyPerson.getProjectRole();
                        }
                    }
                    else {
                        otherRole = FieldValueConstants.VALUE_UNKNOWN;
                    }
                    otherProjectRole.setStringValue(otherRole);
                    profileKeyPerson.setOtherProjectRoleCategory(otherProjectRole);
                }

                PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
                        .newInstance();
                AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, keyPerson.getPersonId(), keyPerson
                        .getRolodexId(), BIOSKETCH_TYPE);
                personBioSketch.setBioSketchAttached(bioSketchAttachment);
                profileKeyPerson.setBioSketchsAttached(personBioSketch);

                AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, keyPerson.getPersonId(), keyPerson
                        .getRolodexId(), CURRENT_PENDING_TYPE);
                if (supportAttachment != null) {
                    PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory
                            .newInstance();
                    supportsAttached.setSupportAttached(supportAttachment);
                    profileKeyPerson.setSupportsAttached(supportsAttached);
                }

                PersonProfileDataType personProfileDataTypeKeyPerson = PersonProfileDataType.Factory.newInstance();
                personProfileDataTypeKeyPerson.setProfile(profileKeyPerson);
                personProfileDataTypeList.add(personProfileDataTypeKeyPerson);
            }
        }
        PersonProfileDataType[] personProfileDataArray = new PersonProfileDataType[0];
        personProfileDataArray = personProfileDataTypeList.toArray(personProfileDataArray);
        return personProfileDataArray;
    }

    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRKeyPersonExpanded();
    }

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
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
