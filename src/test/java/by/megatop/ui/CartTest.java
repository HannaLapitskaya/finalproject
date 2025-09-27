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

    private static final Logger LOGGER = LogManager.getLogger();
    private CartPage cartPage;

    @BeforeEach
    public void openCartPage() {
        LOGGER.info("Opening Cart page");
        cartPage = new CartPage();
        cartPage.clickCartButton();
        LOGGER.info("Cart page opened successfully");
    }

    @Test
    @DisplayName("Verify empty cart message is displayed correctly")
    public void shouldDisplayEmptyCartMessageIfCartIsEmpty() {
        LOGGER.info("Starting test: Verify empty cart message");

        String expectedResult = "Ваша корзина пуста";
        String actualResult = cartPage.getEmptyCartMessageText();

        Assertions.assertEquals(expectedResult, actualResult, "Empty cart message should be displayed when cart is empty");
        LOGGER.info("Test completed successfully - empty cart message is correct");
    }

    @Test
    @DisplayName("Verify login prompt is displayed for unauthorized users")
    public void shouldDisplayLoginPromptIfUserIsUnauthorized() {
        LOGGER.info("Starting test: Verify login prompt for unauthorized users");

        String expectedResult = "Войдите в систему, чтобы увидеть корзину покупок";
        String actualResult = cartPage.getLoginPromptText();

        Assertions.assertEquals(expectedResult, actualResult, "Login prompt should be displayed when user is not authorized");
        LOGGER.info("Test completed successfully - login prompt is correct");
    }
}

