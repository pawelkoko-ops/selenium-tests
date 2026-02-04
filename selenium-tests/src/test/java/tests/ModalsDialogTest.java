package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import pages.ModalDialogsPage;

public class ModalsDialogTest extends BaseTest{
	@Test
	public void testModalDialogs() {
	    driver.get("https://demoqa.com/modal-dialogs");
	    ModalDialogsPage page = new ModalDialogsPage(driver);

	    page.openSmallModal();
	    assertEquals("This is a small modal. It has very less content", page.getSmallModalText());
	}

}
