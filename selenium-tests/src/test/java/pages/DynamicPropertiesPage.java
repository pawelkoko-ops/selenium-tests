package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DynamicPropertiesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By enableBtn = By.id("enableAfter");
    private By colorBtn = By.id("colorChange");
    private By visibleAfterBtn = By.id("visibleAfter");

    public DynamicPropertiesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getInitialColor() {
        return driver.findElement(colorBtn).getCssValue("color");
    }

    public void waitUntilButtonEnabled() {
        wait.until(ExpectedConditions.elementToBeClickable(enableBtn));
    }

    public String getChangedColor() {
        return driver.findElement(colorBtn).getCssValue("color");
    }

    public void waitUntilVisibleAppears() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(visibleAfterBtn));
    }

    public boolean isVisibleAfterDisplayed() {
        return driver.findElement(visibleAfterBtn).isDisplayed();
    }
}