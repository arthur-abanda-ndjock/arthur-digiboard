package com.example.test.healthcheck.model;

public class CrmDashboardCard {

	private double clients;
	private double admin;
	private double expenses;

	public CrmDashboardCard(double clients, double admin, double expenses) {

		super();
		this.clients = clients;
		this.admin = admin;
		this.expenses = expenses;
	}

	public double getClients() {
		return clients;
	}

	public void setClients(double clients) {
		this.clients = clients;
	}

	public double getAdmin() {
		return admin;
	}

	public void setAdmin(double admin) {
		this.admin = admin;
	}

	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

}
