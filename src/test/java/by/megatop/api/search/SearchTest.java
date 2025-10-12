package by.megatop.api.search;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Epic("API Testing")
@Feature("Search Functionality")
@Story("Product Search API")
@DisplayName("API search functionality tests")
public class SearchTest {

    private SearchService service;

    @BeforeEach
    void setUp() {
        service = new SearchService();
    }

    @AfterEach
    void tearDown() {
        service = null;
    }

    @Test
    @DisplayName("Search API should return 200 status code")
    @Description("Test verifies that search API returns 200 OK status code for successful request")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("SH-01")
    public void searchApiShouldReturn200StatusCode() {
        service.doSearchRequest();

        assertThat(service.getStatusCode()).isEqualTo(SearchExpectedMessages.SUCCESS_STATUS_CODE);
    }

    @Test
    @DisplayName("Search API should return correct response structure")
    @Description("Test verifies that search API response contains non-empty categories and products lists")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-02")
    public void searchApiShouldReturnCorrectStructure() {
        service.doSearchRequest();

        JsonPath jsonBody = service.getJsonBody();

        assertAll(
                () -> assertThat(jsonBody.getList("products").isEmpty()).isFalse()
        );
    }

    @Test
    @DisplayName("Search API should return correct first product data")
    @Description("Test verifies detailed product information for the first product in search results")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-03")
    public void searchApiShouldReturnCorrectFirstProduct() {
        service.doSearchRequest();

        JsonPath jsonBody = service.getJsonBody();

        assertAll(
                () -> assertThat(jsonBody.getString("products[0].name")).isEqualTo(SearchExpectedMessages.FIRST_PRODUCT_NAME),
                () -> assertThat(jsonBody.getString("products[0].modelId")).isEqualTo(SearchExpectedMessages.FIRST_PRODUCT_MODEL_ID),
                () -> assertThat(jsonBody.getDouble("products[0].price")).isEqualTo(SearchExpectedMessages.FIRST_PRODUCT_PRICE),
                () -> assertThat(jsonBody.getInt("products[0].discount")).isEqualTo(SearchExpectedMessages.FIRST_PRODUCT_DISCOUNT),
                () -> assertThat(jsonBody.getBoolean("products[0].isOnSale")).isFalse(),
                () -> assertThat(jsonBody.getBoolean("products[0].isNew")).isFalse(),
                () -> assertThat(jsonBody.getBoolean("products[0].isHit")).isFalse(),
                () -> assertThat(jsonBody.getBoolean("products[0].isInStock")).isTrue()
        );
    }

    @Test
    @DisplayName("Search API should return correct product category")
    @Description("Test verifies category information for products in search results")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-04")
    public void searchApiShouldReturnCorrectCategory() {
        service.doSearchRequest();

        JsonPath jsonBody = service.getJsonBody();

        assertAll(
                () -> assertThat(jsonBody.getString("products[0].mainCategory.name")).isEqualTo(SearchExpectedMessages.MAIN_CATEGORY_NAME),
                () -> assertThat(jsonBody.getString("products[0].mainCategory.header")).isEqualTo(SearchExpectedMessages.MAIN_CATEGORY_HEADER),
                () -> assertThat(jsonBody.getString("products[0].mainCategory._id")).isEqualTo(SearchExpectedMessages.MAIN_CATEGORY_ID),
                () -> assertThat(jsonBody.getInt("products[0].mainCategory.rubricId")).isEqualTo(SearchExpectedMessages.MAIN_CATEGORY_RUBRIC_ID)
        );
    }

    @Test
    @DisplayName("Search API should return correct product links")
    @Description("Test verifies that product links are correctly formatted in search results")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-05")
    public void searchApiShouldReturnCorrectLinks() {
        service.doSearchRequest();

        JsonPath jsonBody = service.getJsonBody();

        assertAll(
                () -> assertThat(jsonBody.getString("products[0].link")).isEqualTo(SearchExpectedMessages.FIRST_PRODUCT_LINK),
                () -> assertThat(jsonBody.getString("products[0].url")).isEqualTo(SearchExpectedMessages.FIRST_PRODUCT_URL));
    }

    @Test
    @DisplayName("Search API should return correct second product")
    @Description("Test verifies product information for the second product in search results")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-06")
    public void searchApiShouldReturnCorrectSecondProduct() {
        service.doSearchRequest();

        JsonPath jsonBody = service.getJsonBody();
        List<String> images = jsonBody.getList("products[0].images");

        assertAll(
                () -> assertThat(images.getFirst()).isEqualTo(SearchExpectedMessages.PRODUCT_FIRST_IMAGE),
                () -> assertThat(images.getLast()).isEqualTo(SearchExpectedMessages.PRODUCT_LAST_IMAGE)
        );
    }

    @Test
    @DisplayName("Search API should return correct product additional info")
    @Description("Test verifies additional product information like brand, article, and availability")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-07")
    public void searchApiShouldReturnCorrectAdditionalInfo() {
        service.doSearchRequest();

        JsonPath jsonBody = service.getJsonBody();

        assertAll(
                () -> assertThat(jsonBody.getString("products[0].brand")).isEqualTo(SearchExpectedMessages.PRODUCT_BRAND),
                () -> assertThat(jsonBody.getString("products[0].article")).isEqualTo(SearchExpectedMessages.PRODUCT_ARTICLE),
                () -> assertThat(jsonBody.getString("products[0].fullCategory")).isEqualTo(SearchExpectedMessages.PRODUCT_FULL_CATEGORY),
                () -> assertThat(jsonBody.getString("products[0].madeIn")).isEqualTo(SearchExpectedMessages.PRODUCT_MADE_IN),
                () -> assertThat(jsonBody.getBoolean("products[0].isAvailableForOrder")).isTrue(),
                () -> assertThat(jsonBody.getBoolean("products[0].isStockProduct")).isFalse()
        );
    }

    @Test
    @DisplayName("Search API should return correct size information")
    @Description("Test verifies product sizes and size prices in search results")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-08")
    public void searchApiShouldReturnCorrectSizeInfo() {
        service.doSearchRequest();

        JsonPath jsonBody = service.getJsonBody();

        assertAll(
                () -> assertThat(jsonBody.getString("products[0].sizePrices[0].modelId")).isEqualTo(SearchExpectedMessages.FIRST_SIZE_PRICE_MODEL_ID),
                () -> assertThat(jsonBody.getInt("products[0].sizePrices[0].size")).isEqualTo(SearchExpectedMessages.SIZE_PRICE),
                () -> assertThat(jsonBody.getDouble("products[0].sizePrices[0].price")).isEqualTo(SearchExpectedMessages.FIRST_SIZE_PRICE_PRICE),
                () -> assertThat(jsonBody.getString("products[0].sizePrices[0].type")).isEqualTo(SearchExpectedMessages.FIRST_SIZE_PRICE_TYPE)
        );
    }
}
