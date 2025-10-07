package by.megatop.api;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        service.doRequest();

        assertThat(service.getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Search API should return correct response structure")
    @Description("Test verifies that search API response contains non-empty categories and products lists")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-02")
    public void searchApiShouldReturnCorrectStructure() {
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(jsonPath.getList("categories").isEmpty()).isFalse(),
                () -> assertThat(jsonPath.getList("products").isEmpty()).isFalse()
        );
    }

    @Test
    @DisplayName("Search API should return correct first product data")
    @Description("Test verifies detailed product information for the first product in search results")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SH-03")
    public void searchApiShouldReturnCorrectFirstProduct() {
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(jsonPath.getString("products[0].name")).isEqualTo("Балетки Enjoin' 0545000302"),
                () -> assertThat(jsonPath.getString("products[0].modelId")).isEqualTo("0545000302"),
                () -> assertThat(jsonPath.getDouble("products[0].price")).isEqualTo(49.95),
                () -> assertThat(jsonPath.getDouble("products[0].priceOld")).isEqualTo(96.43),
                () -> assertThat(jsonPath.getInt("products[0].discount")).isEqualTo(48),
                () -> assertThat(jsonPath.getBoolean("products[0].isOnSale")).isTrue(),
                () -> assertThat(jsonPath.getBoolean("products[0].isNew")).isFalse(),
                () -> assertThat(jsonPath.getBoolean("products[0].isHit")).isFalse()
        );
    }

    @Test
    @DisplayName("Search API should return correct product category")
    @Description("Test verifies category information for products in search results")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-04")
    public void searchApiShouldReturnCorrectCategory() {
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(jsonPath.getString("products[0].mainCategory.name")).isEqualTo("Женщины"),
                () -> assertThat(jsonPath.getString("products[0].mainCategory.header")).isEqualTo("Женские товары")
        );
    }

    @Test
    @DisplayName("Search API should return correct product links")
    @Description("Test verifies that product links are correctly formatted in search results")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-05")
    public void searchApiShouldReturnCorrectLinks() {
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(jsonPath.getString("products[0].link")).isEqualTo("/products/0545000302-baletki-enjoin_")
        );
    }

    @Test
    @DisplayName("Search API should return correct second product")
    @Description("Test verifies product information for the second product in search results")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("SH-06")
    public void searchApiShouldReturnCorrectSecondProduct() {
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(jsonPath.getString("products[1].name")).isEqualTo("Туфли Enjoin' 0545000306"),
                () -> assertThat(jsonPath.getDouble("products[1].price")).isEqualTo(69.95),
                () -> assertThat(jsonPath.getBoolean("products[1].isInStock")).isFalse()
        );
    }
}
