package by.megatop.ui.pages;

import by.megatop.ui.utils.WaitUtils;
import by.megatop.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static by.megatop.ui.webdriver.DriverManager.getDriver;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage {

    private static final Logger logger = LogManager.getLogger();
    private static final String BUTTON_SEARCH = "//button[@class='px-0 btn-wrap v-btn v-btn--text theme--light v-size--default white--text']";
    private static final String INPUT_SEARCH = "//input[@ autofocus ='autofocus']";
    private static final String HEADER_SEARCH_PAGE = "//h1[@class='catalog__title text-uppercase']";
    private static final String COUNTER = "//span[@class='ml-4 my-auto catalog__title-count']";
    private static final String SEARCH_RESULTS = "(//div[@class='pa-0 content__title col col-12'])[1]";

    public SearchPage() {

    }

    public void openSearchButton() {
        logger.debug("Opening search input");
        DriverManager.clickElement(BUTTON_SEARCH);
        logger.info("Search input opened");
    }

    public void clickSearchInput() {
        logger.debug("Clicking on search input");
        DriverManager.clickElement(INPUT_SEARCH);
        logger.info("Search input clicked");
    }

    public void sendKeysSearch(String search) {
        logger.debug("Sending keys to search input");
        WaitUtils.waitForElementClickable(INPUT_SEARCH);
        DriverManager.sendKeys(INPUT_SEARCH, search);
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
        WaitUtils.waitForElementVisible(HEADER_SEARCH_PAGE);
        return DriverManager.getTextFromElement(HEADER_SEARCH_PAGE);
    }

    public String getCounterText() {
        logger.debug("Getting counter text");
        return DriverManager.getTextFromElement(COUNTER);
    }

    public List<String> getSearchResultItemsTitleText() {
        logger.debug("Getting search result items titles");
        return DriverManager.findElements(SEARCH_RESULTS).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
