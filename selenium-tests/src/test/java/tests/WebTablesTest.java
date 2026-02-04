package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.RadioButtonsPage;
import pages.WebTablesPage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class WebTablesTest extends BaseTest {
@Test
public void testWebTables() throws InterruptedException {
    driver.get("https://demoqa.com/webtables");

    WebTablesPage tablePage = new WebTablesPage(driver);

    tablePage.clickAdd();
    tablePage.fillForm("Paweł", "Koko", "pawel.koko@gmail.com", "41", "10000", "IT");
    tablePage.submit();
    
    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	//WebElement progressBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[1]")));
	
    //Thread.sleep(3000);
    By cell = By.xpath("//*[@id='app']/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[1]");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(d -> {
        try {
            WebElement element = d.findElement(cell);
            String text = element.getText().trim();
            return text.equals("Paweł");
        } catch (StaleElementReferenceException e) {
            return false;
        }
    });
    
    assertTrue(tablePage.getNewRowByFirstName("Paweł").isDisplayed());
}
}
