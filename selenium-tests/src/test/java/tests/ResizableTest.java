package tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import pages.ResizablePage;

public class ResizableTest extends BaseTest{
	@Test
	public void testResizable() {
	    driver.get("https://demoqa.com/resizable");
	    ResizablePage page = new ResizablePage(driver);

	    page.scrollToBox();
	    Dimension startSize = page.getBoxSize();
	    page.resizeBox(300, 150);
	    Dimension endSize = page.getBoxSize();

	    assertTrue(endSize.getWidth() <= 500);
	    assertTrue(endSize.getHeight() <= 300);
	}

}
