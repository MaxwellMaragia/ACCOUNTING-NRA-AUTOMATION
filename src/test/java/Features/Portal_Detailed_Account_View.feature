Feature: [SUC:03-16]-Portal-Detailed Account View

  @SUC:03-16 @UAT_M3_16-01
  Scenario: UAT_M3_16-01-To verify all fields on Detailed Account View screen-Individual Portal
    Given Launch portal in browser
    Then Login to portal
      | portalsanity | Codei@maseno2020 |
    Then Verify Home Screen Buttons
      | HOME       |
      | MY ACCOUNT |
      | MY TAX     |
      | SUPPORT    |
    Then Verify Tax Accounts Table
      | Account Type |
      | Balance      |

    Then Click MY TAX button
    Then Verify my tax account table columns
      | Tax Account    |
      | Balance        |
      | Account Status |

    Then Verify manage my account dropdowns
      | Objections and Appeals    |
      | Tasks                     |
      | Requests and Applications |
      | Refunds                   |

  @SUC:03-16 @UAT_M3_16-02
  Scenario Outline: UAT_M3_16-02-To verify the fields displayed in Taxpayer's suspense account-Individual Portal
    Given Launch portal in browser
    Then Login to portal
      | portalsanity | Codei@maseno2020 |
    Then Click MY TAX button
    Then Select Suspense account
    Then Verify the input fields
      |//*[@id="id_startDate"]|
      |//*[@id="id_endDate"]|
      |//*[@id="btnSearch"]|
    Then Verify table columns "<TaxAccountType>" and "<AccountNumber>" and "<CurrentBalance>" and "<CurrentStatus>" and "<Date>" and "<DocumentType>" and "<DocumentReference>" and "<Status>" and "<Amount>" in my tax
    Examples:
      | TaxAccountType   | AccountNumber  | CurrentBalance  | CurrentStatus  | Date | DocumentType  | DocumentReference  | Status | Amount |
      | Tax Account Type | Account Number | Current Balance | Current Status | Date | Document Type | Document Reference | Status | Amount |


  @SUC:03-16 @UAT_M3_16-3
  Scenario: UAT_M3_16-3-Taxpayer Account Enquiry of a Taxpayer with suspended Tax types-Individual Portal
    Given Launch portal in browser
    Then Login to portal
      | portalsanity | Codei@maseno2020 |
    Then Click MY TAX button
    Then Click on a suspended tax type
    Then Verify Status "Suspended" in field "//span[contains(text(),'Suspended')]"


  @SUC:03-16 @UAT_M3_16-4
  Scenario: UAT_M3_16-4-Taxpayer Account Enquiry of a Taxpayer with De-Registered Tax types-Individual Portal
    Given Launch portal in browser
    Then Login to portal
      | portalsanity | Codei@maseno2020 |
    Then Click MY TAX button
    Then Click on a de registered tax type
    Then Verify Status "De-registered" in field "//span[contains(text(),'De-registered')]"


  @SUC:03-16 @UAT_M3_16-5
  Scenario Outline: UAT_M3_16-5-To view payment details Individual portal
    Given Launch portal in browser
    Then Login to portal
      | portalsanity | Codei@maseno2020 |
    Then Click MY TAX button
    Then Select Suspense account with trans
    Then Verify transaction table columns "<TaxAccountType>" and "<AccountNumber>" and "<CurrentBalance>" and "<CurrentStatus>"
    Then Verify current balance is not zero
    Examples:
      | TaxAccountType   | AccountNumber  | CurrentBalance  | CurrentStatus  |
      | Tax Account Type | Account Number | Current Balance | Current Status |

  @SUC:03-16 @UAT_M3_16-6
  Scenario Outline: UAT_M3_16-6-To view payment details Individual portal
    Given Launch portal in browser
    Then Login to portal
      | portalsanity | Codei@maseno2020 |
    Then Click MY TAX button
    Then Select Suspense account with trans
    Then Enter end date value that is more than 365 days from current start date "<Date>"
    Then Search for payment details
    Then Verify error message "<ErrorMessage>"
    Then Logout
    Examples:
      | Date       | ErrorMessage                              |
      | 30/07/2026 | Maximum period you can search is 365 days |


  @SUC:03-16 @UAT_M3_16-07
  Scenario: UAT_M3_16-07-To verify all the fields on Detailed Account View screen-Organisation Portal
    Given Launch portal in browser
    Then Login to portal
      | portalsanity | Codei@maseno2020 |
    Then Verify Home Screen Buttons
      | HOME       |
      | MY ACCOUNT |
      | MY TAX     |
      | SUPPORT    |
    Then Verify Tax Accounts Table
      | Account Type |
      | Balance      |

    Then Click MY TAX button
    Then Verify my tax account table columns
      | Tax Account    |
      | Balance        |
      | Account Status |

    Then Verify manage my account dropdowns
      | Objections and Appeals    |
      | Tasks                     |
      | Requests and Applications |
      | Refunds                   |

  @SUC:03-16 @UAT_M3_16-08
  Scenario Outline: UAT_M3_16-08-To verify the fields displayed in Taxpayer's suspense account-Organisation Portal
    Given Launch portal in browser
    Then Login to portal
      | codeitechnologies | Codei@maseno2020 |
    Then Click MY TAX button
    Then Select Suspense account
    Then Verify the input fields
      |//*[@id="id_startDate"]|
      |//*[@id="id_endDate"]|
      |//*[@id="btnSearch"]|
    Then Verify table columns "<TaxAccountType>" and "<AccountNumber>" and "<CurrentBalance>" and "<CurrentStatus>" and "<Date>" and "<DocumentType>" and "<DocumentReference>" and "<Status>" and "<Amount>" in my tax
    Examples:
      | TaxAccountType   | AccountNumber  | CurrentBalance  | CurrentStatus  | Date | DocumentType  | DocumentReference  | Status | Amount |
      | Tax Account Type | Account Number | Current Balance | Current Status | Date | Document Type | Document Reference | Status | Amount |


  @SUC:03-16 @UAT_M3_16-09
  Scenario: UAT_M3_16-9-Taxpayer Account Enquiry of a Taxpayer with suspended Tax types-Organisation Portal
    Given Launch portal in browser
    Then Login to portal
      | codeiaccounting | Codei@maseno2020 |
    Then Click MY TAX button
    Then Click on a suspended tax type
    Then Verify Status "Suspended" in field "//span[contains(text(),'Suspended')]"

  @SUC:03-16 @UAT_M3_16-10
  Scenario: UAT_M3_16-10-Taxpayer Account Enquiry of a Taxpayer with De-Registered Tax types-Organisation Portal
    Given Launch portal in browser
    Then Login to portal
      | codeiaccounting | Codei@maseno2020 |
    Then Click MY TAX button
    Then Click on a de registered tax type
    Then Verify Status "De-registered" in field "//span[contains(text(),'De-registered')]"

  @SUC:03-16 @UAT_M3_16-11
  Scenario Outline: UAT_M3_16-11-To View Payment Details-Organisation Portal
    Given Launch portal in browser
    Then Login to portal
      | codeiaccounting | Codei@maseno2020 |
    Then Click MY TAX button
    Then Select Suspense account
    Then Verify transaction table columns "<TaxAccountType>" and "<AccountNumber>" and "<CurrentBalance>" and "<CurrentStatus>"
    Then Verify current balance is not zero
    Examples:
      | TaxAccountType   | AccountNumber  | CurrentBalance  | CurrentStatus  |
      | Tax Account Type | Account Number | Current Balance | Current Status |

  @SUC:03-16 @UAT_M3_16-12
  Scenario Outline: UAT_M3_16-12-To verify the validation error during Tax Type Account Equiry-Organisation Portal
    Given Launch portal in browser
    Then Login to portal
      | codeiaccounting | Codei@maseno2020 |
    Then Click MY TAX button
    Then Select Suspense account
    Then Enter end date value that is more than 365 days from current start date "<Date>"
    Then Search for payment details
    Then Verify error message "<ErrorMessage>"
    Then Logout
    Examples:
      | Date       | ErrorMessage                              |
      | 30/07/2026 | Maximum period you can search is 365 days |



