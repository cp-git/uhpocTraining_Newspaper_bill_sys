package com.cp.newspaper.entity;

public class BillParticular {

	private int bpart_id;
	private int bill_id;
	private int part_id;

	public BillParticular(int bill_id, int part_id) {
		super();
		this.bill_id = bill_id;
		this.part_id = part_id;
	}

	public BillParticular(int bpart_id, int bill_id, int part_id) {
		super();
		this.bpart_id = bpart_id;
		this.bill_id = bill_id;
		this.part_id = part_id;
	}

	public int getBpart_id() {
		return bpart_id;
	}

	public void setBpart_id(int bpart_id) {
		this.bpart_id = bpart_id;
	}

	public int getBill_id() {
		return bill_id;
	}

	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}

	public int getPart_id() {
		return part_id;
	}

	public void setPart_id(int part_id) {
		this.part_id = part_id;
	}

	@Override
	public String toString() {
		return "BillParticular [bpart_id=" + bpart_id + ", bill_id=" + bill_id + ", part_id=" + part_id + "]";
	}

}
