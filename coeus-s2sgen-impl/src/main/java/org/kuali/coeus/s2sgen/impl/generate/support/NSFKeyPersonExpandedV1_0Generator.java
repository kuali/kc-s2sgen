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

import gov.grants.apply.coeus.personProfile.PersonProfileListDocument;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.NSFKeyPersonExpandedDocument;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded.AdditionalProfilesAttached;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.PersonProfileDataType;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.PersonProfileDataType.Profile;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.PersonProfileDataType.Profile.OtherProjectRoleCategory;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.ProjectRoleDataType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.person.KcPersonRepositoryService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.common.api.unit.UnitRepositoryService;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonDegreeContract;
import org.kuali.coeus.propdev.api.person.attachment.ProposalPersonBiographyContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.kuali.coeus.s2sgen.impl.print.GenericPrintable;
import org.kuali.coeus.s2sgen.impl.print.S2SPrintingService;
import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;
import org.kuali.coeus.sys.api.model.KcFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@FormGenerator("NSFKeyPersonExpandedV1_0Generator")
public class NSFKeyPersonExpandedV1_0Generator extends S2SBaseFormGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(NSFKeyPersonExpandedV1_0Generator.class);

    protected static final int TITLE_MAX_LENGTH = 45;
    protected static final int ROLE_DESCRIPTION_MAX_LENGTH = 40;
    private static final int MAX_KEY_PERSON_COUNT = 100;
    protected static final int BIOSKETCH_DOC_TYPE = 16;
    protected static final int CURRENTPENDING_DOC_TYPE = 17;
    protected static final int COLLABORATOR_DOC_TYPE = 148;
    protected static final String BIOSKETCH_TYPE = "1";
    protected static final String COLLABORATOR_TYPE = "6";
    protected static final String CURRENT_PENDING_TYPE = "2";
    private static final String COMMENT = "Auto generated document for ";
    private static final String BIOSKETCH_COMMENT = "BIOSKETCH";
    private static final String CURRENT_PENDING_COMMENT = "CURRENTPENDING";
    private static final String COLLABORATOR_COMMENT = "COLLABORATOR";
    protected static final String PROFILE_COMMENT = "PROFILE";
    protected static final int PROFILE_TYPE = 18;
    protected static final int DIVISION_NAME_MAX_LENGTH = 30;
    protected static final String NIH_CO_INVESTIGATOR = "Co-Investigator";
    protected static final String ERROR_ERA_COMMON_USER_NAME = "eRA Commons User Name is missing for ";
    protected static final int DEPARTMENT_NAME_MAX_LENGTH = 30;
    private static final int ADDRESS_LINE_MAX_LENGTH = 55;

    protected RolodexContract rolodex;
    protected List<ProposalPersonContract> extraPersons = null;
    protected String pIPersonOrRolodexId = null;

    @Autowired
    @Qualifier("s2SPrintingService")
    private S2SPrintingService s2SPrintingService;

    @Autowired
    @Qualifier("s2SProposalPersonService")
    protected S2SProposalPersonService s2SProposalPersonService;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/additionalkeypersonprofiles.xsl")
    private Resource additionalkeypersonprofilesStyleSheet;

    @Value("http://apply.grants.gov/forms/NSF_KeyPersonExpanded-V1.0")
    private String namespace;

    @Value("NSFKeyPersonExpanded_1_0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/NSF_KeyPersonExpanded-V1.0.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.nsfKeyPersonExpandedV10")
    private String packageName;

    @Value("155")
    private int sortIndex;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    @Autowired
    @Qualifier("kcPersonRepositoryService")
    private KcPersonRepositoryService kcPersonRepositoryService;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    @Autowired
    @Qualifier("unitRepositoryService")
    private UnitRepositoryService unitRepositoryService;

    protected void saveKeyPersonAttachmentsToProposal() {
        if (!CollectionUtils.isEmpty(extraPersons)) {
            saveKeyPersonAttachments();
            saveKeypersonProfileObject();
        }
    }

    private NarrativeContract[] saveKeyPersonAttachments() {
        List<String> bioSketchBookMarks = new ArrayList<>();
        List<String> curPendBookMarks = new ArrayList<>();
        List<String> collaboratorBookMarks = new ArrayList<>();
        List<byte[]> bioSketchDataList = new ArrayList<>();
        List<byte[]> curPendDataList = new ArrayList<>();
        List<byte[]> collaboratorDataList = new ArrayList<>();

        NarrativeContract[] extraKeyPersonAttachments = new NarrativeContract[2];
        for (ProposalPersonContract proposalPerson : extraPersons) {
            setBookMarkAndData(bioSketchBookMarks, bioSketchDataList, proposalPerson, BIOSKETCH_TYPE);
            setBookMarkAndData(curPendBookMarks, curPendDataList, proposalPerson, CURRENT_PENDING_TYPE);
            setBookMarkAndData(collaboratorBookMarks, collaboratorDataList, proposalPerson, COLLABORATOR_TYPE);
        }
        byte[] bioSketchData;
        byte[] curPendData;
        byte[] collaboratorData;
        try {
            bioSketchData = s2SPrintingService.mergePdfBytes(bioSketchDataList, bioSketchBookMarks, true);
            curPendData = s2SPrintingService.mergePdfBytes(curPendDataList, curPendBookMarks, true);
            collaboratorData = s2SPrintingService.mergePdfBytes(collaboratorDataList, collaboratorBookMarks, true);
            String fileName;
            if (bioSketchData != null && bioSketchData.length > 0) {
                fileName = pdDoc.getDevelopmentProposal().getProposalNumber() + "_" + BIOSKETCH_COMMENT + ".pdf";
                extraKeyPersonAttachments[0] = saveNarrative(bioSketchData, "" + BIOSKETCH_DOC_TYPE, fileName, COMMENT + BIOSKETCH_COMMENT);
            }
            if (curPendData != null && curPendData.length > 0) {
                fileName = pdDoc.getDevelopmentProposal().getProposalNumber() + "_" + CURRENT_PENDING_COMMENT + ".pdf";
                extraKeyPersonAttachments[1] = saveNarrative(curPendData, "" + CURRENTPENDING_DOC_TYPE, fileName, COMMENT + CURRENT_PENDING_COMMENT);
            }
            if (collaboratorData != null && collaboratorData.length > 0) {
                fileName = pdDoc.getDevelopmentProposal().getProposalNumber() + "_" + COLLABORATOR_COMMENT + ".pdf";
                extraKeyPersonAttachments[1] = saveNarrative(collaboratorData, "" + COLLABORATOR_DOC_TYPE, fileName, COMMENT + COLLABORATOR_COMMENT);
            }
        } catch (S2SException e) {
            LOG.error("Auto generation of Biosketch/Currend Pending report for extra Keypersons is failed", e);
        }
        return extraKeyPersonAttachments;
    }

    private void setBookMarkAndData(List<String> bookMarksList,
                                    List<byte[]> dataList, ProposalPersonContract proposalPerson, String docType) {
        final String personId;
        if (proposalPerson.getPersonId() != null && proposalPerson.getPersonId().length() > 0) {
            personId = proposalPerson.getPersonId();
        } else {
            personId = "" + proposalPerson.getRolodexId();
        }

        for (ProposalPersonBiographyContract personBiography : getPernonnelAttachments(pdDoc, proposalPerson, docType)) {
            byte[] content = personBiography.getPersonnelAttachment().getData();
            if (content != null && content.length > 0) {
                dataList.add(content);
                bookMarksList.add(personId);
            }
        }
    }

    private List<ProposalPersonBiographyContract> getPernonnelAttachments(ProposalDevelopmentDocumentContract pdDoc, ProposalPersonContract proposalPerson, String documentType) {
        List<ProposalPersonBiographyContract> result = new ArrayList<>();
        for (ProposalPersonBiographyContract proposalPersonBiography : pdDoc.getDevelopmentProposal().getPropPersonBios()) {
            String personId = proposalPerson.getPersonId();
            Integer rolodexId = proposalPerson.getRolodexId();
            if (personId != null && proposalPersonBiography.getPersonId() != null && proposalPersonBiography.getPersonId().equals(personId) && documentType.equals(proposalPersonBiography.getPropPerDocType().getCode())) {
                result.add(proposalPersonBiography);
            } else if (rolodexId != null && proposalPersonBiography.getRolodexId() != null
                    && proposalPersonBiography.getRolodexId().toString().equals(rolodexId.toString())
                    && documentType.equals(proposalPersonBiography.getPropPerDocType().getCode())) {
                result.add(proposalPersonBiography);
            }
        }
        return result;
    }

    protected PersonProfileListDocument.PersonProfileList.ExtraKeyPerson[] getExtraKeyPersons() {
        List<PersonProfileListDocument.PersonProfileList.ExtraKeyPerson> extraPersonList = new ArrayList<>();

        for (ProposalPersonContract proposalPerson : extraPersons) {

            PersonProfileListDocument.PersonProfileList.ExtraKeyPerson extraPerson = PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Factory.newInstance();

            extraPerson.setName(getExtraPersonName(proposalPerson));
            extraPerson.setAddress(getExtraPersonAddress(proposalPerson));
            if (proposalPerson.getPrimaryTitle() != null && proposalPerson.getPrimaryTitle().length() > TITLE_MAX_LENGTH)
                extraPerson.setTitle(proposalPerson.getPrimaryTitle().substring(0, TITLE_MAX_LENGTH));
            else {
                extraPerson.setTitle(proposalPerson.getPrimaryTitle());
            }

            if (proposalPerson.getProposalPersonRoleId() != null) {
                if (proposalPerson.isPrincipalInvestigator()) {
                    extraPerson.setProjectRole(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.PD_PI);
                } else if (proposalPerson.isCoInvestigator()) {
                    if (isSponsorNIH(pdDoc)) {
                        extraPerson.setProjectRole(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.OTHER_SPECIFY);
                        extraPerson.setOtherProjectRoleCategory(NIH_CO_INVESTIGATOR);
                    } else {
                        extraPerson.setProjectRole(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.CO_PD_PI);
                    }
                } else {
                    final String otherRole;
                    if (proposalPerson.getProjectRole() != null && proposalPerson.getProjectRole().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
                        otherRole = proposalPerson.getProjectRole().substring(0, ROLE_DESCRIPTION_MAX_LENGTH);
                    } else {
                        otherRole = proposalPerson.getProjectRole();
                    }
                    extraPerson.setOtherProjectRoleCategory(otherRole);
                    extraPerson.setProjectRole(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.OTHER_SPECIFY);
                }
            }

            if (proposalPerson.getEraCommonsUserName() != null) {
                extraPerson.setCredential(proposalPerson.getEraCommonsUserName());
            }
            setDepartmentName(extraPerson);
            setDivisionName(extraPerson);
            if (proposalPerson.getEmailAddress() != null) {
                extraPerson.setEmail(proposalPerson.getEmailAddress());
            }
            if (StringUtils.isNotEmpty(proposalPerson.getFaxNumber())) {
                extraPerson.setFax(proposalPerson.getFaxNumber());
            }
            UnitContract unit = unitRepositoryService.findUnitByUnitNumber(proposalPerson.getHomeUnit());

            if (unit != null && unit.getUnitName() != null) {
                extraPerson.setOrganizationName(unit.getUnitName());
            }
            if (proposalPerson.getOfficePhone() != null) {
                extraPerson.setPhone(proposalPerson.getOfficePhone());
            }
            // degree type and year added for version 1.2 - case 4337
            if (proposalPerson.getDegree() != null) {
                extraPerson.setDegreeType(proposalPerson.getDegree());
            }
            if (proposalPerson.getYearGraduated() != null) {
                extraPerson.setDegreeYear(proposalPerson.getYearGraduated());
            }
            AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, proposalPerson.getPersonId(), proposalPerson.getRolodexId(), BIOSKETCH_TYPE);
            if (bioSketchAttachment != null) {
                extraPerson.setBioSketchAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.BioSketchAttached.YES);
            }

            AttachedFileDataType curPendingAttachment = getPernonnelAttachments(pdDoc, proposalPerson.getPersonId(), proposalPerson.getRolodexId(), CURRENT_PENDING_TYPE);
            if (curPendingAttachment != null) {
                extraPerson.setSupportsAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.SupportsAttached.YES);
            }

            AttachedFileDataType collaboratorAttachment = getPernonnelAttachments(pdDoc, proposalPerson.getPersonId(), proposalPerson.getRolodexId(), COLLABORATOR_TYPE);
            if (collaboratorAttachment != null) {
                extraPerson.setCollaboratorAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.CollaboratorAttached.YES);
            }

            extraPersonList.add(extraPerson);
        }
        return extraPersonList.toArray(new PersonProfileListDocument.PersonProfileList.ExtraKeyPerson[0]);
    }

    private PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address getExtraPersonAddress(
            ProposalPersonContract proposalPerson) {
        PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address address = PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address.Factory.newInstance();
        if (proposalPerson.getAddressLine1() != null) {
            if (proposalPerson.getAddressLine1().length() > ADDRESS_LINE_MAX_LENGTH) {
                address.setStreet1(proposalPerson.getAddressLine1().substring(0, ADDRESS_LINE_MAX_LENGTH));
            } else {
                address.setStreet1(proposalPerson.getAddressLine1());
            }
        }
        if (proposalPerson.getAddressLine2() != null) {
            if (proposalPerson.getAddressLine2().length() > ADDRESS_LINE_MAX_LENGTH) {
                address.setStreet2(proposalPerson.getAddressLine2().substring(0, ADDRESS_LINE_MAX_LENGTH));
            } else {
                address.setStreet2(proposalPerson.getAddressLine2());
            }
        }
        if (proposalPerson.getCity() != null) {
            address.setCity(proposalPerson.getCity());
        }
        if (proposalPerson.getCounty() != null) {
            address.setCounty(proposalPerson.getCounty());
        }

        if (proposalPerson.getPostalCode() != null) {
            address.setZipCode(proposalPerson.getPostalCode());
        }

        if (proposalPerson.getCountryCode() != null) {
            PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address.Country.Enum county = PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address.Country.Enum.forString(proposalPerson.getCountryCode());
            address.setCountry(county);
        }
        if (proposalPerson.getState() != null) {
            address.setState(proposalPerson.getState());
        }
        return address;
    }

    private PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Name getExtraPersonName(ProposalPersonContract proposalPerson) {
        PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Name name = PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Name.Factory.newInstance();
        if (proposalPerson.getFirstName() != null) {
            name.setFirstName(proposalPerson.getFirstName());
        }
        if (proposalPerson.getMiddleName() != null) {
            name.setMiddleName(proposalPerson.getMiddleName());
        }
        if (proposalPerson.getLastName() != null) {
            name.setLastName(proposalPerson.getLastName());
        }
        return name;
    }

    private void setDivisionName(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson extraPerson) {
        extraPerson.setDivisionName("");
    }

    private void setDepartmentName(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson extraPerson) {
        extraPerson.setDepartmentName("");
    }

    private NarrativeContract saveKeypersonProfileObject() {
        NarrativeContract narrative = null;
        if (!CollectionUtils.isEmpty(extraPersons)) {
            PersonProfileListDocument.PersonProfileList extraPersonProfileList = PersonProfileListDocument.PersonProfileList.Factory.newInstance();

            extraPersonProfileList.setProposalNumber(pdDoc.getDevelopmentProposal().getProposalNumber());
            extraPersonProfileList.setExtraKeyPersonArray(getExtraKeyPersons());

            PersonProfileListDocument extraPersonDoc = PersonProfileListDocument.Factory.newInstance();
            extraPersonDoc.setPersonProfileList(extraPersonProfileList);
            String xmlData = extraPersonDoc.xmlText();
            Map<String, byte[]> streamMap = new HashMap<>();
            streamMap.put("", xmlData.getBytes());

            Source xsltSource;
            try {
                xsltSource = new StreamSource(additionalkeypersonprofilesStyleSheet.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException("the stream could not be opened", e);
            }
            Map<String, Source> xSLTemplateWithBookmarks = new HashMap<>();
            xSLTemplateWithBookmarks.put("", xsltSource);


            GenericPrintable printable = new GenericPrintable();
            printable.setXSLTemplateWithBookmarks(xSLTemplateWithBookmarks);
            printable.setStreamMap(streamMap);
            try {
                KcFile printData = s2SPrintingService.print(printable);
                String fileName = pdDoc.getDevelopmentProposal().getProposalNumber() + "_" + PROFILE_COMMENT + ".pdf";
                narrative = saveNarrative(printData.getData(), "" + PROFILE_TYPE, fileName, COMMENT + PROFILE_COMMENT);
            } catch (S2SException e) {
                LOG.error("Auto generation of Profile attachment for extra Keypersons failed", e);
            }
        }
        return narrative;
    }


    private NSFKeyPersonExpandedDocument getNSFKeyPersonExpanded() {
        NSFKeyPersonExpandedDocument nsfKeyPersonExpandedDocument = NSFKeyPersonExpandedDocument.Factory.newInstance();
        NSFKeyPersonExpanded nsfKeyPersonExpanded = NSFKeyPersonExpanded.Factory.newInstance();
        setNSFKeyPersonExpandedAttributes(nsfKeyPersonExpanded);
        nsfKeyPersonExpandedDocument.setNSFKeyPersonExpanded(nsfKeyPersonExpanded);

        return nsfKeyPersonExpandedDocument;
    }

    private void setNSFKeyPersonExpandedAttributes(NSFKeyPersonExpanded nsfKeyPersonExpanded) {
        nsfKeyPersonExpanded.setFormVersion(FormVersion.v1_0.getVersion());
        nsfKeyPersonExpanded.setPDPI(getPersonProfilePI());
        PersonProfileDataType[] keyPersonArray = getpersonProfileKeyPerson();
        if (keyPersonArray.length > 0) {
            nsfKeyPersonExpanded.setKeyPersonArray(keyPersonArray);
        }
        saveKeyPersonAttachmentsToProposal();
        if (extraPersons.size() > 0) {
            for (ProposalPersonContract extraPerson : extraPersons) {
                setBioSketchAttchment(nsfKeyPersonExpanded, extraPerson);
                setCurrentPendingTypeAttachment(nsfKeyPersonExpanded, extraPerson);
            }
            for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                if (narrative.getNarrativeType().getCode() != null) {
                    if (Integer.parseInt(narrative.getNarrativeType().getCode()) == PROFILE_TYPE) {
                        setProfileTypeAttachment(nsfKeyPersonExpanded, narrative);
                    }
                }
            }
        }
    }


    private void setProfileTypeAttachment(NSFKeyPersonExpanded nsfKeyPersonExpanded, NarrativeContract narrative) {
        AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
        if (attachedFileDataType != null) {
            AdditionalProfilesAttached additionalProfilesAttached = AdditionalProfilesAttached.Factory.newInstance();
            additionalProfilesAttached.setAdditionalProfileAttached(attachedFileDataType);
            nsfKeyPersonExpanded.setAdditionalProfilesAttached(additionalProfilesAttached);
        }
    }

    private void setCurrentPendingTypeAttachment(NSFKeyPersonExpanded nsfKeyPersonExpanded, ProposalPersonContract extraPerson) {
        AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson.getRolodexId(), CURRENT_PENDING_TYPE);
        if (supportAttachment != null) {
            NSFKeyPersonExpanded.SupportsAttached supportsAttached = NSFKeyPersonExpanded.SupportsAttached.Factory.newInstance();
            supportsAttached.setSupportAttached(supportAttachment);
            nsfKeyPersonExpanded.setSupportsAttached(supportsAttached);
        }
    }

    private void setBioSketchAttchment(NSFKeyPersonExpanded nsfKeyPersonExpanded, ProposalPersonContract extraPerson) {
        NSFKeyPersonExpanded.BioSketchsAttached personBioSketch = NSFKeyPersonExpanded.BioSketchsAttached.Factory.newInstance();
        AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson.getRolodexId(), BIOSKETCH_TYPE);
        personBioSketch.setBioSketchAttached(bioSketchAttachment);
        nsfKeyPersonExpanded.setBioSketchsAttached(personBioSketch);
    }


    private PersonProfileDataType getPersonProfilePI() {
        PersonProfileDataType profileDataType = PersonProfileDataType.Factory.newInstance();
        Profile profile = Profile.Factory.newInstance();
        ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
        if (PI != null) {
            setPersonalProfileDetailsToProfile(profileDataType, profile, PI);
        }
        return profileDataType;
    }

    private void setPersonalProfileDetailsToProfile(PersonProfileDataType profileDataType, Profile profile, ProposalPersonContract PI) {
        assignRolodexId(PI);
        profile.setName(globLibV20Generator.getHumanNameDataType(PI));
        setDirectoryTitleToProfile(profile, PI);
        profile.setAddress(globLibV20Generator.getAddressDataType(PI));
        profile.setPhone(PI.getOfficePhone());
        if (StringUtils.isNotEmpty(PI.getFaxNumber())) {
            profile.setFax(PI.getFaxNumber());
        }
        setDegreeInfo(PI, profile);
        profile.setEmail(PI.getEmailAddress());
        DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
        setOrganizationName(profile, developmentProposal);
        setDepartmentNameToProfile(profile, PI);
        String divisionName = PI.getDivision();
        if (divisionName != null) {
            profile.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
        } else {
            String personId = PI.getPersonId();
            KcPersonContract kcPersonContact = kcPersonRepositoryService.findKcPersonByPersonId(personId);
            if (!kcPersonContact.getOrganizationIdentifier().isEmpty()) {
                divisionName = getPIDivision(kcPersonContact.getOrganizationIdentifier());
            }
            if (divisionName != null) {
                profile.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
            }
        }
        if (PI.getEraCommonsUserName() != null) {
            profile.setCredential(PI.getEraCommonsUserName());
        } else {
            if (getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                getAuditErrors().add(new AuditError(AuditError.NO_FIELD_ERROR_KEY, ERROR_ERA_COMMON_USER_NAME + PI.getFullName(), AuditError.GG_LINK));
            }
        }
        profile.setProjectRole(ProjectRoleDataType.PD_PI);
        setAttachments(profile, PI);
        profileDataType.setProfile(profile);
    }

    private String getPIDivision(String departmentId) {
        String divisionName = null;
        String unitName = getUnitName(departmentId);
        String heirarchyLevelDivisionName = null;
        int hierarchyLevel = Integer.parseInt(s2SConfigurationService.getValueAsString(ConfigurationConstants.HIERARCHY_LEVEL));
        int levelCount = 1;
        List<UnitContract> heirarchyUnits = unitRepositoryService.getUnitHierarchyForUnit(departmentId);
        for (UnitContract heirarchyUnit : heirarchyUnits) {
            if (levelCount < hierarchyLevel && heirarchyUnit.getUnitName().equalsIgnoreCase(unitName)) {
                divisionName = heirarchyUnit.getUnitName();
            } else if (levelCount == hierarchyLevel) {
                heirarchyLevelDivisionName = heirarchyUnit.getUnitName();
                if (heirarchyUnit.getUnitName().equalsIgnoreCase(unitName)) {
                    divisionName = heirarchyLevelDivisionName;
                }
            } else if (levelCount > hierarchyLevel && heirarchyUnit.getUnitName().equalsIgnoreCase(unitName)) {
                divisionName = heirarchyLevelDivisionName;
            }
            levelCount++;
        }
        return divisionName;
    }

    private String getUnitName(String departmentCode) {
        UnitContract unit = unitRepositoryService.findUnitByUnitNumber(departmentCode);
        return unit == null ? null : unit.getUnitName();
    }

    private void setDepartmentNameToProfile(Profile profile, ProposalPersonContract PI) {
        if (PI.getHomeUnit() != null && PI.getPerson() != null && PI.getPerson().getUnit() != null) {
            final String departmentName = PI.getPerson().getUnit().getUnitName();
            profile.setDepartmentName(StringUtils.substring(departmentName, 0, DEPARTMENT_NAME_MAX_LENGTH));
        } else {
            DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
            profile.setDepartmentName(StringUtils.substring(developmentProposal.getOwnedByUnit().getUnitName(), 0, DEPARTMENT_NAME_MAX_LENGTH));
        }
    }

    private void setDirectoryTitleToProfile(Profile profile, ProposalPersonContract PI) {
        if (PI.getDirectoryTitle() != null) {
            if (PI.getDirectoryTitle().length() > TITLE_MAX_LENGTH) {
                profile.setTitle(PI.getDirectoryTitle().substring(0, TITLE_MAX_LENGTH));
            } else {
                profile.setTitle(PI.getDirectoryTitle());
            }
        }
    }

    private void assignRolodexId(ProposalPersonContract PI) {
        if (PI.getPersonId() != null) {
            pIPersonOrRolodexId = PI.getPersonId();
            rolodex = null;
        } else if (PI.getRolodexId() != null) {
            pIPersonOrRolodexId = PI.getRolodexId().toString();
            rolodex = rolodexService.getRolodex(Integer.valueOf(pIPersonOrRolodexId));
        }
    }

    private void setAttachments(Profile profile, ProposalPersonContract PI) {
        setBioSketchAttachment(profile, PI);
        setCurrentPendingAttachment(profile, PI);
        setCollaboratorsAttachment(profile, PI);
    }

    private void setCurrentPendingAttachment(Profile profile, ProposalPersonContract PI) {
        AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(), CURRENT_PENDING_TYPE);
        if (supportAttachment != null) {
            Profile.SupportsAttached supportsAttached = Profile.SupportsAttached.Factory.newInstance();
            supportsAttached.setSupportAttached(supportAttachment);
            profile.setSupportsAttached(supportsAttached);
        }
    }

    private void setBioSketchAttachment(Profile profile, ProposalPersonContract PI) {
        Profile.BioSketchsAttached personBioSketch = Profile.BioSketchsAttached.Factory.newInstance();
        AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(), BIOSKETCH_TYPE);
        if (bioSketchAttachment != null) {
            personBioSketch.setBioSketchAttached(bioSketchAttachment);
            profile.setBioSketchsAttached(personBioSketch);
        }
    }

    private void setCollaboratorsAttachment(Profile profile, ProposalPersonContract PI) {
        Profile.CollaboratorsAttached personCollaborator = Profile.CollaboratorsAttached.Factory.newInstance();
        AttachedFileDataType collaboratorAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(), COLLABORATOR_TYPE);
        if (collaboratorAttachment != null) {
            personCollaborator.setCollaboratorAttached(collaboratorAttachment);
            profile.setCollaboratorsAttached(personCollaborator);
        }
    }

    private PersonProfileDataType[] getpersonProfileKeyPerson() {
        List<PersonProfileDataType> personProfileDataTypeList = new ArrayList<>();
        DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
        List<? extends ProposalPersonContract> keyPersons = developmentProposal.getProposalPersons();
        if (keyPersons != null) {
            Collections.sort(keyPersons, new ProposalPersonComparator());
        }
        List<ProposalPersonContract> nKeyPersons = s2SProposalPersonService.getNKeyPersons(keyPersons, MAX_KEY_PERSON_COUNT);

        extraPersons = keyPersons != null ? keyPersons.stream()
                .filter(kp -> !nKeyPersons.contains(kp))
                .collect(Collectors.toList()) : Collections.emptyList();

        if (nKeyPersons.size() > 0) {
            setKeyPersonToPersonProfileDataType(personProfileDataTypeList, nKeyPersons);
        }
        PersonProfileDataType[] personProfileDataArray = new PersonProfileDataType[0];
        personProfileDataArray = personProfileDataTypeList.toArray(personProfileDataArray);
        return personProfileDataArray;
    }

    private void setKeyPersonToPersonProfileDataType(
            List<PersonProfileDataType> personProfileDataTypeList,
            List<ProposalPersonContract> nKeyPersons) {
        for (ProposalPersonContract keyPerson : nKeyPersons) {
            if (pIPersonOrRolodexId != null) {
                // Don't add PI to keyperson list
                if (keyPerson.getPersonId() != null && keyPerson.getPersonId().equals(pIPersonOrRolodexId)) {
                    continue;
                } else if ((keyPerson.getRolodexId() != null) && pIPersonOrRolodexId.equals(keyPerson.getRolodexId().toString())) {
                    continue;
                }
            }
            Profile profileKeyPerson = Profile.Factory.newInstance();
            setAllkeyPersonDetailsToKeyPerson(keyPerson, profileKeyPerson);
            setAttachments(profileKeyPerson, keyPerson);
            PersonProfileDataType personProfileDataTypeKeyPerson = PersonProfileDataType.Factory.newInstance();
            personProfileDataTypeKeyPerson.setProfile(profileKeyPerson);
            personProfileDataTypeList.add(personProfileDataTypeKeyPerson);
        }
    }

    private void setAllkeyPersonDetailsToKeyPerson(ProposalPersonContract keyPerson,
                                                   Profile profileKeyPerson) {
        assignRolodexId(keyPerson);
        profileKeyPerson.setName(globLibV20Generator.getHumanNameDataType(keyPerson));
        setDirectoryTitleToProfile(profileKeyPerson, keyPerson);
        profileKeyPerson.setAddress(globLibV20Generator.getAddressDataType(keyPerson));
        profileKeyPerson.setPhone(keyPerson.getOfficePhone());
        if (StringUtils.isNotEmpty(keyPerson.getFaxNumber())) {
            profileKeyPerson.setFax(keyPerson.getFaxNumber());
        }
        setDegreeInfo(keyPerson, profileKeyPerson);
        profileKeyPerson.setEmail(keyPerson.getEmailAddress());
        DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
        setOrganizationName(profileKeyPerson, developmentProposal);
        setDepartmentNameToProfile(profileKeyPerson, keyPerson);
        String divisionName = keyPerson.getDivision();
        if (divisionName != null) {
            profileKeyPerson.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
        }
        if (keyPerson.getEraCommonsUserName() != null) {
            profileKeyPerson.setCredential(keyPerson.getEraCommonsUserName());
        } else {
            if (getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                if (keyPerson.isMultiplePi()) {
                    getAuditErrors().add(new AuditError(AuditError.NO_FIELD_ERROR_KEY, ERROR_ERA_COMMON_USER_NAME + keyPerson.getFullName(), AuditError.GG_LINK));
                }
            }
        }
        setProjectRole(keyPerson, profileKeyPerson);
    }

    private void setDegreeInfo(ProposalPersonContract keyPerson, Profile profileKeyPerson) {
        if (keyPerson.getDegree() != null) {
            profileKeyPerson.setDegreeType(keyPerson.getDegree());
        }
        if (keyPerson.getYearGraduated() != null) {
            profileKeyPerson.setDegreeYear(keyPerson.getYearGraduated());
        }
        if (keyPerson.getDegree() == null && keyPerson.getYearGraduated() == null) {
            if (keyPerson.getProposalPersonDegrees() != null && keyPerson.getProposalPersonDegrees().size() > 0) {
                ProposalPersonDegreeContract proposalPersonDegree = keyPerson.getProposalPersonDegrees().get(0);
                if (proposalPersonDegree != null) {
                    if (proposalPersonDegree.getDegreeType() != null && proposalPersonDegree.getDegreeType().getDescription() != null)
                        profileKeyPerson.setDegreeType(proposalPersonDegree.getDegreeType().getDescription());
                    if (proposalPersonDegree.getGraduationYear() != null)
                        profileKeyPerson.setDegreeYear(proposalPersonDegree.getGraduationYear());
                }
            }
        }
    }

    private void setProjectRole(ProposalPersonContract keyPerson, Profile profileKeyPerson) {
        if (keyPerson.isMultiplePi() || keyPerson.isCoInvestigator()) {
            if (getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                if (keyPerson.isMultiplePi()) {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
                } else {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_INVESTIGATOR);
                }
            } else {
                profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
            }
        } else {
            setProjectRoleCategoryToProfile(keyPerson, profileKeyPerson);
        }
    }

    private void setOrganizationName(Profile profileKeyPerson,
                                     DevelopmentProposalContract developmentProposal) {
        if (developmentProposal.getApplicantOrganization() != null && developmentProposal.getApplicantOrganization().getOrganization() != null) {
            if (rolodex != null) {
                profileKeyPerson.setOrganizationName(rolodex.getOrganization());
            } else
                profileKeyPerson.setOrganizationName(developmentProposal.getApplicantOrganization().getOrganization().getOrganizationName());
        }
    }

    private void setProjectRoleCategoryToProfile(ProposalPersonContract keyPerson,
                                                 Profile profileKeyPerson) {
        if (keyPerson.getRolodexId() != null && keyPerson.getProjectRole().equals(ProjectRoleDataType.PD_PI.toString())) {
            profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
        } else {
            profileKeyPerson.setProjectRole(ProjectRoleDataType.OTHER_SPECIFY);
            Profile.OtherProjectRoleCategory otherProjectRole = OtherProjectRoleCategory.Factory.newInstance();
            String otherRole;
            if (keyPerson.getProjectRole() != null) {
                if (keyPerson.getProjectRole().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
                    otherRole = keyPerson.getProjectRole().substring(0, ROLE_DESCRIPTION_MAX_LENGTH);
                } else {
                    otherRole = keyPerson.getProjectRole();
                }
            } else {
                otherRole = FieldValueConstants.VALUE_UNKNOWN;
            }
            otherProjectRole.setStringValue(otherRole);
            profileKeyPerson.setOtherProjectRoleCategory(otherProjectRole);
        }
    }

    @Override
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getNSFKeyPersonExpanded();
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public KcPersonRepositoryService getKcPersonRepositoryService() {
        return kcPersonRepositoryService;
    }

    public void setKcPersonRepositoryService(
            KcPersonRepositoryService kcPersonRepositoryService) {
        this.kcPersonRepositoryService = kcPersonRepositoryService;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(
            S2SConfigurationService s2sConfigurationService) {
        s2SConfigurationService = s2sConfigurationService;
    }

    public S2SPrintingService getS2SPrintingService() {
        return s2SPrintingService;
    }

    public void setS2SPrintingService(S2SPrintingService s2SPrintingService) {
        this.s2SPrintingService = s2SPrintingService;
    }

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }

    public Resource getAdditionalkeypersonprofilesStyleSheet() {
        return additionalkeypersonprofilesStyleSheet;
    }

    public void setAdditionalkeypersonprofilesStyleSheet(Resource additionalkeypersonprofilesStyleSheet) {
        this.additionalkeypersonprofilesStyleSheet = additionalkeypersonprofilesStyleSheet;
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
