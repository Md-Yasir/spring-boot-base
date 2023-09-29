package com.yass.sprinboot.utils;

import com.yass.sprinboot.dto.ResponseDto;
import com.yass.sprinboot.enums.ErrorCodes;

public class ResponseUtil {
    public static <T> ResponseDto<T> success(T data) {
        return ResponseDto.<T>builder()
                .statusCode(200)
                .statusMessage("Request Successful")
                .status(AppConstants.SUCCESS)
                .data(data)
                .build();
    }

    public static <T> ResponseDto<T> error(ErrorCodes errorCodes) {
        return ResponseDto.<T>builder()
                .statusCode(400)
                .statusMessage("Request Successful")
                .status(AppConstants.ERROR)
                .errorCode(errorCodes)
                .build();
    }
}
