package by.megatop.api.base;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Map;

public abstract class BaseAPIService {

    private Response response;

    public void doPost(String body, String URL) {
        response = given()
                .headers(getHeaders())
                .body(body)
                .when().post(URL);
    }

    public void doGet(String URL, Map<String, String> params) {
        response = given()
                .headers(getHeaders())
                .queryParams(params)
                .when().get(URL);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getBody() {
        return response.getBody().asPrettyString();
    }

    public JsonPath getJsonPath() {
        return new JsonPath(getBody());
    }

    protected abstract Map<String, String> getHeaders();
}
