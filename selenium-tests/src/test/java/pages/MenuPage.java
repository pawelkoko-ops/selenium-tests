package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    private By mainItem(String text) {
        return By.linkText(text);
    }

    private By subItem(String text) {
        return By.xpath("//a[text()='" + text + "']");
    }

    public void hoverMainItem(String text) {
    	WebElement el = driver.findElement(mainItem(text));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        actions.moveToElement(el).perform();
    }

    public void hoverSubItem(String text) {
        WebElement el = driver.findElement(subItem(text));
        actions.moveToElement(el).perform();
    }

    public void clickSubItem(String text) {
        WebElement el = driver.findElement(subItem(text));
        actions.moveToElement(el).click().perform();
    }

    public boolean isDisplayedMainItem(String text) {
        return driver.findElement(mainItem(text)).isDisplayed();
    }

    public boolean isDisplayedSubItem(String text) {
        return driver.findElement(subItem(text)).isDisplayed();
    }

    public boolean isEnabledSubItem(String text) {
        return driver.findElement(subItem(text)).isEnabled();
    }
}