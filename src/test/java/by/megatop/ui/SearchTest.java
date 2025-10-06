package by.megatop.ui;

import by.megatop.ui.pages.SearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    public void shouldDisplayCorrectPageHeaderWhenSearchIsPerformed() {
        searchPage.sendKeysSearch("Лоферы");
        searchPage.startSearch();

        String actualResult = "ПОИСК ПО ЗАПРОСУ 'ЛОФЕРЫ'";
        String expectedResult = searchPage.getSearchPageHeaderText();

        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("Zero results for non-existent product")
    public void shouldShow0ResultsCounterWhenNoProductsAreFound() {
        searchPage.sendKeysSearch("полянка");
        searchPage.startSearch();

        String actualResult = "0";
        String expectedResult = searchPage.getCounterText();

        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("Multiple results for existing product")
    public void shouldDisplayNonZeroResultsCounterWhenProductsAreFound() {
        searchPage.sendKeysSearch("шнурки");
        searchPage.startSearch();

        String actualResult = "29 товаров";
        String expectedResult = searchPage.getCounterText();

        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("Single result by product name")
    public void shouldFindExactlyOneProductWhenSearchingByFullProductName() {
        searchPage.sendKeysSearch("Крем-краска WiMi");
        searchPage.startSearch();

        String actualResult = "1 товар";
        String expectedResult = searchPage.getCounterText();

        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("Single result by article number")
    public void shouldFindExactlyOneProductWhenSearchingByProductArticleNumber() {
        searchPage.sendKeysSearch("1403000818");
        searchPage.startSearch();

        String actualResult = "1 товар";
        String expectedResult = searchPage.getCounterText();

        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("All results contain search term")
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
    public void shouldDisplayCorrectProductAsFirstResultWhenSearching() {
        searchPage.sendKeysSearch("кеды");
        searchPage.startSearch();

        List<String> results = searchPage.getSearchResultItemsTitleText();

        Assertions.assertEquals("Кеды", results.getFirst());
    }
}
