package com.cp.newspaper.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.cp.newspaper.entity.Customer;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.repository.CustRepo;
import com.cp.newspaper.service.CustomerService;
import com.cp.newspaper.serviceImpl.CustomerServiceImpl;
		
		public class MainController {
			static HashMap<Long, Customer> custHashMap = new HashMap<>();
			private static void loadCache() throws CPException {
				CustRepo cr = new CustRepo();
				List<Customer> listCust = cr.getCustomerDetails();
				for (Customer cObj : listCust) {
					custHashMap.put(cObj.getCust_phone(), cObj);
				}
			}
			public static void main(String[] args) throws CPException {
				// TODO Auto-generated method stub
				loadCache();
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
						
							System.out.println("Customer Name");
							String custName = sc.next();
							System.out.println("Customer_Address1");
							String cust_Address1 = sc.next();
							System.out.println("Customer_Address2");
							String cust_Address2 = sc.next();
							System.out.println("Customer Phone");
							Long custPhone = sc.nextLong();
							CustomerService custServ = new CustomerServiceImpl();
							Customer cust = new Customer(custName, cust_Address1,cust_Address2,custPhone);
							System.out.println("Customer created successfully");
							try {
								System.out.println(cust.toString());
								custServ.createCustomer(cust);
							} catch (CPException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							custHashMap.put(custPhone, cust);
	
						break;
					case 3:
						break;
					case 4:
						System.out.println("Exit Successfully");
						sc.close();
						System.exit(0);
						break;
					default:
						System.out.println("Please enter option 1, 2, 3 or 4 ");
						
					}
				}
			}

	}


