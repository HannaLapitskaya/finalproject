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
    public static final String CART_ITEMS_COUNTER = "//div[@class='content__title']";
    public static final String BUTTON_INCREASE_QUANTITY = "//div[contains(text(), '+')]";
    private static final String ITEM_QUANTITY_DISPLAY = "//div[@class='mr-3 my-auto text__param']";

    public CartPage() {

    }

    public void clickCartButton() {
        logger.info("Opening cart");
        DriverManager.clickElement(BUTTON_CART_ICON);
    }

    public String getEmptyCartMessage() {
        logger.info("Getting empty cart message");
        return DriverManager.getTextFromElement(EMPTY_CART_MESSAGE);
    }

    public String getLoginPromptMessage() {
        logger.info("Waiting for login prompt message to be visible");
        WaitUtils.waitForElementClickable(LOGIN_PROMPT_MESSAGE);
        return DriverManager.getTextFromElement(LOGIN_PROMPT_MESSAGE);
    }

    public void clickAddFirstItemToCartIcon() {
        logger.info("Selecting first item for adding to cart");
        WaitUtils.waitForElementVisible(BUTTON_FIRST_ITEM_ADD_TO_CART);
        DriverManager.clickElement(BUTTON_FIRST_ITEM_ADD_TO_CART);
    }

    public void clickItemSizeButton() {
        WaitUtils.waitForElementVisible(BUTTON_ITEM_SIZE);
        DriverManager.clickElement(BUTTON_ITEM_SIZE);
    }

    public void clickAddToCartConfirmButton() {
        logger.info("Confirming add to cart action");
        DriverManager.clickElement((BUTTON_CONFIRM_ADD_TO_CART));
    }

    public void clickGoToCartButton() {
        logger.info("Navigating to cart page");
        DriverManager.clickElement(BUTTON_GO_TO_CART);
    }

    public void addItemToCart() {
        logger.info("Starting process to add item to cart with size selection");
        clickAddFirstItemToCartIcon();
        clickItemSizeButton();
        clickAddToCartConfirmButton();
        clickGoToCartButton();
    }

    public String getCounterItemsText(){
        logger.info("Getting cart items count");
        return DriverManager.getTextFromElement(CART_ITEMS_COUNTER);
    }

    public void clickIncreaseItemQuantityButton() {
        logger.info("Increasing item quantity");
        WaitUtils.waitForElementVisible(BUTTON_INCREASE_QUANTITY);
        DriverManager.clickElement(BUTTON_INCREASE_QUANTITY);
    }

    public String getCounterItemParamsText(){
        logger.info("Getting current item quantity");
        return DriverManager.getTextFromElement(ITEM_QUANTITY_DISPLAY);
    }
}
