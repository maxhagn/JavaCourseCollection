/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting;

import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private static final String BASE_URL = "http://192.168.18.89/";
    private static final DriverManagerType DRIVER_TYPE = DriverManagerType.CHROME;

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void setUpBeforeClass() {
        WebDriverManager.getInstance(DRIVER_TYPE).setup();
        driver = new ChromeDriver();
    }

    @After
    public void tearDownAfterClass() {
        driver.close();
        driver.quit();
    }


    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        homePage = HomePage.navigateTo(driver, BASE_URL);
    }

    @When("I go to the loginpage")
    public void i_goto_the_loginpage() {
        loginPage = homePage.gotoLoginPage();
    }

    @When("I login as user {string} with password {string}")
    public void i_login_as_user_with_password(String username, String password) {
        homePage = loginPage.login(username, password);
    }

    @Then("I should be on the homepage")
    public void i_should_be_on_the_homepage() {
        assertEquals("Bugzilla Main Page", homePage.getTitle());
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        assertTrue(homePage.isLoggedin());
    }

}
