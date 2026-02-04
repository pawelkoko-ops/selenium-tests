package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioButtonsPage {

    private WebDriver driver;

    private By yesRadio = By.xpath("//label[@for='yesRadio']");
    private By impressiveRadio = By.xpath("//label[@for='impressiveRadio']");
    private By noRadio = By.id("noRadio");
    private By resultText = By.className("text-success");

    public RadioButtonsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectYes() {
    	WebElement yesBtn = driver.findElement(yesRadio);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yesBtn);
        driver.findElement(yesRadio).click();
    }

    public void selectImpressive() {
        driver.findElement(impressiveRadio).click();
    }

    public boolean isNoEnabled() {
        return driver.findElement(noRadio).isEnabled();
    }

    public String getResultText() {
        return driver.findElement(resultText).getText();
    }
}
