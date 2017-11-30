package com.ness.services.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ness.services.model.User;
import com.ness.services.repository.UserRepository;
import com.ness.services.util.PasswordManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.services.model.UserDTO;


@Service(value="UserService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    public UserDTO create(UserDTO userDTO) {
        LOGGER.info("Creating a new Book entry with information: {}", userDTO);
        User user = mapUserDTOToUser(userDTO);
        final User savedUser = repository.save(user);
        System.out.println("**********"+savedUser);
        return new UserDTO(savedUser.getFirstName(), savedUser.getLastName(), savedUser.getRoleId() == 1,
                savedUser.getUserName(), savedUser.getPassword());
    }

    @Override public List<UserDTO> getAllUsers() {
        final Iterable<User> all = repository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User u: all) {
            System.out.println("**********"+u);
            userDTOS.add(new UserDTO(u.getFirstName(), u.getLastName(), u.getRoleId() == 1,
                    u.getUserName(), u.getPassword()));
        }
        return userDTOS;
    }

    public UserDTO findByUserName(String username) {
        final User savedUser = repository.findFirstByUserName(username);
        if (savedUser == null) {
            return null;
        }
        return new UserDTO(savedUser.getFirstName(), savedUser.getLastName(), savedUser.getRoleId() == 1,
                savedUser.getUserName(), savedUser.getPassword());
    }

    private User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRoleId(userDTO.getAdmin() ? 1 : 2);
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setCreatedDate(new Date(Calendar.getInstance().getTime().getTime()));
        user.setUpdatedDate(new Date(Calendar.getInstance().getTime().getTime()));
        return user;
    }

}
