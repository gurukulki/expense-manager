package com.appcoretech.expensemanager.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.appcoretech.expensemanager.models.User;

public class UserMapper implements RowMapper {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("ID"));
		user.setUserName(rs.getString("USER_NAME"));
		user.setFirstName(rs.getString("FIRST_NAME"));
		user.setLastName(rs.getString("LAST_NAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
		return user;
	}

}
