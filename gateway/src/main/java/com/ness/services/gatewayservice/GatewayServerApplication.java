package com.ness.services.gatewayservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@Controller
@EnableZuulProxy
@Configuration
public class GatewayServerApplication
{

    @Autowired
    private GatewayServerConfiguration gatewayServerConfiguration;

    public static void main(String[] args)
    {
    	SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        System.out.println("ORIGINS = " + gatewayServerConfiguration.getOrigins());
//        for(String o : gatewayServerConfiguration.getOrigins())
//        {
//            System.out.println("ORIGIN = " + o);
//            config.addAllowedOrigin(o);
//        }
//        for(String m : gatewayServerConfiguration.getMethods())
//        {
//        	System.out.println("METHOD = " + m);
//        	config.addAllowedMethod(m);
//        }
//        config.addAllowedHeader("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
    	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(2000);
        return loggingFilter;
    }
}
