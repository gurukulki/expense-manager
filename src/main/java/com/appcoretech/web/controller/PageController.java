package com.appcoretech.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author guru This controller will resolve a view from the url path matching a suffix
 */
public class PageController
{

	private Logger log = Logger.getLogger(PageController.class);

	private Map<String, String> viewMap;

	@RequestMapping(method =
	{ RequestMethod.GET })
	public String getPage(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{

		String key = request.getRequestURI()
				.substring(request.getRequestURI().lastIndexOf("/") + 1)
				.trim();
		if (key == null || key.length() == 0 || !viewMap.containsKey(key))
		{
			log.info(request.getRequestURI() + " No mapping found ");
			return "redirect:/";
		}
		String view = null;
		if (viewMap.containsKey(key))
			view = viewMap.get(key);
		else
			view = key;
		model.addAttribute("viewMapping", view);
		log.info(request.getRequestURI() + " mapped to view " + view);
		return view;
	}

	public Map<String, String> getViewMap()
	{
		return viewMap;
	}

	public void setViewMap(Map<String, String> viewMap)
	{
		this.viewMap = viewMap;
	}
}
