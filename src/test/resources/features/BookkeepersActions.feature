Feature: Access Data from Database by Bookkeeper

  As a Bookkeeper,I want to retrieve data from Database and validate to report it to my Bookkeeping Manager

  Scenario: TC-001 Get list of working hero's from Database
    Given I set Headers for request

      |KEY         |VALUE|
      |Content-Type| application/json |

    When I send GET request to "calculator/taxRelief"
    Then The status code is 200
    And I shall get a list consist of "natid","name" and "relief"

  Scenario: TC-002 validate natid masked with $ from 5th character
    Given I set Headers for request

      |KEY         |VALUE|
      |Content-Type| application/json |

    When I send GET request to "calculator/taxRelief"
    Then The status code is 200
    And I shall see natid field must be masked from the 5th character onwards with dollar sign ‘$’


  Scenario: TC-003 calculate tax relief using formula
    Given I added working hero to the DB
    When I retrieve data from Database using GET api
    Then I shall see tax relief computation obeys formula '((Salary-tax paid) * variable)+gender bonus'

  Scenario: TC-004 validate when tax relief amount is less than 50.00
    Given I added working hero to the DB with less tax relief
    When I retrieve data from Database using GET api
    Then I shall see tax relief computation obeys formula '((Salary-tax paid) * variable)+gender bonus' and validate it to be 50.0
