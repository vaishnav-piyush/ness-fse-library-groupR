package com.ness.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ness.services.error.ErrorOutput;
import com.ness.services.model.UserDTO;
import com.ness.services.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService service;
    
    @PreAuthorize("hasRole('user_write')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody @Valid UserDTO userDto) {
        LOGGER.info("Creating a new user entry with information: {}", userDto);
        UserDTO created = service.create(userDto);
        LOGGER.info("Created a new user entry with information: {}", created);
        return created;
    }

    @PreAuthorize("hasRole('user_read')")
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
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
