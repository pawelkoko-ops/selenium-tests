package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModalDialogsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By smallButton = By.id("showSmallModal");
    private By largeButton = By.id("showLargeModal");
    private By modalBody = By.className("modal-body");

    public ModalDialogsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void openSmallModal() {
    	WebElement small = driver.findElement(smallButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", small);
        driver.findElement(smallButton).click();
    }

    public String getSmallModalText() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalBody));
        return modal.getText();
    }

    public void openLargeModal() {
        driver.findElement(largeButton).click();
    }

    public String getLargeModalText() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalBody));
        return modal.getText();
    }
}