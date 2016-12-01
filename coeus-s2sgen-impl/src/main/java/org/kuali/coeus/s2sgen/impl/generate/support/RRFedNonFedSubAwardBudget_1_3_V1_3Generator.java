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

import gov.grants.apply.forms.rrFedNonFedBudget12V12.RRFedNonFedBudget12Document;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.RRFedNonFedBudget12Document.RRFedNonFedBudget12;
import gov.grants.apply.forms.rrFedNonFedSubawardBudget13V13.RRFedNonFedSubawardBudget13Document;
import gov.grants.apply.forms.rrFedNonFedSubawardBudget13V13.RRFedNonFedSubawardBudget13Document.RRFedNonFedSubawardBudget13;
import gov.grants.apply.forms.rrFedNonFedSubawardBudget13V13.RRFedNonFedSubawardBudget13Document.RRFedNonFedSubawardBudget13.BudgetAttachments;
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

@FormGenerator("RRFedNonFedSubAwardBudget_1_3_V1_3Generator")
public class RRFedNonFedSubAwardBudget_1_3_V1_3Generator extends S2SAdobeFormAttachmentBaseGenerator {

    @Value("http://apply.grants.gov/forms/RR_FedNonFed_SubawardBudget_1_3-V1.3")
    private String namespace;

    @Value("RRFedNonFedSubAwardBudget_1_3_V1_3")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_FedNonFedSubawardBudget13-V1.3.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrFedNonFedSubawardBudget13V13")
    private String packageName;

    @Value("172")
    private int sortIndex;

    private static final String RR_FED_NON_FED_BUDGET_12_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_FedNonFedBudget_1_2-V1.2";
    private static final String LOCAL_FED_NON_FED_NAME = "RR_FedNonFedBudget_1_2";


    private RRFedNonFedSubawardBudget13Document getRRFedNonFedSubawardBudgetDocument() {
        RRFedNonFedSubawardBudget13 rrSubawardBudget = RRFedNonFedSubawardBudget13.Factory.newInstance();
        rrSubawardBudget.setFormVersion(FormVersion.v1_3.getVersion());

        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc, RR_FED_NON_FED_BUDGET_12_NAMESPACE_URI,false);
        RRFedNonFedBudget12[] budgetList = new RRFedNonFedBudget12[budgetSubAwardsList.size()];

        int attCount = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            RRFedNonFedBudget12 rrBudget = getRRFedNonFedBudget(budgetSubAwards).getRRFedNonFedBudget12();
            switch (attCount) {
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
            attCount++;
            
        }
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        budgetAttachments.setRRFedNonFedBudget12Array(budgetList);
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        RRFedNonFedSubawardBudget13Document rrSubawardBudgetDocument = RRFedNonFedSubawardBudget13Document.Factory.newInstance();
        rrSubawardBudgetDocument.setRRFedNonFedSubawardBudget13(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    private RRFedNonFedBudget12Document getRRFedNonFedBudget(BudgetSubAwardsContract budgetSubAwards) {
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc = stringToDom(subAwdXML);
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_FED_NON_FED_BUDGET_12_NAMESPACE_URI, LOCAL_FED_NON_FED_NAME);
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }

        try {
            byte[] subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
            return (RRFedNonFedBudget12Document) XmlObject.Factory.parse(new ByteArrayInputStream(subAwdNodeBytes));
        } catch (XmlException|IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public RRFedNonFedSubawardBudget13Document getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        pdDoc=proposalDevelopmentDocument;
        return getRRFedNonFedSubawardBudgetDocument();
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
