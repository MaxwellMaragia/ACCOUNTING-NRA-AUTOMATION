Feature: [SUC:03-10] Taxpayer Accounting Reports

  @SUC:03-10 @UAT_M3_10-01
  Scenario Outline: UAT_M3_10-01-Verify the adjustment report with Start date and End date specified and Report Format as PDF or Excel
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then click Reporting-->Reports
    And click Account Adjustments Reports link
    Then select the report format as "<PDF>"
    And enter start date and  end date
    Then select taxpayer category "<taxpayer_category>"
    Then select tax office "<tax_office>"
    And run report
    Examples:
      | PDF | taxpayer_category | tax_office | username  | password |
      | PDF | All               | All        | tripsuser | Passw0rd |

  @SUC:03-10 @UAT_M3_10-02
  Scenario Outline: UAT_M3_10-02-Verify the adjustment report with Start date and End date specified and Report Format as PDF or Excel
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Click_on Reporting-->Reports
    And Click the Account Adjustments Reports link
    Then Select thee report format as "<format>"
    And Skip the start date and  end date
    Then Select a taxpayer category "<taxpayer_category>"
    Then Select a tax office "<tax_office>"
    And run the__report

    Examples:
      | format | taxpayer_category | tax_office | username  | password |
      | PDF    | Individual        | Balaka     | tripsuser | Passw0rd |

  @SUC:03-10 @UAT_M3_10-03
  Scenario Outline: UAT_M3_10-03-Verify the credit allocations report with Start date and End date specified and Report Format as PDF or Excel
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then click   Reporting-->Reports
    And Click Credit Allocation to launch
    Then Select the report format "<format>"
    Then select date
    Then select a Tax Office "<taxoffice>"
    Then run the report
    Examples:
      | format | taxoffice | username  | password |
      | PDF    | Balaka    | tripsuser | Passw0rd |

  @SUC:03-10 @UAT_M3_10-04
  Scenario Outline: UAT_M3_10-04-Verify the credit allocations report without Start date and End date specified and Report Format as PDF or Excel
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then click Reporting--->Reports
    And Click Credit Allocation to Launch
    Then Select the Report Format "<format>"
    Then select a Tax office "<taxoffice>"
    Then run the Report
    Examples:
      | format | taxoffice | username  | password |
      | EXCEL  | Balaka    | tripsuser | Passw0rd |

  @SUC:03-10 @UAT_M3_10-05
  Scenario Outline: UAT_M3_10-05-Verify  the taxpayer account statement report with specifying the Start Date and End Date specified and report format as PDF or Excel
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then click_on  Reporting--->Reports
    And click_Credit Allocation to Launch
    Then select on a Report Format "<format>"
    And select Dates
    Then Enter TIN "<tin>"
    And select tax type : nickson "<taxtype>"
    Then Run the report
    Examples:
      | format | taxtype | username  | password | tin      |
      | EXCEL  | All     | tripsuser | Passw0rd | P0022044 |

  @SUC:03-10 @UAT_M3_10-06
  Scenario Outline: UAT_M3_10-06-Verify the taxpayer account statement report without specifying the Start Date and End Date and specify the Tax Type
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then click_on_Reporting--->Reports
    And click_taxpayer account statement to Launch
    Then select on a report Format "<format>"
    Then Enter Tin "<tin>"
    And select tax type : nickson "<taxtype>"
    Then run the_report
    Examples:
      | format | taxtype | username  | password | tin      |
      | EXCEL  | All     | tripsuser | Passw0rd | P0022044 |

  @SUC:03-10 @UAT_M3_10-07
  Scenario Outline: UAT_M3_10-07-Verify the taxpayer account statement report with specifying the tax type as 'All'
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then click_on__Reporting--->Report
    And click_taxpayer account Statement to Launch
    Then select on a report format "<format>"
    Then Enter tin "<tin>"
    And select tax_Type "<taxtype>"
    Then Select dates
    Then run the_Report
    Examples:
      | format | taxtype | username  | password | tin      |
      | EXCEL  | All     | tripsuser | Passw0rd | P0022044 |

  @SUC:03-10 @UAT_M3_10-08
  Scenario Outline: UAT_M3_10-08-Verify  the Ageing Debts Report specifying the Tax Type and report format as PDF or Excel
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then click_on___Reporting--->Reports
    And click ageing debts report  to Launch
    Then select on a report_format "<format>"
    And select tax_type "<taxtype>"
    Then select a taxoffice "<taxoffice>"
    Then run the__Report
    Examples:
      | format | taxtype | username  | password | tin      | taxoffice |
      | EXCEL  | All     | tripsuser | Passw0rd | P0022044 | Balaka    |

  @SUC:03-10 @UAT_M3_10-09
  Scenario Outline: UAT_M3_10-09-Verify  the Ageing Debts Report specifying All in the Tax Type and Tax Office
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then click__on___Reporting--->Reports
    And click Ageing debts report  to Launch
    Then select on a Report_format "<format>"
    And select Tax_type "<taxtype>"
    Then select a Taxoffice "<taxoffice>"
    Then Run the__Report
    Examples:
      | format | taxtype | username  | password | tin      | taxoffice |
      | EXCEL  | All     | tripsuser | Passw0rd | P0022044 | All       |