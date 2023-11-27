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

public class SearchTest {

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
    @DisplayName(value = "Searching for bugs with status closed and specified search words should return three bugs.")
    void homePage_searchQuery_returnListOfBugs() {

        //  Navigate to the search section.
        driver.findElement(By.linkText("Search")).click();

        // Click on the search words.
        driver.findElement(By.id("content")).click();

        // Type the search words.
        driver.findElement(By.id("content")).sendKeys("This is one of three closed tests");

        // Verify that the content was set to the search string.
        String value = driver.findElement(By.id("content")).getAttribute("value");
        assertEquals("This is one of three closed tests", value, "Verify that the value of the search words is correct.");

        // Select the bug status closed.
        WebElement element = driver.findElement(By.id("bug_status"));
        element.findElement(By.xpath("//*[@value = '__closed__']")).click();

        // Verify that the value of the bug status was set to "__closed__".
        value = driver.findElement(By.id("bug_status")).getAttribute("value");
        assertEquals("__closed__", value, "Verify that the value of the bug status is closed.");

        // Select the product TestProduct closed.
        element = driver.findElement(By.id("product"));
        element.findElement(By.xpath("//*[@value = 'TestProduct']")).click();

        // Verify that the value of the product was set to "TestProduct".
        value = driver.findElement(By.id("product")).getAttribute("value");
        assertEquals("TestProduct", value, "Verify that the value of the product is TestProduct.");

        // Click on search.
        driver.findElement(By.id("search")).click();

        // Verify that the string "3 bugs found." is contained on this page.
        element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(.,'3 bugs found.')]")));
        assertNotNull(element, "Since three predefined bugs are available under these search conditions, the string 3 bugs founds must be contained on the page.");
    }
}
