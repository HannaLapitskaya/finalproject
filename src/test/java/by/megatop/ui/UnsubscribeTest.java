package by.megatop.ui;

import by.megatop.ui.pages.BasePage;
import by.megatop.ui.pages.UnsubscribePage;
import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

public class UnsubscribeTest {

    private static final Logger logger = LogManager.getLogger();
    private UnsubscribePage unsubscribePage;
    private BasePage basePage;

    @BeforeEach
    public void openUnsubscribePage() {
        unsubscribePage = new UnsubscribePage();
        basePage = new BasePage();

        unsubscribePage.openUnsubscribePage();
        basePage.clickCityByLocation();
        basePage.clickAcceptCookiesButton();
        WaitUtils.waitForPageToLoad();
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quit();
        logger.info("Driver quit successfully");
    }

    @Test
    @DisplayName("Successful unsubscribe with valid email format")
    public void shouldSendEmailWhenValidEmailProvided() {
        logger.info("Starting test: Successful unsubscribe with valid email format");
        unsubscribePage.sendKeysEmailInput("test@test.com");
        unsubscribePage.clickSubmitButton();

        String expectedResult = "Вам на почту выслано письмо для отписки от рассылки";
        String actualResult = unsubscribePage.getEmailUnsubscribeNotificationText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Successful unsubscribe with valid email format' passed successfully");
    }

    @Test
    @DisplayName("Email input form header presence")
    public void shouldShowHeaderWhenPageOpened() {
        logger.info("Starting test: Email input form header presence");

        String expectedResult = "Отписаться от email рассылки";
        String actualResult = unsubscribePage.getFormHeaderText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Email input form header presence' passed successfully");
    }

    @Test
    @DisplayName("Email input field placeholder presence")
    public void shouldDisplayPlaceholder_WhenInputRendered() {
        logger.info("Starting test: Email input field placeholder presence");

        String expectedResult = "Введите свой e-mail";
        String actualResult = unsubscribePage.getPlaceholderInputFieldText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Email input field placeholder presence' passed successfully");
    }

    @Test
    @DisplayName("Already unsubscribed from the newsletter")
    public void shouldConfirmUnsubscription_WhenNoEmailProvided() {
        logger.info("Starting test: Already unsubscribed from the newsletter");
        unsubscribePage.clickSubmitButton();

        String expectedResult = "Вы отписались от рассылки";
        String actualResult = unsubscribePage.getSuccessfulUnsubscribeText();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test 'Already unsubscribed from the newsletter' passed successfully");
    }
}
