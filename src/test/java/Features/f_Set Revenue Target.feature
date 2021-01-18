Feature:[SUC:03-16]-Set Revenue Target

  @SUC:03-16 @UAT_M4_03-01
  Scenario Outline:UAT_M4_03-01-To Verify fields in Treasury Target Allotment screen
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Treasury Target
    Then Click  on Add button
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

  @SUC:03-16 @UAT_M4_03-02
  Scenario Outline:UAT_M4_03-02-To Verify the process of Set Treasury Target
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate To Revenue Accounting System->Maintain Revenue Targets->Maintain Treasury Target
    Then Click  on Add Button
    And Enter Required Data and click ok button
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

  @SUC:03-16 @UAT_M4_03-03
  Scenario Outline:UAT_M4_03-03-To Verify fields in set Management target screen
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Management Target
    Then Click  on add button
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

  @SUC:03-16 @UAT_M4_03-04
  Scenario Outline:UAT_M4_03-04-To Verify the process of set Management target
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate To revenue Accounting System->Maintain Revenue Targets-->Maintain Management  Target
    Then Click add button : set management target
    Then enter data
    And Click the ok button
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

  @SUC:03-16 @UAT_M4_03-05
  Scenario Outline:UAT_M4_03-05-To Verify the process of Update Management target
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate To Revenue Accounting System->Maintain Revenue Targets-->Maintain Management  Target
    Then Enter the data required and click search
    Then Select on data and update
    Then Update Tax Office Target details
    And Click ok
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

  @SUC:03-16 @UAT_M4_03-06
  Scenario Outline:UAT_M4_03-06-To Verify the process of View Management Target
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Revenue Accounting system->Maintain Revenue Targets-->Maintain Management  Target
    Then enter the test data required and click view button
    Then click cancel
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

  @SUC:03-16 @UAT_M4_03-07
  Scenario Outline:UAT_M4_03-07-To Verify the process of Validation fails
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Management  Target
    Then Click  on the Add button
    Then Enter the data required
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

  @SUC:03-16 @UAT_M4_03-08
  Scenario Outline:UAT_M4_03-08-To Verify the process of No Records found
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Revenue Accounting System---->Maintain Revenue Targets->Maintain Management  Target
    Then click view button
    Then enter all data  and click ok
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

  @SUC:03-16 @UAT_M4_03-09
  Scenario Outline:UAT_M4_03-09-To Verify the process of Update Treasury Allotment
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to Revenue Accounting System-->Maintain Revenue Targets->Maintain Treasury Target
    Then select one record and click update button
    Then Edit year and treasury target
    Then click ok
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |

  @SUC:03-16 @UAT_M4_03-10
  Scenario Outline:UAT_M4_03-10-To Verify the process of View Treasury Alloment
    Given User is in browser to launch application url
    Then Enter  username "<username>" and  password "<password>" to login
    Then Navigate to revenue accounting system->Maintain Revenue Targets->Maintain Treasury Target
    Then click on view  button
    Then Select year to be viewed and click on view button
    Examples:
      | username  | password |
      | tripsuser | Passw0rd |