package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePickerPage {

    private WebDriver driver;

    private By dateInput = By.id("datePickerMonthYearInput");
    private By dateAndTime = By.id("dateAndTimePickerInput");
    private By monthSelect = By.className("react-datepicker__month-select");
    private By yearSelect = By.className("react-datepicker__year-select");
    private By monthSelect2 = By.className("react-datepicker__month-read-view--selected-month");
    private By yearSelect2 = By.className("react-datepicker__year-read-view--selected-year");
    private By monthDropdown = By.cssSelector(".react-datepicker__month-read-view");
    private By monthOptions  = By.cssSelector(".react-datepicker__month-option");
    private By yearDropdown = By.cssSelector(".react-datepicker__year-read-view");
    private By yearOptions  = By.cssSelector(".react-datepicker__year-option");
    //private By day30 = By.cssSelector(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)");
    

    public DatePickerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openDatePicker() {
    	WebElement el = driver.findElement(dateInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        driver.findElement(dateInput).click();
    }

    public void selectMonth(String month) {
        new Select(driver.findElement(monthSelect)).selectByVisibleText(month);
    }

    public void selectYear(String year) {
        new Select(driver.findElement(yearSelect)).selectByVisibleText(year);
    }

    public void selectDay(String day) {
        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']")).click();
    }
    
    public void openDateAndTimePicker() {
        driver.findElement(dateAndTime).click();
    }
    
    public void selectMonth2(String month) {
        //new Select(driver.findElement(monthSelect2)).selectByVisibleText(month);
        
        driver.findElement(monthDropdown).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(monthOptions));

        for (WebElement option : driver.findElements(monthOptions)) {
            if (option.getText().equalsIgnoreCase(month)) {
                option.click();
                break;
            }
        }
        
    }

    public void selectYear2(String year) throws InterruptedException {
        //new Select(driver.findElement(yearSelect2)).selectByVisibleText(year);
    	Thread.sleep(3000);
        driver.findElement(yearDropdown).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(yearOptions));

        for (WebElement option : driver.findElements(yearOptions)) {
            if (option.getText().equals(year)) {
                option.click();
                break;
            }
        }
    }
    
    public void selectDay2(String day) {
        //driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']")).click();
    	driver.findElement(By.cssSelector(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month")).click();
    }
    
    public String getSelectedDate() {
        return driver.findElement(dateInput).getAttribute("value");
    }
    
    public String getSelectedDateAndTime() {
        return driver.findElement(dateAndTime).getAttribute("value");
    }
    
    
    
    
}