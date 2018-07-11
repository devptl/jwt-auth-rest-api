package com.mindfire.secureapi.core.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.secureapi.core.entity.User;

/**
 * Core interface that implements CRUD operations on User entity.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // Finds user by user name
    User findByUsername(String username);

}
