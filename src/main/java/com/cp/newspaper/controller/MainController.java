package com.cp.newspaper.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.cp.newspaper.entity.Customer;
import com.cp.newspaper.entity.Particular;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.jdbc.MessageBundle;
import com.cp.newspaper.repository.CustRepo;
import com.cp.newspaper.repository.PartRepo;
import com.cp.newspaper.service.CustService;
import com.cp.newspaper.service.ParticularService;
import com.cp.newspaper.serviceImpl.CustServiceImpl;
import com.cp.newspaper.serviceImpl.ParticularServiceImpl;

public class MainController {
	 static HashMap<Long, Customer> customerHash = new HashMap<Long, Customer>();

	private static void loadCache() {
		CustService custService = new CustServiceImpl();
		// System.out.println("Object Id :: " + custService);
		customerHash = custService.display();
		// System.out.println(customerHash);

	}

	public static void main(String[] args) throws CPException {
		// TODO Auto-generated method stub
		MessageBundle mb = MessageBundle.getBundle();
		try {
			if ((customerHash != null || customerHash.size() == 0)) {
				loadCache();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			System.out.println("============= Main Menu ============");
			System.out.println("1. Add Particular Details");
			System.out.println("2. Add Customer Details");
			System.out.println("3. Generate Newspaper Bill");
			System.out.println("4. Exit");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			System.out.println(choice);
			switch (choice) {
			case 1:
				break;
			case 2:
				CustService custService = new CustServiceImpl();

				int custId = 0;

				// custService.display();

				System.out.println("Enter the Customer Name");
				String custName = sc.next();

				System.out.println("Enter the Customer Address1");
				String custAddrs1 = sc.next();

				System.out.println("Enter the Customer Address2");
				String custAddrs2 = sc.next();
				System.out.println("Enter the Customer Phone Number");
				long custPhone = sc.nextLong();

				if (customerHash.containsKey(custPhone)) {
					System.out.println("Already Exist");
					custId = customerHash.get(custPhone).getCust_id();

				} else {
					Customer customer = new Customer(custName, custAddrs1, custAddrs2, custPhone);
					custId = custService.createCustomer(customer);
					customer.setCust_id(custId);
					customerHash.put(custPhone, customer);

				}
				// printing customer details
				System.out.println(customerHash.get(custPhone));

				System.out.println();
				break;
			}
		}
	}
}
