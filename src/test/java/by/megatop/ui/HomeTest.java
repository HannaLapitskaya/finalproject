package by.megatop.ui;

import by.megatop.ui.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Home page UI tests")
public class HomeTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    @DisplayName("The presence of a contact phone number")
    public void contactPhoneNumberShouldBeDisplayed() {
        logger.debug("Starting contact phone number verification test");
        HomePage homePage = new HomePage();

        String expectedResult = "7976";
        String actualResult = homePage.getContactPhoneText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Contact phone number test passed successfully");
    }

    @Test
    @DisplayName("Design in Korea category header should be displayed")
    public void designInKoreaCategoryHeaderShouldBeDisplayed() {
        logger.debug("Starting Design in Korea category header verification test");
        HomePage homePage = new HomePage();

        String expectedResult = "Design in Korea";
        String actualResult = homePage.getHeaderDesignInKoreaCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Design in Korea category header test passed successfully");
    }

    @Test
    @DisplayName("Trend Zone category header should be displayed")
    public void trendZoneCategoryHeaderShouldBeDisplayed() {
        logger.debug("Starting Trend Zone category header verification test");
        HomePage homePage = new HomePage();

        String expectedResult = "Зона трендов";
        String actualResult = homePage.getHeaderWomenTrendZoneCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Trend Zone category header test passed successfully");
    }

    @Test
    @DisplayName("Accessories category header should be displayed")
    public void accessoriesCategoryHeaderShouldBeDisplayed() {
        logger.debug("Starting Accessories category header verification test");
        HomePage homePage = new HomePage();

        String expectedResult = "Аксессуары";
        String actualResult = homePage.getHeaderAccessoriesCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Accessories category header test passed successfully");
    }

    @Test
    @DisplayName("Women Shoes category header should be displayed")
    public void womenShoesCategoryHeaderShouldBeDisplayed() {
        logger.debug("Starting Women Shoes category header verification test");
        HomePage homePage = new HomePage();

        String expectedResult = "Обувь";
        String actualResult = homePage.getHeaderWomenShoesCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Women Shoes category header test passed successfully");
    }

    @Test
    @DisplayName("Haberdashery category header should be displayed")
    public void haberdasheryCategoryHeaderShouldBeDisplayed() {
        logger.debug("Starting Haberdashery category header verification test");
        HomePage homePage = new HomePage();

        String expectedResult = "Галантерея";
        String actualResult = homePage.getHeaderHaberdasheryCategoryText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Haberdashery category header test passed successfully");
    }
}
