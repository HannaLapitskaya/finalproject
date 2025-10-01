package by.megatop.ui.pages;

import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;


public class UnsubscribePage {

    private static final Logger logger = LogManager.getLogger();
    private static final String UNSUBSCRIBE_PAGE_URL = "https://megatop.by/email_unsubscribe";
    private static final String INPUT_EMAIL = "//input[@type='text']";
    private static final String BUTTON_SUBMIT = "(//button[@type='button'])[2]/span";
    private static final String TEXT_SUCCESS_MESSAGE = "//div[contains(text(), 'Вам на почту выслано письмо для отписки от рассылки')]";
    private static final String EMAIL_FORM_HEADER = "(//div[@class='d-flex flex-column']/div)[1]";
    private static final String PLACEHOLDER_INPUT_FIELD = "//div[@class='subscription__input-label']";
    private static final String UNSUBSCRIBE_CONFIRMATION_TEXT = "//div[@class='w-100 d-flex justify-center align-center']/div";

    public UnsubscribePage() {

    }

    public void openUnsubscribePage() {
        DriverManager.getDriver().get(UNSUBSCRIBE_PAGE_URL);
        logger.info("Unsubscribe page opened successfully");
    }

    public void sendKeysEmailInput(String email) {
        WebElement emailInput = DriverManager.getDriver().findElement(By.xpath(INPUT_EMAIL));
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].removeAttribute('autocomplete');", emailInput);

        DriverManager.sendKeys(INPUT_EMAIL, email);
    }

    public void clickSubmitButton() {
        WaitUtils.waitForElementClickable(BUTTON_SUBMIT);
        DriverManager.clickElement(BUTTON_SUBMIT);
    }

    public String getEmailUnsubscribeNotificationText() {
        WaitUtils.waitForElementVisible(TEXT_SUCCESS_MESSAGE);
        return DriverManager.getTextFromElement(TEXT_SUCCESS_MESSAGE);
    }

    public String getFormHeaderText() {
        WaitUtils.waitForElementVisible(EMAIL_FORM_HEADER);
        return DriverManager.getTextFromElement(EMAIL_FORM_HEADER);
    }

    public String getPlaceholderInputFieldText() {
        return DriverManager.getTextFromElement(PLACEHOLDER_INPUT_FIELD);
    }

    public String getSuccessfulUnsubscribeText() {
        return DriverManager.getTextFromElement(UNSUBSCRIBE_CONFIRMATION_TEXT);
    }
}
