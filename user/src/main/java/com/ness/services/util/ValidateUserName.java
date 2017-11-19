package com.ness.services.util;

import com.ness.services.model.UserDTO;
import com.ness.services.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidateUserName implements ConstraintValidator<ValidUserName, String>{

    @Autowired
    private UserServiceImpl userService;

    @Override public void initialize(ValidUserName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        final UserDTO user = userService.findByUserName(username);
        if (user != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("There is already a user exist with the username " +  username)
            .addConstraintViolation();
            return false;
        }
        return true;
    }
}
