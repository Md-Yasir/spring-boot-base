package com.yass.sprinboot.enums;

public enum ErrorCodes {
    SRV001("Technical Error"),
    SRV002("Internal Server Error"),
    SRV003("UnAuthorized"),
    SRV004(""),
    SRV005(""),
    SRV006("");

    public final String errorCode;

    ErrorCodes(String errorCode){
        this.errorCode=errorCode;
    }
}
