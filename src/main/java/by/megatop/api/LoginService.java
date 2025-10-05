package by.megatop.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private static final String URL = APIConstants.BASE_URL + "/api/v1/user/login";
    private static final String BODY_DEFAULT = "{\"email\":\"375250000000\",\"password\":\"123123123123123\"}";
    private static final String BODY_TEMPLATE = "{\"email\":\"%s\",\"password\":\"%s\"}";

    private Response response;

    public LoginService() {

    }

    public void doRequest() {
        response = given()
                .headers(getHeaders())
                .body(BODY_DEFAULT)
                .when().post(URL);
    }

    public void doRequest(String body) {
        response = given()
                .headers(getHeaders())
                .body(body)
                .when().post(URL);
    }

    public void doRequest(String phone, String password) {
        response = given()
                .headers(getHeaders())
                .body(String.format(BODY_TEMPLATE, phone, password))
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
        headers.put("accept", "application/json, text/plain, */*");

        return headers;
    }

    public JsonPath getJsonPath() {
        return new JsonPath(getBody());
    }
}
