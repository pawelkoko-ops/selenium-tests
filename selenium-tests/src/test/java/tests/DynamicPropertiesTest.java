package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import pages.DynamicPropertiesPage;

public class DynamicPropertiesTest extends BaseTest {
	@Test
	public void testDynamicProperties() {
	    driver.get("https://demoqa.com/dynamic-properties");

	    DynamicPropertiesPage page = new DynamicPropertiesPage(driver);

	    String initialColor = page.getInitialColor();

	    page.waitUntilButtonEnabled();
	    String changedColor = page.getChangedColor();

	    assertNotEquals(initialColor, changedColor);

	    page.waitUntilVisibleAppears();
	    assertTrue(page.isVisibleAfterDisplayed());
	}

}
