package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SelectMenuPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By oldSelect = By.id("oldSelectMenu");
    private By selectValueInput = By.id("react-select-2-input");
    private By selectOneInput = By.id("react-select-3-input");
    private By multiSelectInput = By.id("react-select-4-input");
    private By multiSelectedOptions = By.cssSelector(".css-12jo7m5");

    public SelectMenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void selectOldStyle(String option) {
        Select select = new Select(driver.findElement(oldSelect));
        select.selectByVisibleText(option);
    }

    public String getOldSelectValue() {
        Select select = new Select(driver.findElement(oldSelect));
        return select.getFirstSelectedOption().getText();
    }

    public void selectReactValue(String value) {
        WebElement input = driver.findElement(selectValueInput);
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    public String getSelectedReactValue() {
        WebElement selected = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#withOptGroup .css-1uccc91-singleValue")));
        return selected.getText();
    }

    public void selectReactOne(String value) {
        WebElement input = driver.findElement(selectOneInput);
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    public String getSelectedReactOne() {
        WebElement selected = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#selectOne div[class$='-singleValue']")));
        return selected.getText();
    }

    public void selectMulti(String... values) {
        WebElement input = driver.findElement(multiSelectInput);
        for (String value : values) {
            input.sendKeys(value);
            input.sendKeys(Keys.ENTER);
        }
    }

    public List<String> getMultiSelected() {
        List<WebElement> selectedOptions = driver.findElements(multiSelectedOptions);
        return selectedOptions.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // Standardowy <select> wielokrotny
    public void selectMultipleOld(WebElement select, String... options) {
        Select sel = new Select(select);
        for (String option : options) sel.selectByVisibleText(option);
    }

    public List<String> getMultipleOldSelected(WebElement select) {
        Select sel = new Select(select);
        return sel.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
