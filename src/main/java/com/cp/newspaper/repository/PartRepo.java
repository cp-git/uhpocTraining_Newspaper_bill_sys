package com.cp.newspaper.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.cp.newspaper.entity.Particular;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.jdbc.DBManager;

public class PartRepo {
	public void insertParticular(Particular part) throws CPException {
		// TODO Auto-generated method stub
		DBManager dbm = DBManager.getDBManager();
		Connection con = null;
		String insertQuery = "INSERT INTO particular ( part_name, part_amount) VALUES (?,?)";
		PreparedStatement pstmt = null;
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, part.getPart_name());
			pstmt.setInt(2, part.getPart_amount());
			
			pstmt.execute();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Particular> getParticularDetails() throws CPException {
		DBManager dbm = DBManager.getDBManager();
		Connection con = null;
		con = dbm.getConnection();
		String dataQuery = "select * from particular";
		Statement stmt = null;
		List<Particular> listPart = new ArrayList<Particular>();

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(dataQuery);
			while (rs.next()) {
				int partId = rs.getInt("part_id");
				String partName = rs.getString("part_name");
				int partAmount = rs.getInt("part_amount");
			

				Particular part = new Particular(partId, partName, partAmount);
				listPart.add(part);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listPart;
	}

	public Particular getProductByName(String partName) {
		// TODO Auto-generated method stub
		return null;
	}
}


