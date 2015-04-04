/**
 * 
 */
package com.appcoretech.expensemanager.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.appcoretech.expensemanager.dao.ExpenseDAO;
import com.appcoretech.expensemanager.dao.mappers.UserMapper;
import com.appcoretech.expensemanager.models.User;

/**
 * @author gururajk
 *
 */
public class ExpenseDAOImpl implements ExpenseDAO {

	private static final Logger LOG = Logger.getLogger(ExpenseDAOImpl.class);

	@Resource(name = "expManagerDataSource")
	public DataSource dataSource;

	private NamedParameterJdbcTemplate jdbcTemplate;

	@PostConstruct
	public void postConstruct() {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<User> getUsers() {
		List<User> users = null;
		UserMapper userMapper = new UserMapper();
		try {
			users = this.jdbcTemplate.getJdbcOperations().query(
					"SELECT * FROM users", userMapper);
			LOG.info("returning the users fetched from the database");
		} catch (Exception ex) {
			LOG.error("Failed to fetch the user details");
			return null;
		}
		return users;
	}
}
