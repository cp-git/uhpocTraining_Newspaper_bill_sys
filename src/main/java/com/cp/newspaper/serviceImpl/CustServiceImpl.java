package com.cp.newspaper.serviceImpl;


import java.util.HashMap;
import java.util.List;

import com.cp.newspaper.entity.Customer;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.repository.CustRepo;
import com.cp.newspaper.service.CustService;


public class CustServiceImpl implements CustService {
	CustRepo custRepo = new CustRepo();
	HashMap<Long, Customer> customerHash = new HashMap<Long, Customer>();


	@Override
	public int createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		int custId = 0;
		// if(customerHash.containsKey(customer.getCustName()))
		// {
		// System.out.println("Already Exist...");
		// }
		// else {
		try {
			custId = custRepo.insertCustomer(customer);
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
		return custId;

		
	}

	@Override
	public List<Customer> getDetailsAll() {
		// TODO Auto-generated method stub
		return custRepo.getCustomerDetails();
		
	}

	@Override
	public HashMap<Long, Customer> display() {
		// TODO Auto-generated method stub
		// System.out.println("loading Cache...");
				for (Customer customer : custRepo.getCustomerDetails()) {
					customerHash.put(customer.getCust_phone(), customer);

				}
				// System.out.println(customerHash);
				return customerHash;

		
	}

	

}
