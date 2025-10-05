package by.megatop.ui.pages;

import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartPage {

    private static final Logger logger = LogManager.getLogger();
    private static final String BUTTON_CART_ICON = "(//div[@class='v-image v-responsive ma-auto theme--light'])[3]";
    private static final String EMPTY_CART_MESSAGE = "(//div[@class='text-center mt-2'])[1]";
    private static final String LOGIN_PROMPT_MESSAGE = "(//div[@class='text-center mt-2'])[2]";
    private static final String BUTTON_FIRST_ITEM_ADD_TO_CART = "(//button[@class='basket v-btn v-btn--icon v-btn--round theme--light v-size--default'])[1]";
    private static final String BUTTON_ITEM_SIZE = "(//tr[@class='sizes__item cursor-pointer'])[1]";
    private static final String BUTTON_CONFIRM_ADD_TO_CART = "//button[@class='btn-gray mb-4 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']";
    private static final String BUTTON_GO_TO_CART = "//button[@class='btn-gray mt-5 mb-2 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']";
    private static final String CART_ITEMS_COUNTER = "//div[@class='content__title']";
    private static final String BUTTON_INCREASE_QUANTITY = "//div[contains(text(), '+')]";
    private static final String ITEM_QUANTITY_DISPLAY = "//div[@class='mr-3 my-auto text__param']";

    public CartPage() {

    }

    public void clickCartButton() {
        logger.debug("Clicking on cart button");
        DriverManager.clickElement(BUTTON_CART_ICON);
        logger.info("Cart opened");
    }

    public String getEmptyCartMessage() {
        logger.debug("Getting empty cart message");
        return DriverManager.getTextFromElementWhenVisible(EMPTY_CART_MESSAGE);
    }

    public String getLoginPromptMessage() {
        logger.debug("Getting login prompt message");
        return DriverManager.getTextFromElementWhenVisible(LOGIN_PROMPT_MESSAGE);
    }

    public void clickAddFirstItemToCartIcon() {
        logger.debug("Selecting first item for adding to cart");
        DriverManager.clickElementWhenClickable(BUTTON_FIRST_ITEM_ADD_TO_CART);
        logger.info("First item selected for adding to cart");
    }

    public void clickItemSizeButton() {
        logger.debug("Clicking item size button");
        DriverManager.clickElementWhenClickable(BUTTON_ITEM_SIZE);
        logger.info("Item size selected");
    }

    public void clickAddToCartConfirmButton() {
        logger.info("Confirming add to cart action");
        DriverManager.clickElementWhenClickable((BUTTON_CONFIRM_ADD_TO_CART));
        logger.info("Add to cart confirmed");
    }

    public void clickGoToCartButton() {
        logger.debug("Navigating to cart page");
        DriverManager.clickElement(BUTTON_GO_TO_CART);
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
        return DriverManager.getTextFromElement(CART_ITEMS_COUNTER);
    }

    public void clickIncreaseItemQuantityButton() {
        logger.debug("Increasing item quantity");
        DriverManager.clickElementWhenClickable(BUTTON_INCREASE_QUANTITY);
        logger.info("Item quantity increased");
    }

    public String getCounterItemParamsText() {
        logger.debug("Getting current item quantity");
        return DriverManager.getTextFromElementWhenVisible(ITEM_QUANTITY_DISPLAY);
    }
}
