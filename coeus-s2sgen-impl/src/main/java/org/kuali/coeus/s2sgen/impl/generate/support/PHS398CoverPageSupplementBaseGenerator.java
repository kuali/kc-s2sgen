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

import org.kuali.coeus.common.api.ynq.YnqContract;
import org.kuali.coeus.propdev.api.ynq.ProposalYnqContract;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonService;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class has methods that are common to all the versions of
 * PHS398CoverPageSupplement form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398CoverPageSupplementBaseGenerator extends
		S2SBaseFormGenerator {
	
	public static final Integer IS_CLINICAL_TRIAL = 2;
	public static final Integer PHASE_III_CLINICAL_TRIAL = 3;
	public static final Integer IS_HUMAN_STEM_CELLS_INVOLVED = 5;
	public static final Integer SPECIFIC_STEM_CELL_LINE = 6;
	public static final Integer REGISTRATION_NUMBER = 7;
	public static final Integer IS_NEW_INVESTIGATOR = 13;
    protected static final int MAX_NUMBER_OF_DEGREES = 3;
    protected static final int PERSON_DEGREE_MAX_LENGTH = 10;

    @Autowired
    @Qualifier("s2SProposalPersonService")
	protected S2SProposalPersonService s2SProposalPersonService;

    @Autowired
    @Qualifier("departmentalPersonService")
    protected DepartmentalPersonService departmentalPersonService;
	/**
	 * 
	 * This method is used to get the Ynq answer for ProposalYnq
	 * 
	 * @param questionId
	 *            to be checked.
	 * @return proposalYnq corresponding to the question id.
	 */
	protected ProposalYnqContract getProposalYnQ(Integer questionId) {
		ProposalYnqContract ynQ = null;
		for (ProposalYnqContract proposalYnq : pdDoc.getDevelopmentProposal()
				.getProposalYnqs()) {
            YnqContract question = proposalYnq.getYnq();
			if (question != null && question.getQuestionId().equals(questionId.toString())) {
				ynQ = proposalYnq;
			}
		}
		return ynQ;
	}

	/**
	 * This method splits the passed explanation comprising cell line
	 * information, puts into a list and returns the list.
	 * 
	 * @param explanation
	 *            String of cell lines
	 * @return {@link List}
	 */
	protected List<String> getCellLines(String explanation) {
		int startPos = 0;
		List<String> cellLines = new ArrayList<String>();
		for (int commaPos = 0; commaPos > -1;) {
			commaPos = explanation.indexOf(",", startPos);
			if (commaPos >= 0) {
                String cellLine = (explanation.substring(startPos, commaPos).trim());
				explanation = explanation.substring(commaPos + 1);
				if (cellLine.length() > 0) {
					cellLines.add(cellLine);
				}
			} else if (explanation.length() > 0) {
				cellLines.add(explanation.trim());
			}
		}
		return cellLines;
	}

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }

    public DepartmentalPersonService getDepartmentalPersonService() {
        return departmentalPersonService;
    }

    public void setDepartmentalPersonService(DepartmentalPersonService departmentalPersonService) {
        this.departmentalPersonService = departmentalPersonService;
    }
}
