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
	DBManager dbm = DBManager.getDBManager();

	Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	public int insertParticular(Particular part) throws CPException {
		// TODO Auto-generated method stub

		String insertParticular = "INSERT INTO particular ( part_name, part_amount) VALUES (?,?)";
		con = dbm.getConnection();
		try {

			pstmt = con.prepareStatement(insertParticular);
			pstmt.setString(1, part.getPart_name());
			pstmt.setInt(2, part.getPart_amount());

			pstmt.execute();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbm.closeConnection(con);
		}
		return getLastPartId();

	}

	private int getLastPartId() {
		// TODO Auto-generated method stub
		int partId = 0;

		String insertQuery = "select max(part_id) from particular";
		try {
			con = dbm.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(insertQuery);
			while (rs.next()) {
				partId = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			dbm.closeConnection(con);

		}
		return partId;
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

//	public Particular getParticularByName(String partName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public Particular getParticularById(int part_id) {
		// TODO Auto-generated method stub
		int partId = 0;
		Particular part = null;
		String insertQuery = "select * from Particular where part_id = ?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setInt(1, part_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int partId2 = rs.getInt("part_id");
				String partName = rs.getString("part_name");
				int partAmount = rs.getInt("part_amount");

				part = new Particular(partId2, partName, partAmount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			dbm.closeConnection(con);

		}
		return part;
	}

}
