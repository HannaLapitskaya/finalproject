package by.megatop.ui.base;

import by.megatop.ui.pages.base.BasePage;
import by.megatop.webdriver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected BasePage basePage;

    @BeforeEach
    public void setups() {
        basePage = new BasePage();

        basePage.openSite();
        basePage.clickCityByLocationAndAcceptCookies();
        basePage.clickWomanCategory();
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quit();
    }
}
