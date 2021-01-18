Feature: [SUC:03-15]-Maintain Period Formula Configuration

  @UAT_M3_15-01
  Scenario: UAT_M3_15-01-To verify the fields displayed in the Maintain Period Generation Configuration screen	
    Given User is in browser and launches the app URL
     Then Login as Revenue Officer
      | tripsuser | Passw0rd |

     And Click on Taxpayer accounting > Maintain period generation configuration
     Then Verify dropdown list values
      | Personal Income Tax | 
      | Company Income Tax  | 
      | Withholding Tax     | 
      | Pay As You Earn     | 
      | Fringe Benefits Tax | 
      | Turnover Tax        | 
      | Domestic Excise     | 
      | Domestic VAT        | 
      | Dividend Tax        | 
      | Non-Resident Tax    | 
      | Capital Gains Tax   | 
  
     Then Verify existence of buttons
      | Create New | 
      | Cancel     | 
      | Search     | 
      | Edit       | 
      | View       | 
  
     Then Verify existence of table columns  
      | Tax Type          | 
      | Return Type       | 
      | Formula           | 
      | Filing Frequency  | 
      | Number of Periods | 
      | Effective Date    | 
      | Expiry Date       | 
      
      @UAT_M3_15-02
      Scenario: UAT_M3_15-02-To verify the fields displayed in the Period Formula Configuration Detail screen
      And Click on Taxpayer accounting > Maintain period generation configuration
      Then Click on Create New button
      Then Verify Create New screen
      Then Select tax type to search then select table column and click edit button
      Then Verify correct data is loaded in edit fields
      Then Select tax type to search then select table column and click view button
      Then Verify screen is readonly
      
      @UAT_M3_15-03
      Scenario Outline: UAT_M3_15-03-To verify the Process of Creating a Period Formula Configuration-Monthly, Quarterly, Annually
      And Click on Taxpayer accounting > Maintain period generation configuration
      Then Click on Create New button
      Then Fill in fields "<TaxType>" and "<ReturnType>" and "<TaxCycleStartDay>" and "<TaxCycleStartMonth>" and "<TaxCycleEndDay>" and "<TaxCycleEndMonth>" and "<FillingFrequency>" and "<Formula>" and "<Number of Periods>" and "<Effective Date>" and "<Expiry Date>" and "<Status>"
      Then Click save
      Then Confirm saved success message
      Examples: 
      | TaxType       | ReturnType          | TaxCycleStartDay | TaxCycleStartMonth | TaxCycleEndDay | TaxCycleEndMonth | FillingFrequency | Formula       | Number of Periods | Effective Date | Expiry Date | Status | 
      | Domestic VAT  | Domestic VAT Return | 12               | May                | 31             | July             | Adhoc            | AdHoc 14 Days | 9                 | 24/07/2020     | 30/04/2021  | Active |
      
      
      
      @UAT_M3_15-04
      Scenario Outline: UAT_M3_15-04-To verify the Process of Modifying Period Formula Configuration
      And Click on Taxpayer accounting > Maintain period generation configuration
      Then Select tax type to search then select table column and click edit button
      Then Change Number of periods to "<NumberOfPeriods>"
      Then Click save
      Then Confirm saved success message	
      Examples:
      | NumberOfPeriods |
      | 7               |
      
      @UAT_M3_15-05
      Scenario: UAT_M3_15-05-To verify the Process of Viewing Period Formula Configuration
      And Click on Taxpayer accounting > Maintain period generation configuration
      Then Select tax type to search then select table column and click view button
      
      @UAT_M3_15-06
      Scenario Outline: UAT_M3_15-06-To verify the Process of Validating Invalid Data
      And Click on Taxpayer accounting > Maintain period generation configuration
      Then Select tax type to search then select table column and click edit button
      Then Enter invalid date as "<date>" in Effective Date field
      Then Click save
      #We will use part of the error message string
      And Confirm that error message contains the string "<error>"
      Examples:
      | date | error                              |
      | 0    | could not be understood as a date. |
      
      @UAT_M3_15-07
      Scenario Outline: UAT_M3_15-07-To verify the Process of Validating Formula-Duplicate Check while creating new formula
       And Click on Taxpayer accounting > Maintain period generation configuration
       Then Click on Create New button
       Then Fill in fields "<TaxType>" and "<ReturnType>" and "<TaxCycleStartDay>" and "<TaxCycleStartMonth>" and "<TaxCycleEndDay>" and "<TaxCycleEndMonth>" and "<FillingFrequency>" and "<Formula>" and "<Number of Periods>" and "<Effective Date>" and "<Expiry Date>" and "<Status>"
       Then Click save
       #We will use part of the error message string
       And Confirm duplicate input error message has string "<error>"
       Examples:
        | TaxType       | ReturnType          | TaxCycleStartDay | TaxCycleStartMonth | TaxCycleEndDay | TaxCycleEndMonth | FillingFrequency | Formula       | Number of Periods | Effective Date | Expiry Date | Status | error          |
        | Dividend Tax  | Dividend Tax Return | 12               | May                | 31             | July             | Adhoc            | AdHoc 14 Days | 9                 | 24/07/2020     | 30/04/2021  | Active | already exists |

       @UAT_M3_15-08
       Scenario Outline: UAT_M3_15-07-To verify the Process of Validating Formula-Duplicate Check while Editing formula
        And Click on Taxpayer accounting > Maintain period generation configuration
        Then Select tax type to search then select table column and click edit button
        Then Provide duplicate values in return tax type "<TaxType>" and "<ReturnType>" and filling frequency "<FillingFrequency>"
        Then Click save
        And Confirm duplicate input error message has string "<error>"

        Examples:
         | TaxType       | ReturnType    | FillingFrequency | error          |
         | PAYE          | PAYE Return   | Monthly          | already exists |

        @UAT_M3_15-09
        Scenario Outline: UAT_M3_15-09-To verify the functionality of Search button
         And Click on Taxpayer accounting > Maintain period generation configuration
        Then Search every Tax type "<TaxType>"
         Examples:
         | TaxType                 |
         | Personal Income Tax     |
         | Company Income Tax      |
         | Withholding Tax(WHT)    |
         | PAYE         |
         | Fringe Benefit Tax     |
         | Turnover Tax(TOT)       |
         | Domestic Excise         |
         | Domestic VAT            |
         | Dividend Tax            |
         | Non Resident Tax(NRT)   |
         | Capital Gain Tax(CGT)  |



  
  
  
