package com.cp.newspaper.serviceImpl;

import java.util.HashMap;
import java.util.List;

import com.cp.newspaper.entity.Bill;
import com.cp.newspaper.entity.BillParticular;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.service.BillService;

public class BillServiceImpl implements BillService {
	BillRepo billRepo = new BillRepo();
	HashMap<Integer, Bill> billHash = new HashMap<Integer, Bill>();

	@Override
	public int billCreate(Bill bill) {
		// TODO Auto-generated method stub
		int billId = 0;
		try {
			billId = billRepo.insertbill(bill);

		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return billId;

	}

	@Override
	public List<Bill> getAllBillData() {
		// TODO Auto-generated method stub
		return billRepo.getbillDetails();
	}

	@Override
	public boolean isCacheEmpty() {
		// TODO Auto-generated method stub
		return billHash.isEmpty();
	}

	@Override
	public int createBillParticular(HashMap<String, BillParticular> partCart) {
		// TODO Auto-generated method stub
		int billId = 0;
		for (String partName : partCart.keySet()) {
			BillParticular billPart = partCart.get(partName);
			billPart.setBillId(billRepo.getBillId());
			partCart.put(partName, billPart);
		}

		billId = billRepo.insertBillParticular(partCart);

		return billId;
	}

	@Override
	public List<BillParticular> getBillParticular(int billId) {
		// TODO Auto-generated method stub
		List<BillParticular> listPartList = billRepo.getAllBillParticular(billId);

		return listPartList;
	}

	@Override
	public Bill getBill(int billId) {
		// TODO Auto-generated method stub
		Bill bill = billRepo.getBillForBillId(billId);
		// System.out.println(bill.getBillDate());
		return bill;
	}

}
