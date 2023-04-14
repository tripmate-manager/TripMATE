package com.tripmate.common.exception;

import lombok.Getter;

@Getter
public class FileUploadException extends RuntimeException {
    private final String resultCode;
    private final String resultMessage;

    public FileUploadException(String resultCode, String resultMessage, Throwable throwable) {
        super(resultMessage, throwable);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public FileUploadException(String resultCode, String resultMessage) {
        super(resultMessage);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getMessageArgumentsToString() {
        return "[" + resultCode + "] " + resultMessage;
    }
}
