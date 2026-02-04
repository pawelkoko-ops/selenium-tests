package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class DraggablePage {

    private WebDriver driver;
    private Actions actions;

    public DraggablePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    private By dragBox = By.id("dragBox");

    public WebElement getDragBox() {
        return driver.findElement(dragBox);
    }

    public Point getPosition() {
        return getDragBox().getLocation();
    }

    public void moveByOffset(int x, int y) {
        actions.clickAndHold(getDragBox()).moveByOffset(x, y).release().perform();
    }
}