

##CURRENT
*  Fix populating months effort for costshare on personal line items
  * Geo Thomas on Tue, 22 Sep 2015 16:09:32 -0400 [View Commit](../../commit/3e7373cf083fef6a8eaef6d7e457a991d1c5869e)

##coeus-s2sgen-1509.0010
*  Fix stylesheet for PHS_fellowship_supplemental-V2
  * Geo Thomas on Tue, 22 Sep 2015 17:01:18 -0400 [View Commit](../../commit/4a49c0cdb9554a0f4046e50765d0d7429e1370ce)

##coeus-s2sgen-1509.0009
* No Changes


##coeus-s2sgen-1509.0008
*  Add missing LA amounts
  * Gayathri Athreya on Thu, 17 Sep 2015 12:55:26 -0700 [View Commit](../../commit/5816bde6a12cd8f9d5090d5f76ecd318e9740554)

##coeus-s2sgen-1509.0007
*  Fixing sf424A

  * Section B > Budget Categories >a. Personnel is not populating with data. reads as $0.
  * See res-demo 1 example proposal 854 and 852 (lab allocated).
  * Issue #1:
  * Section B. Budget Categories: Subsection 6, Column 1
  * Items a through h: Currently we are publishing categories but only for P1 expense.
  * Desired results: these fields should be the TOTAL ALL PERIODS expense in each category.
  * (note that items i, j, and k DO reflect the totals)
  * Issue #2:
  * Section E - Budget Estimates of Federal Funds Needed for Balance of Project
  * This section has columns for up to 4 budget periods. We are not consistently providing the necessary data for the defined project periods. Please fix.
  * In one test proposal that had 3 periods, on 2 columns - periods 1 & 2 populated.
  * In another test proposal with 4 periods, only 3 columns populated.
  * Issue #3:
  * Section F: Other Budget Information
  * Field 21; Direct Charges. The DATstates; "Use this space to explain amounts for individual direct object class cost categories that may appear to be out of the ordinary or to explain the details as required by the Federal grantor agency" I don't see how this can be supported easily from the budget, and it is not a required field.
*Field 22: Indirect Charges: I do believe we should populate this, *as it is the ONLY place on this budget form to provide IDC rate information.
  * Form DAT requests the following:
  * "Enter the type of indirect rate (provisional, predetermined, final or fixed) that will be in effect during the funding period, the estimated amount of the base to which the rate is applied, and the total indirect expense."
  * I think we can provide that easily from budget data as follows:* Rate Type: "xxxx"; Indirect Base: "$xxxx"; Total Indirect Expense: "$xxxx"*
  * Gayathri Athreya on Wed, 16 Sep 2015 17:04:20 -0700 [View Commit](../../commit/2c1ca615700d0401970957fc2bd3703bb44041fe)

##coeus-s2sgen-1509.0006
* RESKC-840 : NIFA form formatting and Directory Title length in RRSF424
  * MITKC-2121- NIFA_Supplemental_Info_1_2-V1.2 User Attached form causing
  * STE- issue fixed
  * MITKC-2098-sf424 form title fieldvalidation issue fixed
  * vineeth on Sat, 18 Jul 2015 18:00:48 -0400 [View Commit](../../commit/7aa2cca7043b5bb988a2ae8534ece0b09db3e8b2)

##coeus-s2sgen-1509.0005
*  PD - S2S Forms screen Create PDF of Grants.gov Form CD-511 poorly formatted and illegible

  * MITKC-2100
  * vineeth on Thu, 23 Jul 2015 12:03:13 -0400 [View Commit](../../commit/7d884dea3af6fee5db7ec7517a68df95c5395c36)
* RESKC-837 : RRbudget form basesalary by period data printing issue fixed

  * MITKC-2124- RRbudget form basesalary by period data printing issue fixed
  * vineeth on Thu, 23 Jul 2015 16:22:06 -0400 [View Commit](../../commit/cce77152775b00960cb9b676e9c64e0955346537)

##coeus-s2sgen-1509.0004
* MITKC-2124- RRbudget form basesalary by period data printing issue fixed
  * vineeth on Thu, 23 Jul 2015 16:22:06 -0400 [View Commit](../../commit/6b3209a849da915475046cabc7627b63aef632ac)

##coeus-s2sgen-1509.0003
* MITKC-2100
  * vineeth on Thu, 23 Jul 2015 12:03:13 -0400 [View Commit](../../commit/6aa3d14e0244d5140ea4384f3f53bc702d0b3926)

##coeus-s2sgen-1509.0002
* No Changes


##coeus-s2sgen-1509.0001
* Changes to support external citizenship sources better
  * blackcathacker on Mon, 31 Aug 2015 18:51:54 -0700 [View Commit](../../commit/ad20fc32346bfbef55abf3f8b50f0ab92f6e6370)

##coeus-s2sgen-1508.0004
* No Changes


##coeus-s2sgen-1508.0003
* No Changes


##coeus-s2sgen-1508.0002
* No Changes


##coeus-s2sgen-1508.0001
* No Changes


##coeus-s2sgen-1507.2
* No Changes


##coeus-s2sgen-1507.1
*  Fixing print when apply rate flag is checked.
  * Gayathri Athreya on Wed, 8 Jul 2015 18:50:42 -0700 [View Commit](../../commit/600558cde27e3e456db1d8e0fb33192d205ae4f5)

##coeus-s2sgen-1506.2
*  If CFDA is null, do not add it to the element to the XML.
  * Gayathri Athreya on Wed, 17 Jun 2015 10:28:44 -0700 [View Commit](../../commit/22eb3c18ca2cf86f1595a9d339cee403d17ea67a)

##coeus-s2sgen-1506.1
* only pi/contact is required on s2s budget form, other roles are only displayed if they are on the budget

  * Fix required for RR budgets 1-2, 1-3
  * As a proposal creator, I need to add an external address book person to my proposal as Multi PI. The person is the Subcontract PI, and thus will not be assigned to personnel expense in my proposal budget; instead, they will appear in the uploaded subaward budget. But for purposes of other forms (Sr/Key Person); the external Multi PI must be listed in key personnel.
  * The current problem is that these external Multi PI's populate on the RR Budget form in the Senior Person section, even though I have not assigned them to the budget period. This undesired row will cause validation errors at NIH eCommons.
  * The RR Budget form does mandate that the PI is required on the budget form. BUT
  * Multi-PI's should ONLY appear on the RR budget forms if the user assigns their expense to the budget.

  * To duplicate:
  * Create a proposal using a federal sponsor (example NIH 000340)
  * Link an s2s opportunity that utilizes the RR Budget 1-3 form. (Example; PA-C-R01)
  * Add a Person to be PI.
  * Add a Rolodex person to be Multi PI
  * Save
  * create a budget version
  * Assign the PI to the budget; generate all periods & Save
  * Return to proposal
  * Toolbar > Print > Grants.gov Forms > RR Budget 1-3 -check to include, check select > create PDF.

  * Result:
  * Both the budget PI and the non-budget Multi-PI generate in the Senior Personnel section of the form.

  * Desired Result: Only the PI should be mandatory published to the budget forms. Multi-PI should only appear if they have been specifically assigned to this proposal budget.
  * Joe Williams on Tue, 2 Jun 2015 09:47:13 -0500 [View Commit](../../commit/79ce1962d4f86c1956ce11ddb95eed131854815f)

