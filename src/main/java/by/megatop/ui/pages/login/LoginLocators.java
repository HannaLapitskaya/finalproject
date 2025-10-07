package by.megatop.ui.pages.login;

public class LoginLocators {

    public static final String BUTTON_ACCOUNT = "//a[@class='btn px-0 v-btn v-btn--text theme--light v-size--default white--text']";
    public static final String HEADER_LOGIN_FORM = "//a[contains(text(), 'Вход')]";
    public static final String LINK_FORGOT_PASSWORD = "//a[@class='forgot label label--gray text-decoration-underline']";
    public static final String INPUT_PHONE = "//input[@id='input-29']";
    public static final String INPUT_PASSWORD = "//input[@id='input-30']";
    public static final String BUTTON_SUBMIT_LOGIN = "//button[@type='submit']";
    public static final String ERROR_EMPTY_PHONE = "//div[@class='v-messages__message']";
    public static final String ERROR_INCOMPLETE_PHONE = "//div[@class='v-messages__message']";
    public static final String ERROR_INVALID_CREDENTIALS = "//div[@class='v-messages__message']";
    public static final String ERROR_INVALID_PHONE_OPERATOR = "//div[@class='v-messages__message']";
}
