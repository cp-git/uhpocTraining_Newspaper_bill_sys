package com.cp.newspaper.entity;

public class Customer {
 private int cust_id;
 private String cust_name;
 private String cust_address1;
 private String cust_address2;
 private long cust_phone;
public int getCust_id() {
	return cust_id;
}
public Customer(int cust_id, String cust_name, String cust_address1, String cust_address2, long custPhone) {
	super();
	this.cust_id = cust_id;
	this.cust_name = cust_name;
	this.cust_address1 = cust_address1;
	this.cust_address2 = cust_address2;
	this.cust_phone = custPhone;
}
public Customer() {
	super();
	// TODO Auto-generated constructor stub
}

public Customer(String cust_name, String cust_address1, String cust_address2, long custPhone) {
	// TODO Auto-generated constructor stub
	super();
	this.cust_name = cust_name;
	this.cust_address1 = cust_address1;
	this.cust_address2 = cust_address2;
	this.cust_phone = custPhone;
	
}
@Override
public String toString() {
	return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_address1=" + cust_address1
			+ ", cust_address2=" + cust_address2 + ", cust_phone=" + cust_phone + "]";
}
public void setCust_id(int cust_id) {
	this.cust_id = cust_id;
}
public String getCust_name() {
	return cust_name;
}
public void setCust_name(String cust_name) {
	this.cust_name = cust_name;
}
public String getCust_address1() {
	return cust_address1;
}
public void setCust_address1(String cust_address1) {
	this.cust_address1 = cust_address1;
}
public String getCust_address2() {
	return cust_address2;
}
public void setCust_address2(String cust_address2) {
	this.cust_address2 = cust_address2;
}

public long getCust_phone() {
	return cust_phone;
}
public void setCust_phone(long cust_phone) {
	this.cust_phone = cust_phone;
}

}
