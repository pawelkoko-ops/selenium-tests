package tests;

import org.junit.jupiter.api.Test;

import pages.ButtonsPage;

import static org.junit.jupiter.api.Assertions.*;

public class ButtonsTest extends BaseTest {
@Test
public void testButtons() {
    driver.get("https://demoqa.com/buttons");

    ButtonsPage buttonsPage = new ButtonsPage(driver);

    buttonsPage.clickDoubleClick();
    buttonsPage.clickRightClick();
    buttonsPage.clickDynamic();

    assertEquals("You have done a double click", buttonsPage.getDoubleClickMessage());
    assertEquals("You have done a right click", buttonsPage.getRightClickMessage());
    assertEquals("You have done a dynamic click", buttonsPage.getDynamicClickMessage());
}
}
