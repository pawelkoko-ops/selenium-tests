package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutoCompletePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By multiInput = By.id("autoCompleteMultipleInput");
    private By singleInput = By.id("autoCompleteSingleInput");
    private By multiOptions = By.cssSelector(".auto-complete__menu");
    private By singleValue = By.cssSelector("#autoCompleteSingleContainer div[class$='-singleValue']");

    public AutoCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public void selectMultiple(String value) {
        WebElement input = driver.findElement(multiInput);
        input.sendKeys(value);
        wait.until(ExpectedConditions.visibilityOfElementLocated(multiOptions));
        WebElement option = driver.findElement(By.xpath("//div[contains(@class,'auto-complete__option') and text()='" + value + "']"));
        option.click();
    }

    public boolean isMultiSelected(String value) {
        WebElement selected = driver.findElement(By.xpath("//*[@id=\"autoCompleteMultipleContainer\"]//div[text()='" + value + "']"));
        return selected.isDisplayed();
    }

    public void selectSingle(String value) {
        WebElement input = driver.findElement(singleInput);
        input.sendKeys(value);
        wait.until(ExpectedConditions.visibilityOfElementLocated(multiOptions));
        WebElement option = driver.findElement(By.xpath("//div[contains(@class,'auto-complete__option') and text()='" + value + "']"));
        js.executeScript("arguments[0].scrollIntoView(true);", option);
        option.click();
    }

    public String getSingleSelected() {
        WebElement selected = wait.until(ExpectedConditions.visibilityOfElementLocated(singleValue));
        return selected.getText().trim();
    }
}
