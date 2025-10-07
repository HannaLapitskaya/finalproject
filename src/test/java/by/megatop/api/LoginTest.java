package by.megatop.api;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.megatop.ui.utils.LoginUtils.generatePassword;
import static by.megatop.ui.utils.LoginUtils.generatePhoneNumberForAPI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Epic("API Testing")
@Feature("Authentication")
@Story("Login Functionality")
@DisplayName("API login functionality tests")
public class LoginTest {

    private static final int UNPROCESSABLE_ENTITY_CODE = 422;
    private static final int INTERNAL_SERVER_ERROR_CODE = 500;
    private static final String ERROR_MESSAGE = "Вы ввели неверный номер телефона и/или пароль";

    private LoginService service;

    @BeforeEach
    void setUp() {
        service = new LoginService();
    }

    @AfterEach
    void tearDown() {
        service = null;
    }

    @Test
    @DisplayName("Login with incorrect data")
    @Description("Test verifies that login with incorrect credentials returns 422 status code and proper error message")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LGN-01")
    public void shouldReturn422StatusCodeWithErrorMessageWhenLoginWithIncorrectData() {
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(service.getBody()).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.get("error") != null || jsonPath.get("message") != null).isTrue(),
                () -> assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase(ERROR_MESSAGE)
        );
    }

    @Test
    @DisplayName("Login with a filled-in password and an empty phone number")
    @Description("Test verifies that login with empty phone number returns 500 internal server error")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LGN-02")
    public void shouldReturn500StatusCodeWhenLoginWithEmptyEmailAndEmptyPassword() {
        service.doRequest("", "");

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR_CODE),
                () -> assertThat(service.getBody()).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase("Server Error")
        );
    }

    @Test
    @DisplayName("Login with valid phone number and empty password should return 422 status cod")
    @Description("Test verifies that login with valid phone but empty password returns 422 validation error")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LGN-03")
    public void shouldReturn422StatusCodeWhenLoginWithValidPhoneAndEmptyPassword() {
        service.doRequest(generatePhoneNumberForAPI(), "");

        JsonPath jsonPath = service.getJsonPath();
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(service.getBody()).isNotNull().isNotBlank(),
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(jsonPath.getString("status")).isEqualTo("error"),
                () -> assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(phoneErrors.size()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("Login with a non-numeric phone number")
    @Description("Test verifies that login with non-numeric phone number returns 422 validation error")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("LGN-04")
    public void shouldReturn422StatusCodeWithErrorMessageWhenLoginWithNonNumericPhone() {
        service.doRequest(generatePassword(), "");

        JsonPath jsonPath = service.getJsonPath();
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(service.getBody()).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("status")).isEqualTo("error"),
                () -> assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(phoneErrors).isNotNull().extracting(List::isEmpty).isEqualTo(false),
                () -> assertThat(jsonPath.getMap("errors")).isNotNull()
        );
    }

    @Test
    @DisplayName("Login with a phone number with fewer than 12 digits")
    @Description("Test verifies that login with phone number shorter than 12 digits returns 422 validation error")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("LGN-05")
    public void shouldReturn422StatusCodeWithErrorMessageWhenPhoneHavingFewerThan12Digits() {
        service.doRequest("37525", generatePassword());

        JsonPath jsonPath = service.getJsonPath();
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(service.getBody()).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("status")).isEqualTo("error"),
                () -> assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getMap("errors")).isNotNull(),
                () -> assertThat(jsonPath.getMap("errors")).asString().contains("phone"),
                () -> assertThat(phoneErrors).isNotNull().extracting(List::isEmpty).isEqualTo(false),
                () -> assertThat(phoneErrors.size()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("Login with a phone number with more than 12 digits")
    @Description("Test verifies that login with phone number longer than 12 digits returns 422 validation error")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("LOGIN-006")
    public void shouldReturn422StatusCodeWithErrorMessageWhenPhoneHavingMoreThan12Digits() {
        service.doRequest(generatePhoneNumberForAPI() + "123", generatePassword());

        JsonPath jsonPath = service.getJsonPath();
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase(ERROR_MESSAGE),
                () -> assertThat(jsonPath.get("error") != null || jsonPath.get("message") != null).isTrue(),
                () -> assertThat(service.getBody()).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getMap("errors")).asString().contains("phone"),
                () -> assertThat(phoneErrors).extracting(List::isEmpty).isEqualTo(false)
        );
    }
}
