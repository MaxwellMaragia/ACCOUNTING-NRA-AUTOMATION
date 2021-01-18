Feature: [SUC:03-17]-Portal Statement Request


  @SUC:03-17 @UAT_M3_17-01
  Scenario: UAT_M3_17-01-To verify the fields in Taxpayer Account's Statement Request-Individual Portal
    Given Launch portal in browser
    Then Login to portal
      | maxipain | Codei@maseno2020 |
    Then Verify Home Screen Buttons
      | HOME       |
      | MY ACCOUNT |
      | MY TAX     |
      | SUPPORT    |
    Then Verify Tax Accounts Table
      | Account Type |
      | Balance      |
    Then Click MY TAX button
    Then Click statement requests under tasks
    Then Click tax type dropdown and select tax type that has transactions
#    Then Verify statement request input fields
#      | MonthXPATH     |
#      | YearXPATH      |
#      | Submit_XPATH   |
#      | Cancel_XPATH   |
#      | Download_XPATH |
#    Then Click cancel button

  @SUC:03-17 @UAT_M3_17-02
  Scenario Outline: UAT_M3_17-02-To verify the fields displayed in Taxpayer's suspense account-Individual Portal
    Given Launch portal in browser
    Then Login to portal
      | maxipain | Codei@maseno2020 |
    Then Click MY TAX button
    Then Click statement requests under tasks
    Then Click tax type dropdown and select tax type that has transactions
    Then Select month "<Month>" and "<Year>"
    Then Click submit : portal
    Then Click download and verify download
    Examples:
      | Month  | Year |
      | August | 2020 |

  @SUC:03-17 @UAT_M3_17-03
  Scenario: UAT_M3_17-03-To verify the fields in Taxpayer Account's Statement Request-Individual Portal
    Given Launch portal in browser
    Then Login to portal
      | maxipain | Codei@maseno2020 |
    Then Verify Home Screen Buttons
      | HOME       |
      | MY ACCOUNT |
      | MY TAX     |
      | SUPPORT    |
    Then Verify Tax Accounts Table
      | Account Type |
      | Balance      |
    Then Click MY TAX button
    Then Click statement requests under tasks
    Then Click tax type dropdown and select tax type that has transactions
    Then Verify statement request input fields
      | //*[@id='btnSubmit']   |
      | //*[@id='btnDownload'] |
      | //*[@id='btnCancel']   |
    Then Click cancel button

  @SUC:03-17 @UAT_M3_17-04
  Scenario Outline: UAT_M3_17-04-To verify the fields displayed in Taxpayer's suspense account-Individual Portal
    Given Launch portal in browser
    Then Login to portal
      | maxipain | Codei@maseno2020 |
    Then Click MY TAX button
    Then Click statement requests under tasks
    Then Click tax type dropdown and select tax type that has transactions
    Then Select month "<Month>" and "<Year>"
    Then Click submit : portal
    Then Click download and verify download
    Examples:
      | Month  | Year |
      | August | 2020 |



