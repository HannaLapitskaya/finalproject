package by.megatop.ui.pages;

import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {

    private static final Logger logger = LogManager.getLogger();
    private static final String SPAN_CONTACT_PHONE = "//span[@class='my-auto']";

    public HomePage() {

    }

    public String getContactPhoneText() {
        logger.debug("Getting contact phone text");
        return DriverManager.getTextFromElement(SPAN_CONTACT_PHONE);
    }
}
