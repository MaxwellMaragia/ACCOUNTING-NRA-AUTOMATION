Feature: Revenue Control Accounting

  @UAT_M3_04-03 @RevenueControlAccounting
  Scenario Outline: UAT_M3_04-03-To verify all the fields displayed in the Tax Receivable Control Account
    Given User is in browser and launches the app URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on Revenue Accounting System > Revenue Control Accounts > Tax Receivable Control Account
    Then Verify The input fields "<TaxType>" and "<TaxOffice>" and "<TransactionType>" and "<DateFrom>" and "<DateTo>" and "<OpeningBalance>" and "<ClosingBalance>" and "<SearchButton>"
    Then Verify existence of table columns "<Date>" and "<TaxOfficeColumn>" and "<TaxPayerName>" and "<Tin>" and "<TransactionDescription>" and "<TaxTypeColumn>" and "<DocumentReference>" and "<Debit>" and "<Credit>" and "<Balance>" in table
    Examples:
      | TaxType                                   | TaxOffice                                   | TransactionType                                   | DateFrom                                    | DateTo                                   | OpeningBalance                             | ClosingBalance                             | SearchButton                        | Date | TaxOfficeColumn | TaxPayerName  | Tin | TransactionDescription  | TaxTypeColumn | DocumentReference  | Debit | Credit | Balance |
      | TaxReceivableControlAccount:TaxType_label | TaxReceivableControlAccount:TaxOffice_label | TaxReceivableControlAccount:TransactionType_label | TaxReceivableControlAccount:FromDate_input | TaxReceivableControlAccount:ToDate_input | TaxReceivableControlAccount:openingBalance | TaxReceivableControlAccount:closingBalance | TaxReceivableControlAccount:j_idt63 | DATE | TAX OFFICE      | TAXPAYER NAME | TIN | TRANSACTION DESCRIPTION | TAX TYPE      | DOCUMENT REFERENCE | DEBIT | CREDIT | BALANCE |

  @UAT_M3_04-03 @RevenueControlAccounting
  Scenario Outline: UAT_M3_04-03-To verify all the fields displayed in the Total Revenue Account
    Given User is in browser and launches the app URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on Revenue Accounting System > Revenue Control Accounts > Total Revenue Account
    Then Verify The input fields "<TaxOffice>" and "<DateFrom>" and "<DateTo>" and "<SearchButton>" in revenue account
    Then Verify existence of table columns "<TaxOfficeColumn>" and "<TransactionDescription>" and "<Debit>" and "<Credit>" and "<Balance>" in revenue account table
    Examples:
      | TaxOffice                  | DateFrom                            | DateTo                            | SearchButton                | TaxOfficeColumn | TransactionDescription  | Debit | Credit | Balance |
      | TotalRevenueAccount:office | TotalRevenueAccount:StartDate_input | TotalRevenueAccount:EndDate_input | TotalRevenueAccount:j_idt48 | TAX OFFICE      | TRANSACTION DESCRIPTION | DEBIT | CREDIT | BALANCE |

