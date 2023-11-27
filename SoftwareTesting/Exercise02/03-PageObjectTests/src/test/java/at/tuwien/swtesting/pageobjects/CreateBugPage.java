/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateBugPage extends AbstractPage {

    @FindBy(id = "short_desc")
    private WebElement summaryInput;

    @FindBy(id = "comment")
    private WebElement descriptionInput;

    @FindBy(id = "commit")
    private WebElement commitButton;


    public CreateBugPage(WebDriver driver) {
        super(driver);
    }

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "Enter Bug: TestProduct";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

    public CreateBugPage setSummary(String description) {
        summaryInput.clear();
        summaryInput.sendKeys(description);
        return PageFactory.initElements(driver, CreateBugPage.class);
    }

    public String getSummary() {
        return summaryInput.getAttribute("value");
    }

    public CreateBugPage setDescription(String description) {
        descriptionInput.clear();
        descriptionInput.sendKeys(description);
        return PageFactory.initElements(driver, CreateBugPage.class);
    }

    public String getDescription() {
        return descriptionInput.getAttribute("value");
    }

    public BugDetailsPage createBugEntry() {
        commitButton.submit();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return PageFactory.initElements(driver, BugDetailsPage.class);
    }

}


