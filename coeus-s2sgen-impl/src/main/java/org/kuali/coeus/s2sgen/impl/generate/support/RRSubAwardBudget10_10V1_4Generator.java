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

import gov.grants.apply.forms.rrBudget1014V14.RRBudget1014Document;
import gov.grants.apply.forms.rrBudget1014V14.RRBudget1014Document.RRBudget1014;
import gov.grants.apply.forms.rrSubawardBudget101014V14.RRSubawardBudget101014Document;
import gov.grants.apply.forms.rrSubawardBudget101014V14.RRSubawardBudget101014Document.RRSubawardBudget101014;
import gov.grants.apply.forms.rrSubawardBudget101014V14.RRSubawardBudget101014Document.RRSubawardBudget101014.BudgetAttachments;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;

import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.List;

@FormGenerator("RRSubAwardBudget10_10V1_4Generator")
public class RRSubAwardBudget10_10V1_4Generator extends S2SAdobeFormAttachmentBaseGenerator {
    
    private static final String RR_BUDGET_10_1_4_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget10_1_4-V1.4";
    private static final String RR_BUDGET_10_1_4_LOCAL_NAME = "RR_Budget10_1_4";

    @Value("http://apply.grants.gov/forms/RR_SubawardBudget10_10_1_4-V1.4")
    private String namespace;

    @Value("RR_SubawardBudget10_10_1_4")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_SubawardBudget10_10-V1.4.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrSubawardBudget101014V14")
    private String packageName;

    @Value("178")
    private int sortIndex;

    private RRSubawardBudget101014Document getRRSubawardBudgetDocument() {

        RRSubawardBudget101014Document rrSubawardBudgetDocument = RRSubawardBudget101014Document.Factory.newInstance();
        RRSubawardBudget101014 rrSubawardBudget = RRSubawardBudget101014.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc,RR_BUDGET_10_1_4_NAMESPACE_URI,false);
        RRBudget1014[] budgetList = new RRBudget1014[budgetSubAwardsList.size()];
        rrSubawardBudget.setFormVersion(FormVersion.v1_4.getVersion());

        int attIndex = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            final RRBudget1014Document rrBudgetDocument = getRRBudget(budgetSubAwards);
            if (rrBudgetDocument != null) {
                RRBudget1014 rrBudget = rrBudgetDocument.getRRBudget1014();
                switch (attIndex) {
                    case 1:
                        rrSubawardBudget.setATT1(prepareAttName(budgetSubAwards));
                        budgetList[0] = rrBudget;
                        break;
                    case 2:
                        rrSubawardBudget.setATT2(prepareAttName(budgetSubAwards));
                        budgetList[1] = rrBudget;
                        break;
                    case 3:
                        rrSubawardBudget.setATT3(prepareAttName(budgetSubAwards));
                        budgetList[2] = rrBudget;
                        break;
                    case 4:
                        rrSubawardBudget.setATT4(prepareAttName(budgetSubAwards));
                        budgetList[3] = rrBudget;
                        break;
                    case 5:
                        rrSubawardBudget.setATT5(prepareAttName(budgetSubAwards));
                        budgetList[4] = rrBudget;
                        break;
                    case 6:
                        rrSubawardBudget.setATT6(prepareAttName(budgetSubAwards));
                        budgetList[5] = rrBudget;
                        break;
                    case 7:
                        rrSubawardBudget.setATT7(prepareAttName(budgetSubAwards));
                        budgetList[6] = rrBudget;
                        break;
                    case 8:
                        rrSubawardBudget.setATT8(prepareAttName(budgetSubAwards));
                        budgetList[7] = rrBudget;
                        break;
                    case 9:
                        rrSubawardBudget.setATT9(prepareAttName(budgetSubAwards));
                        budgetList[8] = rrBudget;
                        break;
                    case 10:
                        rrSubawardBudget.setATT10(prepareAttName(budgetSubAwards));
                        budgetList[9] = rrBudget;
                        break;
                }
                addSubAwdAttachments(budgetSubAwards);
                attIndex++;
            }
        }
        budgetAttachments.setRRBudget1014Array(budgetList);
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRSubawardBudget101014(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    private RRBudget1014Document getRRBudget(BudgetSubAwardsContract budgetSubAwards) {
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc = stringToDom(subAwdXML);
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_BUDGET_10_1_4_NAMESPACE_URI, RR_BUDGET_10_1_4_LOCAL_NAME);
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }
        byte[] subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
        try {
            return (RRBudget1014Document) XmlObject.Factory.parse(new ByteArrayInputStream(subAwdNodeBytes));
        } catch (XmlException|IOException e) {
           throw new RuntimeException(e);
        }
    }

    @Override
    public RRSubawardBudget101014Document getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
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
