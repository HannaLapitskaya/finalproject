package by.megatop.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected LoginService service;

    @BeforeEach
    void setUp() {
        service =  new LoginService();
    }

    @AfterEach
    void  tearDown() {
        service = null;
    }
}
