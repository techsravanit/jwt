package com.purpletalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purpletalk.entities.AuthUser;
import com.purpletalk.repositary.AuthUserRepositary;

@RestController
//@RequestMapping("/users")
public class UserController {

	@Autowired
	private AuthUserRepositary authUserRepo;
	private BCryptPasswordEncoder passwordEncoder;

	public UserController(AuthUserRepositary authUserRepo, BCryptPasswordEncoder passwordEncoder) {
		this.authUserRepo = authUserRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@RequestMapping("/")
	public AuthUser getData(){
		return authUserRepo.findByUsername("testsuperadmin@xcubelabs.com");
	}

	@PostMapping("/signUp")
	public void signUp(@RequestBody AuthUser user) {
		System.out.println("user details:"+user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		authUserRepo.save(user);
	}
}
