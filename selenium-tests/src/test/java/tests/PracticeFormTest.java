package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import static org.junit.jupiter.api.Assertions.*;

public class PracticeFormTest extends BaseTest {

    @Test
    public void testPracticeForm() {
        PracticeFormPage form = new PracticeFormPage(driver);

        form.open();
        form.fillPersonalData("Paweł", "Koko", "pawel.koko@gmail.com", "5068007501");
        form.selectGenderMale();
        form.setBirthDate("1984", "July");
        form.selectSubject("English");
        form.uploadPicture("C:\\Users\\Dell\\Desktop\\avocado\\1.jpg");
        form.selectState("NCR");
        form.selectCity("Delhi");
        form.submitForm();

        assertTrue(form.isModalVisible());
    }
}
