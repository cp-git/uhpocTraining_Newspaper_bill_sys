package com.cp.newspaper.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cp.newspaper.entity.Customer;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.jdbc.DBManager;

public class CustRepo {
	Connection con;
	PreparedStatement psobj = null;
	ResultSet rsObj = null;
	Statement stmt = null;
	DBManager dbManager = DBManager.getDBManager();

	public int insertCustomer(Customer customer) throws CPException {
		String insertCustomer = "INSERT INTO customer(cust_name,cust_address1,cust_address2,cust_phone) VALUES(?,?,?,?)";

		con = dbManager.getConnection();

		try {
			psobj = con.prepareStatement(insertCustomer);
			psobj.setString(1, customer.getCust_name());
		
			psobj.setString(2, customer.getCust_address1());
			psobj.setString(3, customer.getCust_address2());
			psobj.setLong(2, customer.getCust_phone());
			
			psobj.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(con);
		}
		return getLastCustId();

	}

	public int getLastCustId() {
		int custId = 0;
		String insertQuery = "select max(cust_id) from customer";
		try {
			con = dbManager.getConnection();
			stmt = con.createStatement();
			rsObj = stmt.executeQuery(insertQuery);
			while (rsObj.next()) {
				custId = rsObj.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			dbManager.closeConnection(con);

		}
		return custId;
	}

	public List<Customer> getCustomerDetails() {
		List<Customer> customer = new ArrayList<>();
		String DataQuery = "Select * from customer";

		try {

			con = dbManager.getConnection();
			psobj = con.prepareStatement(DataQuery);
			rsObj = psobj.executeQuery();
			while (rsObj.next()) {
				int custId = rsObj.getInt("cust_id");
				String custName = rsObj.getString("cust_name");
				String custAddrs1 = rsObj.getString("cust_address1");
				String custAddrs2 = rsObj.getString("cust_address2");
				int custPhone = rsObj.getInt("cust_phone");

				Customer cust = new Customer(custId, custName, custAddrs1,
						custAddrs2,custPhone);

				customer.add(cust);
				// System.out.println(customer);

			} // while--Loop Close

		}// try block close
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(con);

		}
		return customer;

	}
}