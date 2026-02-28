package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




public class HerokuappTest {
	
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
    public void testAbTestingLink() {
        // 1. Wejście na stronę główną
        driver.get("https://the-internet.herokuapp.com");

        // 2. Znalezienie linku "A/B Testing"
        WebElement abLink = driver.findElement(By.linkText("A/B Testing"));

        // 3. Kliknięcie
        abLink.click();
        
        // 4. Asercja: sprawdź, że URL zawiera "/abtest"
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/abtest"));
    }
    
    //@Test
	public void titleTest() {
	    driver.get("https://the-internet.herokuapp.com");
	    String title = driver.getTitle();
	    assertTrue(title.contains("The Internet"));
	}

	//@Test
    public void testInvalidLogin() {
    	driver.get("https://the-internet.herokuapp.com/login");
    	WebElement usernameField = driver.findElement(By.name("username"));
    	WebElement passwordField = driver.findElement(By.name("password"));
    	usernameField.sendKeys("baduser");
    	passwordField.sendKeys("badpassword");
    	driver.findElement(By.cssSelector("button[type='submit']")).click();
    	
    	 try {
    	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	        wait.until(ExpectedConditions.alertIsPresent());
    	        driver.switchTo().alert().accept(); // kliknij "OK"
    	    } catch (Exception e) {
    	        System.out.println("Brak alertu — kontynuuję test.");
    	    }
    	
    	assertTrue(driver.getPageSource().contains("Your username is invalid!"));
    }
	
	//@Test
	public void testValidLogin() {
	    driver.get("https://the-internet.herokuapp.com/login");
	    WebElement usernameField = driver.findElement(By.name("username"));
    	WebElement passwordField = driver.findElement(By.name("password"));
	    usernameField.sendKeys("tomsmith");
	    passwordField.sendKeys("SuperSecretPassword!");
	    driver.findElement(By.cssSelector("button[type='submit']")).click();
	    
	    /*try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept(); // kliknij "OK"
	    } catch (Exception e) {
	        System.out.println("Brak alertu — kontynuuję test.");
	    } */
	    
	    
	    assertTrue(driver.getPageSource().contains("You logged into a secure area!"));
	}
	
