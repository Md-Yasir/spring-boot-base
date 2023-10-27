package com.ryconnect.sprinboot.config;

import com.ryconnect.sprinboot.dto.ResponseDto;
import com.ryconnect.sprinboot.enums.ErrorCodes;
import com.ryconnect.sprinboot.exception.UnAuthorizedException;
import com.ryconnect.sprinboot.utils.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    private final static Logger LOG = LoggerFactory.getLogger("springboot-service");

    @ResponseBody
    @ExceptionHandler
    public ResponseDto<Void> handleUnknownException(Exception ex) {
        LOG.info("UNKNOWN EXCEPTION: " + ex.getMessage());
        return ResponseUtil.error(ErrorCodes.SRV001);
    }
    @ResponseBody
    @ExceptionHandler
    public static ResponseDto<Void> handleRunTimeExceptions(RuntimeException ex) {
        LOG.info("RUNTIME EXCEPTION: " + ex.getMessage());
        return ResponseUtil.error(ErrorCodes.SRV001);
    }


    @ResponseBody
    @ExceptionHandler
    public ResponseDto<Void> handleUnAuthorizedException(UnAuthorizedException ex) {
        LOG.info("UNAUTHORIZED EXCEPTION: " + ex.getMessage());
        return ResponseUtil.error(ErrorCodes.SRV002);
    }
}
