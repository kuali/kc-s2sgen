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


import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30.ClinicalTrial;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30.IncomeBudgetPeriod;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30.StemCells;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30.VertebrateAnimals;
import gov.grants.apply.system.globalLibraryV20.HumanNameDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.budget.api.income.BudgetProjectIncomeContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.budget.S2SCommonBudgetService;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@FormGenerator("PHS398CoverPageSupplement_3_0V3_0Generator")
public class PHS398CoverPageSupplement_3_0V3_0Generator extends PHS398CoverPageSupplementBaseGenerator {

    protected static final Integer PROPOSAL_YNQ_QUESTION_114 = 114;
    protected static final Integer PROPOSAL_YNQ_QUESTION_115 = 115;
    protected static final Integer PROPOSAL_YNQ_QUESTION_116 = 116;
    protected static final Integer PROPOSAL_YNQ_QUESTION_117 = 117;
    protected static final Integer PROPOSAL_YNQ_QUESTION_118 = 118;
    protected static final Integer PROPOSAL_YNQ_QUESTION_119 = 119;
    protected static final Integer PROPOSAL_YNQ_QUESTION_120 = 120;
    protected static final Integer PROPOSAL_YNQ_QUESTION_145 = 145;
    protected static final Integer PROPOSAL_YNQ_QUESTION_146 = 146;
    protected static final Integer PROPOSAL_YNQ_QUESTION_147 = 147;
    protected static final Integer PROPOSAL_YNQ_QUESTION_148 = 148;
    public static final Integer INCREASED_REGISTRATION_NUMBER = 142;
    protected static final int PROJECT_INCOME_DESCRIPTION_MAX_LENGTH = 150;
    private static final int MAX_EUTHANASIA_METHOD_DESC = 1000;

    private List<? extends AnswerHeaderContract> answerHeaders;

    @Value("http://apply.grants.gov/forms/PHS398_CoverPageSupplement_3_0-V3.0")
    private String namespace;

    @Value("PHS398_CoverPageSupplement_3_0-V3.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PHS398_CoverPageSupplement-V3.0.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398CoverPageSupplement30V30")
    private String packageName;

    @Value("185")
    private int sortIndex;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    @Autowired
    @Qualifier("s2SCommonBudgetService")
    private S2SCommonBudgetService s2SCommonBudgetService;

