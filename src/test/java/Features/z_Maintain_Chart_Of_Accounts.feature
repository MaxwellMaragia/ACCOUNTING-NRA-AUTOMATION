Feature: [SUC:04-01]-Maintain Chart of Accounts

# @SUC:04-01 @UAT_M4_01-01
# Scenario: UAT_M4_01-01-To verify the fields displayed in the Maintain Chart Of Accounts screen
#  Given User is in browser and launches the app URL
#  Then Login as Revenue Officer
#   | tripsuser | Passw0rd |
#
#  And Click on Revenue accounting > Maintain chart of accounts
#  Then Verify Mantain Chart of Accounts table exists with the columns
#   | Category    |
#   | Description |
#  Then Verify existence of data buttons in chart of accounts table
#  Then Verify Sub Category table exists with the columns
#   | Account Code   |
#   | Description    |
#   | Effective Date |
#   | Expiry Date    |
#   | Status         |
#  Then Verify existence of data buttons in Sub Category table
#  Then Verify Ledger account table exists with the columns
#   | Account Code   |
#   | Description    |
#   | Effective Date |
#   | Expiry Date    |
#   | Status         |
#  Then Verify existence of data buttons in Ledger account table

# @SUC:04-01 @UAT_M4_01-02 
# Scenario Outline: UAT_M4_01-02-To verify the fields displayed in the Add Category - Code & Description screen
#  Given User is in browser and launches the app URL
#  Then Login as Revenue Officer
#   | tripsuser | Passw0rd |
#  And Click on Revenue accounting > Maintain chart of accounts
#  Then Click on Add Category button under Maintain chart of accounts table
#  Then Then shift focus to add category modal
#  Then Verify fields "<Code>" and "<Description>" and "<EffectiveDate>" and "<ExpiryDate>" and "<Status>" in add category modal screen
#  Then Verify Add "<Add>" and Cancel "<Cancel>" buttons in add category modal screen
#  And Close modal
#  Examples:
#   | Code                        | Description                 | EffectiveDate                       | ExpiryDate                       | Status                 | Add                | Cancel                 |
#   | categoryDetails:AccountCode | categoryDetails:Description | categoryDetails:EffectiveDate_input | categoryDetails:ExpiryDate_input | categoryDetails:Status | categoryDetails:Ok | categoryDetails:Cancel |

  @SUC:04-01 @UAT_M4_01-03 @UAT_M4_01-01  @UAT_M4_01-02
  Scenario Outline: UAT_M3_01-03-To verify the "Add Category" functionality in Chart of Accounts structure
    Given User is in browser and launches the app URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on Revenue accounting > Maintain chart of accounts
    Then Click on Add Category button under Maintain chart of accounts table
    Then Then shift focus to add category modal
    Then Fill in fields "<Code>" and "<Description>" and "<EffectiveDate>" and "<ExpiryDate>" and "<Status>"
    Then Confirm saved success message "<SuccessMessage>"
    Examples:
      | Code | Description    | EffectiveDate | ExpiryDate | Status | SuccessMessage            |
      | A26  | Account code15 | 25/07/2020    | 03/07/2021 | Active | Record Successfully Saved |

#  @SUC:04-01 @UAT_M4_01-04
#  Scenario Outline: UAT_M4_01-04-To verify the fields displayed in the Account Sub Category - Code & Description screen
#   Given User is in browser and launches the app URL
#   Then Login as Revenue Officer
#    | tripsuser | Passw0rd |
#   And Click on Revenue accounting > Maintain chart of accounts
#   Then Click table row in Chart of accounts table
#   Then Click add Sub category button in sub category table
#   Then Then shift focus to add sub category modal
#   Then Verify fields "<ParentCategory>" and "<Code>" and "<Description>" and "<EffectiveDate>" and "<ExpiryDate>" and "<Status>" and "<BusinessSectorDivision>" in add sub category modal screen
#   Then Verify Add "<Add>" and Cancel "<Cancel>" buttons in add sub category modal screen
#   And Close modal
#   Examples:
#    | ParentCategory         | Code                        | Description                 | EffectiveDate                       | ExpiryDate                       | Status                 | BusinessSectorDivision        |Add                | Cancel                 |
#    | categoryDetails:parent | categoryDetails:AccountCode | categoryDetails:Description | categoryDetails:EffectiveDate_input | categoryDetails:ExpiryDate_input | categoryDetails:Status | categoryDetails:BusineeSector |categoryDetails:Ok | categoryDetails:Cancel |

  @SUC:04-01 @UAT_M4_01-05 @SUC:04-01 @UAT_M4_01-04
  Scenario Outline: UAT_M4_01-05-To verify the "Add Sub Category" functionality in Chart of Accounts structure
    Given User is in browser and launches the app URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on Revenue accounting > Maintain chart of accounts
    Then Click table row in Chart of accounts table
    Then Click add Sub category button in sub category table
    Then Then shift focus to add sub category modal
    Then Fill in fields "<Code>" and "<Description>" and "<EffectiveDate>" and "<ExpiryDate>" and "<Status>" to save sub category
    Then Confirm saved success message "<SuccessMessage>"
    Examples:
      | Code | Description     | EffectiveDate | ExpiryDate | Status | SuccessMessage            |
      | AD55 | Sub Category 54 | 25/07/2020    | 03/10/2020 | Active | Record Successfully Saved |

  @SUC:04-01 @UAT_M4_01-06
  Scenario Outline: UAT_M4_01-06-To verify the "Edit" Category details  in Chart of Accounts structure
    Given User is in browser and launches the app URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on Revenue accounting > Maintain chart of accounts
    Then Click table row in Chart of accounts table
    Then Click edit category button
     #Edit in this case but same logic :)
    Then Then shift focus to add category modal
    Then Edit fields "<Description>" and "<EffectiveDate>" and "<ExpiryDate>" and "<Status>" then click save
    Then Confirm saved success message "<SuccessMessage>"
    Examples:
      | Description | EffectiveDate | ExpiryDate | Status | SuccessMessage            |
      | Run test 24 | 20/07/2020    | 01/10/2020 | Active | Record Successfully Saved |

  @SUC:04-01 @UAT_M4_01-07
  Scenario Outline: UAT_M4_01-07-To verify the "Edit" Sub Category details  in Chart of Accounts structure
    Given User is in browser and launches the app URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on Revenue accounting > Maintain chart of accounts
    Then Click second table row in Chart of accounts table
    Then Click table row in Sub Category table
    Then Click edit button in sub category table
    Then Then shift focus to add sub category modal
    Then Edit fields "<Code>" and "<Description>" and "<EffectiveDate>" then click save to edit subcategory
    Then Confirm saved success message "<SuccessMessage>"
    Examples:
      | Code   | Description | EffectiveDate | SuccessMessage            |
      | ADS986 | Edit 114    | 25/07/2021    | Record Successfully Saved |

