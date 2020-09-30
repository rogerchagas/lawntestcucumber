
Feature: Sign up flow for Lawn Starter Users
    As a customer from Lawn Starter
    I should be able to Sign up
    Than I can order and use the services
    And get a quote for lawn service
    
    Scenario: Sign up new user
        Given I have accessed the home Page
        And I have provide my basic info to get a quote for lawn service:
        | Address    | 1234 South Lamar Blvd|
        | First Name | Test                 |
        | Phone      | 5125555555           |
        When I fill the form from single sign up with the follow information:
        | First Name    | Test         |
        | Last Name    | User          |
        | Phone        | 5125555555    |
        | Address      | 1234 South Lamar Blvd  |
        | Card Number   | 4111111111111111      |
        | Exp Date      | 3                     |
        | Exp Year      | 24                    |
        | CVV           | 987                   |

      Then I can see my profile on the Lawn Starter Page Profile




