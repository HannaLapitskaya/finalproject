package by.megatop.ui.pages.search;

import by.megatop.utils.WaitUtils;
import by.megatop.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

import static by.megatop.webdriver.DriverManager.getDriver;

public class SearchPage {

    private static final Logger logger = LogManager.getLogger(SearchPage.class);

    public SearchPage() {

    }

    public void clickSearchButton() {
        logger.debug("Opening search input");
        DriverManager.clickElement(SearchLocators.BUTTON_SEARCH);
        logger.info("Search input opened");
    }

    public void clickSearchInput() {
        logger.debug("Clicking on search input");
        DriverManager.clickElement(SearchLocators.INPUT_SEARCH);
        logger.info("Search input clicked");
    }

    public void clickSearchButtonAndSearchInput() {
        clickSearchButton();
        clickSearchInput();
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

    public void sendKeysAndStartSearch(String search) {
        sendKeysSearch(search);
        startSearch();
    }

    public String getSearchPageHeaderText() {
        logger.debug("Getting text from search page header");
        String headerText = DriverManager.getTextFromElementWhenVisible(SearchLocators.HEADER_SEARCH_PAGE);
        logger.info("Retrieved search page header: {}", headerText);
        return headerText;
    }

    public String getCounterText() {
        logger.debug("Getting counter text");
        String counterText = DriverManager.getTextFromElement(SearchLocators.COUNTER);
        logger.info("Retrieved counter text: {}", counterText);
        return counterText;
    }

    public List<String> getSearchResultItemsTitleText() {
        logger.debug("Getting search result items titles");
        List<String> titles = DriverManager.findElements(SearchLocators.SEARCH_RESULTS).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        logger.info("Retrieved {} search result items titles", titles.size());
        return titles;
    }

    public String getSimilarQueriesText() {
        logger.debug("Getting similar queries text");
        String similarQueriesText = DriverManager.getTextFromElement(SearchLocators.SIMILAR_QUERIES_TEXT);
        logger.info("Retrieved similar queries text: {}", similarQueriesText);
        return similarQueriesText;
    }

    public String getCategoriesText() {
        logger.debug("Getting categories text");
        String categoriesText = DriverManager.getTextFromElement(SearchLocators.CATEGORIES_SECTION);
        logger.info("Retrieved categories text: {}", categoriesText);
        return categoriesText;
    }
}
