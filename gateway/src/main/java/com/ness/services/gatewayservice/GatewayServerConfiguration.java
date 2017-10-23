package com.ness.services.gatewayservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayServerConfiguration
{
	@Value("${gatewayserver.cors.origins:empty}")
    private String[] origins;
    
    @Value("${gatewayserver.cors.methods:empty}")
    private String[] methods;
    
    @Value("${gatewayserver.cors.allow-credentials:true}")
    private boolean allowCredentials;
    
//    @Value("${jwt.secret}")
//    private String jwtkey;

    @Value("${gatewayserver.cors.allow-header:*}")
    private String header;
    
    
//	public String getJwtkey() {
//		return jwtkey;
//	}
//
//	public void setJwtkey(String jwtkey) {
//		this.jwtkey = jwtkey;
//	}


    public String[] getOrigins()
    {
        return origins;
    }

    public void setOrigins(String[] origins)
    {
        this.origins = origins;
    }

	public String[] getMethods() {
		return methods;
	}

	public void setMethods(String[] methods) {
		this.methods = methods;
	}

	public boolean isAllowCredentials() {
		return allowCredentials;
	}

	public void setAllowCredentials(boolean allowCredentials) {
		this.allowCredentials = allowCredentials;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}
