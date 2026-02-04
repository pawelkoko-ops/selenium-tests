package tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import pages.TabsPage;

public class TabsTest extends BaseTest{
	@Test
	public void testTabs() {
	    driver.get("https://demoqa.com/tabs");
	    TabsPage page = new TabsPage(driver);

	    page.clickTab("What");
	    page.clickTab("Origin");
	    page.clickTab("Use");

	    assertTrue(page.isMoreTabDisabled());
	}

}
