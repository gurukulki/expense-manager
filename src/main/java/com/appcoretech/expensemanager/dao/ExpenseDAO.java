package com.appcoretech.expensemanager.dao;

import java.util.List;

import com.appcoretech.expensemanager.models.User;


public interface ExpenseDAO {

	/**
	 * DAO method to get the users details from the database.
	 * @return
	 */
	List<User> getUsers();
	
}
