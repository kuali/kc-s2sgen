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

import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * This abstract class has methods that are common to all the versions of EDSF424Supplement form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public abstract class EDSF424SupplementBaseGenerator extends S2SBaseFormGenerator {

    protected static final Integer PROPOSAL_YNQ_NOVICE_APPLICANT = 133;
    protected static final String SPECIAL_REVIEW_CODE = "1";
    protected static final String APPROVAL_TYPE_CODE = "4";
    protected static final int NARRATIVE_TYPE_ED_SF424_SUPPLIMENT = 54;

    @Autowired
    @Qualifier("s2SProposalPersonService")
    protected S2SProposalPersonService s2SProposalPersonService;

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }

    public static String colToString(List<String> stringList) {
        String retVal = "";
        if (stringList != null) {
            for (int i = 0; i < stringList.size(); i++) {
                retVal += stringList.get(i);
                if (i != stringList.size() - 1) {
                    retVal += ", ";
                }
            }
        }
        return retVal;
    }
}
