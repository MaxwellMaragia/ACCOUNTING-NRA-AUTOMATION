Feature: [SUC:03-04] Maintain Allocation Rules

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in

  @SUC:03-04 @UAT_M3_04-01
  Scenario: UAT_M3_04-01-Verify fields displayed in configurations
    Given User navigates to Taxpayer Accounting dropdown
    When the User clicks Find Allocation Rules Configuration
    Then  User is navigated to Allocation Rules Configuration page
    And configuration Field details are displayed
    When User then clicks Add button
    Then Credit Allocation tab displayed


  @SUC:03-04 @UAT_M3_04-02 @UAT_M3_04-03
  Scenario: UAT_M3_04-02-UAT_M3_04-03-To verify the process of Adding allocation Rules
    Given User navigates to Taxpayer Accounting dropdown
    When the User clicks Find Allocation Rules Configuration
    Then  User is navigated to Allocation Rules Configuration page
    And configuration Field details are displayed
    When user Clicks the Add button on allocation configuration
    Then Credit Allocation tab displayed
    When user clicks on add under credit allocation
    Then NEW Allocation Method screen should be displayed
    When User selects "Interest" priority "1" and check oldest first
    Then Record is added successfully
    And Allocation Rule has been added
    And Allocation rule saved

  @SUC:03-04 @UAT_M3_04-04
  Scenario: UAT_M3_04-04-To verify the process of Updating an Allocation Rule
    Given User navigates to Taxpayer Accounting dropdown
    When the User clicks Find Allocation Rules Configuration
    Then  User is navigated to Allocation Rules Configuration page
    And configuration Field details are displayed
    When User inputs % in configuration refrence and clicks search
    Then Grid Table populated with search result of allocation rules
    When user selects configuration reference and clicks update
    Then Allocation configuration screen displayed
    And User selects an allocation rule and click update
    When User selects "Penalty" priority "2" and check oldest first
    And Allocation Rule successfully modified
    And effective date changed

  @SUC:03-04 @UAT_M3_04-05
  Scenario: UAT_M3_04-05 To verify the process of Viewing an Allocation Rule
    Given User navigates to Taxpayer Accounting dropdown
    When the User clicks Find Allocation Rules Configuration
    Then  User is navigated to Allocation Rules Configuration page
    And configuration Field details are displayed
    When User inputs % in configuration refrence and clicks search
    Then Grid Table populated with search result of allocation rules
    When user selects configuration reference and clicks view
    Then Allocation configuration screen displayed
    And User selects an allocation rule and click view
    Then VIEW Allocation Method screen should be displayed
    When User clicks on cancel button
    Then System should display Allocation Configuration screen


  @SUC:03-04 @UAT_M3_04-06
  Scenario: UAT_M3_04-06 To verify the process of Removing an Allocation Rule
    Given User navigates to Taxpayer Accounting dropdown
    When the User clicks Find Allocation Rules Configuration
    Then  User is navigated to Allocation Rules Configuration page
    And configuration Field details are displayed
    When User inputs % in configuration refrence and clicks search
    Then Grid Table populated with search result of allocation rules
    When user selects configuration reference and clicks update
    Then Allocation configuration screen displayed
    When User selects an allocation rule and click remove
    Then User clicks yes
    Then Record Deleted successfully


  @SUC:03-04 @UAT_M3_04-07
  Scenario: UAT_M3_04-07-To verify the process of checking an Invalid Allocation Rule
    Given User navigates to Taxpayer Accounting dropdown
    When the User clicks Find Allocation Rules Configuration
    Then  User is navigated to Allocation Rules Configuration page
    And configuration Field details are displayed
    When user Clicks the Add button on allocation configuration
    Then Credit Allocation tab displayed
    When user clicks on add under credit allocation
    Then NEW Allocation Method screen should be displayed
    When User selects "Interest" priority "1" and check oldest first
    Then Record is added successfully
    When user clicks on add under allocation allocation
    Then NEW Allocation Method screen should be displayed
    When User selects "Interest" priority "1" and check oldest first
    Then A valid error message should be displayed
    When User clicks on cancel button
    Then Allocation configuration screen displayed


  @SUC:03-04 @UAT_M3_04-08
  Scenario: UAT_M3_04-08-To verify the process of checking Invalid Modified Allocation Rule
    Given User navigates to Taxpayer Accounting dropdown
    When the User clicks Find Allocation Rules Configuration
    Then  User is navigated to Allocation Rules Configuration page
    And configuration Field details are displayed
    When User inputs % in configuration refrence and clicks search
    Then Grid Table populated with search result of allocation rules
    When user selects configuration reference and clicks update
    Then Allocation configuration screen displayed
    And User selects an allocation rule and click update
    When User selects "Interest" priority "1" and check oldest first
    Then A valid error message should be displayed
    When User clicks on cancel button
    Then Allocation configuration screen displayed


