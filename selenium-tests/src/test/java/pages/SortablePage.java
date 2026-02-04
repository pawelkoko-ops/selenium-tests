package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

public class SortablePage {

    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;

    public SortablePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    private By listTab = By.id("demo-tab-list");
    private By gridTab = By.id("demo-tab-grid");

    private By listItems = By.cssSelector("#demo-tabpane-list .list-group-item");
    private By gridItems = By.cssSelector("#demo-tabpane-grid .list-group-item");

    public void clickListTab() {
        WebElement tab = driver.findElement(listTab);
        js.executeScript("arguments[0].scrollIntoView(true);", tab);
        tab.click();
    }

    public void clickGridTab() {
        driver.findElement(gridTab).click();
    }

    public List<String> getListOrder() {
        return driver.findElements(listItems).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getGridOrder() {
        return driver.findElements(gridItems).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void dragListItem(String sourceText, String targetText) {
        WebElement source = driver.findElement(By.xpath("//div[@id='demo-tabpane-list']//div[text()='" + sourceText + "']"));
        WebElement target = driver.findElement(By.xpath("//div[@id='demo-tabpane-list']//div[text()='" + targetText + "']"));
        js.executeScript("arguments[0].scrollIntoView(true);", target);
        actions.clickAndHold(source).moveToElement(target).moveByOffset(10, 10).release().perform();
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
    }

    public void dragGridItem(String sourceText, String targetText) {
        WebElement source = driver.findElement(By.xpath("//div[@id='demo-tabpane-grid']//div[text()='" + sourceText + "']"));
        WebElement target = driver.findElement(By.xpath("//div[@id='demo-tabpane-grid']//div[text()='" + targetText + "']"));
        js.executeScript("arguments[0].scrollIntoView(true);", target);
        actions.clickAndHold(source).moveToElement(target).moveByOffset(10, 10).release().perform();
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
    }
}