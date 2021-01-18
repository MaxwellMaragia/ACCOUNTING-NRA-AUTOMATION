Feature: [SUC:03-07]-Perform Credit Allocation

 @SUC:03-07 @UAT_M3_07-01
 Scenario Outline: UAT_M3_07-01-To verify all the fields are displayed in the Manage Credit Allocation Screen & Credit Allocation Screen
  Given User is in browser and launches the app URL
  Then Login as Revenue Officer
   | tripsuser | Passw0rd |
  And Click on Taxpayer accounting > Manage Credit Allocation
  Then Verify fields "<TIN>" and "<TaxType>" and "<Add>" and "<View>" and "<Search>" and "<Cancel>" in Credit Allocation
  Then Verify table columns "<TaxTypeColumn>" and "<UnallocatedCreditPeriod>" and "<DocumentTypeUnallocatedCredit>" and "<UnallocatedCreditAmount>" and "<OutstandingLiabilityPeriod>" and "<DocumentTypeOutstandingLiability>" and "<OutstandingLiabilityAmount>"
  Then Click add button
  Then Verify input field "<TIN_input>" and "<Name_input>" and "<TaxTypeInput>"  in credit allocation fields
  Examples:
   | TIN            | TaxType                | Add               | View              | Search             | Cancel            | TaxTypeColumn | UnallocatedCreditPeriod   | DocumentTypeUnallocatedCredit        | UnallocatedCreditAmount   | OutstandingLiabilityPeriod   | DocumentTypeOutstandingLiability        | OutstandingLiabilityAmount   | TIN_input            | Name_input                  | TaxTypeInput             |
   | SearchForm:TIN | SearchForm:RevenueType | SearchForm:j_id10 | SearchForm:j_id11 | SearchForm:j_idt42 | SearchForm:Cancel | Tax Type      | Unallocated Credit Period | Document Type for Unallocated Credit | Unallocated Credit Amount | Outstanding Liability Period | Document Type for Outstanding Liability | Outstanding Liability Amount | CreditAllocation:TIN | CreditAllocation:EntityName | CreditAllocation:TaxType |

 @SUC:03-07 @UAT_M3_07-02
 Scenario Outline: UAT_M3_07-02-To verify the Credit Allocation Process (Suspense Account to Tax Type Account-Fully Cleared)
  Given User is in browser and launches the app URL
  Then Login as Revenue Officer
   | tripsuser | Passw0rd |
  And Click on Taxpayer accounting > Manage Credit Allocation
  Then Click add button
  Then Click find button
  Then Shift focus to modal
  Then enter tin "<Tin>" and click search
  Then select tax type "<TaxType>"
  Then Click suspense radio button under unallocated credit balance
  Then Select transaction with document "<DocumentType>" under unallocated credit
  Then Click suspense radio button under outstanding liability
  Then Select transaction with document "<DocumentType>" under outstanding liability
  Then populate allocated amount field
  Then Click submit
  Then Confirm saved success message "<SuccessMessage>"
  Then Obtain reference number "<SuccessMessage>"
  Then Open CRM and close modal
  Then Click on accounting application link
  Then switch to frame
  Then search for reference number
  Then Click on reference number
  Then approve transaction
  Then Click save CRM
  Then Application Account Adjustment status should be "<Status>"
  Examples:
   | Tin      | TaxType            | DocumentType       | SuccessMessage       | Status   |
   | P0020797 | Fringe Benefit Tax | Account Adjustment | Processing Completed | Approved |

 @SUC:03-07 @UAT_M3_07-03
 Scenario Outline: UAT_M3_07-03-To verify the Credit Allocation Process (Suspense Account to Tax Type Account-Partially Cleared)
  Given User is in browser and launches the app URL
  Then Login as Revenue Officer
   | tripsuser | Passw0rd |
  And Click on Taxpayer accounting > Manage Credit Allocation
  Then Click add button
  Then Click find button
  Then Shift focus to modal
  Then enter tin "<Tin>" and click search
  Then select tax type "<TaxType>"
  Then Click suspense radio button under unallocated credit balance
  Then Select transaction with document "<DocumentType>" under unallocated credit
  Then Click suspense radio button under outstanding liability
  Then Select transaction with document "<DocumentType>" under outstanding liability
  Then populate allocated amount field
  Then Click submit
  Then Confirm saved success message "<SuccessMessage>"
  Then Obtain reference number "<SuccessMessage>"
  Then Open CRM and close modal
  Then Click on accounting application link
  Then switch to frame
  Then search for reference number
  Then Click on reference number
  Then approve transaction
  Then Click save CRM
  Then Application Account Adjustment status should be "<Status>"
  Examples:
   | Tin      | TaxType            | DocumentType       | SuccessMessage       | Status   |
   | P0020797 | Fringe Benefit Tax | Account Adjustment | Processing Completed | Approved |

 @SUC:03-07 @UAT_M3_07-04
 Scenario Outline: UAT_M3_07-04-To verify the process of clearing Selection in Unallocated Credit Section
  Given User is in browser and launches the app URL
  Then Login as Revenue Officer
   | tripsuser | Passw0rd |
  And Click on Taxpayer accounting > Manage Credit Allocation
  Then Click add button
  Then Click find button
  Then Shift focus to modal
  Then enter tin "<Tin>" and click search
  Then select tax type "<TaxType>"
  Then Click suspense radio button under unallocated credit balance
  Then Click clear button "<Button>" and verify field "<Field>" has been cleared
  Examples:
   | Tin      | TaxType            | Button                   | Field                            |
   | P0020797 | Fringe Benefit Tax | CreditAllocation:crClear | CreditAllocation:crBalance_input |

 @SUC:03-07 @UAT_M3_07-05
 Scenario Outline: UAT_M3_07-05-To verify the process of clearing Selection  in Outstanding Liability Section
  Given User is in browser and launches the app URL
  Then Login as Revenue Officer
   | tripsuser | Passw0rd |
  And Click on Taxpayer accounting > Manage Credit Allocation
  Then Click add button
  Then Click find button
  Then Shift focus to modal
  Then enter tin "<Tin>" and click search
  Then select tax type "<TaxType>"
  Then Click suspense radio button under unallocated credit balance
  Then Click clear button "<Button>" and verify field "<Field>" has been cleared
  Examples:
   | Tin      | TaxType            | Button                   | Field                            |
   | P0020797 | Fringe Benefit Tax | CreditAllocation:crClear | CreditAllocation:crBalance_input |

 @SUC:03-07 @UAT_M3_07-06
 Scenario Outline: UAT_M3_07-06-To verify the process of rejecting a Credit allocation Transaction
  Given User is in browser and launches the app URL
  Then Login as Revenue Officer
   | tripsuser | Passw0rd |
  And Click on Taxpayer accounting > Manage Credit Allocation
  Then Click add button
  Then Click find button
  Then Shift focus to modal
  Then enter tin "<Tin>" and click search
  Then select tax type "<TaxType>"
  Then Click suspense radio button under unallocated credit balance
  Then Select transaction with document "<DocumentType>" under unallocated credit
  Then Click suspense radio button under outstanding liability
  Then Select transaction with document "<DocumentType>" under outstanding liability
  Then populate allocated amount field
  Then Click submit
  Then Confirm saved success message "<SuccessMessage>"
  Then Obtain reference number "<SuccessMessage>"
  Then Open CRM and close modal
  Then Click on accounting application link
  Then switch to frame
  Then search for reference number
  Then Click on reference number
  And clicks Decline from the dropdown
  Then Enter Outcome Notes "<Notes>"
  And Enter Outcome Reason for Taxpayer accounting
  Then Click save CRM
  Then Application Account Adjustment status should be "Rejected"
  Examples:
   | Tin      | TaxType            | DocumentType       | SuccessMessage       | Notes   |
   | P0020797 | Fringe Benefit Tax | Account Adjustment | Processing Completed | Invalid data |

 @SUC:03-07 @UAT_M3_07-07
 Scenario Outline: UAT_M3_07-07-To verify the process of View Credit Allocation
  Given User is in browser and launches the app URL
  Then Login as Revenue Officer
   | tripsuser | Passw0rd |
  And Click on Taxpayer accounting > Manage Credit Allocation
  Then input tin "<Tin>"
  Then Click search
  Then Select first table row and click view
  Then Verify fields are readonly
  Examples:
   | ErrorMessage        | Tin      |
   | Please select a tin | P0020180 |

 @SUC:03-07 @UAT_M3_07-08
 Scenario Outline: UAT_M3_07-08-To verify the process of Validation Errors
  Given User is in browser and launches the app URL
  Then Login as Revenue Officer
   | tripsuser | Passw0rd |
  And Click on Taxpayer accounting > Manage Credit Allocation
  Then Click add button
  Then Click save
  Then verify error message "<ErrorMessage>"
  Examples:
   | ErrorMessage        |
   | Please select a tin |


