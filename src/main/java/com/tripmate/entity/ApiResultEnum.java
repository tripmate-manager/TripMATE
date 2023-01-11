package com.tripmate.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiResultEnum {
    SUCCESS("0000", "success"),
    MESSAGING("8888", "messaging error"),
    VALIDATION("9000", "Method Argument Type Mismatch"),
    UNKNOWN("9999", "unknown exception");

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
