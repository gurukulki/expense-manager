package com.appcoretech.expensemanager.models;

import java.sql.Timestamp;
import java.util.Date;

public class Expenses {

	private Long id;

	private int userId;

	private String name;
	
	private String shop;

	private double amount;

	private Timestamp shopDate = new Timestamp(new Date().getTime());

	public Expenses() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getShopDate() {
		return shopDate;
	}

	public void setShopDate(Timestamp shopDate) {
		this.shopDate = shopDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}