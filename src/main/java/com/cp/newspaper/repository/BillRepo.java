package com.cp.newspaper.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cp.newspaper.entity.Bill;
import com.cp.newspaper.entity.BillParticular;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.jdbc.DBManager;

public class BillRepo {
	DBManager dbManager = DBManager.getDBManager();
	Connection con = null;
	PreparedStatement psobj = null;
	ResultSet rsobj = null;
	Statement stmt = null;

	public int insertbill(Bill bill) throws CPException {
		con = dbManager.getConnection();
		// String
		String insertBill = "INSERT INTO bill(bill_date,bill_month,cust_id) VALUES(?,?,?)";
		LocalDate now = LocalDate.now();
		LocalDate earlier = now.minusMonths(1);
		Date date = Date.valueOf(now); // psobj .setDate needs date datatype.

		earlier.getMonth();
		earlier.getYear();
		System.out.println(now);
		System.out.println(earlier);
		System.out.println(earlier.getMonth());
		try {
			System.out.println(bill.toString());
			psobj = con.prepareStatement(insertBill);
			psobj.setDate(1, date);
			psobj.setString(2, String.valueOf(earlier.getMonth()));
			psobj.setInt(3, bill.getCust_id());
			System.out.println("inside repo" + bill.getCust_id());
			psobj.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(con);
		}

		return getLastbillId();

	}

	public int getLastbillId() {
		int billId = 0;
		String insertQuery = "select max(bill_id) from bill";
		try {
			con = dbManager.getConnection();
			stmt = con.createStatement();
			rsobj = stmt.executeQuery(insertQuery);
			while (rsobj.next()) {
				billId = rsobj.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			dbManager.closeConnection(con);

		}
		return billId;
	}

	public List<Bill> getbillDetails() {
		List<Bill> bill = new ArrayList<>();
		String DataQuery = "Select * from bill";

		try {

			con = dbManager.getConnection();
			psobj = con.prepareStatement(DataQuery);
			rsobj = psobj.executeQuery();
			while (rsobj.next()) {

				Date billDate = rsobj.getDate("bill_date");
				String billMonth = rsobj.getString("bill_month");
				int custId = rsobj.getInt("cust_id");

				Bill bil = new Bill(billDate, billMonth, custId);

				bill.add(bil);
				// System.out.println(bill);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(con);

		}
		return bill;

	}

	// Invoice Product
	public int getBillId() {

		String queryBillId = "SELECT max(bill_Id) FROM bill";
		int billId = 0;
		try {
			con = dbManager.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryBillId);
			while (rs.next()) {
				billId = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(con);
		}
		return billId;
	}

	public int insertBillParticular(HashMap<String, BillParticular> partCart) {
		// TODO Auto-generated method stub
		String insertCart = "INSERT INTO bill_particular(bill_id,part_id) VALUES(?,?)";
		int billId = 0;

		for (String partName : partCart.keySet()) {
			try {
				con = dbManager.getConnection();
				psobj = con.prepareStatement(insertCart);
				BillParticular part = partCart.get(partName);
				billId = part.getBill_id();
				psobj.setInt(1, billId);
				psobj.setInt(2, part.getPart_id());

				// System.out.println(part.getBillId() + " " +
				// part.getPart_id());
				psobj.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				dbManager.closeConnection(con);
			}
		}

		return billId;
	}

	public List<BillParticular> getAllBillParticular(int billId) {
		List<BillParticular> bilPartList = new ArrayList<>();

		String getQuery = "SELECT * FROM bill_particular where bill_id=?";
		try {
			con = dbManager.getConnection();

			psobj = con.prepareStatement(getQuery);
			psobj.setInt(1, billId);

			ResultSet rs = psobj.executeQuery();

			while (rs.next()) {
				int bilPartId = rs.getInt("bpart_id");
				int BillId = rs.getInt("bill_id");
				int partId = rs.getInt("part_id");
				BillParticular bilPart = new BillParticular(bilPartId, BillId, partId);

				bilPartList.add(bilPart);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(con);
		}

		return bilPartList;
	}

	public Bill getBillForBillId(int billId) {
		// TODO Auto-generated method stub

		String getQuery = "SELECT * FROM bill where bill_Id = ?";
		Bill bill = null;
		try {
			con = dbManager.getConnection();
			psobj = con.prepareStatement(getQuery);
			psobj.setInt(1, billId);

			ResultSet rs = psobj.executeQuery();
			while (rs.next()) {

				Date billDate = rsobj.getDate("bill_date");
				String billMonth = rsobj.getString("bill_month");
				int custId = rsobj.getInt("cust_id");

				bill = new Bill(billDate, billMonth, custId);
				// System.out.println("here" + bill.getBillDate());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(con);
		}

		return bill;
	}
	//
	// public float getTotal(int billNo) {
	// // TODO Auto-generated method stub
	// String getTotalQuery =
	// "select sum(b.prod_qty * a.prod_unit_price - (b.prod_qty * a.prod_unit_price
	// * a.prod_discount/100.00)) as total from product as a FULL JOIN
	// invoice_product as b ON a.prod_id = b.prod_id where b.bill_no="
	// + billNo + "";
	// Statement st = null;
	// float totalPrice = 0;
	// try {
	// con = dbManager.getConnection();
	// st = con.createStatement();
	// ResultSet rs = st.executeQuery(getTotalQuery);
	// while (rs.next()) {
	// totalPrice = rs.getFloat(1);
	// }
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (CPException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return totalPrice;
	// }
}
