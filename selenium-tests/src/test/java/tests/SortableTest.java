package tests;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import pages.SortablePage;

public class SortableTest extends BaseTest {
	@Test
	public void testSortable() {
	    driver.get("https://demoqa.com/sortable");
	    SortablePage page = new SortablePage(driver);
	    

	    // LIST
	    page.clickListTab();
	    List<String> beforeList = page.getListOrder();
	    page.dragListItem("One", "Three");
	    List<String> afterList = page.getListOrder();
	    assertNotEquals(beforeList, afterList);

	    // GRID
	    page.clickGridTab();
	    List<String> beforeGrid = page.getGridOrder();
	    page.dragGridItem("One", "Nine");
	    List<String> afterGrid = page.getGridOrder();
	    assertNotEquals(beforeGrid, afterGrid);
	}

}
