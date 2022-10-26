package com.cp.newspaper.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cp.newspaper.entity.Particular;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.jdbc.DBManager;
import com.cp.newspaper.service.ParticularService;
import com.cp.newspaper.serviceImpl.ParticularServiceImpl;

public class TestParticularServiceImpl {
	private static ParticularService partServ = null;
	static HashMap<String, Particular> partCache = null;
	static DBManager dbm = null;
	static Connection con = null;
	static List<Particular> expList = null;
	HashMap<String, Particular> partList = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before");
		partServ = new ParticularServiceImpl();
		partCache = new HashMap<>();
		dbm = DBManager.getDBManager();
		expList = new ArrayList<>();
	}

	@Before
	public void setUp() throws Exception {
		con = dbm.getConnection();
	}

	@Test
	public void testGetParticularDetails() throws CPException {
		Particular particular = new Particular(8, "TimesOfIndia", 300);
		partList = partServ.getPartHashMap();
		Particular part = partList.get(8);

		assertEquals(particular.getPart_name(), part.getPart_name());

		assertEquals(particular.getPart_amount(), part.getPart_amount());

	}

}
