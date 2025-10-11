package by.megatop.ui;

import by.megatop.ui.pages.login.LoginExpectedMessages;
import by.megatop.ui.pages.login.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static by.megatop.utils.LoginUtils.generatePassword;
import static by.megatop.utils.LoginUtils.generatePhoneNumberForUI;

@Epic("UI Testing")
@Feature("Authentication")
@Story("Login Form Validation")
@DisplayName("UI login functionality tests")
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void openLoginForm() {
        loginPage = new LoginPage();
        loginPage.clickAccountButton();
    }

    @Test
    @DisplayName("Verify 'ВХОД' header is present")
    @Description("Test verifies that login form header is displayed correctly when form is opened")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LN-01")
    public void shouldDisplayLoginHeaderWhenLoginFormIsOpened() {
        String expectedResult = "ВХОД";

        Assertions.assertEquals(expectedResult, loginPage.getHeaderLoginFormText());
    }

    @Test
    @DisplayName("Verify 'Забыли пароль?' link is present")
    @Description("Test verifies that 'Forgot Password' link is displayed in login form")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("LN-02")
    public void shouldDisplayForgotPasswordLinkWhenLoginFormIsOpened() {
        String expectedResult = "Забыли пароль?";

        Assertions.assertEquals(expectedResult, loginPage.getForgotPasswordText());
    }

    @Test
    @DisplayName("Validate empty phone number field validation")
    @Description("Test verifies validation error when phone number field is empty")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LN-03")
    public void shouldShowValidationErrorWhenPhoneNumberIsEmpty() {
        loginPage.clickButtonSubmitLogin();

        Assertions.assertEquals(LoginExpectedMessages.PHONE_REQUIRED_ERROR, loginPage.getErrorEmptyPhoneText());
    }

    @Test
    @DisplayName("Validate incomplete phone number validation")
    @Description("Test verifies validation error when phone number is incomplete")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LN-04")
    public void shouldShowValidationErrorWhenPhoneNumberIsIncomplete() {
        loginPage.sendKeysInputPhone("12");
        loginPage.clickButtonSubmitLogin();

        Assertions.assertEquals(LoginExpectedMessages.INCOMPLETE_PHONE_ERROR, loginPage.getErrorIncompletePhoneText());
    }

    @Test
    @DisplayName("Validate phone number with non-belarusian operator code")
    @Description("Test verifies validation error when phone number has non-Belarusian operator code")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("LN-05")
    public void shouldShowValidationErrorWhenPhoneNumberHasNonBelarusianCode() {
        loginPage.sendKeysInputPhone("110000000");
        loginPage.clickButtonSubmitLogin();

        Assertions.assertEquals(LoginExpectedMessages.INVALID_PHONE_FORMAT_ERROR, loginPage.getErrorInvalidPhoneOperator());
    }

    @Test
    @DisplayName("Verify error message when invalid credentials are used for login")
    @Description("Test verifies error message when invalid phone and password are used for login")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LN-06")
    public void shouldShowErrorMessageWhenInvalidCredentialsAreUsed() {
        loginPage.sendKeysInputPhone(generatePhoneNumberForUI());
        loginPage.sendKeysInputPassword(generatePassword());
        loginPage.clickButtonSubmitLogin();

        Assertions.assertEquals(LoginExpectedMessages.INVALID_CREDENTIALS_ERROR, loginPage.getErrorInvalidCredentialsText());
    }
}
