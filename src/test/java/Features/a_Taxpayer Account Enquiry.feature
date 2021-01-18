
Feature:[SUC:03-09]-Taxpayer Account Enquiries

  @SUC:03-09 @UAT_M3_09-01 @UAT_M3_09-02
  Scenario Outline:UAT_M3_09-01-UAT_M3_09-02-Verify all the fields in Taxpayer Account Enquiry Main screen
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Click on Taxpayer Accounting-->Taxpayer Account Enquiry
    And Select Entity Type "<entity_type>" and enter "<tin>"
    Then Select Tax Type Account "<tax_type_account>" and click select button
    And Click on cancel button
    When Select taxpayer
    Then Click on view button
    When Select Payments1 under "<tax_type_account_1>"
    Then Click on thee select button
    When Select Suspense account  under "<tax_type_account_2>"
    Then Click on select button
    Examples:
      | username  | password | entity_type | tin      | tax_type_account | tax_type_account_1 | tax_type_account_2 |
      | tripsuser | Passw0rd | INDIVIDUAL  | P0021518 | Domestic VAT     | Payments           | Suspense           |




