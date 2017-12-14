package com.ness.services.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import com.ness.services.error.ServiceUnavailableException;

@Service(value="PublishService")
public class PublishService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublishService.class);
	
	@Value("${service.atomic.kafkaservice.name}")
    private String kafkaservicename;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	/**
     * Locate a service from the registry
     * 
     * @param serviceId
     * @return
     */
	public String locateServiceUrl(String serviceId, String target) throws ServiceUnavailableException{
		ServiceInstance instance = loadBalancer.choose(serviceId);
		URI uri = null;
		if (instance == null) {
			if (kafkaservicename.equals(serviceId)) throw new ServiceUnavailableException();						
		}
		uri = instance.getUri();
		LOGGER.info("locateServiceUrl(): Resolved serviceId = " + serviceId + " to URL = " + uri);
		return uri.toString() + "/" + serviceId + "/" + target;
	}
	
	/*public void publishEvent(AuditBO auditBO){
		ObjectMapper mapper = builder.build();
        ResponseEntity<String> response = null;
		try {
			RequestEntity<AuditBO> panRequest = new RequestEntity<AuditBO>(auditBO, HttpMethod.POST, new URI(locateServiceUrl(kafkaservicename, "/kafka/audit")));
			restTemplate.exchange(panRequest, String.class);
		} catch (HttpStatusCodeException hsce) {			
			throw hsce;
		}
	}*/
}
