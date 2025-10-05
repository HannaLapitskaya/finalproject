package by.megatop.ui;

import by.megatop.ui.pages.BasePage;
import by.megatop.ui.utils.WaitUtils;
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
        basePage.clickCityByLocation();
        basePage.clickAcceptCookiesButton();
        WaitUtils.waitForPageToLoad();
        basePage.clickWomanCategory();
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quit();
    }
}
