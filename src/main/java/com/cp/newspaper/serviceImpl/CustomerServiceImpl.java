package com.cp.newspaper.serviceImpl;

import java.sql.SQLException;
import java.util.HashMap;

import com.cp.newspaper.entity.Customer;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.repository.CustRepo;
import com.cp.newspaper.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	public boolean isCustExists(Customer cust) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public HashMap<Long, Customer> display() {
		// TODO Auto-generated method stub
		return null;
	}

	public void createCustomer(Customer cust) throws CPException {
		// TODO Auto-generated method stub
		CustRepo custRepo = new CustRepo();
		custRepo.insertCustomer(cust);

	}

}
