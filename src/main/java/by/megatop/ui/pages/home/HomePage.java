package by.megatop.ui.pages.home;

import by.megatop.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage() {

    }

    public String getContactPhoneText() {
        logger.debug("Getting contact phone text");
        return DriverManager.getTextFromElement(HomeLocators.SPAN_CONTACT_PHONE);
    }

    public String getHeaderWomenTrendZoneCategoryText() {
        return DriverManager.getTextFromElement(HomeLocators.HEADER_WOMEN_TREND_ZONE);
    }

    public String getHeaderWomenShoesCategoryText() {
        return DriverManager.getTextFromElement(HomeLocators.HEADER_WOMEN_SHOES_CATEGORY);
    }

    public String getHeaderAccessoriesCategoryText() {
        return DriverManager.getTextFromElement(HomeLocators.HEADER_ACCESSORIES_CATEGORY);
    }

    public String getHeaderHaberdasheryCategoryText() {
        return DriverManager.getTextFromElement(HomeLocators.HEADER_HABERDASHERY_CATEGORY);
    }

    public String getHeaderDesignInKoreaCategoryText() {
        return DriverManager.getTextFromElementWhenVisible(HomeLocators.HEADER_DESIGN_IN_KOREA_CATEGORY);
    }
}
