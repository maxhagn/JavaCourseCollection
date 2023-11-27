/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting;

import at.tuwien.swtesting.pageobjects.*;
import at.tuwien.swtesting.shared.Constants;
import at.tuwien.swtesting.shared.CreateBugStatus;
import at.tuwien.swtesting.shared.Resolution;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeStatusSteps {

    private WebDriver driver;
    private HomePage homePage;
    private DescribeComponentsPage describeComponentsPage;
    private BugListPage bugListPage;
    private BugDetailsPage bugDetailsPage;

    @Before(value = "@ChangeStatus")
    public void setUpBeforeClass() {
        WebDriverManager.getInstance(Constants.DRIVER_TYPE).setup();
        driver = new ChromeDriver();
        homePage = HomePage.navigateTo(driver, Constants.BASE_URL);
        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constants.USERNAME, Constants.PASSWORD);
        assertEquals("Bugzilla Main Page", homePage.getTitle());
    }

    @After(value = "@ChangeStatus")
    public void tearDownAfterClass() {
        driver.close();
        driver.quit();
    }

    @Given("I go to the describecomponentspage")
    public void i_go_to_the_describecomponentspage() {
        describeComponentsPage = homePage.gotoDescribeComponentsPage();
    }

    @When("I go to the buglistpage")
    public void i_go_to_the_buglistpage() {
        bugListPage = describeComponentsPage.gotoBugListPage();
    }

    @When("I go to the bug with ID {string}")
    public void i_go_to_the_bug_with_id(String id) {
        bugDetailsPage = bugListPage.gotoBugById(id);
    }

    @When("I clone the bug and create a new one")
    public void i_clone_the_bug_and_create_a_new_one() {
        bugDetailsPage.cloneBug();
        bugDetailsPage = bugDetailsPage.createBug();
    }

    @When("I go to the cloned bug")
    public void i_go_to_the_cloned_bug() {
        bugDetailsPage = bugDetailsPage.gotoClonedBug();
    }

    @When("I set the bug status to {string} and resolution to {string} with comment {string}")
    public void i_set_the_bug_status_and_resolution_with_comment(String status, String resolution, String comment) {
        bugDetailsPage.setStatus(CreateBugStatus.valueOf(status));
        bugDetailsPage.setResolution(Resolution.valueOf(resolution));
        bugDetailsPage.setComment(comment);
        ProcessBugPage processBugPage = bugDetailsPage.saveChanges();
        bugDetailsPage = processBugPage.gotoUpdatedBug();
    }

    @Then("I should see the status {string}, resolution {string} and comment {string}")
    public void i_should_see_the_status_resolution_and_comment(String status, String resolution, String comment) {
        assertEquals(CreateBugStatus.valueOf(status), CreateBugStatus.valueOf(bugDetailsPage.getStatus()), "Since the bug status must be resolved, the bug status should be resolved on the details page.");
        assertEquals(Resolution.valueOf(resolution), Resolution.valueOf(bugDetailsPage.getResolution()), "Since the resolution must be fixed, the resolution should be fixed on the details page.");
        assertTrue(bugDetailsPage.getBugResolvedComment().contains(comment), "Since a comment must be added to the updated bug, the comment should be present on the details page.");
    }

}
