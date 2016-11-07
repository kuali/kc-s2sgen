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

import gov.grants.apply.forms.rrBudget14V14.RRBudget14Document;
import gov.grants.apply.forms.rrBudget14V14.RRBudget14Document.RRBudget14;
import gov.grants.apply.forms.rrSubawardBudget14V14.RRSubawardBudget14Document;
import gov.grants.apply.forms.rrSubawardBudget14V14.RRSubawardBudget14Document.RRSubawardBudget14;
import gov.grants.apply.forms.rrSubawardBudget14V14.RRSubawardBudget14Document.RRSubawardBudget14.BudgetAttachments;
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
import java.util.ArrayList;
import java.util.List;

@FormGenerator("RRSubAwardBudget1_4V1_4Generator")
public class RRSubAwardBudget1_4V1_4Generator extends S2SAdobeFormAttachmentBaseGenerator {

    private static final String RR_BUDGET1_4_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget_1_4-V1.4";
    private static final String RR_BUDGET1_4_LOCAL_NAME = "RR_Budget_1_4";

    @Value("http://apply.grants.gov/forms/RR_SubawardBudget_1_4-V1.4")
    private String namespace;

    @Value("RR_SubawardBudget_1_4")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_SubawardBudget-V1.4.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrSubawardBudget14V14")
    private String packageName;

    @Value("177")
    private int sortIndex;

    private RRSubawardBudget14Document getRRSubawardBudgetDocument() {

        RRSubawardBudget14Document rrSubawardBudgetDocument = RRSubawardBudget14Document.Factory.newInstance();
        RRSubawardBudget14 rrSubawardBudget = RRSubawardBudget14.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc,RR_BUDGET1_4_NAMESPACE_URI,false);
        List<RRBudget14> budgetList = new ArrayList<>();
        rrSubawardBudget.setFormVersion(FormVersion.v1_4.getVersion());

        int attCount = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            final RRBudget14Document rrBudgetDocument = getRRBudget10(budgetSubAwards);
            if (rrBudgetDocument != null) {
               final  RRBudget14 rrBudget = rrBudgetDocument.getRRBudget14();

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
        budgetAttachments.setRRBudget14Array(budgetList.toArray(new RRBudget14[budgetList.size()]));
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRSubawardBudget14(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    private RRBudget14Document getRRBudget10(BudgetSubAwardsContract budgetSubAwards) {
        final RRBudget14Document rrBudget;
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc = stringToDom(subAwdXML);

        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_BUDGET1_4_NAMESPACE_URI, RR_BUDGET1_4_LOCAL_NAME);
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }


        byte[] subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));

        try {
            rrBudget = (RRBudget14Document) XmlObject.Factory.parse(new ByteArrayInputStream(subAwdNodeBytes));
        } catch (XmlException|IOException e) {
            throw new RuntimeException(e);
        }

        return rrBudget;
    }

    @Override
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
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
