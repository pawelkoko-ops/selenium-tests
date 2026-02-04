package tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import pages.MenuPage;

public class MenuTest extends BaseTest{
	@Test
	public void testMenu() {
	    driver.get("https://demoqa.com/menu");
	    MenuPage page = new MenuPage(driver);

	    assertTrue(page.isDisplayedMainItem("Main Item 1"));
	    assertTrue(page.isDisplayedMainItem("Main Item 2"));
	    assertTrue(page.isDisplayedMainItem("Main Item 3"));

	    page.hoverMainItem("Main Item 2");
	    page.hoverSubItem("SUB SUB LIST »");

	    assertTrue(page.isDisplayedSubItem("Sub Sub Item 2"));
	    assertTrue(page.isEnabledSubItem("Sub Sub Item 2"));

	    page.clickSubItem("Sub Sub Item 2");
	}

}
