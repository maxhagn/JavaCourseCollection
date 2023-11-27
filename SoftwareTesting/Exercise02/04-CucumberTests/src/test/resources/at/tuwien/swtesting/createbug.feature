# Hagn Maximilian
# 11808237
# Exercise 02

Feature: Create new bug on Bugzilla
  As a user I want to create a new bug with summary and description.

  @CreateBug
  Scenario: Create new bug
    Given I go to the createbugpage
    When I set the summary to "This is a test summary."
    And I set the description to "This is a test description."
    And I create the bug entry
    Then I should see the success message "has been successfully created"