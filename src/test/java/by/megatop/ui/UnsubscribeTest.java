package by.megatop.ui;

import by.megatop.ui.pages.base.BasePage;
import by.megatop.ui.pages.unsubscribe.UnsubscribePage;
import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static by.megatop.ui.utils.EmailUtils.generateRandomGmail;

public class UnsubscribeTest {

    private static final Logger logger = LogManager.getLogger();
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
    public void shouldSendEmailWhenValidEmailProvided() {
        unsubscribePage.sendKeysEmailInput(generateRandomGmail());
        unsubscribePage.clickSubmitButton();

        String expectedResult = "Вам на почту выслано письмо для отписки от рассылки";
        String actualResult = unsubscribePage.getEmailUnsubscribeNotificationText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Email input form header presence")
    public void shouldShowHeaderWhenPageOpened() {
        String expectedResult = "Отписаться от email рассылки";
        String actualResult = unsubscribePage.getFormHeaderText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Email input field placeholder presence")
    public void shouldDisplayPlaceholderWhenInputRendered() {
        String expectedResult = "Введите свой e-mail";
        String actualResult = unsubscribePage.getPlaceholderInputFieldText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Already unsubscribed from the newsletter")
    public void shouldConfirmUnsubscriptionWhenNoEmailProvided() {
        unsubscribePage.clickSubmitButton();

        String expectedResult = "Вы отписались от рассылки";
        String actualResult = unsubscribePage.getSuccessfulUnsubscribeText();

        Assertions.assertEquals(expectedResult, actualResult);
    }
}
