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

    private CartPage cartPage;

    @BeforeEach
    public void openCartPage() {
        cartPage = new CartPage();
        cartPage.clickCartButton();
    }

    @Test
    @DisplayName("Verify empty cart message is displayed correctly")
    public void shouldDisplayEmptyCartMessageIfCartIsEmpty() {
        String expectedResult = "Ваша корзина пуста";
        String actualResult = cartPage.getEmptyCartMessage();

        Assertions.assertEquals(expectedResult, actualResult, "Empty cart message should be displayed when cart is empty");
    }

    @Test
    @DisplayName("Verify login prompt is displayed for unauthorized users")
    public void shouldDisplayLoginPromptIfUserIsUnauthorized() {
        String expectedResult = "Войдите в систему, чтобы увидеть корзину покупок";
        String actualResult = cartPage.getLoginPromptMessage();

        Assertions.assertEquals(expectedResult, actualResult, "Login prompt should be displayed when user is not authorized");
    }

    @Test
    @DisplayName("Verify cart items counter updates when adding item to cart")
    public void shouldUpdateCartCounterWhenAddingItem() {
        cartPage.addItemToCart();

        String expectedResult = "В корзине 1 товар";
        String actualResult = cartPage.getCounterItemsText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Verify item quantity increases when clicking increase button")
    public void shouldIncreaseItemQuantityWhenClickingIncreaseButton() {
        cartPage.addItemToCart();
        cartPage.clickIncreaseItemQuantityButton();

        String expectedResult = "1 шт.";
        String actualResult = cartPage.getCounterItemParamsText();

        Assertions.assertEquals(expectedResult, actualResult);
    }
}
