/**
 * 
 */
package com.ness.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

/**
 * @author P7109857
 *
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.ness.services")
public class LibraryServiceConfiguration {

	@Value("${mongo.db.user}")
	private String user;
	
	@Value("${mongo.db.password}")
	private String password;
	
	@Value("${mongo.db.host}")
	private String host;
	
	@Value("${mongo.db.port}")
	private String port;
	
	@Value("${mongo.db.database}")
	private String database;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	
	@Bean
    public MongoClient mongo() throws Exception {
        return new MongoClient(getHost());
    }
	
	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(mongo(), getDatabase());
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor postProcessor() throws Exception{
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
