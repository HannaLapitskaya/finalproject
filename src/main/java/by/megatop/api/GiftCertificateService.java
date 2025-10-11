package by.megatop.api;

import java.util.HashMap;
import java.util.Map;

import static by.megatop.api.APIConstants.BASE_URL;

public class GiftCertificateService extends BaseAPIService {

    private static final String URL = BASE_URL + "/api/v3/user/verify-phone";
    private static final String BODY_TEMPLATE = "{\"phone\":\"%s\",\"code\":\"6ab5ba719eca923583f21f3ad7b75f05\",\"registration\":false}";

    public GiftCertificateService() {

    }

    public void doRequest(String phone) {
        doPost(BODY_TEMPLATE, URL);
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json, text/plain, */*");

        return headers;
    }
}
