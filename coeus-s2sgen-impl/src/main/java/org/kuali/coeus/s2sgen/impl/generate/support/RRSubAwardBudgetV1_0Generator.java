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

import gov.grants.apply.forms.rrBudgetV10.RRBudgetDocument;
import gov.grants.apply.forms.rrBudgetV10.RRBudgetDocument.RRBudget;
import gov.grants.apply.forms.rrSubawardBudgetV10.RRSubawardBudgetDocument;
import gov.grants.apply.forms.rrSubawardBudgetV10.RRSubawardBudgetDocument.RRSubawardBudget;
import gov.grants.apply.forms.rrSubawardBudgetV10.RRSubawardBudgetDocument.RRSubawardBudget.BudgetAttachments;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@FormGenerator("RRSubAwardBudgetV1_0Generator")
public class RRSubAwardBudgetV1_0Generator extends S2SAdobeFormAttachmentBaseGenerator {

    @Value("http://apply.grants.gov/forms/RR_SubawardBudget-V1.0")
    private String namespace;

    @Value("RR_SubawardBudget-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_SubawardBudget-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrSubawardBudgetV10")
    private String packageName;

    @Value("177")
    private int sortIndex;

    private RRSubawardBudgetDocument getRRSubawardBudgetDocument() {
        RRSubawardBudgetDocument rrSubawardBudgetDocument = RRSubawardBudgetDocument.Factory.newInstance();

        RRSubawardBudget rrSubawardBudget = RRSubawardBudget.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc,RR_BUDGET_10_NAMESPACE_URI, true);
        List<RRBudget> budgetList = new ArrayList<>();

        rrSubawardBudget.setFormVersion(FormVersion.v1_0.getVersion());
        int attCount = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            RRBudgetDocument rrBudgetDocument = getRRBudget(budgetSubAwards);
            if (rrBudgetDocument != null) {
                final RRBudget rrBudget = rrBudgetDocument.getRRBudget();
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
        budgetAttachments.setRRBudgetArray(budgetList.toArray(new RRBudget[budgetList.size()]));
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRSubawardBudget(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }


    private RRBudgetDocument getRRBudget(BudgetSubAwardsContract budgetSubAwards) {
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc = stringToDom(subAwdXML);
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_BUDGET_10_NAMESPACE_URI, LOCAL_NAME);
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
            return (RRBudgetDocument) XmlObject.Factory.parse(bgtIS);

        }
        catch (XmlException|IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
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
