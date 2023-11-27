/**
 * Hagn Maximilian
 * 11808237
 * Exercise 03
 **/

package at.tuwien.swtesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class AbstractPage {

    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;

        if (!isMatchingPage()) {
            String errorMsg = "Page object " + this.getClass().getName()
                    + " does not match the displayed page (title: " + driver.getTitle() + ")!";
            throw new IllegalStateException(errorMsg);
        }
    }

    abstract protected boolean isMatchingPage();

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

    protected String getSelectedOptionValue(WebElement element) {
        Select select = new Select(element);
        WebElement selectedElement = select.getFirstSelectedOption();
        String selectedOption = selectedElement.getAttribute("value");
        return selectedOption;
    }
}


