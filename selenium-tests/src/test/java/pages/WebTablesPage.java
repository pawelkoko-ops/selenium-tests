package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTablesPage {

    private WebDriver driver;

    private By addButton = By.id("addNewRecordButton");
    private By firstNameInput = By.id("firstName");
    private By lastNameInput = By.id("lastName");
    private By emailInput = By.id("userEmail");
    private By ageInput = By.id("age");
    private By salaryInput = By.id("salary");
    private By departmentInput = By.id("department");
    private By submitBtn = By.id("submit");

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAdd() {
    	WebElement addBtn = driver.findElement(By.id("addNewRecordButton"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", addBtn);
        //driver.findElement(addButton).click();
    }

    public void fillForm(String firstName, String lastName, String email, String age, String salary, String department) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(ageInput).sendKeys(age);
        driver.findElement(salaryInput).sendKeys(salary);
        driver.findElement(departmentInput).sendKeys(department);
    }

    public void submit() {
    	WebElement submit = driver.findElement(By.id("submit"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", submit);
        //driver.findElement(submitBtn).click();
    }

    public WebElement getNewRowByFirstName(String firstName) {
        return driver.findElement(By.xpath("//div[contains(text(),'" + firstName + "')]"));
    }
}
