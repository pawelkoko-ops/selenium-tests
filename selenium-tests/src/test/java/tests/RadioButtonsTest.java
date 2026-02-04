package tests;

import org.junit.jupiter.api.Test;

import pages.RadioButtonsPage;

import static org.junit.jupiter.api.Assertions.*;

public class RadioButtonsTest extends BaseTest {
@Test
public void testRadioButtons() {
    driver.get("https://demoqa.com/radio-button");

    RadioButtonsPage radioPage = new RadioButtonsPage(driver);

    radioPage.selectYes();
    assertEquals("Yes", radioPage.getResultText());

    radioPage.selectImpressive();
    assertEquals("Impressive", radioPage.getResultText());

    assertFalse(radioPage.isNoEnabled());
}
}
