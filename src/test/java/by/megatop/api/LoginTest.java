package by.megatop.api;

import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("API login functionality tests")
public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();
    private static final int UNPROCESSABLE_ENTITY_CODE = 422;
    private static final int INTERNAL_SERVER_ERROR_CODE = 500;
    private static  final String ERROR_MESSAGE = "Вы ввели неверный номер телефона и/или пароль";

    @Test
    @DisplayName("Login with incorrect data")
    public void shouldReturn422StatusCodeWithErrorMessageWhenLoginWithIncorrectData() {
        logger.info("Starting test: Login with incorrect data");
        service.doRequest();

        int actualStatusCode = service.getStatusCode();

        String responseBody = service.getBody();
        JsonPath jsonPath = new JsonPath(service.getBody());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE);
            softly.assertThat(responseBody).isNotNull().isNotBlank();
            softly.assertThat(jsonPath.get("error") != null || jsonPath.get("message") != null).isTrue();
            softly.assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase(ERROR_MESSAGE);
        });
        logger.info("Test completed successfully: Login with incorrect data");
    }

    @Test
    @DisplayName("Login with a filled-in password and an empty phone number")
    public void shouldReturn500StatusCodeWhenLoginWithEmptyEmailAndEmptyPassword() {
        logger.info("Starting test: Login with a filled-in password and an empty phone number");
        service.doRequest("", "");

        String responseBody = service.getBody();
        JsonPath jsonPath = new JsonPath(service.getBody());

        int actualStatusCode = service.getStatusCode();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualStatusCode).isEqualTo(INTERNAL_SERVER_ERROR_CODE);
            softly.assertThat(responseBody).isNotNull().isNotBlank();
            softly.assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase("Server Error");
        });
        logger.info("Test completed successfully: Login with a filled-in password and an empty phone number");
    }

    @Test
    @DisplayName("Login with valid phone number and empty password should return 422 status cod")
    public void shouldReturn422StatusCodeWhenLoginWithValidPhoneAndEmptyPassword() {
        logger.info("Login with valid phone number and empty password should return 422 status cod");
        service.doRequest("+375255555555", "");

        String responseBody = service.getBody();

        JsonPath jsonPath = new JsonPath(responseBody);
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(responseBody).isNotNull().isNotBlank();
            softly.assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE);
            softly.assertThat(jsonPath.getString("status")).isEqualTo("error");
            softly.assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE);
            softly.assertThat(phoneErrors.size()).isEqualTo(1);
            softly.assertThat(phoneErrors.getFirst()).isEqualTo(ERROR_MESSAGE);
        });
    }

    @Test
    @DisplayName("Login with a non-numeric phone number")
    public void shouldReturn422StatusCodeWithErrorMessageWhenLoginWithNonNumericPhone() {
        logger.info("Login with a non-numeric phone number");
        service.doRequest("test@login.by", "");

        int actualStatusCode = service.getStatusCode();
        String responseBody = service.getBody();

        JsonPath jsonPath = new JsonPath(responseBody);
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE);
            softly.assertThat(responseBody).isNotNull().isNotBlank();
            softly.assertThat(jsonPath.getString("status")).isEqualTo("error");
            softly.assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE);
            softly.assertThat(phoneErrors).isNotEmpty().isNotNull();
            softly.assertThat(jsonPath.getMap("errors")).isNotNull().isNotEmpty();
            softly.assertThat(phoneErrors.getFirst()).isEqualTo(ERROR_MESSAGE);
        });
    }

    @Test
    @DisplayName("Login with a phone number with fewer than 12 digits")
    public void shouldReturn422StatusCodeWithErrorMessageWhenPhoneHavingFewerThan12Digits() {
        logger.info("Login with a phone number with fewer than 12 digits");
        service.doRequest("37525", "123123123");

        int actualStatusCode = service.getStatusCode();
        String responseBody = service.getBody();

        JsonPath jsonPath = new JsonPath(responseBody);
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE);
            softly.assertThat(responseBody).isNotNull().isNotBlank();
            softly.assertThat(responseBody).isNotNull().isNotBlank();
            softly.assertThat(jsonPath.getString("status")).isEqualTo("error");
            softly.assertThat(jsonPath.getString("message")).isEqualTo(ERROR_MESSAGE);
            softly.assertThat(jsonPath.getMap("errors")).isNotNull().isNotEmpty();
            softly.assertThat(jsonPath.getMap("errors")).containsKey("phone");
            softly.assertThat(phoneErrors).isNotNull().isNotEmpty();
            softly.assertThat(phoneErrors.size()).isEqualTo(1);
            softly.assertThat(phoneErrors.getFirst()).isEqualTo(ERROR_MESSAGE);
        });
    }

    @Test
    @DisplayName("Login with a phone number with more than 12 digits")
    public void shouldReturn422StatusCodeWithErrorMessageWhenPhoneHavingMoreThan12Digits() {
        logger.info("Login with a phone number with more than 12 digits");
        service.doRequest("3752511111111111111", "123123123");

        int actualStatusCode = service.getStatusCode();
        String responseBody = service.getBody();

        JsonPath jsonPath = new JsonPath(service.getBody());
        List<String> phoneErrors = jsonPath.getList("errors.phone");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualStatusCode).isEqualTo(UNPROCESSABLE_ENTITY_CODE);
            softly.assertThat(jsonPath.getString("message")).isNotBlank().containsIgnoringCase(ERROR_MESSAGE);
            softly.assertThat(jsonPath.get("error") != null || jsonPath.get("message") != null).isTrue();
            softly.assertThat(responseBody).isNotNull().isNotBlank();
            softly.assertThat(jsonPath.getMap("errors")).containsKey("phone");
            softly.assertThat(phoneErrors).isNotEmpty();
            softly.assertThat(phoneErrors.getFirst()).isEqualTo(ERROR_MESSAGE);
        });
    }
}
