package by.megatop.ui;

import by.megatop.ui.pages.base.BasePage;
import by.megatop.ui.pages.unsubscribe.UnsubscribePage;
import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static by.megatop.ui.utils.EmailUtils.generateRandomGmail;

@Epic("UI Testing")
@Feature("Email Subscriptions")
@Story("Unsubscribe Functionality")
@DisplayName("UI Unsubscribe functionality tests")
public class UnsubscribeTest {

    private UnsubscribePage unsubscribePage;
    private BasePage basePage;

    @BeforeEach
    public void openUnsubscribePage() {
        unsubscribePage = new UnsubscribePage();
        basePage = new BasePage();

        unsubscribePage.openUnsubscribePage();
        WaitUtils.waitForPageToLoad();
        basePage.clickCityByLocation();
        basePage.clickAcceptCookiesButton();
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quit();
    }

    @Test
    @DisplayName("Successful unsubscribe with valid email format")
    @Description("Test verifies successful unsubscribe process with valid email address")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("UNS-01")
    public void shouldSendEmailWhenValidEmailProvided() {
        unsubscribePage.sendKeysEmailInput(generateRandomGmail());
        unsubscribePage.clickSubmitButton();

        String expectedResult = "Вам на почту выслано письмо для отписки от рассылки";

        Assertions.assertEquals(expectedResult, unsubscribePage.getEmailUnsubscribeNotificationText());
    }

    @Test
    @DisplayName("Email input form header presence")
    @Description("Test verifies that unsubscribe form header is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("UNS-02")
    public void shouldShowHeaderWhenPageOpened() {
        String expectedResult = "Отписаться от email рассылки";

        Assertions.assertEquals(expectedResult, unsubscribePage.getFormHeaderText());
    }

    @Test
    @DisplayName("Email input field placeholder presence")
    @Description("Test verifies that email input field placeholder text is displayed")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("UNS-03")
    public void shouldDisplayPlaceholderWhenInputRendered() {
        String expectedResult = "Введите свой e-mail";

        Assertions.assertEquals(expectedResult, unsubscribePage.getPlaceholderInputFieldText());
    }

    @Test
    @DisplayName("Already unsubscribed from the newsletter")
    @Description("Test verifies unsubscribe confirmation when no email is provided")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("UNS-04")
    public void shouldConfirmUnsubscriptionWhenNoEmailProvided() {
        unsubscribePage.clickSubmitButton();

        String expectedResult = "Вы отписались от рассылки";

        Assertions.assertEquals(expectedResult, unsubscribePage.getSuccessfulUnsubscribeText());
    }
}
