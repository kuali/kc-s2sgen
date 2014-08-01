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

import gov.grants.apply.forms.nsfSuggestedReviewersV10.NSFSuggestedReviewersDocument;
import gov.grants.apply.forms.nsfSuggestedReviewersV10.NSFSuggestedReviewersDocument.NSFSuggestedReviewers;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


/**
 * 
 * This class is used to generate XML Document object for grants.gov NSFSuggestedReviewersV1.0. This form is generated using XMLBean
 * API's generated by compiling NSFSuggestedReviewersV1.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("NSFSuggestedReviewersV1_0Generator")
public class NSFSuggestedReviewersV1_0Generator extends NSFSuggestedReviewersBaseGenerator {
    @Value("http://apply.grants.gov/forms/NSF_SuggestedReviewers-V1.0")
    private String namespace;

    @Value("NSF_SuggestedReviewers-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/NSF_SuggestedReviewers-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.nsfSuggestedReviewersV10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    /**
     * 
     * This method returns NSFSuggestedReviewersDocument object based on proposal development document which contains the
     * NSFSuggestedReviewersDocument informations SuggestedReviewers and inclusion of Reviewer for a particular proposal
     * 
     * @return nsfReviewersDocument {@link XmlObject} of type NSFSuggestedReviewersDocument.
     */
    private NSFSuggestedReviewersDocument getNSFSuggestedReviewers() {

        NSFSuggestedReviewersDocument nsfReviewersDocument = NSFSuggestedReviewersDocument.Factory.newInstance();
        NSFSuggestedReviewers nsfSuggestedReviewers = NSFSuggestedReviewers.Factory.newInstance();
        nsfSuggestedReviewers.setFormVersion(FormVersion.v1_0.getVersion());
        String suggestedRev = getAbstractText(SUGGESTED_REVIEWERS);
        if (suggestedRev != null && !suggestedRev.equals("")) {
            nsfSuggestedReviewers.setSuggestedReviewers(suggestedRev);
        }
        String doNotInclude = getAbstractText(REVIEWERS_NOT_TO_INCLUDE);
        if (doNotInclude != null && !doNotInclude.equals("")) {
            nsfSuggestedReviewers.setReviewersNotToInclude(doNotInclude);
        }
        nsfReviewersDocument.setNSFSuggestedReviewers(nsfSuggestedReviewers);
        return nsfReviewersDocument;
    }

    /**
     * This method creates {@link XmlObject} of type {@link NSFSuggestedReviewersDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getNSFSuggestedReviewers();
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
