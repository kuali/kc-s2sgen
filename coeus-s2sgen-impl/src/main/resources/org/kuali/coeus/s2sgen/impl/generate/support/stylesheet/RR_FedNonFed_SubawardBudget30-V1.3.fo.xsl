<?xml version="1.0" encoding="UTF-8"?>
<!--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" 
                xmlns:fo="http://www.w3.org/1999/XSL/Format" 
                xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" 
                xmlns:RR_FedNonFed_SubawardBudget30_1_3="http://apply.grants.gov/forms/RR_FedNonFed_SubawardBudget30_1_3-V1.3"
                xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:RR_FedNonFedBudget_1_2="http://apply.grants.gov/forms/RR_FedNonFedBudget_1_2-V1.2">

	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="AB" page-height="8.5in" page-width="11in" margin-left="0.3in" margin-right="0.3in">
				<fo:region-body margin-top="0.3in" margin-bottom=".4in"/>
				<fo:region-after extent=".3in"/>
			</fo:simple-page-master>
			<fo:simple-page-master master-name="main" page-height="8.5in" page-width="11in" margin-left="0.4in" margin-right="0.4in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.5in"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
			<fo:page-sequence-master master-name="primary">
				<fo:single-page-master-reference master-reference="main"/>
			</fo:page-sequence-master>
			<fo:page-sequence-master master-name="summary">
				<fo:single-page-master-reference master-reference="main"/>
			</fo:page-sequence-master>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.34in" margin-right="0.34in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.5in" font-family="Helvetica,Times,Courier" font-size="8pt"/>
				<fo:region-after extent=".5in"/>6.
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="RR_FedNonFed_SubawardBudget30_1_3:RR_FedNonFed_SubawardBudget30_1_3">
		<fo:root>

			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
									</fo:block>
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>

				</fo:static-content>

				<fo:flow flow-name="xsl-region-body">
					<fo:table width="100%">
						<fo:table-column column-width="0.2in"/>
						<fo:table-column/>
						<fo:table-column column-width="0.2in"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell width="7.5in">
									<fo:block text-align="center" font-family="Helvetica,Times,Courier" font-size="11pt" font-weight="bold">R&amp;R Subaward Budget (Fed/Non-Fed) Attachment(s) Form</fo:block>
									<fo:block>&#160;</fo:block>
									<fo:block font-size="8pt" hyphenate="true" language="en">
										<fo:inline font-weight="bold" font-size="8pt">Instructions:</fo:inline>
                                     On this form, you will attach the R&amp;R Subaward Budget files for your grant application.  Complete the subawardee budget(s) in accordance with the R&amp;R budget instructions.  Please remember that any files you attach must be a PDF document.
                                    </fo:block>
									<fo:block line-height="4pt">&#160;</fo:block>
									<fo:block>&#160;</fo:block>
									<fo:block>&#160;</fo:block>
									<fo:block font-size="8pt" hyphenate="true" language="en">
										<fo:inline font-weight="bold" font-size="8pt">Important:</fo:inline>
                               Please attach your subawardee budget file(s) with the file name of the subawardee organization.  Each file name must be unique.
                                    </fo:block>
									<fo:block>&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>

					<fo:table width="100%">
						<fo:table-column/>
						<fo:table-body>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT1!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">1) Please attach Attachment 1</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT1"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>

							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT2!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">2) Please attach Attachment 2</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT2"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT3!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">3) Please attach Attachment 3</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT3"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT4!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">4) Please attach Attachment 4</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT4"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT5!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">5) Please attach Attachment 5</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT5"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT6!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">6) Please attach Attachment 6</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT6"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT7!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">7) Please attach Attachment 7</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT7"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT8!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">8) Please attach Attachment 8</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT8"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT9!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">9) Please attach Attachment 9</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT9"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT10!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">10) Please attach Attachment 10</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT10"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT11!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">11) Please attach Attachment 11</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT11"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT12!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">12) Please attach Attachment 12</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT12"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT13!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">13) Please attach Attachment 13</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT13"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT14!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">14) Please attach Attachment 14</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT14"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT15!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">15) Please attach Attachment 15</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT15"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT16!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">16) Please attach Attachment 16</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT16"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT17!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">17) Please attach Attachment 17</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT17"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT18!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">18) Please attach Attachment 18</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT18"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT19!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">19) Please attach Attachment 19</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT19"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT20!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">20) Please attach Attachment 20</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT20"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT21!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">21) Please attach Attachment 21</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT21"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT22!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">22) Please attach Attachment 22</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT22"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT23!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">23) Please attach Attachment 23</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT23"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT24!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">24) Please attach Attachment 24</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT24"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT25!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">25) Please attach Attachment 25</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT25"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT26!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">26) Please attach Attachment 26</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT26"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT27!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">27) Please attach Attachment 27</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT27"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT28!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">28) Please attach Attachment 28</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT28"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT29!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">29) Please attach Attachment 29</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT29"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="RR_FedNonFed_SubawardBudget30_1_3:ATT30!=''">
								<xsl:call-template name="attach_block">
									<xsl:with-param name="block_num"/>
									<xsl:with-param name="block_title">30) Please attach Attachment 30</xsl:with-param>
									<xsl:with-param name="filename">
										<xsl:value-of select="RR_FedNonFed_SubawardBudget30_1_3:ATT30"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:if>
						</fo:table-body>
					</fo:table>

				</fo:flow>
			</fo:page-sequence>

			<xsl:for-each select="RR_FedNonFed_SubawardBudget30_1_3:BudgetAttachments/RR_FedNonFedBudget_1_2:RR_FedNonFedBudget_1_2">
				<fo:page-sequence master-reference="AB" format="1">
					<fo:static-content flow-name="xsl-region-after">

						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
								
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[1]">
							<xsl:call-template name="SingleYearPart1">
								<xsl:with-param name="year">1</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
						
					</fo:flow>
				</fo:page-sequence>
				<fo:page-sequence master-reference="primary" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[1]">
							<xsl:call-template name="SingleYearPart2">
								<xsl:with-param name="year">1</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
					</fo:flow>
				</fo:page-sequence>
				
				<xsl:if test="RR_FedNonFedBudget_1_2:BudgetYear[2]!=''">
					<fo:page-sequence master-reference="AB" format="1">
						<fo:static-content flow-name="xsl-region-after">
							<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
											</fo:block>
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:static-content>
						<fo:flow flow-name="xsl-region-body">
							<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[2]">
								<xsl:call-template name="SingleYearPart1">
									<xsl:with-param name="year">2</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
						</fo:flow>
					</fo:page-sequence>
					<fo:page-sequence master-reference="primary" format="1">
						<fo:static-content flow-name="xsl-region-after">
							<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
											</fo:block>
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:static-content>
						<fo:flow flow-name="xsl-region-body">
							<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[2]">
								<xsl:call-template name="SingleYearPart2">
									<xsl:with-param name="year">2</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
						</fo:flow>
					</fo:page-sequence>
				</xsl:if>
				<xsl:if test="RR_FedNonFedBudget_1_2:BudgetYear[3]!=''">
					<fo:page-sequence master-reference="AB" format="1">
						<fo:static-content flow-name="xsl-region-after">
							<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
											</fo:block>
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:static-content>
						<fo:flow flow-name="xsl-region-body">
							<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[3]">
								<xsl:call-template name="SingleYearPart1">
									<xsl:with-param name="year">3</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
						</fo:flow>
					</fo:page-sequence>
					<fo:page-sequence master-reference="primary" format="1">
						<fo:static-content flow-name="xsl-region-after">
							<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
											</fo:block>
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:static-content>
						<fo:flow flow-name="xsl-region-body">
							<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[3]">
								<xsl:call-template name="SingleYearPart2">
									<xsl:with-param name="year">3</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
						</fo:flow>
					</fo:page-sequence>
				</xsl:if>
				<xsl:if test="RR_FedNonFedBudget_1_2:BudgetYear[4]!=''">
					<fo:page-sequence master-reference="AB" format="1">
						<fo:static-content flow-name="xsl-region-after">
							<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
											</fo:block>
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:static-content>
						<fo:flow flow-name="xsl-region-body">
							<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[4]">
								<xsl:call-template name="SingleYearPart1">
									<xsl:with-param name="year">4</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
						</fo:flow>
					</fo:page-sequence>
					<fo:page-sequence master-reference="primary" format="1">
						<fo:static-content flow-name="xsl-region-after">
							<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
											</fo:block>
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:static-content>
						<fo:flow flow-name="xsl-region-body">
							<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[4]">
								<xsl:call-template name="SingleYearPart2">
									<xsl:with-param name="year">4</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
						</fo:flow>
					</fo:page-sequence>
				</xsl:if>
				<xsl:if test="RR_FedNonFedBudget_1_2:BudgetYear[5]!=''">
					<fo:page-sequence master-reference="AB" format="1">
						<fo:static-content flow-name="xsl-region-after">
							<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
											</fo:block>
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:static-content>
						<fo:flow flow-name="xsl-region-body">
							<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[5]">
								<xsl:call-template name="SingleYearPart1">
									<xsl:with-param name="year">5</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
						</fo:flow>
					</fo:page-sequence>
					<fo:page-sequence master-reference="primary" format="1">
						<fo:static-content flow-name="xsl-region-after">
							<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
											</fo:block>
											<fo:block>
												<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:static-content>
						<fo:flow flow-name="xsl-region-body">
							<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetYear[5]">
								<xsl:call-template name="SingleYearPart2">
									<xsl:with-param name="year">5</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
						</fo:flow>
					</fo:page-sequence>
				</xsl:if>
				
				<fo:page-sequence master-reference="summary" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 10/31/2019</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<fo:table width="100%" space-before.optimum="3pt" space-after.optimum="2pt">
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="before">
										<fo:block>
											<fo:inline font-weight="bold" font-size="10pt">RESEARCH &amp; RELATED BUDGET (TOTAL FED + NON-FED) - Cumulative Budget</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						<fo:block>
							<fo:leader leader-pattern="space"/>
						</fo:block>
						<fo:block>
							<fo:leader leader-pattern="space"/>
						</fo:block>
						
						
						<fo:block font-size="8pt">
							<fo:table width="500pt" space-before.optimum="3pt" space-after.optimum="2pt">
								<fo:table-column column-width="proportional-column-width(70)"/>
								<fo:table-column column-width="proportional-column-width(30)"/>
								<fo:table-column column-width="proportional-column-width(30)"/>
								<fo:table-column column-width="proportional-column-width(30)"/>
								<fo:table-body>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:BudgetSummary">
										<fo:table-row>
											<fo:table-cell>
												<fo:block/>
											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Non-Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">*Totals (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell number-columns-spanned="4">
												<fo:block>&#160;</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section A, Senior/Key Person</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget_1_2:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget_1_2:NonFederalSummary) or RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget_1_2:NonFederalSummary=''">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget_1_2:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section B, Other Personnel</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number
