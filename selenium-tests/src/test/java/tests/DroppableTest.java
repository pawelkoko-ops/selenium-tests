package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import pages.DroppablePage;

public class DroppableTest extends BaseTest{
	@Test
	public void testDroppable() throws InterruptedException {
	    driver.get("https://demoqa.com/droppable");
	    DroppablePage page = new DroppablePage(driver);

	    // Simple
	    page.clickTab("simple");
	    page.dragSimple();
	    assertEquals("Dropped!", page.getDroppableText());

	    // Accept
	    page.clickTab("accept");
	    page.dragToAccept(page.getNotAcceptable());
	    assertEquals("Drop here", page.getAcceptDropText());
	    page.dragToAccept(page.getAcceptable());
	    assertEquals("Dropped!", page.getAcceptDropText());

	    // Revert
	    page.clickTab("revert");
	    Point afterNot = page.dragNotRevertable();
	    assertNotEquals(afterNot, afterNot); // powinna nie wrócić
	    Point afterRev = page.dragRevertable();
	    assertEquals(afterRev, afterRev); // powinna wrócić
	}

}
