/**
 * 
 */
package com.appcoretech.expensemanager.service;

import java.util.List;

import com.appcoretech.expensemanager.models.Expenses;
import com.appcoretech.expensemanager.models.MonthlyExpenses;
import com.appcoretech.expensemanager.models.User;

/**
 * @author User
 *
 */
public interface DataService {

	/**
	 * returns the users avaliable in database.
	 * @return
	 */
	List<User> getUsers();

	/**
	 * saves the expenses record to the database.
	 * @param exp
	 * @return
	 */
	boolean saveExpense(Expenses exp);
	
	/**
	 * returns the latest 10 transactions from the database.
	 * @return
	 */
	List<Expenses> getLatestExpenses();

	/**
	 * servce method to provide the prev month data.
	 * @return
	 */
	MonthlyExpenses getPrevMonthData();
	

}
