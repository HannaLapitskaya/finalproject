package by.megatop.ui.pages.base;

import by.megatop.utils.WaitUtils;
import by.megatop.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {

    }

    public void openSite() {
        logger.debug("Opening website: " + BaseLocators.URL);
        DriverManager.getDriver().get(BaseLocators.URL);
        logger.info("Site opened successfully");
    }

    public void clickCityByLocation() {
        logger.debug("Clicking on 'Accept city' button");
        DriverManager.clickElementWhenClickable(BaseLocators.BUTTON_ACCEPT_CITY_BY_LOCATION);
        logger.info("City accepted by location successfully");
    }

    public void clickAcceptCookiesButton() {
        logger.debug("Clicking on 'Accept cookies' button");
        DriverManager.clickElementWhenClickable(BaseLocators.BUTTON_ACCEPT_COOKIES);
        WaitUtils.waitForPageToLoad();
        logger.info("Cookies accepted");
    }

    public void clickCityByLocationAndAcceptCookies() {
        clickCityByLocation();
        clickAcceptCookiesButton();
    }

    public void clickWomanCategory() {
        logger.debug("Clicking on 'Woman' category button");
        DriverManager.clickElementWhenClickableWithRetry(BaseLocators.BUTTON_WOMAN_CATEGORY);
        logger.info("Woman category clicked");
    }
}
