package com.appcoretech.expensemanager.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BaseRestResponse
{

	private ResponseCode responseCode;

	private String responseMessage;

	private Object responseMessages;

	public Object getResponseMessages()
	{
		return responseMessages;
	}

	public void setResponseMessages(Object responseMessages)
	{
		this.responseMessages = responseMessages;
	}

	public ResponseCode getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode)
	{
		this.responseCode = responseCode;
	}

	public String getResponseMessage()
	{
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}

}
