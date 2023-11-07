package com.ryconnect.appname.api.exception;

import java.util.Map;

import com.ryconnect.appname.api.enums.ErrorCodes;

public class ApplicationException extends Exception {
    private ErrorCodes errorCode;
    private Map<String, Object> metaData;

    public ApplicationException(Throwable e, String message) {
        super(message, e);
    }

    public ApplicationException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApplicationException(String message, ErrorCodes errorCode, Map<String, Object> metaData) {
        super(message);
        this.errorCode = errorCode;
        this.metaData = metaData;
    }

    public Map<String, Object> getMetaData() {
        return this.metaData;
    }

    public ErrorCodes getErrorCode() {
        return this.errorCode;
    }

}
