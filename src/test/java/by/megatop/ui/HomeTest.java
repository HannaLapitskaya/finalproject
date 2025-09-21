package by.megatop.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    @DisplayName("The presence of a contact phone number")
    public void contactPhoneNumberShouldBeDisplayed() {
        logger.info("Starting contact phone number verification test");

        String expectedResult = "7976";
        String actualResult = homePage.getContactPhoneText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'The presence of a contact phone number' passed successfully");
    }
}
