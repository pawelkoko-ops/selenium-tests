package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import pages.ToolTipsPage;

public class ToolTipsTest extends BaseTest {
	@Test
	public void testToolTips() {
	    driver.get("https://demoqa.com/tool-tips");

	    ToolTipsPage page = new ToolTipsPage(driver);

	    Object[][] data = {
	            {By.id("toolTipButton"), "You hovered over the Button"},
	            {By.id("toolTipTextField"), "You hovered over the text field"},
	            {By.xpath("//a[text()='Contrary']"), "You hovered over the Contrary"},
	            {By.xpath("//a[text()='1.10.32']"), "You hovered over the 1.10.32"}
	    };

	    for (Object[] row : data) {
	        By locator = (By) row[0];
	        String expected = (String) row[1];

	        page.hover(locator);
	        page.waitForTooltipText(expected);
	        assertEquals(expected, page.getTooltipText());
	    }
	}

}
