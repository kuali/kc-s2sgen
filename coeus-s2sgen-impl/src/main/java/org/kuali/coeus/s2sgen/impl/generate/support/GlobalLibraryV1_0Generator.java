/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import gov.grants.apply.system.globalLibraryV10.AddressDataType;
import gov.grants.apply.system.globalLibraryV10.AddressRequireCountryDataType;
import gov.grants.apply.system.globalLibraryV10.ContactPersonDataType;
import gov.grants.apply.system.globalLibraryV10.HumanNameDataType;
import gov.grants.apply.system.universalCodesV10.CountryCodeType;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;
import org.kuali.coeus.s2sgen.impl.budget.KeyPersonDto;

@FormGenerator("GlobalLibraryV1_0Generator")
public class GlobalLibraryV1_0Generator {

    /**
     * Create AddressDataType from ProposalPerson
     * 
     * @param person ProposalPerson
     * @return AddressDataType corresponding to the ProposalPerson object.
     */
    public AddressDataType getAddressDataType(ProposalPersonContract person) {

        AddressDataType addressType = AddressDataType.Factory.newInstance();
        if (person != null) {

            String street1 = person.getAddressLine1();
            addressType.setStreet1(street1);
            String street2 = person.getAddressLine2();
            if (street2 != null && !street2.equals("")) {                
                addressType.setStreet2(street2);
            }
            String city = person.getCity();
            addressType.setCity(city);

            String state = person.getState();
            if (state != null && !state.equals("")) {
                addressType.setState(state);
            }
            String zipcode = person.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                addressType.setZipCode(zipcode);
            }
            String county = person.getCounty();
            if (county != null && !county.equals("")) {
                addressType.setCounty(county);
            }
            String country = person.getCountryCode();
            CountryCodeType.Enum countryCode = null;
            if (country != null && !country.equals("")) {
                countryCode = CountryCodeType.Enum.forString(country);
            }
            addressType.setCountry(countryCode);
        }
        return addressType;
    }

    /**
     * Create AddressDataType from rolodex entry
     * 
     * @param rolodex Rolodex entry
     * @return The AddressDataType corresponding to the rolodex entry.
     */

    public AddressDataType getAddressDataType(RolodexContract rolodex) {
        AddressDataType addressType = AddressDataType.Factory.newInstance();
        if (rolodex != null) {

            String street1 = rolodex.getAddressLine1();
            addressType.setStreet1(street1);
            String street2 = rolodex.getAddressLine2();
            if (street2 != null && !street2.equals("")) {
                addressType.setStreet2(street2);
            }
            addressType.setCity(rolodex.getCity());
            String state = rolodex.getState();
            if (state != null && !state.equals("")) {
                addressType.setState(state);
            }
            String zipcode = rolodex.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                addressType.setZipCode(zipcode);
            }
            String county = rolodex.getCounty();
            if (county != null && !county.equals("")) {
                addressType.setCounty(county);
            }
            String country = rolodex.getCountryCode();
            if (country != null && !country.equals("")) {
                CountryCodeType.Enum countryCode = CountryCodeType.Enum.forString(country);
                addressType.setCountry(countryCode);
            }
        }
        return addressType;
    }

    /**
     * Create AddressRequireCountryDataType from rolodex object
     * 
     * @param rolodex Rolodex object
     * @return AddressRequireCountryDataType corresponding to the rolodex object.
     */
    public AddressRequireCountryDataType getAddressRequireCountryDataType(RolodexContract rolodex) {

        AddressRequireCountryDataType address = AddressRequireCountryDataType.Factory.newInstance();
        if (rolodex != null) {

            String street1 = rolodex.getAddressLine1();
            address.setStreet1(street1);
            String street2 = rolodex.getAddressLine2();
            if (street2 != null && !street2.equals("")) {
                address.setStreet2(street2);
            }
            String city = rolodex.getCity();
            address.setCity(city);
            String county = rolodex.getCounty();
            if (county != null && !county.equals("")) {
                address.setCounty(county);
            }
            String state = rolodex.getState();
            if (state != null && !state.equals("")) {
                address.setState(state);
            }
            String zipcode = rolodex.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                address.setZipCode(zipcode);
            }
            String country = rolodex.getCountryCode();
            address.setCountry(CountryCodeType.Enum.forString(country));

        }
        return address;
    }

    /**
     * Create AddressRequireCountryDataType from ProposalPerson object
     * 
     * @param person ProposalPerson Object
     * @return AddressRequireCountryDataType corresponding to the ProposalPerson object
     */
    public AddressRequireCountryDataType getAddressRequireCountryDataType(ProposalPersonContract person) {

        AddressRequireCountryDataType address = AddressRequireCountryDataType.Factory.newInstance();
        if (person != null) {

            String street1 = person.getAddressLine1();
            address.setStreet1(street1);
            String street2 = person.getAddressLine2();
            if (street2 != null && !street2.equals("")) {
                address.setStreet2(street2);
            }
            String city = person.getCity();
            address.setCity(city);
            String county = person.getCounty();
            if (county != null && !county.equals("")) {
                address.setCounty(county);
            }
            String state = person.getState();
            if (state != null && !state.equals("")) {
                address.setState(state);
            }
            String zipcode = person.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                address.setZipCode(zipcode);
            }
            String country = person.getCountryCode();
            address.setCountry(CountryCodeType.Enum.forString(country));

        }
        return address;
    }

    /**
     * Create AddressRequireCountryDataType from DepartmentalPerson object
     * 
     * @param person DepartmentalPerson
     * @return AddressRequireCountryDataType corresponding to the DepartmentalPerson object.
     */
    public AddressRequireCountryDataType getAddressRequireCountryDataType(DepartmentalPersonDto person) {

        AddressRequireCountryDataType address = AddressRequireCountryDataType.Factory.newInstance();
        if (person != null) {

            String street1 = person.getAddress1();
            address.setStreet1(street1);
            String street2 = person.getAddress2();
            if (street2 != null && !street2.equals("")) {
                address.setStreet2(street2);
            }
            String city = person.getCity();
            address.setCity(city);
            String county = person.getCounty();
            if (county != null && !county.equals("")) {
                address.setCounty(county);
            }
            String state = person.getState();
            if (state != null && !state.equals("")) {
                address.setState(state);
            }
            String zipcode = person.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                address.setZipCode(zipcode);
            }
            String country = person.getCountryCode();
            address.setCountry(CountryCodeType.Enum.forString(country));

        }
        return address;
    }

    /**
     * Create HumanNameDataType from ProposalPerson object
     * 
     * @param person ProposalPerson
     * @return HumanNameDataType corresponding to the ProposalPerson object.
     */
    public HumanNameDataType getHumanNameDataType(ProposalPersonContract person) {

        HumanNameDataType humanName = HumanNameDataType.Factory.newInstance();
        if (person != null) {
            humanName.setFirstName(person.getFirstName());
            humanName.setLastName(person.getLastName());
            String middleName = person.getMiddleName();
            if (middleName != null && !middleName.equals("")) {
                humanName.setMiddleName(middleName);
            }

        }
        return humanName;
    }

    /**
     * Create HumanNameDataType from DepartmentalPerson object
     * 
     * @param person DepartmentalPerson
     * @return HumanNameDataType corresponding to the DepartmentalPerson object
     */

    public HumanNameDataType getHumanNameDataType(DepartmentalPersonDto person) {

        HumanNameDataType humanName = HumanNameDataType.Factory.newInstance();
        if (person != null) {
            humanName.setFirstName(person.getFirstName());
            humanName.setLastName(person.getLastName());
            String middleName = person.getMiddleName();
            if (middleName != null && !middleName.equals("")) {
                humanName.setMiddleName(middleName);
            }

        }
        return humanName;
    }

    /**
     * Create a HumanNameDataType from Rolodex object
     * 
     * @param rolodex Rolodex object
     * @return HumanNameDataType corresponding to the rolodex object.
     */
    public HumanNameDataType getHumanNameDataType(RolodexContract rolodex) {

        HumanNameDataType humanName = HumanNameDataType.Factory.newInstance();
        if (rolodex != null) {
            humanName.setFirstName(rolodex.getFirstName());
            humanName.setLastName(rolodex.getLastName());
            String middleName = rolodex.getMiddleName();
            if (middleName != null && !middleName.equals("")) {
                humanName.setMiddleName(middleName);
            }

        }
        return humanName;
    }

    /**
     * Create HumanNameDataType from KeyPersonInfo object
     * 
     * @param keyPerson KeyPersonInfo
     * @return HumanNameDataType corresponding to the KeyPersonInfo object
     */
    public HumanNameDataType getHumanNameDataType(KeyPersonDto keyPerson) {

        HumanNameDataType humanName = HumanNameDataType.Factory.newInstance();
        humanName.setFirstName(keyPerson.getFirstName());
        humanName.setLastName(keyPerson.getLastName());
        String middleName = keyPerson.getMiddleName();
        if (middleName != null && !middleName.equals("")) {
            humanName.setMiddleName(middleName);
        }
        return humanName;
    }

    /**
     * Create ContactPersonDataType from ProposalPerson object
     * 
     * @param person Proposalperson
     * @return ContactPersonDataType created from ProposalPerson object
     */
    public ContactPersonDataType getContactPersonDataType(ProposalPersonContract person) {
        ContactPersonDataType contactPerson = ContactPersonDataType.Factory.newInstance();
        if (person != null) {
            contactPerson.setName(getHumanNameDataType(person));
            contactPerson.setPhone(person.getOfficePhone());
            String email = person.getEmailAddress();
            if (email != null && !email.equals("")) {
                contactPerson.setEmail(email);
            }
            String title = person.getDirectoryTitle();
            if (title != null && !title.equals("")) {
                contactPerson.setTitle(title);
            }
            String fax = person.getFaxNumber();
            if (StringUtils.isNotEmpty(fax)) {
                contactPerson.setFax(fax);
            }
            contactPerson.setAddress(getAddressDataType(person));
        }
        return contactPerson;
    }

}
