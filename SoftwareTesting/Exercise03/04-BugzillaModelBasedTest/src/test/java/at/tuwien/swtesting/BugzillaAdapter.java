/**
 * Hagn Maximilian
 * 11808237
 * Exercise 03
 **/

package at.tuwien.swtesting;

import at.tuwien.swtesting.pageobjects.BugDetailsPage;
import at.tuwien.swtesting.pageobjects.BugListPage;
import at.tuwien.swtesting.pageobjects.CreateBugPage;
import at.tuwien.swtesting.pageobjects.DescribeComponentsPage;
import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.ProcessBugPage;
import at.tuwien.swtesting.shared.Constants;
import at.tuwien.swtesting.shared.CreateBugStatus;
import at.tuwien.swtesting.shared.Resolution;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Adapter for Bugzilla. It provides methods to access Bugzilla from the model.
 * The adapter is configured in the setup of the test and passed to the
 * constructor of the model.
 */
public class BugzillaAdapter {
    WebDriver webDriver;
    HomePage homePage;
    BugDetailsPage bugDetailsPage;

    BugzillaAdapter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Creates a new bug
     */
    public void createNewBug() {
        homePage = HomePage.navigateTo(webDriver, Constants.BASE_URL);
        CreateBugPage createBugPage = homePage.gotoCreateBugPage();
        createBugPage.setSummary("This is a test summary.");
        assertEquals("This is a test summary.",
                     createBugPage.getSummary(),
                     "Verify that the summary was set successfully.");
        createBugPage.setDescription("This is a test description.");
        assertEquals("This is a test description.",
                     createBugPage.getDescription(),
                     "Verify that the description was set successfully.");
        bugDetailsPage = createBugPage.createBugEntry();
        String successMessage = bugDetailsPage.getSuccessMessage();
        assertTrue(successMessage.contains("has been successfully created"),
                   "Since the bug should be created successfully, the expected string must be contained in the return"
                   + " message area.");
        bugDetailsPage = bugDetailsPage.gotoCreatedBug();
    }

    /**
     * Changes the status of the bug entry on the Bugzilla Web page
     * to NEW (CONFIRMED) and checks that the status has been changed afterwards.
     *
     * @return true if change was successful.
     */
    public boolean performChangeToNew() {
        try {
            setBugStatus(CreateBugStatus.CONFIRMED);
        } catch (AssertionError e) {
            return false;
        }
        return true;
    }

    /**
     * Changes the status of the bug entry on the Bugzilla Web page
     * to IN_PROGRESS and checks that the status has been changed afterwards.
     *
     * @return true if change was successful.
     */
    public boolean performChangeToInProgress() {
        try {
            setBugStatus(CreateBugStatus.IN_PROGRESS);
        } catch (AssertionError e) {
            return false;
        }
        return true;
    }

    /**
     * Changes the status of the bug entry on the Bugzilla Web page
     * to RESOLVED and checks that the status has been changed afterwards.
     *
     * @return true if change was successful.
     */
    public boolean performChangeToResolved() {
        try {
            setBugStatus(CreateBugStatus.RESOLVED);
        } catch (AssertionError e) {
            return false;
        }
        return true;
    }

    /**
     * Changes the status of the bug entry on the Bugzilla Web page
     * to VERIFIED and checks that the status has been changed afterwards.
     *
     * @return true if change was successful.
     */
    public boolean performChangeToVerified() {
        try {
            setBugStatus(CreateBugStatus.VERIFIED);
        } catch (AssertionError e) {
            return false;
        }
        return true;
    }

    /**
     * Changes the status of the bug entry on the Bugzilla Web page
     * to UNCONFIRMED and checks that the status has been changed afterwards.
     *
     * @return true if change was successful.
     */
    public boolean performChangeToUnconfirmed() {
        try {
            setBugStatus(CreateBugStatus.UNCONFIRMED);
        } catch (AssertionError e) {
            return false;
        }
        return true;
    }

    private void setBugStatus(CreateBugStatus bugStatus) throws AssertionError {
        bugDetailsPage.setStatus(bugStatus);
        assertEquals(bugStatus, CreateBugStatus.valueOf(bugDetailsPage.getStatus()), "Verify that the status was set successfully.");
        bugDetailsPage.setComment("The status was set to " + bugStatus);
        assertEquals("The status was set to " + bugStatus, bugDetailsPage.getComment(), "Verify that the comment was typed successfully.");
        ProcessBugPage processBugPage = bugDetailsPage.saveChanges();
        bugDetailsPage = processBugPage.gotoUpdatedBug();
        assertEquals(bugStatus, CreateBugStatus.valueOf(bugDetailsPage.getStatus()), "Since the bug status must be resolved, the bug status should be resolved on the details page.");
        assertTrue(bugDetailsPage.getLastComment().contains("The status was set to " + bugStatus), "Since a comment must be added to the updated bug, the comment should be present on the details page.");
    }
}
