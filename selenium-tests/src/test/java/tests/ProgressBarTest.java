package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import pages.ProgressBarPage;

public class ProgressBarTest extends BaseTest{
	@Test
	public void testProgressBar() {

	    driver.get("https://demoqa.com/progress-bar");

	    ProgressBarPage page = new ProgressBarPage(driver);

	    page.start();
	    page.waitUntilFinished();
	    page.waitUntilButtonIsReset();
	    page.reset();
	    page.waitUntilZero();
	}
}