##coeus-s2sgen-1505.7
* No Changes


##coeus-s2sgen-1505.6
* Fixing javadoc for strict java 8 javadoc tool
  * Travis Schneberger on Thu, 14 May 2015 08:36:40 -0400 [View Commit](../../commit/7273285941470c58d0d68fd2032af53e9a9d7bfa)
* Move to Java 8
  * Travis Schneberger on Thu, 23 Apr 2015 16:13:24 -0400 [View Commit](../../commit/3fa101a52d43aa6a25c452f59e2f99a0b24dd085)
* Fixed STE on S2S form SF424

  * Continued testing of s2s forms
  * Located SF424 2.1 in opportunity: 03162012-TEST
  * Listed as 'available'
  * Uses proposal mapped data, must have authorized approver info and requires s2s questionnaire EO question only.
  * See proposal #448 in Res-Demo 1
  * (note: I can successfully print this form in MIT Coeus Consortium instance.)
  * Tried to print in Kualico and MIT QA: got this STE:

  * java.lang.NullPointerException at org.kuali.coeus.s2sgen.impl.generate.support.SF424V2_1Generator.getSF42421(SF424V2_1Generator.java:157) at org.kuali.coeus.s2sgen.impl.generate.support.SF424V2_1Generator.getSF42421Doc(SF424V2_1Generator.java:115) at org.kuali.coeus.s2sgen.impl.generate.support.SF424V2_1Generator.getFormObject(SF424V2_1Generator.java:576) at org.kuali.coeus.s2sgen.impl.print.FormPrintServiceImpl.getPDFStream(FormPrintServiceImpl.java:374) at
  * Joe Williams on Mon, 11 May 2015 14:27:40 -0500 [View Commit](../../commit/688abc6ec2aff6c628d16b7b7cacac269d362be4)
*  Fixing STE related to user attached forms.
  * If a user needs to attach particular files to be able to connect to an S2S opportunity, the system is not seeing the forms correctly and throws STE
  * For opportunity ID USDA-NIFA-SRGP-001930, user needs to add three forms to allow connection to S2S.
  * Prior to adding if user tries to connect to S2S, a good error message appears to show user which files are needed.
  * However, if those files are added, user gets STE when selecting the opportunity. See attached steps from MIT for this opportunity. Files used in testing are attached here also.
  * STE:
  * java.lang.RuntimeException: No resource found at org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/NIFA_Supplemental_Info_1_2-V1.2.xsl at org.kuali.coeus.s2sgen.impl.generate.FormMappingServiceImpl.createStylesheetName(FormMappingServiceImpl.java:108) at org.kuali.coeus.s2sgen.impl.generate.FormMappingServiceImpl.getUserAttachedForm(FormMappingServiceImpl.java:93) at org.kuali.coeus.s2sgen.impl.generate.FormMappingServiceImpl.getFormInfo(FormMappingServiceImpl.java:78) at org.kuali.coeus.propdev.impl.s2s.connect.OpportunitySchemaParserServiceImpl.getForms(OpportunitySchemaParserServiceImpl.java:130) at org.kuali.coeus.propdev.impl.s2s.S2sSubmissionServiceImpl.parseOpportunityForms(S2sSubmissionServiceImpl.java:485) at org.kuali.coeus.propdev.impl.s2s.ProposalDevelopmentS2SController.refresh(ProposalDevelopmentS2SController.java:128) at org.kuali.coeus.propdev.impl.s2s.ProposalDevelopmentS2SController$$FastClassBySpringCGLIB$$5f5dd18.invoke(<generated>) at
  * Gayathri on Wed, 29 Apr 2015 15:20:14 -0700 [View Commit](../../commit/742efb7773c50ffffe71752b7eeeb96b4489830e)
* update pom dependencies
  * Travis Schneberger on Thu, 16 Apr 2015 15:36:24 -0400 [View Commit](../../commit/e48fe1ace82cdf1198db875e4fea93b5871522b9)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:32:55 -0400 [View Commit](../../commit/50b94a46dc2ba199184101155e6d1b5b94880302)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:32:41 -0400 [View Commit](../../commit/215ce47f0213cfd5c6db2c53a8a76b68e1587391)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:32:16 -0400 [View Commit](../../commit/6b08216facab2492d83b668c52c0d2cf0d5974e8)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:16:33 -0400 [View Commit](../../commit/cea5ffe33f197a48f26d0d1328dab0d8d42ce326)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:16:15 -0400 [View Commit](../../commit/62f6091f686869332909e6e4ce633ad180077cfe)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:15:56 -0400 [View Commit](../../commit/79996a5de42e240c8b7d5e77ccb2b45932488c80)
* release plugin
  * Travis Schneberger on Sat, 4 Apr 2015 00:14:48 -0400 [View Commit](../../commit/925c967cdb75480e4bb13543420506b2b23b65b0)
* Update pom.xml  * Travis Schneeberger on Fri, 3 Apr 2015 22:59:56 -0400 [View Commit](../../commit/fa2363af5de242ae4a192d984828bc99a50d34a6)
* Update pom.xml  * Travis Schneeberger on Fri, 3 Apr 2015 22:59:34 -0400 [View Commit](../../commit/395e7eb1b471fa45b5de2db0cc7faac61517f0f5)
* Update pom.xml  * Travis Schneeberger on Fri, 3 Apr 2015 22:59:17 -0400 [View Commit](../../commit/69531f56c884c1bed7526d57edfe8f5b5887cb85)
* release plugin
  * Travis Schneberger on Fri, 3 Apr 2015 22:57:54 -0400 [View Commit](../../commit/3cde5bab218cca9a3aa26c2ef2c7e12058fd79de)
* release plugin
  * Travis Schneberger on Fri, 3 Apr 2015 21:13:09 -0400 [View Commit](../../commit/fe2ae08eb1003b79975f3ef1dd24322f52550c9d)
* remove dm section
  * Travis Schneberger on Fri, 27 Mar 2015 09:06:49 -0400 [View Commit](../../commit/fb89bb92fd946b32261442047b323d10425e4f6c)
* add s3 wagon
  * Travis Schneberger on Fri, 27 Mar 2015 09:04:02 -0400 [View Commit](../../commit/ec941bac64a32f9390aa6ebc83e5c98abba838fc)
* move to kualico build
  * Travis Schneberger on Fri, 27 Mar 2015 08:43:32 -0400 [View Commit](../../commit/b80b64f8142a63ab1409b9855f04396d63c321e3)
* review comments
  * Gayathri on Wed, 25 Mar 2015 15:54:24 -0700 [View Commit](../../commit/1391d63186844fdcaded6455caf73ee559c5a9e0)
*  new nsf cover page 1-6
  * Gayathri on Wed, 25 Mar 2015 09:52:41 -0700 [View Commit](../../commit/877fca3fc11fee1b89d5cea443d1fc4afb04955c)
