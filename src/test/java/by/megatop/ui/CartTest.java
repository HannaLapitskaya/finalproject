package by.megatop.ui;

import by.megatop.ui.pages.cart.CartExpectedMessages;
import by.megatop.ui.pages.cart.CartPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("UI Testing")
@Feature("Shopping Cart")
@Story("Cart Functionality")
@DisplayName("UI cart functionality tests")
public class CartTest extends BaseTest {

    private CartPage cartPage;

    @BeforeEach
    public void openCartPage() {
        cartPage = new CartPage();
        cartPage.clickCartButton();
    }

    @Test
    @DisplayName("Verify empty cart message is displayed correctly")
    @Description("Test verifies that empty cart message is displayed when cart contains no items")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("CT-01")
    public void shouldDisplayEmptyCartMessageIfCartIsEmpty() {
        Assertions.assertEquals(CartExpectedMessages.EMPTY_CART_MESSAGE, cartPage.getEmptyCartMessage(), "Empty cart message should be displayed when cart is empty");
    }

    @Test
    @DisplayName("Verify login prompt is displayed for unauthorized users")
    @Description("Test verifies that login prompt is displayed when unauthorized user accesses cart")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("CT-02")
    public void shouldDisplayLoginPromptIfUserIsUnauthorized() {
        Assertions.assertEquals(CartExpectedMessages.UNAUTHORIZED_CART_MESSAGE, cartPage.getLoginPromptMessage(), "Login prompt should be displayed when user is not authorized");
    }

    @Test
    @DisplayName("Verify cart items counter updates when adding item to cart")
    @Description("Test verifies that cart items counter updates correctly after adding item to cart")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("CT-03")
    public void shouldUpdateCartCounterWhenAddingItem() {
        cartPage.addItemToCart();

        Assertions.assertEquals(CartExpectedMessages.SINGLE_ITEM_CART_MESSAGE, cartPage.getCounterItemsText());
    }

    @Test
    @DisplayName("Verify item quantity increases when clicking increase button")
    @Description("Test verifies that item quantity increases when clicking increase quantity button")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("СT-04")
    public void shouldIncreaseItemQuantityWhenClickingIncreaseButton() {
        cartPage.addItemToCart();
        cartPage.clickIncreaseItemQuantityButton();

        Assertions.assertEquals(CartExpectedMessages.SINGLE_QUANTITY_TEXT, cartPage.getCounterItemParamsText());
    }
}
