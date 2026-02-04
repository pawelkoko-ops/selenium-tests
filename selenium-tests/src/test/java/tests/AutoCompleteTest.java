package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import pages.AutoCompletePage;

public class AutoCompleteTest extends BaseTest{
	@Test
	public void testAutoComplete() {
	    driver.get("https://demoqa.com/auto-complete");
	    AutoCompletePage page = new AutoCompletePage(driver);

	    page.selectMultiple("Red");
	    assertTrue(page.isMultiSelected("Red"));

	    page.selectSingle("Blue");
	    assertEquals("Blue", page.getSingleSelected());
	}

}
