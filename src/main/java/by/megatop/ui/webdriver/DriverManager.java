package by.megatop.ui.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class DriverManager {

    private static final int DEFAULT_TIMEOUT = 5;
    private static WebDriver driver;

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

    public static void sendKeys(String xpath, String value) {
        findElement(xpath).sendKeys(value);
    }

    public static String getTextFromElement(String xpath) {
        return findElement(xpath).getText();
    }

    public static String getAttributeFromElement(String xpath, String attribute) {
        return findElement(xpath).getAttribute(attribute);
    }
}
