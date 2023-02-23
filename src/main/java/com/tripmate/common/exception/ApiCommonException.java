package com.tripmate.common.exception;


import lombok.Getter;

@Getter
public class ApiCommonException extends RuntimeException {

    private final String resultCode;
    private final String resultMessage;

    public ApiCommonException(String resultCode, String resultMessage, Throwable throwable) {
        super(resultMessage, throwable);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public ApiCommonException(String resultCode, String resultMessage) {
        super(resultMessage);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getMessageArgumentsToString() {
        return "[" + resultCode + "] " + resultMessage;
    }
}
