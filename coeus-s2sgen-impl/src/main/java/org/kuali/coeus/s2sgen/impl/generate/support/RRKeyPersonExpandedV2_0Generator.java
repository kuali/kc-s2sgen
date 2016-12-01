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

import gov.grants.apply.forms.rrKeyPersonExpanded20V20.PersonProfileDataType;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.PersonProfileDataType.Profile;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.PersonProfileDataType.Profile.OtherProjectRoleCategory;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.ProjectRoleDataType;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document.RRKeyPersonExpanded20;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document.RRKeyPersonExpanded20.AdditionalProfilesAttached;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document.RRKeyPersonExpanded20.BioSketchsAttached;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document.RRKeyPersonExpanded20.SupportsAttached;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.person.KcPersonRepositoryService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonDegreeContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
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

@FormGenerator("RRKeyPersonExpandedV2_0Generator")
public class RRKeyPersonExpandedV2_0Generator extends
		RRKeyPersonExpandedBaseGenerator {

    private static final int MAX_KEY_PERSON_COUNT = 100;

	RolodexContract rolodex;

    @Value("http://apply.grants.gov/forms/RR_KeyPersonExpanded_2_0-V2.0")
    private String namespace;

    @Value("RR_KeyPersonExpanded_2_0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_KeyPersonExpanded-V2.0.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrKeyPersonExpanded20V20")
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

	private RRKeyPersonExpanded20Document getRRKeyPersonExpanded() {
		RRKeyPersonExpanded20Document rrKeyPersonExpandedDocument = RRKeyPersonExpanded20Document.Factory
				.newInstance();
		RRKeyPersonExpanded20 rrKeyPersonExpanded = RRKeyPersonExpanded20.Factory
				.newInstance();
		setRRKeyPersonExpandedAttributes(rrKeyPersonExpanded);
		rrKeyPersonExpandedDocument
				.setRRKeyPersonExpanded20(rrKeyPersonExpanded);
		return rrKeyPersonExpandedDocument;
	}

	private void setRRKeyPersonExpandedAttributes(
			RRKeyPersonExpanded20 rrKeyPersonExpanded) {
		rrKeyPersonExpanded.setFormVersion(FormVersion.v2_0.getVersion());
		rrKeyPersonExpanded.setPDPI(getPersonProfilePI());
		PersonProfileDataType[] keyPersonArray = getpersonProfileKeyPerson();
		if (keyPersonArray.length > 0) {
			rrKeyPersonExpanded.setKeyPersonArray(keyPersonArray);
		}
		saveKeyPersonAttachmentsToProposal();
		if (extraPersons.size() > 0) {
			for (ProposalPersonContract extraPerson : extraPersons) {
				setBioSketchAttchment(rrKeyPersonExpanded, extraPerson);
				setCurrentPendingTypeAttachment(rrKeyPersonExpanded,
						extraPerson);
			}
			for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
					.getNarratives()) {
				if (narrative.getNarrativeType().getCode() != null) {
					if (Integer.parseInt(narrative.getNarrativeType().getCode()) == PROFILE_TYPE) {
						setProfileTypeAttachment(rrKeyPersonExpanded, narrative);
					}
				}
			}
		}
	}

	private void setProfileTypeAttachment(
			RRKeyPersonExpanded20 rrKeyPersonExpanded, NarrativeContract narrative) {
		AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
		if(attachedFileDataType != null){
			AdditionalProfilesAttached additionalProfilesAttached = AdditionalProfilesAttached.Factory
					.newInstance();
			additionalProfilesAttached
					.setAdditionalProfileAttached(attachedFileDataType);
			rrKeyPersonExpanded
				.setAdditionalProfilesAttached(additionalProfilesAttached);
		}
	}

	private void setCurrentPendingTypeAttachment(
			RRKeyPersonExpanded20 rrKeyPersonExpanded,
			ProposalPersonContract extraPerson) {
		AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc,
				extraPerson.getPersonId(), extraPerson.getRolodexId(),
				CURRENT_PENDING_TYPE);
		if (supportAttachment != null) {
			SupportsAttached supportsAttached = SupportsAttached.Factory
					.newInstance();
			supportsAttached.setSupportAttached(supportAttachment);
			rrKeyPersonExpanded.setSupportsAttached(supportsAttached);
		}
	}

	private void setBioSketchAttchment(
			RRKeyPersonExpanded20 rrKeyPersonExpanded,
			ProposalPersonContract extraPerson) {
		BioSketchsAttached 
		    personBioSketch =  BioSketchsAttached.Factory.newInstance();
		AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(
				pdDoc, extraPerson.getPersonId(), extraPerson.getRolodexId(),
				BIOSKETCH_TYPE);
		personBioSketch.setBioSketchAttached(bioSketchAttachment);
		rrKeyPersonExpanded.setBioSketchsAttached(personBioSketch);
	}

	private PersonProfileDataType getPersonProfilePI() {
		PersonProfileDataType profileDataType = PersonProfileDataType.Factory
				.newInstance();
		Profile profile = Profile.Factory.newInstance();
		ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
		if (PI != null) {
			setPersonalProfileDetailsToProfile(profileDataType, profile, PI);
		}
		return profileDataType;
	}

	private void setPersonalProfileDetailsToProfile(
			PersonProfileDataType profileDataType, Profile profile,
			ProposalPersonContract PI) {
		assignRolodexId(PI);
		profile.setName(globLibV20Generator.getHumanNameDataType(PI));
		setDirectoryTitleToProfile(profile, PI);
		profile.setAddress(globLibV20Generator.getAddressDataType(PI));
		profile.setPhone(PI.getOfficePhone());
		if (StringUtils.isNotEmpty(PI.getFaxNumber())) {
			profile.setFax(PI.getFaxNumber());
		}
		if (PI.getDegree() != null) {
			profile.setDegreeType(PI.getDegree());
		}
		if (PI.getYearGraduated() != null) {
			profile.setDegreeYear(PI.getYearGraduated());
		}		
		if(PI.getDegree() == null && PI.getYearGraduated() == null ){		    
		   if(PI.getProposalPersonDegrees() != null && PI.getProposalPersonDegrees().size() > 0){
		       ProposalPersonDegreeContract proposalPersonDegree = PI.getProposalPersonDegrees().get(0);
		       if(proposalPersonDegree != null){  
		           if(proposalPersonDegree.getDegreeType() != null && proposalPersonDegree.getDegreeType().getDescription()!= null)
		               profile.setDegreeType(proposalPersonDegree.getDegreeType().getDescription());
		           if(proposalPersonDegree.getGraduationYear() != null)
		               profile.setDegreeYear(proposalPersonDegree.getGraduationYear());
		       }		   
		   }		
		}
		profile.setEmail(PI.getEmailAddress());
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		setOrganizationName(profile, developmentProposal);
		setDepartmentNameToProfile(profile,PI);
		String divisionName = PI.getDivision();
		if (divisionName != null) {
			profile.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
        } else {
            String personId = PI.getPersonId();
            KcPersonContract kcPersonContact = kcPersonRepositoryService.findKcPersonByPersonId(personId);
            if(!kcPersonContact.getOrganizationIdentifier().isEmpty()) {
            divisionName=getPIDivision(kcPersonContact.getOrganizationIdentifier());
            }
            if (divisionName != null) {
              profile.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
           }
        }
		if (PI.getEraCommonsUserName() != null) {
			profile.setCredential(PI.getEraCommonsUserName());
		} else {
            if (getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                getAuditErrors().add(new AuditError(AuditError.NO_FIELD_ERROR_KEY, ERROR_ERA_COMMON_USER_NAME + PI.getFullName(),
                        AuditError.GG_LINK));
            }
        }
		profile.setProjectRole(ProjectRoleDataType.PD_PI);
		setAttachments(profile, PI);
		profileDataType.setProfile(profile);
	}
	
	private String getPIDivision(String departmentId) {
        String divisionName = null;
        String unitName=getUnitName(departmentId);
        String heirarchyLevelDivisionName= null;       
        int hierarchyLevel = Integer.parseInt(s2SConfigurationService.getValueAsString(ConfigurationConstants.HIERARCHY_LEVEL));
        int levelCount =1;
        List<UnitContract> heirarchyUnits = getUnitRepositoryService().getUnitHierarchyForUnit(departmentId);
        for(UnitContract heirarchyUnit:heirarchyUnits) {
            if(levelCount < hierarchyLevel && heirarchyUnit.getUnitName().equalsIgnoreCase(unitName)) {
                 divisionName=heirarchyUnit.getUnitName();
            }
            else if(levelCount == hierarchyLevel) {
                heirarchyLevelDivisionName=heirarchyUnit.getUnitName();
                if(heirarchyUnit.getUnitName().equalsIgnoreCase(unitName)) {
                    divisionName=heirarchyLevelDivisionName;  
                }
            }
            else if(levelCount > hierarchyLevel && heirarchyUnit.getUnitName().equalsIgnoreCase(unitName)) {
                divisionName=heirarchyLevelDivisionName;
            }
            levelCount++;
        }
        return divisionName;
    }
	
	private String getUnitName(String departmentCode) {
		 UnitContract unit = getUnitRepositoryService().findUnitByUnitNumber(departmentCode);
	        return unit==null?null:unit.getUnitName();
	}

	private void setDepartmentNameToProfile(Profile profile, ProposalPersonContract PI) {
		if(PI.getHomeUnit() != null && PI.getPerson() != null && PI.getPerson().getUnit() != null) {
            final String departmentName =  PI.getPerson().getUnit().getUnitName();
            profile.setDepartmentName(StringUtils.substring(departmentName, 0, DEPARTMENT_NAME_MAX_LENGTH));
        }
        else
        {
            DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
            profile.setDepartmentName(StringUtils.substring(developmentProposal.getOwnedByUnit().getUnitName(), 0, DEPARTMENT_NAME_MAX_LENGTH));
        }
	}

	private void setDirectoryTitleToProfile(Profile profile, ProposalPersonContract PI) {
		if (PI.getDirectoryTitle() != null) {
			if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
				profile.setTitle(PI.getDirectoryTitle().substring(0,
						DIRECTORY_TITLE_MAX_LENGTH));
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
	}

	private void setCurrentPendingAttachment(Profile profile, ProposalPersonContract PI) {
		AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc,
				PI.getPersonId(), PI.getRolodexId(), CURRENT_PENDING_TYPE);
		if (supportAttachment != null) {
			PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory
					.newInstance();
			supportsAttached.setSupportAttached(supportAttachment);
			profile.setSupportsAttached(supportsAttached);
		}
	}

	private void setBioSketchAttachment(Profile profile, ProposalPersonContract PI) {
		PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
				.newInstance();
		AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(
				pdDoc, PI.getPersonId(), PI.getRolodexId(), BIOSKETCH_TYPE);
		if (bioSketchAttachment != null) {
		    personBioSketch.setBioSketchAttached(bioSketchAttachment);
	        profile.setBioSketchsAttached(personBioSketch);
		}
	}

	private PersonProfileDataType[] getpersonProfileKeyPerson() {
		List<PersonProfileDataType> personProfileDataTypeList = new ArrayList<>();
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		List<? extends ProposalPersonContract> keyPersons = developmentProposal
				.getProposalPersons();
		if (keyPersons != null) {
			Collections.sort(keyPersons, new ProposalPersonComparator());
		}
		List<ProposalPersonContract> nKeyPersons = s2SProposalPersonService.getNKeyPersons(
				keyPersons, MAX_KEY_PERSON_COUNT);
		extraPersons = keyPersons != null ? keyPersons.stream()
				.filter(kp -> !nKeyPersons.contains(kp))
				.collect(Collectors.toList()) : Collections.emptyList();
		if (nKeyPersons.size() > 0) {
			setKeyPersonToPersonProfileDataType(personProfileDataTypeList,
					nKeyPersons);
		}
		PersonProfileDataType[] personProfileDataArray = new PersonProfileDataType[0];
		personProfileDataArray = personProfileDataTypeList
				.toArray(personProfileDataArray);
		return personProfileDataArray;
	}

	private void setKeyPersonToPersonProfileDataType(
			List<PersonProfileDataType> personProfileDataTypeList,
			List<ProposalPersonContract> nKeyPersons) {
		for (ProposalPersonContract keyPerson : nKeyPersons) {
			if (pIPersonOrRolodexId != null) {
				// Don't add PI to keyperson list
				if (keyPerson.getPersonId() != null
						&& keyPerson.getPersonId().equals(pIPersonOrRolodexId)) {
					continue;
				} else if ((keyPerson.getRolodexId() != null)
						&& pIPersonOrRolodexId.equals(keyPerson.getRolodexId()
								.toString())) {
					continue;
				}
			}
			Profile profileKeyPerson = Profile.Factory.newInstance();
			setAllkeyPersonDetailsToKeyPerson(keyPerson, profileKeyPerson);
			setAttachments(profileKeyPerson, keyPerson);
			PersonProfileDataType personProfileDataTypeKeyPerson = PersonProfileDataType.Factory
					.newInstance();
			personProfileDataTypeKeyPerson.setProfile(profileKeyPerson);
			personProfileDataTypeList.add(personProfileDataTypeKeyPerson);
		}
	}

	/*
	 * This method is used to add all key person details to key person
	 */
	private void setAllkeyPersonDetailsToKeyPerson(ProposalPersonContract keyPerson,
            Profile profileKeyPerson) {
        assignRolodexId(keyPerson);
		profileKeyPerson.setName(globLibV20Generator
				.getHumanNameDataType(keyPerson));
		setDirectoryTitleToProfile(profileKeyPerson, keyPerson);
		profileKeyPerson.setAddress(globLibV20Generator
				.getAddressDataType(keyPerson));
		profileKeyPerson.setPhone(keyPerson.getOfficePhone());
		if (StringUtils.isNotEmpty(keyPerson.getFaxNumber())) {
			profileKeyPerson.setFax(keyPerson.getFaxNumber());
		}
		if (keyPerson.getDegree() != null) {
			profileKeyPerson.setDegreeType(keyPerson.getDegree());
		}
		if (keyPerson.getYearGraduated() != null) {
			profileKeyPerson.setDegreeYear(keyPerson.getYearGraduated());
		}
		if(keyPerson.getDegree() == null && keyPerson.getYearGraduated() == null ){
	          if(keyPerson.getProposalPersonDegrees() != null && keyPerson.getProposalPersonDegrees().size() > 0){
	              ProposalPersonDegreeContract proposalPersonDegree = keyPerson.getProposalPersonDegrees().get(0);
	              if(proposalPersonDegree != null){  
	                  if(proposalPersonDegree.getDegreeType() != null &&proposalPersonDegree.getDegreeType().getDescription() != null)
	                      profileKeyPerson.setDegreeType(proposalPersonDegree.getDegreeType().getDescription());
	                  if(proposalPersonDegree.getGraduationYear() != null)
	                      profileKeyPerson.setDegreeYear(proposalPersonDegree.getGraduationYear());
	              }	            
	          }  
	     }
		profileKeyPerson.setEmail(keyPerson.getEmailAddress());
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		setOrganizationName(profileKeyPerson, developmentProposal);
		setDepartmentNameToProfile(profileKeyPerson,keyPerson);
		String divisionName = keyPerson.getDivision();
		if (divisionName != null) {
			profileKeyPerson.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
		}
		if (keyPerson.getEraCommonsUserName() != null) {
			profileKeyPerson.setCredential(keyPerson.getEraCommonsUserName());
		} else {
            if (getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                if (keyPerson.isMultiplePi()) {
                    getAuditErrors().add(new AuditError(AuditError.NO_FIELD_ERROR_KEY, ERROR_ERA_COMMON_USER_NAME + keyPerson.getFullName(),
                            AuditError.GG_LINK));
                }
            }
        }
        if (keyPerson.isMultiplePi() || keyPerson.isCoInvestigator()) {
            if(getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())){
                if (keyPerson.isMultiplePi()) {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
                } else {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_INVESTIGATOR);
                }
            }else{
                profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
            }
        } else {
			setProjectRoleCategoryToProfile(keyPerson, profileKeyPerson);
		}
	}

	private void setOrganizationName(Profile profileKeyPerson,
			DevelopmentProposalContract developmentProposal) {
		if (developmentProposal.getApplicantOrganization() != null
				&& developmentProposal.getApplicantOrganization()
						.getOrganization() != null) {
		    if (rolodex != null){
                profileKeyPerson.setOrganizationName(rolodex.getOrganization());
            }
            else
                profileKeyPerson.setOrganizationName(developmentProposal
                    .getApplicantOrganization().getOrganization()
                    .getOrganizationName());
		}
	}

	private void setProjectRoleCategoryToProfile(ProposalPersonContract keyPerson,
			Profile profileKeyPerson) {
		if (keyPerson.getRolodexId() != null 
				&& keyPerson.getProjectRole().equals(ProjectRoleDataType.PD_PI.toString())) {
			profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
		} else {
	        profileKeyPerson.setProjectRole(ProjectRoleDataType.OTHER_SPECIFY);
	        OtherProjectRoleCategory otherProjectRole = OtherProjectRoleCategory.Factory
	                .newInstance();
	        String otherRole;
	        if (keyPerson.getProjectRole() != null) {
	            if (keyPerson.getProjectRole().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
	                otherRole = keyPerson.getProjectRole().substring(0,
	                        ROLE_DESCRIPTION_MAX_LENGTH);
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
	public RRKeyPersonExpanded20Document getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getRRKeyPersonExpanded();
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
