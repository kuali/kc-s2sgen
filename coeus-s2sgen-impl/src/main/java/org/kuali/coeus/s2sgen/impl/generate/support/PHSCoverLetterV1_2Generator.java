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

import gov.grants.apply.forms.phsCoverLetter12V12.PHSCoverLetter12Document;
import gov.grants.apply.forms.phsCoverLetter12V12.PHSCoverLetter12Document.PHSCoverLetter12;
import gov.grants.apply.forms.phsCoverLetter12V12.PHSCoverLetter12Document.PHSCoverLetter12.CoverLetterFile;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


@FormGenerator("PHSCoverLetterV1_2Generator")
public class PHSCoverLetterV1_2Generator extends PHSCoverLetterBaseGenerator {

    @Value("http://apply.grants.gov/forms/PHS_CoverLetter_1_2-V1.2")
    private String namespace;

    @Value("PHS_CoverLetter_1_2-V1.2")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PHS_CoverLetter-V1.2.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phsCoverLetter12V12")
    private String packageName;

    @Value("110")
    private int sortIndex;

	/**
	 * This method is used to get PHSCoverLetter12Document attachment from the
	 * narrative attachments.
	 * 
	 * @return phsCoverLetter12Document {@link XmlObject} of type
	 *         PHS398CoverLetterDocument.
	 */
	private PHSCoverLetter12Document getPHSCoverLetter() {

		PHSCoverLetter12Document phsCoverLetterDocument = PHSCoverLetter12Document.Factory
				.newInstance();
		PHSCoverLetter12 phsCoverLetter = PHSCoverLetter12.Factory
				.newInstance();
        CoverLetterFile coverLetterFile = CoverLetterFile.Factory.newInstance();
		phsCoverLetter.setFormVersion(FormVersion.v1_2.getVersion());
		AttachedFileDataType attachedFileDataType = null;
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& Integer.parseInt(narrative.getNarrativeType().getCode()) == NARRATIVE_PHS_COVER_LETTER) {
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					coverLetterFile.setCoverLetterFilename(attachedFileDataType);
					break;
				}
			}
		}
        phsCoverLetter.setCoverLetterFile(coverLetterFile);
		phsCoverLetterDocument.setPHSCoverLetter12(phsCoverLetter);
		return phsCoverLetterDocument;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHSCoverLetter12Document} by populating data from the given
	 * {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	@Override
    public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getPHSCoverLetter();
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
