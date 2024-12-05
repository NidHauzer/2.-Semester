package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Party;
import model.Shipment;

public class ShipmentDB implements ShipmentDBIF {

	DBConnection dbc = DBConnection.getInstance();
	PartyDB pdb;
	
	public Shipment create(Shipment s) throws SQLException {
		String phoneNo = s.getParty().getPhoneNo();
		LocalDate date = s.getDate();
		int employeeNo = s.getEmployee().getEmployeeNo();
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("INSERT INTO Shipment VALUES (?, ?, ?)");
		p.setString(0, phoneNo);
		p.setDate(1, Date.valueOf(date));
		p.setInt(2, employeeNo);
		ResultSet rs = p.executeQuery();
		
		return buildObject(rs);
	}

	@Override
	public boolean delete(Shipment s) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shipment update(Shipment s) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shipment> findAll() throws SQLException {
		Connection con = dbc.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM Shipment");
		return buildObjects(rs);
	}

	@Override
	public Shipment findByShipmentNo(int shipmentNo) throws SQLException {
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM Shipment WHERE shipmentNo = ?");
		p.setInt(0, shipmentNo);
		ResultSet rs = p.executeQuery();
		return buildObject(rs);
		
	}
	
	public Shipment buildObject(ResultSet rs) throws SQLException {
		pdb = new PartyDB();
		
		Party party = pdb.findByPhoneNo(rs.getString("phoneNo"));
		int shipmentNo = rs.getInt("shipmentNo");
		LocalDate date = rs.getDate("date").toLocalDate();
		
		return new Shipment(party, shipmentNo, date);
	}
	
	public List<Shipment> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Shipment> list = new ArrayList();
		while(rs.next()) {
			list.add(buildObject(rs));
		}
		return list;
	}

}
