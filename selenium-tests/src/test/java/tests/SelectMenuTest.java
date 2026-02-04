package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pages.SelectMenuPage;

public class SelectMenuTest extends BaseTest{
	@Test
	public void testSelectMenu() {
	    driver.get("https://demoqa.com/select-menu");
	    SelectMenuPage page = new SelectMenuPage(driver);

	    page.selectOldStyle("Purple");
	    assertEquals("Purple", page.getOldSelectValue());

	    page.selectReactValue("Group 1, option 2");
	    assertEquals("Group 1, option 2", page.getSelectedReactValue());

	    page.selectReactOne("Dr.");
	    assertEquals("Dr.", page.getSelectedReactOne());

	    page.selectMulti("Blue", "Green");
	    List<String> multi = page.getMultiSelected();
	    assertTrue(multi.contains("Blue"));
	    assertTrue(multi.contains("Green"));

	    // standard multi <select>
	    WebElement cars = driver.findElement(By.id("cars"));
	    page.selectMultipleOld(cars, "Volvo", "Saab");
	    List<String> selectedCars = page.getMultipleOldSelected(cars);
	    assertEquals(List.of("Volvo", "Saab"), selectedCars);
	}

}
