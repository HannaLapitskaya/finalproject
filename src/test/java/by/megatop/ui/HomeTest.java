package by.megatop.ui;

import by.megatop.ui.pages.cart.CartPage;
import by.megatop.ui.pages.home.HomePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("UI Testing")
@Feature("Home Page")
@Story("Home Page Content Verification")
@DisplayName("UI home functionality tests")
public class HomeTest extends BaseTest {

    @Test
    @DisplayName("The presence of a contact phone number")
    @Description("Test verifies that contact phone number is displayed on home page")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-01")
    public void contactPhoneNumberShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "7976";

        Assertions.assertEquals(expectedResult, homePage.getContactPhoneText());
    }

    @Test
    @DisplayName("Design in Korea category header should be displayed")
    @Description("Test verifies that 'Design in Korea' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-02")
    public void designInKoreaCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Design in Korea";

        Assertions.assertEquals(expectedResult, homePage.getHeaderDesignInKoreaCategoryText());
    }

    @Test
    @DisplayName("Trend Zone category header should be displayed")
    @Description("Test verifies that 'Trend Zone' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-03")
    public void trendZoneCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Зона трендов";

        Assertions.assertEquals(expectedResult, homePage.getHeaderWomenTrendZoneCategoryText());
    }

    @Test
    @DisplayName("Accessories category header should be displayed")
    @Description("Test verifies that 'Accessories' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-04")
    public void accessoriesCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Аксессуары";

        Assertions.assertEquals(expectedResult, homePage.getHeaderAccessoriesCategoryText());
    }

    @Test
    @DisplayName("Women Shoes category header should be displayed")
    @Description("Test verifies that 'Women Shoes' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-05")
    public void womenShoesCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Обувь";

        Assertions.assertEquals(expectedResult, homePage.getHeaderWomenShoesCategoryText());
    }

    @Test
    @DisplayName("Haberdashery category header should be displayed")
    @Description("Test verifies that 'Haberdashery' category header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("HM-06")
    public void haberdasheryCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Галантерея";

        Assertions.assertEquals(expectedResult, homePage.getHeaderHaberdasheryCategoryText());
    }
}
