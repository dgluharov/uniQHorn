package com.uniqhorn.utils;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@ControllerAdvice
public class ExceptionHandlers {
	@ExceptionHandler({InvalidFormatException.class, MismatchedInputException.class})
	public void handlerIllegalArgumentException(JsonProcessingException exception,
	                                            ServletWebRequest webRequest) throws IOException {
	    if(exception instanceof InvalidFormatException) {
	        //LOGGER.error(exception.getMessage(), exception);
	        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), "The entered date format is invalid" );
	    } else if (exception instanceof MismatchedInputException) {
	        //LOGGER.error(exception.getMessage(), exception);
	        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
	    }
	}
}
