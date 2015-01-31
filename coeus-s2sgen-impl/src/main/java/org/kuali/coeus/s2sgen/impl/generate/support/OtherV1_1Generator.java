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

import gov.grants.apply.forms.otherV11.OtherNarrativeAttachmentsDocument;
import gov.grants.apply.forms.otherV11.OtherNarrativeAttachmentsDocument.OtherNarrativeAttachments;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin1Max100DataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating the XML object for grants.gov OtherV1_1. Form is generated using XMLBean classes and is based on OtherV1_1
 * schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("OtherV1_1Generator")
public class OtherV1_1Generator extends OtherBaseGenerator {

    @Value("http://apply.grants.gov/forms/Other-V1.1")
    private String namespace;

    @Value("Other-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/Other-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.otherV11")
    private String packageName;

    @Value("145")
    private int sortIndex;

    /**
     * 
     * This method is used to get Other Narrative Attachments
     * 
     * @return otherNarrativeAttachmentsDocument {@link XmlObject} of type OtherNarrativeAttachmentsDocument.
     */
    private OtherNarrativeAttachmentsDocument getOtherNarrativeAttachments() {

        OtherNarrativeAttachmentsDocument otherNarrativeAttachmentsDocument = OtherNarrativeAttachmentsDocument.Factory
                .newInstance();
        OtherNarrativeAttachments otherNarrativeAttachments = OtherNarrativeAttachments.Factory.newInstance();
        otherNarrativeAttachments.setFormVersion(FormVersion.v1_1.getVersion());
        AttachmentGroupMin1Max100DataType attachmentGroupMin1Max100DataType = AttachmentGroupMin1Max100DataType.Factory
                .newInstance();
        attachmentGroupMin1Max100DataType.setAttachedFileArray(getAttachedFileDataTypes());
        otherNarrativeAttachments.setAttachments(attachmentGroupMin1Max100DataType);
        otherNarrativeAttachmentsDocument.setOtherNarrativeAttachments(otherNarrativeAttachments);
        return otherNarrativeAttachmentsDocument;
    }

    /**
     * 
     * This method is used to get List of Other attachments from NarrativeAttachment
     * 
     * @return AttachedFileDataType[] based on the narrative type code.
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {

        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && Integer.parseInt(narrative.getNarrativeType().getCode()) == OTHER_ATTACHMENTS_FORM) {
            	attachedFileDataType = getAttachedFileType(narrative);
            	if(attachedFileDataType != null){
            		attachedFileDataTypeList.add(attachedFileDataType);
            	}
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
    }

    /**
     * This method creates {@link XmlObject} of type {@link OtherNarrativeAttachmentsDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getOtherNarrativeAttachments();
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
