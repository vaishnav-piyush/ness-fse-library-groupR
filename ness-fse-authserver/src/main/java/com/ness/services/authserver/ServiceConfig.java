package com.ness.services.authserver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import com.ness.services.authserver.jpa.User;
import com.ness.services.authserver.jpa.repository.UserRepository;

@Configuration
public class ServiceConfig extends GlobalAuthenticationConfigurerAdapter {	
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		
		List<User> users = userRepository.findAll();
//		User user1 = new User("piyush", "passwordPiyush", "USER");
//		User user2 = new User("swathi", "passwordSwathi", "ADMIN");
//		users.add(user1);
//		users.add(user2);
		
		List<String> adminRoles = new ArrayList<String>();
		adminRoles.add("book_read");
		adminRoles.add("book_write");
		adminRoles.add("book_reserve");
		adminRoles.add("book_release");
		adminRoles.add("user_read");
		adminRoles.add("user_write");
		adminRoles.add("audit_trail");	
				
		List<String> userRoles = new ArrayList<String>();
		userRoles.add("book_read");
		userRoles.add("book_reserve");
		userRoles.add("book_release");
		userRoles.add("user_read");
		userRoles.add("audit_trail");		
		
		for (User user : users) {			
			auth.inMemoryAuthentication()
			.withUser(user.getUserName()).password(user.getPassword()).authorities(user.getRoleId() == 1 ? "ADMIN" : "USER").roles(
					user.getRoleId() == 1 ? adminRoles.toArray(new String[adminRoles.size()]) :
						userRoles.toArray(new String[userRoles.size()])
					);
		}
//		auth.inMemoryAuthentication()
//		.withUser("agoldberg").password("pass1").roles("USER");
//		auth.inMemoryAuthentication()
//		.withUser("bgoldberg").password("pass2").roles("USER", "OPERATOR");
	}
}

