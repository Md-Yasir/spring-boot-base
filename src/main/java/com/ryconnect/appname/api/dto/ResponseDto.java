package com.ryconnect.appname.api.dto;

import com.ryconnect.appname.api.enums.ErrorCodes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<T> {
    private int statusCode;
    private String statusMessage;
    private String status;
    private T data;
    private ErrorCodes errorCode;
}