* next iteration
  * Travis Schneberger on Mon, 16 Mar 2015 14:30:59 -0400 [View Commit](../../commit/a86e6cb460629dd588427b079ecadf0dbf69d94f)
* update pom dependencies
  * Travis Schneberger on Thu, 16 Apr 2015 15:36:24 -0400 [View Commit](../../commit/991700ddcd735bacc56adfcc5f653a0b840718ca)
* review comments
  * Gayathri on Wed, 25 Mar 2015 15:54:24 -0700 [View Commit](../../commit/869aef0e46070e19314710588e119ec5d80fb0b2)
*  new nsf cover page 1-6
  * Gayathri on Wed, 25 Mar 2015 09:52:41 -0700 [View Commit](../../commit/4b2e14076d2e2298c92d607c8164d2fb30744411)
* Update pom.xml
  * Travis Schneeberger on Sat, 4 Apr 2015 00:32:55 -0400 [View Commit](../../commit/81c335c147c094f1f6f8d040e0aba85dea594da6)
* Update pom.xml
  * Travis Schneeberger on Sat, 4 Apr 2015 00:32:41 -0400 [View Commit](../../commit/fff6153487580d663c4697e7714e001e09137b7b)
* Update pom.xml
  * Travis Schneeberger on Sat, 4 Apr 2015 00:32:16 -0400 [View Commit](../../commit/40bd017de329d2918b4fc95f00ba873ebedcaa80)
* Update pom.xml
  * Travis Schneeberger on Sat, 4 Apr 2015 00:16:33 -0400 [View Commit](../../commit/943936ac5af4b7deaaf0255c73bfcaf997abbb6b)
* Update pom.xml
  * Travis Schneeberger on Sat, 4 Apr 2015 00:16:15 -0400 [View Commit](../../commit/870cb8735797e5c4f7995f0da2a7b23420e181c6)
* Update pom.xml
  * Travis Schneeberger on Sat, 4 Apr 2015 00:15:56 -0400 [View Commit](../../commit/e42678d59bd9a711533ef73af9258ba3f4aa51dc)
* release plugin
  * Travis Schneberger on Sat, 4 Apr 2015 00:14:48 -0400 [View Commit](../../commit/c812b6586500c274d5d8004bd870291da616f4d1)
* Update pom.xml
  * Travis Schneeberger on Fri, 3 Apr 2015 22:59:56 -0400 [View Commit](../../commit/f751aa59a521f7906131162c3885e0f77185d6c1)
* Update pom.xml
  * Travis Schneeberger on Fri, 3 Apr 2015 22:59:34 -0400 [View Commit](../../commit/d6da43ccfebc0e48f2c3522eae5575a612f9f7cc)
* Update pom.xml
  * Travis Schneeberger on Fri, 3 Apr 2015 22:59:17 -0400 [View Commit](../../commit/36c2f7d343074cc0a63a680922a423aa802863c8)
* release plugin
  * Travis Schneberger on Fri, 3 Apr 2015 22:57:54 -0400 [View Commit](../../commit/939a5caa4fa4e97b39c5f2d6b95a57d2c86a617c)
* release plugin
  * Travis Schneberger on Fri, 3 Apr 2015 21:13:09 -0400 [View Commit](../../commit/3ec77ff11840b015544a8e2f17467910b64df7a2)
* remove dm section
  * Travis Schneberger on Fri, 27 Mar 2015 09:06:49 -0400 [View Commit](../../commit/d6c0a9c3bde43c9505256ce124a97de6c4e9c5e3)
* add s3 wagon
  * Travis Schneberger on Fri, 27 Mar 2015 09:04:02 -0400 [View Commit](../../commit/fe7e8c83a012f03b8fe0028cb118687c7af8310a)
* fixing error prone profile
  * Travis Schneberger on Fri, 27 Mar 2015 08:51:32 -0400 [View Commit](../../commit/77b354e0e369e2cf95079830fb3d948be964f516)
* move to kualico build
  * Travis Schneberger on Fri, 27 Mar 2015 08:43:32 -0400 [View Commit](../../commit/c00bddff261350d16ee9b4dd4098aa4cc3627be1)
* creates s2s error instead of throw exception for missing citizenship
  * Joe Williams on Mon, 23 Mar 2015 12:46:32 -0500 [View Commit](../../commit/bbc0eceae80cfb5d078c749f219755274b6a8713)
* next iteration
  * Travis Schneberger on Mon, 16 Mar 2015 14:30:59 -0400 [View Commit](../../commit/56cc9651350bde2d8df383dc7a7568b3267345b1)
*  fix training budget generator STE
  * Joe Williams on Fri, 13 Mar 2015 16:01:14 -0500 [View Commit](../../commit/8e73357f874f7a7e0e7b6379298d68a29e6e69f4)
*  fix material budget type mapping
  * Joe Williams on Thu, 12 Mar 2015 14:38:36 -0500 [View Commit](../../commit/a6bcab7b431336b34a0bca34685e555c52df07f7)

##coeus-s2sgen-1505.5
* No Changes


##coeus-s2sgen-1505.4
* Fixing javadoc for strict java 8 javadoc tool
  * Travis Schneberger on Thu, 14 May 2015 08:36:40 -0400 [View Commit](../../commit/93ee4fe982199d7f1ded03ab37a96e628551fbd3)

##coeus-s2sgen-1505.3
* Move to Java 8
  * Travis Schneberger on Thu, 23 Apr 2015 16:13:24 -0400 [View Commit](../../commit/d9146ad961aaa91782660e83a1270c2970bd8bdd)

##coeus-s2sgen-1505.2
* Fixed STE on S2S form SF424

  * Continued testing of s2s forms
  * Located SF424 2.1 in opportunity: 03162012-TEST
  * Listed as 'available'
  * Uses proposal mapped data, must have authorized approver info and requires s2s questionnaire EO question only.
  * See proposal #448 in Res-Demo 1
  * (note: I can successfully print this form in MIT Coeus Consortium instance.)
  * Tried to print in Kualico and MIT QA: got this STE:

  * java.lang.NullPointerException at org.kuali.coeus.s2sgen.impl.generate.support.SF424V2_1Generator.getSF42421(SF424V2_1Generator.java:157) at org.kuali.coeus.s2sgen.impl.generate.support.SF424V2_1Generator.getSF42421Doc(SF424V2_1Generator.java:115) at org.kuali.coeus.s2sgen.impl.generate.support.SF424V2_1Generator.getFormObject(SF424V2_1Generator.java:576) at org.kuali.coeus.s2sgen.impl.print.FormPrintServiceImpl.getPDFStream(FormPrintServiceImpl.java:374) at
  * Joe Williams on Mon, 11 May 2015 14:27:40 -0500 [View Commit](../../commit/a0af6190219dc324acd1f0ff75d95fbae6292dca)

##coeus-s2sgen-1505.1
* No Changes


