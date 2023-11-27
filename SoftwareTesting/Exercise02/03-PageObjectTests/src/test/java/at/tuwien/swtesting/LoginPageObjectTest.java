/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting;

import at.tuwien.swtesting.shared.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.LoginPage;

public class LoginPageObjectTest {

    private static WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

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
    public void testLoginLogout() {

        // Log the user in.
        assertFalse(homePage.isLoggedin());
        loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constants.USERNAME, Constants.PASSWORD);
        assertTrue(homePage.isLoggedin());

        // Log the user out.
        homePage = homePage.logout();
        assertFalse(homePage.isLoggedin());
    }
}
