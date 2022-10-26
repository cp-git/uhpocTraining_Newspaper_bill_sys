package com.cp.newspaper.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cp.newspaper.entity.Customer;
import com.cp.newspaper.jdbc.DBManager;
import com.cp.newspaper.service.CustService;
import com.cp.newspaper.serviceImpl.CustServiceImpl;

public class TestCustServiceImpl {
	private static CustService custServ = null;
	static HashMap<Long, Customer> customerHash = null;
	static DBManager dbm = null;
	static Connection con = null;
	static List<Customer> expList = null;
	List<Customer> custList = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before");
		custServ = new CustServiceImpl();
		customerHash = new HashMap<>();
		dbm = DBManager.getDBManager();
		expList = new ArrayList<>();
	}

	@Before
	public void setUp() throws Exception {
		con = dbm.getConnection();
	}

	@Test
	public void testGetCustomerDetails() {
		Customer customer = new Customer(1, "Akash", "Hadapsar", "Pune", 8847741211L);
		custList = custServ.getDetailsAll();
		Customer cust = custList.get(0);

		assertEquals(customer.getCust_name(), cust.getCust_name());

		assertEquals(customer.getCust_address1(), cust.getCust_address1());
		assertEquals(customer.getCust_address2(), cust.getCust_address2());
		assertEquals(customer.getCust_phone(), cust.getCust_phone());
	}

	@Test
	public void testDisplay() {
		Customer customer = new Customer(1, "Akash", "Hadapsar", "Pune", 8847741211L);
		HashMap<Long, Customer> customerHash = custServ.display();
		Customer cust = customerHash.get(8847741211L);

		assertEquals(customer.getCust_name(), cust.getCust_name());

		assertEquals(customer.getCust_address1(), cust.getCust_address1());
		assertEquals(customer.getCust_address2(), cust.getCust_address2());
		assertEquals(customer.getCust_phone(), cust.getCust_phone());

	}

	@After
	public void tearDown() {
		dbm.closeConnection(con);
	}
}
