package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class LinksPage {

    private WebDriver driver;

    private By homeLink = By.id("simpleLink");
    private By createdLink = By.id("created");
    private By responseMessage = By.id("linkResponse");

    public LinksPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickHomeLink() {
    	WebElement home = driver.findElement(homeLink);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", home);
        driver.findElement(homeLink).click();
    }

    public String switchToNewTabAndGetUrl() {
        String mainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String w : windows) {
            if (!w.equals(mainWindow)) {
                driver.switchTo().window(w);
                String url = driver.getCurrentUrl();
                driver.close();
                driver.switchTo().window(mainWindow);
                return url;
            }
        }
        return null;
    }

    public void clickCreatedLink() {
        driver.findElement(createdLink).click();
    }

    public String getResponseMessage() {
        return driver.findElement(responseMessage).getText();
    }
}
