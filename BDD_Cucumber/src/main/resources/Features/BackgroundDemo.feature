@Background
Feature:  Login functionality

  Background: User is logged in
    Given User is on login
    When User enter UN and PWD
    And Click on Login
    Then User is on home page

  Scenario: Check logout link
    When User click on welcome link
    Then Logout link is displayed

#  Scenario: Check Patent search page displayed
#    When User click on Patent Search tile
#    Then Patent search page displayed