package by.megatop.api;

import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SearchTest {

    private static final Logger logger = LogManager.getLogger();
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
    public void searchApiShouldReturn200StatusCode() {
        logger.debug("Starting test: Search API status code validation");
        service.doRequest();

        assertThat(service.getStatusCode()).isEqualTo(200);
        logger.info("Test completed successfully: Search API status code validation");
    }

    @Test
    @DisplayName("Search API should return correct response structure")
    public void searchApiShouldReturnCorrectStructure() {
        logger.debug("Starting test: Search API structure validation");
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(jsonPath.getList("categories").isEmpty()).isFalse(),
                () -> assertThat(jsonPath.getList("products").isEmpty()).isFalse()
        );
        logger.info("Test completed successfully: Search API structure validation");
    }

    @Test
    @DisplayName("Search API should return correct first product data")
    public void searchApiShouldReturnCorrectFirstProduct() {
        logger.debug("Starting test: First product data validation");
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
        logger.info("Test completed successfully: First product data validation");
    }

    @Test
    @DisplayName("Search API should return correct product category")
    public void searchApiShouldReturnCorrectCategory() {
        logger.debug("Starting test: Product category validation");
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(jsonPath.getString("products[0].mainCategory.name")).isEqualTo("Женщины"),
                () -> assertThat(jsonPath.getString("products[0].mainCategory.header")).isEqualTo("Женские товары")
        );
        logger.info("Test completed successfully: Product category validation");
    }

    @Test
    @DisplayName("Search API should return correct product links")
    public void searchApiShouldReturnCorrectLinks() {
        logger.debug("Starting test: Product links validation");
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(jsonPath.getString("products[0].link")).isEqualTo("/products/0545000302-baletki-enjoin_")
        );
        logger.info("Test completed successfully: Product links validation");
    }

    @Test
    @DisplayName("Search API should return correct second product")
    public void searchApiShouldReturnCorrectSecondProduct() {
        logger.debug("Starting test: Second product validation");
        service.doRequest();

        JsonPath jsonPath = service.getJsonPath();

        assertAll(
                () -> assertThat(jsonPath.getString("products[1].name")).isEqualTo("Туфли Enjoin' 0545000306"),
                () -> assertThat(jsonPath.getDouble("products[1].price")).isEqualTo(69.95),
                () -> assertThat(jsonPath.getBoolean("products[1].isInStock")).isFalse()
        );
        logger.info("Test completed successfully: Second product validation");
    }
}
