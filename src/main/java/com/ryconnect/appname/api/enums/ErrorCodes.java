package com.ryconnect.appname.api.enums;

public enum ErrorCodes {
    SRV001("Technical Error"),
    SRV500("Internal Server Error"),
    SRV403("UnAuthorized"),
    SRV400("Bad Request"),
    SRV005(""),
    SRV006("");

    public final String errorCode;

    ErrorCodes(String errorCode){
        this.errorCode=errorCode;
    }
}
