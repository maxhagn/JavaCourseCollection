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

public class CreateBugTest {

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
    @DisplayName(value = "Creating a bug with summary and description should create new bug.")
    void homePage_createBug_returnCreatedBug() {

        // Navigate to the new section.
        driver.findElement(By.linkText("New")).click();

        // Click on the short description section.
        driver.findElement(By.id("short_desc")).click();

        // Enter the short description.
        driver.findElement(By.id("short_desc")).sendKeys("This is the first Test");

        // Verify that the short description was set as value.
        String value = driver.findElement(By.id("short_desc")).getAttribute("value");
        assertEquals("This is the first Test", value, "Verify that the value of the summary is correct.");

        // Click on the comment section.
        driver.findElement(By.id("comment")).click();

        // Enter the comment.
        driver.findElement(By.id("comment")).sendKeys("This is the first test that i recorded with selenium.");

        // Verify that the comment was set as value.
        value = driver.findElement(By.id("comment")).getAttribute("value");
        assertEquals("This is the first test that i recorded with selenium.", value, "Verify that the value of the comment is correct.");

        // Create the bug.
        driver.findElement(By.id("commit")).click();

        // Verify that the element with id bugzilla-body contains the string "successfully created".
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='bugzilla-body']/dl/dt[contains(.,'successfully created')]")));
        assertNotNull(element, "Since a new bug must be added, the success message must be displayed on the next page.");

    }
}