Feature: Test project login functionality

  @TestProjectLoginPOM
  Scenario Outline: Validate Test project login functionality with valid credentials

    Given User open google browser
    And User is on Test project login page
    When User enter valid <UN> and <PWD>
    And click on login
    Then User navigated to test project home page

    Examples:
    | UN | PWD |
    | Raghav | 12345 |