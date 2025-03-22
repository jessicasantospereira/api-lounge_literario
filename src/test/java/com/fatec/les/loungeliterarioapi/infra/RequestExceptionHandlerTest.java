package com.fatec.les.loungeliterarioapi.infra;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RequestExceptionHandlerTest {

    @Test
    void threat404() {
        RequestExceptionHandler requestExceptionHandler = new RequestExceptionHandler();
        assertNotNull(requestExceptionHandler.threat404());
    }
}