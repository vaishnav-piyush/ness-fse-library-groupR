package com.ness.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ness.services.error.ErrorOutput;
import com.ness.services.error.KafkaServiceException;

@ControllerAdvice(assignableTypes=KafkaMessageController.class)
public class KafkaMessageControllerAdvice
{

	@ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    ErrorOutput handleException(Exception nve){
		nve.printStackTrace();
		ErrorOutput output = new ErrorOutput();
		output.setStatusCode("50100");
		output.setDescription("Kafka Message Service General Failure");
		return output;
    }    
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    ErrorOutput handleException(KafkaServiceException nve){
		nve.printStackTrace();
		ErrorOutput output = new ErrorOutput();
		output.setStatusCode("50200");
		output.setDescription("Kafka Message Service Exception");
		return output;
    }
}
