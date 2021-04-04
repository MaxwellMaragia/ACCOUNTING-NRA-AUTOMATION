Feature: [SUC:03-06] Perform Taxpayer Account Adjustments

  @SUC:03-06 @UAT_M3_06-01
  Scenario: UAT_M3_06-01-verify the fields in the Find Transaction screen to Manage Taxpayer Account Adjustments
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    Then Manage Taxpayer Account Adjustment, screen should be displayed

  @SUC:03-06 @UAT_M3_06-02
  Scenario: UAT_M3_06-02-verify the fields in the Find Transaction screen to Manage Taxpayer Account Adjustments
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And user Clicks on Add button
    Then Taxpayer Account Adjustment screen should be displayed
    When User Clicks on Find Button
    Then Taxpayer Account Adjustment Details Search Screen should be displayed

  @SUC:03-06 @UAT_M3_06-03 @UAT_TCS-02.19.1 @UAT_TCS-02.19.4
  Scenario Outline: UAT_M3_06-03-verify the process of Creating Adjustments by Revenue Officer and  Approving
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And user Clicks on Add button
    When User Clicks on Find Button
    Then Taxpayer Account Adjustment Details Search Screen should be displayed
    And enter Tin number <TIN> and <AccountType> then click search
#    Then Click table column ""
    And select charge type <chargetype>
    And select adjustment type <adjtype>
    Then give reason value <reason>
    Then enter revenue ledger code <code> and amount <amount>
    And click on submit button
    Then Credit reference number will generate <RefNo>
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    And Click on Case management dropdown
    And click on Accounting application
    Then switch to frame
    When enters reference number in search results
    Then switch to frame
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on accounting
    Then Application Account Adjustment status should be "Approved"
    Examples:
      | TIN         | AccountType         | chargetype | adjtype | reason                            | code    | amount   | RefNo                                   |
      | N0000036323 | Suspense Account    | Liability  | Credit  | MISCELLANEOUS ADJUSTMENT - CREDIT | 1111201 | 67587678 | Processing Completed - Reference Number |
