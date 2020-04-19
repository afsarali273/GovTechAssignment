Feature: Addition of working class hero by Clerk

  As a Clerk ,I shall be able to add working hero's to the Database by using API and UI

  Scenario: TC-001 Addition of Single Working class hero

    Given I set Headers for request

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with below details
    | natid       | name      | gender |birthday |salary | tax |
    | 78096663444567 | Afsar Ali | M      | 12031992| 18479578 | 12968 |

    When I send POST request to "calculator/insert"
    Then The status code is 202
    And Response body is Alright

  Scenario: TC-002 Addition of Multiple Working class hero
    Given I set Headers for request

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Multiple working class hero with below details
      | natid          | name      | gender |birthday |salary    | tax   |
      | 78096663444567 | Afsar Ali | M      | 12031992| 18479578 | 12968 |
      | 675439776467   | User 2    | M      | 12031992| 19864568 | 1230  |
      | 458777333356   | User 3    | M      | 12031992| 9858349  | 9642  |

    When I send POST request to "calculator/insertMultiple"
    Then The status code is 202
    And Response body is Alright

  Scenario: TC-003 Addition of Multiple Working class hero using csv file
    Given I set Headers for request

      |KEY         |VALUE|
      |Content-Type| multipart/form-data |

    And  Request body for Multiple working class hero with csv file "working-hero.csv"
    When I send POST request to "calculator/uploadLargeFileForInsertionToDatabase"
    Then The status code is 202
    And  Response body is Alright

  @UI
  Scenario: TC-004 Addition of working class hero using csv file from UI
    Given I have a valid csv file named "working-hero.csv"
    When  I navigate to baseurl in chrome browser
    Then  I Verify UI for Oppenheimer project
    And   I shall be able to upload csv file successfully using UI
