package com.cp.newspaper.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.cp.newspaper.jdbc.DBManager;

public class BillRepo {
	DBManager dbManager = DBManager.getDBManager();
	Connection con = null;
	PreparedStatement psobj = null;
	ResultSet rsobj = null;
	Statement stmt = null;

}
