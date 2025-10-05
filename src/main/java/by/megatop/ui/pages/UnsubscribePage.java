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
        logger.debug("Opening unsubscribe page");
        DriverManager.getDriver().get(UNSUBSCRIBE_PAGE_URL);
        logger.info("Unsubscribe page opened");
    }

    public void sendKeysEmailInput(String email) {
        logger.debug("Sending keys to email input");
        WebElement emailInput = DriverManager.getDriver().findElement(By.xpath(INPUT_EMAIL));
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].removeAttribute('autocomplete');", emailInput);

        DriverManager.sendKeys(INPUT_EMAIL, email);
        logger.info("Email entered: " + email);
    }

    public void clickSubmitButton() {
        logger.debug("Clicking submit button");
        DriverManager.clickElementWhenClickable(BUTTON_SUBMIT);
        logger.info("Submit button clicked");
    }

    public String getEmailUnsubscribeNotificationText() {
        logger.debug("Getting email unsubscribe notification text");
        return DriverManager.getTextFromElementWhenVisible(TEXT_SUCCESS_MESSAGE);
    }

    public String getFormHeaderText() {
        logger.debug("Getting form header text");
        return DriverManager.getTextFromElementWhenVisible(EMAIL_FORM_HEADER);
    }

    public String getPlaceholderInputFieldText() {
        logger.debug("Getting placeholder input field text");
        return DriverManager.getTextFromElementWhenVisible(PLACEHOLDER_INPUT_FIELD);
    }

    public String getSuccessfulUnsubscribeText() {
        logger.debug("Getting successful unsubscribe text");
        return DriverManager.getTextFromElement(UNSUBSCRIBE_CONFIRMATION_TEXT);
    }
}
