package com.cp.newspaper.entity;

public class Particular {
	private int Part_id;
	private String Part_name;
	private int Part_amount;
	
	public Particular(int part_id, String part_name, int part_amount) {
		super();
		Part_id = part_id;
		Part_name = part_name;
		Part_amount = part_amount;
	}
	public Particular() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Particular(String part_name, int part_amount) {
		// TODO Auto-generated constructor stub
		super();
		
		Part_name = part_name;
		Part_amount = part_amount;

	}
	@Override
	public String toString() {
		return "Particular [Part_id=" + Part_id + ", Part_name=" + Part_name + ", Part_amount=" + Part_amount + "]";
	}
	public int getPart_id() {
		return Part_id;
	}
	public void setPart_id(int part_id) {
		Part_id = part_id;
	}
	public String getPart_name() {
		return Part_name;
	}
	public void setPart_name(String part_name) {
		Part_name = part_name;
	}
	public int getPart_amount() {
		return Part_amount;
	}
	public void setPart_amount(int part_amount) {
		Part_amount = part_amount;
	}

}
