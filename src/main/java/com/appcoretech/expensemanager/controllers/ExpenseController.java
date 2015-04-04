package com.appcoretech.expensemanager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appcoretech.expensemanager.models.User;
import com.appcoretech.expensemanager.service.DataService;

public class ExpenseController {

	private static final Logger LOG = Logger.getLogger(ExpenseController.class);

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "/expensemanager", method = RequestMethod.GET)
	public String getHomePage(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		List<User> usersList = new ArrayList<User>();
		try {
			usersList = dataService.getUsers();
			model.put("usersList", usersList);
			LOG.info("returning the home page with the users details.");
		} catch (Exception exp) {
			LOG.error("Failed to get the home page with the users details", exp);
		}
		return "expensemanager";
	}
}
