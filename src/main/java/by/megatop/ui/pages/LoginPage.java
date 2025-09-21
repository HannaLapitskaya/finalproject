package by.megatop.ui.pages;

import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger LOGGER = LogManager.getLogger();

    public LoginPage() {

    }

    public void clickAccountButton() {
        LOGGER.info("Clicking on account button");
        DriverManager.clickElement(BUTTON_ACCOUNT);
    }

    public String getHeaderLoginFormText() {
        LOGGER.info("Getting login form header text");
        return DriverManager.getTextFromElement(HEADER_LOGIN_FORM);
    }

    public String getForgotPasswordText() {
        LOGGER.info("Getting 'Forgot password' link text");
        return DriverManager.getTextFromElement(LINK_FORGOT_PASSWORD);
    }

    public void sendKeysInputPassword(String password) {
        LOGGER.info("Entering password in password field");
        DriverManager.sendKeys(INPUT_PASSWORD, password);
    }

    public void sendKeysInputPhone(String phone) {
        LOGGER.info("Entering phone in phone field");
        DriverManager.sendKeys(INPUT_PHONE, phone);
    }

    public void clickButtonSubmitLogin() {
        LOGGER.info("Clicking on login submit button");
        DriverManager.clickElement(BUTTON_SUBMIT_LOGIN);
    }

    public String getErrorEmptyPhoneText() {
        LOGGER.info("Getting empty phone error message");
        return DriverManager.getTextFromElement(ERROR_EMPTY_PHONE);
    }

    public String getErrorIncompletePhoneText() {
        LOGGER.info("Getting incomplete phone error message");
        return DriverManager.getTextFromElement(ERROR_INCOMPLETE_PHONE);
    }

    public String getErrorInvalidCredentialsText() {
        LOGGER.info("Getting invalid credentials error message");
        return DriverManager.getTextFromElement(ERROR_INVALID_CREDENTIALS);
    }

    public String getErrorInvalidPhoneOperator() {
        LOGGER.info("Getting invalid phone operator error message");
        return DriverManager.getTextFromElement(ERROR_INVALID_PHONE_OPERATOR);
    }
}
