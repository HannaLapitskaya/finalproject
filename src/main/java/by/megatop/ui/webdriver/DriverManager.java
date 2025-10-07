package by.megatop.ui.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static by.megatop.ui.utils.WaitUtils.waitForElementClickable;
import static by.megatop.ui.utils.WaitUtils.waitForElementVisible;

public class DriverManager {

    private static final int DEFAULT_TIMEOUT = 20;
    private static WebDriver driver;
    private static final int MAX_RETRY_ATTEMPTS = 4;

    private DriverManager() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

    public static WebElement findElement(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    public static List<WebElement> findElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public static void clickElement(String xpath) {
        findElement(xpath).click();
    }

    public static void clickElementWhenClickable(String xpath) {
        waitForElementClickable(xpath).click();
    }

    public static void clickElementWhenClickableWithRetry(String xpath) {
        for (int i = 0; i < MAX_RETRY_ATTEMPTS; i++) {
            try {
                waitForElementClickable(xpath).click();
                return;
            } catch (Exception e) {
                if (i == 2) throw new RuntimeException("Click failed after 3 attempts: " + xpath, e);
            }
        }
    }

    public static void sendKeys(String xpath, String value) {
        findElement(xpath).sendKeys(value);
    }

    public static String getTextFromElement(String xpath) {
        return findElement(xpath).getText();
    }

    public static String getTextFromElementWhenVisible(String xpath) {
        return waitForElementVisible(xpath).getText();
    }

    public static String getTextFromElementWhenVisibleWithRetry(String xpath) {
        for (int i = 0; i < MAX_RETRY_ATTEMPTS; i++) {
            try {
                return waitForElementVisible(xpath).getText();
            } catch (StaleElementReferenceException e) {
                if (i == 2) throw e;
            }
        }
        return null;
    }

    public static String getAttributeFromElement(String xpath, String attribute) {
        return findElement(xpath).getAttribute(attribute);
    }
}
