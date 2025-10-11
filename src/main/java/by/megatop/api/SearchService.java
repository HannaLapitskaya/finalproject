package by.megatop.api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static by.megatop.api.APIConstants.BASE_URL;

public class SearchService extends BaseAPIService {
    private static final String URL = BASE_URL + "/api/v1/search";

    public SearchService() {

    }

    public void doRequest() {
        doGet(URL, getParams());
    }

    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("query", "0579000526");
        return params;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json, text/plain, */*");
        return headers;
    }
}