##coeus-s2sgen-1504.7
*  Fixing STE related to user attached forms.
  * If a user needs to attach particular files to be able to connect to an S2S opportunity, the system is not seeing the forms correctly and throws STE
  * For opportunity ID USDA-NIFA-SRGP-001930, user needs to add three forms to allow connection to S2S.
  * Prior to adding if user tries to connect to S2S, a good error message appears to show user which files are needed.
  * However, if those files are added, user gets STE when selecting the opportunity. See attached steps from MIT for this opportunity. Files used in testing are attached here also.
  * STE:
  * java.lang.RuntimeException: No resource found at org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/NIFA_Supplemental_Info_1_2-V1.2.xsl at org.kuali.coeus.s2sgen.impl.generate.FormMappingServiceImpl.createStylesheetName(FormMappingServiceImpl.java:108) at org.kuali.coeus.s2sgen.impl.generate.FormMappingServiceImpl.getUserAttachedForm(FormMappingServiceImpl.java:93) at org.kuali.coeus.s2sgen.impl.generate.FormMappingServiceImpl.getFormInfo(FormMappingServiceImpl.java:78) at org.kuali.coeus.propdev.impl.s2s.connect.OpportunitySchemaParserServiceImpl.getForms(OpportunitySchemaParserServiceImpl.java:130) at org.kuali.coeus.propdev.impl.s2s.S2sSubmissionServiceImpl.parseOpportunityForms(S2sSubmissionServiceImpl.java:485) at org.kuali.coeus.propdev.impl.s2s.ProposalDevelopmentS2SController.refresh(ProposalDevelopmentS2SController.java:128) at org.kuali.coeus.propdev.impl.s2s.ProposalDevelopmentS2SController$$FastClassBySpringCGLIB$$5f5dd18.invoke(<generated>) at
  * Gayathri on Wed, 29 Apr 2015 15:20:14 -0700 [View Commit](../../commit/089ccb4514f7e75b05d2709b5f7e8c18c5eba755)

##coeus-s2sgen-1504.6
* No Changes


##coeus-s2sgen-1504.5
* No Changes


##coeus-s2sgen-1504.4
* No Changes


##coeus-s2sgen-1504.3
* No Changes


##coeus-s2sgen-1504.2
* No Changes


##coeus-s2sgen-1504.1
* No Changes


##coeus-s2sgen-6.0.3.1
* update pom dependencies
  * Travis Schneberger on Thu, 16 Apr 2015 15:36:24 -0400 [View Commit](../../commit/01d62b2940b0cfda86d1b9354c4d70c032dd4475)

##coeus-s2sgen-6.0.3
* review comments
  * Gayathri on Wed, 25 Mar 2015 15:54:24 -0700 [View Commit](../../commit/bb93a01ccf4633c2cabe956f333704a0e49e3f03)
*  new nsf cover page 1-6
  * Gayathri on Wed, 25 Mar 2015 09:52:41 -0700 [View Commit](../../commit/f193cb68f705d5493d39c2ef774410b2247542ff)

##coeus-s2sgen-6.0.2
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:32:55 -0400 [View Commit](../../commit/358d7761dd0477ac715108f027a1fe55e0b3a317)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:32:41 -0400 [View Commit](../../commit/45a167f68cb7f90dbe9e035392882acdae3e0ce7)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:32:16 -0400 [View Commit](../../commit/d0c1d2fa39b7ea0d252cfbe67c1d3f6025a09f35)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:16:33 -0400 [View Commit](../../commit/2af2024240087a04688e984448e6a968e58c62ca)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:16:15 -0400 [View Commit](../../commit/151daa7ba1e4c25280ee5125378ac6029311c127)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:15:56 -0400 [View Commit](../../commit/f719c8a4ffdc4a45871fe4f21bc75dc4e3cc8383)
* release plugin
  * Travis Schneberger on Sat, 4 Apr 2015 00:14:48 -0400 [View Commit](../../commit/cbc22d1757008d7f372725a8c7c7d47ce60716a1)
* Update pom.xml  * Travis Schneeberger on Fri, 3 Apr 2015 22:59:56 -0400 [View Commit](../../commit/009846883136c8587a3ca47044f205bbd3568532)
* Update pom.xml  * Travis Schneeberger on Fri, 3 Apr 2015 22:59:34 -0400 [View Commit](../../commit/1547ea7f2b24f656507760339930336cfe1e6866)
* Update pom.xml  * Travis Schneeberger on Fri, 3 Apr 2015 22:59:17 -0400 [View Commit](../../commit/60851158e2a989b6b15bb7e80e6275c0e010ce63)
* release plugin
  * Travis Schneberger on Fri, 3 Apr 2015 22:57:54 -0400 [View Commit](../../commit/89baebea41e48326aa8aea6f3b1c8ad2de7fb72f)
* release plugin
  * Travis Schneberger on Fri, 3 Apr 2015 21:13:09 -0400 [View Commit](../../commit/62b6de8d1067e7cd987c188a1e7324fa23e67691)
* remove dm section
  * Travis Schneberger on Fri, 27 Mar 2015 09:06:49 -0400 [View Commit](../../commit/b3a0fe0d34f29d20871a067a1df15069941cff7b)
* add s3 wagon
  * Travis Schneberger on Fri, 27 Mar 2015 09:04:02 -0400 [View Commit](../../commit/ff9d45e688278a9d1354677b16e0893fbf684698)
* fixing error prone profile
  * Travis Schneberger on Fri, 27 Mar 2015 08:51:32 -0400 [View Commit](../../commit/78b7888bebcbe6282048336aceb7fba2d0ce4943)
* move to kualico build
  * Travis Schneberger on Fri, 27 Mar 2015 08:43:32 -0400 [View Commit](../../commit/1dc72658e52d216283f66058b256cb74a30c5d8a)
* creates s2s error instead of throw exception for missing citizenship
  * Joe Williams on Mon, 23 Mar 2015 12:46:32 -0500 [View Commit](../../commit/1626bfda664f611d35246b89a0cccfed727f958b)
* next iteration
  * Travis Schneberger on Mon, 16 Mar 2015 14:30:59 -0400 [View Commit](../../commit/a87e56db1ab6247a6602f6b1a297347bc8f214a9)
*  fix training budget generator STE
  * Joe Williams on Fri, 13 Mar 2015 16:01:14 -0500 [View Commit](../../commit/fdea55383a3f74d31f75783c21d3dfcaa3a9a18d)
*  fix material budget type mapping
  * Joe Williams on Thu, 12 Mar 2015 14:38:36 -0500 [View Commit](../../commit/7027867253b55a22d39efa7387b072acd248548b)

##coeus-s2sgen-6.0.1
* KRACOEUS-8949: non snapshot
  * Travis Schneberger on Mon, 16 Mar 2015 09:05:10 -0400 [View Commit](../../commit/edb45d4004dcf7b425e715caab3af7cfb17e618a)
* KRACOEUS-8905:still return errors if printing xml
  * Joe Williams on Wed, 25 Feb 2015 13:05:40 -0600 [View Commit](../../commit/06fa1bc3dd0f755772cb19e5f0718c6da60b58d9)
* KRACOEUS-8867: changing git location
  * Travis Schneberger on Tue, 17 Feb 2015 13:13:23 -0500 [View Commit](../../commit/7c6b628cf9078b3d27b508db25ee7e1d958624fc)
