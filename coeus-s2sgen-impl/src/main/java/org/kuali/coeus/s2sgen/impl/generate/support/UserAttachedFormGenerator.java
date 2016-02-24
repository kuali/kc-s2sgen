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

import gov.grants.apply.forms.budgetV11.BudgetNarrativeAttachmentsDocument;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.s2s.*;

import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.kuali.coeus.s2sgen.impl.generate.DynamicNamespace;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.api.generate.AttachmentData;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.S2SFormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@FormGenerator("UserAttachedFormGenerator")
public class UserAttachedFormGenerator implements S2SFormGenerator, DynamicNamespace {

    protected ProposalDevelopmentDocumentContract pdDoc = null;
    private List<AuditError> auditErrors = new ArrayList<>();
    private List<AttachmentData> attachments = new ArrayList<>();

    @Autowired
    @Qualifier("userAttachedFormService")
    private UserAttachedFormService userAttachedFormService;

    private String namespace;

    /**
     * This method creates {@link XmlObject} of type {@link BudgetNarrativeAttachmentsDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    @Override
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        S2sUserAttachedFormFileContract userAttachedFormFile = findUserAttachedFormFile();
        if(userAttachedFormFile==null){
            throw new RuntimeException("Cannot find XML Data");
        }
        String formXml = userAttachedFormFile.getXmlFile();
        XmlObject xmlObject;
        try {
            xmlObject = XmlObject.Factory.parse(formXml);
        }catch (XmlException e) {
            throw new RuntimeException("XmlObject not ready");
        }
        S2sUserAttachedFormContract userAttachedForm = findUserAttachedForm();
        List<? extends S2sUserAttachedFormAttContract> attachments = userAttachedForm.getS2sUserAttachedFormAtts();
        for (Iterator iterator = attachments.iterator(); iterator.hasNext();) {
            S2sUserAttachedFormAttContract s2sUserAttachedFormAtt = (S2sUserAttachedFormAttContract) iterator.next();
            addAttachment(s2sUserAttachedFormAtt);
        }
        return xmlObject;
    }

    protected void addAttachment(AttachmentData attachment) {
        attachments.add(attachment);
    }

    private void addAttachment(S2sUserAttachedFormAttContract s2sUserAttachedFormAtt) {
        S2sUserAttachedFormAttFileContract s2sUserAttachedFormAttFile = findS2sUserAttachedFormAttFile(s2sUserAttachedFormAtt);
        AttachmentData attachmentData = new AttachmentData();
        attachmentData.setContent(s2sUserAttachedFormAttFile.getAttachment());
        attachmentData.setContentId(s2sUserAttachedFormAtt.getContentId());
        attachmentData.setContentType(s2sUserAttachedFormAtt.getType());
        attachmentData.setFileName(s2sUserAttachedFormAtt.getContentId());
        addAttachment(attachmentData);
    }

    private S2sUserAttachedFormAttFileContract findS2sUserAttachedFormAttFile(S2sUserAttachedFormAttContract s2sUserAttachedFormAtt) {
        if(s2sUserAttachedFormAtt!=null){
            return userAttachedFormService.findUserAttachedFormAttFile(s2sUserAttachedFormAtt);
        }else{
            return null;
        }
    }

    private S2sUserAttachedFormFileContract findUserAttachedFormFile() {
        S2sUserAttachedFormContract userAttachedForm = findUserAttachedForm();
        if(userAttachedForm!=null){
            return userAttachedFormService.findUserAttachedFormFile(userAttachedForm);
        }
        return null;
    }

    public List<AuditError> getAuditErrors() {
        return auditErrors;
    }

    public void setAuditErrors(List<AuditError> auditErrors) {
        this.auditErrors = auditErrors;
    }

    public List<AttachmentData> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentData> attachments) {
        this.attachments = attachments;
    }

    private S2sUserAttachedFormContract findUserAttachedForm() {
        return userAttachedFormService.findFormByProposalNumberAndNamespace(pdDoc.getDevelopmentProposal().getProposalNumber(), getNamespace());
    }

    public UserAttachedFormService getUserAttachedFormService() {
        return userAttachedFormService;
    }

    public void setUserAttachedFormService(UserAttachedFormService userAttachedFormService) {
        this.userAttachedFormService = userAttachedFormService;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
