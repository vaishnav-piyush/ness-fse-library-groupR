package com.ness.services.error;

public class KafkaServiceException extends Exception
{

    private static final long serialVersionUID = 1L;
    
    private String statusCode;
    private String description;
    private int httpStatus;  
    
    public KafkaServiceException(){}
    
    public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public KafkaServiceException(String code, String desc)
    {
        this.statusCode = code;
        this.description = desc;
    }
    
    public String getStatusCode()
    {
        return statusCode;
    }
    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String descrption)
    {
        this.description = descrption;
    }
}
