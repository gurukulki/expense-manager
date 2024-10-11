package com.appcoretech.expensemanager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcoretech.expensemanager.models.Expenses;
import com.appcoretech.expensemanager.models.ResponseCode;
import com.appcoretech.expensemanager.models.RestResponse;
import com.appcoretech.expensemanager.models.User;
import com.appcoretech.expensemanager.service.DataService;

public class ExpenseController {

	private static final Logger LOG = Logger.getLogger(ExpenseController.class);

	@Autowired
	private DataService dataService;

	/**
	 * returns the home page all the startup data.
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/expensemanager", method = RequestMethod.GET)
	public String getHomePage(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		List<Expenses> exps;
		List<User> usersList = new ArrayList<User>();
		try {
			usersList = dataService.getUsers();
			exps = dataService.getLatestExpenses();
			model.put("usersList", usersList);
			model.put("expenses", exps);
			LOG.info("returning the home page with the users details.");
		} catch (Exception exp) {
			LOG.error("Failed to get the home page with the users details", exp);
		}
		return "expensemanager";
	}

	/**
	 * saves the given expenses and refreshes the latest expenses data as well.
	 * @param exp
	 * @return
	 */
	@RequestMapping(value = "/saveexpense", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody RestResponse saveExpense(@RequestBody Expenses exp) {
		LOG.info("In save expense controller method");
		List<Expenses> expenses = null;
		try {
			boolean res = dataService.saveExpense(exp);
			if (res) {
				expenses = dataService.getLatestExpenses();
			}
			LOG.info("returning the home page with the users details.");
		} catch (Exception e) {
			LOG.error("Failed to get the home page with the users details", e);
		}
		RestResponse restResponse = new RestResponse();
		restResponse.setResponseCode(ResponseCode.SUCCESS);
		restResponse.setItems(expenses);
		LOG.info("Returning Selected VMS device information from the database");
		return restResponse;
	}

		/**
	 * saves the given expenses and refreshes the latest expenses data as well.
	 * @param exp
	 * @return
	 */
	@RequestMapping(value = "/getexpense", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody RestResponse getExpense(@RequestBody Expenses exp) {
		LOG.info("In save expense controller method");
		List<Expenses> expenses = null;
		try {
			boolean res = dataService.saveExpense(exp);
			if (res) {
				expenses = dataService.getLatestExpenses();
			}
			LOG.info("returning the home page with the users details.");
		} catch (Exception e) {
			LOG.error("Failed to get the home page with the users details", e);
		}
		RestResponse restResponse = new RestResponse();
		restResponse.setResponseCode(ResponseCode.SUCCESS);
		restResponse.setItems(expenses);
		LOG.info("Returning Selected VMS device information from the database");
		return restResponse;
	}
}
