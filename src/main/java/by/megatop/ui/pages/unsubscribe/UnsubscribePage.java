package by.megatop.ui.pages.unsubscribe;

import by.megatop.utils.WaitUtils;
import by.megatop.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class UnsubscribePage {

    private static final Logger logger = LogManager.getLogger(UnsubscribePage.class);

    public UnsubscribePage() {

    }

    public void openUnsubscribePage() {
        logger.debug("Opening unsubscribe page");
        DriverManager.getDriver().get(UnsubscribeLocators.UNSUBSCRIBE_PAGE_URL);
        WaitUtils.waitForPageToLoad();
        logger.info("Unsubscribe page opened");
    }

    public void sendKeysEmailInput(String email) {
        logger.debug("Sending keys to email input");
        DriverManager.getDriver().findElement(By.xpath(UnsubscribeLocators.INPUT_EMAIL));
        DriverManager.sendKeys(UnsubscribeLocators.INPUT_EMAIL, email);
        logger.info("Email entered");
    }

    public void clickSubmitButton() {
        logger.debug("Clicking submit button");
        DriverManager.clickElementWhenClickable(UnsubscribeLocators.BUTTON_SUBMIT);
        logger.info("Submit button clicked");
    }

    public String getEmailUnsubscribeNotificationText() {
        logger.debug("Getting email unsubscribe notification text");
        return DriverManager.getTextFromElementWhenVisible(UnsubscribeLocators.TEXT_SUCCESS_MESSAGE);
    }

    public String getFormHeaderText() {
        logger.debug("Getting form header text");
        return DriverManager.getTextFromElementWhenVisibleWithRetry(UnsubscribeLocators.EMAIL_FORM_HEADER);
    }

    public String getPlaceholderInputFieldText() {
        logger.debug("Getting placeholder input field text");
        return DriverManager.getTextFromElementWhenVisibleWithRetry(UnsubscribeLocators.PLACEHOLDER_INPUT_FIELD);
    }

    public String getSuccessfulUnsubscribeText() {
        logger.debug("Getting successful unsubscribe text");
        return DriverManager.getTextFromElement(UnsubscribeLocators.UNSUBSCRIBE_CONFIRMATION_TEXT);
    }
}
