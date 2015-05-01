package com.appcoretech.expensemanager.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.appcoretech.expensemanager.models.Expenses;
import com.appcoretech.expensemanager.models.User;

public class ExpenserMapper implements RowMapper {

	public Expenses mapRow(ResultSet rs, int rowNum) throws SQLException {
		Expenses exp = new Expenses();
		exp.setName(rs.getString("USER_NAME"));
		exp.setShop(rs.getString("SHOP"));
		exp.setAmount(rs.getDouble("AMOUNT"));
		exp.setShopDate(rs.getTimestamp("SHOPDATE"));
		return exp;
	}

}
