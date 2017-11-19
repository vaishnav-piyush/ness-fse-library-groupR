package com.ness.services.repository;

import com.ness.services.model.User;
import com.ness.services.model.UserDTO;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by p7109792 on 11/14/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findFirstByUserName(String username);
}
