package by.megatop.ui;

import by.megatop.ui.pages.base.BasePage;
import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected BasePage basePage;

    @BeforeEach
    public void setups() {
        basePage = new BasePage();

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
