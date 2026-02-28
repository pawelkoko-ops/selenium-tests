package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EverMedLogoTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }


    @Test
    public void testLogoIsDisplayed() {
        driver.get("https://www.ever-med.pl");
        WebElement logo = driver.findElement(By.cssSelector("img[src='/images/logo.png']"));
        logo.isDisplayed();

        assertTrue(logo.isDisplayed(), "Logo nie jest widoczne na stronie głównej");
    }
    
    @Test
    public void testKontaktLink() throws InterruptedException {
        driver.get("https://www.ever-med.pl");
        WebElement kontaktLink = driver.findElement(By.linkText("kontakt"));
        kontaktLink.click();
        Thread.sleep(2000);
        assertTrue(driver.getCurrentUrl().contains("kontakt"));
    }
    
    @Test
    public void testKontakt() throws InterruptedException {
        driver.get("https://www.ever-med.pl/kontakt.html");
        WebElement kontakt = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[4]/table/tbody/tr/td/p"));
        assertTrue(kontakt.isDisplayed() && kontakt.getText().contains("58 668 07 87"));
        assertTrue(kontakt.isDisplayed() && kontakt.getText().contains("506 692 013"));
    }
    
    @Test
    public void testMenuLinks() {
        driver.get("https://www.ever-med.pl");

        // Zlokalizuj link "Kontakt" po selektorze CSS
        WebElement kontaktLink = driver.findElement(By.xpath("//a[contains(text(),'kontakt')]"));

        // Sprawdź, czy link jest widoczny
        assertTrue(kontaktLink.isDisplayed(), "Link 'Kontakt' nie jest widoczny");

        // Kliknij w link
        kontaktLink.click();

        // Sprawdź, czy URL zawiera słowo "kontakt"
        assertTrue(driver.getCurrentUrl().contains("kontakt"), "Nie przeniesiono do strony kontaktowej");

        // Wróć do strony głównej
        driver.navigate().back();
        
        // Zlokalizuj link "O nas" po selektorze CSS
        WebElement onasLink = driver.findElement(By.xpath("//a[contains(text(),'O nas')]"));

        // Sprawdź, czy link jest widoczny
        assertTrue(onasLink.isDisplayed(), "Link 'O nas' nie jest widoczny");

        // Kliknij w link
        onasLink.click();

        // Sprawdź, czy URL zawiera słowo "index"
        assertTrue(driver.getCurrentUrl().contains("index"), "Nie przeniesiono do strony o nas");

        // Wróć do strony głównej
        driver.navigate().back();
        
        // Zlokalizuj link "cennik" po selektorze CSS
        WebElement cennikLink = driver.findElement(By.xpath("//a[contains(text(),'cennik')]"));

        // Sprawdź, czy link jest widoczny
        assertTrue(cennikLink.isDisplayed(), "Link 'cennik' nie jest widoczny");

        // Kliknij w link
        cennikLink.click();

        // Sprawdź, czy URL zawiera słowo "index"
        assertTrue(driver.getCurrentUrl().contains("cennik"), "Nie przeniesiono do strony cennik");

        // Wróć do strony głównej
        driver.navigate().back();
                
    }
    
    @Test
    public void testOnas() throws InterruptedException {
        driver.get("https://www.ever-med.pl/index.html");
        WebElement Onas = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[4]/p[1]"));
        assertTrue(Onas.isDisplayed() && Onas.getText().contains("Jesteśmy prywatną poradnią rehabilitacyjną. Posiadamy ponad dziesięcioletnie doświadczenie w pracy z pacjentami. Wykorzystujemy metody terapii manualnej, chiropraktyki, fizykoterapii, kinezyterapii oraz masażu. Do każdego pacjenta podchodzimy w sposób indywidualny, ponieważ wiemy, że takie podejście zwiększa sukcesy w leczeniu i pozwala dobrać odpowiednie metody terapii do konkretnego przypadku. Jeżeli cierpisz z powodu dolegliwości bólowych:"));
     }
    
    
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
