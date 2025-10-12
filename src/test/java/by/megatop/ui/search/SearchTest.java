package by.megatop.ui.search;

import by.megatop.ui.base.BaseTest;
import by.megatop.ui.pages.search.SearchExpectedMessages;
import by.megatop.ui.pages.search.SearchPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Epic("UI Testing")
@Feature("Search Functionality")
@Story("Product Search")
@DisplayName("UI search functionality tests")
public class SearchTest extends BaseTest {

    private SearchPage searchPage;

    @BeforeEach
    public void openSearchPage() {
        searchPage = new SearchPage();
        searchPage.clickSearchButtonAndSearchInput();
    }

    @Test
    @DisplayName("Search page header displays correctly")
    @Description("Test verifies that search page header displays correct text after performing search")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-01")
    public void shouldDisplayCorrectPageHeaderWhenSearchIsPerformed() {
        searchPage.sendKeysAndStartSearch("Лоферы");

        Assertions.assertEquals(SearchExpectedMessages.SEARCH_RESULTS_HEADER, searchPage.getSearchPageHeaderText());
    }

    @Test
    @DisplayName("Zero results for non-existent product")
    @Description("Test verifies that search returns 0 results for non-existent product name")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-02")
    public void shouldShow0ResultsCounterWhenNoProductsAreFound() {
        searchPage.sendKeysAndStartSearch("полянка");

        Assertions.assertEquals(SearchExpectedMessages.ZERO_RESULTS_COUNT, searchPage.getCounterText());
    }

    @Test
    @DisplayName("Multiple results for existing product")
    @Description("Test verifies that search returns correct number of results for existing product")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-03")
    public void shouldDisplayNonZeroResultsCounterWhenProductsAreFound() {
        searchPage.sendKeysAndStartSearch("шнурки");

        Assertions.assertEquals(SearchExpectedMessages.MULTIPLE_RESULTS_COUNT, searchPage.getCounterText());
    }

    @Test
    @DisplayName("Single result by product name")
    @Description("Test verifies that search returns exactly one result when searching by full product name")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-04")
    public void shouldFindExactlyOneProductWhenSearchingByFullProductName() {
        searchPage.sendKeysAndStartSearch("1208000465");

        Assertions.assertEquals(SearchExpectedMessages.SINGLE_RESULT_COUNT, searchPage.getCounterText());
    }

    @Test
    @DisplayName("Single result by article number")
    @Description("Test verifies that search returns exactly one result when searching by product article number")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-05")
    public void shouldFindExactlyOneProductWhenSearchingByProductArticleNumber() {
        searchPage.sendKeysAndStartSearch("1403000818");

        Assertions.assertEquals(SearchExpectedMessages.SINGLE_RESULT_COUNT, searchPage.getCounterText());
    }

    @Test
    @DisplayName("All results contain search term")
    @Description("Test verifies that all search results contain the search term in their titles")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-06")
    public void shouldDisplayOnlyProductsContainingSearchTermInTheirTitles() {
        searchPage.sendKeysAndStartSearch("казаки");

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
        searchPage.sendKeysAndStartSearch("кеды");

        List<String> results = searchPage.getSearchResultItemsTitleText();

        Assertions.assertEquals(SearchExpectedMessages.FIRST_RESULT_TITLE, results.getFirst());
    }

    @Test
    @DisplayName("Should display search suggestions and categories when entering query")
    @Description("Verify that search suggestions and categories sections are displayed when starting to type characters")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-08")
    public void shouldDisplaySearchSuggestionsAndCategoriesWhenTyping() {
        searchPage.sendKeysSearch("сумка");

        assertAll(
                () -> assertThat(searchPage.getCategoriesText()).isEqualTo(SearchExpectedMessages.CATEGORIES_SECTION_TITLE),
                () -> assertThat(searchPage.getSimilarQueriesText()).isEqualTo(SearchExpectedMessages.RELATED_SEARCHES_SECTION_TITLE)
        );
    }
}
