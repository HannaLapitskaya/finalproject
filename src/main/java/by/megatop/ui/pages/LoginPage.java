package by.megatop.ui.pages;

import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {

    private static final Logger logger = LogManager.getLogger();
    private static final String BUTTON_ACCOUNT = "//a[@class='btn px-0 v-btn v-btn--text theme--light v-size--default white--text']";
    private static final String HEADER_LOGIN_FORM = "//a[contains(text(), 'Вход')]";
    private static final String LINK_FORGOT_PASSWORD = "//a[@class='forgot label label--gray text-decoration-underline']";
    private static final String INPUT_PHONE = "//input[@id='input-29']";
    private static final String INPUT_PASSWORD = "//input[@id='input-30']";
    private static final String BUTTON_SUBMIT_LOGIN = "//button[@type='submit']";
    private static final String ERROR_EMPTY_PHONE = "//div[@class='v-messages__message']";
    private static final String ERROR_INCOMPLETE_PHONE = "//div[@class='v-messages__message']";
    private static final String ERROR_INVALID_CREDENTIALS = "//div[@class='v-messages__message']";
    private static final String ERROR_INVALID_PHONE_OPERATOR = "//div[@class='v-messages__message']";

    public LoginPage() {

    }

    public void clickAccountButton() {
        logger.debug("Clicking on account button");
        DriverManager.clickElement(BUTTON_ACCOUNT);
        logger.info("Account button clicked successfully");
    }

    public String getHeaderLoginFormText() {
        logger.debug("Getting login form header text");
        return DriverManager.getTextFromElement(HEADER_LOGIN_FORM);
    }

    public String getForgotPasswordText() {
        logger.debug("Getting 'Forgot password' link text");
        return DriverManager.getTextFromElement(LINK_FORGOT_PASSWORD);
    }

    public void sendKeysInputPassword(String password) {
        logger.debug("Entering password in password field");
        DriverManager.sendKeys(INPUT_PASSWORD, password);
        logger.info("Password entered successfully");
    }

    public void sendKeysInputPhone(String phone) {
        logger.debug("Entering phone in phone field");
        DriverManager.sendKeys(INPUT_PHONE, phone);
        logger.info("Phone number entered successfully");
    }

    public void clickButtonSubmitLogin() {
        logger.debug("Clicking on login submit button");
        DriverManager.clickElement(BUTTON_SUBMIT_LOGIN);
        logger.info("Login submit button clicked successfully");
    }

    public String getErrorEmptyPhoneText() {
        logger.debug("Getting empty phone error message");
        return DriverManager.getTextFromElement(ERROR_EMPTY_PHONE);
    }

    public String getErrorIncompletePhoneText() {
        logger.info("Getting incomplete phone error message");
        return DriverManager.getTextFromElement(ERROR_INCOMPLETE_PHONE);
    }

    public String getErrorInvalidCredentialsText() {
        logger.debug("Getting incomplete phone error message");
        return DriverManager.getTextFromElement(ERROR_INVALID_CREDENTIALS);
    }

    public String getErrorInvalidPhoneOperator() {
        logger.debug("Getting invalid credentials error message");
        return DriverManager.getTextFromElement(ERROR_INVALID_PHONE_OPERATOR);
    }
}
