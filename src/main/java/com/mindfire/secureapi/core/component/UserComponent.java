package com.mindfire.secureapi.core.component;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.secureapi.core.entity.User;
import com.mindfire.secureapi.core.respository.UserRepository;

/**
 * Core implementation of the CRUD operations on the User entity via
 * UserRepository.
 */
@Component
public class UserComponent {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
	return (List<User>) userRepository.findAll();
    }

    public Optional<User> findById(long id) {
	return userRepository.findById(id);
    }

    public User findByUsername(String username) {
	return userRepository.findByUsername(username);
    }

    public User save(User user) {
	return userRepository.save(user);
    }

    public void delete(User user) {
	userRepository.delete(user);
    }

}
