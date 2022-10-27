package com.cp.newspaper.service;

import java.util.HashMap;
import java.util.List;

import com.cp.newspaper.entity.Customer;

public interface CustService {

	int createCustomer(Customer customer);

	List<Customer> getDetailsAll();

	HashMap<Long, Customer> initializeCustomerHash();

	// boolean isCacheEmpty(); }

}
