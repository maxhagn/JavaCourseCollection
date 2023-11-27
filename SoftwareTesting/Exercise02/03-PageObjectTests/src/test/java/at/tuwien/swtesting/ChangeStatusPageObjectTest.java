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
import at.tuwien.swtesting.shared.SearchBugStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeStatusPageObjectTest {

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

        // Navigate to the browse section.
        DescribeComponentsPage describeComponentsPage = homePage.gotoDescribeComponentsPage();
        BugListPage bugListPage = describeComponentsPage.gotoBugListPage();

        // Clone the first entry.
        BugDetailsPage bugDetailsPage = bugListPage.gotoBugById("1");
        bugDetailsPage.cloneBug();

        // Save the cloned entry.
        bugDetailsPage = bugDetailsPage.createBug();

        // Click on the cloned entry.
        bugDetailsPage = bugDetailsPage.gotoClonedBug();

        // Set the bug status to resolved.
        bugDetailsPage.setStatus(CreateBugStatus.RESOLVED);
        assertEquals(CreateBugStatus.RESOLVED, CreateBugStatus.valueOf(bugDetailsPage.getStatus()), "Verify that the status was set successfully.");

        // Set the resolution to fixed.
        bugDetailsPage.setResolution(Resolution.FIXED);
        assertEquals(Resolution.FIXED, Resolution.valueOf(bugDetailsPage.getResolution()), "Verify that the resolution was set successfully.");

        // Type the comment.
        bugDetailsPage.setComment("This bug was resolved!");
        assertEquals("This bug was resolved!", bugDetailsPage.getComment(), "Verify that the comment was typed successfully.");

        // Save the changes to the bug.
        ProcessBugPage processBugPage = bugDetailsPage.saveChanges();

        // Open the updated bug.
        bugDetailsPage = processBugPage.gotoUpdatedBug();

        // Verify that the bug status was updated successfully.
        String status = bugDetailsPage.getStatus();
        assertEquals(CreateBugStatus.RESOLVED, CreateBugStatus.valueOf(status), "Since the bug status must be resolved, the bug status should be resolved on the details page.");

        // Verify that the resolution was updated successfully.
        String resolution = bugDetailsPage.getResolution();
        assertEquals(Resolution.FIXED, Resolution.valueOf(resolution), "Since the resolution must be fixed, the resolution should be fixed on the details page.");

        // Verify that the comment is present.
        String comment = bugDetailsPage.getBugResolvedComment();
        assertTrue(comment.contains("This bug was resolved!"), "Since a comment must be added to the updated bug, the comment should be present on the details page.");
    }
}
