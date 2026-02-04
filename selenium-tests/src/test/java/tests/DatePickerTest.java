package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import pages.DatePickerPage;

public class DatePickerTest extends BaseTest{
	
	@Test
	public void testDatePicker() throws InterruptedException{
	    driver.get("https://demoqa.com/date-picker");

	    DatePickerPage page = new DatePickerPage(driver);

	    page.openDatePicker();
	    page.selectMonth("July");
	    page.selectYear("1984");
	    page.selectDay("30");

	    assertTrue(page.getSelectedDate().contains("07/30/1984"));
	    
	    page.openDateAndTimePicker();
	    page.selectMonth2("November");
	    page.selectYear2("2025");
	    page.selectDay2("28");
	    
	    //String selectedDateTime = dateTimeInput.getAttribute("value");
	    assertTrue(page.getSelectedDateAndTime().contains("November 28, 2025"), "Niepoprawna wartość daty i czasu");
	    
	    
	}

}
