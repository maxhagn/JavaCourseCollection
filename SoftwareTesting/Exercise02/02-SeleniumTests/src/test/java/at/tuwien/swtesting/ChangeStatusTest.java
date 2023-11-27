/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting;

import at.tuwien.swtesting.shared.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChangeStatusTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpBeforeAll() {
        WebDriverManager.getInstance(Constants.DRIVER_TYPE).setup();
        driver = new ChromeDriver();
        driver.get(Constants.BASE_URL);
        driver.get(Constants.BASE_URL + "index.cgi?GoAheadAndLogIn=1");
        WebElement loginName = driver.findElement(By.id("Bugzilla_login"));
        loginName.clear();
        loginName.sendKeys(Constants.USERNAME);
        WebElement loginPassword = driver.findElement(By.id("Bugzilla_password"));
        loginPassword.clear();
        loginPassword.sendKeys(Constants.PASSWORD);
        WebElement loginButton = driver.findElement(By.id("log_in"));
        loginButton.submit();
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    @DisplayName(value = "Cloning a bug and updating the status, the resolution and adding a comment must update the bug correctly.")
    void homePage_updateClonedBug_returnUpdatedBug() {

        // Open the browse section.
        driver.findElement(By.linkText("Browse")).click();

        // Click on the TestComponent section.
        driver.findElement(By.linkText("TestComponent")).click();

        // Open the first entry in the list.
        driver.findElement(By.linkText("1")).click();

        // Clone the first entry.
        driver.findElement(By.linkText("Clone This Bug")).click();

        // Save the cloned entry.
        driver.findElement(By.id("commit")).click();

        // Click on the cloned entry.
        driver.findElement(By.cssSelector("a > b")).click();

        // Click the comment window.
        driver.findElement(By.id("comment")).click();

        // Type the comment.
        driver.findElement(By.id("comment")).sendKeys("This bug was resolved!");

        // Set the bug status to resolved.
        WebElement dropdown = driver.findElement(By.id("bug_status"));
        dropdown.findElement(By.xpath("//option[. = 'RESOLVED']")).click();

        // Verify that the value was set to resolved.
        String value = driver.findElement(By.id("bug_status")).getAttribute("value");
        assertEquals("RESOLVED", value, "Verify that the value of the bug status is resolved.");

        // Verify that the value was set to fixed.
        value = driver.findElement(By.id("resolution")).getAttribute("value");
        assertEquals("FIXED", value, "Verify that the value of the resolution is fixed.");

        // Save the changes to the bug.
        driver.findElement(By.id("commit")).click();

        // Open the updated bug.
        driver.findElement(By.className("bz_bug_link")).click();

        // Verify that the bug status was updated successfully.
        value = driver.findElement(By.id("bug_status")).getAttribute("value");
        assertEquals("RESOLVED", value, "Since the bug status must be resolved, the bug status should be resolved on the details page.");

        // Verify that the resolution was updated successfully.
        value = driver.findElement(By.id("resolution")).getAttribute("value");
        assertEquals("FIXED", value, "Since the resolution must be fixed, the resolution should be fixed on the details page.");

        // Verify that the comment is present.
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//pre[contains(.,'This bug was resolved!')]")));
        assertNotNull(element, "Since a comment must be added to the updated bug, the comment should be present on the details page.");

    }
}
