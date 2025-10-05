package by.megatop.api;

import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.megatop.ui.utils.LoginInfoUtils.generatePassword;
import static by.megatop.ui.utils.LoginInfoUtils.generatePhoneNumberForApi;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("API login functionality tests")
public class LoginTest {

    private static final Logger logger = LogManager.getLogger();
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
        logger.debug("Starting test: Login with incorrect data");
        service.doRequest();

        int actualStatusCode = service.getStatusCode();

        String responseBody = service.getBody();
        JsonPath jsonPath = new JsonPath(service.getBody());

        assertAll(
                () -> assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.get("error") != null || jsonPath.get("message") != null).isTrue(),
                () -> assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase(ERROR_MESSAGE)
        );
        logger.info("Test completed successfully: Login with incorrect data");
    }

    @Test
    @DisplayName("Login with a filled-in password and an empty phone number")
    public void shouldReturn500StatusCodeWhenLoginWithEmptyEmailAndEmptyPassword() {
        logger.debug("Starting test: Login with a filled-in password and an empty phone number");
        service.doRequest("", "");

        String responseBody = service.getBody();
        JsonPath jsonPath = new JsonPath(service.getBody());

        int actualStatusCode = service.getStatusCode();

        assertAll(
                () -> assertThat(actualStatusCode).isEqualTo(INTERNAL_SERVER_ERROR_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase("Server Error")
        );
        logger.info("Test completed successfully: Login with a filled-in password and an empty phone number");
    }

    @Test
    @DisplayName("Login with valid phone number and empty password should return 422 status cod")
    public void shouldReturn422StatusCodeWhenLoginWithValidPhoneAndEmptyPassword() {
        logger.debug("Starting test: Login with valid phone number and empty password should return 422 status cod");
        service.doRequest(generatePhoneNumberForApi(), "");

        String responseBody = service.getBody();

        JsonPath jsonPath = new JsonPath(responseBody);
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(jsonPath.getString("status")).isEqualTo("error"),
                () -> assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(phoneErrors.size()).isEqualTo(1)
        );
        logger.info("Test completed successfully: Login with valid phone number and empty password should return 422 status cod");
    }

    @Test
    @DisplayName("Login with a non-numeric phone number")
    public void shouldReturn422StatusCodeWithErrorMessageWhenLoginWithNonNumericPhone() {
        logger.debug("Starting test: Login with a non-numeric phone number");
        service.doRequest(generatePassword(), "");

        int actualStatusCode = service.getStatusCode();
        String responseBody = service.getBody();

        JsonPath jsonPath = new JsonPath(responseBody);
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("status")).isEqualTo("error"),
                () -> assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(phoneErrors).isNotNull().extracting(List::isEmpty).isEqualTo(false),
                () -> assertThat(jsonPath.getMap("errors")).isNotNull()
        );
        logger.info("Test completed successfully: Login with a non-numeric phone number");
    }

    @Test
    @DisplayName("Login with a phone number with fewer than 12 digits")
    public void shouldReturn422StatusCodeWithErrorMessageWhenPhoneHavingFewerThan12Digits() {
        logger.debug("Starting test: Login with a phone number with fewer than 12 digits");
        service.doRequest("37525", generatePassword());

        int actualStatusCode = service.getStatusCode();
        String responseBody = service.getBody();

        JsonPath jsonPath = new JsonPath(responseBody);
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
        logger.info("Test completed successfully: Login with a phone number with fewer than 12 digits");
    }

    @Test
    @DisplayName("Login with a phone number with more than 12 digits")
    public void shouldReturn422StatusCodeWithErrorMessageWhenPhoneHavingMoreThan12Digits() {
        logger.debug("Starting test: Login with a phone number with more than 12 digits");
        service.doRequest(generatePhoneNumberForApi() + "123", generatePassword());

        int actualStatusCode = service.getStatusCode();
        String responseBody = service.getBody();

        JsonPath jsonPath = new JsonPath(service.getBody());
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        assertAll(
                () -> assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase(ERROR_MESSAGE),
                () -> assertThat(jsonPath.get("error") != null || jsonPath.get("message") != null).isTrue(),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getMap("errors")).asString().contains("phone"),
                () -> assertThat(phoneErrors).extracting(List::isEmpty).isEqualTo(false)
        );
        logger.info("Test completed successfully: Login with a phone number with more than 12 digits");
    }
}
