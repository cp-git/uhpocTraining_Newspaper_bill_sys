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
	public void insertCustomer(Customer cust) throws CPException {
		// TODO Auto-generated method stub
		DBManager dbm = DBManager.getDBManager();
		Connection con = null;
		String insertQuery = "INSERT INTO customer ( cust_name, cust_address1, cust_address2, cust_phone) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, cust.getCust_name());
			pstmt.setString(2, cust.getCust_address1());
			pstmt.setString(3, cust.getCust_address2());
			pstmt.setLong(4, cust.getCust_phone());
			pstmt.execute();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Customer> getCustomerDetails() throws CPException {
		DBManager dbm = DBManager.getDBManager();
		Connection con = null;
		con = dbm.getConnection();
		String dataQuery = "select * from customer";
		Statement stmt = null;
		List<Customer> listCust = new ArrayList<Customer>();

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(dataQuery);
			while (rs.next()) {
				int custId = rs.getInt("cust_id");
				String custName = rs.getString("cust_name");
				String custAddress1 = rs.getString("cust_address1");
				String custAddress2 = rs.getString("cust_address2");
				long custPhone = rs.getLong("cust_phone");

				Customer cust = new Customer(custId, custName, custAddress1, custAddress2, custPhone);
				listCust.add(cust);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCust;
	}
}
