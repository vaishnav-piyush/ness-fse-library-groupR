/**
 * 
 */
package com.ness.services;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * @author P7109857
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.ness.services")
public class AuditServiceConfiguration {

	/*@Value("${datasource.username}")
	private String user;
	
	@Value("${datasource.password}")
	private String password;
	
	@Value("${datasource.url}")
	private String host;
	
	
	
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
	}*/

	@Autowired
	private Environment env;

	@Bean(destroyMethod="close")
    public DataSource dataSource() throws SQLException
    {
    
         BasicDataSource dataSource = new BasicDataSource();
         dataSource.setDriverClassName(env.getRequiredProperty("datasource.driver"));
         dataSource.setUrl(env.getRequiredProperty("datasource.url"));
         dataSource.setUsername(env.getRequiredProperty("datasource.username"));
         dataSource.setPassword(env.getRequiredProperty("datasource.password"));
         return dataSource;
    }

	/*@Bean
	public PersistenceExceptionTranslationPostProcessor postProcessor() throws Exception{
		return new PersistenceExceptionTranslationPostProcessor();
	}*/
}
