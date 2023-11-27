/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting;

import at.tuwien.swtesting.pageobjects.BugListPage;
import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.LoginPage;
import at.tuwien.swtesting.pageobjects.SearchPage;
import at.tuwien.swtesting.shared.Constants;
import at.tuwien.swtesting.shared.Product;
import at.tuwien.swtesting.shared.SearchBugStatus;
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

public class SearchSteps {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private BugListPage bugListPage;

    @Before(value = "@SearchBugs")
    public void setUpBeforeClass() {
        WebDriverManager.getInstance(Constants.DRIVER_TYPE).setup();
        driver = new ChromeDriver();
        homePage = HomePage.navigateTo(driver, Constants.BASE_URL);
        loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constants.USERNAME, Constants.PASSWORD);
        assertEquals("Bugzilla Main Page", homePage.getTitle());
    }

    @After(value = "@SearchBugs")
    public void tearDownAfterClass() {
        driver.close();
        driver.quit();
    }

    @Given("I go to the searchpage")
    public void i_goto_the_searchpage() {
        searchPage = homePage.gotoSearchPage();
    }

    @When("I set the search words to {string}")
    public void i_set_the_search_words_to(String searchWords) {
        searchPage.setSearchWords(searchWords);
    }

    @When("I set the product to TEST_PRODUCT")
    public void i_set_the_product_to_test_product() {
        searchPage.setProduct(Product.TEST_PRODUCT);
    }

    @When("I set the status to CLOSED")
    public void i_set_the_status_to_closed() {
        searchPage.setStatus(SearchBugStatus.CLOSED);
    }

    @When("I start the search")
    public void i_start_the_search() {
        bugListPage = searchPage.startSearch();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String message) {
        String searchCountMessage = bugListPage.getSearchCountMessage();
        assertTrue(searchCountMessage.contains(message), "Since three predefined bugs are available under these search conditions, the string 3 bugs founds must be contained on the page.");
    }

}
