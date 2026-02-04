package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectablePage {

    private WebDriver driver;
    private JavascriptExecutor js;

    public SelectablePage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    private By listTab = By.id("demo-tab-list");

    public void clickListTab() {
        WebElement tab = driver.findElement(listTab);
        js.executeScript("arguments[0].scrollIntoView(true);", tab);
        tab.click();
    }

    public WebElement getListItem(int index) {
        return driver.findElement(By.cssSelector("#verticalListContainer > li:nth-child(" + index + ")"));
    }

    public void clickItem(int index) {
        getListItem(index).click();
    }

    public boolean isActive(int index) {
        return getListItem(index).getAttribute("class").contains("active");
    }
}