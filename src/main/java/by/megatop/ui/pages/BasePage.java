package by.megatop.ui.pages;

import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {

    private static final Logger logger = LogManager.getLogger();
    private static final String URL = "https://megatop.by/";
    private static final String BUTTON_ACCEPT_CITY_BY_LOCATION = "//button[@class='btn-gray v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default' and contains(.//text(), 'Да')]";
    private static final String BUTTON_ACCEPT_COOKIES = "//button[@class='btn-black ml-3 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']";
    private static final String BUTTON_WOMAN_CATEGORY = "//a[contains(text(), 'Женщины')]";

    public BasePage() {

    }

    public void openSite() {
        DriverManager.getDriver().get(URL);
        logger.info("Site opened successfully");
    }

    public void clickCityByLocation() {
        logger.info("Clicking on 'Accept city' button");
        WaitUtils.waitForElementClickable(BUTTON_ACCEPT_CITY_BY_LOCATION);
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
}
