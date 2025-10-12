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
        String phoneText = DriverManager.getTextFromElement(HomeLocators.SPAN_CONTACT_PHONE);
        logger.info("Retrieved contact phone text: {}", phoneText);
        return phoneText;
    }

    public String getHeaderWomenTrendZoneCategoryText() {
        logger.debug("Getting Women Trend Zone category header text");
        String headerText = DriverManager.getTextFromElementWhenVisible(HomeLocators.HEADER_WOMEN_TREND_ZONE);
        logger.info("Retrieved Women Trend Zone header: {}", headerText);
        return headerText;
    }

    public String getHeaderWomenShoesCategoryText() {
        logger.debug("Getting Women Shoes category header text");
        String headerText = DriverManager.getTextFromElementWhenVisible(HomeLocators.HEADER_WOMEN_SHOES_CATEGORY);
        logger.info("Retrieved Women Shoes header: {}", headerText);
        return headerText;
    }

    public String getHeaderAccessoriesCategoryText() {
        logger.debug("Getting Accessories category header text");
        String headerText = DriverManager.getTextFromElementWhenVisible(HomeLocators.HEADER_ACCESSORIES_CATEGORY);
        logger.info("Retrieved Accessories header: {}", headerText);
        return headerText;
    }

    public String getHeaderHaberdasheryCategoryText() {
        logger.debug("Getting Haberdashery category header text");
        String headerText = DriverManager.getTextFromElementWhenVisibleWithRetry(HomeLocators.HEADER_HABERDASHERY_CATEGORY);
        logger.info("Retrieved Haberdashery header: {}", headerText);
        return headerText;
    }

    public String getHeaderDesignInKoreaCategoryText() {
        logger.debug("Getting Design In Korea category header text");
        String headerText = DriverManager.getTextFromElementWhenVisible(HomeLocators.HEADER_DESIGN_IN_KOREA_CATEGORY);
        logger.info("Retrieved Design In Korea header: {}", headerText);
        return headerText;
    }
}
