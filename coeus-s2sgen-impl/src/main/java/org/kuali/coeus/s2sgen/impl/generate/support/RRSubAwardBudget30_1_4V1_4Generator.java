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
import gov.grants.apply.forms.rrSubawardBudget3014V14.RRSubawardBudget3014Document;
import gov.grants.apply.forms.rrSubawardBudget3014V14.RRSubawardBudget3014Document.RRSubawardBudget3014.BudgetAttachments;
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
import java.util.List;

@FormGenerator("RRSubAwardBudget30_1_4V1_4Generator")
public class RRSubAwardBudget30_1_4V1_4Generator extends S2SAdobeFormAttachmentBaseGenerator {

    
    private static final String RR_BUDGET30_14_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget_1_4-V1.4";
    private static final String RR_BUDGET30_14_LOCAL_NAME = "RR_Budget_1_4";

    @Value("http://apply.grants.gov/forms/RR_SubawardBudget30_1_4-V1.4")
    private String namespace;

    @Value("RR_SubawardBudget30_1_4")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_SubawardBudget30_1_4-V1.4.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrSubawardBudget3014V14")
    private String packageName;

    @Value("180")
    private int sortIndex;

    private RRSubawardBudget3014Document getRRSubawardBudgetDocument() {

        RRSubawardBudget3014Document rrSubawardBudgetDocument = RRSubawardBudget3014Document.Factory.newInstance();
        RRSubawardBudget3014Document.RRSubawardBudget3014 rrSubawardBudget = RRSubawardBudget3014Document.RRSubawardBudget3014.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc, RR_BUDGET30_14_NAMESPACE_URI,false);
        RRBudget14[] budgetList = new RRBudget14[budgetSubAwardsList.size()];
        rrSubawardBudget.setFormVersion(FormVersion.v1_4.getVersion());

        int attIndex = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            RRBudget14 rrBudget = getRRBudget(budgetSubAwards).getRRBudget14();
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
                case 11:
                    rrSubawardBudget.setATT11(prepareAttName(budgetSubAwards));
                    budgetList[10] = rrBudget;
                    break;
                case 12:
                    rrSubawardBudget.setATT12(prepareAttName(budgetSubAwards));
                    budgetList[11] = rrBudget;
                    break;
                case 13:
                    rrSubawardBudget.setATT13(prepareAttName(budgetSubAwards));
                    budgetList[12] = rrBudget;
                    break;
                case 14:
                    rrSubawardBudget.setATT14(prepareAttName(budgetSubAwards));
                    budgetList[13] = rrBudget;
                    break;
                case 15:
                    rrSubawardBudget.setATT15(prepareAttName(budgetSubAwards));
                    budgetList[14] = rrBudget;
                    break;
                case 16:
                    rrSubawardBudget.setATT16(prepareAttName(budgetSubAwards));
                    budgetList[15] = rrBudget;
                    break;
                case 17:
                    rrSubawardBudget.setATT17(prepareAttName(budgetSubAwards));
                    budgetList[16] = rrBudget;
                    break;
                case 18:
                    rrSubawardBudget.setATT18(prepareAttName(budgetSubAwards));
                    budgetList[17] = rrBudget;
                    break;
                case 19:
                    rrSubawardBudget.setATT19(prepareAttName(budgetSubAwards));
                    budgetList[18] = rrBudget;
                    break;
                case 20:
                    rrSubawardBudget.setATT20(prepareAttName(budgetSubAwards));
                    budgetList[19] = rrBudget;
                    break;
                case 21:
                    rrSubawardBudget.setATT21(prepareAttName(budgetSubAwards));
                    budgetList[20] = rrBudget;
                    break;
                case 22:
                    rrSubawardBudget.setATT22(prepareAttName(budgetSubAwards));
                    budgetList[21] = rrBudget;
                    break;
                case 23:
                    rrSubawardBudget.setATT23(prepareAttName(budgetSubAwards));
                    budgetList[22] = rrBudget;
                    break;
                case 24:
                    rrSubawardBudget.setATT24(prepareAttName(budgetSubAwards));
                    budgetList[23] = rrBudget;
                    break;
                case 25:
                    rrSubawardBudget.setATT25(prepareAttName(budgetSubAwards));
                    budgetList[24] = rrBudget;
                    break;
                case 26:
                    rrSubawardBudget.setATT26(prepareAttName(budgetSubAwards));
                    budgetList[25] = rrBudget;
                    break;
                case 27:
                    rrSubawardBudget.setATT27(prepareAttName(budgetSubAwards));
                    budgetList[26] = rrBudget;
                    break;
                case 28:
                    rrSubawardBudget.setATT28(prepareAttName(budgetSubAwards));
                    budgetList[27] = rrBudget;
                    break;
                case 29:
                    rrSubawardBudget.setATT29(prepareAttName(budgetSubAwards));
                    budgetList[28] = rrBudget;
                    break;
                case 30:
                    rrSubawardBudget.setATT30(prepareAttName(budgetSubAwards));
                    budgetList[29] = rrBudget;
                    break;
            }
            addSubAwdAttachments(budgetSubAwards);
            attIndex++;
            
        }
        budgetAttachments.setRRBudget14Array(budgetList);
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRSubawardBudget3014(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    private RRBudget14Document getRRBudget(BudgetSubAwardsContract budgetSubAwards) {
        RRBudget14Document rrBudget;
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc;
        subAwdFormsDoc = stringToDom(subAwdXML);
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_BUDGET30_14_NAMESPACE_URI, RR_BUDGET30_14_LOCAL_NAME);
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }
        byte[] subAwdNodeBytes = null;
        try {
            subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
            InputStream bgtIS = new ByteArrayInputStream(subAwdNodeBytes);
            rrBudget = (RRBudget14Document) XmlObject.Factory.parse(bgtIS);
        }
        catch (IOException | XmlException e) {
            throw new RuntimeException("Could not get RRBudget14Document ", e);
        }

        return rrBudget;
    }


    /**
     * This method creates {@link XmlObject} of type {@link RRSubawardBudget3014Document} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
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
