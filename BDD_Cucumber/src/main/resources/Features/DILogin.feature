Feature: DI login functionality

  @Smoke
  Scenario Outline: Validate DI login functionality with valid credentials

    Given Google browser is open
    And User is on DI login page
    When User enter DI valid "<UN>" and "<PWD>"
    And click on login button
    Then User navigated to DI home page

    Examples:
      | UN | PWD |
      | pavandilippatil@clarivate.com | Pavanpatil@03 |