#     @SUC:04-01 @UAT_M4_01-08
#     Scenario Outline: UAT_M4_01-08-To verify the fields displayed in the Ledger Account screen
#      Given User is in browser and launches the app URL
#      Then Login as Revenue Officer
#       | tripsuser | Passw0rd |
#      And Click on Revenue accounting > Maintain chart of accounts
#      Then Click on tree view in revenue category upto sixth level
#      Then Click on sixth table row in treeview
#      Then Click add button in ledger account table
#      Then Then shift focus to add ledger account modal
#      Then  Then Verify fields "<ParentCategory>" and "<Code>" and "<Description>" and "<EffectiveDate>" and "<ExpiryDate>" and "<Status>" and "<Save>" and "<Cancel>"
#      Then Verify tax types
#       | Personal Income Tax   |
#       | Company Income Tax    |
#       | Withholding Tax(WHT)  |
#       | PAYE                  |
#       | Fringe Benefit Tax    |
#       | Turnover Tax(TOT)     |
#       | Domestic Excise       |
#       | Domestic VAT          |
#       | Dividend Tax          |
#       | Non Resident Tax(NRT) |
#       | Capital Gain Tax(CGT) |
#       Then Verify Document types
#        | Revenue Collection Receipt      |
#        | Estimated Assessment            |
#        | Personal Income Tax(PIT) Return |
#        | Provisional Tax(PIT) Return     |
#        | Provisional Tax(CIT) Return     |
#        | Company Income Tax(CIT) Return  |
#        | Instalment Agreement Details    |
#        | Manual Penalty                  |
#        | Refund Claim                    |
#        Then Verify Taxpayer types list
#        | Individual   |
#        | Organization |
#
#        Then Verify charge types list
#         | Liability  |
#         | Interest   |
#         | Penalty    |
#         | Adjustment |
#         | Fine       |
#      Then Close modal
#
#      Examples:
#       | ParentCategory        | Code                        | Description                 | EffectiveDate                       | ExpiryDate                       | Status                 | Save                | Cancel                 |
#       | categoryDetails:parent| categoryDetails:AccountCode | categoryDetails:Description | categoryDetails:EffectiveDate_input | categoryDetails:ExpiryDate_input | categoryDetails:Status | categoryDetails:Ok | categoryDetails:Cancel |

  @SUC:04-01 @UAT_M4_01-09  @UAT_M4_01-08
  Scenario Outline: UAT_M4_01-09-To add the Ledger Account to the Maintain chart of Accounts structure
    Given User is in browser and launches the app URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on Revenue accounting > Maintain chart of accounts
    Then Click on tree view in revenue category upto sixth level
    Then Click on sixth table row in treeview
    Then Click add button in ledger account table
    Then Then shift focus to add ledger account modal
    Then Fill in fields "<Code>" and "<Description>" and "<EffectiveDate>" and "<ExpiryDate>" and "<Status>" and "<TaxType>" and "<DocumentType>" and "<TaxPayerType>" and "<ChargeType>" and "<BusinessSectorDivision>" to save ledger account
    Then Confirm saved success message "<SuccessMessage>"
    Examples:
      | Code    | Description | EffectiveDate | ExpiryDate | Status | TaxType | DocumentType         | TaxPayerType | ChargeType | BusinessSectorDivision                                 | SuccessMessage            |
      | 1234256 | Ledger 24   | 25/07/2020    | 03/10/2020 | Active | PAYE    | Estimated Assessment | Individual   | Liability  | Retail trade, except of motor vehicles and motorcycles | Record Successfully Saved |

  @SUC:04-01 @UAT_M4_01-10
  Scenario Outline: UAT_M4_01-10-To verify abandon process
    Given User is in browser and launches the app URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on Revenue accounting > Maintain chart of accounts
    Then Click on tree view in revenue category upto sixth level
    Then Click on sixth table row in treeview
    Then Click add button in ledger account table
    Then Then shift focus to add ledger account modal
    Then Fill in fields "<Code>" and "<Description>" and "<EffectiveDate>" and "<ExpiryDate>" and "<Status>" in ledger account account details
    Then Close modal
    Examples:
      | Code    | Description      | EffectiveDate | ExpiryDate | Status |
      | 1234256 | Ledger sample 14 | 25/07/2020    | 03/10/2020 | Active |



