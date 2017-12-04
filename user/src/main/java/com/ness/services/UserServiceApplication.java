package com.ness.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Created by p7109792 on 11/14/17.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableDiscoveryClient
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserServiceApplication extends ResourceServerConfigurerAdapter {

	@Value("${security.oauth2.resource.id}")
	private String resourceId;

	// The DefaultTokenServices bean provided at the AuthorizationConfig
	@Autowired
	private DefaultTokenServices tokenServices;

	// The TokenStore bean provided at the AuthorizationConfig
	@Autowired
	private TokenStore tokenStore;


	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		defaultTokenServices.setTokenEnhancer(accessTokenConverter());
		return defaultTokenServices;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("abcd");
		return converter;
	}

	// To allow the rResourceServerConfigurerAdapter to understand the token,
	// it must share the same characteristics with AuthorizationServerConfigurerAdapter.
	// So, we must wire it up the beans in the ResourceServerSecurityConfigurer.
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources
		.resourceId(resourceId)
		.tokenServices(tokenServices)
		.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.anonymous().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		// when restricting access to 'Roles' you must remove the "ROLE_" part role
		// for "ROLE_USER" use only "USER"
		//			.antMatchers("/api/hello").access("hasAnyRole('book_read')")          
		//			.antMatchers("/api/admin").hasRole("book_write")
		// restricting all access to /api/** to authenticated users
		.antMatchers("/user/**").authenticated();
	}


	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