* KRACOEUS-7969: truncating long department names
  * Travis Schneberger on Tue, 17 Feb 2015 09:59:30 -0500 [View Commit](../../commit/3a66bcee1b7dcc66864451131ee3435dd328606c)
* KRACOEUS-8807:fixed budget form when no description is entered for equipment
  * Joe Williams on Mon, 9 Feb 2015 14:02:07 -0600 [View Commit](../../commit/a25cd9ed464d4ec9e2cb0c886719fadf420d6815)
* KRACOEUS-8764:fix additional equipment system generated attachments
  * Joe Williams on Mon, 2 Feb 2015 09:04:20 -0600 [View Commit](../../commit/b0d1ba4f5de382a130c3d536553928ef13cf95ae)
* git@github.com instead of https
  * Jeff Caddel on Sat, 31 Jan 2015 11:06:51 -0800 [View Commit](../../commit/a1e623a3c2ea6312abbfc0e73f1eea247fd93757)
* Update pom.xml  * Travis Schneeberger on Sat, 31 Jan 2015 13:49:07 -0500 [View Commit](../../commit/44c0df470db45744cdf685294a1200c15845a696)
* KRACOEUS-8762: lic changes
  * Travis Schneberger on Fri, 30 Jan 2015 16:56:25 -0500 [View Commit](../../commit/8768f3af96c710f38507d6b4713cd738518d7324)
* Revert "KRACOEUS-8750: sprint 14"
  * Travis Schneeberger on Mon, 26 Jan 2015 21:15:02 -0500 [View Commit](../../commit/24b3d8d39318a65f10a2c2588f1a6bc15025d77e)

##coeus-s2sgen-6.0.0-s14
* KRACOEUS-8750: sprint 14
  * Travis Schneberger on Mon, 26 Jan 2015 20:40:26 -0500 [View Commit](../../commit/aaef70962949eede7d8f7a934449586b55200b4a)
* KRACOEUS-8494  S2S Print forms: After first print attempt, no message
  * given when printing all forms and there are errors on some forms that
  * prevent them from being printed  * mrudulpolus on Thu, 8 Jan 2015 20:13:17 +0530 [View Commit](../../commit/12d9cc4504ed1412b06167b5a76611538818e22d)
* Revert "KRACOEUS-8701: sprint 13"
  * Travis Schneeberger on Mon, 12 Jan 2015 20:55:03 -0500 [View Commit](../../commit/e282b201f278b652db8f986ab7a33cf90894686c)

##coeus-s2sgen-6.0.0-s13
* KRACOEUS-8701: sprint 13
  * Travis Schneberger on Mon, 12 Jan 2015 19:57:11 -0500 [View Commit](../../commit/c672c8d26bfe33bade36a2b407fe01656e0a0146)
* KRACOEUS-8685: truncating division length to 30 - more generators
  * Travis Schneberger on Fri, 9 Jan 2015 13:41:20 -0500 [View Commit](../../commit/32af7d96bf6fef7cbe7c9cd3f259a2a6e169b7ad)
* KRACOEUS-8685: truncating division length to 30
  * Travis Schneberger on Fri, 9 Jan 2015 11:44:57 -0500 [View Commit](../../commit/563c4bc07a852ebe123e2a374238853b8fcd2166)
* Revert "KRACOEUS-8614: sprint 12"
  * Travis Schneeberger on Mon, 29 Dec 2014 12:40:29 -0500 [View Commit](../../commit/a3de41b6eb7a49dbcb1f80b503392a362ca0ebb9)
* KRACOEUS-8564
  * KRAD: Base Salary not mapping to RR Budget forms when 'Salary by Period'
section not completed (should default in Base Salary)  * boneypolus on Tue, 23 Dec 2014 16:34:49 +0530 [View Commit](../../commit/7b4937a91600f3c7e0c96fbf8cdf10ed797fc4c4)

##coeus-s2sgen-6.0.0-s12
* KRACOEUS-8614: sprint 12
  * Travis Schneberger on Mon, 29 Dec 2014 12:06:37 -0500 [View Commit](../../commit/2c650d2172b00eca9284b770e821aefbdf7c4319)
* KRACOEUS-8594 : Fix for blank country codes throwing exceptions

  * CountryService and KcCountryService both throw IllegalArgumentException if the country code is blank, but blank country codes are allowed by validations.
  * blackcathacker on Fri, 19 Dec 2014 17:29:34 -0800 [View Commit](../../commit/7da6ccc8c92f9bada6e91f379abbe2ba0b804568)
* KCSUPPORT-601  Other Degree Information not printing on grants.gov form
  * which should retrieve from the PHS398 Fellowshop Supplimental V2
  * questionnaire  * anithapolus on Wed, 17 Dec 2014 18:52:40 +0530 [View Commit](../../commit/5808f3e1a7dacce04857076d432cfc90a7cd9426)
* Revert "KRACOEUS-8557: sprint 11"
  * Travis Schneeberger on Mon, 15 Dec 2014 21:09:43 -0500 [View Commit](../../commit/d59df169d9389f46d86998e2a2880316609b2927)

##coeus-s2sgen-6.0.0-s11
* KRACOEUS-8557: sprint 11
  * Travis Schneberger on Mon, 15 Dec 2014 20:17:58 -0500 [View Commit](../../commit/9097b5fa90206e38634995cd16f477f311a84497)
* KRACOEUS-8495
  * PD UXI: Unable to print performance site S2S form if if you add a
  * performance site location or organization  * mrudulpolus on Wed, 10 Dec 2014 19:31:49 +0530 [View Commit](../../commit/9451bb4c758e5aaf626ebf00c5f3a3fa9991b48f)
* KRACOEUS-8472
  * Grants.gov: printed 'RR_FedNonFed_SubawardBudget10_10-V1.2' form is
  * incorrect version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 23:06:44 +0530 [View Commit](../../commit/d0b9092f1ae65b1f95f420c54f67bfa8b8b1c1c5)
* KRACOEUS-8441
  * Grants.gov: printed 'RR_SubawardBudget-V1.2' form - Formatting issues  * mrudulpolus on Tue, 9 Dec 2014 22:49:51 +0530 [View Commit](../../commit/d433739d1dea15acbc67f1bebd95d148123e23f3)
* KRACOEUS-8437
  * Grants.gov: printed 'RR_FedNonFed_SubawardBudget10_30-V1.2' form is
  * incorrect version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 20:27:00 +0530 [View Commit](../../commit/c7c402b9be9605dd9f3f3f9ddad670657ca5a7a5)
* KRACOEUS-8439
  * Grants.gov: printed 'RR_SubawardBudget10_10-V1.2' form is incorrect
  * version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 19:41:22 +0530 [View Commit](../../commit/ff4b2451714f22b3a495c66b5ee9511bd61d26b2)
* KRACOEUS-8440
  * Grants.gov: printed 'RR_SubawardBudget10_30-V1.2' form is incorrect
  * version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 17:04:03 +0530 [View Commit](../../commit/dc66ad461b7a7c157f32445593a000a13c13abe0)
