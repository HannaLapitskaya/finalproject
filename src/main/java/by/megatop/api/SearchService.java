package by.megatop.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static by.megatop.api.APIConstants.BASE_URL;
import static io.restassured.RestAssured.given;

public class SearchService {

    private static final String URL = BASE_URL + "/api/v1/search";
    private Response response;

    public SearchService() {

    }

    public void doRequest() {
        response = given()
                .headers(getHeaders())
                .queryParam("query", "туфли")
                .when().get(URL);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public JsonPath getJsonPath() {
        return new JsonPath(response.getBody().asString());
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json, text/plain, */*");
        return headers;
    }
}
