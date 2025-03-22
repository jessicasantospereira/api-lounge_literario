package com.fatec.les.loungeliterarioapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Error {

    // @formatter:off
    INTERNAL_SERVER_ERROR("LL01", HttpStatus.INTERNAL_SERVER_ERROR),
    UNPROCESSABLE_ENTITY("LL02", HttpStatus.UNPROCESSABLE_ENTITY),
    FORBIDEN("LL03", HttpStatus.FORBIDDEN),
    BAD_REQUEST("LL04", HttpStatus.BAD_REQUEST),
    NOT_FOUND("LL05", HttpStatus.NOT_FOUND);
    // @formatter:on

    private final String code;

    private final HttpStatus status;

    Error(String code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }
}
