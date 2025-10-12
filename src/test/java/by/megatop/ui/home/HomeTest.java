package by.megatop.ui.home;

import by.megatop.ui.pages.home.HomeExpectedMessages;
import by.megatop.ui.pages.home.HomePage;
import by.megatop.ui.promo.PromoTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("UI Testing")
@Feature("Home Page")
@Story("Home Page Content Verification")
@DisplayName("UI home functionality tests")
public class HomeTest extends PromoTest {

    private HomePage homePage;

    @BeforeEach
    public void openCartPage() {
        homePage = new HomePage();
    }

    @Test
    @DisplayName("The presence of a contact phone number")
    @Description("Test verifies that contact phone number is displayed on home page")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-01")
    public void contactPhoneNumberShouldBeDisplayed() {
        Assertions.assertEquals(HomeExpectedMessages.CONTACT_PHONE_NUMBER, homePage.getContactPhoneText());
    }

    @Test
    @DisplayName("Design in Korea category header should be displayed")
    @Description("Test verifies that 'Design in Korea' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-02")
    public void designInKoreaCategoryHeaderShouldBeDisplayed() {
        Assertions.assertEquals(HomeExpectedMessages.DESIGN_IN_KOREA_HEADER, homePage.getHeaderDesignInKoreaCategoryText());
    }

    @Test
    @DisplayName("Trend Zone category header should be displayed")
    @Description("Test verifies that 'Trend Zone' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-03")
    public void trendZoneCategoryHeaderShouldBeDisplayed() {
        Assertions.assertEquals(HomeExpectedMessages.TREND_ZONE_HEADER, homePage.getHeaderWomenTrendZoneCategoryText());
    }

    @Test
    @DisplayName("Accessories category header should be displayed")
    @Description("Test verifies that 'Accessories' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-04")
    public void accessoriesCategoryHeaderShouldBeDisplayed() {
        Assertions.assertEquals(HomeExpectedMessages.ACCESSORIES_HEADER, homePage.getHeaderAccessoriesCategoryText());
    }

    @Test
    @DisplayName("Women Shoes category header should be displayed")
    @Description("Test verifies that 'Women Shoes' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-05")
    public void womenShoesCategoryHeaderShouldBeDisplayed() {
        Assertions.assertEquals(HomeExpectedMessages.WOMEN_SHOES_HEADER, homePage.getHeaderWomenShoesCategoryText());
    }

    @Test
    @DisplayName("Haberdashery category header should be displayed")
    @Description("Test verifies that 'Haberdashery' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-06")
    public void haberdasheryCategoryHeaderShouldBeDisplayed() {
        Assertions.assertEquals(HomeExpectedMessages.HABERDASHERY_HEADER, homePage.getHeaderHaberdasheryCategoryText());
    }
}
