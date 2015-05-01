/**
 * 
 */
package com.appcoretech.expensemanager.service.impl;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.appcoretech.expensemanager.dao.ExpenseDAO;
import com.appcoretech.expensemanager.models.MonthlyExpenses;
import com.appcoretech.expensemanager.models.User;
import com.appcoretech.expensemanager.service.EmailService;

/**
 * @author User
 *
 */
public class EmailServiceImpl implements EmailService {

	@Autowired
	private ExpenseDAO expenseDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.appcoretech.expensemanager.service.EmailService#sendEmail()
	 */
	@Override
	public boolean sendEmail(MonthlyExpenses monthExp) {
		List<User> user = expenseDAO.getUsers();
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "guru.nie@gmail.com";//
		final String password = "thinkpad@123";
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress("guru.nie@gmail.com"));
			Iterator<User> usersItr = user.iterator();
			while(usersItr.hasNext()) {
				User use = usersItr.next();
				message.addRecipients(Message.RecipientType.TO,
						InternetAddress.parse(use.getEmailAddress(), false));
			}
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse("girish.hundekar@gmail.com", false));
			// message.addRecipients(Message.RecipientType.TO,
			// InternetAddress.parse("pavannie@gmail.com", false));
			// Set Subject: header field
			message.setSubject("Monthly Expense for :  " + monthExp.getName());

			StringBuilder content = new StringBuilder();
			content.append("Month : " + monthExp.getName());
			content.append("\nTotal : "
					+ new DecimalFormat("####.##").format(monthExp.getTotal()));
			content.append("\nPer Head : "
					+ new DecimalFormat("####.##").format(monthExp.getPerHead()));
			content.append("\nMoney Spent By : ");
			Iterator<String> itr = monthExp.getSpentByUser().keySet()
					.iterator();
			while (itr.hasNext()) {
				String key = itr.next();
				content.append("\n\t").append(key);
				content.append(" : ").append(
						new DecimalFormat("####.##").format(monthExp
								.getSpentByUser().get(key)));
			}
			content.append("\nMoney to Get/Pay : ");
			itr = monthExp.getToGetByUser().keySet().iterator();
			while (itr.hasNext()) {
				String key = itr.next();
				content.append("\n\t").append(key);
				double amount = monthExp.getToGetByUser().get(key);
				if (amount > 0) {
					content.append(" should pay : ").append(
							new DecimalFormat("####.##").format(amount));
				} else {
					content.append(" should get : ").append(
							new DecimalFormat("####.##").format(Math
									.abs(amount)));
				}
			}
			// Now set the actual message
			message.setText(content.toString());
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", username, password);
			message.saveChanges();
			Transport.send(message);
			transport.close();

			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		return false;
	}

}
