package com.cp.newspaper.entity;

import java.util.Date;

public class Bill {
	private int bill_id;
	private Date bill_date;
	private String bill_month;
	private int cust_id;

	public int getBill_id() {
		return bill_id;
	}

	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}

	public Date getBill_date() {
		return bill_date;
	}

	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}

	public String getBill_month() {
		return bill_month;
	}

	public void setBill_month(String bill_month) {
		this.bill_month = bill_month;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	@Override
	public String toString() {
		return "Bill [bill_id=" + bill_id + ", bill_date=" + bill_date + ", bill_month=" + bill_month + ", cust_id="
				+ cust_id + "]";
	}

	public Bill(int bill_id, Date bill_date, String bill_month, int cust_id) {
		super();
		this.bill_id = bill_id;
		this.bill_date = bill_date;
		this.bill_month = bill_month;
		this.cust_id = cust_id;
	}

}
