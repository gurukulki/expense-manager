/**
 * 
 */
package com.appcoretech.expensemanager.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.appcoretech.expensemanager.dao.ExpenseDAO;
import com.appcoretech.expensemanager.dao.mappers.ExpenserMapper;
import com.appcoretech.expensemanager.dao.mappers.MonthlyExpenserMapper;
import com.appcoretech.expensemanager.dao.mappers.UserMapper;
import com.appcoretech.expensemanager.models.Expenses;
import com.appcoretech.expensemanager.models.MonthlyExpenses;
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

	@Override
	public boolean saveExpense(Expenses exp) {
		LOG.info("in DAO method to save the expense record");
		String sql = "INSERT INTO expenses (USER_ID, SHOP, AMOUNT, SHOPDATE) VALUES (?, ?, ?, ?)";

		Object[] params = new Object[] { exp.getUserId(), exp.getShop(),
				exp.getAmount(), exp.getShopDate() };
		PreparedStatementCreatorFactory psc = new PreparedStatementCreatorFactory(
				sql);
		psc.addParameter(new SqlParameter("USER_ID", Types.INTEGER));
		psc.addParameter(new SqlParameter("SHOP", Types.VARCHAR));
		psc.addParameter(new SqlParameter("AMOUNT", Types.DECIMAL));
		psc.addParameter(new SqlParameter("SHOPDATE", Types.TIMESTAMP));
		psc.setReturnGeneratedKeys(true);

		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		int rows = jdbcTemplate.getJdbcOperations().update(
				psc.newPreparedStatementCreator(params), generatedKeyHolder);

		if (rows > 0) {
			LOG.info("Saved expense successfully");
			return true;
		} else {
			LOG.info("Save expense failed");
			return false;
		}
	}

	@Override
	public List<Expenses> latestExpenses() {
		List<Expenses> exps = null;
		ExpenserMapper expMapper = new ExpenserMapper();
		try {
			exps = this.jdbcTemplate
					.getJdbcOperations()
					.query("select U.USER_NAME, E.SHOP, E.AMOUNT, E.SHOPDATE From Expenses AS E INNER JOIN Users AS U ON E.USER_ID = U.ID ORDER BY E.SHOPDATE DESC limit 10",
							expMapper);
			LOG.info("returning the latest transactions from the database");
		} catch (Exception ex) {
			LOG.error("Failed to fetch the transactions details");
			return null;
		}
		return exps;
	}

	@Override
	public List<MonthlyExpenses> lastMonthExpenses() {
		List<MonthlyExpenses> result = null;
		MonthlyExpenserMapper expMapper = new MonthlyExpenserMapper();
		try {
			result = this.jdbcTemplate
					.getJdbcOperations()
					.query(
							"select U.USER_NAME, E.AMOUNT, MONTHNAME(E.SHOPDATE) AS NAME From Expenses AS E INNER JOIN Users AS U ON E.USER_ID = U.ID Where YEAR(E.shopdate) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) and MONTH(E.shopdate) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH)",
							expMapper);
			LOG.info("returning the latest transactions from the database");
		} catch (Exception ex) {
			LOG.error("Failed to fetch the transactions details");
			return null;
		}
		return result;
	}

}
