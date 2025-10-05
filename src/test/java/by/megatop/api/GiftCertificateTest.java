package by.megatop.api;

import by.megatop.ui.utils.LoginUtils;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("API Gift Certificate tests")
public class GiftCertificateTest {

    private static final Logger logger = LogManager.getLogger();
    private static final int UNPROCESSABLE_ENTITY_CODE = 422;
    private static final int OK_CODE = 200;
    private static final String ERROR_MESSAGE = "Ошибка валидации параметров";
    private static final String ERROR_PHONE = "Должен быть валидный номер телефона";

    private GiftCertificateService service;

    @BeforeEach
    void setUp() {
        service = new GiftCertificateService();
    }

    @AfterEach
    void tearDown() {
        service = null;
    }

    @Test
    @DisplayName("Valid phone number")
    public void SendConfirmationCodeWithValidPhone() {
        logger.debug("Valid phone number test");
        service.doRequest(LoginUtils.generatePhoneNumberForAPI());

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(OK_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).contains("Код подтверждения отправлен на номер")
        );
        logger.info("Valid phone test passed");
    }

    @Test
    @DisplayName("Short phone number - less than 12 digits")
    public void PhoneNumberLessThan12Digits() {
        logger.debug("Short phone number test");
        service.doRequest("375255");

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo(ERROR_PHONE)
        );
        logger.info("Short phone number test passed");
    }

    @Test
    @DisplayName("Long phone number - more than 12 digits")
    public void PhoneNumberMoreThan12Digits() {
        logger.debug("Empty phone number test");
        service.doRequest(LoginUtils.generatePhoneNumberForAPI() + 345);

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo(ERROR_PHONE)
        );
        logger.info("Empty phone test passed");
    }

    @Test
    @DisplayName("Empty phone number")
    public void emptyPhoneNumber() {
        logger.debug("Starting test: phone number more than 12 digits");
        service.doRequest("");

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo("Phone обязательное поле")
        );
        logger.info("Test completed successfully: Phone number more than 12 digits");
    }

    @Test
    @DisplayName("Non-digit phone number")
    public void nonDigitPhoneNumber() {
        logger.debug("Non-digit phone number test");
        service.doRequest("рыпвао");

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo(ERROR_PHONE)
        );
        logger.info("Non-digit phone number test passed");
    }

    @Test
    @DisplayName("Phone number is null")
    public void nullPhoneNumber() {
        logger.debug("Starting test: ");
        service.doRequest(null);

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo(ERROR_PHONE)
        );
        logger.info("Null phone test passed");
    }
}
