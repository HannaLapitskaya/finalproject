package by.megatop.ui.pages.login;

import by.megatop.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage() {

    }

    public void clickAccountButton() {
        logger.debug("Clicking on account button");
        DriverManager.clickElement(LoginLocators.BUTTON_ACCOUNT);
        logger.info("Account button clicked successfully");
    }

    public String getHeaderLoginFormText() {
        logger.debug("Getting login form header text");
        String headerText = DriverManager.getTextFromElement(LoginLocators.HEADER_LOGIN_FORM);
        logger.info("Retrieved login form header: {}", headerText);
        return headerText;
    }

    public String getForgotPasswordText() {
        logger.debug("Getting 'Forgot password' link text");
        String forgotPasswordText = DriverManager.getTextFromElement(LoginLocators.LINK_FORGOT_PASSWORD);
        logger.info("Retrieved 'Forgot password' text: {}", forgotPasswordText);
        return forgotPasswordText;
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

    public void sendKeysPhoneNumberAndPassword(String phone, String password) {
        sendKeysInputPhone(phone);
        sendKeysInputPassword(password);
    }

    public void clickButtonSubmitLogin() {
        logger.debug("Clicking on login submit button");
        DriverManager.clickElement(LoginLocators.BUTTON_SUBMIT_LOGIN);
        logger.info("Login submit button clicked successfully");
    }

    public String getErrorEmptyPhoneText() {
        logger.debug("Getting empty phone error message");
        String errorText = DriverManager.getTextFromElement(LoginLocators.ERROR_EMPTY_PHONE);
        logger.info("Retrieved empty phone error: {}", errorText);
        return errorText;
    }

    public String getErrorIncompletePhoneText() {
        logger.info("Getting incomplete phone error message");
        String errorText = DriverManager.getTextFromElement(LoginLocators.ERROR_INCOMPLETE_PHONE);
        logger.info("Retrieved incomplete phone error: {}", errorText);
        return errorText;
    }

    public String getErrorInvalidCredentialsText() {
        logger.debug("Getting incomplete phone error message");
        String errorText = DriverManager.getTextFromElement(LoginLocators.ERROR_INVALID_CREDENTIALS);
        logger.info("Retrieved invalid credentials error: {}", errorText);
        return errorText;
    }

    public String getErrorInvalidPhoneOperator() {
        logger.debug("Getting invalid credentials error message");
        String errorText = DriverManager.getTextFromElement(LoginLocators.ERROR_INVALID_PHONE_OPERATOR);
        logger.info("Retrieved invalid phone operator error: {}", errorText);
        return errorText;
    }
}
