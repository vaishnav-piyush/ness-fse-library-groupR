package com.ness.services.authserver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class ServiceConfig extends GlobalAuthenticationConfigurerAdapter {	
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		
		List<User> users = new ArrayList<User>();
		User user1 = new User("piyush", "passwordPiyush", "USER");
		User user2 = new User("swathi", "passwordSwathi", "ADMIN");
		users.add(user1);
		users.add(user2);
		
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
			.withUser(user.getName()).password(user.getPassword()).authorities(user.getRole()).roles(
					user.getRole().equalsIgnoreCase("ADMIN") ? adminRoles.toArray(new String[adminRoles.size()]) :
						userRoles.toArray(new String[userRoles.size()])
					);
		}
//		auth.inMemoryAuthentication()
//		.withUser("agoldberg").password("pass1").roles("USER");
//		auth.inMemoryAuthentication()
//		.withUser("bgoldberg").password("pass2").roles("USER", "OPERATOR");
	}

	public class User {
		private String name;
		private String password;
		private String role;
		
		public User(String name, String password, String role) {
			this.name = name;
			this.password = password;
			this.role = role;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getPassword() {
			return this.password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}	
		
		public String getRole() {
			return this.role;
		}
		
		public void setRole(String role) {
			this.role = role;
		}
	}
}

