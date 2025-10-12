package by.megatop.ui.pages.cart;

import by.megatop.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartPage {

    private static final Logger logger = LogManager.getLogger(CartPage.class);

    public CartPage() {

    }

    public void clickCartButton() {
        logger.debug("Clicking on cart button");
        DriverManager.clickElement(CartLocators.BUTTON_CART_ICON);
        logger.info("Cart opened successfully");
    }

    public String getEmptyCartMessage() {
        logger.debug("Getting empty cart message");
        String message = DriverManager.getTextFromElementWhenVisible(CartLocators.EMPTY_CART_MESSAGE);
        logger.info("Retrieved empty cart message: {}", message);
        return message;
    }

    public String getLoginPromptMessage() {
        logger.debug("Getting login prompt message");
        String message = DriverManager.getTextFromElementWhenVisible(CartLocators.LOGIN_PROMPT_MESSAGE);
        logger.info("Retrieved login prompt message: {}", message);
        return message;
    }

    public void clickAddFirstItemToCartIcon() {
        logger.debug("Selecting first item for adding to cart");
        DriverManager.clickElementWhenClickable(CartLocators.BUTTON_FIRST_ITEM_ADD_TO_CART);
        logger.info("First item selected for adding to cart");
    }

    public void clickItemSizeButton() {
        logger.debug("Clicking item size button");
        DriverManager.clickElementWhenClickable(CartLocators.BUTTON_ITEM_SIZE);
        logger.info("Item size selected");
    }

    public void clickAddToCartConfirmButton() {
        logger.info("Confirming add to cart action");
        DriverManager.clickElementWhenClickable(CartLocators.BUTTON_CONFIRM_ADD_TO_CART);
        logger.info("Add to cart confirmed");
    }

    public void clickGoToCartButton() {
        logger.debug("Navigating to cart page");
        DriverManager.clickElement(CartLocators.BUTTON_GO_TO_CART);
        logger.info("Navigated to cart page");
    }

    public void addItemToCart() {
        logger.debug("Starting process to add item to cart with size selection");
        clickAddFirstItemToCartIcon();
        clickItemSizeButton();
        clickAddToCartConfirmButton();
        clickGoToCartButton();
        logger.info("Item added to cart successfully");
    }

    public String getCounterItemsText() {
        logger.info("Getting cart items count");
        String count = DriverManager.getTextFromElement(CartLocators.CART_ITEMS_COUNTER);
        logger.info("Retrieved cart items count: {}", count);
        return count;
    }

    public void clickIncreaseItemQuantityButton() {
        logger.debug("Increasing item quantity");
        DriverManager.clickElementWhenClickable(CartLocators.BUTTON_INCREASE_QUANTITY);
        logger.info("Item quantity increased");
    }

    public String getCounterItemParamsText() {
        logger.debug("Getting current item quantity");
        String quantity = DriverManager.getTextFromElementWhenVisible(CartLocators.ITEM_QUANTITY_DISPLAY);
        logger.info("Retrieved current item quantity: {}", quantity);
        return quantity;
    }

    public String getCartPageHeader() {
        String header = DriverManager.getTextFromElementWhenVisible(CartLocators.HEADER_CART_PAGE);
        logger.info("Retrieved cart page header: {}", header);
        return header;
    }
}
