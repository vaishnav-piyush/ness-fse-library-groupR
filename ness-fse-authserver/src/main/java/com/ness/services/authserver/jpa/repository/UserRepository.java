package com.ness.services.authserver.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ness.services.authserver.jpa.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
