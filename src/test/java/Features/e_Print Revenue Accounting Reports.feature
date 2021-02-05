Feature:[SUC:04-02] Print Revenue Accounting Reports

  @SUC:04-02 @UAT_M4_02-01
  Scenario Outline:UAT_M4_02-01-To verify the Revenue Ledger Details Reports in PDF format
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Reporting-->Reports
    Then Select report to print "Revenue Ledger Details"
    Then Select report file type "<report_format>"
    Then Enter transaction date
    Then click run report
    Then Verify file "Revenue Ledger Details.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"
    Examples:
      | username  | password | report_format |
      | tripsuser | Passw0rd | PDF           |

  @SUC:04-02 @UAT_M4_02-02
  Scenario Outline:UAT_M4_02-02-To verify the Revenue Ledger Details Reports in EXCEL format
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Reporting-->Reports
    Then Select report to print "Revenue Ledger Details"
    Then Select report file type "<report_format>"
    Then Enter transaction date
    Then click run report
    Then Verify file "Revenue Ledger Details.xls" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"
    Examples:
      | username  | password | report_format |
      | tripsuser | Passw0rd | EXCEL         |

  @SUC:04-02 @UAT_M4_02-07
  Scenario Outline:UAT_M4_02-07-To verify the process of printing Chart of Accounts Report
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Reporting-->Reports
    Then Select report to print "Chart Of Accounts Report"
    Then Select report file type "<report_format>"
    And Select "<tax_type>"
    Then Click run report
    Then Verify file "Chart Of Accounts Report.xls" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"
    Examples:
      | username  | password | report_format | tax_type     |
      | tripsuser | Passw0rd | EXCEL         | Domestic VAT |

  @SUC:04-02 @UAT_M4_02-08
  Scenario Outline:UAT_M4_02-08-To verify the process of Abandon Report
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Reporting-->Reports
    Then Select report to print "Chart Of Accounts Report"
    Then click on Cancel
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

