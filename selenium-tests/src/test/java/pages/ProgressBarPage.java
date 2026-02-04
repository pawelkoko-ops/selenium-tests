package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProgressBarPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By bar = By.cssSelector("#progressBar .progress-bar");
    private By button = By.id("startStopButton");

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.js = (JavascriptExecutor) driver;
    }

    private int value() {
        return Integer.parseInt(driver.findElement(bar)
                .getAttribute("aria-valuenow"));
    }

    private WebElement btn() {
        return driver.findElement(button);
    }

    public void start() {
        js.executeScript("arguments[0].click();", btn());
    }

    public void waitUntilFinished() {
        wait.until(d -> {
            try {
                return value() >= 100;
            } catch (Exception e) {
                return false;
            }
        });
    }

    public void waitUntilButtonIsReset() {
        wait.until(d -> {
            try {
                return btn().getText().equalsIgnoreCase("Reset");
            } catch (Exception e) {
                return false;
            }
        });
    }

    public void reset() {
        js.executeScript("arguments[0].click();", btn());
    }

    public void waitUntilZero() {
        wait.until(d -> {
            try {
                return value() == 0;
            } catch (Exception e) {
                return false;
            }
        });
    }
}
