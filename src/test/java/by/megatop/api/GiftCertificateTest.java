package by.megatop.api;

import by.megatop.ui.utils.LoginUtils;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Epic("API Testing")
@Feature("Gift Certificate Service")
@Story("Confirmation Code Sending via Phone")
@DisplayName("API gift certificate functionality tests")
public class GiftCertificateTest {

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
    @Description("Test verifies successful confirmation code sending with valid phone number")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("GS-01")
    public void SendConfirmationCodeWithValidPhone() {
        service.doRequest(LoginUtils.generatePhoneNumberForAPI());

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(OK_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).contains("Код подтверждения отправлен на номер")
        );
    }

    @Test
    @DisplayName("Short phone number - less than 12 digits")
    @Description("Test verifies handling of phone number with less than 12 digits")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("GS-02")
    public void PhoneNumberLessThan12Digits() {
        service.doRequest("375255");

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo(ERROR_PHONE)
        );
    }

    @Test
    @DisplayName("Long phone number - more than 12 digits")
    @Description("Test verifies handling of phone number with more than 12 digits")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("GS-03")
    public void PhoneNumberMoreThan12Digits() {
        service.doRequest(LoginUtils.generatePhoneNumberForAPI() + 345);

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo(ERROR_PHONE)
        );
    }

    @Test
    @DisplayName("Empty phone number")
    @Description("Test verifies handling of empty phone number")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("GS-04")
    public void emptyPhoneNumber() {
        service.doRequest("");

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo("Phone обязательное поле")
        );
    }

    @Test
    @DisplayName("Non-digit phone number")
    @Description("Test verifies handling of phone number containing non-digit characters")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("GS-05")
    public void nonDigitPhoneNumber() {
        service.doRequest("рыпвао");

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo(ERROR_PHONE)
        );
    }

    @Test
    @DisplayName("Phone number is null")
    @Description("Test verifies handling of null phone number value")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("GS-06")
    public void nullPhoneNumber() {
        service.doRequest(null);

        String responseBody = service.getBody();
        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(service.getStatusCode()).isEqualTo(UNPROCESSABLE_ENTITY_CODE),
                () -> assertThat(responseBody).isNotNull().isNotBlank(),
                () -> assertThat(jsonPath.getString("data.message")).isEqualTo(ERROR_MESSAGE),
                () -> assertThat(jsonPath.getList("data.errors.phone").getFirst()).isEqualTo(ERROR_PHONE)
        );
    }
}
