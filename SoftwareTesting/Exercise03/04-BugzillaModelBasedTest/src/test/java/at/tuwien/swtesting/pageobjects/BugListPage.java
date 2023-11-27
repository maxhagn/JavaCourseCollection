/**
 * Hagn Maximilian
 * 11808237
 * Exercise 03
 **/

package at.tuwien.swtesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BugListPage extends AbstractPage {

    @FindBy(xpath = "//span[contains(.,'3 bugs found.')]")
    private WebElement searchCountMessage;

    public BugListPage(WebDriver driver) {
        super(driver);
    }

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "Bug List";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

    public BugDetailsPage gotoBugById(String id) {
        driver.findElement(By.xpath("//*[@href = 'show_bug.cgi?id=" + id + "']")).click();
        return PageFactory.initElements(driver, BugDetailsPage.class);
    }

    public String getSearchCountMessage() {
        return searchCountMessage.getText();
    }


}


