package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FakeStoreTest {
	
	private static WebDriver driver;
	private Path downloadDir;

	 @BeforeEach
	    public void setUp() throws IOException {
	        // Opcje przeglądarki — wyłączenie powiadomień i popupów
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--disable-notifications");
	        options.addArguments("--disable-popup-blocking");

	        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver\\chromedriver.exe");
	        
	     // 1. Tworzymy folder do pobrań
	        downloadDir = Paths.get("C:\\Users\\Dell\\Downloads\\selenium_test");
	        Files.createDirectories(downloadDir);

	        // 2. Konfiguracja ChromeDrivera
	        Map<String, Object> prefs = new HashMap<>();
	        prefs.put("download.default_directory", downloadDir.toAbsolutePath().toString());
	        prefs.put("download.prompt_for_download", false);
	        prefs.put("safebrowsing.enabled", true);

	        ChromeOptions options1 = new ChromeOptions();
	        options1.setExperimentalOption("prefs", prefs);
	        
	        driver = new ChromeDriver(options1);
	        
	    }

	    @AfterEach
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit(); // zamyka przeglądarkę po każdym teście
	        }
	    }
	    
	    //@Test
	     public void testLogin() {
	    	 
	    	 driver.get("https://fakestore.testelka.pl/moje-konto/");
	         
	         WebElement user = driver.findElement(By.id("username"));
	         WebElement password = driver.findElement(By.id("password"));
	         WebElement login = driver.findElement(By.name("login"));
	         
	         user.sendKeys("pawel.koko");
	         password.sendKeys("Makaron43");
	         
	         login.click();
	         
	         WebElement title = driver.findElement(By.className("entry-title"));
	         
	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             wait.until(ExpectedConditions.visibilityOf(title));
             
             assertTrue(title.isDisplayed());
	         
	    	 
	     }
	     
	     //@Test
	     public void testSearch() {
	    	 
	    	 driver.get("https://fakestore.testelka.pl/moje-konto/");
	         
	         WebElement search = driver.findElement(By.className("search-field"));
	         
	         
	         search.sendKeys("wspinaczka");
	         search.sendKeys(Keys.ENTER);
	         
	         WebElement title = driver.findElement(By.xpath("//*[@id=\"main\"]/header/h1"));
	         
	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             wait.until(ExpectedConditions.visibilityOf(title));
             
             assertTrue(title.isDisplayed());
             assertTrue(driver.getPageSource().contains("Wyświetlanie wszystkich wyników: 2"));
	         
	    	 
	     }
	     
	     @Test
	     public void testSort() {
	    	 
	    	 driver.get("https://fakestore.testelka.pl/product-category/windsurfing/");
	    	 
	    	 WebElement sort = driver.findElement(By.name("orderby"));
	    	 
	    	 Select select = new Select(sort);
	    	 select.selectByIndex(4);
	    	 
	    	/* try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	    	 
	    	 //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	 //wait.until(ExpectedConditions.textToBePresentInElement(sort, "Sortuj wg ceny: od najniższej"));
	    	 WebElement price1 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/main/ul/li[1]/a[1]/span/span"));
	    	 WebElement price2 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/main/ul/li[2]/a[1]/span/span"));
	    	 WebElement price3 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/main/ul/li[3]/a[1]/span/span"));
	    	 WebElement price4 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/main/ul/li[4]/a[1]/span/span"));
	    	 WebElement price5 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/main/ul/li[5]/a[1]/span/span"));
	    	 WebElement price6 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/main/ul/li[6]/a[1]/span/span"));
	    	
	    	 String price[]= {price1.getText(), price2.getText(), price3.getText(), price4.getText(), price5.getText(), price6.getText()};
	    	 
	    	 String priceSorted[] = Arrays.copyOf(price, price.length);
	    	 
	    	 Arrays.sort(priceSorted);
	    	 
	    	 boolean equal = Arrays.equals(price, priceSorted);
	    	 System.out.println(Arrays.toString(price));
	    	 assertTrue(equal);
	    	 
	    	 //System.out.println(price1.getText());
	    	 
	     }

}
