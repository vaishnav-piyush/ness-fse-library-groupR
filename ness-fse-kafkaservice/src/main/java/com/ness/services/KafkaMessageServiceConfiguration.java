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
public class KafkaMessageServiceConfiguration {
	
	@Autowired
	private Environment environment;

	@Bean(destroyMethod="close")
    public DataSource dataSource() throws SQLException
    {    
         BasicDataSource dataSource = new BasicDataSource();
         dataSource.setDriverClassName(environment.getRequiredProperty("datasource.driver"));
         dataSource.setUrl(environment.getRequiredProperty("datasource.url"));
         dataSource.setUsername(environment.getRequiredProperty("datasource.username"));
         dataSource.setPassword(environment.getRequiredProperty("datasource.password"));
         return dataSource;
    }
}