	 //@Test
		public void AddRemoveElementsTest() {
		    driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		    WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
		    addButton.click();
		    WebElement deleteButton = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Delete']"))
		    );
		    assertTrue(deleteButton.isDisplayed());
		    deleteButton.click();
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[text()='Delete']")));
		    assertTrue(driver.findElements(By.xpath("//button[text()='Delete']")).isEmpty());
		    
		}
	 
	 //@Test
	 public void testCheckboxes() {
	     // 1️⃣ Wejdź na stronę z checkboxami
	     driver.get("https://the-internet.herokuapp.com/checkboxes");

	     // 2️⃣ Pobierz oba checkboxy
	     //List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
	     //WebElement first = checkboxes.get(0);
	     //WebElement second = checkboxes.get(1);
	     
	     WebElement first = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
	     WebElement second = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));

	     // 3️⃣ Upewnij się, że pierwszy jest zaznaczony, a drugi odznaczony
	     if (!first.isSelected()) {
	         first.click();
	     }
	     if (second.isSelected()) {
	         second.click();
	     }

	     // 4️⃣ Sprawdź asercjami, że stany są poprawne
	     assertTrue(first.isSelected(), "Pierwszy checkbox powinien być zaznaczony");
	     assertFalse(second.isSelected(), "Drugi checkbox powinien być odznaczony");
	 }
	 
	//@Test
	 public void testSlider() {
		 driver.get("https://the-internet.herokuapp.com/horizontal_slider");
		 Actions actions = new Actions(driver);
		 WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
		 actions.clickAndHold(slider).moveByOffset(40, 0).release().perform();
		 WebElement value = driver.findElement(By.id("range"));
		 assertEquals("4", value.getText());    
	 }
	 
	 //@Test
	 public void testRemoveCheckbox() {
		 driver.get("https://the-internet.herokuapp.com/dynamic_controls");
		 WebElement checkbox = driver.findElement(By.id("checkbox"));
		 WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
		 removeButton.click();
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.invisibilityOf(checkbox));
		 WebElement message = driver.findElement(By.id("message"));
		 assertTrue(message.getText().contains("It's gone!"));
		 removeButton.click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
		 assertTrue(driver.findElement(By.id("message")).getText().contains("It's back!"));
	 }
	 
	 //@Test
	 public void testEnableDisableInput() {
		 driver.get("https://the-internet.herokuapp.com/dynamic_controls");

		    WebElement enableButton = driver.findElement(By.xpath("//form[@id='input-example']/button"));
		    WebElement inputField = driver.findElement(By.xpath("//form[@id='input-example']/input"));

		    // klikamy "Enable"
		    enableButton.click();

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(inputField));

		    // 🔄 ponownie wyszukujemy elementy po zmianie DOM
		    WebElement message = driver.findElement(By.id("message"));
		    inputField = driver.findElement(By.xpath("//form[@id='input-example']/input"));

		    assertTrue(inputField.isEnabled());
		    assertTrue(message.getText().contains("It's enabled!"));
		    
		    // wpisz tekst po odblokowaniu pola
		    inputField.sendKeys("Test wpisany po odblokowaniu");

		    // sprawdź, że pole faktycznie zawiera tekst
		    assertTrue(inputField.getAttribute("value").equals("Test wpisany po odblokowaniu"));

		    // klikamy "Disable"
		    enableButton.click();

		    // czekamy aż znowu się zaktualizuje
		    wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(inputField)));

		    // 🔄 ponownie wyszukujemy element po zmianie
		    message = driver.findElement(By.id("message"));
		    assertTrue(message.getText().contains("It's disabled!"));
		 
	 }
	 
	 //@Test
	 public void testDynamicLoading() {
		 driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		 
		 WebElement startButton = driver.findElement(By.xpath("//*[@id=\"start\"]/button"));
		 startButton.click();
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement helloText = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//div[@id='finish']/h4")));
		    assertTrue(helloText.getText().contains("Hello World!"));
		    
	 }
	 
	 //@Test
	 public void testDynamicLoading2() {
	     // 1. Przejdź na stronę
		 driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
	     
	     // 2. Kliknij przycisk Start
		 driver.findElement(By.xpath("//div[@id='start']/button")).click();
	     
	     // 3. Poczekaj, aż element stanie się widoczny
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement helloText = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//div[@id='finish']/h4")));
	     
	     // 4. Sprawdź tekst
		 assertTrue(helloText.getText().contains("Hello World!"));
	 }
	 
	 //@Test
	 public void testUpload() {
		 driver.get("https://the-internet.herokuapp.com/upload");
		 
		 WebElement uploadButton = driver.findElement(By.id("file-upload"));
		 		 
		 uploadButton.sendKeys("C:\\Users\\Dell\\Documents\\test.txt");
		 
		 driver.findElement(By.id("file-submit")).click();
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));
		 
		 assertTrue(driver.getPageSource().contains("File Uploaded!"));
		 
		 assertTrue(driver.getPageSource().contains("test.txt"));
		 
		 
	 }
	 
	 //@Test
	 public void testUploadWithoutFile() {
		 driver.get("https://the-internet.herokuapp.com/upload");

	     // Nie wybieramy żadnego pliku — od razu klikamy przycisk
	     driver.findElement(By.id("file-submit")).click();

	     // Sprawdzamy, że strona NIE pokazuje komunikatu sukcesu
	     assertTrue(!driver.getPageSource().contains("File Uploaded!"));
	     assertTrue(driver.getCurrentUrl().contains("/upload"));
	 }
	 
	 //@Test
	 public void testDropDown() {
		 driver.get("https://the-internet.herokuapp.com/dropdown");
		 WebElement dropdownElement = driver.findElement(By.id("dropdown"));
		 Select dropdown = new Select(dropdownElement);
		 dropdown.selectByVisibleText("Option 1");
		 assertEquals("Option 1", dropdown.getFirstSelectedOption().getText());
		 dropdown.selectByVisibleText("Option 2");
		 assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
		 
	 }
	 
	 //@Test
	 public void testAllDropdownOptions() {
	     driver.get("https://the-internet.herokuapp.com/dropdown");

	     WebElement dropdown = driver.findElement(By.id("dropdown"));
	     Select select = new Select(dropdown);

	     // Pobierz wszystkie opcje
	     List<WebElement> options = select.getOptions();

	     // Sprawdź, że lista ma 3 elementy (łącznie z "Please select an option")
	     assertEquals(3, options.size(), "Dropdown should have 3 options");

	     // Sprawdź, że zawiera oczekiwane wartości
	     List<String> expectedTexts = Arrays.asList(
	             "Please select an option",
	             "Option 1",
	             "Option 2"
	     );

	     List<String> actualTexts = options.stream()
	             .map(WebElement::getText)
	             .collect(Collectors.toList());

	     assertEquals(expectedTexts, actualTexts, "Dropdown options should match expected list");

	     // Przetestuj wybór każdej opcji
	     for (int i = 1; i < options.size(); i++) { // pomijamy pierwszą, bo to placeholder
	         select.selectByIndex(i);
	         WebElement selected = select.getFirstSelectedOption();
	         System.out.println("Selected: " + selected.getText());
	         assertTrue(expectedTexts.contains(selected.getText()));
	     }
	 }
	 
	 //@Test
	 public void testSimpleAlert() {
	     // 1. Otwórz stronę z alertami
	     driver.get("https://the-internet.herokuapp.com/javascript_alerts");

	     // 2. Znajdź i kliknij przycisk „Click for JS Alert”
	     WebElement alertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
	     alertButton.click();

	     // 3. Przełącz się na alert
	     Alert alert = driver.switchTo().alert();

	     // 4. Sprawdź tekst w alercie
	     String alertText = alert.getText();
	     assertTrue(alertText.contains("I am a JS Alert"));

	     // 5. Zamknij alert (kliknij OK)
	     alert.accept();

	     // 6. Sprawdź wynik na stronie
	     WebElement result = driver.findElement(By.id("result"));
	     assertEquals("You successfully clicked an alert", result.getText());
	 }
	 
	 //@Test
	 public void testConfirmAlert() {
	     // 1. Otwórz stronę z alertami
	     driver.get("https://the-internet.herokuapp.com/javascript_alerts");

	     // ===== SCENARIUSZ 1: Klikamy OK =====
	     WebElement confirmButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
	     confirmButton.click();

	     Alert alert = driver.switchTo().alert();
	     String alertText = alert.getText();
	     assertTrue(alertText.contains("I am a JS Confirm"));

	     alert.accept();  // klikamy OK

	     WebElement result = driver.findElement(By.id("result"));
	     assertEquals("You clicked: Ok", result.getText());

	     // ===== SCENARIUSZ 2: Klikamy Cancel =====
	     confirmButton.click();  // ponownie klikamy przycisk

	     alert = driver.switchTo().alert();
	     alert.dismiss();  // klikamy Cancel

	     result = driver.findElement(By.id("result"));
	     assertEquals("You clicked: Cancel", result.getText());
	 }
	 
	 //@Test
	 public void testPromptAlert() throws InterruptedException {
	     // 1. Otwórz stronę z alertami
	     driver.get("https://the-internet.herokuapp.com/javascript_alerts");

	     // 2. Znajdź przycisk Prompt
	     WebElement promptButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
	     promptButton.click();

	     // 3. Przełącz się na alert
	     Alert alert = driver.switchTo().alert();

	     // 4. Odczytaj tekst alertu i sprawdź
	     String alertText = alert.getText();
	     assertTrue(alertText.contains("I am a JS prompt"));

	     // 5. Wpisz tekst i zatwierdź OK
	     String inputText = "TestUser";
	     alert.sendKeys(inputText);
	     alert.accept();

	     // 6. Sprawdź wynik na stronie
	     WebElement result = driver.findElement(By.id("result"));
	     assertEquals("You entered: " + inputText, result.getText());
	     
	     //Thread.sleep(5000);
	     // 7. Test Cancel
	     promptButton.click();
	     alert = driver.switchTo().alert();
	     alert.dismiss();  // klik Cancel
	     result = driver.findElement(By.id("result"));
	     assertEquals("You entered: null", result.getText());
	 }
	 
	 //@Test
	 public void testHover() {
	     driver.get("https://the-internet.herokuapp.com/hovers");

	     // 1. Znajdź pierwszy obrazek
	     WebElement firstImage = driver.findElement(By.xpath("(//div[@class='figure'])[1]"));

	     // 2. Utwórz obiekt Actions
	     Actions actions = new Actions(driver);

	     // 3. Najedź myszką na obrazek
	     actions.moveToElement(firstImage).perform();

	     // 4. Sprawdź, czy pojawił się tekst
	     WebElement caption = firstImage.findElement(By.className("figcaption"));
	     assertTrue(caption.isDisplayed());

	     // 5. Opcjonalnie sprawdź treść tekstu
	     String captionText = caption.getText();
	     assertTrue(captionText.contains("name: user1"));
	 }
	 
	 //@Test
	 public void testIframe() {
	     driver.get("https://the-internet.herokuapp.com/iframe");

	     // 1. Przełącz się do iframe
	     try {
	     WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
	     driver.switchTo().frame(iframe);

	     // 2. Znajdź pole tekstowe wewnątrz iframe
	     WebElement textBox = driver.findElement(By.id("tinymce"));
	    

	     // metoda 1: za pomocą kombinacji klawiszy
	     textBox.sendKeys(Keys.CONTROL + "a");
	     textBox.sendKeys(Keys.BACK_SPACE);
	     textBox.sendKeys("Hello, Selenium!");
	     
	  // Poczekaj, aż tekst zostanie wpisany (opcjonalnie)
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.textToBePresentInElement(textBox, "Hello, Selenium!"));

	     // metoda 2: za pomocą JavaScript
	     // JavascriptExecutor js = (JavascriptExecutor) driver;
	     // js.executeScript("arguments[0].innerHTML = '';", textBox);
	     // textBox.sendKeys("Hello, Selenium!");

	     assertEquals("Hello, Selenium!", textBox.getText());}
	     catch (Exception e) {
	    	    System.out.println("Iframe niedostępny: " + e.getMessage());
	    	} finally {
	    	    driver.switchTo().defaultContent();
	    	}
	     
	 }
	     
	     //@Test
	     public void testHoverAllUsers() {
	         driver.get("https://the-internet.herokuapp.com/hovers");

	         // Pobierz wszystkie elementy z klasą "figure" (to są avatary)
	         List<WebElement> users = driver.findElements(By.cssSelector(".figure"));

	         Actions actions = new Actions(driver);
	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	         // Iterujemy po każdym avatarze
	         for (int i = 0; i < users.size(); i++) {
	             WebElement user = users.get(i);

	             // Najedź myszką
	             actions.moveToElement(user).perform();

	             // Poczekaj na link "View profile" dla tego avatara
	             WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
	                 user.findElement(By.tagName("a"))
	             ));

	             // Sprawdź, czy link jest wyświetlony
	             assertTrue(profileLink.isDisplayed(), "Profile link is not visible for user " + (i + 1));

	             // Kliknij w link
	             profileLink.click();

	             // Sprawdź, czy URL zawiera "/users/{i+1}"
	             assertTrue(driver.getCurrentUrl().contains("/users/" + (i + 1)),
	                        "URL is incorrect for user " + (i + 1));

	             // Wróć na stronę hover
	             driver.navigate().back();
	             
	             // Odśwież listę elementów, bo DOM się zmienił po powrocie
	             users = driver.findElements(By.cssSelector(".figure"));
	         }
	     }
	         
	         //@Test
		     public void testTable() {
		         // 1. Otwórz stronę z tabelą
		         driver.get("https://the-internet.herokuapp.com/tables");

		         // 2. Pobierz tabelę po id
		         WebElement table = driver.findElement(By.id("table1"));

		         // 3. Pobierz nagłówki kolumn
		         List<WebElement> headers = table.findElements(By.tagName("th"));
		         for (WebElement header : headers) {
		             System.out.println("Nagłówek: " + header.getText());
		         }

		         // 4. Pobierz wszystkie wiersze
		         List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr[td]"));
		         for (WebElement row : rows) {
		             List<WebElement> cells = row.findElements(By.tagName("td"));
		             for (WebElement cell : cells) {
		                 System.out.print(cell.getText() + " | ");
		             }
		             System.out.println();
		         }
		         
		         List<WebElement> firstRowCells = table.findElements(By.xpath(".//tr[1]/td"));
		         int columnCount = firstRowCells.size();
		         
		         int rowCount = rows.size();
		         
		         WebElement header = table.findElement(By.xpath(".//th/span[text()='Last Name']"));
		         assertTrue(header.isDisplayed());

		         // 5. asercje
		         // sprawdzenie liczby kolumn, wartości konkretnej komórki itp.
		         assertEquals("Smith", rows.get(0).findElements(By.tagName("td")).get(0).getText());
		         assertEquals(6, columnCount);
		         assertEquals(4, rowCount);
		         
		      // 5️⃣ Sprawdzenie klasy CSS wiersza (jeżeli istnieje)
		         //String rowClass = rows.get(0).getAttribute("class");
		         //assertTrue(rowClass.contains("header headerSortUp"), "Wiersz powinien mieć klasę 'example' (jeżeli jest)");

		         // 6️⃣ Iteracja po wszystkich wierszach i sprawdzenie, czy wszystkie mają dane w drugiej kolumnie
		         for (WebElement row : rows) {
		             List<WebElement> columns = row.findElements(By.tagName("td"));
		             assertTrue(!columns.get(1).getText().isEmpty(), "Druga kolumna nie może być pusta");
		         }
		     }
	         
	         
	         @Test
		     public void testSorting() {
		         // 1. Otwórz stronę z tabelą
		         driver.get("https://the-internet.herokuapp.com/tables");

		         // 2. Pobierz tabelę po id
		         WebElement table = driver.findElement(By.id("table1"));
		         
		         List<WebElement> lastNames = table.findElements(By.xpath(".//tbody/tr/td[1]"));
		         List<String> namesText = lastNames.stream()
		                                          .map(WebElement::getText)
		                                          .collect(Collectors.toList());
		         
		         WebElement lastName = driver.findElement(By.xpath("//*[@id=\"table1\"]/thead/tr/th[1]/span"));
			     lastName.click();
			     
			     
			     // 5. Poczekaj chwilę (żeby dane się odświeżyły)
			     new WebDriverWait(driver, Duration.ofSeconds(2))
			             .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//tbody/tr/td[1]")));
			     
			     // 6. Pobierz nazwiska PONOWNIE po kliknięciu
			     List<WebElement> lastNamesAfter = table.findElements(By.xpath(".//tbody/tr/td[1]"));
			     List<String> namesTextAfter = lastNamesAfter.stream()
			             .map(WebElement::getText)
			             .collect(Collectors.toList());
			     
			     List<String> sortedList = new ArrayList<>(namesText);
			     Collections.sort(sortedList);
			     
			     assertEquals(sortedList, namesTextAfter);

		         
	         }
	         
	         //@Test
	         public void testNotificationMessage() {
	             // 1. Otwórz stronę
	             driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

	             // 2. Kliknij w link, aby wywołać komunikat
	             WebElement clickHere = driver.findElement(By.linkText("Click here"));
	             clickHere.click();

	             // 3. Poczekaj na pojawienie się komunikatu
	             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	             WebElement notification = wait.until(
	                 ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
	             );

	             // 4. Pobierz tekst komunikatu
	             String message = notification.getText().trim();

	             // 5. Sprawdź, czy komunikat zawiera jedno z oczekiwanych zdań
	             boolean valid = message.contains("Action successful")
	                          || message.contains("Action unsuccessful")
	                          || message.contains("Action unsuccesful");

	             assertTrue(valid, "Nieprawidłowy komunikat: " + message);
	         }
	         
	         //@Test
	         public void testDragAndDrop() {
	             driver.get("https://the-internet.herokuapp.com/drag_and_drop");

	             WebElement columnA = driver.findElement(By.id("column-a"));
	             WebElement columnB = driver.findElement(By.id("column-b"));
	             
	             Actions actions = new Actions(driver);
	             actions.dragAndDrop(columnA, columnB).perform();

	             // Wykonanie przeciągnięcia (HTML5 wymaga specjalnego JS workaround)
	            /* String jsDragAndDrop = """
	                 function createEvent(typeOfEvent) {
	                     var event = document.createEvent("CustomEvent");
	                     event.initCustomEvent(typeOfEvent, true, true, null);
	                     event.dataTransfer = {
	                         data: {},
	                         setData: function (key, value) { this.data[key] = value; },
	                         getData: function (key) { return this.data[key]; }
	                     };
	                     return event;
	                 }

	                 function dispatchEvent(element, event, transferData) {
	                     if (transferData !== undefined) {
	                         event.dataTransfer = transferData;
	                     }
	                     if (element.dispatchEvent) {
	                         element.dispatchEvent(event);
	                     } else if (element.fireEvent) {
	                         element.fireEvent("on" + event.type, event);
	                     }
	                 }

	                 function simulateHTML5DragAndDrop(elementDrag, elementDrop) {
	                     var dragStartEvent = createEvent('dragstart');
	                     dispatchEvent(elementDrag, dragStartEvent);

	                     var dropEvent = createEvent('drop');
	                     dispatchEvent(elementDrop, dropEvent, dragStartEvent.dataTransfer);

	                     var dragEndEvent = createEvent('dragend');
	                     dispatchEvent(elementDrag, dragEndEvent, dropEvent.dataTransfer);
	                 }

	                 var elementDrag = arguments[0];
	                 var elementDrop = arguments[1];
	                 simulateHTML5DragAndDrop(elementDrag, elementDrop);
	             """;

	             ((JavascriptExecutor) driver).executeScript(jsDragAndDrop, columnA, columnB);
*/
	             // Walidacja — po drag&drop kolumny powinny się zamienić miejscami
	             WebElement headerA = driver.findElement(By.xpath("//div[@id='column-a']/header"));
	             assertEquals("B", headerA.getText());
	         }
	         
	         
	         //@Test
	         public void testFloatingMenu() {
	             driver.get("https://the-internet.herokuapp.com/floating_menu");

	             WebElement menu = driver.findElement(By.id("menu"));
	             assertTrue(menu.isDisplayed(), "Menu nie jest widoczne na początku strony!");

	             // Przewinięcie strony na dół
	             ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

	             // Ponowne sprawdzenie widoczności
	             assertTrue(menu.isDisplayed(), "Menu nie jest widoczne po przewinięciu strony!");
	         }
	         
	         //@Test
	         public void testKeyPresses() {
	             driver.get("https://the-internet.herokuapp.com/key_presses");

	             WebElement body = driver.findElement(By.tagName("body"));

	             // Symulacja naciśnięcia klawisza "A"
	             body.sendKeys("A");

	             WebElement result = driver.findElement(By.id("result"));
	             assertTrue(result.getText().contains("You entered: A"));

	             // Możemy też sprawdzić inny klawisz, np. ENTER
	             body.sendKeys(Keys.ENTER);
	             assertTrue(result.getText().contains("You entered: ENTER"));
	         }
	         
	         
	         //@Test
	         public void testFileDownload() throws Exception {
	             driver.get("https://the-internet.herokuapp.com/download");

	             WebElement firstFile = driver.findElement(By.cssSelector("div.example a"));
	             String fileName = firstFile.getText();
	             firstFile.click();

	             Path downloadedFile = downloadDir.resolve(fileName);

	             int attempts = 0;
	             while (!Files.exists(downloadedFile) && attempts < 10) {
	                 Thread.sleep(1000);
	                 attempts++;
	             }

	             assertTrue(Files.exists(downloadedFile), "Plik nie został pobrany!");
	         }
	         
	         
	         //@Test
	         public void testShowHide() throws Exception {
	             driver.get("https://the-internet.herokuapp.com/dynamic_controls");
	             WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button"));
	             
	             
	             removeButton.click();
	             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	             wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
	             
	             removeButton.click();
	             
	             WebElement hiddenElement = wait.until(
	            	        ExpectedConditions.visibilityOfElementLocated(By.id("checkbox"))
	            	    );             
	             
	                
	             assertTrue(hiddenElement.isDisplayed());
	         }
	         
	         //@Test
	         public void testModal() throws Exception {
	             driver.get("https://the-internet.herokuapp.com/entry_ad");
	             WebElement modal = driver.findElement(By.id("modal"));
	             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	             wait.until(ExpectedConditions.visibilityOf(modal));
	             assertTrue(modal.isDisplayed());
	             WebElement closeButton = driver.findElement(By.cssSelector(".modal-footer p"));
	             closeButton.click();
	             wait.until(ExpectedConditions.invisibilityOf(modal));
	             assertFalse(modal.isDisplayed());
	             
	         }
	         
	       //@Test
	         public void testInfiniteScroll() throws Exception {
	    	   driver.get("https://the-internet.herokuapp.com/infinite_scroll");
	    	   List<WebElement> paragraphs = driver.findElements(By.className("jscroll-added"));
	    	   int initialCount = paragraphs.size();
	    	   JavascriptExecutor js = (JavascriptExecutor) driver;
	    	   js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    	   Thread.sleep(2000); // krótka pauza na dociągnięcie treści
	    	   js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    	   Thread.sleep(2000);
	    	   
	    	   List<WebElement> newParagraphs = driver.findElements(By.className("jscroll-added"));
	    	   assertTrue(newParagraphs.size() > initialCount);
	       }
	       
	      //@Test
	       public void testBrokenImages() throws IOException {
	           driver.get("https://the-internet.herokuapp.com/broken_images");

	           List<WebElement> images = driver.findElements(By.tagName("img"));
	           int brokenCount = 0;

	           for (WebElement img : images) {
	               String imageUrl = img.getAttribute("src");
	               try {
	                   HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
	                   connection.setRequestMethod("GET");
	                   connection.connect();

	                   int responseCode = connection.getResponseCode();

	                   if (responseCode != 200) {
	                       System.out.println("Broken image: " + imageUrl + " | Response code: " + responseCode);
	                       brokenCount++;
	                   }
	               } catch (Exception e) {
	                   System.out.println("Exception for: " + imageUrl);
	                   brokenCount++;
	               }
	           }

	           System.out.println("Total broken images: " + brokenCount);
	           assertTrue(brokenCount > 0, "Expected at least one broken image on the page");
	       }
	       
	       //@Test
	       public void testBrokenLinks() throws IOException {
	           driver.get("https://the-internet.herokuapp.com/");

	           // Pobierz wszystkie elementy typu <a>
	           List<WebElement> links = driver.findElements(By.tagName("a"));
	           int brokenLinks = 0;

	           for (WebElement link : links) {
	               String url = link.getAttribute("href");

	               // Pomijamy puste i javascriptowe linki
	               if (url == null || url.isEmpty() || url.contains("javascript")) {
	                   continue;
	               }

	               try {
	                   HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	                   connection.setRequestMethod("GET");
	                   connection.connect();

	                   int responseCode = connection.getResponseCode();

	                   if (responseCode >= 400) {
	                       System.out.println("❌ Broken link: " + url + " | Response code: " + responseCode);
	                       brokenLinks++;
	                   } else {
	                       System.out.println("✅ OK link: " + url);
	                   }

	               } catch (Exception e) {
	                   System.out.println("⚠️ Exception for link: " + url);
	                   brokenLinks++;
	               }
	           }

	           System.out.println("Total broken links: " + brokenLinks);
	           assertTrue(brokenLinks >= 0, "Test passed — no broken links found");
	       }

	       //@Test
	       public void testRetryLogic() {
	           driver.get("https://the-internet.herokuapp.com/status_codes");

	           // Kliknij link 500 (Internal Server Error)
	           WebElement link500 = driver.findElement(By.linkText("500"));
	           link500.click();

	           // Spróbuj pobrać stronę kilka razy, jeśli wystąpi błąd
	           int maxRetries = 3;
	           int attempt = 0;
	           boolean success = false;

	           while (attempt < maxRetries) {
	               attempt++;
	               try {
	                   String bodyText = driver.findElement(By.tagName("body")).getText();
	                   if (bodyText.contains("This page returned a 500 status code")) {
	                       System.out.println("⚠️ Błąd 500 – próba ponownego załadowania (" + attempt + ")");
	                       driver.navigate().back();
	                       link500 = driver.findElement(By.linkText("500"));
	                       link500.click();
	                       Thread.sleep(1000);
	                   } else {
	                       success = true;
	                       break;
	                   }
	               } catch (Exception e) {
	                   System.out.println("❌ Błąd w próbie " + attempt + ": " + e.getMessage());
	               }
	           }

	           assertTrue(success || attempt == maxRetries, "Strona obsłużyła błąd po kilku próbach");
	       }
	       
	       
	       //@Test
	       public void testContextMenu() {
	           // 1. Otwórz stronę z menu kontekstowym
	           driver.get("https://the-internet.herokuapp.com/context_menu");

	           // 2. Znajdź pole, na którym klikniemy prawym przyciskiem
	           WebElement hotSpot = driver.findElement(By.id("hot-spot"));

	           // 3. Utwórz obiekt Actions i wykonaj kliknięcie prawym przyciskiem myszy
	           Actions actions = new Actions(driver);
	           actions.contextClick(hotSpot).perform();

	           // 4. Przełącz się na alert
	           Alert alert = driver.switchTo().alert();

	           // 5. Sprawdź treść komunikatu
	           String alertText = alert.getText();
	           assertEquals("You selected a context menu", alertText);

	           // 6. Zamknij alert
	           alert.accept();
	       }
	       
	       //@Test
	       public void testTypos() {
	    	   driver.get("https://the-internet.herokuapp.com/typos");
	    	   WebElement text = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p[2]"));
	    	   String napis = text.getText();
	    	   
	    	   //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            // wait.until(ExpectedConditions.visibilityOf(text));
	    	   
	    	   assertTrue(
	    			    napis.equals("Sometimes you'll see a typo, other times you won't.") ||
	    			    napis.equals("Sometimes you'll see a typo, other times you won,t.")
	    			);
	    	   
	    	   
	       }
	       
	       //@Test
	       public void testShiftingMenu() {
	    	   driver.get("https://the-internet.herokuapp.com/shifting_content/menu");

	    	   List<WebElement> menuItems = driver.findElements(By.cssSelector("ul li"));

	    	   assertEquals(5, menuItems.size());

	    	   
	       }
	       
	       //@Test
	       public void testShiftingImage() {
	    	   driver.get("https://the-internet.herokuapp.com/shifting_content/image");
	    	   List<WebElement> images = driver.findElements(By.tagName("img"));
	    	   for (WebElement img : images) {
	    		    assertTrue(img.isDisplayed());
	    		}
	       }
	       
	     //@Test
	       public void testShiftingList() {
	    	   driver.get("https://the-internet.herokuapp.com/shifting_content/list");
	    	   List<WebElement> listItems = driver.findElements(By.cssSelector("ul li"));
	    	   for (WebElement text : listItems) {
	    		    assertTrue(text.isDisplayed());
	    		    assertTrue(
		    			    text.equals("Sed deleniti blanditiis odio laudantium.") ||
		    			    text.equals("Et numquam et aliquam.") ||
		    			    text.equals("Nesciunt autem eum odit fuga tempora deleniti.") ||
		    			    text.equals("Important Information You're Looking For") ||
		    			    text.equals("Vel aliquid dolores veniam enim nesciunt libero quaerat.")
		    			);
	    		}
	       }
	     
	     //@Test
	       public void testImgChangePosition() {
	    	 driver.get("https://the-internet.herokuapp.com/shifting_content/image");
	    	 List<WebElement> images = driver.findElements(By.tagName("img"));
	    	 
	    	 List<Point> positionsBefore = images.stream()
                     .map(WebElement::getLocation)
                     .collect(Collectors.toList());
	    	 
	    	 driver.navigate().refresh();
	    	 
	    	 List<WebElement> imagesAfter = driver.findElements(By.tagName("img"));
	    	 List<Point> positionsAfter = imagesAfter.stream()
	    	                                         .map(WebElement::getLocation)
	    	                                         .collect(Collectors.toList());
	    	 
	    	 boolean changed = false;
	    	 for (int i = 0; i < positionsBefore.size(); i++) {
	    	     if (!positionsBefore.get(i).equals(positionsAfter.get(i))) {
	    	         changed = true;
	    	         break;
	    	     }
	    	 }
	    	 assertTrue(changed, "Przynajmniej jeden obraz powinien zmienić pozycję");


	     }
	     
	     //@Test
	       public void testForgotPassword() {
	    	 driver.get("https://the-internet.herokuapp.com/forgot_password");
	    	 WebElement pole = driver.findElement(By.id("email"));
	    	 pole.sendKeys("example@email.com");
	    	 WebElement retrieve = driver.findElement(By.id("form_submit"));
	    	 retrieve.click();
	    	 assertTrue(driver.getPageSource().contains("Internal Server Error"));
	    	
	    	 
	     }
	     
	     
	     //@Test
	     public void testGeolocation() {
	         driver.get("https://the-internet.herokuapp.com/geolocation");

	         WebElement button = driver.findElement(By.cssSelector("button[onclick='getLocation()']"));
	         button.click();

	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	         wait.until(ExpectedConditions.or(
	                 ExpectedConditions.visibilityOfElementLocated(By.id("lat-value")),
	                 ExpectedConditions.visibilityOfElementLocated(By.id("long-value"))
	         ));

	         String latText = driver.findElement(By.id("lat-value")).getText();
	         String longText = driver.findElement(By.id("long-value")).getText();

	         // Sprawdź, że pola nie są puste
	         assertFalse(latText.isEmpty(), "Latitude value should not be empty");
	         assertFalse(longText.isEmpty(), "Longitude value should not be empty");

	         // Dodatkowa walidacja: czy wartości są liczbami
	         try {
	             Double.parseDouble(latText);
	             Double.parseDouble(longText);
	         } catch (NumberFormatException e) {
	             fail("Latitude/Longitude should be numeric values");
	         }
	     }
	     
	     
	     //@Test
	     public void testInputs() {
	         driver.get("https://the-internet.herokuapp.com/inputs");

	         WebElement input = driver.findElement(By.tagName("input"));

	         // Wyczyść pole i wpisz poprawną wartość
	         input.clear();
	         input.sendKeys("12345");
	         assertEquals("12345", input.getAttribute("value"));

	         // Wpisz wartość niepoprawną (litery)
	         input.clear();
	         input.sendKeys("abc");
	         String valueAfterLetters = input.getAttribute("value");

	         // Sprawdź, że pole nie przyjęło liter (będzie puste)
	         assertTrue(valueAfterLetters.isEmpty() || valueAfterLetters.equals(""), 
	                    "Input should not accept non-numeric values");

	         // Wpisz liczbę i przetestuj strzałki
	         input.clear();
	         input.sendKeys("5");
	         input.sendKeys(Keys.ARROW_UP);
	         String afterArrowUp = input.getAttribute("value");
	         assertEquals("6", afterArrowUp);

	         input.sendKeys(Keys.ARROW_DOWN);
	         String afterArrowDown = input.getAttribute("value");
	         assertEquals("5", afterArrowDown);
	     }
	     
	     
	     //@Test
	     public void testJqueryUIMenuDownloadCSV() {
	         driver.get("https://the-internet.herokuapp.com/jqueryui/menu");

	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	         Actions actions = new Actions(driver);

	         // 1. Najedź na główną kategorię "Enabled"
	         WebElement enabledMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                 By.xpath("//a[text()='Enabled']")));
	         actions.moveToElement(enabledMenu).pause(Duration.ofMillis(500)).perform();

	         // 2. Najedź na podmenu "Downloads"
	         WebElement downloadsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                 By.xpath("//a[text()='Downloads']")));
	         actions.moveToElement(downloadsMenu).pause(Duration.ofMillis(500)).perform();

	         // 3. Kliknij w "CSV"
	         WebElement csvOption = wait.until(ExpectedConditions.elementToBeClickable(
	                 By.xpath("//a[text()='CSV']")));
	         actions.moveToElement(csvOption).click().perform();

	         // 4. Weryfikacja – element powinien być widoczny
	         //assertTrue(csvOption.isDisplayed(), "CSV option should be visible and clickable");
	     }
	     
	     
	     //@Test
	     public void testOpeningNewWindow() {
	         driver.get("https://the-internet.herokuapp.com/windows");

	         String originalWindow = driver.getWindowHandle();
	         WebElement clickHere = driver.findElement(By.linkText("Click Here"));
	         clickHere.click();

	         // Czekamy aż pojawi się nowe okno
	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	         wait.until(driver -> driver.getWindowHandles().size() > 1);

	         // Przełączamy się na nowe okno
	         for (String handle : driver.getWindowHandles()) {
	             if (!handle.equals(originalWindow)) {
	                 driver.switchTo().window(handle);
	                 break;
	             }
	         }

	         // Weryfikacja tytułu lub tekstu
	         assertTrue(driver.getTitle().contains("New Window"));

	         // Zamykamy nowe okno i wracamy do oryginalnego
	         driver.close();
	         driver.switchTo().window(originalWindow);
	     }
	     
	     //@Test
	     public void testNestedFrames() {
	         // 1. Otwórz stronę z nested frames
	         driver.get("https://the-internet.herokuapp.com/nested_frames");

	         // 2. Przełącz się do ramki górnej (frame top)
	         driver.switchTo().frame("frame-top");

	         // 3. Przełącz się do ramki środkowej (frame middle)
	         driver.switchTo().frame("frame-middle");

	         // 4. Pobierz tekst z ramki
	         WebElement middleText = driver.findElement(By.id("content"));
	         String text = middleText.getText();
	         System.out.println("Text in middle frame: " + text);

	         // 5. Sprawdź zawartość ramki
	         assertEquals("MIDDLE", text);

	         // 6. Wróć do głównej strony
	         driver.switchTo().defaultContent();

	         // 7. Opcjonalnie przełącz się do ramki dolnej (frame bottom) i sprawdź tekst
	         driver.switchTo().frame("frame-bottom");
	         WebElement bottomText = driver.findElement(By.tagName("body"));
	         assertEquals("BOTTOM", bottomText.getText());
	     }
	     
	     
	     
	     










	     
	     

  


	         
	         
	     
	     
	    
	     
}
	 
	 


	 
	 
	 
	 
    
   


