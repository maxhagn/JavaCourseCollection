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
import at.tuwien.swtesting.shared.CreateBugStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class CreateBugPageObjectTest {

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

        // Navigate to the new section.
        CreateBugPage createBugPage = homePage.gotoCreateBugPage();

        // Enter the summary.
        createBugPage.setSummary("This is a test summary.");
        assertEquals("This is a test summary.", createBugPage.getSummary(), "Verify that the summary was set successfully.");

        // Enter the description.
        createBugPage.setDescription("This is a test description.");
        assertEquals("This is a test description.", createBugPage.getDescription(), "Verify that the description was set successfully.");

        // Create the bug.
        BugDetailsPage bugDetailsPage = createBugPage.createBugEntry();

        // Verify that the element with id bugzilla-body contains the string "successfully created".
        String successMessage = bugDetailsPage.getSuccessMessage();
        assertTrue(successMessage.contains("has been successfully created"), "Since the bug should be created successfully, the expected string must be contained in the return message area.");
    }
}
