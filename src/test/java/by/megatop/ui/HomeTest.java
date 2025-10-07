package by.megatop.ui;

import by.megatop.ui.pages.home.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Home page UI tests")
public class HomeTest extends BaseTest {


    @Test
    @DisplayName("The presence of a contact phone number")
    public void contactPhoneNumberShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "7976";
        String actualResult = homePage.getContactPhoneText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Design in Korea category header should be displayed")
    public void designInKoreaCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Design in Korea";
        String actualResult = homePage.getHeaderDesignInKoreaCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Trend Zone category header should be displayed")
    public void trendZoneCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Зона трендов";
        String actualResult = homePage.getHeaderWomenTrendZoneCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Accessories category header should be displayed")
    public void accessoriesCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Аксессуары";
        String actualResult = homePage.getHeaderAccessoriesCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Women Shoes category header should be displayed")
    public void womenShoesCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Обувь";
        String actualResult = homePage.getHeaderWomenShoesCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Haberdashery category header should be displayed")
    public void haberdasheryCategoryHeaderShouldBeDisplayed() {
        HomePage homePage = new HomePage();

        String expectedResult = "Галантерея";
        String actualResult = homePage.getHeaderHaberdasheryCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
    }
}