    private PHS398CoverPageSupplement30Document getCoverPageSupplement() {
        PHS398CoverPageSupplement30Document coverPageSupplementDocument = PHS398CoverPageSupplement30Document.Factory.newInstance();
        PHS398CoverPageSupplement30 coverPageSupplement = PHS398CoverPageSupplement30.Factory.newInstance();
        answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber(), getNamespace(), getFormName());
        coverPageSupplement.setFormVersion(FormVersion.v3_0.getVersion());
        coverPageSupplement.setClinicalTrial(getClinicalTrial());
        setIsInventionsAndPatentsAndIsPreviouslyReported(coverPageSupplement);
        setFormerPDNameAndIsChangeOfPDPI(coverPageSupplement);
        setFormerInstitutionNameAndChangeOfInstitution(coverPageSupplement);
        ProposalDevelopmentBudgetExtContract budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());

        if (budget != null) {
            setIncomeBudgetPeriods(coverPageSupplement, budget.getBudgetProjectIncomes());
        } else {
            coverPageSupplement.setProgramIncome(YesNoDataType.N_NO);
        }

        StemCells stemCells = getStemCells();
        coverPageSupplement.setStemCells(stemCells);

        VertebrateAnimals vertebrateAnimals = getVertebrateAnimals();
        coverPageSupplement.setVertebrateAnimals(vertebrateAnimals);

        coverPageSupplementDocument.setPHS398CoverPageSupplement30(coverPageSupplement);
        return coverPageSupplementDocument;
    }

    private ClinicalTrial getClinicalTrial() {

        ClinicalTrial clinicalTrial = ClinicalTrial.Factory.newInstance();
        String answer = getAnswer(IS_CLINICAL_TRIAL, answerHeaders);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(answer)) {
                clinicalTrial.setIsClinicalTrial(YesNoDataType.Y_YES);
                setClinicalTrialSubQuestions(clinicalTrial);
            } else {
                clinicalTrial.setIsClinicalTrial(YesNoDataType.N_NO);
            }
        }
        return clinicalTrial;
    }

    private void setClinicalTrialSubQuestions(ClinicalTrial clinicalTrial) {

        String subAnswer = getAnswer(PHASE_III_CLINICAL_TRIAL, answerHeaders);
        if (subAnswer != null && !subAnswer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(subAnswer)) {
                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.Y_YES);
            } else {
                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.N_NO);
            }
        } else {
            clinicalTrial.setIsPhaseIIIClinicalTrial(null);
        }
    }

    private VertebrateAnimals getVertebrateAnimals() {
        final VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_145, answerHeaders);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(answer)) {
                setVertebrateAnimalsSubQuestions(vertebrateAnimals);
            }
        }
        return vertebrateAnimals;
    }

    private void setVertebrateAnimalsSubQuestions(VertebrateAnimals vertebrateAnimals) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_146, answerHeaders);
        if (answer != null && YnqConstant.YES.code().equals(answer)) {
            vertebrateAnimals.setAnimalEuthanasiaIndicator(YesNoDataType.Y_YES);
        } else if (answer != null && YnqConstant.NO.code().equals(answer)) {
            vertebrateAnimals.setAnimalEuthanasiaIndicator(YesNoDataType.N_NO);
        }

        answer = getAnswer(PROPOSAL_YNQ_QUESTION_147, answerHeaders);
        if (answer != null && YnqConstant.YES.code().equals(answer)) {
            vertebrateAnimals.setAVMAConsistentIndicator(YesNoDataType.Y_YES);
        } else if (answer != null && YnqConstant.NO.code().equals(answer)) {
            vertebrateAnimals.setAVMAConsistentIndicator(YesNoDataType.N_NO);
            answer = getAnswer(PROPOSAL_YNQ_QUESTION_148, answerHeaders);
            if (answer != null) {
                vertebrateAnimals.setEuthanasiaMethodDescription(StringUtils.substring(answer.trim(), 0, MAX_EUTHANASIA_METHOD_DESC));
            }
        }
    }

    private void setIsInventionsAndPatentsAndIsPreviouslyReported(PHS398CoverPageSupplement30 coverPageSupplement) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_118, answerHeaders);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(answer)) {
                setInventionsAndPatentsAndIsPreviouslyReportedSubQuestions(coverPageSupplement);
            }
        }
    }

    private void setInventionsAndPatentsAndIsPreviouslyReportedSubQuestions(PHS398CoverPageSupplement30 coverPageSupplement) {
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_119, answerHeaders);
        if (explanation != null && !explanation.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(explanation)) {
                coverPageSupplement.setIsInventionsAndPatents(YesNoDataType.Y_YES);
                String subQuestionExplanation = getAnswer(PROPOSAL_YNQ_QUESTION_120, answerHeaders);
                if (subQuestionExplanation != null && !subQuestionExplanation.equals(NOT_ANSWERED)) {
                    if (YnqConstant.YES.code().equals(subQuestionExplanation)) {
                        coverPageSupplement.setIsPreviouslyReported(YesNoDataType.Y_YES);
                    } else if (YnqConstant.NO.code().equals(subQuestionExplanation)) {
                        coverPageSupplement.setIsPreviouslyReported(YesNoDataType.N_NO);
                    }
                } else {
                    coverPageSupplement.setIsPreviouslyReported(null);
                }

            } else if (YnqConstant.NO.code().equals(explanation)) {
                coverPageSupplement.setIsInventionsAndPatents(YesNoDataType.N_NO);
            }
        } else {
            coverPageSupplement.setIsInventionsAndPatents(null);
        }
    }

    private void setFormerPDNameAndIsChangeOfPDPI(PHS398CoverPageSupplement30 coverPageSupplement) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_114, answerHeaders);
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_115, answerHeaders);
        if (YnqConstant.YES.code().equals(answer)) {
            coverPageSupplement.setIsChangeOfPDPI(YesNoDataType.Y_YES);
            if (explanation != null) {
                RolodexContract rolodex = rolodexService.getRolodex(Integer.valueOf(explanation));
                HumanNameDataType formerPDName = globLibV20Generator.getHumanNameDataType(rolodex);
                if (formerPDName != null
                        && formerPDName.getFirstName() != null
                        && formerPDName.getLastName() != null) {
                    coverPageSupplement.setFormerPDName(formerPDName);
                }
            } else {
                coverPageSupplement.setFormerPDName(null);
            }
        } else {
            coverPageSupplement.setIsChangeOfPDPI(YesNoDataType.N_NO);
        }
    }

    private void setFormerInstitutionNameAndChangeOfInstitution(PHS398CoverPageSupplement30 coverPageSupplement) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_116, answerHeaders);
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_117, answerHeaders);

        if (YnqConstant.YES.code().equals(answer)) {
            coverPageSupplement.setIsChangeOfInstitution(YesNoDataType.Y_YES);
            if (explanation != null) {
                coverPageSupplement.setFormerInstitutionName(explanation);
            } else {
                coverPageSupplement.setFormerInstitutionName(null);
            }
        } else {
            coverPageSupplement.setIsChangeOfInstitution(YesNoDataType.N_NO);
        }
    }

    private static void setIncomeBudgetPeriods(PHS398CoverPageSupplement30 coverPageSupplement, List<? extends BudgetProjectIncomeContract> projectIncomes) {
        if (projectIncomes.isEmpty()) {
            coverPageSupplement.setProgramIncome(YesNoDataType.N_NO);
        } else {
            coverPageSupplement.setProgramIncome(YesNoDataType.Y_YES);
        }
        coverPageSupplement.setIncomeBudgetPeriodArray(getIncomeBudgetPeriod(projectIncomes));
    }

    private static IncomeBudgetPeriod[] getIncomeBudgetPeriod(final List<? extends BudgetProjectIncomeContract> projectIncomes) {
        //TreeMap Used to maintain the order of the Budget periods.
        Map<Integer, IncomeBudgetPeriod> incomeBudgetPeriodMap = new TreeMap<>();
        BigDecimal anticipatedAmount;
        for (BudgetProjectIncomeContract projectIncome : projectIncomes) {

            Integer budgetPeriodNumber = projectIncome.getBudgetPeriodNumber();
            IncomeBudgetPeriod incomeBudgPeriod = incomeBudgetPeriodMap
                    .get(budgetPeriodNumber);
            if (incomeBudgPeriod == null) {
                incomeBudgPeriod = IncomeBudgetPeriod.Factory.newInstance();
                incomeBudgPeriod.setBudgetPeriod(budgetPeriodNumber);
                anticipatedAmount = BigDecimal.ZERO;
            } else {
                anticipatedAmount = incomeBudgPeriod.getAnticipatedAmount();
            }
            anticipatedAmount = anticipatedAmount.add(projectIncome
                    .getProjectIncome().bigDecimalValue());
            incomeBudgPeriod.setAnticipatedAmount(anticipatedAmount);
            String description = getProjectIncomeDescription(projectIncome);
            if (description != null) {
                if (incomeBudgPeriod.getSource() != null) {
                    incomeBudgPeriod.setSource(incomeBudgPeriod.getSource()
                            + ";" + description);
                } else {
                    incomeBudgPeriod.setSource(description);
                }
            }
            incomeBudgetPeriodMap.put(budgetPeriodNumber, incomeBudgPeriod);
        }
        return incomeBudgetPeriodMap.values().stream().toArray(IncomeBudgetPeriod[]::new);
    }

    protected static String getProjectIncomeDescription(BudgetProjectIncomeContract projectIncome) {
        return projectIncome.getDescription() != null ? StringUtils.substring(projectIncome.getDescription(), 0, PROJECT_INCOME_DESCRIPTION_MAX_LENGTH) : null;
    }

    private StemCells getStemCells() {

        StemCells stemCells = StemCells.Factory.newInstance();

        String answer = getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED, answerHeaders);

        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(answer)) {
                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.Y_YES);
                setStemCellsSubQuestions(stemCells);
            } else {
                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.N_NO);
            }
        }
        return stemCells;
    }

    private void setStemCellsSubQuestions(StemCells stemCells) {
        String subAnswer = getAnswer(SPECIFIC_STEM_CELL_LINE, answerHeaders);
        String childAnswer = null;
        if (subAnswer != null) {
            if (!subAnswer.equals(NOT_ANSWERED)) {
                if (YnqConstant.YES.code().equals(subAnswer)) {
                    stemCells.setStemCellsIndicator(YesNoDataType.N_NO);
                    childAnswer = getAnswers(INCREASED_REGISTRATION_NUMBER, answerHeaders);
                } else {
                    stemCells.setStemCellsIndicator(YesNoDataType.Y_YES);
                }
            }
        }
        if (childAnswer != null) {
            if (FieldValueConstants.VALUE_UNKNOWN.equalsIgnoreCase(childAnswer)) {
                stemCells.setStemCellsIndicator(YesNoDataType.Y_YES);
            } else {
                List<String> cellLines = getCellLines(childAnswer);
                if (cellLines.size() > 0) {
                    stemCells.setCellLinesArray(cellLines.stream().toArray(String[]::new));
                }
            }
        }
    }

    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getCoverPageSupplement();
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public S2SCommonBudgetService getS2SCommonBudgetService() {
        return s2SCommonBudgetService;
    }

    public void setS2SCommonBudgetService(S2SCommonBudgetService s2SCommonBudgetService) {
        this.s2SCommonBudgetService = s2SCommonBudgetService;
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
