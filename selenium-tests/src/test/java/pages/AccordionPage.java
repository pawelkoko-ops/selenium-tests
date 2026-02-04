package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccordionPage {

    private WebDriver driver;
    private JavascriptExecutor js;

    public AccordionPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    private By sectionHeading(String id) {
        return By.id(id);
    }

    private By sectionContent(String id) {
        return By.id(id);
    }

    public void scrollToSection(String sectionId) {
        WebElement el = driver.findElement(sectionHeading(sectionId));
        js.executeScript("arguments[0].scrollIntoView(true);", el);
    }

    public void clickSection(String sectionId) {
        driver.findElement(sectionHeading(sectionId)).click();
    }

    public String getSectionContent(String sectionContentId) {
        return driver.findElement(sectionContent(sectionContentId)).getText();
    }
}