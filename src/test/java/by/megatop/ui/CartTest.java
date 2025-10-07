package by.megatop.ui;

import by.megatop.ui.pages.cart.CartExpectedMessages;
import by.megatop.ui.pages.cart.CartPage;
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

        Assertions.assertEquals(CartExpectedMessages.EMPTY_CART_MESSAGE, cartPage.getEmptyCartMessage(), "Empty cart message should be displayed when cart is empty");
    }

    @Test
    @DisplayName("Verify login prompt is displayed for unauthorized users")
    public void shouldDisplayLoginPromptIfUserIsUnauthorized() {
        String expectedResult = "Войдите в систему, чтобы увидеть корзину покупок";

        Assertions.assertEquals(expectedResult, cartPage.getLoginPromptMessage(), "Login prompt should be displayed when user is not authorized");
    }

    @Test
    @DisplayName("Verify cart items counter updates when adding item to cart")
    public void shouldUpdateCartCounterWhenAddingItem() {
        cartPage.addItemToCart();

        String expectedResult = "В корзине 1 товар";

        Assertions.assertEquals(expectedResult, cartPage.getCounterItemsText());
    }

    @Test
    @DisplayName("Verify item quantity increases when clicking increase button")
    public void shouldIncreaseItemQuantityWhenClickingIncreaseButton() {
        cartPage.addItemToCart();
        cartPage.clickIncreaseItemQuantityButton();

        String expectedResult = "1 шт.";

        Assertions.assertEquals(expectedResult, cartPage.getCounterItemParamsText());
    }
}
