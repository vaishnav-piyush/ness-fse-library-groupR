package com.ness.services.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ness.services.error.GenericExceptionCodeForInvalidAttributes;

@Service
public class LibraryOldService implements GenericExceptionCodeForInvalidAttributes{

    private static final Logger LOG = LoggerFactory.getLogger(LibraryOldService.class);

    private static final String PROFILE_SERVICE_ID = "USERPROFILESERVICE";
    private static final String VERSION_1 = "v1";
    private static final String ENABLED="ENABLED";
    private static final String AUTHENTICATED ="AUTHENTICATED";
    
    private RestTemplate restTemplate = new RestTemplate();
    
    /*public void initiateProfileWorkflow(InitiateProfileInput input) throws ServiceUnavailableException, Exception
    {
    	GenericResponse<ErrorOutput> response = null;
    	ResponseEntity<String> result = null; 
    	ObjectMapper mapper = new ObjectMapper();
    	RequestEntity<InitiateProfileInput> request = new RequestEntity<InitiateProfileInput>(input,  HttpMethod.POST, new URI(locateServiceUrl(PROFILE_SERVICE_ID, "profile/initiateProfileWorkflow")));
    	LOG.info("#### DashControllerService.initiateProfileWorkflow() one attrib: Before Rest Invocation: ");
    	restTemplate.exchange(request,String.class);
    	LOG.info("#### DashControllerService.initiateProfileWorkflow() one attrib: Post Rest Invocation: ");
    	return;
    }

    *//**
     * Locate a service from the registry
     * 
     * @param serviceId
     * @return
     *//*
    private String locateServiceUrl(String serviceId, String target) throws ServiceUnavailableException,AccountServiceFailureException,CustomerServiceFailureException,TransactionServiceFailureException,UserProfileServiceFailureException
    {
        ServiceInstance instance = loadBalancer.choose(serviceId);
        URI uri=null;
        
        	if(instance==null){
        		if(PROFILE_SERVICE_ID.equals(serviceId)) throw new UserProfileServiceFailureException();
        		if(ACCOUNT_SERVICE_ID.equals(serviceId)) throw new AccountServiceFailureException();
        		if(CUSTOMER_SERVICE_ID.equals(serviceId)) throw new CustomerServiceFailureException();
        		if(TRANSACTION_SERVICE_ID.equals(serviceId)) throw new TransactionServiceFailureException();
            	//throw new ServiceUnavailableException();
            }
           uri = instance.getUri();
            LOG.debug("Resolved serviceId=" + serviceId + " to URL=" + uri);
        
        
        return uri.toString() + "/" + target;
       // return "http://t9dssa01:41499/account/v1/5259950130311072";
    }*/
    
    private HttpHeaders setHeader(String userName)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X_CTFS_JWT_Username", userName);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
        
    }
    
    private String formatDate(Date dt) {
    	String formatDate="";
    	if(dt == null) {
    		return formatDate;
    	}
    	
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
    		formatDate = sdf.format(dt);
    	} catch (Exception ex) {
    		
    	}
    	return formatDate;
    }
    
}
