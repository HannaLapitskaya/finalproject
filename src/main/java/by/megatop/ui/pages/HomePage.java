package by.megatop.ui.pages;

import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {

    private final String URL = "https://megatop.by/";
    private final String BUTTON_ACCEPT_CITY_BY_LOCATION = "//button[@class='btn-gray v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default' and contains(.//text(), 'Да')]";
    private final String BUTTON_ACCEPT_COOKIES = "//button[@class='btn-black ml-3 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']";
    private final String BUTTON_WOMAN_CATEGORY = "//a[contains(text(), 'Женщины')]";
    private final String SPAN_CONTACT_PHONE = "//span[@class='my-auto']";
    private final String BUTTON_UNSUBSCRIBE_EMAIL = "//a[contains(text(), 'Отписаться от email рассылки')]";

    private static final Logger logger = LogManager.getLogger();

    public HomePage() {

    }

    public void openSite() {
        DriverManager.getDriver().get(URL);
        logger.info("Site opened successfully");
    }

    public void clickCityByLocation() {
        logger.info("Clicking on 'Accept city' button");
        DriverManager.clickElement(BUTTON_ACCEPT_CITY_BY_LOCATION);
    }

    public void clickAcceptCookiesButton() {
        logger.info("Clicking on 'Accept cookies' button");
        DriverManager.clickElement(BUTTON_ACCEPT_COOKIES);
    }

    public void clickWomanCategory() {
        logger.info("Clicking on 'Woman' category button");
        WaitUtils.waitForElementVisible(BUTTON_WOMAN_CATEGORY);
        WaitUtils.waitForElementClickable(BUTTON_WOMAN_CATEGORY);
        DriverManager.clickElement(BUTTON_WOMAN_CATEGORY);
    }

    public String getContactPhoneText() {
        logger.info("Getting contact phone text");
        return DriverManager.getTextFromElement(SPAN_CONTACT_PHONE);
    }

    public void clickButtonUnsubscribeFromEmail() {
        logger.info("Clicking on 'Unsubscribe from email' button");
        DriverManager.clickElement(BUTTON_UNSUBSCRIBE_EMAIL);
    }
}
