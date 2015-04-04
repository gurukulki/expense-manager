/**
 * 
 */
package com.appcoretech.expensemanager.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.appcoretech.expensemanager.dao.ExpenseDAO;
import com.appcoretech.expensemanager.models.User;
import com.appcoretech.expensemanager.service.DataService;

/**
 * @author User
 *
 */
public class DataServiceImpl implements DataService {

	private static final Logger LOG = Logger
			.getLogger(DataServiceImpl.class);

	@Autowired
	private ExpenseDAO expenseDAO;
	
	@Override
	public List<User> getUsers() {
		LOG.info("Get the user details from the database");
		return expenseDAO.getUsers();
	}

}
