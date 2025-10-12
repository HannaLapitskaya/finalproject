package by.megatop.ui.pages.promo;

import by.megatop.ui.pages.base.BasePage;
import by.megatop.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PromoPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(PromoPage.class);

    public PromoPage() {

    }

    public void openSite() {
        logger.debug("Opening website: " + PromoLocators.URL);
        DriverManager.getDriver().get(PromoLocators.URL);
        logger.info("Site opened successfully");
    }

    public void clickWomanCategory() {
        logger.debug("Clicking on 'Woman' category button");
        DriverManager.clickElementWhenClickableWithRetry(PromoLocators.BUTTON_WOMAN_CATEGORY);
        logger.info("Woman category clicked");
    }
}
