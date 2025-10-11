package by.megatop.api.login;

import by.megatop.api.base.APIConstants;
import by.megatop.api.base.BaseAPIService;

import java.util.HashMap;
import java.util.Map;

public class LoginService extends BaseAPIService {

    private static final String URL = APIConstants.BASE_URL + "/api/v1/user/login";
    private static final String BODY_DEFAULT = "{\"email\":\"375250000000\",\"password\":\"123123123123123\"}";
    private static final String BODY_TEMPLATE = "{\"email\":\"%s\",\"password\":\"%s\"}";

    public LoginService() {

    }

    public void doRequest() {
        doPost(BODY_DEFAULT, URL);
    }

    public void doRequest(String phone, String password) {
        doPost(String.format(BODY_TEMPLATE, phone, password), URL);
    }

    @Override
    protected Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("accept", "application/json, text/plain, */*");

        return headers;
    }
}
