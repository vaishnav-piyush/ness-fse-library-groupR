package com.ness.services.service;

import java.util.List;

import com.ness.services.model.UserDTO;

public interface UserService {

    UserDTO create(UserDTO userDto);

    List<UserDTO> getAllUsers();

}
