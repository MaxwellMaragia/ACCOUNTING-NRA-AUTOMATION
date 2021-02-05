Feature:[SUC:03-16]-Set Revenue Target

  Background:
    Given User is in browser to launch application url
    Then Enter  username "tripsuser" and  password "Passw0rd" to login


  @SUC:03-16 @UAT_M4_03-01
  Scenario: UAT_M4_03-01-To Verify fields in Treasury Target Allotment screen
    Then Navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Treasury Target
    Then Click  on Add button
    Then Verify fields in Treasury target allotment screen


  @SUC:03-16 @UAT_M4_03-02
  #Change year before running
  Scenario: UAT_M4_03-02-To Verify the process of Set Treasury Target
    Then Navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Treasury Target
    Then Click  on Add Button
    And Select "1983" and percentage then click ok
    Then Confirm saved success message "Record Added"


  @SUC:03-16 @UAT_M4_03-03 @UAT_M4_03-04
  Scenario: UAT_M4_03-04-To Verify the process of set Management target
    Then navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Management Target
    Then Click add button : set management target
    Then Fill in year as "1982" and other necessary details
    And Click the ok button
    Then Confirm saved success message "Record Added"


  @SUC:03-16 @UAT_M4_03-05
  Scenario: UAT_M4_03-05-To Verify the process of Update Management target
    Then navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Management Target
    Then Enter the data required and click search
    Then Select on data and update
    Then Change the month to "February"
    And Click ok


  @SUC:03-16 @UAT_M4_03-06
  Scenario Outline:UAT_M4_03-06-To Verify the process of View Management Target
    Then navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Management Target
    Then Search for data with year "<Year>", Month "<Month>", and Tax type "<taxType>"
    Then Confirm data appears in table "<Year>","<Month>","<taxType>"
    Examples:
      | Year | Month   | taxType            |
      | 1980 | January | Company Income Tax |

  @SUC:03-16 @UAT_M4_03-07
  Scenario: UAT_M4_03-07-To Verify the process of Validation fails
    Then navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Management Target
    Then Click add button : set management target
    Then Switch to frame : backoffice
    And Click the ok without filling data
    Then Verify error message "Year: Validation Error: Value is required."

  @SUC:03-16 @UAT_M4_03-08
  Scenario Outline: UAT_M4_03-08-To Verify the process of No Records found
    Then navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Management Target
    Then Search for data with year "<Year>", Month "<Month>", and Tax type "<taxType>"
    Then Verify no data is found in table
    Examples:
      | Year | Month   | taxType            |
      | 2100 | January | Company Income Tax |
  

  @SUC:03-16 @UAT_M4_03-09
  Scenario: UAT_M4_03-09-To Verify the process of Update Treasury Allotment
    Then Navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Treasury Target
    Then Click table column "//*[@id='MaintainTreasuryTarget:MaintainTreasuryTargetTableHandler_data']/tr[1]/td[2]"
    Then Click update in Mantain treasury target screen
    Then Switch to frame : backoffice
    Then Modify percentage of Management Target to "75"
    Then Confirm saved success message "Record Updated"


  @SUC:03-16 @UAT_M4_03-10
  Scenario: UAT_M4_03-10-To Verify the process of View Treasury Alloment
    Then Navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Treasury Target
    Then click on view to check match "//*[@id='MaintainTreasuryTarget:MaintainTreasuryTargetTableHandler_data']/tr[1]/td[2]"

