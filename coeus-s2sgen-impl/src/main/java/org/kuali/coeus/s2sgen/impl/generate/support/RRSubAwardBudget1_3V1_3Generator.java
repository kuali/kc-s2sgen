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

import gov.grants.apply.forms.rrBudget13V13.RRBudget13Document;
import gov.grants.apply.forms.rrBudget13V13.RRBudget13Document.RRBudget13;
import gov.grants.apply.forms.rrSubawardBudget13V13.RRSubawardBudget13Document;
import gov.grants.apply.forms.rrSubawardBudget13V13.RRSubawardBudget13Document.RRSubawardBudget13;
import gov.grants.apply.forms.rrSubawardBudget13V13.RRSubawardBudget13Document.RRSubawardBudget13.BudgetAttachments;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@FormGenerator("RRSubAwardBudget1_3V1_3Generator")
public class RRSubAwardBudget1_3V1_3Generator extends S2SAdobeFormAttachmentBaseGenerator {

    private static final String RR_BUDGET1_3_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget_1_3-V1.3";
    private static final String RR_BUDGET1_3_LOCAL_NAME = "RR_Budget_1_3";

    @Value("http://apply.grants.gov/forms/RR_SubawardBudget_1_3-V1.3")
    private String namespace;

    @Value("RR_SubawardBudget_1_3")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_SubawardBudget-V1.3.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrSubawardBudget13V13")
    private String packageName;

    @Value("177")
    private int sortIndex;

    private RRSubawardBudget13Document getRRSubawardBudgetDocument() {

        RRSubawardBudget13Document rrSubawardBudgetDocument = RRSubawardBudget13Document.Factory.newInstance();
        RRSubawardBudget13 rrSubawardBudget = RRSubawardBudget13.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc,RR_BUDGET1_3_NAMESPACE_URI,false);
        List<RRBudget13> budgetList = new ArrayList<>();
        rrSubawardBudget.setFormVersion(FormVersion.v1_3.getVersion());

        int attCount = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            RRBudget13Document rrBudgetDocument = getRRBudget10(budgetSubAwards);
            if (rrBudgetDocument != null) {
                RRBudget13 rrBudget = rrBudgetDocument.getRRBudget13();
                switch (attCount) {
                    case 1:
                        rrSubawardBudget.setATT1(prepareAttName(budgetSubAwards));
                        budgetList.add(0, rrBudget);
                        break;
                    case 2:
                        rrSubawardBudget.setATT2(prepareAttName(budgetSubAwards));
                        budgetList.add(1, rrBudget);
                        break;
                    case 3:
                        rrSubawardBudget.setATT3(prepareAttName(budgetSubAwards));
                        budgetList.add(2, rrBudget);
                        break;
                    case 4:
                        rrSubawardBudget.setATT4(prepareAttName(budgetSubAwards));
                        budgetList.add(3, rrBudget);
                        break;
                    case 5:
                        rrSubawardBudget.setATT5(prepareAttName(budgetSubAwards));
                        budgetList.add(4, rrBudget);
                        break;
                    case 6:
                        rrSubawardBudget.setATT6(prepareAttName(budgetSubAwards));
                        budgetList.add(5, rrBudget);
                        break;
                    case 7:
                        rrSubawardBudget.setATT7(prepareAttName(budgetSubAwards));
                        budgetList.add(6, rrBudget);
                        break;
                    case 8:
                        rrSubawardBudget.setATT8(prepareAttName(budgetSubAwards));
                        budgetList.add(7, rrBudget);
                        break;
                    case 9:
                        rrSubawardBudget.setATT9(prepareAttName(budgetSubAwards));
                        budgetList.add(8, rrBudget);
                        break;
                    case 10:
                        rrSubawardBudget.setATT10(prepareAttName(budgetSubAwards));
                        budgetList.add(9, rrBudget);
                        break;
                }
                addSubAwdAttachments(budgetSubAwards);
                attCount++;
            }
        }
        budgetAttachments.setRRBudget13Array(budgetList.toArray(new RRBudget13[budgetList.size()]));
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRSubawardBudget13(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    private RRBudget13Document getRRBudget10(BudgetSubAwardsContract budgetSubAwards) {
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc = stringToDom(subAwdXML);
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_BUDGET1_3_NAMESPACE_URI, RR_BUDGET1_3_LOCAL_NAME);
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }
        byte[] subAwdNodeBytes;
        try {
            subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
            InputStream bgtIS = new ByteArrayInputStream(subAwdNodeBytes);
            return (RRBudget13Document) XmlObject.Factory.parse(bgtIS);
        } catch (XmlException|IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RRSubawardBudget13Document getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        pdDoc=proposalDevelopmentDocument;
        return getRRSubawardBudgetDocument();
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
