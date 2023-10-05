package com.bptn.feedapp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bptn.feedapp.jpa.User;
import com.bptn.feedapp.repository.UserRepository;

@Service("UserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> opt = this.userRepository.findByUsername(username);

		if (opt.isEmpty()) {
			throw new UsernameNotFoundException("Username not found: " + username);
		}

		return new CustomUserDetails(opt.get());
	}

}
