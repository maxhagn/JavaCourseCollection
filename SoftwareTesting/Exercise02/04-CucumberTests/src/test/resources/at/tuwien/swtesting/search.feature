# Hagn Maximilian
# 11808237
# Exercise 02

Feature: Search closed bugs with search words on Bugzilla
  As a user I want to search for bugs with status closed and search words.

  @SearchBugs
  Scenario: Search for bugs
    Given I go to the searchpage
    When I set the search words to "This is one of three closed tests"
    And I set the product to TEST_PRODUCT
    And I set the status to CLOSED
    And I start the search
    Then I should see the message "3 bugs found."