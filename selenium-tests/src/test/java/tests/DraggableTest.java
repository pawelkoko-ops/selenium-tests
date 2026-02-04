package tests;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import pages.DraggablePage;

public class DraggableTest extends BaseTest{
	@Test
	public void testDraggable() {
	    driver.get("https://demoqa.com/dragabble");
	    DraggablePage page = new DraggablePage(driver);

	    Point start = page.getPosition();
	    page.moveByOffset(10, 10);
	    Point end = page.getPosition();
	    assertNotEquals(start, end);
	}

}