(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget_1_2:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget_1_2:NonFederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number
(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget_1_2:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>Total Number Other Personnel</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block/>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block/>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalNoOtherPersonnel = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalNoOtherPersonnel)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalNoOtherPersonnel, '#,##0')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Total  Salary, Wages and Fringe Benefits (A + B)</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget_1_2:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget_1_2:NonFederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget_1_2:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section C, Equipment</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeEquipments/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeEquipments/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeEquipments/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget_1_2:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeEquipments/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeEquipments/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget_1_2:NonFederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeEquipments/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget_1_2:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeEquipments/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeEquipments/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeEquipments/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section D, Travel</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget_1_2:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget_1_2:NonFederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget_1_2:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">1. </fo:inline>Domestic</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeDomesticTravelCosts/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeDomesticTravelCosts/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeDomesticTravelCosts/RR_FedNonFedBudget_1_2:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeDomesticTravelCosts/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeDomesticTravelCosts/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeDomesticTravelCosts/RR_FedNonFedBudget_1_2:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeDomesticTravelCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeDomesticTravelCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeDomesticTravelCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">2. </fo:inline>Foreign</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeForeignTravelCosts/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeForeignTravelCosts/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeForeignTravelCosts/RR_FedNonFedBudget_1_2:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeForeignTravelCosts/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeForeignTravelCosts/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeForeignTravelCosts/RR_FedNonFedBudget_1_2:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeForeignTravelCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeForeignTravelCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTravels/RR_FedNonFedBudget_1_2:CumulativeForeignTravelCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section E, Participant/Trainee Support Costs</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget_1_2:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget_1_2:NonFederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget_1_2:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">1. </fo:inline>Tuition/Fees/Health Insurance</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget_1_2:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget_1_2:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget_1_2:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">2. </fo:inline>Stipends</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeStipends/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeStipends/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeStipends/RR_FedNonFedBudget_1_2:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeStipends/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeStipends/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeStipends/RR_FedNonFedBudget_1_2:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeStipends/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeStipends/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeStipends/RR_FedNonFedBudget_1_2:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">3. </fo:inline>Travel</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTravel/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTravel/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTravel/RR_FedNonFedBudget_1_2:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTravel/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTravel/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTravel/RR_FedNonFedBudget_1_2:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTravel/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTravel/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeTravel/RR_FedNonFedBudget_1_2:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">4. </fo:inline>Subsistence</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeSubsistence/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeSubsistence/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeSubsistence/RR_FedNonFedBudget_1_2:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeSubsistence/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeSubsistence/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeSubsistence/RR_FedNonFedBudget_1_2:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeSubsistence/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeSubsistence/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeTraineeSubsistence/RR_FedNonFedBudget_1_2:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">5. </fo:inline>Other&#160;</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeOtherTraineeCost/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeOtherTraineeCost/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeOtherTraineeCost/RR_FedNonFedBudget_1_2:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeOtherTraineeCost/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeOtherTraineeCost/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeOtherTraineeCost/RR_FedNonFedBudget_1_2:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeOtherTraineeCost/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeOtherTraineeCost/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeOtherTraineeCost/RR_FedNonFedBudget_1_2:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">6. </fo:inline>Number of Participants/Trainees</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block/>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block/>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeNoofTrainees = '' or not(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeNoofTrainees)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTrainee/RR_FedNonFedBudget_1_2:CumulativeNoofTrainees, '###')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section F, Other Direct Costs</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget_1_2:FederalSummary
,'#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary
,'#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary
,'#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">1. </fo:inline>Materials and Supplies</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeMaterialAndSupplies/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeMaterialAndSupplies/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeMaterialAndSupplies/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeMaterialAndSupplies/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeMaterialAndSupplies/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeMaterialAndSupplies/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeMaterialAndSupplies/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeMaterialAndSupplies/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeMaterialAndSupplies/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">2. </fo:inline>Publication Costs</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativePublicationCosts/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativePublicationCosts/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativePublicationCosts/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativePublicationCosts/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativePublicationCosts/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativePublicationCosts/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativePublicationCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativePublicationCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativePublicationCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">3. </fo:inline>Consultant Services</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeConsultantServices/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeConsultantServices/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeConsultantServices/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeConsultantServices/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeConsultantServices/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeConsultantServices/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeConsultantServices/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeConsultantServices/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeConsultantServices/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">4. </fo:inline>ADP/Computer Services</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeADPComputerServices/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeADPComputerServices/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeADPComputerServices/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeADPComputerServices/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeADPComputerServices/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeADPComputerServices/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeADPComputerServices/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeADPComputerServices/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeADPComputerServices/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">5. </fo:inline>Subawards/Consortium/Contractual Costs</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">6. </fo:inline>Equipment or Facility Rental/User Fees</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">7. </fo:inline>Alterations and Renovations</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">8. </fo:inline>Other 1</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther1DirectCost/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther1DirectCost/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther1DirectCost/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther1DirectCost/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther1DirectCost/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther1DirectCost/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther1DirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther1DirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther1DirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">9. </fo:inline>Other 2</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther2DirectCost/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther2DirectCost/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther2DirectCost/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther2DirectCost/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther2DirectCost/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther2DirectCost/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther2DirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther2DirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther2DirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block>
													<fo:inline font-weight="bold">10. </fo:inline>Other 3</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther3DirectCost/RR_FedNonFedBudget_1_2:Federal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther3DirectCost/RR_FedNonFedBudget_1_2:Federal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther3DirectCost/RR_FedNonFedBudget_1_2:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther3DirectCost/RR_FedNonFedBudget_1_2:NonFederal = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther3DirectCost/RR_FedNonFedBudget_1_2:NonFederal)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther3DirectCost/RR_FedNonFedBudget_1_2:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther3DirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFed = '' or not(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther3DirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFed)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeOtherDirect/RR_FedNonFedBudget_1_2:CumulativeOther3DirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section G, Direct Costs (A thru F)</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget_1_2:FederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section H, Indirect Costs</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget_1_2:FederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget_1_2:NonFederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget_1_2:NonFederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section I, Total Direct and Indirect Costs (G + H)</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget_1_2:FederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget_1_2:FederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
												<fo:block font-weight="bold">Section J, Fee</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block>
													<xsl:choose>
														<xsl:when test="RR_FedNonFedBudget_1_2:CumulativeFee = '' or not(RR_FedNonFedBudget_1_2:CumulativeFee)">
															<fo:inline>&#160;</fo:inline>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeFee
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block/>
											</fo:table-cell>
											<fo:table-cell text-align="right">
												<fo:block/>
											</fo:table-cell>
										</fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                                <fo:block font-weight="bold">Section K, Total Costs and Fee (I + J)</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block>
                                                    <xsl:choose>
                                                        <xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalCostsFee/RR_FedNonFedBudget_1_2:FederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalCostsFee/RR_FedNonFedBudget_1_2:FederalSummary)">
                                                            <fo:inline>&#160;</fo:inline>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalCostsFee/RR_FedNonFedBudget_1_2:FederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block>
                                                    <xsl:choose>
                                                        <xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalCostsFee/RR_FedNonFedBudget_1_2:NonFederalSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalCostsFee/RR_FedNonFedBudget_1_2:NonFederalSummary)">
                                                            <fo:inline>&#160;</fo:inline>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalCostsFee/RR_FedNonFedBudget_1_2:NonFederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block>
                                                    <xsl:choose>
                                                        <xsl:when test="RR_FedNonFedBudget_1_2:CumulativeTotalCostsFee/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget_1_2:CumulativeTotalCostsFee/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary)">
                                                            <fo:inline>&#160;</fo:inline>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:CumulativeTotalCostsFee/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

									</xsl:for-each>
								</fo:table-body>
							</fo:table>
							<fo:block font-size="8pt">RESEARCH &amp; RELATED Budget (Total Fed + Non-Fed)</fo:block>
						</fo:block>

						
					</fo:flow>
				</fo:page-sequence>
			</xsl:for-each>
			
		</fo:root>
	</xsl:template>
	
	<xsl:template name="attach_block">
		<xsl:param name="block_num"/>
		<xsl:param name="block_title"/>
		<xsl:param name="filename"/>
		<xsl:element name="fo:table-row">
			<xsl:element name="fo:table-cell">
				<xsl:attribute name="font-size">8pt</xsl:attribute>
				<xsl:element name="fo:table">
					<xsl:attribute name="border-color">black</xsl:attribute>
					<xsl:attribute name="width">100%</xsl:attribute>
					<xsl:element name="fo:table-column"/>
					<xsl:element name="fo:table-body">
						<xsl:element name="fo:table-row">
							<xsl:element name="fo:table-cell">
								<xsl:element name="fo:table">
									<xsl:attribute name="width">100%</xsl:attribute>
									<xsl:element name="fo:table-column">
										<xsl:attribute name="column-width">0.2in</xsl:attribute>
									</xsl:element>
									<xsl:element name="fo:table-column">
										<xsl:attribute name="column-width">2.0in</xsl:attribute>
									</xsl:element>
									<xsl:element name="fo:table-column">
										<xsl:attribute name="column-width">2.0in</xsl:attribute>
									</xsl:element>
									<fo:table-column column-width="2.0in"/>
									<xsl:element name="fo:table-body">
										<xsl:element name="fo:table-row">
											<xsl:element name="fo:table-cell">
												<xsl:attribute name="line-height">15pt</xsl:attribute>
												<xsl:attribute name="hyphenate">true</xsl:attribute>
												<xsl:attribute name="language">en</xsl:attribute>
												<xsl:attribute name="font-weight">bold</xsl:attribute>
												<xsl:element name="fo:block">
													<xsl:value-of select="$block_num"/>
												</xsl:element>
											</xsl:element>
											<xsl:element name="fo:table-cell">
												<xsl:attribute name="line-height">15pt</xsl:attribute>
												<xsl:attribute name="hyphenate">true</xsl:attribute>
												<xsl:attribute name="language">en</xsl:attribute>
												<xsl:attribute name="font-weight">bold</xsl:attribute>
												<xsl:element name="fo:block">
													<xsl:value-of select="$block_title"/>
												</xsl:element>
											</xsl:element>
											<xsl:element name="fo:table-cell">
												<xsl:attribute name="line-height">15pt</xsl:attribute>
												<xsl:attribute name="hyphenate">true</xsl:attribute>
												<xsl:attribute name="language">en</xsl:attribute>
												<xsl:element name="fo:block">
													<xsl:value-of select="$filename"/>
												</xsl:element>
											</xsl:element>
										</xsl:element>
									</xsl:element>
								</xsl:element>
							</xsl:element>
						</xsl:element>
					</xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template name="SingleYearPart1">
		<xsl:param name="year"/>
		<fo:block>
			<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="10pt">RESEARCH &amp; RELATED BUDGET (TOTAL FED + NON-FED) - SECTION A &amp; B, BUDGET PERIOD&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">
				<fo:inline font-weight="bold">* ORGANIZATIONAL DUNS:&#160;&#160;</fo:inline>
				<fo:inline>
					<xsl:value-of select="../RR_FedNonFedBudget_1_2:DUNSID"/>
				</fo:inline>
			</fo:block>
			<fo:inline font-size="8pt" font-weight="bold">*&#160;Budget Type:&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget_1_2:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Project'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Project&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget_1_2:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Subaward/Consortium'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Subaward/Consortium</fo:inline>
			<fo:block>
                        
                     </fo:block>
			<fo:inline font-size="8pt" hyphenate="true" language="en" font-weight="bold">Enter name of Organization: </fo:inline>
			<fo:inline font-size="8pt">
				<xsl:value-of select="../RR_FedNonFedBudget_1_2:OrganizationName"/>
			</fo:inline>
			<fo:block>
                        
                     </fo:block>
			<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">*&#160;Start Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget_1_2:BudgetPeriodStartDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; *&#160;End Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget_1_2:BudgetPeriodEndDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* Budget Period:&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<xsl:for-each select="RR_FedNonFedBudget_1_2:KeyPersons">
				<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
					<fo:table-column/>
					<fo:table-body>
						<fo:table-row>
							<fo:table-cell border-style="solid" border-color="black" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<fo:inline font-size="8pt" font-weight="bold">A. Senior/Key Person</fo:inline>
									<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
										<fo:table-column column-width="proportional-column-width(2)"/>
										<fo:table-column column-width="proportional-column-width(4)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(18)"/>
										<fo:table-column column-width="proportional-column-width(4)"/>
										<fo:table-column column-width="proportional-column-width(14)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(8)"/>
										<fo:table-column column-width="proportional-column-width(8)"/>
										<fo:table-column column-width="proportional-column-width(9)"/>
										<fo:table-column column-width="proportional-column-width(9)"/>
										<fo:table-column column-width="proportional-column-width(12)"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="2" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Prefix</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* First Name</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Middle Name</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Last Name</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Suffix</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Project Role</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Base Salary (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Cal. Months</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Acad. Months</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Sum. Months</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Req. Salary&#160;(&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Fringe Ben. (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Total(Sal &amp; FB)(Fed + Non-Fed) (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Federal (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Non-Federal (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:KeyPerson">
										<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
											<fo:table-column column-width="proportional-column-width(2)"/>
											<fo:table-column column-width="proportional-column-width(4)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(18)"/>
											<fo:table-column column-width="proportional-column-width(4)"/>
											<fo:table-column column-width="proportional-column-width(14)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(8)"/>
											<fo:table-column column-width="proportional-column-width(8)"/>
											<fo:table-column column-width="proportional-column-width(9)"/>
											<fo:table-column column-width="proportional-column-width(9)"/>
											<fo:table-column column-width="proportional-column-width(12)"/>
											<fo:table-body>
												<fo:table-row>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<fo:inline font-size="8pt">
																<xsl:value-of select="position()"/>.&#160;</fo:inline>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Name">
																<xsl:for-each select="globLib:PrefixName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Name">
																<xsl:for-each select="globLib:FirstName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Name">
																<xsl:for-each select="globLib:MiddleName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Name">
																<xsl:for-each select="globLib:LastName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Name">
																<xsl:for-each select="globLib:SuffixName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:ProjectRole">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:BaseSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:Total/RR_FedNonFedBudget_1_2:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:Total/RR_FedNonFedBudget_1_2:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:Total/RR_FedNonFedBudget_1_2:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
												</fo:table-row>
											</fo:table-body>
										</fo:table>
									</xsl:for-each>
									<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
										<fo:table-column column-width="proportional-column-width(2)"/>
										<fo:table-column column-width="proportional-column-width(4)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(18)"/>
										<fo:table-column column-width="proportional-column-width(4)"/>
										<fo:table-column column-width="proportional-column-width(14)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(8)"/>
										<fo:table-column column-width="proportional-column-width(8)"/>
										<fo:table-column column-width="proportional-column-width(9)"/>
										<fo:table-column column-width="proportional-column-width(9)"/>
										<fo:table-column column-width="proportional-column-width(12)"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="13" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt" font-weight="bold">9. Total Funds Requested for all Senior Key Persons in the attached file </fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFundForAttachedKeyPersons/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
															<fo:inline font-size="8pt">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFundForAttachedKeyPersons/RR_FedNonFedBudget_1_2:FederalSummary">
															<fo:inline font-size="8pt">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFundForAttachedKeyPersons/RR_FedNonFedBudget_1_2:NonFederalSummary">
															<fo:inline font-size="8pt">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="4" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt" font-weight="bold">* Additional Senior Key Persons:</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="3" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">File Name: </fo:inline>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:AttachedKeyPersons">
															<xsl:for-each select="att:FileName">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="3" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Mime Type: </fo:inline>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:AttachedKeyPersons">
															<xsl:for-each select="att:MimeType">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="3" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt" font-weight="bold">Total Senior/Key Person</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFundForKeyPersons/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFundForKeyPersons/RR_FedNonFedBudget_1_2:FederalSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFundForKeyPersons/RR_FedNonFedBudget_1_2:NonFederalSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</xsl:for-each>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>

			<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell border-style="solid" border-color="black" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">B. Other Personnel</fo:inline>
								<fo:block>
									<xsl:text>&#xA;</xsl:text>
								</fo:block>
								<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(61)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">* Number of Personnel</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">* Project Role</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">Cal. Months</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">Acad. Months</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">Sum. Months</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">* Req. Salary (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">* Fringe Ben. (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Total(Sal &amp; FB)(Fed + Non-Fed) (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherPersonnel">
											<fo:table-row>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Post Doctoral Associates</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Graduate Students</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Undergraduate Students</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Secretarial/Clerical</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget_1_2:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</xsl:for-each>
									</fo:table-body>
								</fo:table>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherPersonnel">
									<xsl:for-each select="RR_FedNonFedBudget_1_2:Other">
										<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(61)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-body>
												<fo:table-row>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:ProjectRole">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherTotal/RR_FedNonFedBudget_1_2:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
												</fo:table-row>
											</fo:table-body>
										</fo:table>
									</xsl:for-each>
								</xsl:for-each>
								<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(61)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherPersonnel">
														<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherPersonnelTotalNumber">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:apply-templates/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left" number-columns-spanned="2" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Number Other Personnel</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell number-columns-spanned="4" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Other Personnel &#160;&#160;&#160;&#160;</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherPersonnel">
														<xsl:if test="RR_FedNonFedBudget_1_2:TotalOtherPersonnelFund/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary!=''">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalOtherPersonnelFund/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
																<fo:inline font-size="8pt" font-weight="bold">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</xsl:if>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherPersonnel">
														<xsl:if test="RR_FedNonFedBudget_1_2:TotalOtherPersonnelFund/RR_FedNonFedBudget_1_2:FederalSummary!=''">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalOtherPersonnelFund/RR_FedNonFedBudget_1_2:FederalSummary">
																<fo:inline font-size="8pt" font-weight="bold">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</xsl:if>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherPersonnel">
														<xsl:if test="RR_FedNonFedBudget_1_2:TotalOtherPersonnelFund/RR_FedNonFedBudget_1_2:NonFederalSummary!=''">
															<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalOtherPersonnelFund/RR_FedNonFedBudget_1_2:NonFederalSummary">
																<fo:inline font-size="8pt" font-weight="bold">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</xsl:if>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell number-columns-spanned="7" hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Salary, Wages and Fringe Benefits (A+B)&#160;&#160;&#160;</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" hyphenate="true" language="en" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalCompensation/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
														<fo:inline font-size="8pt" font-weight="bold">
															<xsl:value-of select="format-number(., '#,##0.00')"/>
														</fo:inline>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" hyphenate="true" language="en" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalCompensation/RR_FedNonFedBudget_1_2:FederalSummary">
														<fo:inline font-size="8pt" font-weight="bold">
															<xsl:value-of select="format-number(., '#,##0.00')"/>
														</fo:inline>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" hyphenate="true" language="en" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalCompensation/RR_FedNonFedBudget_1_2:NonFederalSummary">
														<fo:inline font-size="8pt" font-weight="bold">
															<xsl:value-of select="format-number(., '#,##0.00')"/>
														</fo:inline>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">RESEARCH &amp; RELATED Budget {A-B} (Total Fed + Non-Fed)</fo:block>
		</fo:block>
	</xsl:template>

	<xsl:template name="SingleYearPart2">
		<xsl:param name="year"/>
		<fo:block>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="10pt">RESEARCH &amp; RELATED BUDGET (TOTAL FED + NON-FED) - SECTION C, D, &amp; E, BUDGET PERIOD&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">
				<fo:inline font-weight="bold">* ORGANIZATIONAL DUNS:&#160;&#160;</fo:inline>
				<fo:inline>
					<xsl:value-of select="../RR_FedNonFedBudget_1_2:DUNSID"/>
				</fo:inline>
			</fo:block>
			<fo:inline font-size="8pt" font-weight="bold">* Budget Type:&#160;&#160; </fo:inline>
			<fo:inline font-size="8pt">&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget_1_2:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Project'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Project&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget_1_2:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Subaward/Consortium'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Subaward/Consortium</fo:inline>
			<fo:block>
                        
                     </fo:block>
			<fo:inline font-size="8pt" font-weight="bold">Enter name of Organization: </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget_1_2:OrganizationName">
				<fo:inline font-size="8pt">
					<xsl:apply-templates/>
				</fo:inline>
			</xsl:for-each>
			<fo:block>
                        
                     </fo:block>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">* Start Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget_1_2:BudgetPeriodStartDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* End Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget_1_2:BudgetPeriodEndDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* Budget Period:&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en" border-style="solid" border-color="black" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">C. Equipment Description</fo:inline>
								<fo:block>
                                          
                                       </fo:block>
								<fo:inline font-size="8pt" font-weight="bold">List items and dollar amount for each item exceeding $5,000</fo:inline>
								<xsl:if test="RR_FedNonFedBudget_1_2:Equipment/RR_FedNonFedBudget_1_2:EquipmentList">
								<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
									<fo:table-column column-width="proportional-column-width(3)"/>
									<fo:table-column column-width="proportional-column-width(50)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-header>
										<fo:table-row>
											<fo:table-cell border-before-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Equipment Item</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-header>
									<fo:table-body>
										<xsl:for-each select="RR_FedNonFedBudget_1_2:Equipment">
											<xsl:for-each select="RR_FedNonFedBudget_1_2:EquipmentList">
												<fo:table-row>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center">
														<fo:block>
															<fo:inline font-size="8pt">
																<xsl:value-of select="RR_FedNonFedBudget_1_2:EquipmentItem"/>
															</fo:inline>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:FundsRequested/RR_FedNonFedBudget_1_2:Federal">
																<fo:inline font-size="8pt">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:FundsRequested/RR_FedNonFedBudget_1_2:NonFederal">
																<fo:inline font-size="8pt">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget_1_2:FundsRequested/RR_FedNonFedBudget_1_2:TotalFedNonFed">
																<fo:inline font-size="8pt">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
												</fo:table-row>
											</xsl:for-each>
										</xsl:for-each>
									</fo:table-body>
								</fo:table>
								</xsl:if>
								<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
									<fo:table-column column-width="proportional-column-width(53)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-header>
										<fo:table-row>
											<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">11. Total funds requested for all equipment listed in the attached file</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFundForAttachedEquipment/RR_FedNonFedBudget_1_2:Federal">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFundForAttachedEquipment/RR_FedNonFedBudget_1_2:NonFederal">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFundForAttachedEquipment/RR_FedNonFedBudget_1_2:TotalFedNonFed">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-header>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Equipment</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFund/RR_FedNonFedBudget_1_2:FederalSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFund/RR_FedNonFedBudget_1_2:NonFederalSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalFund/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
								<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
									<fo:table-column/>
									<fo:table-column/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">* Additional Equipment:</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
												<fo:block>
													<fo:inline font-size="8pt">File Name: </fo:inline>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:Equipment/RR_FedNonFedBudget_1_2:AdditionalEquipmentsAttachment">
														<xsl:for-each select="att:FileName">
															<fo:inline font-size="8pt">
																<xsl:apply-templates/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
												<fo:block>
													<fo:inline font-size="8pt">Mime Type: </fo:inline>
													<xsl:for-each select="RR_FedNonFedBudget_1_2:Equipment/RR_FedNonFedBudget_1_2:AdditionalEquipmentsAttachment">
														<xsl:for-each select="att:MimeType">
															<fo:inline font-size="8pt">
																<xsl:apply-templates/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(53)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">D. Travel</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">1. Domestic Travel Costs ( Incl. Canada, Mexico, and U.S. Possessions)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:DomesticTravelCost/RR_FedNonFedBudget_1_2:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:DomesticTravelCost/RR_FedNonFedBudget_1_2:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:DomesticTravelCost/RR_FedNonFedBudget_1_2:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">2. Foreign Travel Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:ForeignTravelCost/RR_FedNonFedBudget_1_2:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:ForeignTravelCost/RR_FedNonFedBudget_1_2:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:ForeignTravelCost/RR_FedNonFedBudget_1_2:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Travel Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:TotalTravelCost/RR_FedNonFedBudget_1_2:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:TotalTravelCost/RR_FedNonFedBudget_1_2:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:TotalTravelCost/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(28)"/>
				<fo:table-column column-width="proportional-column-width(25)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">E. Participant/Trainee Support Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">1. Tuition/Fees/Health Insurance</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:TuitionFeeHealthInsurance/RR_FedNonFedBudget_1_2:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:TuitionFeeHealthInsurance/RR_FedNonFedBudget_1_2:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:TuitionFeeHealthInsurance/RR_FedNonFedBudget_1_2:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">2. Stipends</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Stipends/RR_FedNonFedBudget_1_2:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Stipends/RR_FedNonFedBudget_1_2:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Stipends/RR_FedNonFedBudget_1_2:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">3. Travel</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Travel/RR_FedNonFedBudget_1_2:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">4. Subsistence</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Subsistence/RR_FedNonFedBudget_1_2:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Subsistence/RR_FedNonFedBudget_1_2:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Subsistence/RR_FedNonFedBudget_1_2:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">5. Other&#160;&#160; </fo:inline>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Other">
									<xsl:for-each select="RR_FedNonFedBudget_1_2:Description">
										<fo:inline font-size="8pt">
											<xsl:apply-templates/>
										</fo:inline>
									</xsl:for-each>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Other/RR_FedNonFedBudget_1_2:Cost/RR_FedNonFedBudget_1_2:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Other/RR_FedNonFedBudget_1_2:Cost/RR_FedNonFedBudget_1_2:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:Other/RR_FedNonFedBudget_1_2:Cost/RR_FedNonFedBudget_1_2:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:ParticipantTraineeNumber">
									<fo:inline font-size="8pt">
										<xsl:apply-templates/>
									</fo:inline>
								</xsl:for-each>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;Number of Participants/Trainees</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Participant/Trainee Support Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:TotalCost/RR_FedNonFedBudget_1_2:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:TotalCost/RR_FedNonFedBudget_1_2:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:ParticipantTraineeSupportCosts/RR_FedNonFedBudget_1_2:TotalCost/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">RESEARCH &amp; RELATED Budget {C-E} (Total Fed + Non-Fed)</fo:block>
			<fo:block break-after="page">
				<xsl:text>&#xA;</xsl:text>
			</fo:block>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="10pt">RESEARCH &amp; RELATED BUDGET (TOTAL FED + NON-FED) - SECTIONS F-K, BUDGET PERIOD&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">
				<fo:inline font-weight="bold">* ORGANIZATIONAL DUNS:&#160;&#160;</fo:inline>
				<fo:inline>
					<xsl:value-of select="../RR_FedNonFedBudget_1_2:DUNSID"/>
				</fo:inline>
			</fo:block>
			<fo:inline font-size="8pt" font-weight="bold">* Budget Type:&#160;&#160; </fo:inline>
			<fo:inline font-size="8pt">&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget_1_2:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Project'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Project&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget_1_2:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Subaward/Consortium'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Subaward/Consortium</fo:inline>
			<fo:block>
                        
                     </fo:block>
			<fo:inline font-size="8pt" font-weight="bold">Enter name of Organization: </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget_1_2:OrganizationName">
				<fo:inline font-size="8pt">
					<xsl:apply-templates/>
				</fo:inline>
			</xsl:for-each>
			<fo:block>
                        
                     </fo:block>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">* Start Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget_1_2:BudgetPeriodStartDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* End Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget_1_2:BudgetPeriodEndDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* Budget Period:&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(53)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">F. Other Direct Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherDirectCosts">
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">1. Materials and Supplies</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:MaterialsSupplies/RR_FedNonFedBudget_1_2:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:MaterialsSupplies/RR_FedNonFedBudget_1_2:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:MaterialsSupplies/RR_FedNonFedBudget_1_2:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">2. Publication Costs</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:PublicationCosts/RR_FedNonFedBudget_1_2:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:PublicationCosts/RR_FedNonFedBudget_1_2:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:PublicationCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">3. Consultant Services</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:ConsultantServices/RR_FedNonFedBudget_1_2:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:ConsultantServices/RR_FedNonFedBudget_1_2:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:ConsultantServices/RR_FedNonFedBudget_1_2:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">4. ADP/Computer Services</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:ADPComputerServices/RR_FedNonFedBudget_1_2:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:ADPComputerServices/RR_FedNonFedBudget_1_2:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:ADPComputerServices/RR_FedNonFedBudget_1_2:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">5. Subawards/Consortium/Contractual Costs</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:SubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:SubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:SubawardConsortiumContractualCosts/RR_FedNonFedBudget_1_2:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">6. Equipment or Facility Rental/User Fees</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:EquipmentRentalFee/RR_FedNonFedBudget_1_2:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:EquipmentRentalFee/RR_FedNonFedBudget_1_2:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:EquipmentRentalFee/RR_FedNonFedBudget_1_2:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">7. Alterations and Renovations</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:AlterationsRenovations/RR_FedNonFedBudget_1_2:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:AlterationsRenovations/RR_FedNonFedBudget_1_2:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget_1_2:AlterationsRenovations/RR_FedNonFedBudget_1_2:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<xsl:for-each select="RR_FedNonFedBudget_1_2:Others">
							<xsl:for-each select="RR_FedNonFedBudget_1_2:Other">
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
										<fo:block>
											<fo:inline font-size="8pt">
												<xsl:value-of select="position()+7"/>.&#160;</fo:inline>
											<xsl:for-each select="RR_FedNonFedBudget_1_2:Description">
												<fo:inline font-size="8pt">
													<xsl:apply-templates/>
												</fo:inline>
											</xsl:for-each>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
										<fo:block>
											<xsl:for-each select="RR_FedNonFedBudget_1_2:Cost/RR_FedNonFedBudget_1_2:Federal">
												<fo:inline font-size="8pt">
													<xsl:value-of select="format-number(., '#,##0.00')"/>
												</fo:inline>
											</xsl:for-each>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
										<fo:block>
											<xsl:for-each select="RR_FedNonFedBudget_1_2:Cost/RR_FedNonFedBudget_1_2:NonFederal">
												<fo:inline font-size="8pt">
													<xsl:value-of select="format-number(., '#,##0.00')"/>
												</fo:inline>
											</xsl:for-each>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
										<fo:block>
											<xsl:for-each select="RR_FedNonFedBudget_1_2:Cost/RR_FedNonFedBudget_1_2:TotalFedNonFed">
												<fo:inline font-size="8pt">
													<xsl:value-of select="format-number(., '#,##0.00')"/>
												</fo:inline>
											</xsl:for-each>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<fo:table-row>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Other Direct Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherDirectCosts/RR_FedNonFedBudget_1_2:TotalOtherDirectCost/RR_FedNonFedBudget_1_2:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherDirectCosts/RR_FedNonFedBudget_1_2:TotalOtherDirectCost/RR_FedNonFedBudget_1_2:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:OtherDirectCosts/RR_FedNonFedBudget_1_2:TotalOtherDirectCost/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(53)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">G. Direct Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Direct Costs (A thru F)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:DirectCosts/RR_FedNonFedBudget_1_2:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:DirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:DirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(31)"/>				
				<fo:table-column column-width="proportional-column-width(17)"/>
				<fo:table-column column-width="proportional-column-width(17)"/>
				<fo:table-column column-width="proportional-column-width(12)"/>
				<fo:table-column column-width="proportional-column-width(12)"/>
				<fo:table-column column-width="proportional-column-width(12)"/>
				<fo:table-header>
					<fo:table-row>
						<fo:table-cell number-columns-spanned="4" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">H. Indirect Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">*Indirect Cost Type</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Indirect Cost Rate (%)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Indirect Cost Base ($)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-header>
				<fo:table-body>
					<xsl:for-each select="RR_FedNonFedBudget_1_2:IndirectCosts">
						<xsl:for-each select="RR_FedNonFedBudget_1_2:IndirectCost">
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
									<fo:block>
										<fo:inline font-size="8pt">
											<xsl:value-of select="position()"/>.&#160;</fo:inline>
										<xsl:for-each select="RR_FedNonFedBudget_1_2:CostType">
											<fo:inline font-size="8pt">
												<xsl:apply-templates/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget_1_2:Rate">
											<fo:inline font-size="8pt">
												<xsl:apply-templates/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget_1_2:Base">
											<fo:inline font-size="8pt">
												<xsl:value-of select="format-number(., '#,##0.00')"/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en"  line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget_1_2:FundRequested/RR_FedNonFedBudget_1_2:Federal">
											<fo:inline font-size="8pt">
												<xsl:value-of select="format-number(., '#,##0.00')"/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en"  line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget_1_2:FundRequested/RR_FedNonFedBudget_1_2:NonFederal">
											<fo:inline font-size="8pt">
												<xsl:value-of select="format-number(., '#,##0.00')"/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en"  line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget_1_2:FundRequested/RR_FedNonFedBudget_1_2:TotalFedNonFed">
											<fo:inline font-size="8pt">
												<xsl:value-of select="format-number(., '#,##0.00')"/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
					</xsl:for-each>
					<fo:table-row>
						<fo:table-cell number-columns-spanned="3" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Indirect Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"  text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">
									<xsl:choose>
										<xsl:when test="RR_FedNonFedBudget_1_2:IndirectCosts/RR_FedNonFedBudget_1_2:TotalIndirectCosts/RR_FedNonFedBudget_1_2:FederalSummary">
											<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:IndirectCosts/RR_FedNonFedBudget_1_2:TotalIndirectCosts/RR_FedNonFedBudget_1_2:FederalSummary, '#,##0.00')"/>
										</xsl:when>

									</xsl:choose>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"  text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">
									<xsl:choose>
										<xsl:when test="RR_FedNonFedBudget_1_2:IndirectCosts/RR_FedNonFedBudget_1_2:TotalIndirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary">
											<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:IndirectCosts/RR_FedNonFedBudget_1_2:TotalIndirectCosts/RR_FedNonFedBudget_1_2:NonFederalSummary, '#,##0.00')"/>
										</xsl:when>

									</xsl:choose>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">
									<xsl:choose>
										<xsl:when test="RR_FedNonFedBudget_1_2:IndirectCosts/RR_FedNonFedBudget_1_2:TotalIndirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
											<xsl:value-of select="format-number(RR_FedNonFedBudget_1_2:IndirectCosts/RR_FedNonFedBudget_1_2:TotalIndirectCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary, '#,##0.00')"/>
										</xsl:when>

									</xsl:choose>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en" number-columns-spanned="6" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="0pt" display-align="before">
							<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
								<fo:table-column column-width="proportional-column-width(20)"/>
								<fo:table-column column-width="proportional-column-width(60)"/>
								<fo:table-column column-width="proportional-column-width(50)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell>
											<fo:block>
												<fo:inline font-size="8pt" font-weight="bold">Cognizant Agency</fo:inline>
												<fo:inline font-size="8pt">&#160; </fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block>
												<xsl:for-each select="RR_FedNonFedBudget_1_2:CognizantFederalAgency">
													<fo:inline font-size="8pt">
														<xsl:apply-templates/>
													</fo:inline>
												</xsl:for-each>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-start="4pt">
											<fo:block>
												<fo:inline font-size="8pt" font-weight="normal">(Agency Name, POC Name and Phone Number)</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(53)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">I. Total Direct and Indirect Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Direct and Indirect Costs (G + H)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalCosts/RR_FedNonFedBudget_1_2:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalCosts/RR_FedNonFedBudget_1_2:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:TotalCosts/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(65)"/>
				<fo:table-column column-width="proportional-column-width(15)"/>
				<fo:table-column column-width="proportional-column-width(35)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">J. Fee</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">&#160; </fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">&#160; </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget_1_2:Fee">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">&#160; </fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
            <fo:block>
                <fo:leader leader-pattern="space"/>
            </fo:block>
            <fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                <fo:table-column column-width="proportional-column-width(53)"/>
                <fo:table-column column-width="proportional-column-width(16)"/>
                <fo:table-column column-width="proportional-column-width(16)"/>
                <fo:table-column column-width="proportional-column-width(16)"/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
                            <fo:block>
                                <fo:inline font-size="8pt" font-weight="bold">K. Total Costs and Fee</fo:inline>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                            <fo:block>
                                <fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                            <fo:block>
                                <fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                            <fo:block>
                                <fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                            <fo:block>
                                <fo:inline font-size="8pt" font-weight="bold">Total Costs and Fee (I + J)</fo:inline>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                            <fo:block>
                                <xsl:for-each select="RR_FedNonFedBudget_1_2:TotalCostsFee/RR_FedNonFedBudget_1_2:FederalSummary">
                                    <fo:inline font-size="8pt" font-weight="bold">
                                        <xsl:value-of select="format-number(., '#,##0.00')"/>
                                    </fo:inline>
                                </xsl:for-each>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                            <fo:block>
                                <xsl:for-each select="RR_FedNonFedBudget_1_2:TotalCostsFee/RR_FedNonFedBudget_1_2:NonFederalSummary">
                                    <fo:inline font-size="8pt" font-weight="bold">
                                        <xsl:value-of select="format-number(., '#,##0.00')"/>
                                    </fo:inline>
                                </xsl:for-each>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                            <fo:block>
                                <xsl:for-each select="RR_FedNonFedBudget_1_2:TotalCostsFee/RR_FedNonFedBudget_1_2:TotalFedNonFedSummary">
                                    <fo:inline font-size="8pt" font-weight="bold">
                                        <xsl:value-of select="format-number(., '#,##0.00')"/>
                                    </fo:inline>
                                </xsl:for-each>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-column/>
				<fo:table-column/><fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">L. * Budget Justification</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">File Name: </fo:inline>
								<xsl:for-each select="../RR_FedNonFedBudget_1_2:BudgetJustificationAttachment/att:FileName">
									<fo:inline font-size="8pt">
										<xsl:apply-templates/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">Mime Type: </fo:inline>
								<xsl:for-each select="../RR_FedNonFedBudget_1_2:BudgetJustificationAttachment/att:MimeType">
									<fo:inline font-size="8pt">
										<xsl:apply-templates/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">(Only attach one file.)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell>
							<fo:block/>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">RESEARCH &amp; RELATED Budget {F-K} (Total Fed + Non-Fed)</fo:block>
		</fo:block>
	</xsl:template>


	<xsl:template name="formatDate">
		<xsl:param name="value"/>
		<xsl:value-of select="format-number(substring($value,6,2), '00')"/>
		<xsl:text>-</xsl:text>
		<xsl:value-of select="format-number(substring($value,9,2), '00')"/>
		<xsl:text>-</xsl:text>
		<xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
	</xsl:template>
</xsl:stylesheet>
