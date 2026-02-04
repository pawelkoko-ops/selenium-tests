package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PracticeFormPage extends BasePage {

    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By mobile = By.id("userNumber");
    private By maleRadioLabel = By.xpath("//label[@for='gender-radio-1']");
    private By dateOfBirthInput = By.id("dateOfBirthInput");
    private By yearSelect = By.className("react-datepicker__year-select");
    private By monthSelect = By.className("react-datepicker__month-select");
    private By subjectInput = By.id("subjectsInput");
    private By uploadPicture = By.id("uploadPicture");
    private By state = By.id("state");
    private By city = By.id("city");
    private By submit = By.id("submit");
    private By modal = By.className("modal-content");

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    public void fillPersonalData(String fn, String ln, String em, String phone) {
        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(email).sendKeys(em);
        driver.findElement(mobile).sendKeys(phone);
    }

    public void selectGenderMale() {
        driver.findElement(maleRadioLabel).click();
    }

    public void setBirthDate(String year, String month) {
        WebElement dob = driver.findElement(dateOfBirthInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dob);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dob);
        dob.click();
        new Select(driver.findElement(yearSelect)).selectByValue(year);
        new Select(driver.findElement(monthSelect)).selectByVisibleText(month);

        driver.findElement(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--030']")).click();
    }

    public void selectSubject(String subject) {
        WebElement s = driver.findElement(subjectInput);
        s.sendKeys(subject);
        s.sendKeys(Keys.ENTER);
    }

    public void uploadPicture(String path) {
        driver.findElement(uploadPicture).sendKeys(path);
    }

    public void selectState(String s) {
        driver.findElement(state).click();
        WebElement input = driver.findElement(By.id("react-select-3-input"));
        input.sendKeys(s);
        input.sendKeys(Keys.ENTER);
    }

    public void selectCity(String c) {
        driver.findElement(city).click();
        WebElement input = driver.findElement(By.id("react-select-4-input"));
        input.sendKeys(c);
        input.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
    	WebElement submitBtn = driver.findElement(submit);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        driver.findElement(submit).click();
    }

    public boolean isModalVisible() {
        return driver.findElement(modal).isDisplayed();
    }
}
