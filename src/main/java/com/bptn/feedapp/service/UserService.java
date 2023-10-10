package com.bptn.feedapp.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bptn.feedapp.exception.domain.EmailExistException;
import com.bptn.feedapp.exception.domain.UsernameExistException;
import com.bptn.feedapp.jpa.User;
import com.bptn.feedapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailService emailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	public List<User> listUsers() {

		// this calls findAll() method from CrudRepository(superInterface for
		// JpaRepository)
		// this is internally firing "Select * from "\"User\"";
		return this.userRepository.findAll();
	}

	public Optional<User> findByUsername(String username) {

		// this calls findByUsername() method from UserRepository interface
		// this is internally firing "Select * from "\"User\"" where "username"=?;
		return this.userRepository.findByUsername(username);

	}

	public void createUser(User user) {

		// this calls createUser() method from CrudRepository(superInterface for
		// JpaRepository)
		// Insert operation for a new record
		// or an update operation for an already inserted record
		this.userRepository.save(user);
	}

	public User signup(User user) {

		user.setUsername(user.getUsername().toLowerCase());
		user.setEmailId(user.getEmailId().toLowerCase());

		//check if username or emailId is existing and handle it with exceptions
		this.validateUsernameAndEmail(user.getUsername(), user.getEmailId());

		//encrypt the password to save it to the DB
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setEmailVerified(false);
		user.setCreatedOn(Timestamp.from(Instant.now()));

		//creating the user in the DB using INSERT operation
	    this.userRepository.save(user);

		this.emailService.sendVerificationEmail(user);
		
		return user;
	}

	private void validateUsernameAndEmail(String username, String emailId) {

		this.userRepository.findByUsername(username).ifPresent(u -> {
			throw new UsernameExistException(String.format("Username already exists, %s", u.getUsername()));
		});

		this.userRepository.findByEmailId(emailId).ifPresent(u -> {
			throw new EmailExistException(String.format("Email already exists, %s", u.getEmailId()));
		});

	}

}
