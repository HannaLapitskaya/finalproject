package by.megatop.ui.pages;

import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {

    private static final Logger logger = LogManager.getLogger();
    private static final String SPAN_CONTACT_PHONE = "//span[@class='my-auto']";
    private static final String HEADER_WOMEN_TREND_ZONE = "(//a[@class='categories__title font-weight-medium'])[1]";
    private static final String HEADER_WOMEN_SHOES_CATEGORY = "(//a[@class='categories__title font-weight-medium'])[2]";
    private static final String HEADER_ACCESSORIES_CATEGORY = "(//a[@class='categories__title font-weight-medium'])[3]";
    private static final String HEADER_HABERDASHERY_CATEGORY= "(//a[@class='categories__title font-weight-medium'])[4]";
    private static final String HEADER_DESIGN_IN_KOREA_CATEGORY = "(//a[@class='categories__title font-weight-medium'])[5]";

    public HomePage() {

    }

    public String getContactPhoneText() {
        logger.debug("Getting contact phone text");
        return DriverManager.getTextFromElement(SPAN_CONTACT_PHONE);
    }

    public String getHeaderWomenTrendZoneCategoryText() {
        return DriverManager.getTextFromElement(HEADER_WOMEN_TREND_ZONE);
    }

    public String getHeaderWomenShoesCategoryText() {
        return DriverManager.getTextFromElement(HEADER_WOMEN_SHOES_CATEGORY);
    }

    public String getHeaderAccessoriesCategoryText() {
        return DriverManager.getTextFromElement(HEADER_ACCESSORIES_CATEGORY);
    }

    public String getHeaderHaberdasheryCategoryText() {
        return DriverManager.getTextFromElement(HEADER_HABERDASHERY_CATEGORY);
    }

    public String getHeaderDesignInKoreaCategoryText() {
        return DriverManager.getTextFromElementWhenVisible(HEADER_DESIGN_IN_KOREA_CATEGORY);
    }
}
