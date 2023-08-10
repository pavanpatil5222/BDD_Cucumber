Feature: To test google search functionality

  @google
  Scenario: Validate google search

    Given browser is open
    And User is on google search page
    When User enter text in search box
    And clicks on search button
    Then User can see search results