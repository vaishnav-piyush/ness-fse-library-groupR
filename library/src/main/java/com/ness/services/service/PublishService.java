package com.ness.services.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.ness.services.error.ServiceUnavailableException;
import com.ness.services.model.AuditBO;

@Service(value="PublishService")
public class PublishService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublishService.class);
	
	@Value("${service.atomic.kafkaservice.name}")
    private String kafkaServiceName;
	
	@Value("${service.atomic.kafkaservice.enabled}")
    private boolean enabled;
	
	@Value("${service.atomic.kafkaservice.url}")
    private String kafkaServiceUrl;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	/**
     * Locate a service from the registry
     * 
     * @param serviceId
     * @return
     */
	private String locateServiceUrl(String serviceId, String target) throws ServiceUnavailableException{
		ServiceInstance instance = loadBalancer.choose(serviceId);
		URI uri = null;
		String url = null;
		if (instance == null) {
			if (kafkaServiceName.equals(serviceId)) throw new ServiceUnavailableException();						
		}
		uri = instance.getUri();
		url = uri.toString() + "/" + target;
		LOGGER.info("locateServiceUrl(): Resolved serviceId = " + serviceId + " to URL = " + url);
		return url;
	}
	
	public void publishEvent(AuditBO auditBO) throws Exception{
		LOGGER.info("publishEvent(): Method Entry");
		ResponseEntity<String> response = null;
        RestTemplate restTemplate = new RestTemplate(); 
        HttpHeaders headers = new HttpHeaders();
		try {
			LOGGER.info("publishEvent(): kafkaservice.enabled: " + enabled);
			if(enabled){
				headers.setContentType(MediaType.APPLICATION_JSON);
				RequestEntity<AuditBO> panRequest = new RequestEntity<AuditBO>(auditBO, headers, HttpMethod.POST, new URI(locateServiceUrl(kafkaServiceName, kafkaServiceUrl)));
				response = restTemplate.exchange(panRequest, String.class);
				LOGGER.info("publishEvent(): response returned: " + response);
			}else{
				LOGGER.info("publishEvent(): kafkaservice no invoked since service.atomic.kafkaservice.enabled is set to false");
			}
		} catch (HttpStatusCodeException hsce) {
			LOGGER.info("publishEvent(): HttpStatusCodeException observed: " + hsce.getMessage());
			throw hsce;
		} catch (Exception e){
			LOGGER.info("publishEvent(): Exception observed: " + e.getMessage());
			throw e;
		}
		LOGGER.info("publishEvent(): Method Exit");
	}
}
