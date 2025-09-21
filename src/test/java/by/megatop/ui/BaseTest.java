package by.megatop.ui;

import by.megatop.ui.pages.HomePage;
import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
    protected HomePage homePage;

    @BeforeEach
    public void setups() {
        homePage = new HomePage();
        LOGGER.info("HomePage object created");

        homePage.openSite();
        LOGGER.info("Site opened successfully");

        homePage.clickCityByLocation();
        LOGGER.info("City selection clicked");

        homePage.clickAcceptCookiesButton();
        LOGGER.info("Cookies accepted");

        homePage.clickWomanCategory();
        LOGGER.info("Woman category selected");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quit();
        LOGGER.info("Driver quit successfully");
    }
}
