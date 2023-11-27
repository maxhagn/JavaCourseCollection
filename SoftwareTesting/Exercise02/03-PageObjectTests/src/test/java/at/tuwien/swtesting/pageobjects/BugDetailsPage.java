/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting.pageobjects;

import at.tuwien.swtesting.shared.CreateBugStatus;
import at.tuwien.swtesting.shared.Resolution;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BugDetailsPage extends AbstractPage {

    @FindBy(xpath = "//div[@id='bugzilla-body']/dl/dt[contains(.,'successfully created')]")
    private WebElement successMessage;

    @FindBy(linkText = "Clone This Bug")
    private WebElement cloneBugButton;

    @FindBy(id = "bug_status")
    private WebElement bugStatusSelect;

    @FindBy(id = "resolution")
    private WebElement resolutionSelect;

    @FindBy(id = "commit")
    private WebElement commitButton;

    @FindBy(className = "bz_bug_link")
    private WebElement clonedBugLink;

    @FindBy(id = "comment")
    private WebElement commentInput;

    @FindBy(xpath = "//pre[contains(.,'This bug was resolved!')]")
    private WebElement bugComment;

    public BugDetailsPage(WebDriver driver) {
        super(driver);
    }

    protected boolean isMatchingPage() {
        String expectedURLSubstring = "/show_bug.cgi?";
        return driver.getCurrentUrl().contains(expectedURLSubstring);
    }

    public String getStatus() {
        return bugStatusSelect.getAttribute("value");
    }

    public BugDetailsPage setStatus(CreateBugStatus status) {
        bugStatusSelect.findElement(By.xpath("//*[@id = '" + status.getText() + "']")).click();
        return PageFactory.initElements(driver, BugDetailsPage.class);
    }

    public String getResolution() {
        return resolutionSelect.getAttribute("value");
    }

    public BugDetailsPage setResolution(Resolution resolution) {
        resolutionSelect.findElement(By.xpath("//*[@id = '" + resolution.getText() + "']")).click();
        return PageFactory.initElements(driver, BugDetailsPage.class);
    }

    public String getBugResolvedComment() {
        return bugComment.getText();
    }

    public BugDetailsPage setComment(String comment) {
        commentInput.clear();
        commentInput.sendKeys(comment);
        return PageFactory.initElements(driver, BugDetailsPage.class);
    }

    public String getComment() {
        return commentInput.getAttribute("value");
    }

    public CreateBugPage cloneBug() {
        cloneBugButton.click();
        return PageFactory.initElements(driver, CreateBugPage.class);
    }

    public ProcessBugPage saveChanges() {
        commitButton.submit();
        return PageFactory.initElements(driver, ProcessBugPage.class);
    }

    public BugDetailsPage createBug() {
        commitButton.submit();
        return PageFactory.initElements(driver, BugDetailsPage.class);
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public BugDetailsPage gotoClonedBug() {
        clonedBugLink.click();
        return PageFactory.initElements(driver, BugDetailsPage.class);
    }

}


