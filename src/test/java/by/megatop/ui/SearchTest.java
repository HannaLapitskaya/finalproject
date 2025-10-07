package by.megatop.ui;

import by.megatop.ui.pages.search.SearchExpectedMessages;
import by.megatop.ui.pages.search.SearchPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@Epic("UI Testing")
@Feature("Search Functionality")
@Story("Product Search")
@DisplayName("UI search functionality tests")
public class SearchTest extends BaseTest {

    private SearchPage searchPage;

    @BeforeEach
    public void openSearchPage() {
        searchPage = new SearchPage();
        searchPage.openSearchButton();
        searchPage.clickSearchInput();
    }

    @Test
    @DisplayName("Search page header displays correctly")
    @Description("Test verifies that search page header displays correct text after performing search")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-01")
    public void shouldDisplayCorrectPageHeaderWhenSearchIsPerformed() {
        searchPage.sendKeysSearch("Лоферы");
        searchPage.startSearch();

        Assertions.assertEquals(SearchExpectedMessages.SEARCH_RESULTS_HEADER, searchPage.getSearchPageHeaderText());
    }

    @Test
    @DisplayName("Zero results for non-existent product")
    @Description("Test verifies that search returns 0 results for non-existent product name")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-02")
    public void shouldShow0ResultsCounterWhenNoProductsAreFound() {
        searchPage.sendKeysSearch("полянка");
        searchPage.startSearch();

        Assertions.assertEquals(SearchExpectedMessages.ZERO_RESULTS_COUNT, searchPage.getCounterText());
    }

    @Test
    @DisplayName("Multiple results for existing product")
    @Description("Test verifies that search returns correct number of results for existing product")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-03")
    public void shouldDisplayNonZeroResultsCounterWhenProductsAreFound() {
        searchPage.sendKeysSearch("шнурки");
        searchPage.startSearch();

        Assertions.assertEquals(SearchExpectedMessages.MULTIPLE_RESULTS_COUNT, searchPage.getCounterText());
    }

    @Test
    @DisplayName("Single result by product name")
    @Description("Test verifies that search returns exactly one result when searching by full product name")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-04")
    public void shouldFindExactlyOneProductWhenSearchingByFullProductName() {
        searchPage.sendKeysSearch("Крем-краска WiMi");
        searchPage.startSearch();

        Assertions.assertEquals(SearchExpectedMessages.SINGLE_RESULT_COUNT, searchPage.getCounterText());
    }

    @Test
    @DisplayName("Single result by article number")
    @Description("Test verifies that search returns exactly one result when searching by product article number")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-05")
    public void shouldFindExactlyOneProductWhenSearchingByProductArticleNumber() {
        searchPage.sendKeysSearch("1403000818");
        searchPage.startSearch();

        Assertions.assertEquals(SearchExpectedMessages.SINGLE_RESULT_COUNT, searchPage.getCounterText());
    }

    @Test
    @DisplayName("All results contain search term")
    @Description("Test verifies that all search results contain the search term in their titles")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-06")
    public void shouldDisplayOnlyProductsContainingSearchTermInTheirTitles() {
        searchPage.sendKeysSearch("казаки");
        searchPage.startSearch();

        List<String> results = searchPage.getSearchResultItemsTitleText();

        Assertions.assertTrue(results.stream()
                .map(String::toLowerCase)
                .allMatch(title -> title.contains("казаки")));
    }

    @Test
    @DisplayName("First result matches query")
    @Description("Test verifies that the first search result matches the search query")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-07")
    public void shouldDisplayCorrectProductAsFirstResultWhenSearching() {
        searchPage.sendKeysSearch("кеды");
        searchPage.startSearch();

        List<String> results = searchPage.getSearchResultItemsTitleText();

        Assertions.assertEquals(SearchExpectedMessages.FIRST_RESULT_TITLE, results.getFirst());
    }
}
