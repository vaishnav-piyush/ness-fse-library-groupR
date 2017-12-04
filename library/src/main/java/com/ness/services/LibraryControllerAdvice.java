package com.ness.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ness.services.error.BookNotFoundException;
import com.ness.services.error.ErrorOutput;
import com.ness.services.error.GenericExceptionCodeForInvalidAttributes;
import com.ness.services.error.LibraryServiceException;

@ControllerAdvice(assignableTypes=LibraryController.class)
public class LibraryControllerAdvice implements GenericExceptionCodeForInvalidAttributes
{

	@ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    ErrorOutput handleException(Exception nve){
		nve.printStackTrace();
		ErrorOutput output = new ErrorOutput();
		output.setStatusCode("50100");
		output.setDescription("Library Service General Failure");
		return output;
    }    
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    ErrorOutput handleException(LibraryServiceException nve){
		nve.printStackTrace();
		ErrorOutput output = new ErrorOutput();
		output.setStatusCode("50200");
		output.setDescription("Library Service Exception");
		return output;
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorOutput handleException(BookNotFoundException nve){
		nve.printStackTrace();
		ErrorOutput output = new ErrorOutput();
		output.setStatusCode(nve.getStatusCode());
		output.setDescription(nve.getDescription());
		return output;
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    ErrorOutput handleException(AccessDeniedException e){
		e.printStackTrace();
		ErrorOutput output = new ErrorOutput();
		output.setStatusCode("401");
		output.setDescription(e.getLocalizedMessage());
		return output;
    }
}
