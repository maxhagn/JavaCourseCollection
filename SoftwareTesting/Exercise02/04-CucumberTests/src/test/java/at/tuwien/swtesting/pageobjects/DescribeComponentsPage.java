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

public class DescribeComponentsPage extends AbstractPage {

    @FindBy(linkText = "TestComponent")
    WebElement testComponentLink;

    public DescribeComponentsPage(WebDriver driver) {
        super(driver);
    }

    protected boolean isMatchingPage() {
        String expectedURLSubstring = "/describecomponents.cgi";
        return driver.getCurrentUrl().contains(expectedURLSubstring);
    }

    public BugListPage gotoBugListPage() {
        testComponentLink.click();
        return PageFactory.initElements(driver, BugListPage.class);
    }

}


