package by.megatop.ui.utils;

import by.megatop.ui.webdriver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 30;

    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public static WebElement waitForElementVisible(String xpath) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated((By.xpath(xpath))));
    }

    public static WebElement waitForElementClickable(String xpath) {
        return getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public static void waitForPageToLoad() {
        getWait().until(driver ->
                Objects.equals(((JavascriptExecutor) driver).executeScript("return document.readyState"), "complete")
        );
    }

    public static void waitForElementToDisappear(String xpath) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }
}
