package com.cp.newspaper.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cp.newspaper.entity.Bill;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.jdbc.DBManager;
import com.cp.newspaper.service.BillService;
import com.cp.newspaper.serviceImpl.BillServiceImpl;

public class TestBillServiceImpl {
	private static BillService bilServ = null;
	static HashMap<Integer, Bill> billHash = null;
	static DBManager dbm = null;
	static Connection con = null;
	static List<Bill> expList = null;
	List<Bill> billList = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before");
		bilServ = new BillServiceImpl();
		billHash = new HashMap<>();
		dbm = DBManager.getDBManager();
		expList = new ArrayList<>();
	}

	@Before
	public void setUp() throws Exception {
		con = dbm.getConnection();
	}

	@Test
	public void testGetgetAllBillData() throws CPException, ParseException {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = formatter1.parse("2022-10-23");
		Bill bill = new Bill(15, date1, "SEPTEMBER", 22);
		billList = bilServ.getAllBillData();
		Bill bil = billList.get(0);

		assertEquals(bill.getBill_date(), bil.getBill_date());

		assertEquals(bill.getBill_month(), bil.getBill_month());
		assertEquals(bill.getCust_id(), bil.getCust_id());

	}

	@Test
	public void testGetAllBillData() throws CPException, ParseException {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = formatter1.parse("2022-10-23");
		Bill bill = new Bill(15, date1, "SEPTEMBER", 22);
		List<Bill> billHash = bilServ.getAllBillData();
		Bill bil = billHash.get(0);

		assertEquals(bill.getBill_date(), bil.getBill_date());

		assertEquals(bill.getBill_month(), bil.getBill_month());
		assertEquals(bill.getCust_id(), bil.getCust_id());

	}

}
