package by.megatop.ui;

import by.megatop.ui.pages.login.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static by.megatop.ui.utils.LoginUtils.generatePassword;
import static by.megatop.ui.utils.LoginUtils.generatePhoneNumberForUI;

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
    public void shouldDisplayLoginHeaderWhenLoginFormIsOpened() {
        String expectedResult = "ВХОД";
        String actualResult = loginPage.getHeaderLoginFormText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Verify 'Забыли пароль?' link is present")
    public void shouldDisplayForgotPasswordLinkWhenLoginFormIsOpened() {
        String expectedResult = "Забыли пароль?";
        String actualResult = loginPage.getForgotPasswordText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Validate empty phone number field validation")
    public void shouldShowValidationErrorWhenPhoneNumberIsEmpty() {
        loginPage.clickButtonSubmitLogin();

        String expectedResult = "Телефон обязательное поле";
        String actualResult = loginPage.getErrorEmptyPhoneText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Validate incomplete phone number validation")
    public void shouldShowValidationErrorWhenPhoneNumberIsIncomplete() {

        loginPage.sendKeysInputPhone("12");
        loginPage.clickButtonSubmitLogin();

        String expectedResult = "Введите телефон полностью";
        String actualResult = loginPage.getErrorIncompletePhoneText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Validate phone number with non-belarusian operator code")
    public void shouldShowValidationErrorWhenPhoneNumberHasNonBelarusianCode() {
        loginPage.sendKeysInputPhone("110000000");
        loginPage.clickButtonSubmitLogin();

        String expectedResult = "Введите корректно номер телефона";
        String actualResult = loginPage.getErrorInvalidPhoneOperator();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Verify error message when invalid credentials are used for login")
    public void shouldShowErrorMessageWhenInvalidCredentialsAreUsed() {
        loginPage.sendKeysInputPhone(generatePhoneNumberForUI());
        loginPage.sendKeysInputPassword(generatePassword());
        loginPage.clickButtonSubmitLogin();

        String expectedResult = "Вы ввели неверный номер телефона и/или пароль";
        String actualResult = loginPage.getErrorInvalidCredentialsText();

        Assertions.assertEquals(expectedResult, actualResult);
    }
}
