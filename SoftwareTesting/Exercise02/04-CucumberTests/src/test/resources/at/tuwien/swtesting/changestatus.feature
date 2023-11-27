# Hagn Maximilian
# 11808237
# Exercise 02

Feature: Clone bug and set status to closed
  As a user I want to set a cloned bug to status closed and add a comment

  @ChangeStatus
  Scenario: Clone and resolve bug
    Given I go to the describecomponentspage
    When I go to the buglistpage
    And I go to the bug with ID "1"
    And I clone the bug and create a new one
    And I go to the cloned bug
    And I set the bug status to "RESOLVED" and resolution to "FIXED" with comment "This bug was resolved!"
    Then I should see the status "RESOLVED", resolution "FIXED" and comment "This bug was resolved!"