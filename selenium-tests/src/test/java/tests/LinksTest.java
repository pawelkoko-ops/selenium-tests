package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.RadioButtonsPage;
import pages.LinksPage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class LinksTest extends BaseTest{
	
	@Test
	public void testLinks() {
	    driver.get("https://demoqa.com/links");

	    LinksPage linksPage = new LinksPage(driver);
	    
	    linksPage.clickHomeLink();
	    String newTabUrl = linksPage.switchToNewTabAndGetUrl();
	    //sprawdzenie urla
	    assertTrue(newTabUrl.contains("demoqa.com"));
	    
	    //przejście do poprzedniego tabu
	    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(tabs.size() - 1)); // ostatni
	    
	    linksPage.clickCreatedLink();
	    //assertTrue(linksPage.getResponseMessage().contains("201"));
	    //assertTrue(driver.getPageSource().contains("Link has responded with staus"));
	    assertTrue(driver.getPageSource().contains("201"));
	    assertTrue(driver.getPageSource().contains("Created"));
	}

}
