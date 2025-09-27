package by.megatop.ui.pages;

import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {

    private final String SPAN_CONTACT_PHONE = "//span[@class='my-auto']";

    private static final Logger logger = LogManager.getLogger();

    public HomePage() {

    }

    public String getContactPhoneText() {
        logger.info("Getting contact phone text");
        return DriverManager.getTextFromElement(SPAN_CONTACT_PHONE);
    }
}
