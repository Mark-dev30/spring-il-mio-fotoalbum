package org.java.demo.auth.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.demo.auth.pojo.User;
import org.java.demo.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserServ implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	public List<User> findAll() {
		
		return userRepo.findAll();
	}
	public Optional<User> findById(int id) {
		
		return userRepo.findById(id);
	}
	public User save(User user) {
		
		return userRepo.save(user);
	}
	
	@Transactional
	public Optional<User> findByUsernameWithImage(String username){
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		Hibernate.initialize(userOpt.get().getImage());
		
		return userOpt;
	}

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if (userOpt.isEmpty()) throw new UsernameNotFoundException("Username not found");
		
		return userOpt.get();
	}
}
