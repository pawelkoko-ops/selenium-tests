package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class DroppablePage {

    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;

    public DroppablePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    // -------------------------
    // Tabs
    // -------------------------
    private By simpleTab = By.id("droppableExample-tab-simple");
    private By acceptTab = By.id("droppableExample-tab-accept");
    private By preventTab = By.id("droppableExample-tab-preventPropogation");
    private By revertTab = By.id("droppableExample-tab-revertable");

    public void clickTab(String tab) {
        switch (tab.toLowerCase()) {
            case "simple": driver.findElement(simpleTab).click(); break;
            case "accept": driver.findElement(acceptTab).click(); break;
            case "prevent": driver.findElement(preventTab).click(); break;
            case "revert": driver.findElement(revertTab).click(); break;
        }
    }

    // -------------------------
    // Simple droppable
    // -------------------------
    public WebElement getDraggable() { return driver.findElement(By.id("draggable")); }
    public WebElement getDroppable() { return driver.findElement(By.id("droppable")); }

    public void dragSimple() {
        actions.dragAndDrop(getDraggable(), getDroppable()).perform();
    }

    public String getDroppableText() {
        return getDroppable().findElement(By.tagName("p")).getText();
    }

    // -------------------------
    // Accept droppable
    // -------------------------
    public WebElement getAcceptable() { return driver.findElement(By.id("acceptable")); }
    public WebElement getNotAcceptable() { return driver.findElement(By.id("notAcceptable")); }
    public WebElement getAcceptDrop() { return driver.findElement(By.cssSelector("#acceptDropContainer #droppable")); }

    public void dragToAccept(WebElement source) {
        actions.dragAndDrop(source, getAcceptDrop()).perform();
    }

    public String getAcceptDropText() {
        return getAcceptDrop().findElement(By.tagName("p")).getText();
    }

    // -------------------------
    // Prevent Propagation
    // -------------------------
    public WebElement getDragPP() { return driver.findElement(By.id("dragBox")); }
    public WebElement getOuterPP() { return driver.findElement(By.id("notGreedyDropBox")); }
    public WebElement getInnerPP() { return driver.findElement(By.id("notGreedyInnerDropBox")); }

    public void dragPPtoInner() {
        actions.dragAndDrop(getDragPP(), getInnerPP()).perform();
    }

    // -------------------------
    // Revert draggable
    // -------------------------
    public WebElement getRevertable() { return driver.findElement(By.id("revertable")); }
    public WebElement getNotRevertable() { return driver.findElement(By.id("notRevertable")); }
    public WebElement getDropRevert() { return driver.findElement(By.cssSelector("#revertableDropContainer #droppable")); }

    public Point dragNotRevertable() throws InterruptedException {
        js.executeScript("arguments[0].scrollIntoView(true);", getNotRevertable());
        Point before = getNotRevertable().getLocation();
        actions.dragAndDrop(getNotRevertable(), getDropRevert()).perform();
        Thread.sleep(500);
        return getNotRevertable().getLocation();
    }

    public Point dragRevertable() throws InterruptedException {
        js.executeScript("arguments[0].scrollIntoView(true);", getRevertable());
        Point before = getRevertable().getLocation();
        actions.dragAndDrop(getRevertable(), getDropRevert()).perform();
        Thread.sleep(1000); // poczekaj animacje revert
        return getRevertable().getLocation();
    }
}
