package by.megatop.ui;

import by.megatop.ui.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static by.megatop.ui.utils.LoginInfoUtils.generatePassword;
import static by.megatop.ui.utils.LoginInfoUtils.generatePhoneNumberForUi;

@DisplayName("UI login functionality tests")
public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();
    private LoginPage loginPage;

    @BeforeEach
    public void openLoginForm() {
        loginPage = new LoginPage();
        loginPage.clickAccountButton();
    }

    @Test
    @DisplayName("Verify 'ВХОД' header is present")
    public void shouldDisplayLoginHeaderWhenLoginFormIsOpened() {
        logger.debug("Starting test: Verify 'ВХОД' header is present");

        String expectedResult = "ВХОД";
        String actualResult = loginPage.getHeaderLoginFormText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Verify 'ВХОД' header is present' passed successfully");
    }

    @Test
    @DisplayName("Verify 'Забыли пароль?' link is present")
    public void shouldDisplayForgotPasswordLinkWhenLoginFormIsOpened() {
        logger.debug("Starting test: Verify 'Забыли пароль?' link is present");

        String expectedResult = "Забыли пароль?";
        String actualResult = loginPage.getForgotPasswordText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Verify 'Забыли пароль?' link is present' passed successfully");
    }

    @Test
    @DisplayName("Validate empty phone number field validation")
    public void shouldShowValidationErrorWhenPhoneNumberIsEmpty() {
        logger.debug("Starting test: Validate empty phone number field validation");

        loginPage.clickButtonSubmitLogin();

        String expectedResult = "Телефон обязательное поле";
        String actualResult = loginPage.getErrorEmptyPhoneText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Validate empty phone number field validation' passed successfully");
    }

    @Test
    @DisplayName("Validate incomplete phone number validation")
    public void shouldShowValidationErrorWhenPhoneNumberIsIncomplete() {
        logger.debug("Starting test: Validate incomplete phone number validation");

        loginPage.sendKeysInputPhone("12");
        loginPage.clickButtonSubmitLogin();

        String expectedResult = "Введите телефон полностью";
        String actualResult = loginPage.getErrorIncompletePhoneText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Validate incomplete phone number validation' passed successfully");
    }

    @Test
    @DisplayName("Validate phone number with non-belarusian operator code")
    public void shouldShowValidationErrorWhenPhoneNumberHasNonBelarusianCode() {
        logger.debug("Starting test: Validate phone number with non-belarusian operator code");

        loginPage.sendKeysInputPhone("110000000");
        loginPage.clickButtonSubmitLogin();

        String expectedResult = "Введите корректно номер телефона";
        String actualResult = loginPage.getErrorInvalidPhoneOperator();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Validate phone number with non-belarusian operator code' passed successfully");
    }

    @Test
    @DisplayName("Verify error message when invalid credentials are used for login")
    public void shouldShowErrorMessageWhenInvalidCredentialsAreUsed() {
        logger.debug("Starting test: Verify error message when invalid credentials are used for login");

        loginPage.sendKeysInputPhone(generatePhoneNumberForUi());
        loginPage.sendKeysInputPassword(generatePassword());
        loginPage.clickButtonSubmitLogin();

        String expectedResult = "Введите корректно номер телефона";
        String actualResult = loginPage.getErrorInvalidCredentialsText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Verify error message when invalid credentials are used for login' passed successfully");
    }
}
