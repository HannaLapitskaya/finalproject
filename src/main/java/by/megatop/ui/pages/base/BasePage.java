package by.megatop.ui.pages.base;

import by.megatop.ui.pages.promo.PromoLocators;
import by.megatop.utils.WaitUtils;
import by.megatop.webdriver.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public void clickCityByLocationAndAcceptCookies() {
        clickCityByLocation();
        clickAcceptCookiesButton();
    }

    private void clickCityByLocation() {
        logger.debug("Clicking on 'Accept city' button");
        DriverManager.clickElementWhenClickable(PromoLocators.BUTTON_ACCEPT_CITY_BY_LOCATION);
        logger.info("City accepted by location successfully");
    }

    private void clickAcceptCookiesButton() {
        logger.debug("Clicking on 'Accept cookies' button");
        DriverManager.clickElementWhenClickable(PromoLocators.BUTTON_ACCEPT_COOKIES);
        WaitUtils.waitForPageToLoad();
        logger.info("Cookies accepted");
    }
}
