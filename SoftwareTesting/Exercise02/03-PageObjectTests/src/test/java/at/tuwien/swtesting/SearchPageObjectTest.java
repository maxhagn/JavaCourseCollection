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
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class SearchPageObjectTest {

    private static WebDriver driver;
    private HomePage homePage;

    @BeforeAll
    public static void setUpBeforeAll() {
        WebDriverManager.getInstance(Constants.DRIVER_TYPE).setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void tearDownAfterAll() {
        driver.close();
        driver.quit();
    }

    @BeforeEach
    public void setUp() {
        homePage = HomePage.navigateTo(driver, Constants.BASE_URL);
    }

    @Test
    @DisplayName(value = "Navigating to the new section, entering a summary and a description, committing the bug should create the bug successfully.")
    void loginPage_createBug_returnsSuccessMessage() {

        // Log the user in.
        assertFalse(homePage.isLoggedin(), "To guarantee a fresh start, the user must not be logged in already.");
        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constants.USERNAME, Constants.PASSWORD);
        assertTrue(homePage.isLoggedin(), "After the log in process, the user should be logged in.");

        //  Navigate to the search section.
        SearchPage searchPage = homePage.gotoSearchPage();

        // Type the search words.
        searchPage.setSearchWords("This is one of three closed tests");
        assertEquals("This is one of three closed tests", searchPage.getSearchWords(), "Verify that the search words were set successfully.");

        // Select the bug status closed.
        searchPage.setProduct(Product.TEST_PRODUCT);
        assertEquals(Product.TEST_PRODUCT, Product.fromText(searchPage.getProduct()), "Verify that the product was set successfully.");

        // Select the product TestProduct closed.
        searchPage.setStatus(SearchBugStatus.CLOSED);
        assertEquals(SearchBugStatus.CLOSED, SearchBugStatus.fromText(searchPage.getStatus()), "Verify that the status was set successfully.");

        // Click on search.
        BugListPage bugListPage = searchPage.startSearch();

        // Verify that the string "3 bugs found." is contained on this page.
        String searchCountMessage = bugListPage.getSearchCountMessage();
        assertTrue(searchCountMessage.contains("3 bugs found."), "Since three predefined bugs are available under these search conditions, the string 3 bugs founds must be contained on the page.");
    }
}
