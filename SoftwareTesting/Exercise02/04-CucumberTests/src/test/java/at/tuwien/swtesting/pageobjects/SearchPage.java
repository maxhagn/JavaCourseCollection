/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting.pageobjects;

import at.tuwien.swtesting.shared.Product;
import at.tuwien.swtesting.shared.SearchBugStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage extends AbstractPage {

    @FindBy(id = "content")
    private WebElement searchWordsInput;

    @FindBy(id = "search")
    private WebElement searchButton;

    @FindBy(id = "bug_status")
    private WebElement bugStatusSelect;

    @FindBy(id = "product")
    private WebElement productSelect;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    protected boolean isMatchingPage() {
        String expectedPageTitleRegex = "Simple Search";
        return driver.getTitle().matches(expectedPageTitleRegex);
    }

    public SearchPage setSearchWords(String searchWords) {
        searchWordsInput.clear();
        searchWordsInput.sendKeys(searchWords);
        return PageFactory.initElements(driver, SearchPage.class);
    }

    public String getSearchWords() {
        return searchWordsInput.getAttribute("value");
    }

    public SearchPage setStatus(SearchBugStatus status) {
        bugStatusSelect.findElement(By.xpath("//*[@value = '" + status.getText() + "']")).click();
        return PageFactory.initElements(driver, SearchPage.class);
    }

    public String getStatus() {
        return bugStatusSelect.getAttribute("value");
    }

    public SearchPage setProduct(Product product) {
        productSelect.findElement(By.xpath("//*[@value = '" + product.getText() + "']")).click();
        return PageFactory.initElements(driver, SearchPage.class);
    }

    public String getProduct() {
        return productSelect.getAttribute("value");
    }

    public BugListPage startSearch() {
        searchButton.submit();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return PageFactory.initElements(driver, BugListPage.class);
    }

}


