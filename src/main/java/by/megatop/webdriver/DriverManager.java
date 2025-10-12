package by.megatop.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static by.megatop.utils.WaitUtils.waitForElementClickable;
import static by.megatop.utils.WaitUtils.waitForElementVisible;

public class DriverManager {

    private static final int DEFAULT_TIMEOUT = 20;
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static WebDriver driver;

    private DriverManager() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--disable-extensions");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
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
                if (i == MAX_RETRY_ATTEMPTS - 1) throw new RuntimeException("Click failed after 3 attempts: " + xpath, e);
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
                if (i == MAX_RETRY_ATTEMPTS - 1) throw e;
            }
        }
        return null;
    }
}
