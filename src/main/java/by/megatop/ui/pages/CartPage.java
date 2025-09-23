package by.megatop.ui.pages;

import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;

public class CartPage {

    private final String BUTTON_CART = "(//div[@class='v-image v-responsive ma-auto theme--light'])[3]";
    private final String EMPTY_CART_MESSAGE = "(//div[@class='text-center mt-2'])[1]";
    private final String LOGIN_PROMPT_TEXT = "(//div[@class='text-center mt-2'])[2]";

    public CartPage() {

    }

    public void clickCartButton() {
        DriverManager.clickElement(BUTTON_CART);
    }

    public String getEmptyCartMessageText() {
        return DriverManager.getTextFromElement(EMPTY_CART_MESSAGE);
    }

    public String getLoginPromptText() {
        WaitUtils.waitForElementClickable(LOGIN_PROMPT_TEXT);
        return DriverManager.getTextFromElement(LOGIN_PROMPT_TEXT);
    }
}
