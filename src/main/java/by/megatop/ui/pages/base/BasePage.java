package by.megatop.ui.pages.base;

import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {

    private static final Logger logger = LogManager.getLogger();
    private static final String URL = "https://megatop.by/";
    private static final String BUTTON_ACCEPT_CITY_BY_LOCATION = "//button[@class='btn-gray v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default' and contains(.//text(), 'Да')]";
    private static final String BUTTON_ACCEPT_COOKIES = "//button[@class='btn-black ml-3 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']";
    private static final String BUTTON_WOMAN_CATEGORY = "(//a[@class='main__btn text-uppercase white--text text-center mr-12'])[1]";

    public BasePage() {

    }

    public void openSite() {
        logger.debug("Opening website: " + URL);
        DriverManager.getDriver().get(URL);
        logger.info("Site opened successfully");
    }

    public void clickCityByLocation() {
        logger.debug("Clicking on 'Accept city' button");
        DriverManager.clickElementWhenClickable(BUTTON_ACCEPT_CITY_BY_LOCATION);
        logger.info("City accepted by location successfully");
    }

    public void clickAcceptCookiesButton() {
        logger.debug("Clicking on 'Accept cookies' button");
        DriverManager.clickElementWhenClickable(BUTTON_ACCEPT_COOKIES);
        logger.info("Cookies accepted");
    }

    public void clickWomanCategory() {
        logger.debug("Clicking on 'Woman' category button");
        DriverManager.clickElementWhenClickableWithRetry(BUTTON_WOMAN_CATEGORY);
        logger.info("Woman category clicked");
    }
}
