package com.appcoretech.expensemanager.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.appcoretech.expensemanager.models.MonthlyExpenses;

public class MonthlyExpenserMapper implements RowMapper {

	public MonthlyExpenses mapRow(ResultSet rs, int rowNum) throws SQLException {
		MonthlyExpenses monthlyExp = new MonthlyExpenses();
		String name = rs.getString("USER_NAME");
		Double amount = rs.getDouble("AMOUNT");
		monthlyExp.setName(rs.getString("NAME"));
		monthlyExp.setUserName(name);
		monthlyExp.setTotal(amount);
		return monthlyExp;
	}

}
