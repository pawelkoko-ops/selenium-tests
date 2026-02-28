package tests;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.FileHandler;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoQaTest {
	
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
	     public void testPracticeForm() throws InterruptedException {	    	
	    
	         // 1. Otwórz stronę z Practice Form
	         driver.get("https://demoqa.com/automation-practice-form");
	         
	         WebElement form = driver.findElement(By.className("practice-form-wrapper"));
	         assertTrue(form.isDisplayed());
	         //2. wypełnij pola
	         WebElement firstName = driver.findElement(By.id("firstName"));
	         WebElement lastName = driver.findElement(By.id("lastName"));
	         WebElement userEmail = driver.findElement(By.id("userEmail"));
	         WebElement userNumber = driver.findElement(By.id("userNumber"));
	         
	         firstName.sendKeys("Paweł");
	         lastName.sendKeys("Koko");
	         userEmail.sendKeys("pawel.koko@gmail.com");
	         userNumber.sendKeys("5068007501");
	         //Thread.sleep(2000);
	         assertTrue(firstName.getAttribute("value").equals("Paweł"));
	         assertTrue(lastName.getAttribute("value").equals("Koko"));
	         assertTrue(userEmail.getAttribute("value").equals("pawel.koko@gmail.com"));
	         assertTrue(userNumber.getAttribute("value").equals("5068007501"));
	         //3. wybierz płeć
	       //*[@id="genterWrapper"]/div[2]/div[1]/label
	      // Znajdź i kliknij etykietę
	         WebElement maleLabel = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
	         maleLabel.click();

	         // Sprawdź, czy radio zostało zaznaczone
	         WebElement maleInput = driver.findElement(By.id("gender-radio-1"));
	         assertTrue(maleInput.isSelected());

	         //4. wybierz date
	         WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateOfBirth);
	         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dateOfBirth);
	         dateOfBirth.click();
	         WebElement year = driver.findElement(By.className("react-datepicker__year-select"));
	         Select yearSelect = new Select(year);
	         yearSelect.selectByValue("1984");
	         WebElement month = driver.findElement(By.className("react-datepicker__month-select"));
	         Select monthSelect = new Select(month);
	         monthSelect.selectByVisibleText("July");
	         WebElement day = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[5]/div[2]"));
	         day.click();
	         assertTrue(dateOfBirth.getAttribute("value").equals("30 Jul 1984"));
	         
	         //5. wybierz subject
	         WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
	         subjectsInput.sendKeys("English");
	         subjectsInput.sendKeys(Keys.ENTER);

	         // Sprawdź, czy "English" pojawiło się w tagu
	         WebElement selectedSubject = driver.findElement(By.xpath("//div[contains(@class,'subjects-auto-complete__multi-value__label')]"));
	         assertTrue(selectedSubject.getText().equals("English"));
	         
	         //6. upload zdj
	         WebElement zdj = driver.findElement(By.id("uploadPicture"));
	         
	         zdj.sendKeys("C:\\Users\\Dell\\Desktop\\avocado\\1.jpg");
	         String uploadedFile = zdj.getAttribute("value");
	         assertTrue(uploadedFile.contains("1.jpg"));
	         
	         //7. miasto i stan
	         WebElement state = driver.findElement(By.id("state"));
	         state.click();

	         // Znajdź input, który pojawia się po kliknięciu (React Select)
	         WebElement stateInput = driver.findElement(By.id("react-select-3-input"));
	         stateInput.sendKeys("NCR");
	         stateInput.sendKeys(Keys.ENTER);
	         WebElement city = driver.findElement(By.id("city"));
	         city.click();

	         WebElement cityInput = driver.findElement(By.id("react-select-4-input"));
	         cityInput.sendKeys("Delhi");
	         cityInput.sendKeys(Keys.ENTER);
	         
	         WebElement selectedState = driver.findElement(By.cssSelector("#state .css-1uccc91-singleValue"));
	         WebElement selectedCity = driver.findElement(By.cssSelector("#city .css-1uccc91-singleValue"));

	         assertTrue(selectedState.getText().equals("NCR"));
	         assertTrue(selectedCity.getText().equals("Delhi"));
	         
	         //8.submit
	         WebElement submitButton = driver.findElement(By.id("submit"));
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
	         Thread.sleep(500); // małe opóźnienie dla bezpieczeństwa
	         submitButton.click();
	         
	         //9. walidacja modal window
	         WebElement modal = driver.findElement(By.className("modal-content"));
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             wait.until(ExpectedConditions.visibilityOf(modal));
             assertTrue(modal.isDisplayed());
	    }
	    
	    //@Test
	     public void testButtons()  {
	    	// 1. Otwórz stronę Buttons
	         driver.get("https://demoqa.com/buttons");
	         
	         //2. identyfikacja przypisków
	         WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));
	         WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));
	         WebElement oneClickButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[3]/button"));
	         
	         //3.klikniecia
	         Actions actions = new Actions(driver);
	         
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", oneClickButton);
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rightClickButton);
	         oneClickButton.click();
	         actions.doubleClick(doubleClickButton).perform();
	         actions.contextClick(rightClickButton).perform();
	         
	         //4. sprawdzenie komunikatów
	         
	         WebElement doubleClickMessage = driver.findElement(By.id("doubleClickMessage"));
	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             wait.until(ExpectedConditions.visibilityOf(doubleClickMessage));
	         assertTrue(doubleClickMessage.isDisplayed());
	         assertTrue(doubleClickMessage.getText().contentEquals("You have done a double click"));
	         
	         WebElement rightClickMessage = driver.findElement(By.id("rightClickMessage"));
	         wait.until(ExpectedConditions.visibilityOf(rightClickMessage));
	         assertTrue(rightClickMessage.isDisplayed());
	         assertTrue(rightClickMessage.getText().contentEquals("You have done a right click"));
	         
	         WebElement dynamicClickMessage = driver.findElement(By.id("dynamicClickMessage"));
	         wait.until(ExpectedConditions.visibilityOf(dynamicClickMessage));
	         assertTrue(dynamicClickMessage.isDisplayed());
	         assertTrue(dynamicClickMessage.getText().contentEquals("You have done a dynamic click"));
	         
	         
	    }
	    
	     //@Test
	     public void testRadioButtons()  {
	    	 driver.get("https://demoqa.com/radio-button");
	    	 
	    	 WebElement yesLabel = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[2]/label"));
	    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yesLabel);
	    	 yesLabel.click();
	    	 
	    	 WebElement yesMessage = driver.findElement(By.className("text-success"));
	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             wait.until(ExpectedConditions.visibilityOf(yesMessage));
	         assertTrue(yesMessage.isDisplayed());
	         assertTrue(yesMessage.getText().contentEquals("Yes"));
	         
	         WebElement impressiveLabel = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/label"));
	    	 impressiveLabel.click();
	         
	         WebElement impressiveMessage = driver.findElement(By.className("text-success"));
	         wait.until(ExpectedConditions.visibilityOf(impressiveMessage));
	         assertTrue(impressiveMessage.isDisplayed());
	         assertTrue(impressiveMessage.getText().contentEquals("Impressive"));
	         
	         
	         WebElement noLabel = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[4]/label"));
	         noLabel.click();
	         
	         WebElement noRadioInput = driver.findElement(By.id("noRadio"));
	         assertFalse(noRadioInput.isEnabled());
	         
	    	 
	    	 
	    	 
	     }
	     
	   /*@Test
	     public void testCheckboxes()  {
		   driver.get("https://demoqa.com/checkbox");
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // 1️⃣ Rozwiń drzewo Home
		    WebElement homeExpand = driver.findElement(By.xpath("//*[@id='tree-node']/ol/li/span/button"));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homeExpand);
		    homeExpand.click();

		    // 2️⃣ Poczekaj aż Desktop się pojawi
		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//span[@class='rct-title' and text()='Desktop']")));

		    // 🔍 Debug: wypisz HTML fragmentu drzewa
		    WebElement tree = driver.findElement(By.cssSelector(".rct-node-parent"));
		    System.out.println("Tree HTML:\n" + tree.getAttribute("innerHTML"));

		    // 3️⃣ Kliknij checkbox obok Desktop
		    WebElement desktopCheckbox = driver.findElement(
		            By.xpath("//label[.//span[text()='Desktop']]//span[contains(@class,'rct-checkbox')]"));
		    desktopCheckbox.click();

		    // 4️⃣ Poczekaj, aż SVG zmieni klasę
		    WebElement svg = wait.until(ExpectedConditions.presenceOfElementLocated(
		            By.xpath("//label[.//span[text()='Desktop']]//span[contains(@class,'rct-checkbox')]/svg")));

		    String svgClass = svg.getAttribute("class");
		    System.out.println("SVG class after click: " + svgClass);

		    assertTrue(svgClass.contains("rct-icon-check"), "Checkbox for Desktop not checked!");

		    // 5️⃣ Sprawdź wynik na dole
		    WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
		    String resultText = result.getText().toLowerCase();
		    System.out.println("Result text: " + resultText);
		    assertTrue(resultText.contains("desktop"), "Result text does not contain 'desktop'");
	    	 
	     } */
	   
	   //@Test
	     public void testWebTables()  {
	    	 driver.get("https://demoqa.com/webtables");
			    
			   WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
			   addButton.click();
			   
			   WebElement firstName = driver.findElement(By.id("firstName"));
		       WebElement lastName = driver.findElement(By.id("lastName"));
		       WebElement userEmail = driver.findElement(By.id("userEmail"));
		       WebElement age = driver.findElement(By.id("age"));
		       WebElement salary = driver.findElement(By.id("salary"));
		       WebElement department = driver.findElement(By.id("department"));
		         
		         firstName.sendKeys("Paweł");
		         lastName.sendKeys("Koko");
		         userEmail.sendKeys("pawel.koko@gmail.com");
		         age.sendKeys("41");
		         salary.sendKeys("10000");
		         department.sendKeys("IT");
		         
		      WebElement submit = driver.findElement(By.id("submit"));
		      ((JavascriptExecutor)driver).executeScript("arguments[0].click();", submit);
		      WebElement newName = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[1]"));
		      assertTrue(newName.getText().contains("Paweł"));
		      
			   
			   
	     }
	     
	   //@Test
	     public void testLinks()  {
	    	 driver.get("https://demoqa.com/links");
	    	 String mainWindow = driver.getWindowHandle();
	    	 
	    	 WebElement link = driver.findElement(By.id("simpleLink")); // lub nowy przycisk
	    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
	    	 link.click();
	    	 
	    	 for (String handle : driver.getWindowHandles()) {
	    		    if (!handle.equals(mainWindow)) {
	    		        driver.switchTo().window(handle);
	    		        break;
	    		    }
	    		}
	    	 
	    	 assertEquals("https://demoqa.com/", driver.getCurrentUrl());
	    	 
	    	 driver.close(); // zamyka aktualne okno
	    	 driver.switchTo().window(mainWindow); // wraca do głównego
	    	 
	     }
	     
	   //@Test
	     public void testDatePicker()  {
	    	 driver.get("https://demoqa.com/date-picker");
	    	 WebElement dateInput = driver.findElement(By.id("datePickerMonthYearInput"));
	    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateInput);
	    	    dateInput.click();
	    	    dateInput.sendKeys(Keys.CONTROL + "a"); 
	    	    dateInput.sendKeys("11/15/2025"); // MM/dd/yyyy
	    	    dateInput.sendKeys(Keys.ENTER);
	    	    
	    	    String selectedDate = dateInput.getAttribute("value");
	    	    System.out.println("Wartość w inpucie: " + selectedDate);
	    	    assertEquals("Niepoprawna wartość daty w input", "11/15/2025", selectedDate);

	    	    // ---- data i godzina ----
	    	    WebElement dateTimeInput = driver.findElement(By.id("dateAndTimePickerInput"));
	    	    dateTimeInput.click();
	    	    dateTimeInput.sendKeys(Keys.CONTROL + "a"); // zaznacz wszystko
	    	    dateTimeInput.sendKeys("November 15, 2025 3:30 PM");
	    	    dateTimeInput.sendKeys(Keys.ENTER);

	    	    String selectedDateTime = dateTimeInput.getAttribute("value");
	    	    assertTrue(selectedDateTime.contains("November 15, 2025"), "Niepoprawna wartość daty i czasu");
	    	    
	    	    
	     }
	     
	     
	     //@Test
	     public void testSlider() {
	    	    driver.get("https://demoqa.com/slider");

	    	    WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
	    	    WebElement sliderValue = driver.findElement(By.id("sliderValue"));

	    	    // Odczytaj wartość początkową
	    	    String startValue = sliderValue.getAttribute("value");
	    	    System.out.println("Początkowa wartość: " + startValue);

	    	    // 🔹 Sposób 1: przesuwanie klawiszami (najbezpieczniejszy)
	    	    slider.sendKeys(Keys.ARROW_RIGHT);
	    	    slider.sendKeys(Keys.ARROW_RIGHT);
	    	    slider.sendKeys(Keys.ARROW_RIGHT);

	    	    String endValue = sliderValue.getAttribute("value");
	    	    System.out.println("Końcowa wartość: " + endValue);

	    	    assertNotEquals(startValue, endValue, "Wartość suwaka nie zmieniła się!");
	    	}
	     
	     //@Test
	     public void testProgressBar() throws InterruptedException {
	    	 driver.get("https://demoqa.com/progress-bar");

	         // ensure button in viewport
	         ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");

	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			 // wait until start button present & clickable
	         WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("startStopButton")));
	         WebElement progressBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#progressBar .progress-bar")));

	         // click Start
	         startButton.click();

	         // Poll progress value every 500ms, log it, stop when >= 100 or timeout
	         final long maxWaitMs = Duration.ofSeconds(40).toMillis(); // adjust if CI is slow
	         final long pollIntervalMs = 500;
	         long endTime = System.currentTimeMillis() + maxWaitMs;
	         int lastValue = -1;
	         boolean reached100 = false;

	         System.out.println("=== Progress logging start ===");
	         while (System.currentTimeMillis() < endTime) {
	             try {
	                 String attr = progressBar.getAttribute("aria-valuenow");
	                 if (attr == null) attr = progressBar.getAttribute("value"); // fallback
	                 if (attr != null) {
	                     try {
	                         lastValue = Integer.parseInt(attr.trim());
	                     } catch (NumberFormatException nfe) {
	                         // ignore parse error, keep lastValue
	                     }
	                 }
	                 String ts = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
	                 System.out.printf("%s - progress aria-valuenow = %s%n", ts, attr);

	                 if (lastValue >= 100) {
	                     reached100 = true;
	                     break;
	                 }
	             } catch (StaleElementReferenceException sere) {
	                 // progressBar reference might become stale; refresh reference
	                 try {
	                     progressBar = driver.findElement(By.cssSelector("#progressBar .progress-bar"));
	                 } catch (Exception e) {
	                     // ignore, will retry
	                 }
	             } catch (Exception ex) {
	                 // ignore transient issues and retry polling
	             }
	             Thread.sleep(pollIntervalMs);
	         }
	         System.out.println("=== Progress logging end ===");

	         // If we didn't reach 100 -> diagnostics + fail
	         if (!reached100) {
	             System.out.println(">>> DID NOT reach 100% within timeout. Last observed value = " + lastValue);
	             saveDiagnostics("progressbar_not_100");
	             fail("Progress bar did not reach 100% within timeout. Last observed value = " + lastValue);
	             return; // unreachable but explicit
	         }

	         // ensure the old startButton reference is stale (it was removed and will be re-rendered)
	         try {
	             wait.until(ExpectedConditions.stalenessOf(startButton));
	         } catch (Exception e) {
	             // staleness may not occur in some timings; continue to polling for new button
	         }

	         // Poll JS until the new element exists and is visible (longer timeout, tolerant)
	         WebElement resetButton = null;
	         long pollEnd = System.currentTimeMillis() + Duration.ofSeconds(30).toMillis();
	         while (System.currentTimeMillis() < pollEnd) {
	             try {
	                 Object exists = ((JavascriptExecutor) driver).executeScript(
	                         "return document.getElementById('startStopButton') != null ? document.getElementById('startStopButton').innerText : null;"
	                 );
	                 if (exists != null) {
	                     // now try to get element and ensure it's displayed
	                     try {
	                         resetButton = driver.findElement(By.id("startStopButton"));
	                         if (resetButton.isDisplayed()) break;
	                     } catch (NoSuchElementException | StaleElementReferenceException ignored) { }
	                 }
	             } catch (Exception ignored) { }
	             Thread.sleep(300);
	         }

	         if (resetButton == null) {
	             System.out.println(">>> Reset button did not appear after progress reached 100%. Last observed progress = " + lastValue);
	             saveDiagnostics("reset_button_missing_after_100");
	             fail("Nie znaleziono przycisku Reset w DOM po zakończeniu progress baru. Ostatnia wartość: " + lastValue);
	             return;
	         }

	         // Wait until visible (extra safety)
	         wait.until(ExpectedConditions.visibilityOf(resetButton));
	         String btnText = resetButton.getText();
	         System.out.println("Button text after render: '" + btnText + "'");
	         assertEquals("Reset", btnText, "Przycisk powinien mieć tekst 'Reset'");

	         // Click Reset (JS click to avoid interactivity issues)
	         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resetButton);

	         // Wait until progress bar is back to 0
	         long zeroTimeoutMs = Duration.ofSeconds(10).toMillis();
	         long zeroEnd = System.currentTimeMillis() + zeroTimeoutMs;
	         boolean resetToZero = false;
	         while (System.currentTimeMillis() < zeroEnd) {
	             try {
	                 String val = progressBar.getAttribute("aria-valuenow");
	                 if (val != null && val.trim().equals("0")) {
	                     resetToZero = true;
	                     break;
	                 }
	             } catch (StaleElementReferenceException sere) {
	                 // refresh reference and retry
	                 progressBar = driver.findElement(By.cssSelector("#progressBar .progress-bar"));
	             }
	             Thread.sleep(250);
	         }

	         if (!resetToZero) {
	             System.out.println(">>> Progress bar did not reset to 0 after clicking Reset. Last observed value = " + progressBar.getAttribute("aria-valuenow"));
	             saveDiagnostics("progress_not_reset_to_0");
	             fail("Pasek postępu nie zresetował się do 0% po kliknięciu Reset");
	         }

	         // success
	         System.out.println("✅ Progress bar reached 100% and reset to 0% successfully.");
	     }
	     
	     
	     private void saveDiagnostics(String prefix) {
	         try {
	             // page source snippet
	             String pageSource = driver.getPageSource();
	             String snippet = pageSource.length() > 4000 ? pageSource.substring(0, 4000) : pageSource;
	             System.out.println("=== PAGE SOURCE SNIPPET START ===");
	             System.out.println(snippet);
	             System.out.println("=== PAGE SOURCE SNIPPET END ===");

	             // screenshot
	             File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	             String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	             String filename = prefix + "_" + timestamp + ".png";
	             File dest = new File(System.getProperty("user.dir"), filename);
	             //FileHandler.copy(screenshot, dest);
	             System.out.println("Saved screenshot to: " + dest.getAbsolutePath());
	         } catch (Exception e) {
	             System.out.println("Failed to save diagnostics: " + e.getMessage());
	         }
	     }
	     
	     
	     
	     //@Test
	     public void testDynamicProperties() {
	    	 driver.get("https://demoqa.com/dynamic-properties");
	    	 WebElement enableButton = driver.findElement(By.id("enableAfter"));
   			 WebElement colorButton = driver.findElement(By.id("colorChange"));
   			 
   			//assertFalse(enableButton.isEnabled(), "Przycisk już się załadował");
   			String initialColor = colorButton.getCssValue("color");
   			
   			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("enableAfter")));
   			assertTrue(enableButton.isEnabled());
   			
   			/*wait.until(driver -> {
   		        try {
   		            String newColor = colorButton.getCssValue("color");
   		            return !newColor.equals(initialColor);
   		        } catch (StaleElementReferenceException e) {
   		            // React potrafi odświeżyć element, więc pobierz go ponownie
   		            colorButton = driver.findElement(By.id("colorChange"));
   		            String newColor = colorButton.getCssValue("color");
   		            return !newColor.equals(initialColor);
   		        }
   		    });*/
   			
   			String changedColor = colorButton.getCssValue("color");
   			
   			assertNotEquals(initialColor, changedColor); //kolor na stronie sie nie zmienia, musiały być jakieś zmiany w kodzie
   			
   			WebElement visibleAfter = driver.findElement(By.id("visibleAfter"));
   			
   			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));

   			assertTrue(visibleAfter.isDisplayed());
   			
   			  			
	    	 
	    	 
	     }
	     
	     //@Test
	     public void testToolTips() {
	    	 driver.get("https://demoqa.com/tool-tips");
	    	 WebElement button = driver.findElement(By.id("toolTipButton"));
	    	 WebElement textField = driver.findElement(By.id("toolTipTextField"));
	    	 WebElement contraryLink = driver.findElement(By.xpath("//a[text()='Contrary']"));
	    	 WebElement sectionLink = driver.findElement(By.xpath("//a[text()='1.10.32']"));
	    	 
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    	 
	    	 Actions actions = new Actions(driver);
	    	// Mapa elementów do hoverowania z identyfikatorami
	    	    String[] elements = {"button", "textField", "contrary", "section"};

	    	    for (String el : elements) {
	    	        WebElement target = null;
	    	        String expectedText = "";
	    	        	    	        

	    	        switch (el) {
	    	            case "button":
	    	                target = driver.findElement(By.id("toolTipButton"));
	    	                expectedText = "You hovered over the Button";
	    	                break;
	    	            case "textField":
	    	                target = driver.findElement(By.id("toolTipTextField"));
	    	                expectedText = "You hovered over the text field";
	    	                break;
	    	            case "contrary":
	    	                target = driver.findElement(By.xpath("//a[text()='Contrary']"));
	    	                expectedText = "You hovered over the Contrary";
	    	                break;
	    	            case "section":
	    	                target = driver.findElement(By.xpath("//a[text()='1.10.32']"));
	    	                expectedText = "You hovered over the 1.10.32";
	    	                break;
	    	        }
	    	        
	    	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", target);

	    	        // Hover na element
	    	        actions.moveToElement(target).perform();

	    	        // Poczekaj aż tooltip będzie widoczny
	    	        wait.until(ExpectedConditions.textToBe(By.className("tooltip-inner"), expectedText));

	    	     // Pobierz tooltip i asercja
	    	        WebElement tooltip = driver.findElement(By.className("tooltip-inner"));
	    	        assertEquals("Tooltip text mismatch for: " + el, expectedText, tooltip.getText());
	    	    }
	    	 
	    	 
	    	 
	     }
	     
	     //@Test
	     public void testModalDialogs() {
	    	 driver.get("https://demoqa.com/modal-dialogs");
	    	 WebElement smallButton = driver.findElement(By.id("showSmallModal"));
	    	 WebElement largeButton = driver.findElement(By.id("showLargeModal"));
	    	 smallButton.click();
	    	 
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    	 WebElement smallDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-body")));
	    	 assertEquals(
	    			    "This is a small modal. It has very less content",
	    			    smallDialog.getText()
	    			);
	    	 
	    	 
	     }
	     
	     //@Test
	     public void testSelectMenu() throws InterruptedException {
	    	 driver.get("https://demoqa.com/select-menu");
	    	 
	    	 WebElement oldSelect = driver.findElement(By.id("oldSelectMenu"));
	    	 Select select = new Select(oldSelect);
	    	 select.selectByVisibleText("Purple");
	    	 assertEquals("Purple", select.getFirstSelectedOption().getText());
	    	 
	    	 WebElement selectValue = driver.findElement(By.id("react-select-2-input"));
	    	 selectValue.sendKeys("Group 1, option 2");
	    	 selectValue.sendKeys(Keys.ENTER);
	    	 
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    	 WebElement selectedValue = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	         By.cssSelector("#withOptGroup .css-1uccc91-singleValue")));

	    	 // 🔹 Asercja
	    	 assertEquals("Group 1, option 2", selectedValue.getText());
	    	 
	    	 
	    	 WebElement selectOne = driver.findElement(By.id("react-select-3-input"));
	    	 selectOne.sendKeys("Dr.");
	    	 selectOne.sendKeys(Keys.ENTER);
	    	 
	    	 WebElement selectedOne = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	         By.cssSelector("#selectOne div[class$='-singleValue']")));

	    	 // 🔹 Asercja
	    	 assertEquals("Dr.", selectedOne.getText());
	    	 
	    	 WebElement multiselect = driver.findElement(By.id("react-select-4-input"));
	    	 multiselect.sendKeys("Blue");
	    	 multiselect.sendKeys(Keys.ENTER);
	    	 multiselect.sendKeys("Green");
	    	 multiselect.sendKeys(Keys.ENTER);
	    	 
	    	     	 
	    	// Pobierz wybrane wartości
	    	    List<WebElement> selectedOptions = driver.findElements(By.cssSelector(".css-12jo7m5"));
	    	    List<String> values = selectedOptions.stream()
	    	        .map(WebElement::getText)
	    	        .toList();

	    	    // Sprawdź, że są obie
	    	    assertTrue(values.contains("Blue"));
	    	    assertTrue(values.contains("Green"));
	    	    
	    	    
	    	    WebElement smultiselect = driver.findElement(By.id("cars"));
	    	    Select select2 = new Select(smultiselect);
	    	    select2.selectByVisibleText("Volvo");
	    	    select2.selectByVisibleText("Saab");
	    	    List<String> expected = Arrays.asList("Volvo", "Saab");
	    	    List<String> actual = select2.getAllSelectedOptions()
	    	            .stream()
	    	            .map(WebElement::getText)
	    	            .toList();
	    	    
	    	    
	    	    assertEquals(expected, actual);
	    	    
	    	 
	    	 
	    	 
	     }
	     
	     //@Test
	     public void testTabs() {
	    	 driver.get("https://demoqa.com/tabs");
	    	// Zlokalizuj zakładki
	    	    WebElement whatTab = driver.findElement(By.id("demo-tab-what"));
	    	    WebElement originTab = driver.findElement(By.id("demo-tab-origin"));
	    	    WebElement useTab = driver.findElement(By.id("demo-tab-use"));

	    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	    
	    	    String[] elements = {"What", "Origin", "Use"};

	    	    for (String el : elements) {
	    	        WebElement tab = null;
	    	        WebElement Content = null;
	    	        String expectedText = "";
	    	        
	    	        switch (el) {
	    	        	case "What":
	    	        		tab = driver.findElement(By.id("demo-tab-what"));
	    	        		Content = driver.findElement(By.id("demo-tabpane-what"));
	    	        		expectedText = "Lorem Ipsum";
	    	        		break;
	    	        	case "Origin":
	    	        		tab = driver.findElement(By.id("demo-tab-origin"));
	    	        		Content = driver.findElement(By.id("demo-tabpane-origin"));
	    	        		expectedText = "Contrary to popular belief";
	    	        		break;
	    	        	case "Use":
	    	        		tab = driver.findElement(By.id("demo-tab-use"));
	    	        		Content = driver.findElement(By.id("demo-tabpane-use"));
	    	        		expectedText = "It is a long established fact";
	    	        		break;  
	    	        }
	    	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tab);
	    	        tab.click();
	    	        wait.until(ExpectedConditions.visibilityOf(Content));
	    	        assertTrue(Content.getText().contains(expectedText));
	    	        
	    	        
	    	        }

	    	    // Kliknij pierwszą zakładkę i sprawdź treść
	    	    /*whatTab.click();
	    	    WebElement whatContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("demo-tabpane-what")));
	    	    assertTrue(whatContent.getText().contains("Lorem Ipsum"));
	    	    
	    	 // Kliknij drugą zakładkę i sprawdź treść
	    	    originTab.click();
	    	    WebElement originContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("demo-tabpane-origin")));
	    	    assertTrue(originContent.getText().contains("Contrary to popular belief"));

	    	    // Kliknij trzecią zakładkę i sprawdź treść
	    	    useTab.click();
	    	    WebElement useContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("demo-tabpane-use")));
	    	    assertTrue(useContent.getText().contains("It is a long established fact"));
	    	    */
	    	    // More Tab nie dostepna
	    	    WebElement moreTab = driver.findElement(By.id("demo-tab-more"));
	    	    assertTrue(Boolean.parseBoolean(moreTab.getAttribute("aria-disabled")));
	     }
	     
	     //@Test
	     public void testMenu() {
	    	 driver.get("https://demoqa.com/menu");
	    	 
	    	 Actions actions = new Actions(driver);
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	    
	    	 WebElement Menu1 = driver.findElement(By.linkText("Main Item 1"));
	    	 WebElement Menu2 = driver.findElement(By.linkText("Main Item 2"));
	    	 WebElement Menu3 = driver.findElement(By.linkText("Main Item 3"));
	    	 
	    	 assertTrue(Menu1.isDisplayed());
	    	 assertTrue(Menu2.isDisplayed());
	    	 assertTrue(Menu3.isDisplayed());
	    	 
	    	// Hover na Main Item 2, aby rozwinąć submenu
	    	    actions.moveToElement(Menu2).perform();

	    	    // Poczekaj na widoczność submenu
	    	    WebElement subList = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("//a[text()='SUB SUB LIST »']")));


	    	 // Najechanie na SUB SUB LIST »
	    	    WebElement subSubList = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
	    	    actions.moveToElement(subSubList).perform();
	    	    
	    	    
	    	    // Teraz kliknij Sub Sub Item 2 i sprawdz że subitem sie wyświetlił
	    	    WebElement subSubItem2 = driver.findElement(By.xpath("//a[text()='Sub Sub Item 2']"));
	    	    assertTrue(subSubItem2.isDisplayed());
	    	    assertTrue(subSubItem2.isEnabled());
	    	    actions.moveToElement(subSubItem2).click().perform();

	    	    
	    	 
	    	 
	     }
	     
	     //@Test
	     public void testAccordian() {
	    	 driver.get("https://demoqa.com/accordian");
	    	 
	    	 WebElement Menu1 = driver.findElement(By.id("section1Heading"));
	    	 WebElement Menu2 = driver.findElement(By.id("section2Heading"));
	    	 WebElement Menu3 = driver.findElement(By.id("section3Heading"));
	    	 
	    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Menu1);
	    	 
	    	 Menu1.click();
	    	 
	    	 WebElement Menu1Content = driver.findElement(By.id("section1Content"));
	    	 
	    	 assertTrue(Menu1Content.getText().contains("Lorem Ipsum is simply dummy text"));
	    	 
	    	 
	     }
	     
	     //@Test
	     public void testAutoComplete() throws InterruptedException {
	    	 driver.get("https://demoqa.com/auto-complete");
	    	 
	    	 WebElement Select1 = driver.findElement(By.id("autoCompleteMultipleInput"));
	    	 Select1.sendKeys("r");
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auto-complete__menu")));
	    	 WebElement Red = driver.findElement(By.xpath("//div[contains(@class,'auto-complete__option') and text()='Red']"));
	    	 Red.click();
	    	 
	    	 WebElement RedSelected = driver.findElement(By.xpath("//*[@id=\"autoCompleteMultipleContainer\"]/div/div[1]/div[1]/div[1]"));
	    	 assertTrue(RedSelected.isDisplayed());
	    	 
	    	 WebElement Select2 = driver.findElement(By.id("autoCompleteSingleInput"));
	    	 
	    	 Select2.sendKeys("b");
	    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auto-complete__menu")));
	    	 WebElement Blue = driver.findElement(By.xpath("//div[contains(@class,'auto-complete__option') and text()='Blue']"));
	    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Blue);
	    	 Blue.click();
	    	// po wybraniu opcji "Blue"
	    	 // czekamy aż react wyrenderuje element pokazujący wybraną wartość
	    	 By singleValueSelector = By.cssSelector("#autoCompleteSingleContainer div[class$='-singleValue']");

	    	 WebElement selectedDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(singleValueSelector));
	    	 String actual = selectedDiv.getText().trim();
	    	 assertEquals("Blue", actual);
	    	 
	    	 	    	 
	    	 
	    	 
	     }
	     
	     
	     //@Test
	     public void testSortable() {
	    	 driver.get("https://demoqa.com/sortable");
	    	 
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	    Actions actions = new Actions(driver);

	    	    // ---------------------------
	    	    // *** SORTABLE LIST ***
	    	    // ---------------------------

	    	    // Upewnij się, że jesteśmy w trybie List
	    	    WebElement listTab = driver.findElement(By.id("demo-tab-list"));
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listTab);
	    	    listTab.click();

	    	    // Pobierz elementy listy
	    	    List<WebElement> listItems = driver.findElements(
	    	            By.cssSelector("#demo-tabpane-list .list-group-item"));

	    	    // Zapamiętaj początkową kolejność
	    	    List<String> beforeList = listItems.stream()
	    	            .map(WebElement::getText)
	    	            .collect(Collectors.toList());

	    	    // Element do przeciągnięcia
	    	    WebElement item1 = driver.findElement(By.xpath("//div[@id='demo-tabpane-list']//div[text()='One']"));
	    	    WebElement item3 = driver.findElement(By.xpath("//div[@id='demo-tabpane-list']//div[text()='Three']"));

	    	    // Przewiń jeśli trzeba
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item3);

	    	    // Drag & drop (React-friendly)
	    	    actions.clickAndHold(item1)
	    	            .moveToElement(item3)
	    	            .moveByOffset(10, 10)
	    	            .release()
	    	            .perform();

	    	    // Poczekaj chwilę na animację
	    	    try { Thread.sleep(800); } catch (Exception ignored) {}

	    	    // Pobierz kolejność po zmianie
	    	    List<WebElement> listItemsAfter = driver.findElements(
	    	            By.cssSelector("#demo-tabpane-list .list-group-item"));

	    	    List<String> afterList = listItemsAfter.stream()
	    	            .map(WebElement::getText)
	    	            .collect(Collectors.toList());

	    	    // --- TU WSTAW SWOJE ASERCJE ---
	    	    assertNotEquals(beforeList, afterList);
	    	    //assertTrue(afterList.get(2).contains("One"));



	    	    // ---------------------------
	    	    // *** SORTABLE GRID ***
	    	    // ---------------------------

	    	    WebElement gridTab = driver.findElement(By.id("demo-tab-grid"));
	    	    gridTab.click();

	    	    // Pobierz elementy gridu
	    	    List<WebElement> gridItems = driver.findElements(
	    	            By.cssSelector("#demo-tabpane-grid .list-group-item"));

	    	    // Zapamiętaj początkową kolejność
	    	    List<String> beforeGrid = gridItems.stream()
	    	            .map(WebElement::getText)
	    	            .collect(Collectors.toList());

	    	    // Elementy do drag & drop
	    	    WebElement one = driver.findElement(By.xpath("//div[@id='demo-tabpane-grid']//div[text()='One']"));
	    	    WebElement nine = driver.findElement(By.xpath("//div[@id='demo-tabpane-grid']//div[text()='Nine']"));

	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nine);

	    	    // Drag & drop
	    	    actions.clickAndHold(one)
	    	            .moveToElement(nine)
	    	            .moveByOffset(10, 10)
	    	            .release()
	    	            .perform();

	    	    try { Thread.sleep(800); } catch (Exception ignored) {}

	    	    // Pobierz nową kolejność
	    	    List<WebElement> gridItemsAfter = driver.findElements(
	    	            By.cssSelector("#demo-tabpane-grid .list-group-item"));

	    	    List<String> afterGrid = gridItemsAfter.stream()
	    	            .map(WebElement::getText)
	    	            .collect(Collectors.toList());

	    	    // --- TU WSTAW SWOJE ASERCJE ---
	    	     assertNotEquals(beforeGrid, afterGrid);
	    	     //assertFalse(afterGrid.get(0).contains("One"));
	    	 
	    	 
	     }
	     
	     //@Test
	     public void testSelectable() {
	    	 driver.get("https://demoqa.com/selectable");
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	 
	    	// Upewnij się, że jesteśmy w trybie List
	    	    WebElement listTab = driver.findElement(By.id("demo-tab-list"));
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listTab);
	    	    listTab.click();
	    	    
	    	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	            By.id("verticalListContainer")
	    	    ));
	    	    
	    	 // elementy listy
	    	    WebElement item1 = driver.findElement(By.cssSelector("#verticalListContainer > li:nth-child(1)"));
	    	    WebElement item2 = driver.findElement(By.cssSelector("#verticalListContainer > li:nth-child(2)"));

	    	    // klikamy 1.
	    	    item1.click();
	    	    assertTrue(item1.getAttribute("class").contains("active"));
	    	    assertFalse(item2.getAttribute("class").contains("active"), "Błąd");

	    	    // klikamy 2.
	    	    item2.click();
	    	    assertTrue(item2.getAttribute("class").contains("active"));
	    	    //assertFalse(item1.getAttribute("class").contains("active"), "Błąd");
	    	 
	     }
	     
	     //@Test
	     public void testResizable() {
	    	 driver.get("https://demoqa.com/resizable");
	    	 
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	    Actions actions = new Actions(driver);

	    	    // -----------------------------
	    	    // 1. Resizable Box With Restriction
	    	    // -----------------------------

	    	    WebElement restrictedBox = driver.findElement(By.id("resizableBoxWithRestriction"));
	    	    WebElement restrictedHandle = restrictedBox.findElement(
	    	            By.cssSelector(".react-resizable-handle-se")
	    	    );
	    	    
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", restrictedBox);

	    	    // Odczytaj początkowy rozmiar
	    	    Dimension startSize = restrictedBox.getSize();
	    	    
	    	    

	    	    // Przeciągnij uchwyt (np. 300px w prawo i 150px w dół)
	    	    actions.dragAndDropBy(restrictedHandle, 300, 150).perform();

	    	    // Sprawdź końcowy rozmiar i wykonaj asercje:
	    	    Dimension endSize = restrictedBox.getSize();
	    	    assertTrue(endSize.getWidth()<=500);
	    	    assertTrue(endSize.getHeight()<=300);
	    	    // - szerokość <= 500
	    	    // - wysokość <= 300
	     }
	     
	     
	     //@Test
	     public void testDroppable() throws InterruptedException {

	         driver.get("https://demoqa.com/droppable");
	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	         Actions actions = new Actions(driver);

	         // =============================================================
	         // 1. SIMPLE DROPPABLE
	         // =============================================================

	         WebElement dragSimple = driver.findElement(By.id("draggable"));
	         WebElement dropSimple = driver.findElement(By.id("droppable"));

	         actions.dragAndDrop(dragSimple, dropSimple).perform();

	         assertEquals("Dropped!", dropSimple.findElement(By.tagName("p")).getText());


	         // =============================================================
	         // 2. ACCEPT DROPPABLE
	         // =============================================================

	         driver.findElement(By.id("droppableExample-tab-accept")).click();

	         WebElement acceptable = driver.findElement(By.id("acceptable"));
	         WebElement notAcceptable = driver.findElement(By.id("notAcceptable"));
	         WebElement dropAccept = driver.findElement(By.cssSelector("#acceptDropContainer #droppable"));

	         // Najpierw element NIEakceptowalny
	         actions.dragAndDrop(notAcceptable, dropAccept).perform();
	         assertEquals("Drop here", dropAccept.findElement(By.tagName("p")).getText());

	         // Teraz element akceptowalny
	         actions.dragAndDrop(acceptable, dropAccept).perform();
	         assertEquals("Dropped!", dropAccept.findElement(By.tagName("p")).getText());


	         // =============================================================
	         // 3. PREVENT PROPAGATION
	         // =============================================================

	         driver.findElement(By.id("droppableExample-tab-preventPropogation")).click();

	         WebElement dragPP = driver.findElement(By.id("dragBox"));
	         WebElement outerPP = driver.findElement(By.id("notGreedyDropBox"));
	         WebElement innerPP = driver.findElement(By.id("notGreedyInnerDropBox"));

	         // Przeciągnięcie na inner
	         actions.dragAndDrop(dragPP, innerPP).perform();

	         //assertEquals("Dropped!", innerPP.findElement(By.tagName("p")).getText());
	         //assertEquals("Dropped!", outerPP.findElement(By.tagName("p")).getText());


	         // =============================================================
	         // 4. REVERT DRAGGABLE
	         // =============================================================

	         driver.findElement(By.id("droppableExample-tab-revertable")).click();

	         WebElement revertable = driver.findElement(By.id("revertable"));
	         WebElement notRevertable = driver.findElement(By.id("notRevertable"));
	         WebElement dropRevert = driver.findElement(By.cssSelector("#revertableDropContainer #droppable"));

	         // ------------- 4.1 NOT REVERTABLE -------------
	         Point beforeNot = notRevertable.getLocation();
	        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", revertable);
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", notRevertable);
	         actions.dragAndDrop(notRevertable, dropRevert).perform();
	         Thread.sleep(500);
	         Point afterNot = notRevertable.getLocation();

	         // Nie powinien wrócić
	         assertNotEquals(beforeNot, afterNot);

	         // ------------- 4.2 REVERTABLE -------------
	         Point beforeRev = revertable.getLocation();
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", revertable);
	         //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropSimple);
	         actions.dragAndDrop(revertable, dropRevert).perform();

	         Thread.sleep(1000); // revert animacja
	         Point afterRev = revertable.getLocation();

	         // Powinien wrócić na swoje miejsce
	         assertEquals(beforeRev, afterRev);

	     }
	     
	   @Test
	     public void testDraggable() {
	    	 driver.get("https://demoqa.com/dragabble");
	    	 Actions actions = new Actions(driver);
	    	 
	    	 WebElement dragBox = driver.findElement(By.id("dragBox"));
	    	 
	    	 Point start = dragBox.getLocation();
	    	 
	    	 actions.clickAndHold(dragBox)
	            .moveByOffset(10, 10)
	            .release()
	            .perform();
	    	 
	    	 Point end = dragBox.getLocation();
	    	 assertNotEquals(start, end);
	    	 
	    	 
	     }
	   
	   
	    	 
	    	 
	     
	     
	     
	     
	     
	    	 
	    	 
	    	 
	    	 
	    	 
	        
	         
	     
	     
	    	 
	     
	     

	 }    
	   
	   
	   
	   
	     
	   
	     
	    
	    
	    
	    
	    
	    
	    
	    
	         

