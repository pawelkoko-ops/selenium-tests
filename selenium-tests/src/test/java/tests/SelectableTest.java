package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import pages.SelectablePage;

public class SelectableTest extends BaseTest {
	@Test
	public void testSelectable() {
	    driver.get("https://demoqa.com/selectable");
	    SelectablePage page = new SelectablePage(driver);

	    page.clickListTab();
	    page.clickItem(1);
	    assertTrue(page.isActive(1));
	    assertFalse(page.isActive(2));

	    page.clickItem(2);
	    assertTrue(page.isActive(2));
	}
	

}
