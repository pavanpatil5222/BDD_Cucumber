Feature: To test login functionality

 @LoginTest
 Scenario: Check login functionality with valid credentials

Given User is on login page
When User enter username and password
And clicks on login button
Then User can see home page
