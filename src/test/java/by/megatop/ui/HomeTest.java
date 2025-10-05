package by.megatop.ui;

import by.megatop.ui.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Home page functionality tests")
public class HomeTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    @DisplayName("The presence of a contact phone number")
    public void contactPhoneNumberShouldBeDisplayed() {
        logger.debug("Starting contact phone number verification test");
        HomePage homePage = new HomePage();

        String expectedResult = "7976";
        String actualResult = homePage.getContactPhoneText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'The presence of a contact phone number' passed successfully");
    }
}
