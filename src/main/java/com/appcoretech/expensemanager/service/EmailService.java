/**
 * 
 */
package com.appcoretech.expensemanager.service;

import com.appcoretech.expensemanager.models.MonthlyExpenses;


/**
 * @author User
 *
 */
public interface EmailService {

	/**
	 * Send email to all the relevant users.
	 * @return
	 */
	boolean sendEmail(MonthlyExpenses monthExp);

}
