package com.cp.newspaper.service;

import java.util.HashMap;
import java.util.List;

import com.cp.newspaper.entity.Bill;
import com.cp.newspaper.entity.BillParticular;

public interface BillService {

	public int billCreate(Bill bill);

	List<Bill> getAllBillData();

	// HashMap<Integer, Bill> display();

	boolean isCacheEmpty();

	int createBillParticular(HashMap<String, BillParticular> partCart);

	public List<BillParticular> getBillParticular(int billId);

	Bill getBill(int billId);

	// public float getTotal(int billId);
}
