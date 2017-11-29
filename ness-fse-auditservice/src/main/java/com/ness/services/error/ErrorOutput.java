package com.ness.services.error;

public final class ErrorOutput
{
    private String statusCode;
    private String description;
    
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
    public void setDescription(String description)
    {
        this.description = description;
    }
	@Override
	public String toString() {
		return "ErrorOutput [statusCode=" + statusCode + ", description=" + description + "]";
	}
    

}
