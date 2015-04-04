/**
 * 
 */
package com.appcoretech.expensemanager.service;

import java.util.List;

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

	
}