* KRACOEUS-8438
  * Grants.gov: printed 'RR_FedNonFed_SubawardBudget-V1.2' form is incorrect
  * version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 19:12:44 +0530 [View Commit](../../commit/a910e6971aa61243d632b4823e9440414343cfd9)
* KRACOEUS-8414  Summary Budget funds are not mapping to the SF424 R&R
  * v1-2, possibly all versions  * anithapolus on Mon, 8 Dec 2014 23:28:55 +0530 [View Commit](../../commit/e55531e51c0f44e1b326450178c07b122478970d)
* KRACOEUS-8285: s10, renaming version
  * Travis Schneberger on Wed, 3 Dec 2014 17:07:52 -0500 [View Commit](../../commit/a2851c1456d2283304d161cf31ed68175db4e0bc)
* Revert "KRACOEUS-8457: spring 10"
  * Travis Schneeberger on Wed, 3 Dec 2014 16:57:53 -0500 [View Commit](../../commit/0d729f348618af46e7d72fec8b3c8b371dad5208)

##coeus-s2sgen-6.0.0-s10
* KRACOEUS-8457: spring 10
  * Travis Schneberger on Wed, 3 Dec 2014 00:27:45 -0500 [View Commit](../../commit/d56f2804561681c3fd3735cbf766d099e9124917)
* KRACOEUS-8221 : Intg Test Fixes
  * blackcathacker on Tue, 25 Nov 2014 12:11:21 -0800 [View Commit](../../commit/dd97abe218031abc55b2308715098710dcf223c1)
* KRACOEUS-8407: update pom
  * Travis Schneberger on Mon, 24 Nov 2014 21:04:05 -0500 [View Commit](../../commit/2254314798c5aaf3b91075529f37d85315ed696f)
* KRACOEUS-8384:fixed issue with missing AOR fax not being valid
  * Joe Williams on Fri, 21 Nov 2014 08:25:27 -0600 [View Commit](../../commit/45a46912033e5afb39ee4f2892d29d48039ab7a7)
* KRACOEUS-7413	
  * R&R 424 and Key Person Expanded form not printing correct department
  * information  * mrudulpolus on Wed, 19 Nov 2014 19:30:08 +0530 [View Commit](../../commit/e6c2ec871ee8de0189819335df1c7f288251f222)
* KRACOEUS-8012 Non-employees are appearing as PD/PI on S2S forms
  * mrudulpolus on Mon, 10 Nov 2014 15:09:27 +0530 [View Commit](../../commit/e54e9fe77e42bb2c0c0f38e6393fbd4018cb0d38)
* KRACOEUS-8003
  * Sub award budget not print correctly  * mrudulpolus on Tue, 11 Nov 2014 14:49:31 +0530 [View Commit](../../commit/111acfe79a7923d6dd6a92f70a3ddae91ab3ac7d)
* Revert "KRACOEUS-8285: tagging release"
  * Travis Schneeberger on Mon, 17 Nov 2014 21:08:58 -0500 [View Commit](../../commit/eb23f53fca5eb4073357952074241be9315edcd7)

##coeus-s2sgen-6.0.0-s9
* KRACOEUS-8285: tagging release
  * Travis Schneberger on Mon, 17 Nov 2014 20:22:39 -0500 [View Commit](../../commit/dab73d1b36d580d8b3029f981437f80ccf5edf14)
* KRACOEUS-8303:fixed RRSF424 generators so validation won't fail if fax number is present
  * Joe Williams on Thu, 6 Nov 2014 10:03:08 -0600 [View Commit](../../commit/d0341e71a3be905318e3e685a42f0e5bf1991eb0)
* KRACOEUS-8302:fixed STE on navigation with data validation turn on due to no file attached to personnel attachment
  * Joe Williams on Wed, 5 Nov 2014 16:24:46 -0600 [View Commit](../../commit/335d13f66a025cfe9503b034a5ea7c0cae39f886)
* Revert "KRACOEUS-8285: tagging release"
  * Travis Schneeberger on Mon, 3 Nov 2014 20:51:33 -0500 [View Commit](../../commit/43e4ec3acb4a94d0abef039bba4977fe36f787f3)

##coeus-s2sgen-6.0.0-s8
* No Changes


##coeus-s2sgen-6.0.0
* git@github.com instead of https
  * Jeff Caddel on Sat, 31 Jan 2015 11:06:51 -0800 [View Commit](../../commit/a1e623a3c2ea6312abbfc0e73f1eea247fd93757)
* Update pom.xml  * Travis Schneeberger on Sat, 31 Jan 2015 13:49:07 -0500 [View Commit](../../commit/44c0df470db45744cdf685294a1200c15845a696)
* KRACOEUS-8762: lic changes
  * Travis Schneberger on Fri, 30 Jan 2015 16:56:25 -0500 [View Commit](../../commit/8768f3af96c710f38507d6b4713cd738518d7324)
* Revert "KRACOEUS-8750: sprint 14"
  * Travis Schneeberger on Mon, 26 Jan 2015 21:15:02 -0500 [View Commit](../../commit/24b3d8d39318a65f10a2c2588f1a6bc15025d77e)
* KRACOEUS-8750: sprint 14
  * Travis Schneberger on Mon, 26 Jan 2015 20:40:26 -0500 [View Commit](../../commit/aaef70962949eede7d8f7a934449586b55200b4a)
* KRACOEUS-8494  S2S Print forms: After first print attempt, no message
  * given when printing all forms and there are errors on some forms that
  * prevent them from being printed  * mrudulpolus on Thu, 8 Jan 2015 20:13:17 +0530 [View Commit](../../commit/12d9cc4504ed1412b06167b5a76611538818e22d)
* Revert "KRACOEUS-8701: sprint 13"
  * Travis Schneeberger on Mon, 12 Jan 2015 20:55:03 -0500 [View Commit](../../commit/e282b201f278b652db8f986ab7a33cf90894686c)
* KRACOEUS-8701: sprint 13
  * Travis Schneberger on Mon, 12 Jan 2015 19:57:11 -0500 [View Commit](../../commit/c672c8d26bfe33bade36a2b407fe01656e0a0146)
* KRACOEUS-8685: truncating division length to 30 - more generators
  * Travis Schneberger on Fri, 9 Jan 2015 13:41:20 -0500 [View Commit](../../commit/32af7d96bf6fef7cbe7c9cd3f259a2a6e169b7ad)
* KRACOEUS-8685: truncating division length to 30
  * Travis Schneberger on Fri, 9 Jan 2015 11:44:57 -0500 [View Commit](../../commit/563c4bc07a852ebe123e2a374238853b8fcd2166)
* Revert "KRACOEUS-8614: sprint 12"
  * Travis Schneeberger on Mon, 29 Dec 2014 12:40:29 -0500 [View Commit](../../commit/a3de41b6eb7a49dbcb1f80b503392a362ca0ebb9)
* KRACOEUS-8614: sprint 12
  * Travis Schneberger on Mon, 29 Dec 2014 12:06:37 -0500 [View Commit](../../commit/2c650d2172b00eca9284b770e821aefbdf7c4319)
