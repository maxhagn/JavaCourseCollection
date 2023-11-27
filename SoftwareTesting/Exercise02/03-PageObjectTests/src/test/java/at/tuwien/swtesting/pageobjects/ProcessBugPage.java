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

public class ProcessBugPage extends AbstractPage {

    @FindBy(className = "bz_bug_link")
    private WebElement updatedBugLink;

    public ProcessBugPage(WebDriver driver) {
        super(driver);
    }

    protected boolean isMatchingPage() {
        String expectedURLSubstring = "/process_bug.cgi";
        return driver.getCurrentUrl().contains(expectedURLSubstring);
    }

    public BugDetailsPage gotoUpdatedBug() {
        updatedBugLink.click();
        return PageFactory.initElements(driver, BugDetailsPage.class);
    }

}


