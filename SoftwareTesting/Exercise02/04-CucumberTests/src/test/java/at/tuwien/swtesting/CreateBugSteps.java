/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting;

import at.tuwien.swtesting.pageobjects.BugDetailsPage;
import at.tuwien.swtesting.pageobjects.CreateBugPage;
import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.LoginPage;
import at.tuwien.swtesting.shared.Constants;
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

public class CreateBugSteps {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private CreateBugPage createBugPage;
    private BugDetailsPage bugDetailsPage;

    @Before(value = "@CreateBug")
    public void setUpBeforeClass() {
        WebDriverManager.getInstance(Constants.DRIVER_TYPE).setup();
        driver = new ChromeDriver();
        homePage = HomePage.navigateTo(driver, Constants.BASE_URL);
        loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constants.USERNAME, Constants.PASSWORD);
        assertEquals("Bugzilla Main Page", homePage.getTitle());
    }

    @After(value = "@CreateBug")
    public void tearDownAfterClass() {
        driver.close();
        driver.quit();
    }

    @Given("I go to the createbugpage")
    public void i_go_to_the_createbugpage() {
        createBugPage = homePage.gotoCreateBugPage();
    }

    @When("I set the summary to {string}")
    public void i_set_the_summary_to(String summary) {
        createBugPage.setSummary(summary);
    }

    @When("I set the description to {string}")
    public void i_set_the_description_to(String description) {
        createBugPage.setDescription(description);
    }

    @When("I create the bug entry")
    public void i_create_the_bug_entry() {
        bugDetailsPage = createBugPage.createBugEntry();
    }

    @Then("I should see the success message {string}")
    public void i_should_see_the_success_message(String message) {
        String successMessage = bugDetailsPage.getSuccessMessage();
        assertTrue(successMessage.contains(message), "Since the bug should be created successfully, the expected string must be contained in the return message area.");
    }
}
