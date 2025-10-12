package by.megatop.api.search;

import by.megatop.api.base.BaseAPIService;

import java.util.HashMap;
import java.util.Map;

import static by.megatop.api.base.APIConstants.BASE_URL;

public class SearchService extends BaseAPIService {

    private static final String URL = BASE_URL + "/api/v1/search";

    public SearchService() {

    }

    public void doSearchRequest() {
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
