package by.megatop.ui.promo;

import by.megatop.ui.pages.promo.PromoPage;
import by.megatop.webdriver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PromoTest {

    protected PromoPage basePage;

    @BeforeEach
    public void setups() {
        basePage = new PromoPage();

        basePage.openSite();
        basePage.clickCityByLocationAndAcceptCookies();
        basePage.clickWomanCategory();
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quit();
    }
}
