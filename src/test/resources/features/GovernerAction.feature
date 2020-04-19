Feature: Verify Governor actions on UI

  Scenario: Verify Governor actions on UI
    Given I navigate to baseurl in chrome browser
    And Verify UI button with its color and text
    When I click on the Dispense button
    Then I Shall navigate to a page which says "Cash dispensed"


