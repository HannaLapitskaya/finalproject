package by.megatop.ui;

import by.megatop.ui.pages.CartPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Cart functionality tests")
public class CartTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();
    private CartPage cartPage;

    @BeforeEach
    public void openCartPage() {
        logger.info("Opening Cart page");
        cartPage = new CartPage();
        cartPage.clickCartButton();
        logger.info("Cart page opened successfully");
    }

    @Test
    @DisplayName("Verify empty cart message is displayed correctly")
    public void shouldDisplayEmptyCartMessageIfCartIsEmpty() {
        logger.info("Starting test: Verify empty cart message");

        String expectedResult = "Ваша корзина пуста";
        String actualResult = cartPage.getEmptyCartMessage();

        Assertions.assertEquals(expectedResult, actualResult, "Empty cart message should be displayed when cart is empty");
        logger.info("Test completed successfully - empty cart message is correct");
    }

    @Test
    @DisplayName("Verify login prompt is displayed for unauthorized users")
    public void shouldDisplayLoginPromptIfUserIsUnauthorized() {
        logger.info("Starting test: Verify login prompt for unauthorized users");

        String expectedResult = "Войдите в систему, чтобы увидеть корзину покупок";
        String actualResult = cartPage.getLoginPromptMessage();

        Assertions.assertEquals(expectedResult, actualResult, "Login prompt should be displayed when user is not authorized");
        logger.info("Test completed successfully - login prompt is correct");
    }

    @Test
    @DisplayName("Verify cart items counter updates when adding item to cart")
    public void shouldUpdateCartCounterWhenAddingItem() {
        logger.info("Starting test: Verify cart items counter updates when adding item to cart");
        cartPage.addItemToCart();

        String expectedResult = "В корзине 1 товар";
        String actualResult = cartPage.getCounterItemsText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test completed successfully - Verify cart items counter updates when adding item to cart");
    }

    @Test
    @DisplayName("Verify item quantity increases when clicking increase button")
    public void shouldIncreaseItemQuantityWhenClickingIncreaseButton() {
        logger.info("Starting test: Verify item quantity increases when clicking increase button");
        cartPage.addItemToCart();
        cartPage.clickIncreaseItemQuantityButton();

        String expectedResult = "1 шт.";
        String actualResult = cartPage.getCounterItemParamsText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test completed successfully - Verify item quantity increases when clicking increase button");
    }
}
