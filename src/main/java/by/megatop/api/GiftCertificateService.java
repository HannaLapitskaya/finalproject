package by.megatop.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static by.megatop.api.APIConstants.BASE_URL;
import static io.restassured.RestAssured.given;

public class GiftCertificateService {

    private static final String URL = BASE_URL + "/api/v3/user/verify-phone";
    private static final String BODY_TEMPLATE = "{\"phone\":\"%s\",\"code\":\"6ab5ba719eca923583f21f3ad7b75f05\",\"registration\":false}";
    private Response response;

    public GiftCertificateService() {

    }

    public void doRequest(String phone) {
        response = given()
                .headers(getHeaders())
                .body(String.format(BODY_TEMPLATE, phone))
                .when().post(URL);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getBody() {
        return response.getBody().asPrettyString();
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json, text/plain, */*");

        return headers;
    }

    public JsonPath getJsonPath() {
        return new JsonPath(getBody());
    }
}
