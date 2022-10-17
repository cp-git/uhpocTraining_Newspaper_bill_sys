package com.cp.newspaper.service;
import java.sql.SQLException;
import java.util.HashMap;
import com.cp.newspaper.entity.Customer;
import com.cp.newspaper.exception.CPException;

public interface CustomerService {

		boolean isCustExists(Customer cust) throws SQLException;
		HashMap<Long, Customer> display();
		void createCustomer(Customer cust) throws CPException;
	}




