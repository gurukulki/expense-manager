package com.appcoretech.expensemanager.dao;

import java.util.List;

import com.appcoretech.expensemanager.models.Expenses;
import com.appcoretech.expensemanager.models.MonthlyExpenses;
import com.appcoretech.expensemanager.models.User;


public interface ExpenseDAO {

	/**
	 * DAO method to get the users details from the database.
	 * @return
	 */
	List<User> getUsers();

	/**
	 * DAO method to save the expense record to the database.
	 * @param exp
	 * @return
	 */
	boolean saveExpense(Expenses exp);

	/**
	 * returns the latest 10 transactions from the database.
	 * @return
	 */
	List<Expenses> latestExpenses();
	
	/**
	 * retrieves the last month expenses.
	 * @return
	 */
	List<MonthlyExpenses> lastMonthExpenses();

}
