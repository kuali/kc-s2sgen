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


import gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument;
import gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument.AdditionalEquipmentList;
import gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument;
import gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList;
import gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons.Compensation;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.*;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.BudgetYearDataType.*;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.BudgetYearDataType.Equipment.EquipmentList;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.BudgetYearDataType.OtherDirectCosts.Others;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.BudgetYearDataType.OtherPersonnel.GraduateStudents;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.BudgetYearDataType.OtherPersonnel.PostDocAssociates;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.BudgetYearDataType.OtherPersonnel.SecretarialClerical;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.BudgetYearDataType.OtherPersonnel.UndergraduateStudents;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.BudgetYearDataType.ParticipantTraineeSupportCosts.Other;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.RRFedNonFedBudget12Document.RRFedNonFedBudget12;
import gov.grants.apply.forms.rrFedNonFedBudget12V12.RRFedNonFedBudget12Document.RRFedNonFedBudget12.BudgetSummary;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.budget.api.core.BudgetContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.budget.*;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.print.GenericPrintable;
import org.kuali.coeus.s2sgen.impl.print.S2SPrintingService;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FormGenerator("RRFedNonFedBudgetV1_2Generator")
public class RRFedNonFedBudgetV1_2Generator extends RRFedNonFedBudgetBaseGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(RRFedNonFedBudgetV1_0Generator.class);
    private static final String EXTRA_KEYPERSONS = "RRFEDNONFED_EXTRA_KEYPERSONS";
    private static final int EXTRA_KEYPERSONS_TYPE = 11;
    private static final String EXTRA_KEYPERSONS_COMMENT = "RRFEDNONFED_EXTRA_KEYPERSONS";
    private static final String ADDITIONAL_EQUIPMENT_NARRATIVE_TYPE_CODE ="12";
    private static final String ADDITIONAL_EQUIPMENT_NARRATIVE_COMMENT = "RRFEDNONFED_ADDITIONAL_EQUIPMENT";
    private BudgetContract budget;

    @Value("http://apply.grants.gov/forms/RR_FedNonFedBudget_1_2-V1.2")
    private String namespace;

    @Value("RR_FedNonFedBudget_1_2-V1.2")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_FedNonFedBudget-V1.2.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrFedNonFedBudget12V12")
    private String packageName;

    @Value("169")
    private int sortIndex;

    @Autowired
    @Qualifier("s2SPrintingService")
    private S2SPrintingService s2SPrintingService;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/AdditionalEquipmentAttachmentNonFed.xsl")
    private  Resource additionalEquipmentAttachmentNonFedStyleSheet;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/ExtraKeyPersonAttachmentNonFed.xsl")
    private Resource extraKeyPersonAttachmentNonFedStyleSheet;

    private RRFedNonFedBudget12Document getRRFedNonFedBudget() {
        RRFedNonFedBudget12Document rrFedNonFedBudgetDocument = RRFedNonFedBudget12Document.Factory.newInstance();
        RRFedNonFedBudget12 rrFedNonFedBudget = RRFedNonFedBudget12.Factory.newInstance();
        rrFedNonFedBudget.setFormVersion(FormVersion.v1_2.getVersion());
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            rrFedNonFedBudget.setDUNSID(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getDunsNumber());
        }
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            rrFedNonFedBudget.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
        }
        rrFedNonFedBudget.setBudgetType(BudgetTypeDataType.PROJECT);

        validateBudgetForForm(pdDoc);
        List<BudgetPeriodDto> budgetPeriodList = s2sBudgetCalculatorService.getBudgetPeriods(pdDoc);
        BudgetSummaryDto budgetSummary = s2sBudgetCalculatorService.getBudgetInfo(pdDoc,budgetPeriodList);
        budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());

        rrFedNonFedBudget.setBudgetYearArray(budgetPeriodList.stream()
                .filter(budgetPeriodData ->
                        Stream.of(BudgetPeriodNum.P1.getNum(),BudgetPeriodNum.P2.getNum(),BudgetPeriodNum.P3.getNum(),BudgetPeriodNum.P4.getNum(),BudgetPeriodNum.P5.getNum())
                                .collect(Collectors.toSet())
                                .contains(budgetPeriodData.getBudgetPeriod()))
                .map(this::getBudgetYearDataType)
                .toArray(BudgetYearDataType[]::new));

        final AttachedFileDataType budgetJustification = getBudgetJustificationAttachment();
        if (budgetJustification != null) {
            rrFedNonFedBudget.setBudgetJustificationAttachment(budgetJustification);
        }

        rrFedNonFedBudget.setBudgetSummary(getBudgetSummary(budgetSummary));
        rrFedNonFedBudgetDocument.setRRFedNonFedBudget12(rrFedNonFedBudget);
        return rrFedNonFedBudgetDocument;
    }

    private BudgetYearDataType getBudgetYearDataType(BudgetPeriodDto periodInfo) {

        ScaleTwoDecimal totalDirectCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalIndirectCostSharing = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal directCostsTotal = ScaleTwoDecimal.ZERO;
        BudgetYearDataType budgetYear = Factory.newInstance();
        if (periodInfo != null) {
            budgetYear.setBudgetPeriodStartDate(s2SDateTimeService.convertDateToCalendar(periodInfo.getStartDate()));
            budgetYear.setBudgetPeriodEndDate(s2SDateTimeService.convertDateToCalendar(periodInfo.getEndDate()));
            BudgetPeriod.Enum budgetPeriodEnum = BudgetPeriod.Enum
                    .forInt(periodInfo.getBudgetPeriod());
            budgetYear.setBudgetPeriod(budgetPeriodEnum);
            budgetYear.setKeyPersons(getKeyPersons(periodInfo));
            budgetYear.setOtherPersonnel(getOtherPersonnel(periodInfo));
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalCompensation() != null) {
                summary.setFederalSummary(periodInfo.getTotalCompensation().bigDecimalValue());
            }
            if (periodInfo.getTotalCompensationCostSharing() != null) {
                if (budget.getSubmitCostSharingFlag()) {
                    summary.setNonFederalSummary(periodInfo.getTotalCompensationCostSharing().bigDecimalValue());
                    if (periodInfo.getTotalCompensation() != null) {
                        summary.setTotalFedNonFedSummary(periodInfo.getTotalCompensation().add(
                                periodInfo.getTotalCompensationCostSharing()).bigDecimalValue());
                    } else {
                        summary.setTotalFedNonFedSummary(periodInfo.getTotalCompensationCostSharing().bigDecimalValue());
                    }
                } else {
                    summary.setNonFederalSummary(BigDecimal.ZERO);
                    if (periodInfo.getTotalCompensation() != null) {
                        summary.setTotalFedNonFedSummary(periodInfo.getTotalCompensation().bigDecimalValue());
                    } else {
                        summary.setTotalFedNonFedSummary(BigDecimal.ZERO);
                    }
                }
            }
            budgetYear.setTotalCompensation(summary);
            budgetYear.setEquipment(getEquipment(periodInfo));
            budgetYear.setTravel(getTravel(periodInfo));
            budgetYear.setParticipantTraineeSupportCosts(getParticipantTraineeSupportCosts(periodInfo));
            budgetYear.setOtherDirectCosts(getOtherDirectCosts(periodInfo));
            SummaryDataType summaryDirect = SummaryDataType.Factory.newInstance();
            if (periodInfo.getDirectCostsTotal() != null) {
                directCostsTotal = periodInfo.getDirectCostsTotal();
                summaryDirect.setFederalSummary( directCostsTotal.bigDecimalValue());
            }

                if (periodInfo.getTotalDirectCostSharing() != null) {
                totalDirectCostSharing = periodInfo.getTotalDirectCostSharing();
                }
                summaryDirect.setNonFederalSummary(totalDirectCostSharing.bigDecimalValue());
                if (directCostsTotal != null) {
                    summaryDirect.setTotalFedNonFedSummary(directCostsTotal.add(totalDirectCostSharing).bigDecimalValue());
                }
                else {
                    summaryDirect.setTotalFedNonFedSummary(totalDirectCostSharing.bigDecimalValue());
                }

            budgetYear.setDirectCosts(summaryDirect);
            IndirectCosts indirectCosts = getIndirectCosts(periodInfo);
            if (indirectCosts != null) {
                budgetYear.setIndirectCosts(indirectCosts);
            }
            budgetYear.setCognizantFederalAgency(periodInfo.getCognizantFedAgency());
            if (periodInfo.getIndirectCosts().getTotalIndirectCostSharing() != null){
            totalIndirectCostSharing = periodInfo.getIndirectCosts().getTotalIndirectCostSharing();
            }
            SummaryDataType summaryTotal = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalCosts() != null) {
                summaryTotal.setFederalSummary(periodInfo.getTotalCosts().bigDecimalValue());
            }
            summaryTotal.setNonFederalSummary(totalDirectCostSharing.bigDecimalValue().add(totalIndirectCostSharing.bigDecimalValue()));
             if (periodInfo.getTotalCosts() != null) {
                 summaryTotal.setTotalFedNonFedSummary(periodInfo.getTotalCosts().add(totalDirectCostSharing).bigDecimalValue().add(totalIndirectCostSharing.bigDecimalValue()));
             }
             else {
                 summaryTotal.setTotalFedNonFedSummary(totalDirectCostSharing.bigDecimalValue().add(totalIndirectCostSharing.bigDecimalValue()));
             }
            budgetYear.setTotalCosts(summaryTotal);
        }

        final SummaryDataType totalCostsFee = SummaryDataType.Factory.newInstance();
        if (budgetYear.getFee() != null) {
            totalCostsFee.setTotalFedNonFedSummary(budgetYear.getFee().add(budgetYear.getTotalCosts().getTotalFedNonFedSummary()));
            totalCostsFee.setFederalSummary(budgetYear.getTotalCosts().getFederalSummary());
            totalCostsFee.setNonFederalSummary(budgetYear.getTotalCosts().getNonFederalSummary());
        } else {
            totalCostsFee.setTotalFedNonFedSummary(budgetYear.getTotalCosts().getTotalFedNonFedSummary());
            totalCostsFee.setFederalSummary(budgetYear.getTotalCosts().getFederalSummary());
            totalCostsFee.setNonFederalSummary(budgetYear.getTotalCosts().getNonFederalSummary());
        }
        budgetYear.setTotalCostsFee(totalCostsFee);

        return budgetYear;
    }

    private BudgetSummary getBudgetSummary(BudgetSummaryDto budgetSummaryData) {

        ScaleTwoDecimal cumTotalDirectCostSharing = ScaleTwoDecimal.ZERO;
        BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
        SummaryDataType summarySeniorKey = SummaryDataType.Factory.newInstance();
        SummaryDataType summaryPersonnel = SummaryDataType.Factory.newInstance();
        SummaryDataType directCosts = SummaryDataType.Factory.newInstance();
        SummaryDataType directIndirectCosts = SummaryDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getCumTotalFundsForSrPersonnel() != null) {
                summarySeniorKey.setFederalSummary(budgetSummaryData.getCumTotalFundsForSrPersonnel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumTotalNonFundsForSrPersonnel() != null) {
                summarySeniorKey.setNonFederalSummary(budgetSummaryData.getCumTotalNonFundsForSrPersonnel().bigDecimalValue());

                if (budgetSummaryData.getCumTotalFundsForSrPersonnel() != null) {
                    summarySeniorKey.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalFundsForSrPersonnel().add(
                            budgetSummaryData.getCumTotalNonFundsForSrPersonnel()).bigDecimalValue());
                }
                else {
                    summarySeniorKey.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalNonFundsForSrPersonnel()
                            .bigDecimalValue());
                }

            }
            SummaryDataType summaryOtherPersonnel = SummaryDataType.Factory.newInstance();
            if (budgetSummaryData.getCumTotalFundsForOtherPersonnel() != null) {
                summaryOtherPersonnel.setFederalSummary(budgetSummaryData.getCumTotalFundsForOtherPersonnel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumTotalNonFundsForOtherPersonnel() != null) {
                summaryOtherPersonnel.setNonFederalSummary(budgetSummaryData.getCumTotalNonFundsForOtherPersonnel()
                        .bigDecimalValue());


                if (budgetSummaryData.getCumTotalFundsForOtherPersonnel() != null) {
                    summaryOtherPersonnel.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalFundsForOtherPersonnel().add(
                            budgetSummaryData.getCumTotalNonFundsForOtherPersonnel()).bigDecimalValue());
                }
                else {
                    summaryOtherPersonnel.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalNonFundsForOtherPersonnel()
                            .bigDecimalValue());
                }
            }
            budgetSummary.setCumulativeTotalFundsRequestedOtherPersonnel(summaryOtherPersonnel);
            if (budgetSummaryData.getCumNumOtherPersonnel() != null) {
                budgetSummary.setCumulativeTotalNoOtherPersonnel(budgetSummaryData.getCumNumOtherPersonnel().intValue());
            }
            if (budgetSummaryData.getCumTotalFundsForPersonnel() != null) {
                summaryPersonnel.setFederalSummary(budgetSummaryData.getCumTotalFundsForPersonnel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumTotalNonFundsForPersonnel() != null) {
                summaryPersonnel.setNonFederalSummary(budgetSummaryData.getCumTotalNonFundsForPersonnel().bigDecimalValue());
                if (budgetSummaryData.getCumTotalFundsForPersonnel() != null) {
                    summaryPersonnel.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalFundsForPersonnel().add(
                            budgetSummaryData.getCumTotalNonFundsForPersonnel()).bigDecimalValue());
                }
                else {
                    summaryPersonnel
                            .setTotalFedNonFedSummary(budgetSummaryData.getCumTotalNonFundsForPersonnel().bigDecimalValue());
                }
            }
            budgetSummary.setCumulativeTotalFundsRequestedEquipment(getCumulativeEquipments(budgetSummaryData));

            budgetSummary.setCumulativeTotalFundsRequestedTravel(getCumulativeTravels(budgetSummaryData));
            budgetSummary.setCumulativeDomesticTravelCosts(getCumulativeDomesticTravelCosts(budgetSummaryData));
            budgetSummary.setCumulativeForeignTravelCosts(getCumulativeForeignTravelCosts(budgetSummaryData));

            budgetSummary.setCumulativeTotalFundsRequestedTraineeCosts(getCumulativeTrainee(budgetSummaryData));
            budgetSummary.setCumulativeTraineeTuitionFeesHealthInsurance(getCumulativeTraineeTuitionFeesHealthInsurance(budgetSummaryData));
            budgetSummary.setCumulativeTraineeStipends(getCumulativeTraineeStipends(budgetSummaryData));
            budgetSummary.setCumulativeTraineeTravel(getCumulativeTraineeTravel(budgetSummaryData));
            budgetSummary.setCumulativeTraineeSubsistence(getCumulativeTraineeSubsistence(budgetSummaryData));
            budgetSummary.setCumulativeOtherTraineeCost(getCumulativeOtherTraineeCost(budgetSummaryData));
            budgetSummary.setCumulativeNoofTrainees(getCumulativeNoofTrainees(budgetSummaryData));

            budgetSummary.setCumulativeTotalFundsRequestedOtherDirectCosts(getCumulativeOtherDirect(budgetSummaryData));
            budgetSummary.setCumulativeOther1DirectCost(getCumulativeOther1DirectCost(budgetSummaryData));
            budgetSummary.setCumulativeAlterationsAndRenovations(getCumulativeAlterationsAndRenovations(budgetSummaryData));
            budgetSummary.setCumulativeEquipmentFacilityRentalFees(getCumulativeEquipmentFacilityRentalFees(budgetSummaryData));
            budgetSummary.setCumulativeSubawardConsortiumContractualCosts(getCumulativeSubawardConsortiumContractualCosts(budgetSummaryData));
            budgetSummary.setCumulativeADPComputerServices(getCumulativeADPComputerServices(budgetSummaryData));
            budgetSummary.setCumulativeConsultantServices(getCumulativeConsultantServices(budgetSummaryData));
            budgetSummary.setCumulativeMaterialAndSupplies(getCumulativeMaterialAndSupplies(budgetSummaryData));
            budgetSummary.setCumulativePublicationCosts(getCumulativePublicationCosts(budgetSummaryData));

            if (budgetSummaryData.getCumTotalDirectCosts() != null && budgetSummaryData.getCumTotalDirectCostSharing() != null) {
                if (budgetSummaryData.getCumFee() != null) {
                    budgetSummary.setCumulativeFee(budgetSummaryData.getCumFee().bigDecimalValue());
                }

                cumTotalDirectCostSharing = budgetSummaryData.getCumTotalNonFundsForPersonnel().add(budgetSummaryData.getCumEquipmentNonFunds()).add(budgetSummaryData.getCumTravelNonFund()).add(budgetSummaryData.getPartOtherCostSharing().add(
                                                        budgetSummaryData.getPartStipendCostSharing().add(budgetSummaryData.getPartTravelCostSharing().add(
                                                                        budgetSummaryData.getPartSubsistenceCostSharing().add( budgetSummaryData.getPartTuitionCostSharing())))));
                for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                      if (cumOtherDirect.getTotalOtherDirectCostSharing() != null) {
                          cumTotalDirectCostSharing= cumTotalDirectCostSharing.add(cumOtherDirect.getTotalOtherDirectCostSharing());
                      }
                }
                directCosts.setFederalSummary(budgetSummaryData.getCumTotalDirectCosts().bigDecimalValue());
                directCosts.setNonFederalSummary(cumTotalDirectCostSharing.bigDecimalValue());
                directCosts.setTotalFedNonFedSummary((budgetSummaryData.getCumTotalDirectCosts().bigDecimalValue()).add(
                        cumTotalDirectCostSharing.bigDecimalValue()));
            }
            if (budgetSummaryData.getCumTotalIndirectCosts() != null && budgetSummaryData.getCumTotalIndirectCostSharing() != null) {
                SummaryDataType summary = SummaryDataType.Factory.newInstance();
                summary.setFederalSummary(budgetSummaryData.getCumTotalIndirectCosts().bigDecimalValue());
                summary.setNonFederalSummary(budgetSummaryData.getCumTotalIndirectCostSharing().bigDecimalValue());
                summary.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalIndirectCosts().add(
                        budgetSummaryData.getCumTotalIndirectCostSharing()).bigDecimalValue());
                budgetSummary.setCumulativeTotalFundsRequestedIndirectCost(summary);
            }
            if (budgetSummaryData.getCumTotalCosts() != null && budgetSummaryData.getCumTotalDirectCostSharing() != null) {
                directIndirectCosts.setFederalSummary(budgetSummaryData.getCumTotalCosts().bigDecimalValue());
                directIndirectCosts.setNonFederalSummary((cumTotalDirectCostSharing.bigDecimalValue()).add(budgetSummaryData.getCumTotalIndirectCostSharing().bigDecimalValue()));
                directIndirectCosts.setTotalFedNonFedSummary((budgetSummaryData.getCumTotalCosts().bigDecimalValue()).add
                        (cumTotalDirectCostSharing.bigDecimalValue()).add(budgetSummaryData.getCumTotalIndirectCostSharing().bigDecimalValue()));
            }
        }
        budgetSummary.setCumulativeTotalFundsRequestedSeniorKeyPerson(summarySeniorKey);
        budgetSummary.setCumulativeTotalFundsRequestedPersonnel(summaryPersonnel);
        budgetSummary.setCumulativeTotalFundsRequestedDirectCosts(directCosts);
        budgetSummary.setCumulativeTotalFundsRequestedDirectIndirectCosts(directIndirectCosts);


        final SummaryDataType cumulativeTotalCostsFee = SummaryDataType.Factory.newInstance();
        if (budgetSummary.getCumulativeFee() != null) {
            cumulativeTotalCostsFee.setTotalFedNonFedSummary(budgetSummary.getCumulativeFee().add(budgetSummary.getCumulativeTotalFundsRequestedDirectIndirectCosts().getTotalFedNonFedSummary()));
            cumulativeTotalCostsFee.setFederalSummary(budgetSummary.getCumulativeTotalFundsRequestedDirectIndirectCosts().getFederalSummary());
            cumulativeTotalCostsFee.setNonFederalSummary(budgetSummary.getCumulativeTotalFundsRequestedDirectIndirectCosts().getNonFederalSummary());
        } else {
            cumulativeTotalCostsFee.setTotalFedNonFedSummary(budgetSummary.getCumulativeTotalFundsRequestedDirectIndirectCosts().getTotalFedNonFedSummary());
            cumulativeTotalCostsFee.setFederalSummary(budgetSummary.getCumulativeTotalFundsRequestedDirectIndirectCosts().getFederalSummary());
            cumulativeTotalCostsFee.setNonFederalSummary(budgetSummary.getCumulativeTotalFundsRequestedDirectIndirectCosts().getNonFederalSummary());
        }
        budgetSummary.setCumulativeTotalCostsFee(cumulativeTotalCostsFee);

        return budgetSummary;
    }

    private SummaryDataType getCumulativeOtherDirect(BudgetSummaryDto budgetSummaryData) {

        SummaryDataType summary = SummaryDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.gettotalOtherDirect() != null) {
                    summary.setFederalSummary(cumOtherDirect.gettotalOtherDirect().bigDecimalValue());
                }
                if (cumOtherDirect.getTotalOtherDirectCostSharing() != null) {
                    summary.setNonFederalSummary(cumOtherDirect.getTotalOtherDirectCostSharing().bigDecimalValue());
                    if (cumOtherDirect.gettotalOtherDirect() != null) {
                        summary.setTotalFedNonFedSummary(cumOtherDirect.gettotalOtherDirect().add(
                                cumOtherDirect.getTotalOtherDirectCostSharing()).bigDecimalValue());
                    }
                    else {
                        summary.setTotalFedNonFedSummary(cumOtherDirect.getTotalOtherDirectCostSharing().bigDecimalValue());
                    }
                }
            }
        }
        return summary;
    }

    private TotalDataType getCumulativeOther1DirectCost(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalOther = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.getOtherCosts() != null && cumOtherDirect.getOtherCosts().size() > 0) {
                    totalOther.setFederal(new BigDecimal(cumOtherDirect.getOtherCosts().get(0).get(CostConstants.KEY_COST)));
                    totalOther.setNonFederal(new BigDecimal(cumOtherDirect.getOtherCosts().get(0).get(CostConstants.KEY_COSTSHARING)));
                }
                if (totalOther.getFederal() != null && totalOther.getNonFederal() != null) {
                    totalOther.setTotalFedNonFed(totalOther.getFederal().add(totalOther.getNonFederal()));
                }
            }
        }
        return totalOther;
    }

    private TotalDataType getCumulativeAlterationsAndRenovations(BudgetSummaryDto budgetSummaryData) {

        TotalDataType totalAlterations = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.getAlterations() != null) {
                    totalAlterations.setFederal(cumOtherDirect.getAlterations().bigDecimalValue());
                }
                if (cumOtherDirect.getAlterationsCostSharing() != null) {
                    totalAlterations.setNonFederal(cumOtherDirect.getAlterationsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getAlterations() != null) {
                        totalAlterations.setTotalFedNonFed(cumOtherDirect.getAlterations().add(
                                cumOtherDirect.getAlterationsCostSharing()).bigDecimalValue());
                    } else {
                        totalAlterations.setTotalFedNonFed(cumOtherDirect.getAlterationsCostSharing().bigDecimalValue());
                    }
                }
            }
        }
        return totalAlterations;
    }

    private TotalDataType getCumulativeEquipmentFacilityRentalFees(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalEquipment = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.getEquipRental() != null) {
                    totalEquipment.setFederal(cumOtherDirect.getEquipRental().bigDecimalValue());
                }
                if (cumOtherDirect.getEquipRentalCostSharing() != null) {
                    totalEquipment.setNonFederal(cumOtherDirect.getEquipRentalCostSharing().bigDecimalValue());
                    totalEquipment.setTotalFedNonFed(cumOtherDirect.getEquipRental()
                            .add(cumOtherDirect.getEquipRentalCostSharing()).bigDecimalValue());
                }
            }
        }
        return totalEquipment;
    }

    private TotalDataType getCumulativeSubawardConsortiumContractualCosts(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalSubaward = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.getsubAwards() != null) {
                    totalSubaward.setFederal(cumOtherDirect.getsubAwards().bigDecimalValue());
                }
                if (cumOtherDirect.getSubAwardsCostSharing() != null) {
                    totalSubaward.setNonFederal(cumOtherDirect.getSubAwardsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getsubAwards() != null) {
                        totalSubaward.setTotalFedNonFed(cumOtherDirect.getsubAwards().add(cumOtherDirect.getSubAwardsCostSharing())
                                .bigDecimalValue());
                    } else {
                        totalSubaward.setTotalFedNonFed(cumOtherDirect.getSubAwardsCostSharing().bigDecimalValue());
                    }
                }
            }
        }
        return totalSubaward;
    }

    private TotalDataType getCumulativeADPComputerServices(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalComputer = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.getcomputer() != null) {
                    totalComputer.setFederal(cumOtherDirect.getcomputer().bigDecimalValue());
                }
                if (cumOtherDirect.getComputerCostSharing() != null) {
                    totalComputer.setNonFederal(cumOtherDirect.getComputerCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getcomputer() != null) {
                        totalComputer.setTotalFedNonFed(cumOtherDirect.getcomputer().add(cumOtherDirect.getComputerCostSharing())
                                .bigDecimalValue());
                    } else {
                        totalComputer.setTotalFedNonFed(cumOtherDirect.getComputerCostSharing().bigDecimalValue());
                    }
                }
            }
        }
        return totalComputer;
    }

    private TotalDataType getCumulativeConsultantServices(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalConsultant = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.getConsultants() != null) {
                    totalConsultant.setFederal(cumOtherDirect.getConsultants().bigDecimalValue());
                }
                if (cumOtherDirect.getConsultantsCostSharing() != null) {
                    totalConsultant.setNonFederal(cumOtherDirect.getConsultantsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getConsultants() != null) {
                        totalConsultant.setTotalFedNonFed(cumOtherDirect.getConsultants().add(
                                cumOtherDirect.getConsultantsCostSharing()).bigDecimalValue());
                    } else {
                        totalConsultant.setTotalFedNonFed(cumOtherDirect.getConsultantsCostSharing().bigDecimalValue());
                    }
                }
            }
        }
        return totalConsultant;
    }

    private TotalDataType getCumulativeMaterialAndSupplies(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalMaterial = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.getmaterials() != null) {
                    totalMaterial.setFederal(cumOtherDirect.getmaterials().bigDecimalValue());
                }
                if (cumOtherDirect.getMaterialsCostSharing() != null) {
                    totalMaterial.setNonFederal(cumOtherDirect.getMaterialsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getmaterials() != null) {
                        totalMaterial.setTotalFedNonFed(cumOtherDirect.getmaterials().add(cumOtherDirect.getMaterialsCostSharing())
                                .bigDecimalValue());
                    } else {
                        totalMaterial.setTotalFedNonFed(cumOtherDirect.getMaterialsCostSharing().bigDecimalValue());
                    }
                }
            }
        }
        return totalMaterial;
    }

    private TotalDataType getCumulativePublicationCosts(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalPublication = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            for (OtherDirectCostInfoDto cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.getpublications() != null) {
                    totalPublication.setFederal(cumOtherDirect.getpublications().bigDecimalValue());
                }
                if (cumOtherDirect.getPublicationsCostSharing() != null) {
                    totalPublication.setNonFederal(cumOtherDirect.getPublicationsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getpublications() != null) {
                        totalPublication.setTotalFedNonFed(cumOtherDirect.getpublications().add(
                                cumOtherDirect.getPublicationsCostSharing()).bigDecimalValue());
                    } else {
                        totalPublication.setTotalFedNonFed(cumOtherDirect.getPublicationsCostSharing().bigDecimalValue());
                    }
                }
            }
        }
        return totalPublication;
    }

    private SummaryDataType getCumulativeTrainee(BudgetSummaryDto budgetSummaryData) {

        SummaryDataType summaryTraineeCosts = SummaryDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getpartOtherCost() != null && budgetSummaryData.getpartStipendCost() != null
                    && budgetSummaryData.getpartTravelCost() != null && budgetSummaryData.getPartSubsistence() != null
                    && budgetSummaryData.getPartTuition() != null) {
                summaryTraineeCosts.setFederalSummary(budgetSummaryData.getpartOtherCost().add(
                        budgetSummaryData.getpartStipendCost().add(
                                budgetSummaryData.getpartTravelCost().add(
                                        budgetSummaryData.getPartSubsistence().add(budgetSummaryData.getPartTuition()))))
                        .bigDecimalValue());
            }
            if (budgetSummaryData.getPartOtherCostSharing() != null && budgetSummaryData.getPartStipendCostSharing() != null
                    && budgetSummaryData.getPartTravelCostSharing() != null
                    && budgetSummaryData.getPartSubsistenceCostSharing() != null
                    && budgetSummaryData.getPartTuitionCostSharing() != null) {
                summaryTraineeCosts.setNonFederalSummary(budgetSummaryData.getPartOtherCostSharing().add(
                        budgetSummaryData.getPartStipendCostSharing().add(
                                budgetSummaryData.getPartTravelCostSharing().add(
                                        budgetSummaryData.getPartSubsistenceCostSharing().add(
                                                budgetSummaryData.getPartTuitionCostSharing())))).bigDecimalValue());
            }

            if (summaryTraineeCosts.getNonFederalSummary() != null) {
                if (summaryTraineeCosts.getFederalSummary() != null) {
                    summaryTraineeCosts.setTotalFedNonFedSummary(summaryTraineeCosts.getFederalSummary().add(
                            summaryTraineeCosts.getNonFederalSummary()));
                }

                else {
                    summaryTraineeCosts.setTotalFedNonFedSummary(summaryTraineeCosts.getNonFederalSummary());
                }
            }
        }

        return summaryTraineeCosts;
    }

    private TotalDataType getCumulativeTraineeTuitionFeesHealthInsurance(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalTuition = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getPartTuition() != null) {
                totalTuition.setFederal(budgetSummaryData.getPartTuition().bigDecimalValue());
            }
            if (budgetSummaryData.getPartTuitionCostSharing() != null) {
                totalTuition.setNonFederal(budgetSummaryData.getPartTuitionCostSharing().bigDecimalValue());
                if (budgetSummaryData.getPartTuition() != null) {
                    totalTuition.setTotalFedNonFed(budgetSummaryData.getPartTuition().add(
                            budgetSummaryData.getPartTuitionCostSharing()).bigDecimalValue());
                }
                else {
                    totalTuition.setTotalFedNonFed(budgetSummaryData.getPartTuitionCostSharing().bigDecimalValue());
                }
            }
        }
        return totalTuition;
    }

    private TotalDataType getCumulativeTraineeStipends(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalStipends = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getpartStipendCost() != null) {
                totalStipends.setFederal(budgetSummaryData.getpartStipendCost().bigDecimalValue());
            }
            if (budgetSummaryData.getPartStipendCostSharing() != null) {
                totalStipends.setNonFederal(budgetSummaryData.getPartStipendCostSharing().bigDecimalValue());
                if (budgetSummaryData.getpartStipendCost() != null) {
                    totalStipends.setTotalFedNonFed(budgetSummaryData.getpartStipendCost().add(
                            budgetSummaryData.getPartStipendCostSharing()).bigDecimalValue());
                } else {
                    totalStipends.setTotalFedNonFed(budgetSummaryData.getPartStipendCostSharing().bigDecimalValue());
                }
            }
        }
        return totalStipends;
    }

    private TotalDataType getCumulativeTraineeTravel(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalTravel = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getpartTravelCost() != null) {
                totalTravel.setFederal(budgetSummaryData.getpartTravelCost().bigDecimalValue());
            }
            if (budgetSummaryData.getPartTravelCostSharing() != null) {
                totalTravel.setNonFederal(budgetSummaryData.getPartTravelCostSharing().bigDecimalValue());
                if (budgetSummaryData.getpartTravelCost() != null) {
                    totalTravel.setTotalFedNonFed(budgetSummaryData.getpartTravelCost().add(
                            budgetSummaryData.getPartTravelCostSharing()).bigDecimalValue());
                } else {
                    totalTravel.setTotalFedNonFed(budgetSummaryData.getPartTravelCostSharing().bigDecimalValue());
                }
            }
        }
        return totalTravel;
    }

    private TotalDataType getCumulativeTraineeSubsistence(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalSubsistence = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getPartSubsistence() != null) {
                totalSubsistence.setFederal(budgetSummaryData.getPartSubsistence().bigDecimalValue());
            }
            if (budgetSummaryData.getPartSubsistenceCostSharing() != null) {
                totalSubsistence.setNonFederal(budgetSummaryData.getPartSubsistenceCostSharing().bigDecimalValue());
                if (budgetSummaryData.getPartSubsistence() != null) {
                    totalSubsistence.setTotalFedNonFed(budgetSummaryData.getPartSubsistence().add(
                            budgetSummaryData.getPartSubsistenceCostSharing()).bigDecimalValue());
                } else {
                    totalSubsistence.setTotalFedNonFed(budgetSummaryData.getPartSubsistenceCostSharing().bigDecimalValue());
                }
            }
        }
        return totalSubsistence;
    }

    private TotalDataType getCumulativeOtherTraineeCost(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalOtherTrainee = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getpartOtherCost() != null) {
                totalOtherTrainee.setFederal(budgetSummaryData.getpartOtherCost().bigDecimalValue());
            }
            if (budgetSummaryData.getPartOtherCostSharing() != null) {
                totalOtherTrainee.setNonFederal(budgetSummaryData.getPartOtherCostSharing().bigDecimalValue());
                if (budgetSummaryData.getpartOtherCost() != null) {
                    totalOtherTrainee.setTotalFedNonFed(budgetSummaryData.getpartOtherCost().add(
                            budgetSummaryData.getPartOtherCostSharing()).bigDecimalValue());
                } else {
                    totalOtherTrainee.setTotalFedNonFed(budgetSummaryData.getPartOtherCostSharing().bigDecimalValue());
                }
            }
        }
        return totalOtherTrainee;
    }

    private int getCumulativeNoofTrainees(BudgetSummaryDto budgetSummaryData) {
        return budgetSummaryData != null ? budgetSummaryData.getparticipantCount() : 0;
    }

    private SummaryDataType getCumulativeEquipments(BudgetSummaryDto budgetSummaryData) {

        SummaryDataType summary = SummaryDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getCumEquipmentFunds() != null) {
                summary.setFederalSummary(budgetSummaryData.getCumEquipmentFunds().bigDecimalValue());
            }
            if (budgetSummaryData.getCumEquipmentNonFunds() != null) {
                summary.setNonFederalSummary(budgetSummaryData.getCumEquipmentNonFunds().bigDecimalValue());
                if (budgetSummaryData.getCumEquipmentFunds() != null) {
                    summary.setTotalFedNonFedSummary(budgetSummaryData.getCumEquipmentFunds().add(
                            budgetSummaryData.getCumEquipmentNonFunds()).bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(budgetSummaryData.getCumEquipmentNonFunds().bigDecimalValue());
                }
            }

        }
        return summary;
    }

    private SummaryDataType getCumulativeTravels(BudgetSummaryDto budgetSummaryData) {
        SummaryDataType summary = SummaryDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getCumTravel() != null) {
                summary.setFederalSummary(budgetSummaryData.getCumTravel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumTravelNonFund() != null) {
                summary.setNonFederalSummary(budgetSummaryData.getCumTravelNonFund().bigDecimalValue());
                if (budgetSummaryData.getCumTravel() != null) {
                    summary.setTotalFedNonFedSummary(budgetSummaryData.getCumTravel().add(budgetSummaryData.getCumTravelNonFund())
                            .bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(budgetSummaryData.getCumTravelNonFund().bigDecimalValue());
                }
            }
        }

        return summary;
    }

    private TotalDataType getCumulativeDomesticTravelCosts(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalDomestic = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getCumDomesticTravel() != null) {
                totalDomestic.setFederal(budgetSummaryData.getCumDomesticTravel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumDomesticTravelNonFund() != null) {
                totalDomestic.setNonFederal(budgetSummaryData.getCumDomesticTravelNonFund().bigDecimalValue());
                if (budgetSummaryData.getCumDomesticTravel() != null) {
                    totalDomestic.setTotalFedNonFed(budgetSummaryData.getCumDomesticTravel().add(
                            budgetSummaryData.getCumDomesticTravelNonFund()).bigDecimalValue());
                }
                else {
                    totalDomestic.setTotalFedNonFed(budgetSummaryData.getCumDomesticTravelNonFund().bigDecimalValue());
                }
            }
        }
        return totalDomestic;
    }

    private TotalDataType getCumulativeForeignTravelCosts(BudgetSummaryDto budgetSummaryData) {
        TotalDataType totalForeign = TotalDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getCumForeignTravel() != null) {
                totalForeign.setFederal(budgetSummaryData.getCumForeignTravel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumForeignTravelNonFund() != null) {
                totalForeign.setNonFederal(budgetSummaryData.getCumForeignTravelNonFund().bigDecimalValue());
                if (budgetSummaryData.getCumForeignTravel() != null) {
                    totalForeign.setTotalFedNonFed(budgetSummaryData.getCumForeignTravel().add(
                            budgetSummaryData.getCumForeignTravelNonFund()).bigDecimalValue());
                }
                else {
                    totalForeign.setTotalFedNonFed(budgetSummaryData.getCumForeignTravelNonFund().bigDecimalValue());
                }
            }
        }
        return totalForeign;
    }

    private AttachedFileDataType getBudgetJustificationAttachment() {
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && Integer.parseInt(narrative.getNarrativeType().getCode()) == BUDGET_JUSTIFICATION_ATTACHMENT) {
                AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null){
                    return attachedFileDataType;
                }
            }
        }
        return null;
    }

    private IndirectCosts getIndirectCosts(BudgetPeriodDto periodInfo) {

        IndirectCosts indirectCosts = null;
        if (periodInfo != null && periodInfo.getIndirectCosts() != null
                && periodInfo.getIndirectCosts().getIndirectCostDetails() != null) {

            int IndirectCostCount = 0;
            List<IndirectCosts.IndirectCost> indirectCostList = new ArrayList<>();
            for (IndirectCostDetailsDto indirectCostDetails : periodInfo.getIndirectCosts().getIndirectCostDetails()) {
                IndirectCosts.IndirectCost indirectCost = IndirectCosts.IndirectCost.Factory.newInstance();
                indirectCost.setCostType(indirectCostDetails.getCostType());
                if (indirectCostDetails.getBase() != null) {
                    indirectCost.setBase(indirectCostDetails.getBase().bigDecimalValue());
                }
                if (indirectCostDetails.getRate() != null) {
                    indirectCost.setRate(indirectCostDetails.getRate().bigDecimalValue());
                }
                TotalDataType total = TotalDataType.Factory.newInstance();
                if (indirectCostDetails.getFunds() != null) {
                    total.setFederal(indirectCostDetails.getFunds().bigDecimalValue());
                }
                else{
                    total.setFederal(new BigDecimal(0.00));
                }
                if (indirectCostDetails.getCostSharing() != null) {
                    total.setNonFederal(indirectCostDetails.getCostSharing().bigDecimalValue());
                    if (indirectCostDetails.getFunds() != null) {
                        total.setTotalFedNonFed(indirectCostDetails.getFunds().add(indirectCostDetails.getCostSharing())
                                .bigDecimalValue());
                    }
                    else {
                        total.setTotalFedNonFed(indirectCostDetails.getCostSharing().bigDecimalValue());
                    }
                }
                else if(indirectCostDetails.getFunds() != null){
                    total.setTotalFedNonFed(indirectCostDetails.getFunds().bigDecimalValue());
                    total.setNonFederal(new BigDecimal(0.00));
                }
                else{
                    total.setNonFederal(new BigDecimal(0.00));
                    total.setTotalFedNonFed(new BigDecimal(0.00));
                }
                indirectCost.setFundRequested(total);
                indirectCostList.add(indirectCost);
                IndirectCostCount++;
                if (IndirectCostCount == ARRAY_LIMIT_IN_SCHEMA) {
                    LOG.warn("Stopping iteration over indirect cost details because array limit in schema is only 4");
                    break;
                }
            }
            if (IndirectCostCount > 0) {
                indirectCosts = IndirectCosts.Factory.newInstance();
                IndirectCosts.IndirectCost indirectCostArray[] = new IndirectCosts.IndirectCost[0];
                indirectCosts.setIndirectCostArray(indirectCostList.toArray(indirectCostArray));
                SummaryDataType summary = SummaryDataType.Factory.newInstance();
                if (periodInfo.getIndirectCosts().getTotalIndirectCosts() != null) {
                    summary.setFederalSummary(periodInfo.getIndirectCosts().getTotalIndirectCosts().bigDecimalValue());
                }
                if (periodInfo.getIndirectCosts().getTotalIndirectCostSharing() != null) {
                    summary.setNonFederalSummary(periodInfo.getIndirectCosts().getTotalIndirectCostSharing().bigDecimalValue());
                    if (periodInfo.getIndirectCosts().getTotalIndirectCosts() != null) {
                        summary.setTotalFedNonFedSummary(periodInfo.getIndirectCosts().getTotalIndirectCosts().add(
                                periodInfo.getIndirectCosts().getTotalIndirectCostSharing()).bigDecimalValue());
                    }
                    else {
                        summary.setTotalFedNonFedSummary(periodInfo.getIndirectCosts().getTotalIndirectCostSharing()
                                .bigDecimalValue());
                    }
                }
                indirectCosts.setTotalIndirectCosts(summary);
            }
        }
        return indirectCosts;
    }

    private OtherDirectCosts getOtherDirectCosts(BudgetPeriodDto periodInfo) {

        OtherDirectCosts otherDirectCosts = OtherDirectCosts.Factory.newInstance();
        TotalDataType totalMaterials = TotalDataType.Factory.newInstance();
        if (periodInfo != null && periodInfo.getOtherDirectCosts() != null && periodInfo.getOtherDirectCosts().size() > 0) {
            if (periodInfo.getOtherDirectCosts().get(0).getmaterials() != null) {
                totalMaterials.setFederal(periodInfo.getOtherDirectCosts().get(0).getmaterials().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getMaterialsCostSharing() != null) {
                totalMaterials.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getMaterialsCostSharing().bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getmaterials() != null) {
                    totalMaterials.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getmaterials().add(
                            periodInfo.getOtherDirectCosts().get(0).getMaterialsCostSharing()).bigDecimalValue());
                }
                else {
                    totalMaterials.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getMaterialsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setMaterialsSupplies(totalMaterials);
            TotalDataType totalPublication = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getpublications() != null) {
                totalPublication.setFederal(periodInfo.getOtherDirectCosts().get(0).getpublications().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getPublicationsCostSharing() != null) {
                totalPublication.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getPublicationsCostSharing()
                        .bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getpublications() != null) {
                    totalPublication.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getpublications().add(
                            periodInfo.getOtherDirectCosts().get(0).getPublicationsCostSharing()).bigDecimalValue());
                }
                else {
                    totalPublication.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getPublicationsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setPublicationCosts(totalPublication);
            TotalDataType totalConsultant = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getConsultants() != null) {
                totalConsultant.setFederal(periodInfo.getOtherDirectCosts().get(0).getConsultants().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getConsultantsCostSharing() != null) {
                totalConsultant
                        .setNonFederal(periodInfo.getOtherDirectCosts().get(0).getConsultantsCostSharing().bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getConsultants() != null) {
                    totalConsultant.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getConsultants().add(
                            periodInfo.getOtherDirectCosts().get(0).getConsultantsCostSharing()).bigDecimalValue());
                }
                else {
                    totalConsultant.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getConsultantsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setConsultantServices(totalConsultant);
            TotalDataType totalADP = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getcomputer() != null) {
                totalADP.setFederal(periodInfo.getOtherDirectCosts().get(0).getcomputer().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getComputerCostSharing() != null) {
                totalADP.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getComputerCostSharing().bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getcomputer() != null) {
                    totalADP.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getcomputer().add(
                            periodInfo.getOtherDirectCosts().get(0).getComputerCostSharing()).bigDecimalValue());
                }
                else {
                    totalADP.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getComputerCostSharing().bigDecimalValue());
                }
            }
            otherDirectCosts.setADPComputerServices(totalADP);
            TotalDataType totalSubaward = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getsubAwards() != null) {
                totalSubaward.setFederal(periodInfo.getOtherDirectCosts().get(0).getsubAwards().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getSubAwardsCostSharing() != null) {
                totalSubaward.setNonFederal((periodInfo.getOtherDirectCosts().get(0).getSubAwardsCostSharing().bigDecimalValue()));
                if (periodInfo.getOtherDirectCosts().get(0).getsubAwards() != null) {
                    totalSubaward.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getsubAwards().add(
                            periodInfo.getOtherDirectCosts().get(0).getSubAwardsCostSharing()).bigDecimalValue());
                }
                else {
                    totalSubaward.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getSubAwardsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setSubawardConsortiumContractualCosts(totalSubaward);
            TotalDataType totalEquipment = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getEquipRental() != null) {
                totalEquipment.setFederal(periodInfo.getOtherDirectCosts().get(0).getEquipRental().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getEquipRentalCostSharing() != null) {
                totalEquipment.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getEquipRentalCostSharing().bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getEquipRental() != null) {
                    totalEquipment.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getEquipRental().add(
                            periodInfo.getOtherDirectCosts().get(0).getEquipRentalCostSharing()).bigDecimalValue());
                }
                else {
                    totalEquipment.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getEquipRentalCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setEquipmentRentalFee(totalEquipment);
            TotalDataType totalAlterations = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getAlterations() != null) {
                totalAlterations.setFederal(periodInfo.getOtherDirectCosts().get(0).getAlterations().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getAlterationsCostSharing() != null) {
                totalAlterations.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getAlterationsCostSharing()
                        .bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getAlterations() != null) {
                    totalAlterations.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getAlterations().add(
                            periodInfo.getOtherDirectCosts().get(0).getAlterationsCostSharing()).bigDecimalValue());
                }
                else {
                    totalAlterations.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getAlterationsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setAlterationsRenovations(totalAlterations);
            otherDirectCosts.setOthers(getOthersForOtherDirectCosts(periodInfo));
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).gettotalOtherDirect() != null) {
                summary.setFederalSummary(periodInfo.getOtherDirectCosts().get(0).gettotalOtherDirect().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getTotalOtherDirectCostSharing() != null) {
                summary.setNonFederalSummary(periodInfo.getOtherDirectCosts().get(0).getTotalOtherDirectCostSharing()
                        .bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).gettotalOtherDirect() != null) {
                    summary.setTotalFedNonFedSummary(periodInfo.getOtherDirectCosts().get(0).gettotalOtherDirect().add(
                            periodInfo.getOtherDirectCosts().get(0).getTotalOtherDirectCostSharing()).bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(periodInfo.getOtherDirectCosts().get(0).getTotalOtherDirectCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setTotalOtherDirectCost(summary);
        }
        return otherDirectCosts;
    }

    private Others getOthersForOtherDirectCosts(BudgetPeriodDto periodInfo) {

        Others othersDirect = Others.Factory.newInstance();
        if (periodInfo != null && periodInfo.getOtherDirectCosts() != null) {
            Others.Other otherArray[] = new Others.Other[periodInfo.getOtherDirectCosts().size()];
            int Otherscount = 0;
            Others.Other other = Others.Other.Factory.newInstance();
            for (OtherDirectCostInfoDto otherDirectCostInfo : periodInfo.getOtherDirectCosts()) {
                TotalDataType total = TotalDataType.Factory.newInstance();
                if (otherDirectCostInfo.getOtherCosts() != null && otherDirectCostInfo.getOtherCosts().size() > 0) {
                    total.setFederal(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0).get(CostConstants.KEY_COST)));
                    total
                            .setNonFederal(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0).get(
                                    CostConstants.KEY_COSTSHARING)));
                    if (otherDirectCostInfo.getOtherCosts().get(0).get(CostConstants.KEY_COST) != null) {
                        total.setTotalFedNonFed(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0)
                                .get(CostConstants.KEY_COST)).add(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0).get(
                                CostConstants.KEY_COSTSHARING))));
                    }
                    else {
                        total.setTotalFedNonFed(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0).get(
                                CostConstants.KEY_COSTSHARING)));
                    }
                }
                other.setCost(total);
                other.setDescription(OTHERCOST_DESCRIPTION);
                otherArray[Otherscount] = other;
                Otherscount++;
            }
            othersDirect.setOtherArray(otherArray);
        }
        return othersDirect;
    }

    private ParticipantTraineeSupportCosts getParticipantTraineeSupportCosts(BudgetPeriodDto periodInfo) {

        ParticipantTraineeSupportCosts traineeSupportCosts = ParticipantTraineeSupportCosts.Factory.newInstance();
        if (periodInfo != null) {
            TotalDataType totalTution = TotalDataType.Factory.newInstance();
            if (periodInfo.getPartTuition() != null) {
                totalTution.setFederal(periodInfo.getPartTuition().bigDecimalValue());
            }
            if (periodInfo.getPartTuitionCostSharing() != null) {
                totalTution.setNonFederal(periodInfo.getPartTuitionCostSharing().bigDecimalValue());
                if (periodInfo.getPartTuition() != null) {
                    totalTution.setTotalFedNonFed(periodInfo.getPartTuition().add(periodInfo.getPartTuitionCostSharing())
                            .bigDecimalValue());
                }
                else {
                    totalTution.setTotalFedNonFed(periodInfo.getPartTuitionCostSharing().bigDecimalValue());
                }
            }
            traineeSupportCosts.setTuitionFeeHealthInsurance(totalTution);
            TotalDataType totalStipends = TotalDataType.Factory.newInstance();
            if (periodInfo.getpartStipendCost() != null) {
                totalStipends.setFederal(periodInfo.getpartStipendCost().bigDecimalValue());
            }
            if (periodInfo.getPartStipendCostSharing() != null) {
                totalStipends.setNonFederal(periodInfo.getPartStipendCostSharing().bigDecimalValue());
                if (periodInfo.getpartStipendCost() != null) {
                    totalStipends.setTotalFedNonFed(periodInfo.getpartStipendCost().add(periodInfo.getPartStipendCostSharing())
                            .bigDecimalValue());
                }
                else {
                    totalStipends.setTotalFedNonFed(periodInfo.getPartStipendCostSharing().bigDecimalValue());
                }
            }
            traineeSupportCosts.setStipends(totalStipends);
            TotalDataType totalTravel = TotalDataType.Factory.newInstance();
            if (periodInfo.getpartTravelCost() != null) {
                totalTravel.setFederal(periodInfo.getpartTravelCost().bigDecimalValue());
            }
            if (periodInfo.getPartTravelCostSharing() != null) {
                totalTravel.setNonFederal(periodInfo.getPartTravelCostSharing().bigDecimalValue());
                if (periodInfo.getpartTravelCost() != null) {
                    totalTravel.setTotalFedNonFed(periodInfo.getpartTravelCost().add(periodInfo.getPartTravelCostSharing())
                            .bigDecimalValue());
                }
                else {
                    totalTravel.setTotalFedNonFed(periodInfo.getPartTravelCostSharing().bigDecimalValue());
                }
            }
            traineeSupportCosts.setParticipantTravel(totalTravel);
            TotalDataType totalSubsistence = TotalDataType.Factory.newInstance();
            if (periodInfo.getPartSubsistence() != null) {
                totalSubsistence.setFederal(periodInfo.getPartSubsistence().bigDecimalValue());
            }
            if (periodInfo.getPartSubsistenceCostSharing() != null) {
                totalSubsistence.setNonFederal(periodInfo.getPartSubsistenceCostSharing().bigDecimalValue());
                if (periodInfo.getPartSubsistence() != null) {
                    totalSubsistence.setTotalFedNonFed(periodInfo.getPartSubsistence().add(
                            periodInfo.getPartSubsistenceCostSharing()).bigDecimalValue());
                }
                else {
                    totalSubsistence.setTotalFedNonFed(periodInfo.getPartSubsistenceCostSharing().bigDecimalValue());
                }
            }
            traineeSupportCosts.setSubsistence(totalSubsistence);
            traineeSupportCosts.setOther(getOtherPTSupportCosts(periodInfo));
            traineeSupportCosts.setParticipantTraineeNumber(periodInfo.getparticipantCount());
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            summary.setFederalSummary(periodInfo.getpartOtherCost().add(
                    periodInfo.getpartStipendCost().add(
                            periodInfo.getpartTravelCost().add(periodInfo.getPartSubsistence().add(periodInfo.getPartTuition()))))
                    .bigDecimalValue());

            summary.setNonFederalSummary(periodInfo.getPartOtherCostSharing().add(
                    periodInfo.getPartStipendCostSharing().add(
                            periodInfo.getPartTravelCostSharing().add(
                                    periodInfo.getPartSubsistenceCostSharing().add(periodInfo.getPartTuitionCostSharing()))))
                    .bigDecimalValue());
            if (summary.getNonFederalSummary() != null) {
                if (summary.getFederalSummary() != null) {
                    summary.setTotalFedNonFedSummary(summary.getFederalSummary().add(summary.getNonFederalSummary()));
                }
                else {
                    summary.setTotalFedNonFedSummary(summary.getNonFederalSummary());
                }
            }
            traineeSupportCosts.setTotalCost(summary);
        }
        return traineeSupportCosts;
    }

    private Other getOtherPTSupportCosts(BudgetPeriodDto periodInfo) {

        Other other = Other.Factory.newInstance();
        other.setDescription(OTHERCOST_DESCRIPTION);
        TotalDataType total = TotalDataType.Factory.newInstance();
        if (periodInfo != null) {
            if (periodInfo.getpartOtherCost() != null) {
                total.setFederal(periodInfo.getpartOtherCost().bigDecimalValue());
            }
            if (periodInfo.getPartOtherCostSharing() != null) {
                total.setNonFederal(periodInfo.getPartOtherCostSharing().bigDecimalValue());
                if (periodInfo.getpartOtherCost() != null) {
                    total.setTotalFedNonFed(periodInfo.getpartOtherCost().add(periodInfo.getPartOtherCostSharing())
                            .bigDecimalValue());
                }
                else {
                    total.setTotalFedNonFed(periodInfo.getPartOtherCostSharing().bigDecimalValue());
                }
            }
        }
        other.setCost(total);
        return other;
    }

    private Equipment getEquipment(BudgetPeriodDto periodInfo) {
        Equipment equipment = Equipment.Factory.newInstance();
        EquipmentList[] equipmentArray = new EquipmentList[0];
        List<EquipmentList> equipmentArrayList = new ArrayList<>();
        if (periodInfo.getEquipment() != null && periodInfo.getEquipment().size() > 0) {
                SummaryDataType totalFund = SummaryDataType.Factory.newInstance();
                totalFund.setFederalSummary(BigDecimal.ZERO);
                totalFund.setNonFederalSummary(BigDecimal.ZERO);
                totalFund.setTotalFedNonFedSummary(BigDecimal.ZERO);
                for (CostDto costInfo : periodInfo.getEquipment().get(0).getEquipmentList()) {
                    EquipmentList equipmentList = EquipmentList.Factory.newInstance();
                    equipmentList.setEquipmentItem(costInfo.getDescription());

                    TotalDataType fundsRequested = TotalDataType.Factory.newInstance();
                    fundsRequested.setFederal(costInfo.getCost().bigDecimalValue());
                    fundsRequested.setNonFederal(costInfo.getCostSharing().bigDecimalValue());
                    fundsRequested.setTotalFedNonFed(costInfo.getCost().add(costInfo.getCostSharing()).bigDecimalValue());

                    //  prepare the totals
                    totalFund.setFederalSummary(totalFund.getFederalSummary().add(costInfo.getCost().bigDecimalValue()));
                    totalFund.setNonFederalSummary(totalFund.getNonFederalSummary()
                            .add(costInfo.getCostSharing().bigDecimalValue()));

                    equipmentList.setFundsRequested(fundsRequested);
                    equipmentArrayList.add(equipmentList);
                }
                totalFund.setTotalFedNonFedSummary(totalFund.getFederalSummary().add(totalFund.getNonFederalSummary()));
                equipmentArray = equipmentArrayList.toArray(equipmentArray);
                equipment.setEquipmentListArray(equipmentArray);
                equipment.setTotalFund(totalFund);
                // Extra Exuipment Amount Setting.
                EquipmentDto equipmentInfo = periodInfo.getEquipment().get(0);
                TotalDataType totalFundForExtraEquipment = TotalDataType.Factory.newInstance();
                totalFundForExtraEquipment.setFederal(equipmentInfo.getTotalExtraFund().bigDecimalValue());
                totalFundForExtraEquipment.setNonFederal(equipmentInfo.getTotalExtraNonFund().bigDecimalValue());
                if (equipmentInfo.getTotalExtraFund() != null) {
                    totalFundForExtraEquipment.setTotalFedNonFed(equipmentInfo.getTotalExtraFund().add(equipmentInfo.getTotalExtraNonFund())
                            .bigDecimalValue());
                }
                else {
                    totalFundForExtraEquipment.setTotalFedNonFed(equipmentInfo.getTotalExtraNonFund().bigDecimalValue());
                }
                equipment.setTotalFundForAttachedEquipment(totalFundForExtraEquipment);
                // Save Total Fund.
                SummaryDataType summary = SummaryDataType.Factory.newInstance();
                if (equipmentInfo.getTotalFund() != null) {
                    summary.setFederalSummary(equipmentInfo.getTotalFund().bigDecimalValue());
                }
                if (equipmentInfo.getTotalNonFund() != null) {
                    summary.setNonFederalSummary(equipmentInfo.getTotalNonFund().bigDecimalValue());
                    if (equipmentInfo.getTotalFund() != null) {
                        summary.setTotalFedNonFedSummary(equipmentInfo.getTotalFund().add(equipmentInfo.getTotalNonFund())
                                .bigDecimalValue());
                    }
                    else {
                        summary.setTotalFedNonFedSummary(equipmentInfo.getTotalNonFund().bigDecimalValue());
                    }
                }
                equipment.setTotalFund(summary);


        }
        NarrativeContract narrative = saveExtraEquipment(periodInfo);
        if(narrative!=null){
            AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
            if(attachedFileDataType != null){
                equipment.setAdditionalEquipmentsAttachment(attachedFileDataType);
            }
        }
        return equipment;
    }

    private NarrativeContract saveExtraEquipment(BudgetPeriodDto periodInfo) {
        NarrativeContract narrative=null;
        List<CostDto> extraEquipmentList = periodInfo.getEquipment().get(0).getExtraEquipmentList();
        if (extraEquipmentList.size() > 0) {
            AdditionalEquipmentList additionalEquipmentList = AdditionalEquipmentList.Factory
                    .newInstance();
            additionalEquipmentList.setProposalNumber(pdDoc
                    .getDevelopmentProposal().getProposalNumber());
            additionalEquipmentList.setBudgetPeriod(new BigInteger(Integer
                    .toString(periodInfo.getBudgetPeriod())));

            additionalEquipmentList
                    .setEquipmentListArray(getEquipmentListArray(extraEquipmentList));

            AdditionalEquipmentListDocument additionalEquipmentDoc = AdditionalEquipmentListDocument.Factory
                    .newInstance();
            additionalEquipmentDoc
                    .setAdditionalEquipmentList(additionalEquipmentList);
            final Source xsltSource;
            try {
                xsltSource =  new StreamSource(additionalEquipmentAttachmentNonFedStyleSheet.getInputStream());
            } catch(IOException e) {
                throw new RuntimeException("the stream could not be opened",e);
            }
            Map<String, Source> xSLTemplateWithBookmarks = new HashMap<>();
            xSLTemplateWithBookmarks.put("", xsltSource);

            String xmlData = additionalEquipmentDoc.xmlText();
            Map<String, byte[]> streamMap = new HashMap<>();
            streamMap.put("", xmlData.getBytes());
            GenericPrintable printable = new GenericPrintable();
            printable.setXSLTemplateWithBookmarks(xSLTemplateWithBookmarks);
            printable.setStreamMap(streamMap);
            try {
                KcFile printData = s2SPrintingService
                        .print(printable);
                String fileName = pdDoc.getDevelopmentProposal()
                        .getProposalNumber()
                        + "_ADDITIONAL_EQUIPMENT.pdf";
                narrative = saveNarrative(printData.getData(),
                        ADDITIONAL_EQUIPMENT_NARRATIVE_TYPE_CODE, fileName,
                        ADDITIONAL_EQUIPMENT_NARRATIVE_COMMENT);
            } catch (S2SException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return narrative;
    }

    private AdditionalEquipmentList.EquipmentList[] getEquipmentListArray(
            List<CostDto> extraEquipmentArrayList) {
        List<AdditionalEquipmentList.EquipmentList> additionalEquipmentListList = new ArrayList<>();

        for (CostDto costInfo : extraEquipmentArrayList) {
            AdditionalEquipmentList.EquipmentList equipmentList = AdditionalEquipmentList.EquipmentList.Factory
                    .newInstance();
            equipmentList.setFundsRequested(costInfo.getCost()
                    .bigDecimalValue());
            equipmentList.setNonFederal(costInfo.getCostSharing().bigDecimalValue());
            equipmentList.setTotalFedNonFed(costInfo.getCost().add(costInfo.getCostSharing()).bigDecimalValue());

            equipmentList
                    .setEquipmentItem(costInfo.getDescription() != null ? costInfo
                            .getDescription()
                            : costInfo.getCategory());
            additionalEquipmentListList.add(equipmentList);
        }
        return additionalEquipmentListList
                .toArray(new AdditionalEquipmentList.EquipmentList[0]);
    }

    private NarrativeContract saveExtraKeyPersons(BudgetPeriodDto periodInfo) {
        NarrativeContract extraKPNarrative = null;
        if (periodInfo.getExtraKeyPersons() != null && !periodInfo.getExtraKeyPersons().isEmpty()) {
            ExtraKeyPersonListDocument  extraKeyPersonListDocument = ExtraKeyPersonListDocument.Factory.newInstance();
            ExtraKeyPersonList extraKeyPersonList = ExtraKeyPersonList.Factory.newInstance();
            extraKeyPersonList.setProposalNumber(pdDoc.getDevelopmentProposal().getProposalNumber());
            extraKeyPersonList.setBudgetPeriod(new BigInteger(""+periodInfo.getBudgetPeriod()));
            extraKeyPersonList.setKeyPersonsArray(getExtraKeyPersons(periodInfo.getExtraKeyPersons()));
            extraKeyPersonListDocument.setExtraKeyPersonList(extraKeyPersonList);
            String xmlData = extraKeyPersonListDocument.xmlText();
            Map<String, byte[]> streamMap = new HashMap<>();
            streamMap.put("", xmlData.getBytes());
            final Source xsltSource;
            try {
                xsltSource =  new StreamSource(extraKeyPersonAttachmentNonFedStyleSheet.getInputStream());
            } catch(IOException e) {
                throw new RuntimeException("the stream could not be opened",e);
            }
            Map<String, Source> xSLTemplateWithBookmarks = new HashMap<>();
            xSLTemplateWithBookmarks.put("", xsltSource);

            GenericPrintable printable = new GenericPrintable();
            printable.setXSLTemplateWithBookmarks(xSLTemplateWithBookmarks);
            printable.setStreamMap(streamMap);
            try {
                KcFile printData = s2SPrintingService.print(printable);
                String fileName = pdDoc.getDevelopmentProposal().getProposalNumber()+"_"+periodInfo.getBudgetPeriod()+"_"+EXTRA_KEYPERSONS+".pdf";
                extraKPNarrative = saveNarrative(printData.getData(), ""+EXTRA_KEYPERSONS_TYPE, fileName, EXTRA_KEYPERSONS_COMMENT);
            } catch (S2SException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return extraKPNarrative;
    }

    private ExtraKeyPersonList.KeyPersons[] getExtraKeyPersons(List<KeyPersonDto> keyPersonList) {
        List<ExtraKeyPersonList.KeyPersons> keypersonslist = new ArrayList<>();
        for(KeyPersonDto keyPersonInfo : keyPersonList){
            ExtraKeyPersonList.KeyPersons keyPerson = ExtraKeyPersonList.KeyPersons.Factory.newInstance();
            keyPerson.setFirstName(keyPersonInfo.getFirstName());
            keyPerson.setMiddleName(keyPersonInfo.getMiddleName());
            keyPerson.setLastName(keyPersonInfo.getLastName());
            keyPerson.setProjectRole(keyPersonInfo.getRole());
            keyPerson.setCompensation(getExtraKeyPersonCompensation(keyPersonInfo));
            keypersonslist.add(keyPerson);
        }
        return keypersonslist.toArray(new ExtraKeyPersonList.KeyPersons[0]);
    }

    private Compensation getExtraKeyPersonCompensation(KeyPersonDto keyPersonInfo) {
        Compensation compensation = Compensation.Factory.newInstance();
        compensation.setAcademicMonths(keyPersonInfo.getAcademicMonths().bigDecimalValue());
        compensation.setCalendarMonths(keyPersonInfo.getCalendarMonths().bigDecimalValue());
        compensation.setSummerMonths(keyPersonInfo.getSummerMonths().bigDecimalValue());

         compensation.setBaseSalary(keyPersonInfo.getBaseSalary().bigDecimalValue());
        compensation.setFringeBenefits(keyPersonInfo.getFringe().add(keyPersonInfo.getFringeCostSharing() != null ? keyPersonInfo.getFringeCostSharing() : ScaleTwoDecimal.ZERO).bigDecimalValue());
        compensation.setFundsRequested(keyPersonInfo.getFundsRequested().bigDecimalValue());
        compensation.setRequestedSalary(keyPersonInfo.getRequestedSalary().add(keyPersonInfo.getCostSharingAmount() != null ? keyPersonInfo.getCostSharingAmount() : ScaleTwoDecimal.ZERO).bigDecimalValue());
        compensation.setNonFederal(keyPersonInfo.getNonFundsRequested().bigDecimalValue());
        if (keyPersonInfo.getFundsRequested() != null){
            compensation.setTotalFedNonFed(keyPersonInfo.getFundsRequested().add(keyPersonInfo.getNonFundsRequested()).bigDecimalValue());
        }else {
            compensation.setTotalFedNonFed(keyPersonInfo.getNonFundsRequested().bigDecimalValue());
        }
        
        return compensation;
    }

    private Travel getTravel(BudgetPeriodDto periodInfo) {

        Travel travel = Travel.Factory.newInstance();
        if (periodInfo != null) {
            TotalDataType total = TotalDataType.Factory.newInstance();
            if (periodInfo.getDomesticTravelCost() != null) {
                total.setFederal(periodInfo.getDomesticTravelCost().bigDecimalValue());
            }
            if (periodInfo.getDomesticTravelCostSharing() != null) {
                total.setNonFederal(periodInfo.getDomesticTravelCostSharing().bigDecimalValue());
                if (periodInfo.getDomesticTravelCost() != null) {
                    total.setTotalFedNonFed(periodInfo.getDomesticTravelCost().add(periodInfo.getDomesticTravelCostSharing())
                            .bigDecimalValue());
                }
                else {
                    total.setTotalFedNonFed(periodInfo.getDomesticTravelCostSharing().bigDecimalValue());
                }
            }
            travel.setDomesticTravelCost(total);
            TotalDataType totalForeign = TotalDataType.Factory.newInstance();
            if (periodInfo.getForeignTravelCost() != null) {
                totalForeign.setFederal(periodInfo.getForeignTravelCost().bigDecimalValue());
            }
            if (periodInfo.getForeignTravelCostSharing() != null) {
                totalForeign.setNonFederal(periodInfo.getForeignTravelCostSharing().bigDecimalValue());
                if (periodInfo.getForeignTravelCost() != null) {
                    totalForeign.setTotalFedNonFed(periodInfo.getForeignTravelCost().add(periodInfo.getForeignTravelCostSharing())
                            .bigDecimalValue());
                }
                else {
                    totalForeign.setTotalFedNonFed(periodInfo.getForeignTravelCostSharing().bigDecimalValue());
                }
            }
            travel.setForeignTravelCost(totalForeign);
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalTravelCost() != null) {
                summary.setFederalSummary(periodInfo.getTotalTravelCost().bigDecimalValue());
            }
            if (periodInfo.getTotalTravelCostSharing() != null) {
                summary.setNonFederalSummary(periodInfo.getTotalTravelCostSharing().bigDecimalValue());
                if (periodInfo.getTotalTravelCost() != null) {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalTravelCost().add(periodInfo.getTotalTravelCostSharing())
                            .bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalTravelCostSharing().bigDecimalValue());
                }
            }
            travel.setTotalTravelCost(summary);
        }
        return travel;
    }

    private OtherPersonnel getOtherPersonnel(BudgetPeriodDto periodInfo) {
        OtherPersonnel otherPersonnel = OtherPersonnel.Factory.newInstance();
        int OtherpersonalCount = 0;
        List<OtherPersonnelDataType> otherPersonnelList = new ArrayList<>();
        OtherPersonnelDataType otherPersonnelDataTypeArray[] = new OtherPersonnelDataType[1];
        if (periodInfo != null) {
            if (periodInfo.getOtherPersonnel() != null) {
                for (OtherPersonnelDto otherPersonnelInfo : periodInfo.getOtherPersonnel()) {
                    if (OTHERPERSONNEL_POSTDOC.equals(otherPersonnelInfo.getPersonnelType())) {
                        otherPersonnel.setPostDocAssociates(getPostDocAssociates(otherPersonnelInfo));
                    } else if (OTHERPERSONNEL_GRADUATE.equals(otherPersonnelInfo.getPersonnelType())) {
                        otherPersonnel.setGraduateStudents(getGraduateStudents(otherPersonnelInfo));
                    } else if (OTHERPERSONNEL_UNDERGRADUATE.equals(otherPersonnelInfo.getPersonnelType())) {
                        otherPersonnel.setUndergraduateStudents(getUndergraduateStudents(otherPersonnelInfo));
                    } else if (OTHERPERSONNEL_SECRETARIAL.equals(otherPersonnelInfo.getPersonnelType())) {
                        otherPersonnel.setSecretarialClerical(getSecretarialClerical(otherPersonnelInfo));
                    } else if (OtherpersonalCount < OTHERPERSONNEL_MAX_ALLOWED) {// Max allowed is 6
                        final CompensationDto compensation = otherPersonnelInfo.getCompensation();

                        OtherPersonnelDataType otherPersonnelDataType = OtherPersonnelDataType.Factory.newInstance();
                        otherPersonnelDataType.setNumberOfPersonnel(otherPersonnelInfo.getNumberPersonnel());
                        otherPersonnelDataType.setProjectRole(otherPersonnelInfo.getRole());

                        if (compensation != null) {
                            if (compensation.getAcademicMonths() != null) {
                                otherPersonnelDataType.setAcademicMonths(compensation.getAcademicMonths().bigDecimalValue());
                            }

                            if (compensation.getCalendarMonths() != null) {
                                otherPersonnelDataType.setCalendarMonths(compensation.getCalendarMonths().bigDecimalValue());
                            }

                            if (compensation.getSummerMonths() != null) {
                                otherPersonnelDataType.setSummerMonths(compensation.getSummerMonths().bigDecimalValue());
                            }

                            if (compensation.getFringe() != null) {
                                otherPersonnelDataType.setFringeBenefits(compensation.getFringe().add(compensation.getFringeCostSharing() != null ? compensation.getFringeCostSharing() : ScaleTwoDecimal.ZERO).bigDecimalValue());
                            }

                            if (compensation.getRequestedSalary() != null) {
                                otherPersonnelDataType.setRequestedSalary(compensation.getRequestedSalary().add(compensation.getCostSharingAmount() != null ? compensation.getCostSharingAmount() : ScaleTwoDecimal.ZERO).bigDecimalValue());
                            }

                            otherPersonnelDataType.setOtherTotal(getCompensationTotalDataType(compensation));

                        }

                        otherPersonnelList.add(otherPersonnelDataType);
                        OtherpersonalCount++;
                    }
                }
                otherPersonnelDataTypeArray = otherPersonnelList.toArray(otherPersonnelDataTypeArray);
                otherPersonnel.setOtherArray(otherPersonnelDataTypeArray);

                if (periodInfo.getOtherPersonnelTotalNumber() != null) {
                    otherPersonnel.setOtherPersonnelTotalNumber(periodInfo.getOtherPersonnelTotalNumber().intValue());
                }
            }
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalOtherPersonnelFunds() != null) {
                summary.setFederalSummary(periodInfo.getTotalOtherPersonnelFunds().bigDecimalValue());
            }
            if (periodInfo.getTotalOtherPersonnelNonFunds() != null) {
                if (budget.getSubmitCostSharingFlag()) {
                    summary.setNonFederalSummary(periodInfo.getTotalOtherPersonnelNonFunds().bigDecimalValue());  
                    if (periodInfo.getTotalOtherPersonnelFunds() != null) {
                        summary.setTotalFedNonFedSummary(periodInfo.getTotalOtherPersonnelFunds().add(
                                periodInfo.getTotalOtherPersonnelNonFunds()).bigDecimalValue());
                    } else {
                        summary.setTotalFedNonFedSummary(periodInfo.getTotalOtherPersonnelNonFunds().bigDecimalValue());
                    }
                } else {
                    summary.setNonFederalSummary(BigDecimal.ZERO);
                    if (periodInfo.getTotalOtherPersonnelFunds() != null) {
                        summary.setTotalFedNonFedSummary(periodInfo.getTotalOtherPersonnelFunds().bigDecimalValue());
                    } else {
                        summary.setTotalFedNonFedSummary(BigDecimal.ZERO);
                    }
                }
            }
            otherPersonnel.setTotalOtherPersonnelFund(summary);
        }
        return otherPersonnel;
    }

    private PostDocAssociates getPostDocAssociates(OtherPersonnelDto otherPersonnel) {

        PostDocAssociates postDoc = PostDocAssociates.Factory.newInstance();
        if (otherPersonnel != null) {
            postDoc.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            postDoc.setProjectRole(otherPersonnel.getRole());
            postDoc.setCompensation(getSectBCompensationDataType(otherPersonnel.getCompensation()));
        }
        return postDoc;
    }

    private GraduateStudents getGraduateStudents(OtherPersonnelDto otherPersonnel) {

        GraduateStudents graduate = GraduateStudents.Factory.newInstance();
        if (otherPersonnel != null) {
            graduate.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            graduate.setProjectRole(otherPersonnel.getRole());
            graduate.setCompensation(getSectBCompensationDataType(otherPersonnel.getCompensation()));
        }
        return graduate;
    }

    private UndergraduateStudents getUndergraduateStudents(OtherPersonnelDto otherPersonnel) {

        UndergraduateStudents undergraduate = UndergraduateStudents.Factory.newInstance();
        if (otherPersonnel != null) {
            undergraduate.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            undergraduate.setProjectRole(otherPersonnel.getRole());
            undergraduate.setCompensation(getSectBCompensationDataType(otherPersonnel.getCompensation()));
        }
        return undergraduate;
    }

    private SecretarialClerical getSecretarialClerical(OtherPersonnelDto otherPersonnel) {

        SecretarialClerical secretarial = SecretarialClerical.Factory.newInstance();
        if (otherPersonnel != null) {
            secretarial.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            secretarial.setProjectRole(otherPersonnel.getRole());
            secretarial.setCompensation(getSectBCompensationDataType(otherPersonnel.getCompensation()));
        }
        return secretarial;
    }

    private SectBCompensationDataType getSectBCompensationDataType(CompensationDto compensation) {

        SectBCompensationDataType sectBCompensation = SectBCompensationDataType.Factory.newInstance();
        if (compensation != null) {
            if (compensation.getAcademicMonths() != null) {
                sectBCompensation.setAcademicMonths(compensation.getAcademicMonths().bigDecimalValue());
            }
            if (compensation.getCalendarMonths() != null) {
                sectBCompensation.setCalendarMonths(compensation.getCalendarMonths().bigDecimalValue());
            }
            if (compensation.getSummerMonths() != null) {
                sectBCompensation.setSummerMonths(compensation.getSummerMonths().bigDecimalValue());
            }
            if (compensation.getFringe() != null) {
                sectBCompensation.setFringeBenefits(compensation.getFringe().add(compensation.getFringeCostSharing() != null ? compensation.getFringeCostSharing() : ScaleTwoDecimal.ZERO).bigDecimalValue());
            }
            if (compensation.getRequestedSalary() != null) {
                sectBCompensation.setRequestedSalary(compensation.getRequestedSalary().add(compensation.getCostSharingAmount() != null ? compensation.getCostSharingAmount() : ScaleTwoDecimal.ZERO).bigDecimalValue());
            }

            sectBCompensation.setOtherTotal(getCompensationTotalDataType(compensation));
        }
        return sectBCompensation;
    }

    private TotalDataType getCompensationTotalDataType(CompensationDto compensation) {
        TotalDataType totalDataType = TotalDataType.Factory.newInstance();
        if (compensation.getFundsRequested() != null) {
            totalDataType.setFederal(compensation.getFundsRequested().bigDecimalValue());
        }
        if (compensation.getNonFundsRequested() != null) {
            if (budget.getSubmitCostSharingFlag()) {
                totalDataType.setNonFederal(compensation.getNonFundsRequested().bigDecimalValue());
                if (compensation.getFundsRequested() != null && compensation.getNonFundsRequested() != null) {
                    totalDataType.setTotalFedNonFed(compensation.getFundsRequested().add(compensation.getNonFundsRequested())
                            .bigDecimalValue());
                }
            } else {
                totalDataType.setNonFederal(BigDecimal.ZERO);
                if (compensation.getFundsRequested() != null && compensation.getNonFundsRequested() != null) {
                    totalDataType.setTotalFedNonFed(compensation.getFundsRequested().bigDecimalValue());
                }
            }
        }
        return totalDataType;
    }

    private KeyPersons getKeyPersons(BudgetPeriodDto periodInfo) {
        KeyPersons keyPersons = KeyPersons.Factory.newInstance();
        if (periodInfo != null) {
            if (periodInfo.getKeyPersons() != null) {
                List<KeyPersonDataType> keyPersonList = new ArrayList<>();
                int keyPersonCount = 0;
                for (KeyPersonDto keyPerson : periodInfo.getKeyPersons()) {
                  if(keyPerson.getRole().equals(NID_PD_PI) || hasPersonnelBudget(keyPerson,periodInfo.getBudgetPeriod())){
                    KeyPersonDataType keyPersonDataType = KeyPersonDataType.Factory.newInstance();
                    keyPersonDataType.setName(globLibV20Generator.getHumanNameDataType(keyPerson));
                    if(keyPerson.getKeyPersonRole()!=null){
                        keyPersonDataType.setProjectRole(keyPerson.getKeyPersonRole());
                    }
                    else {
                        keyPersonDataType.setProjectRole(keyPerson.getRole());
                    }
                    keyPersonDataType.setCompensation(getCompensation(keyPerson, periodInfo.getBudgetPeriod()));
                    keyPersonList.add(keyPersonDataType);
                    keyPersonCount++;
                    LOG.info("keyPersonCount:" + keyPersonCount);
                }
                }
                keyPersons.setKeyPersonArray(keyPersonList.toArray(new KeyPersonDataType[0]));
            }
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalFundsKeyPersons() != null) {
                summary.setFederalSummary(periodInfo.getTotalFundsKeyPersons().bigDecimalValue());
            }
            if (periodInfo.getTotalNonFundsKeyPersons() != null) {
                summary.setNonFederalSummary(periodInfo.getTotalNonFundsKeyPersons().bigDecimalValue());
                if (periodInfo.getTotalFundsKeyPersons() != null) {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalFundsKeyPersons().add(
                            periodInfo.getTotalNonFundsKeyPersons()).bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalNonFundsKeyPersons().bigDecimalValue());
                }
            }
            keyPersons.setTotalFundForKeyPersons(summary);
            SummaryDataType summaryAttachedKey = SummaryDataType.Factory.newInstance();

            BigDecimal totalFederalSummary = BigDecimal.ZERO;
            BigDecimal totalNonFederalSummary = BigDecimal.ZERO;
            for(KeyPersonDto keyPersonInfo : periodInfo.getExtraKeyPersons()){
                totalFederalSummary = totalFederalSummary.add(keyPersonInfo.getFundsRequested().bigDecimalValue());
                totalNonFederalSummary = totalNonFederalSummary.add(keyPersonInfo.getNonFundsRequested().bigDecimalValue());
            }
            summaryAttachedKey.setFederalSummary(totalFederalSummary);
            summaryAttachedKey.setNonFederalSummary(totalNonFederalSummary);
            summaryAttachedKey.setTotalFedNonFedSummary(totalFederalSummary.add(totalNonFederalSummary));
            keyPersons.setTotalFundForAttachedKeyPersons(summaryAttachedKey);
        }
        NarrativeContract extraKeyPersonNarr = saveExtraKeyPersons(periodInfo);

        if(extraKeyPersonNarr!=null){
            AttachedFileDataType attachedFileDataType = getAttachedFileType(extraKeyPersonNarr);
            if(attachedFileDataType != null){
                keyPersons.setAttachedKeyPersons(attachedFileDataType);
            }
        }
        return keyPersons;
    }

    private KeyPersonCompensationDataType getCompensation(KeyPersonDto keyPerson, int budgetPeriod) {

        KeyPersonCompensationDataType keyPersonCompensation = KeyPersonCompensationDataType.Factory.newInstance();
        ScaleTwoDecimal baseSalaryByPeriod;
        if (keyPerson != null) {
            if (keyPerson.getAcademicMonths() != null) {
                keyPersonCompensation.setAcademicMonths(keyPerson.getAcademicMonths().bigDecimalValue());
            }
            if (keyPerson.getCalendarMonths() != null) {
                keyPersonCompensation.setCalendarMonths(keyPerson.getCalendarMonths().bigDecimalValue());
            }
            if (keyPerson.getSummerMonths() != null) {
                keyPersonCompensation.setSummerMonths(keyPerson.getSummerMonths().bigDecimalValue());
            }
            if (keyPerson.getFringe() != null) {
                keyPersonCompensation.setFringeBenefits(keyPerson.getFringe().add(keyPerson.getFringeCostSharing() != null ? keyPerson.getFringeCostSharing() : ScaleTwoDecimal.ZERO).bigDecimalValue());
            }
            if (keyPerson.getRequestedSalary() != null) {
                keyPersonCompensation.setRequestedSalary(keyPerson.getRequestedSalary().add(keyPerson.getCostSharingAmount() != null ? keyPerson.getCostSharingAmount() : ScaleTwoDecimal.ZERO).bigDecimalValue());
            }
            TotalDataType totalDataType = TotalDataType.Factory.newInstance();
            if (keyPerson.getFundsRequested() != null) {
                totalDataType.setFederal(keyPerson.getFundsRequested().bigDecimalValue());
            }
            if (keyPerson.getNonFundsRequested() != null) {
                totalDataType.setNonFederal(keyPerson.getNonFundsRequested().bigDecimalValue());
            }
            if (keyPerson.getFundsRequested() != null && keyPerson.getNonFundsRequested() != null) {
                totalDataType.setTotalFedNonFed(keyPerson.getFundsRequested().add(keyPerson.getNonFundsRequested())
                        .bigDecimalValue());
            }
            keyPersonCompensation.setTotal(totalDataType);
            if (pdDoc.getDevelopmentProposal().getBudgets() != null) {
                baseSalaryByPeriod = s2sBudgetCalculatorService.getBaseSalaryByPeriod(pdDoc.getDevelopmentProposal().getBudgets().get(0)
                        .getBudgetId(), budgetPeriod, keyPerson);
                if (baseSalaryByPeriod != null) {
                    keyPersonCompensation.setBaseSalary(baseSalaryByPeriod.bigDecimalValue());
                }
                else {
                    if (keyPerson.getBaseSalary() != null) {
                        keyPersonCompensation.setBaseSalary(keyPerson.getBaseSalary().bigDecimalValue());
                    }
                }
            }
            else {
                if (keyPerson.getBaseSalary() != null) {
                    keyPersonCompensation.setBaseSalary(keyPerson.getBaseSalary().bigDecimalValue());
                }
            }
        }
        return keyPersonCompensation;
    }

    @Override
    public RRFedNonFedBudget12Document getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRFedNonFedBudget();
    }

    public S2SPrintingService getS2SPrintingService() {
        return s2SPrintingService;
    }

    public void setS2SPrintingService(S2SPrintingService s2SPrintingService) {
        this.s2SPrintingService = s2SPrintingService;
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

    public Resource getAdditionalEquipmentAttachmentNonFedStyleSheet() {
        return additionalEquipmentAttachmentNonFedStyleSheet;
    }

    public void setAdditionalEquipmentAttachmentNonFedStyleSheet(Resource additionalEquipmentAttachmentNonFedStyleSheet) {
        this.additionalEquipmentAttachmentNonFedStyleSheet = additionalEquipmentAttachmentNonFedStyleSheet;
    }

    public Resource getExtraKeyPersonAttachmentNonFedStyleSheet() {
        return extraKeyPersonAttachmentNonFedStyleSheet;
    }

    public void setExtraKeyPersonAttachmentNonFedStyleSheet(Resource extraKeyPersonAttachmentNonFedStyleSheet) {
        this.extraKeyPersonAttachmentNonFedStyleSheet = extraKeyPersonAttachmentNonFedStyleSheet;
    }
}
