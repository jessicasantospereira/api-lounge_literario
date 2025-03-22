package com.fatec.les.loungeliterarioapi.infra;

import com.fatec.les.loungeliterarioapi.exceptions.DomainException;
import com.fatec.les.loungeliterarioapi.exceptions.Error;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RequestExceptionHandlerTest {

    @Test
    void threat404() {
        RequestExceptionHandler requestExceptionHandler = new RequestExceptionHandler();
        assertNotNull(requestExceptionHandler.threat404());
    }

    @Test
    void threatDomainException() {
        RequestExceptionHandler requestExceptionHandler = new RequestExceptionHandler();
        var exception = new DomainException(Error.NOT_FOUND, "teste");
        assertNotNull(requestExceptionHandler.threatDomainException(exception));
    }
}