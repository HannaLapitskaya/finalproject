package by.megatop.ui.pages;

import by.megatop.ui.webdriver.DriverManager;

public class LoginPage {

    private final String BUTTON_ACCOUNT = "//a[@class='btn px-0 v-btn v-btn--text theme--light v-size--default white--text']";
    private final String HEADER_LOGIN_FORM = "//a[contains(text(), 'Вход')]";
    private final String LINK_FORGOT_PASSWORD = "//a[@class='forgot label label--gray text-decoration-underline']";
    private final String INPUT_PHONE = "//input[@id='input-29']";
    private final String INPUT_PASSWORD = "//input[@id='input-30']";
    private final String BUTTON_SUBMIT_LOGIN = "//button[@type='submit']";
    private final String ERROR_EMPTY_PHONE = "//div[@class='v-messages__message']";
    private final String ERROR_INCOMPLETE_PHONE = "//div[@class='v-messages__message']";
    private final String ERROR_INVALID_CREDENTIALS = "//div[@class='v-messages__message']";
    private final String ERROR_INVALID_PHONE_OPERATOR = "//div[@class='v-messages__message']";

    public LoginPage() {

    }

    public void clickAccountButton() {
        DriverManager.clickElement(BUTTON_ACCOUNT);
    }

    public String getHeaderLoginFormText() {
        return DriverManager.getTextFromElement(HEADER_LOGIN_FORM);
    }

    public String getForgotPasswordText() {
        return DriverManager.getTextFromElement(LINK_FORGOT_PASSWORD);
    }

    public void sendKeysInputPassword(String password) {
        DriverManager.sendKeys(INPUT_PASSWORD, password);
    }

    public void sendKeysInputPhone(String phone) {
        DriverManager.sendKeys(INPUT_PHONE, phone);
    }

    public void clickButtonSubmitLogin() {
        DriverManager.clickElement(BUTTON_SUBMIT_LOGIN);
    }

    public String getErrorEmptyPhoneText() {
        return DriverManager.getTextFromElement(ERROR_EMPTY_PHONE);
    }

    public String getErrorIncompletePhoneText() {
        return DriverManager.getTextFromElement(ERROR_INCOMPLETE_PHONE);
    }

    public String getErrorInvalidCredentialsText() {
        return DriverManager.getTextFromElement(ERROR_INVALID_CREDENTIALS);
    }

    public String getErrorInvalidPhoneOperator() {
        return DriverManager.getTextFromElement(ERROR_INVALID_PHONE_OPERATOR);
    }
}
