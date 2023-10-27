package com.ryconnect.sprinboot.utils;

import com.ryconnect.sprinboot.dto.ResponseDto;
import com.ryconnect.sprinboot.enums.ErrorCodes;

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
