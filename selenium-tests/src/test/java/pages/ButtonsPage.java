package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ButtonsPage {

    private WebDriver driver;

    private By doubleClickBtn = By.id("doubleClickBtn");
    private By rightClickBtn = By.id("rightClickBtn");
    private By dynamicClickBtn = By.xpath("//button[text()='Click Me']");

    private By doubleClickMessage = By.id("doubleClickMessage");
    private By rightClickMessage = By.id("rightClickMessage");
    private By dynamicClickMessage = By.id("dynamicClickMessage");

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDoubleClick() {
    	WebElement doubleBtn = driver.findElement(doubleClickBtn);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doubleBtn);
        new Actions(driver).doubleClick(driver.findElement(doubleClickBtn)).perform();
    }

    public void clickRightClick() {
    	WebElement rightBtn = driver.findElement(rightClickBtn);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rightBtn);
        new Actions(driver).contextClick(driver.findElement(rightClickBtn)).perform();
    }

    public void clickDynamic() {
    	WebElement dynamicBtn = driver.findElement(dynamicClickBtn);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dynamicBtn);
        driver.findElement(dynamicClickBtn).click();
    }

    public String getDoubleClickMessage() {
        return driver.findElement(doubleClickMessage).getText();
    }

    public String getRightClickMessage() {
        return driver.findElement(rightClickMessage).getText();
    }

    public String getDynamicClickMessage() {
        return driver.findElement(dynamicClickMessage).getText();
    }
}
