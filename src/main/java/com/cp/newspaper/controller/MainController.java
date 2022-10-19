package com.cp.newspaper.controller;

import java.util.HashMap;
import java.util.Scanner;

import com.cp.newspaper.entity.Customer;
import com.cp.newspaper.entity.Particular;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.jdbc.MessageBundle;
import com.cp.newspaper.service.CustService;
import com.cp.newspaper.service.ParticularService;
import com.cp.newspaper.serviceImpl.CustServiceImpl;
import com.cp.newspaper.serviceImpl.ParticularServiceImpl;

public class MainController {
	static HashMap<Long, Customer> customerHash = new HashMap<Long, Customer>();
	private static HashMap<String, Particular> partCache = new HashMap<String, Particular>();

	private static void loadCache() throws CPException {
		CustService custService = new CustServiceImpl();
		// System.out.println("Object Id :: " + custService);
		customerHash = custService.display();
		// System.out.println(customerHash);

		ParticularService part = new ParticularServiceImpl();
		partCache = part.getPartHashMap();
		// System.out.println(prodCache);

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
				ParticularService part = new ParticularServiceImpl();
				while (true) {

					try {
						System.out.println("Enter particular name");
						String partName = sc.next();

						System.out.println("Enter particular Amount");
						int partAmount = sc.nextInt();

						if (partCache.containsKey(partName)) {
							System.out.println("Particular is already available");
						} else {
							Particular particular = new Particular(partName, partAmount);
							int partId = part.createPart(particular);
							particular.setPart_id(partId);
							partCache.put(partName, particular);
							System.out.println("Particular details inserted successfully");
							// System.out.println(partCache);
						}

					} catch (Exception ex) {
						ex.printStackTrace();
						break;
					}

					System.out.println("Do you want to add another particular [Y]es or [N]o?");
					String ch = sc.next();
					sc.nextLine();
					if (ch.equals("Y") || ch.equals("y")) {
						continue;
					} else {
						break;
					}
				}
				break;

			case 2:
				CustService custService = new CustServiceImpl();

				int custId = 0;

				// custService.display();
				while (true) {

					try {
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
							// custId = customerHash.get(custPhone).getCust_id();

						} else {
							Customer customer = new Customer(custName, custAddrs1, custAddrs2, custPhone);
							custId = custService.createCustomer(customer);
							customer.setCust_id(custId);
							customerHash.put(custPhone, customer);
							System.out.println("Customer details inserted successfully");

						}

					} catch (Exception ex) {
						ex.printStackTrace();
						break;
					}

					System.out.println("Do you want to add another customer [Y]es or [N]o?");
					String ch = sc.next();
					sc.nextLine();
					if (ch.equals("Y") || ch.equals("y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case 3:
				break;
			case 4:
				break;
			}
		}
	}
}
