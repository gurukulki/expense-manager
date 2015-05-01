package com.appcoretech.expensemanager.jobs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.appcoretech.expensemanager.models.MonthlyExpenses;
import com.appcoretech.expensemanager.service.DataService;
import com.appcoretech.expensemanager.service.EmailService;

public class ExpenseCalculator {
	private static final Logger LOG = Logger.getLogger(ExpenseCalculator.class);

	@Autowired
	private DataService dataService;

	@Autowired
	private EmailService emailService;

	/**
	 * calculates the monthly expenses executed by a scheduler.
	 */
	public void calculateMonthlyExpense() {
		LOG.info("In Monthly expense calculator method..");
		MonthlyExpenses monthExp = dataService.getPrevMonthData();
		emailService.sendEmail(monthExp);
	}

}