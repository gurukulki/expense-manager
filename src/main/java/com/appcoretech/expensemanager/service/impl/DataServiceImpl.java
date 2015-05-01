/**
 * 
 */
package com.appcoretech.expensemanager.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.appcoretech.expensemanager.dao.ExpenseDAO;
import com.appcoretech.expensemanager.models.Expenses;
import com.appcoretech.expensemanager.models.MonthlyExpenses;
import com.appcoretech.expensemanager.models.User;
import com.appcoretech.expensemanager.service.DataService;

/**
 * @author User
 *
 */
public class DataServiceImpl implements DataService {

	private static final Logger LOG = Logger.getLogger(DataServiceImpl.class);

	@Autowired
	private ExpenseDAO expenseDAO;

	@Override
	public List<User> getUsers() {
		LOG.info("Get the user details from the database");
		return expenseDAO.getUsers();
	}

	@Override
	public boolean saveExpense(Expenses exp) {
		LOG.info("Saving the expense record to the database.");
		return expenseDAO.saveExpense(exp);
	}

	@Override
	public List<Expenses> getLatestExpenses() {
		LOG.info("returning the latest transactions.");
		return expenseDAO.latestExpenses();
	}

	@Override
	public MonthlyExpenses getPrevMonthData() {
		LOG.info("Service method to return the prev month data");
		MonthlyExpenses expense = new MonthlyExpenses();
		List<MonthlyExpenses> exps = expenseDAO.lastMonthExpenses();
		Iterator<MonthlyExpenses> itr = exps.iterator();
		//calculate total and paid by each user
		while (itr.hasNext()) {
			MonthlyExpenses exp = itr.next();
			expense.setName(exp.getName());
			expense.setTotal(expense.getTotal() + exp.getTotal());
			if (expense.getSpentByUser().containsKey(exp.getUserName())) {
				Double spent = expense.getSpentByUser().get(exp.getUserName());
				expense.getSpentByUser().put(exp.getUserName(),
						spent + exp.getTotal());
			} else {
				expense.getSpentByUser().put(exp.getUserName(), exp.getTotal());
			}
		}
		expense.setPerHead(expense.getTotal() / expense.getSpentByUser().size());
		Iterator<String> itrName = expense.getSpentByUser().keySet().iterator();
		while (itrName.hasNext()) {
			String name = itrName.next();
			expense.getToGetByUser().put(name,
					expense.getPerHead() - expense.getSpentByUser().get(name));
		}
		return expense;
	}

}
