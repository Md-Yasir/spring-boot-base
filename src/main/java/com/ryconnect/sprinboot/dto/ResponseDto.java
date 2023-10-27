package com.ryconnect.sprinboot.dto;

import com.ryconnect.sprinboot.enums.ErrorCodes;

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
