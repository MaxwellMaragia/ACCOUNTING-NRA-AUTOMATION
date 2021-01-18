Feature:[SUC:04-02] Print Revenue Accounting Reports

  @SUC:04-02 @UAT_M4_02-01
  Scenario Outline:UAT_M4_02-01-To verify the Revenue Ledger Details Reports in PDF format
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Reporting-->Reports
    Then Select report to print "Revenue Ledger Details"
    And Select Report_Format "<report_format>"
    And Enter  Start Date and click run report
    Examples:
      | username  | password | report_format |
      | tripsuser | Passw0rd | PDF           |

  @SUC:04-02 @UAT_M4_02-02
  Scenario Outline:UAT_M4_02-02-To verify the Revenue Ledger Details Reports in EXCEL format
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then navigate to Reporting-->Reports
    Then Select report to print "Revenue Ledger Details"
    And select Report_Format "<report_format>"
    Then click run report
    Examples:
      | username  | password | report_format |
      | tripsuser | Passw0rd | EXCEL         |

  @SUC:04-02 @UAT_M4_02-07
  Scenario Outline:UAT_M4_02-07-To verify the process of printing Chart of Accounts Report
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then navigate to reporting-->Reports
    Then Select report to print "Chart Of Accounts Report"
    And select Report_format "<report_format>"
    And Select "<tax_type>"
    Then Click run report
    Examples:
      | username  | password | report_format | tax_type     |
      | tripsuser | Passw0rd | EXCEL         | Domestic VAT |

  @SUC:04-02 @UAT_M4_02-08
  Scenario Outline:UAT_M4_02-08-To verify the process of Abandon Report
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then navigate To Reporting--->Reports
    Then Select report to print "Chart Of Accounts Report"
    Then click on Cancel
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

