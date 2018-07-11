package com.mindfire.secureapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.secureapi.api.config.JwtTokenUtil;
import com.mindfire.secureapi.api.service.UserService;
import com.mindfire.secureapi.core.entity.User;
import com.mindfire.secureapi.core.model.AuthToken;
import com.mindfire.secureapi.core.model.LoginUser;

/**
 * Implementation for JWT authentication token generation end point. Handles
 * creation of tokens after authentication of user via username and password.
 */
@RestController
@RequestMapping("/auth/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager; // Checks user authentication

    @Autowired
    private JwtTokenUtil jwtTokenUtil;			 // Generates the token

    @Autowired
    private UserService userService;			 // Gets user data

    /**
     * Maps user token generation request and extracts user name and password from
     * request.
     * 
     * @param loginUser User model for login
     * @return ResponseEntity
     * @throws AuthenticationException
     */
    @PostMapping
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

	// Create new authentication token
	UsernamePasswordAuthenticationToken authenticationToken = 
		new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
	
	// Set authentication manager with the authentication token
	Authentication authentication = authenticationManager.authenticate(authenticationToken);

	// Set authentication to security context, handles authentication
	SecurityContextHolder.getContext().setAuthentication(authentication);
	
	// Get the user requesting for authentication
	User user = userService.findByUsername((loginUser.getUsername()));
	
	// Generate the JWT token for user
	final String token = jwtTokenUtil.generateToken(user);
	
	return ResponseEntity.ok(new AuthToken(token));
    }

}
