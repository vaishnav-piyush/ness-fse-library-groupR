package com.ness.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ness.services.model.UserDTO;
import com.ness.services.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService service;
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO createUser(@RequestBody UserDTO userDto) {
        LOGGER.info("Creating a new user entry with information: {}", userDto);
        UserDTO created = service.create(userDto);
        LOGGER.info("Created a new user entry with information: {}", created);
        return created;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }
}
