package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TabsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public TabsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public void clickTab(String tabName) {
        By tabId;
        By contentId;
        String expectedText;

        switch (tabName) {
            case "What":
                tabId = By.id("demo-tab-what");
                contentId = By.id("demo-tabpane-what");
                expectedText = "Lorem Ipsum";
                break;
            case "Origin":
                tabId = By.id("demo-tab-origin");
                contentId = By.id("demo-tabpane-origin");
                expectedText = "Contrary to popular belief";
                break;
            case "Use":
                tabId = By.id("demo-tab-use");
                contentId = By.id("demo-tabpane-use");
                expectedText = "It is a long established fact";
                break;
            default:
                throw new IllegalArgumentException("Unknown tab: " + tabName);
        }

        WebElement tab = driver.findElement(tabId);
        js.executeScript("arguments[0].scrollIntoView(true);", tab);
        tab.click();
        WebElement content = wait.until(ExpectedConditions.visibilityOfElementLocated(contentId));
        if (!content.getText().contains(expectedText)) {
            throw new AssertionError("Content does not match for tab " + tabName);
        }
    }

    public boolean isMoreTabDisabled() {
        WebElement moreTab = driver.findElement(By.id("demo-tab-more"));
        return Boolean.parseBoolean(moreTab.getAttribute("aria-disabled"));
    }
}
