package com.appcoretech.expensemanager.models;

import java.util.HashMap;
import java.util.Map;

public class MonthlyExpenses {

	private Long id;

	private String name;

	private String userName;
	
	private double total;

	private double perHead;
	
	private Map<String, Double> spentByUser = new HashMap<String, Double>();
	
	private Map<String, Double> toGetByUser = new HashMap<String, Double>();
	
	public MonthlyExpenses() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getPerHead() {
		return perHead;
	}

	public void setPerHead(double perHead) {
		this.perHead = perHead;
	}

	public Map<String, Double> getSpentByUser() {
		return spentByUser;
	}

	public void setSpentByUser(Map<String, Double> spentByUser) {
		this.spentByUser = spentByUser;
	}

	public Map<String, Double> getToGetByUser() {
		return toGetByUser;
	}

	public void setToGetByUser(Map<String, Double> toGetByUser) {
		this.toGetByUser = toGetByUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}