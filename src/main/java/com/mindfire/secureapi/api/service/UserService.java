package com.mindfire.secureapi.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mindfire.secureapi.core.component.UserComponent;
import com.mindfire.secureapi.core.entity.User;

/**
 * Service implementation for business logic of User. It also implements
 * UserDetailsService for User authentication and authorization.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserComponent userComponent;

    /**
     * Finds user based on the user name and creates new Spring security User from
     * the stored user credentials and authority.
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userComponent.findByUsername(username);

	if (user == null) {
	    throw new UsernameNotFoundException("Invalid username or password.");
	}

	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
		getAuthority());
    }

    /**
     * Grants list of authorities to the newly created User. 
     * @return List<SimpleGrantedAuthority> List of authorities
     */
    private List<SimpleGrantedAuthority> getAuthority() {
	return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    /*
     * CRUD operations and business logic implemented here.
     */
    public List<User> findAll() {
	return userComponent.findAll();
    }

    public Optional<User> findById(long id) {
	return userComponent.findById(id);
    }

    public User findByUsername(String username) {
	return userComponent.findByUsername(username);
    }

    public User save(User user) {
	return userComponent.save(user);
    }

    public void delete(User user) {
	userComponent.delete(user);
    }
}
