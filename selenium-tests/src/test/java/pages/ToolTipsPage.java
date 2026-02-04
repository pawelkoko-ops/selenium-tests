package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ToolTipsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By tooltip = By.className("tooltip-inner");

    public ToolTipsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    public void hover(By locator) {
        WebElement el = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        actions.moveToElement(el).perform();
    }

    public void waitForTooltipText(String text) {
        wait.until(ExpectedConditions.textToBe(tooltip, text));
    }

    public String getTooltipText() {
        return driver.findElement(tooltip).getText();
    }
}