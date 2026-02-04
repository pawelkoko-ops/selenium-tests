package tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import pages.SliderPage;

public class SliderTest extends BaseTest{
	
	@Test
	public void testSlider() {
	    driver.get("https://demoqa.com/slider");

	    SliderPage sliderPage = new SliderPage(driver);

	    sliderPage.moveSlider(50); // przesunięcie w prawo
	    int value = Integer.parseInt(sliderPage.getSliderValue());

	    assertTrue(value > 25);
	}

}
