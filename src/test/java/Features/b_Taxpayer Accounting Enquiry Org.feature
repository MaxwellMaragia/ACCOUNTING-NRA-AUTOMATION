Feature:[SUC:03-09]-Taxpayer Account Enquiries

  @SUC:03-09 @UAT_M3_09-01 @UAT_M3_09-12
  Scenario Outline:UAT_M3_09-01-UAT_M3_09-12-Verify all the fields in Taxpayer Account Enquiry Main screen
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Click taxpayer Accounting-->Taxpayer Account Enquiry
    And Select thee entity_type "<entity_type>" and enter the "<tin>"
    Then Select Tax_type Account "<tax_type_account>" and click select button
    And Click on the  cancel button
    When Select a taxpayer
    Then Click on the view button
    When Select Payments under "<tax_type_account_1>"
    Then Click on the select button
    When Select Suspense account  under "<tax_type_account_2>"
    Then Click on the select button
    Examples:
      | username  | password | entity_type  | tin      | tax_type_account      | tax_type_account_1 | tax_type_account_2 |
      | tripsuser | Passw0rd | ORGANIZATION | V0024037 | Non Resident Tax(NRT) | Payments           | Suspense           |