* KRACOEUS-8564
  * KRAD: Base Salary not mapping to RR Budget forms when 'Salary by Period'
section not completed (should default in Base Salary)  * boneypolus on Tue, 23 Dec 2014 16:34:49 +0530 [View Commit](../../commit/7b4937a91600f3c7e0c96fbf8cdf10ed797fc4c4)
* KRACOEUS-8594 : Fix for blank country codes throwing exceptions

  * CountryService and KcCountryService both throw IllegalArgumentException if the country code is blank, but blank country codes are allowed by validations.
  * blackcathacker on Fri, 19 Dec 2014 17:29:34 -0800 [View Commit](../../commit/7da6ccc8c92f9bada6e91f379abbe2ba0b804568)
* KCSUPPORT-601  Other Degree Information not printing on grants.gov form
  * which should retrieve from the PHS398 Fellowshop Supplimental V2
  * questionnaire  * anithapolus on Wed, 17 Dec 2014 18:52:40 +0530 [View Commit](../../commit/5808f3e1a7dacce04857076d432cfc90a7cd9426)
* Revert "KRACOEUS-8557: sprint 11"
  * Travis Schneeberger on Mon, 15 Dec 2014 21:09:43 -0500 [View Commit](../../commit/d59df169d9389f46d86998e2a2880316609b2927)
* KRACOEUS-8557: sprint 11
  * Travis Schneberger on Mon, 15 Dec 2014 20:17:58 -0500 [View Commit](../../commit/9097b5fa90206e38634995cd16f477f311a84497)
* KRACOEUS-8495
  * PD UXI: Unable to print performance site S2S form if if you add a
  * performance site location or organization  * mrudulpolus on Wed, 10 Dec 2014 19:31:49 +0530 [View Commit](../../commit/9451bb4c758e5aaf626ebf00c5f3a3fa9991b48f)
* KRACOEUS-8472
  * Grants.gov: printed 'RR_FedNonFed_SubawardBudget10_10-V1.2' form is
  * incorrect version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 23:06:44 +0530 [View Commit](../../commit/d0b9092f1ae65b1f95f420c54f67bfa8b8b1c1c5)
* KRACOEUS-8441
  * Grants.gov: printed 'RR_SubawardBudget-V1.2' form - Formatting issues  * mrudulpolus on Tue, 9 Dec 2014 22:49:51 +0530 [View Commit](../../commit/d433739d1dea15acbc67f1bebd95d148123e23f3)
* KRACOEUS-8437
  * Grants.gov: printed 'RR_FedNonFed_SubawardBudget10_30-V1.2' form is
  * incorrect version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 20:27:00 +0530 [View Commit](../../commit/c7c402b9be9605dd9f3f3f9ddad670657ca5a7a5)
* KRACOEUS-8439
  * Grants.gov: printed 'RR_SubawardBudget10_10-V1.2' form is incorrect
  * version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 19:41:22 +0530 [View Commit](../../commit/ff4b2451714f22b3a495c66b5ee9511bd61d26b2)
* KRACOEUS-8440
  * Grants.gov: printed 'RR_SubawardBudget10_30-V1.2' form is incorrect
  * version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 17:04:03 +0530 [View Commit](../../commit/dc66ad461b7a7c157f32445593a000a13c13abe0)
* KRACOEUS-8438
  * Grants.gov: printed 'RR_FedNonFed_SubawardBudget-V1.2' form is incorrect
  * version of subaward attachment form  * mrudulpolus on Tue, 9 Dec 2014 19:12:44 +0530 [View Commit](../../commit/a910e6971aa61243d632b4823e9440414343cfd9)
* KRACOEUS-8414  Summary Budget funds are not mapping to the SF424 R&R
  * v1-2, possibly all versions  * anithapolus on Mon, 8 Dec 2014 23:28:55 +0530 [View Commit](../../commit/e55531e51c0f44e1b326450178c07b122478970d)
* KRACOEUS-8285: s10, renaming version
  * Travis Schneberger on Wed, 3 Dec 2014 17:07:52 -0500 [View Commit](../../commit/a2851c1456d2283304d161cf31ed68175db4e0bc)
* Revert "KRACOEUS-8457: spring 10"
  * Travis Schneeberger on Wed, 3 Dec 2014 16:57:53 -0500 [View Commit](../../commit/0d729f348618af46e7d72fec8b3c8b371dad5208)
* KRACOEUS-8457: spring 10
  * Travis Schneberger on Wed, 3 Dec 2014 00:27:45 -0500 [View Commit](../../commit/d56f2804561681c3fd3735cbf766d099e9124917)
* KRACOEUS-8221 : Intg Test Fixes
  * blackcathacker on Tue, 25 Nov 2014 12:11:21 -0800 [View Commit](../../commit/dd97abe218031abc55b2308715098710dcf223c1)
* KRACOEUS-8407: update pom
  * Travis Schneberger on Mon, 24 Nov 2014 21:04:05 -0500 [View Commit](../../commit/2254314798c5aaf3b91075529f37d85315ed696f)
* KRACOEUS-8384:fixed issue with missing AOR fax not being valid
  * Joe Williams on Fri, 21 Nov 2014 08:25:27 -0600 [View Commit](../../commit/45a46912033e5afb39ee4f2892d29d48039ab7a7)
* KRACOEUS-7413	
  * R&R 424 and Key Person Expanded form not printing correct department
  * information  * mrudulpolus on Wed, 19 Nov 2014 19:30:08 +0530 [View Commit](../../commit/e6c2ec871ee8de0189819335df1c7f288251f222)
* KRACOEUS-8012 Non-employees are appearing as PD/PI on S2S forms
  * mrudulpolus on Mon, 10 Nov 2014 15:09:27 +0530 [View Commit](../../commit/e54e9fe77e42bb2c0c0f38e6393fbd4018cb0d38)
* KRACOEUS-8003
  * Sub award budget not print correctly  * mrudulpolus on Tue, 11 Nov 2014 14:49:31 +0530 [View Commit](../../commit/111acfe79a7923d6dd6a92f70a3ddae91ab3ac7d)
* Revert "KRACOEUS-8285: tagging release"
  * Travis Schneeberger on Mon, 17 Nov 2014 21:08:58 -0500 [View Commit](../../commit/eb23f53fca5eb4073357952074241be9315edcd7)
* KRACOEUS-8285: tagging release
  * Travis Schneberger on Mon, 17 Nov 2014 20:22:39 -0500 [View Commit](../../commit/dab73d1b36d580d8b3029f981437f80ccf5edf14)
* KRACOEUS-8303:fixed RRSF424 generators so validation won't fail if fax number is present
  * Joe Williams on Thu, 6 Nov 2014 10:03:08 -0600 [View Commit](../../commit/d0341e71a3be905318e3e685a42f0e5bf1991eb0)
* KRACOEUS-8302:fixed STE on navigation with data validation turn on due to no file attached to personnel attachment
  * Joe Williams on Wed, 5 Nov 2014 16:24:46 -0600 [View Commit](../../commit/335d13f66a025cfe9503b034a5ea7c0cae39f886)
