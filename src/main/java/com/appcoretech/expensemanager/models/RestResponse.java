package com.appcoretech.expensemanager.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RestResponse extends BaseRestResponse
{

	public Object getItems()
	{
		return items;
	}

	public void setItems(Object items)
	{
		this.items = items;
	}

	private Object items;
}
