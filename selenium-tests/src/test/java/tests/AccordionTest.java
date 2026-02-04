package tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import pages.AccordionPage;

public class AccordionTest extends BaseTest{
	@Test
	public void testAccordion() {
	    driver.get("https://demoqa.com/accordian");
	    AccordionPage page = new AccordionPage(driver);

	    page.scrollToSection("section1Heading");
	    page.clickSection("section1Heading");

	    String content = page.getSectionContent("section1Content");
	    assertTrue(content.contains("Lorem Ipsum is simply dummy text"));
	}

}
