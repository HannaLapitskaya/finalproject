package by.megatop.ui.pages.search;

import by.megatop.utils.WaitUtils;
import by.megatop.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static by.megatop.webdriver.DriverManager.getDriver;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage {

    private static final Logger logger = LogManager.getLogger();

    public SearchPage() {

    }

    public void openSearchButton() {
        logger.debug("Opening search input");
        DriverManager.clickElement(SearchLocators.BUTTON_SEARCH);
        logger.info("Search input opened");
    }

    public void clickSearchInput() {
        logger.debug("Clicking on search input");
        DriverManager.clickElement(SearchLocators.INPUT_SEARCH);
        logger.info("Search input clicked");
    }

    public void sendKeysSearch(String search) {
        logger.debug("Sending keys to search input");
        WaitUtils.waitForElementClickable(SearchLocators.INPUT_SEARCH);
        DriverManager.sendKeys(SearchLocators.INPUT_SEARCH, search);
        logger.info("Search keys entered");
    }

    public void startSearch() {
        logger.debug("Starting search by pressing ENTER");
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ENTER).perform();
        logger.info("Search started successfully by pressing ENTER");
    }

    public String getSearchPageHeaderText() {
        logger.debug("Getting text from search page header");
        return DriverManager.getTextFromElementWhenVisible(SearchLocators.HEADER_SEARCH_PAGE);
    }

    public String getCounterText() {
        logger.debug("Getting counter text");
        return DriverManager.getTextFromElement(SearchLocators.COUNTER);
    }

    public List<String> getSearchResultItemsTitleText() {
        logger.debug("Getting search result items titles");
        return DriverManager.findElements(SearchLocators.SEARCH_RESULTS).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