* Revert "KRACOEUS-8285: tagging release"
  * Travis Schneeberger on Mon, 3 Nov 2014 20:51:33 -0500 [View Commit](../../commit/43e4ec3acb4a94d0abef039bba4977fe36f787f3)
* KRACOEUS-8285: tagging release
  * Travis Schneberger on Mon, 3 Nov 2014 20:04:18 -0500 [View Commit](../../commit/bc3753053d3a3428c0c87350ef0e4fc7fac3569f)
* KRACOEUS-8270: fixing generators
  * Travis Schneberger on Fri, 31 Oct 2014 16:11:51 -0400 [View Commit](../../commit/52d8d796a4ad1a0f7f2920be21118d34277c5be0)
* KRACOEUS-8232:if narrative allows multiple attachments use description as file name on s2s forms
  * Joe Williams on Tue, 28 Oct 2014 16:31:17 -0500 [View Commit](../../commit/cc19034e40d5199189025e55028891a8dca0d57a)
* Fixing Questionnaire 129, 130, 131 questions for RRSF424
  * Geo Thomas on Sun, 26 Oct 2014 20:22:52 -0400 [View Commit](../../commit/7282a8418d1498dfdcd89369286e282e5dd05184)
* KRACOEUS-8192: fixing generator
  * Travis Schneberger on Fri, 24 Oct 2014 18:15:27 -0400 [View Commit](../../commit/9921c85441a8ed4eff669073305b6816dd9cc283)
* KRACOEUS-8192 : Fixing questionId for RRSF424 1.2 version
  * Geo Thomas on Fri, 24 Oct 2014 17:51:09 -0400 [View Commit](../../commit/d37e0f1eb2f382e631744b28783a3df2abca9883)
* KRACOEUS-8132  RRSubAwardBudget10_30_1_3V1_3GeneratorTest  * anithapolus on Mon, 20 Oct 2014 19:11:20 +0530 [View Commit](../../commit/8a45a4d74a1ac775a1fd53fe38fc29a2975defc5)
* KRACOEUS-8130 RRSubAwardBudget5_30V1_2GeneratorTest
  * anithapolus on Mon, 20 Oct 2014 17:48:09 +0530 [View Commit](../../commit/7bab69e908ff87d405e2975763d195f2a27dfdd7)
* KRACOEUS-8085 fix:org.kuali.kra.s2s.rrbudget.RRBudget10V1_3GeneratorTest  * sasipolus on Fri, 17 Oct 2014 15:11:07 +0530 [View Commit](../../commit/d35799aaf2bd1b02b880be20fd80e5ca73f92d7f)
* KRACOEUS-8086 fix: org.kuali.kra.s2s.rrbudget.RRBudgetV1_3GeneratorTest  * boneypolus on Thu, 16 Oct 2014 19:04:55 +0530 [View Commit](../../commit/be37673f457339b606a4cc463a3eb5a7768bbf26)
* KRACOEUS-8088
  * fix:org.kuali.kra.s2s.rrfednonfedbudget.RRFedNonFedBudgetV1_1GeneratorTest  * sasipolus on Thu, 16 Oct 2014 15:35:16 +0530 [View Commit](../../commit/1ad7e0c346042f8741763bcd7dd80b8284322a71)
* KRACOEUS-8087 fix:
  * org.kuali.kra.s2s.rrfednonfedbudget.RRFedNonFedBudget10V1_1GeneratorTest  * sasipolus on Thu, 16 Oct 2014 15:12:17 +0530 [View Commit](../../commit/9983b7807820e5dd26c054075acc1754ee16c9bb)
* KRACOEUS-8099:changed s2s generator to use narrativeType as attachment file name
  * Joe Williams on Wed, 15 Oct 2014 10:22:08 -0500 [View Commit](../../commit/01281cf6d6ab49e7dd8b502fbe59a216defa8ee4)
* KRACOEUS-7786 : Remove final flag from budget
  * blackcathacker on Fri, 19 Sep 2014 17:17:58 -0700 [View Commit](../../commit/22c2f5dba6059f88bf35574565410156f34b137c)
* KRACOEUS-7847:fixing issue with state review questions not being picked up by form generators
  * Joe Williams on Fri, 19 Sep 2014 14:07:56 -0500 [View Commit](../../commit/cd06777fb1ff891c3c70cba60e14d114200a5b26)
* KRACOEUS-7539:fixed style sheet path for user attached forms
  * Joe Williams on Wed, 17 Sep 2014 16:26:39 -0500 [View Commit](../../commit/69fbac7c800eb6656c5c5a76ada41a1dfd45cae6)
* KRACOEUS-7539:fixed issue with getting formMappingInfo with user attached forms
  * Joe Williams on Wed, 17 Sep 2014 13:29:57 -0500 [View Commit](../../commit/e793baf69b70b572ea42f745cfe8184436b78f69)
* KRACOEUS-7539: add validate user attached form
  * Joe Williams on Tue, 16 Sep 2014 12:46:09 -0500 [View Commit](../../commit/39e424842b149a7509937b12d7a27beca9f1d6d7)
* KRACOEUS-7866: increasing javadoc memory
  * Travis Schneberger on Fri, 29 Aug 2014 15:37:05 -0400 [View Commit](../../commit/0c1702a3a4cc0b27d90e15c0e1f7e18ee2299e36)
* KRACOEUS-7719: fixing a variety of generator errors
  * Travis Schneberger on Fri, 29 Aug 2014 13:59:10 -0400 [View Commit](../../commit/ba458311fb508ce5d84eb9c8572b10281dbf32c8)
* KRACOEUS-7776
  * fix RRSF424_2_0_V2GeneratorTest.testPrintForm  * mrudulpolus on Fri, 22 Aug 2014 17:32:07 +0530 [View Commit](../../commit/e48e20b55705153c970b5c041de68f32303a21e9)
* KRACOEUS-7773	
  * fix RRFedNonFedBudget10V1_1GeneratorTest.testPrintForm  * mrudulpolus on Fri, 22 Aug 2014 16:36:19 +0530 [View Commit](../../commit/35ee0b1095e90e1d9ad1338c9e6489e54d7f5e74)
* KRACOEUS-7770
  * fix RRSubAwardBudget10_30_1_3V1_3GeneratorTest.testPrintForm  * mrudulpolus on Fri, 22 Aug 2014 15:16:54 +0530 [View Commit](../../commit/48969e12710be410a49abe07ead00c3d36cf2d6f)
* KRACOEUS-7790: not registering user attached form (or when namespace of form isn't directly supported)
  * Travis Schneberger on Tue, 19 Aug 2014 11:31:02 -0400 [View Commit](../../commit/3d30ee774b815228ba30ed75987f7ad72dbebc91)
* moving code from KC proper
  * Travis Schneberger on Fri, 1 Aug 2014 16:12:57 -0400 [View Commit](../../commit/ea985835cbf011d964020d2a21491d2c91d97fcf)
* first commit
  * Travis Schneberger on Fri, 1 Aug 2014 15:47:37 -0400 [View Commit](../../commit/0ce0580ba913882a65b119bbf8391706fc0e61a0)