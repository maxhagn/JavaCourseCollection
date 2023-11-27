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
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private static WebDriver driver;


    @BeforeAll
    public static void setUpBeforeAll() {
        WebDriverManager.getInstance(Constants.DRIVER_TYPE).setup();
        driver = new ChromeDriver();
        driver.get(Constants.BASE_URL);
    }

    @AfterAll
    public static void tearDownAfterAll() {
        driver.close();
        driver.quit();
    }

    @Test
    public void testLoginLogout() {
        assertFalse(isElementPresent(By.linkText("Log out")));

        driver.get(Constants.BASE_URL + "index.cgi?GoAheadAndLogIn=1");

        // on login page ...

        WebElement loginName = driver.findElement(By.id("Bugzilla_login"));
        loginName.clear();
        loginName.sendKeys(Constants.USERNAME);

        WebElement loginPassword = driver.findElement(By.id("Bugzilla_password"));
        loginPassword.clear();
        loginPassword.sendKeys(Constants.PASSWORD);

        WebElement loginButton = driver.findElement(By.id("log_in"));
        loginButton.submit();

        // back on homepage ...

        assertTrue(isElementPresent(By.linkText("Log out")));

        WebElement logoutLink = driver.findElement(By.linkText("Log out"));
        logoutLink.click();

        assertFalse(isElementPresent(By.linkText("Log out")));
    }


    private boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

}