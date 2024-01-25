Feature: Shop user account edit

  Scenario Outline: User adds address to the account
    Given I'm on the main shop page
    When I go to sign in
    And I log in using email "adres.mailowy4@gmail.com" and password "heloł"
    And I go to addresses page
    And I go to create new address
    And I enter new: alias <alias>, address <address>, city <city>, postalCode <postalCode>, country <country> and phone <phone>
    And I submit the form
    Then I can see success alert message: "Address successfully added!"
    And I verify entered address: alias <alias>, address <address>, city <city>, postalCode <postalCode>, country <country> and phone <phone>
    When I remove address
    Then I can see success remove alert message: "Address successfully deleted!"
    And I close browser

    Examples:
    |         alias          |      address         |       city        | postalCode |      country     |   phone    |
    |"h.nowicka99@gmail.com" | "Indian Summer 12/3" | "Southend-on-Sea" |   "SS1"    | "United Kingdom" | "23758867" |