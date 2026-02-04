package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SliderPage {

    private WebDriver driver;

    private By slider = By.cssSelector("input[type='range']");
    private By sliderValue = By.id("sliderValue");

    public SliderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void moveSlider(int xOffset) {
        WebElement sliderEl = driver.findElement(slider);
        new Actions(driver)
                .clickAndHold(sliderEl)
                .moveByOffset(xOffset, 0)
                .release()
                .perform();
    }

    public String getSliderValue() {
        return driver.findElement(sliderValue).getAttribute("value");
    }
}