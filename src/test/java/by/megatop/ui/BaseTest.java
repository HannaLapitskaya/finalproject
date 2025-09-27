package by.megatop.ui;

import by.megatop.ui.pages.BasePage;
import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected BasePage basePage;

    @BeforeEach
    public void setups() {
        basePage = new BasePage();
        logger.info("HomePage object created");

        basePage.openSite();
        logger.info("Site opened successfully");

        basePage.clickCityByLocation();
        logger.info("City selection clicked");

        basePage.clickAcceptCookiesButton();
        logger.info("Cookies accepted");

        basePage.clickWomanCategory();
        logger.info("Woman category selected");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quit();
        logger.info("Driver quit successfully");
    }
}
