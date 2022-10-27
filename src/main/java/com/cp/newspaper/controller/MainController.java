package com.cp.newspaper.controller;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.cp.newspaper.entity.Bill;
import com.cp.newspaper.entity.BillParticular;
import com.cp.newspaper.entity.Customer;
import com.cp.newspaper.entity.Particular;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.jdbc.MessageBundle;
import com.cp.newspaper.service.BillService;
import com.cp.newspaper.service.CustService;
import com.cp.newspaper.service.ParticularService;
import com.cp.newspaper.serviceImpl.BillServiceImpl;
import com.cp.newspaper.serviceImpl.CustServiceImpl;
import com.cp.newspaper.serviceImpl.ParticularServiceImpl;

public class MainController {
	static HashMap<Long, Customer> customerHash = new HashMap<Long, Customer>();
	private static HashMap<String, Particular> partCache = new HashMap<String, Particular>();

	// For Loading Cache of customer and particular
	private static void loadCache() throws CPException {
		CustService custService = new CustServiceImpl();
		// System.out.println("Object Id :: " + custService);
		customerHash = custService.initializeCustomerHash();
		// System.out.println(customerHash);

		ParticularService part = new ParticularServiceImpl();
		partCache = part.initializeParticularHash();
		// System.out.println(partCache);

	}

	public static void main(String[] args) throws CPException, Exception {
		// TODO Auto-generated method stub
		MessageBundle mb = MessageBundle.getBundle();

		// checking in customerHash data is available or not.
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
			System.out.println("1. Add NewsPaper Details");
			System.out.println("2. Add Customer Details");
			System.out.println("3. Generate Newspaper Bill");
			System.out.println("4. Exit");
			System.out.println("Please enter a valid options 1,2,3 or 4");

			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			// System.out.println(choice);
			switch (choice) {

			// Add NewsPaper Details
			case 1:
				ParticularService part = new ParticularServiceImpl();
				while (true) {

					try {
						System.out.println("Enter particular name");
						String partName = sc.next();

						if (partCache.containsKey(partName)) {
							System.out.println("Particular is already available");
						} else {
							System.out.println("Enter particular Amount");
							int partAmount = sc.nextInt();

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
					if (ch.equalsIgnoreCase("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;

			// Add Customer Details
			case 2:
				CustService custService = new CustServiceImpl();

				int custId = 0;

				// custService.initializeCustomerHash();
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
						if (custPhone > 1000000000 && custPhone <= 9999999999L) {
							if (customerHash.containsKey(custPhone)) {
								System.out.println("Already Exist");

							} else {
								Customer customer = new Customer(custName, custAddrs1, custAddrs2, custPhone);
								custId = custService.createCustomer(customer);
								customer.setCust_id(custId);
								System.out.println("custid" + custId);
								customerHash.put(custPhone, customer);
								System.out.println("Customer details inserted successfully");

							}
						} else {
							System.out.println("Invalid customer phone");
						}
					}

					catch (Exception ex) {
						ex.printStackTrace();
						break;
					}

					System.out.println("Do you want to add another customer [Y]es or [N]o?");
					String ch = sc.next();
					sc.nextLine();
					if (ch.equalsIgnoreCase("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;

			// Generate NewsPaper Bill
			case 3:

				if (customerHash.isEmpty()) {
					System.out.println("Please create Customer first");
					continue;
				}

				if (partCache.isEmpty()) {
					System.out.println("Please create particular first");
					continue;
				}

				BillService billService = new BillServiceImpl();

				System.out.println("Enter the Customer Phone Number");
				long custPhone = sc.nextLong();
				int billId = 0;

				if (customerHash.containsKey(custPhone)) {
					// System.out.println("customer added");
					custId = customerHash.get(custPhone).getCust_id();
					// System.out.println("here" + custId);
					Bill bill = new Bill(custId);
					// System.out.println(bill.getCust_id());
					billId = billService.billCreate(bill);

					// Bill Particular
					HashMap<String, BillParticular> partcart = new HashMap<>();

					while (true) {
						System.out.println("Enter particular name");
						String partName = sc.next();

						if (partCache.containsKey(partName)) {
							Particular particular = partCache.get(partName);

							BillParticular bilPart = new BillParticular(bill.getBill_id(), particular.getPart_id());
							partcart.put(partName, bilPart);
//							partCache.put(partName, particular);

							System.out.println("Particular added");

							System.out.println("Do you want to add another particular - [Y]es or [N]o?");
							String ch = sc.next();
							if (ch.equalsIgnoreCase("Y")) {
								continue;
							} else {
								billService.createBillParticular(partcart);
								break;
							}
						} else {
							System.out.println("Please enter valid particular name to continue");

							continue;
						}

					}
					// printing Agency details
					FileReader reader = new FileReader("src/main/resources/Agency_info");
					Properties p = new Properties();
					p.load(reader);

					System.out.println();
					System.out.println(
							"--------------------------------------------------------------------------------------------------");
					System.out.println("                                 " + p.getProperty("AgencyName")
							+ "										     ");
					System.out.println("				 	" + p.getProperty("Address")
							+ "											 ");
					System.out.println("			  	     " + p.getProperty("City") + " " + p.getProperty("state"));

					System.out.println();

					// printing customer details
					Customer customer = customerHash.get(custPhone);
					Bill billTemp = billService.getBillById(billId);
					System.out.println("Name 	    :" + customer.getCust_name() + "              Bill Date 	    :"
							+ billTemp.getBill_date());
					System.out.println("Address 1   :" + customer.getCust_address1()
							+ "           Bill Month          :" + billTemp.getBill_month());
					System.out.println("Address 2   :" + customer.getCust_address2());
					System.out.println("Phone       :" + customer.getCust_phone());
					System.out.println();

					// printing Bill Particular details
					// System.out.println(billId);

					List<BillParticular> listBillPart = billService.getBillParticular(billId);
					// System.out.println(listBillPart);

					ParticularService part1 = new ParticularServiceImpl();
					float totalPartPrice = 0;

					System.out.println("Particulars" + "		" + "Amount");

					for (BillParticular billPart : listBillPart) {
//						System.out.println(bilPart.getPart_id());
						int partId = billPart.getPart_id();

						Particular particular = part1.getParticularById(partId);
						totalPartPrice = totalPartPrice + particular.getPart_amount();

						System.out.println(particular.getPart_name() + "			" + particular.getPart_amount());

					}

					System.out.println("Total" + "			" + totalPartPrice);
				} else {
					System.out.println("Customer not exist.Please Enter a valid customer Phone ");
					break;
				}

				break;

			// Terminating Application
			case 4:

				System.out.println("Terminated Successfully");
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Please enter option 1, 2, 3 or 4 ");
				break;
			}
		}
	}
}
