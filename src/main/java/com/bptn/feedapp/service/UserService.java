package com.bptn.feedapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.feedapp.jpa.User;
import com.bptn.feedapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> listUsers() {

		//this calls findAll() method from CrudRepository(superInterface for
		// JpaRepository)
		//this is internally firing "Select * from "\"User\"";
		return this.userRepository.findAll();
	}

	public Optional<User> findByUsername(String username) {

		// this calls findByUsername() method from UserRepository interface
		//this is internally firing "Select * from "\"User\"" where "username"=?;
		return this.userRepository.findByUsername(username);

	}

	public void createUser(User user) {

		// this calls createUser() method from CrudRepository(superInterface for
		// JpaRepository)
		// Insert operation for a new record
		// or an update operation for an already inserted record
		this.userRepository.save(user);
	}

}
