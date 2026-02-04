package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ResizablePage {

    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;

    private By restrictedBox = By.id("resizableBoxWithRestriction");

    public ResizablePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public WebElement getRestrictedBox() {
        return driver.findElement(restrictedBox);
    }

    public WebElement getResizeHandle() {
        return getRestrictedBox().findElement(By.cssSelector(".react-resizable-handle-se"));
    }

    public Dimension getBoxSize() {
        return getRestrictedBox().getSize();
    }

    public void scrollToBox() {
        js.executeScript("arguments[0].scrollIntoView(true);", getRestrictedBox());
    }

    public void resizeBox(int xOffset, int yOffset) {
        actions.dragAndDropBy(getResizeHandle(), xOffset, yOffset).perform();
    }
}
