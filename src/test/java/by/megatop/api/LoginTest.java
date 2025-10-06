package by.megatop.api;

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
    public void shouldReturn422StatusCodeWithErrorMessageWhenLoginWithIncorrectData() {
        service.doRequest();

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.get("error") != null || jsonPath.get("message") != null).isTrue(),
                () -> assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase(ERROR_MESSAGE)
        );
    }

    @Test
    @DisplayName("Login with a filled-in password and an empty phone number")
    public void shouldReturn500StatusCodeWhenLoginWithEmptyEmailAndEmptyPassword() {
        service.doRequest("", "");

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        int actualStatusCode = service.getStatusCode();

        assertAll(
                () -> assertThat(actualStatusCode).isEqualTo(INTERNAL_SERVER_ERROR_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase("Server Error")
        );
    }

    @Test
    @DisplayName("Login with valid phone number and empty password should return 422 status cod")
    public void shouldReturn422StatusCodeWhenLoginWithValidPhoneAndEmptyPassword() {
        service.doRequest(generatePhoneNumberForAPI(), "");

        String responseBody = service.getBody();

        JsonPath jsonPath = service.getJsonPath();
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(jsonPath.getString("status")).isEqualTo("error"),
                () -> assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(phoneErrors.size()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("Login with a non-numeric phone number")
    public void shouldReturn422StatusCodeWithErrorMessageWhenLoginWithNonNumericPhone() {
        service.doRequest(generatePassword(), "");

        int actualStatusCode = service.getStatusCode();
        String responseBody = service.getBody();

        JsonPath jsonPath = service.getJsonPath();
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("status")).isEqualTo("error"),
                () -> assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(phoneErrors).isNotNull().extracting(List::isEmpty).isEqualTo(false),
                () -> assertThat(jsonPath.getMap("errors")).isNotNull()
        );
    }

    @Test
    @DisplayName("Login with a phone number with fewer than 12 digits")
    public void shouldReturn422StatusCodeWithErrorMessageWhenPhoneHavingFewerThan12Digits() {
        service.doRequest("37525", generatePassword());

        int actualStatusCode = service.getStatusCode();
        String responseBody = service.getBody();

        JsonPath jsonPath = service.getJsonPath();
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
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
    public void shouldReturn422StatusCodeWithErrorMessageWhenPhoneHavingMoreThan12Digits() {
        service.doRequest(generatePhoneNumberForAPI() + "123", generatePassword());

        int actualStatusCode = service.getStatusCode();
        String responseBody = service.getBody();

        JsonPath jsonPath = service.getJsonPath();
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase(ERROR_MESSAGE),
                () -> assertThat(jsonPath.get("error") != null || jsonPath.get("message") != null).isTrue(),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getMap("errors")).asString().contains("phone"),
                () -> assertThat(phoneErrors).extracting(List::isEmpty).isEqualTo(false)
        );
    }
}