#      | N0000036323 | Personal Income Tax | Liability  | Debit   | MISCELLANEOUS ADJUSTMENT - DEBIT  | 1111201 | 67587678 | Processing Completed - Reference Number |

  @SUC:03-06 @UAT_TCS-02.19.3 @UAT_TCS-02.19.6
  Scenario Outline: UAT_M3_06-04-verify the process of Creating Adjustments by Revenue Officer and  rejecting
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And user Clicks on Add button
    When User Clicks on Find Button
    Then Taxpayer Account Adjustment Details Search Screen should be displayed
    And enter Tin number <TIN> and <AccountType> then click search
    And select charge type <chargetype>
    And select adjustment type <adjtype>
    Then give reason value <reason>
    Then enter revenue ledger code <code> and amount <amount>
    And click on submit button
    Then Credit reference number will generate <RefNo>
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    And Click on Case management dropdown
    And click on Accounting application
    Then switch to frame
    When enters reference number in search results
    Then switch to frame
    When Click selected Reference Number
    And clicks Decline from the dropdown
    Then Enter Outcome Notes "Invalid Documentation"
    And Enter Outcome Reason for Taxpayer accounting
    And click save on accounting
    Then Application Account Adjustment status should be "Rejected"
    Examples:
      | TIN         | AccountType      | chargetype | adjtype | reason                            | code    | amount   | RefNo |
      | N0000036323 | Suspense Account | Liability  | Credit  | MISCELLANEOUS ADJUSTMENT - CREDIT | 1111201 | 67587678 | ACAD/ |


  @SUC:03-06 @UAT_M3_06-05
  Scenario Outline: UAT_M3_06-05-To verify the process of Viewing Accounting Adjustments
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And enter Tin number <TIN> and click search on Taxpayer Account Adjustment
    Then Manage Taxpayer Account Adjustment screen displayed
    When User clicks on an adjustment
    Then User adjustment details displayed

    Examples:
      | TIN         |
      | N0000036323 |

  @SUC:03-06 @UAT_M3_06-06
  Scenario Outline: UAT_M3_06-06-To verify the process of Checking Validation for incorrect data
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And user Clicks on Add button
    When User Clicks on Find Button
    Then Taxpayer Account Adjustment Details Search Screen should be displayed
    And enter Tin number <TIN> and <AccountType> then click search
    Given user enters no data and Clicks on Submit button
    Then Error Message should be displayed
    When User inputs invalid data
      | chargeType     | Adjustment   |
      | transactionRef | 000000       |
      | adjType        | credit       |
      | Description    | Invalid data |
    Then Error Message should be displayed
    Examples:
      | TIN         | AccountType      |
      | N0000036323 | Suspense Account |

  @SUC:03-06 @UAT_M3_06-07
  Scenario Outline: UAT_M3_06-07-To Verify the Process of Write Off
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And user Clicks on Add button
    When User Clicks on Find Button
    Then Taxpayer Account Adjustment Details Search Screen should be displayed
    And enter Tin number <TIN> and <AccountType> then click search
    And select charge type <chargetype>
    And select adjustment type <adjtype>
    Then give reason value <reason>
    Then enter revenue ledger code <code> and amount <amount>
    And click on submit button
    Then Credit reference number will generate <RefNo>
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    And Click on Case management dropdown
    And click on Accounting application
    Then switch to frame
    When enters reference number in search results
    Then switch to frame
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on accounting
    Then Application Account Adjustment status should be "Approved"
    Examples:
      | TIN         | AccountType         | chargetype | adjtype | reason                             | code    | amount   | RefNo |
      | N0000036323 | Personal Income Tax | Adjustment | Credit  | CREDIT BAD DEBT WRITE-OFF - CREDIT | 1111201 | 67587678 | ACAD/ |


  @SUC:03-06 @UAT_M3_06-08
  Scenario Outline: UAT_M3_06-08-To Verify the Process of Write On
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And user Clicks on Add button
    When User Clicks on Find Button
    Then Taxpayer Account Adjustment Details Search Screen should be displayed
    And enter Tin number <TIN> and <AccountType> then click search
    And select charge type <chargetype>
    And select adjustment type <adjtype>
    Then give reason value <reason>
    Then enter revenue ledger code <code> and amount <amount>
    And click on submit button
    Then Credit reference number will generate <RefNo>
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    And Click on Case management dropdown
    And click on Accounting application
    Then switch to frame
    When enters reference number in search results
    Then switch to frame
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on accounting
    Then Application Account Adjustment status should be "Approved"
    Examples:
      | TIN         | AccountType      | chargetype | adjtype | reason                         | code    | amount   | RefNo |
      | N0000036250 | Suspense Account | Adjustment | Debit   | INCREASE VAT PENALTIES - DEBIT | 1111201 | 67587678 | ACAD/ |


  @SUC:03-06 @UAT_M3_06-09
  Scenario Outline: UAT_M3_06-09-To Verify the Process of Penalty/Interest Waiver
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And user Clicks on Add button
    When User Clicks on Find Button
    Then Taxpayer Account Adjustment Details Search Screen should be displayed
    And enter Tin number <TIN> and <AccountType> then click search
    And select charge type <chargetype>
    And select adjustment type <adjtype>
    Then give reason value <reason>
    Then enter revenue ledger code <code> and amount <amount>
    And click on submit button
    Then Credit reference number will generate <RefNo>
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    And Click on Case management dropdown
    And click on Accounting application
    Then switch to frame
    When enters reference number in search results
    Then switch to frame
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on accounting
    Then Application Account Adjustment status should be "Approved"
    Examples:
      | TIN         | AccountType      | chargetype | adjtype | reason                          | code    | amount   | RefNo |
      | N0000036250 | Suspense Account | Adjustment | Credit  | DECREASE VAT PENALTIES - CREDIT | 1111201 | 67587678 | ACAD/ |


  @SUC:03-06 @UAT_M3_06-09
  Scenario Outline: UAT_M3_06-09-To verify the Process of debiting or Crediting Revenue Accounts
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And user Clicks on Add button
    When User Clicks on Find Button
    Then Taxpayer Account Adjustment Details Search Screen should be displayed
    And enter Tin number <TIN> and <AccountType> then click search
    And select charge type <chargetype>
    And select adjustment type <adjtype>
    Then give reason value <reason>
    Then enter revenue ledger code <code> and amount <amount>
    And click on submit button
    Then Credit reference number will generate <RefNo>
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    And Click on Case management dropdown
    And click on Accounting application
    Then switch to frame
    When enters reference number in search results
    Then switch to frame
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on accounting
    Then Application Account Adjustment status should be "Approved"
    Examples:
      | TIN         | AccountType      | chargetype | adjtype | reason                             | code    | amount   | RefNo |
      | N0000036250 | Suspense Account | Adjustment | Credit  | CREDIT BAD DEBT WRITE-OFF - CREDIT | 1111201 | 67587678 | ACAD/ |



    
