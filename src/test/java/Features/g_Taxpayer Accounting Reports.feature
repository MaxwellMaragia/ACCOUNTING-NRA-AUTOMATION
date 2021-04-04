Feature: [SUC:03-10] Taxpayer Accounting Reports

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then Navigate to Reporting-->Reports

  @SUC:03-10 @UAT_M3_10-01 @UAT_M3_10-02
  Scenario: UAT_M3_10-01-Verify the adjustment report with Start date and End date specified and Report Format as PDF or Excel
    Then Select report to print "Account Adjustments Report"
    Then Select report file type "PDF"
    Then Select taxpayer category "Individual"
    Then Select tax office "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Account Adjustments Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Account Adjustments Report.xls" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"


  @SUC:03-10 @UAT_M3_10-03 @UAT_M3_10-04
  Scenario: UAT_M3_10-03-Verify the credit allocations report with Start date and End date specified and Report Format as PDF or Excel
    Then Select report to print "Credit Allocations Report"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Credit Allocations Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"
    Then Enter start Date and  end Date provided
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Credit Allocations Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"


  @SUC:03-10 @UAT_M3_10-05 @UAT_M3_10-06 @UAT_M3_10-07
  Scenario: UAT_M3_10-05-Verify  the taxpayer account statement report with specifying the Start Date and End Date specified and report format as PDF or Excel
    Then Select report to print "Taxpayer Account Statement"
    Then Select report file type "PDF"
    Then Enter TIN "%"
    And Select "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Account Statemen.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"
    Then Enter start Date and  end Date provided
    And Select "Company Income Tax"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Account Statemen.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:03-10 @UAT_M3_10-08 @UAT_M3_10-09
  Scenario: UAT_M3_10-08-Verify  the Ageing Debts Report specifying the Tax Type and report format as PDF or Excel
    Then Select report to print "Ageing Debts Report"
    Then Select report file type "PDF"
    Then Select tax office "All"
    And Select "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Ageing Debts Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"


