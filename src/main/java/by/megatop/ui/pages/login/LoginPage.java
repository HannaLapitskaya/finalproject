package by.megatop.ui.pages.login;

import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {

    private static final Logger logger = LogManager.getLogger();

    public LoginPage() {

    }

    public void clickAccountButton() {
        logger.debug("Clicking on account button");
        DriverManager.clickElement(LoginLocators.BUTTON_ACCOUNT);
        logger.info("Account button clicked successfully");
    }

    public String getHeaderLoginFormText() {
        logger.debug("Getting login form header text");
        return DriverManager.getTextFromElement(LoginLocators.HEADER_LOGIN_FORM);
    }

    public String getForgotPasswordText() {
        logger.debug("Getting 'Forgot password' link text");
        return DriverManager.getTextFromElement(LoginLocators.LINK_FORGOT_PASSWORD);
    }

    public void sendKeysInputPassword(String password) {
        logger.debug("Entering password in password field");
        DriverManager.sendKeys(LoginLocators.INPUT_PASSWORD, password);
        logger.info("Password entered successfully");
    }

    public void sendKeysInputPhone(String phone) {
        logger.debug("Entering phone in phone field");
        DriverManager.sendKeys(LoginLocators.INPUT_PHONE, phone);
        logger.info("Phone number entered successfully");
    }

    public void clickButtonSubmitLogin() {
        logger.debug("Clicking on login submit button");
        DriverManager.clickElement(LoginLocators.BUTTON_SUBMIT_LOGIN);
        logger.info("Login submit button clicked successfully");
    }

    public String getErrorEmptyPhoneText() {
        logger.debug("Getting empty phone error message");
        return DriverManager.getTextFromElement(LoginLocators.ERROR_EMPTY_PHONE);
    }

    public String getErrorIncompletePhoneText() {
        logger.info("Getting incomplete phone error message");
        return DriverManager.getTextFromElement(LoginLocators.ERROR_INCOMPLETE_PHONE);
    }

    public String getErrorInvalidCredentialsText() {
        logger.debug("Getting incomplete phone error message");
        return DriverManager.getTextFromElement(LoginLocators.ERROR_INVALID_CREDENTIALS);
    }

    public String getErrorInvalidPhoneOperator() {
        logger.debug("Getting invalid credentials error message");
        return DriverManager.getTextFromElement(LoginLocators.ERROR_INVALID_PHONE_OPERATOR);
    }
}
