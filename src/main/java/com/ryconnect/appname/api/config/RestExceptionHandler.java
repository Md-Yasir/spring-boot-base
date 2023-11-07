package com.ryconnect.appname.api.config;

import com.ryconnect.appname.api.dto.ResponseDto;
import com.ryconnect.appname.api.enums.ErrorCodes;
import com.ryconnect.appname.api.exception.ApplicationException;
import com.ryconnect.appname.api.exception.UnAuthorizedException;
import com.ryconnect.appname.api.utils.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler(SQLException.class)
	public ResponseDto<Void> handleSQLException(HttpServletRequest request, Exception ex){
		logger.error("SQLException Occured =========> " +   ex);
		return ResponseUtil.error(ErrorCodes.SRV500, "database_error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseDto<Void> handleBadRequestException(MethodArgumentNotValidException ex){
		logger.error("Bad Exception handler executed ===========> " + ex.getMessage());
		
		List<String> errorMsgs = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		
		
		return ResponseUtil.error(ErrorCodes.SRV400, String.join(", ", errorMsgs), HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(ApplicationException.class)
	public Object handleApplicationException(HttpServletRequest request,HttpServletRequest response, ApplicationException ex){
		ex.printStackTrace();
		logger.error("ApplicationException handler executed ========> " + ex.getMessage());
		
		ResponseDto<Void> dto = ResponseUtil.error(ErrorCodes.SRV500, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		if(ex.getErrorCode() == ErrorCodes.SRV403) {
			return new ResponseEntity<>(dto, HttpStatus.UNAUTHORIZED);
		} else {
			return dto;
		}
	}

	@ResponseBody
	@ExceptionHandler({AccessDeniedException.class, UnAuthorizedException.class})
	public Object handleAccessException(AccessDeniedException ex){
		ex.printStackTrace();
		logger.error("ApplicationException handler executed ========> " + ex.getMessage());
		
		ResponseDto<Void> dto = ResponseUtil.error(ErrorCodes.SRV403, ex.getMessage(), HttpStatus.UNAUTHORIZED);
		
		return dto;
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseDto<Void> handleException(HttpServletRequest request, Exception ex){
		logger.error("Exception handler executed", ex);
		return ResponseUtil.error(ErrorCodes.SRV500, "Technical Error. Please contact admin.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
    
//    @ResponseBody
//    @ExceptionHandler
//    public ResponseDto<Void> handleUnknownException(Exception ex) {
//        LOG.info("UNKNOWN EXCEPTION: " + ex.getMessage());
//        return ResponseUtil.error(ErrorCodes.SRV500);
//    }
//    @ResponseBody
//    @ExceptionHandler
//    public static ResponseDto<Void> handleRunTimeExceptions(RuntimeException ex) {
//        LOG.info("RUNTIME EXCEPTION: " + ex.getMessage());
//        return ResponseUtil.error(ErrorCodes.SRV001);
//    }
//
//
//    @ResponseBody
//    @ExceptionHandler
//    public ResponseDto<Void> handleUnAuthorizedException(UnAuthorizedException ex) {
//        LOG.info("UNAUTHORIZED EXCEPTION: " + ex.getMessage());
//        return ResponseUtil.error(ErrorCodes.SRV403);
//    }
}
