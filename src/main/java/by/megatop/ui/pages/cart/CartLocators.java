package by.megatop.ui.pages.cart;

public class CartLocators {

    public static final String BUTTON_CART_ICON = "(//div[@class='v-image v-responsive ma-auto theme--light'])[3]";
    public static final String EMPTY_CART_MESSAGE = "(//div[@class='text-center mt-2'])[1]";
    public static final String LOGIN_PROMPT_MESSAGE = "(//div[@class='text-center mt-2'])[2]";
    public static final String BUTTON_FIRST_ITEM_ADD_TO_CART = "(//button[@class='basket v-btn v-btn--icon v-btn--round theme--light v-size--default'])[1]";
    public static final String BUTTON_ITEM_SIZE = "(//tr[@class='sizes__item cursor-pointer'])[1]";
    public static final String BUTTON_CONFIRM_ADD_TO_CART = "//button[@class='btn-gray mb-4 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']";
    public static final String BUTTON_GO_TO_CART = "//button[@class='btn-gray mt-5 mb-2 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']";
    public static final String CART_ITEMS_COUNTER = "//div[@class='content__title']";
    public static final String BUTTON_INCREASE_QUANTITY = "//div[contains(text(), '+')]";
    public static final String ITEM_QUANTITY_DISPLAY = "//div[@class='mr-3 my-auto text__param']";
}
