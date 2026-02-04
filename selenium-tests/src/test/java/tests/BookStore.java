package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookStore {
	
	private WebDriver driver;
	private Path downloadDir;

	 @BeforeEach
	    public void setUp() throws IOException {
	        // Opcje przeglądarki — wyłączenie powiadomień i popupów
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--disable-notifications");
	        options.addArguments("--disable-popup-blocking");

	        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver\\chromedriver.exe");
	        
	     // 1. Tworzymy folder do pobrań
	        /*downloadDir = Paths.get("C:\\Users\\Dell\\Downloads\\selenium_test");
	        Files.createDirectories(downloadDir);

	        // 2. Konfiguracja ChromeDrivera
	        Map<String, Object> prefs = new HashMap<>();
	        prefs.put("download.default_directory", downloadDir.toAbsolutePath().toString());
	        prefs.put("download.prompt_for_download", false);
	        prefs.put("safebrowsing.enabled", true);

	        ChromeOptions options1 = new ChromeOptions();
	        options1.setExperimentalOption("prefs", prefs);*/
	        
	     // Blokada reklam przez filtr domen
	        HashMap<String, Object> prefs = new HashMap<>();
	        prefs.put("profile.managed_default_content_settings.ads", 2);
	        prefs.put("profile.managed_default_content_settings.images", 2);
	        options.setExperimentalOption("prefs", prefs);
	        
	        driver = new ChromeDriver(options);
	        
	    }

	    @AfterEach
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit(); // zamyka przeglądarkę po każdym teście
	        }
	    }
	    
	    
	    //@Test
	     public void testLogin()  {
	    	driver.get("https://demoqa.com/login");
	    	
	    	WebElement user = driver.findElement(By.id("userName"));
	    	WebElement password = driver.findElement(By.id("password"));
	    	
	    	WebElement login = driver.findElement(By.id("login"));
	    	
	    	user.sendKeys("pawel");
	    	password.sendKeys("pawel1");
	    	
	    	login.click();
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
               	
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
	    	
	    	WebElement text = driver.findElement(By.id("name"));
	    	
	    	assertTrue(text.getText().equals("Invalid username or password!"));
	    	
	    }
	    
	    @Test
	     public void testBooks()  {
	    	driver.get("https://demoqa.com/books");
	    	WebElement search = driver.findElement(By.id("searchBox"));
	    	
	    	WebElement bookTable = driver.findElement(By.className("rt-table"));
	    	
	    	assertTrue(bookTable.isDisplayed(), "Lista książek nie jest widoczna");
	    	
	    	search.sendKeys("Git");
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/span/a")));
	    	
	    	WebElement book = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/span/a"));
	    	
	    	assertTrue(book.getText().equals("Git Pocket Guide"));
	    	
	    }
	    	
	    
	    	
	         
	    
	    

}
