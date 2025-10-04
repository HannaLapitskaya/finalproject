package by.megatop.ui;

import by.megatop.ui.pages.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("UI search functionality tests")
public class SearchTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();
    private SearchPage searchPage;

    @BeforeEach
    public void openSearchPage() {
        searchPage = new SearchPage();
        searchPage.openSearchButton();
        searchPage.clickSearchInput();
    }

    @Test
    @DisplayName("Search page header displays correctly")
    public void shouldDisplayCorrectPageHeaderWhenSearchIsPerformed() {
        logger.debug("Starting test: Search page header displays correctly");
        searchPage.sendKeysSearch("Лоферы");
        searchPage.startSearch();

        String actualResult = "ПОИСК ПО ЗАПРОСУ 'ЛОФЕРЫ'";
        String expectedResult = searchPage.getSearchPageHeaderText();

        Assertions.assertEquals(actualResult, expectedResult);
        logger.info("Test 'Search page header displays correctly' passed successfully");
    }

    @Test
    @DisplayName("Zero results for non-existent product")
    public void shouldShow0ResultsCounterWhenNoProductsAreFound() {
        logger.debug("Starting test: Zero results for non-existent product");
        searchPage.sendKeysSearch("полянка");
        searchPage.startSearch();

        String actualResult = "0";
        String expectedResult = searchPage.getCounterText();

        Assertions.assertEquals(actualResult, expectedResult);
        logger.info("Test 'Zero results for non-existent product' passed successfully");
    }

    @Test
    @DisplayName("Multiple results for existing product")
    public void shouldDisplayNonZeroResultsCounterWhenProductsAreFound() {
        logger.debug("Starting test: Multiple results for existing product");
        searchPage.sendKeysSearch("шнурки");
        searchPage.startSearch();

        String actualResult = "29 товаров";
        String expectedResult = searchPage.getCounterText();

        Assertions.assertEquals(actualResult, expectedResult);
        logger.info("Test 'Multiple results for existing product' passed successfully");
    }

    @Test
    @DisplayName("Single result by product name")
    public void shouldFindExactlyOneProductWhenSearchingByFullProductName() {
        logger.debug("Starting test: Single result by product name");
        searchPage.sendKeysSearch("Крем-краска WiMi");
        searchPage.startSearch();

        String actualResult = "1 товар";
        String expectedResult = searchPage.getCounterText();

        Assertions.assertEquals(actualResult, expectedResult);
        logger.info("Test 'Single result by product name' passed successfully");
    }

    @Test
    @DisplayName("Single result by article number")
    public void shouldFindExactlyOneProductWhenSearchingByProductArticleNumber() {
        logger.debug("Starting test: Single result by article number");
        searchPage.sendKeysSearch("1403000818");
        searchPage.startSearch();

        String actualResult = "1 товар";
        String expectedResult = searchPage.getCounterText();

        Assertions.assertEquals(actualResult, expectedResult);
        logger.info("Test 'Single result by article number' passed successfully");
    }

    @Test
    @DisplayName("All results contain search term")
    public void shouldDisplayOnlyProductsContainingSearchTermInTheirTitles() {
        logger.debug("Starting test: All results contain search term");
        searchPage.sendKeysSearch("казаки");
        searchPage.startSearch();

        List<String> results = searchPage.getSearchResultItemsTitleText();

        Assertions.assertTrue(results.stream()
                .map(String::toLowerCase)
                .allMatch(title -> title.contains("казаки")));
        logger.info("Test 'All results contain search term' passed successfully");
    }

    @Test
    @DisplayName("First result matches query")
    public void shouldDisplayCorrectProductAsFirstResultWhenSearching() {
        logger.debug("Starting test: First result matches query");
        searchPage.sendKeysSearch("кеды");
        searchPage.startSearch();

        List<String> results = searchPage.getSearchResultItemsTitleText();

        Assertions.assertEquals("Кеды", results.getFirst());
        logger.info("Test 'First result matches query' passed successfully");
    }
}
