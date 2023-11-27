/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    @FindBy(linkText = "Home")
    private WebElement homeLink;

    @FindBy(id = "Bugzilla_login")
    private WebElement loginName;

    @FindBy(id = "Bugzilla_password")
    private WebElement loginPassword;

    @FindBy(id = "log_in")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static LoginPage navigateTo(WebDriver driver, String baseUrl) {
        driver.get(baseUrl + "index.cgi?GoAheadAndLogIn=1");
        return PageFactory.initElements(driver, LoginPage.class);
    }

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "Log in to Bugzilla";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

    public HomePage login(String username, String password) {
        // WebElement loginName = driver.findElement(By.id("Bugzilla_login"));
        loginName.clear();
        loginName.sendKeys(username);

        // WebElement loginPassword = driver.findElement(By.id("Bugzilla_password"));
        loginPassword.clear();
        loginPassword.sendKeys(password);

        // WebElement loginButton = driver.findElement(By.id("log_in"));
        loginButton.submit();

        return PageFactory.initElements(driver, HomePage.class);
    }

    public HomePage gotoHomePage() {
        homeLink.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

}


