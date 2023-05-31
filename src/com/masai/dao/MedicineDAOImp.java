package com.masai.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.masai.dto.Medicine;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.utility.DBUtils;

public class MedicineDAOImp implements IMedicineDAO {

	@Override
	public void addMedicine(Medicine medicine) throws SomethingWentWrongException {
		try(Connection con = DBUtils.connect()){
			
			String Q = "Insert into medicine values(?,?,?,?,?,?);";
			
			PreparedStatement ps = con.prepareStatement(Q);
			
			ps.setString(1, medicine.getMedId());
			ps.setString(2, medicine.getName());
			ps.setString(3, medicine.getCompany());
			ps.setDouble(4, medicine.getPricePerUnit());
			ps.setDate(5, Date.valueOf(medicine.getMfgDate()));
			ps.setDate(6, Date.valueOf(medicine.getExpDate()));
			
			ps.executeUpdate();
			
		}catch(SQLException e){
			throw new SomethingWentWrongException("Unable to add Employee.");
		}
	}

	@Override
	public List<Medicine> getMedicineList() throws SomethingWentWrongException, NoRecordFoundException {
		List<Medicine> ls = new ArrayList<>();
		try(Connection con = DBUtils.connect()){
			
			String Q = "Select * from medicine order by name";
			PreparedStatement ps = con.prepareStatement(Q);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Medicine m = new Medicine();
				m.setMedId(rs.getString(1));
				m.setName(rs.getString(2));
				m.setCompany(rs.getString(3));
				m.setPricePerUnit(rs.getDouble(4));
				m.setMfgDate(rs.getDate(5).toLocalDate());
				m.setExpDate(rs.getDate(6).toLocalDate());
				ls.add(m);
			}
			
		}catch(SQLException e){
			throw new SomethingWentWrongException("Unable to add Employee.");
		}
		return ls;
	}

	@Override
	public void updateMedicine(Medicine med, String id) throws SomethingWentWrongException {
		try(Connection con = DBUtils.connect()){
			String Q = "Update medicine Set name = ?, company = ?, price_per_unit = ?, MfgDate = ?, ExpDate = ? WHERE med_id = ?";
			PreparedStatement ps = con.prepareStatement(Q);
			
			ps.setString(1, med.getName());
			ps.setString(2, med.getCompany());
			ps.setDouble(3, med.getPricePerUnit());
			ps.setDate(4, Date.valueOf(med.getMfgDate()));
			ps.setDate(5, Date.valueOf(med.getExpDate()));
			ps.setString(6, id);
			
			if(ps.executeUpdate()>0) {
				System.out.println("Medicine Updated successfully.");
			}else {
				throw new SomethingWentWrongException("No medicine found with the provided med_id. Unable to update medicine details.");
			}
		}catch(SQLException e){
			throw new SomethingWentWrongException("Unable to add Employee.");
		}
	}

	@Override
	public void deleteMedicine(String empId) throws SomethingWentWrongException {
		try(Connection con = DBUtils.connect()){
			String Q = "Delete from Medicine where med_id = ?";
			PreparedStatement ps = con.prepareStatement(Q);
			
			ps.setString(1, empId);
			if(ps.executeUpdate()>0) {
				System.out.println("Medicine Deleted successfully.");
			}else {
				throw new SomethingWentWrongException("No medicine found with the provided med_id. Unable to Delete medicine details.");
			}
		}catch(SQLException e){
			throw new SomethingWentWrongException("Unable to add Employee.");
		}
		
	}

	@Override
	public Medicine searchMedById(String medId) throws SomethingWentWrongException {
		Medicine m = null;
		try(Connection con = DBUtils.connect()){
			
			String Q = "Select * from medicine where med_id = ?";
			PreparedStatement ps = con.prepareStatement(Q);
			ps.setString(1, medId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				m = new Medicine();
				m.setMedId(rs.getString(1));
				m.setName(rs.getString(2));
				m.setCompany(rs.getString(3));
				m.setPricePerUnit(rs.getDouble(4));
				m.setMfgDate(rs.getDate(5).toLocalDate());
				m.setExpDate(rs.getDate(6).toLocalDate());
			}
			rs.close();
			
		}catch(SQLException e) {
			throw new SomethingWentWrongException("Something Went Wrong");
		}
		if(m==null) {
			throw new SomethingWentWrongException("No medicine found.");
		}else {
			return m;
		}
	}
	
	@Override
	public List<Medicine> searchExpMeds() throws NoRecordFoundException {
		List<Medicine> ls = new ArrayList<>();
		
		try (Connection con = DBUtils.connect()){
			String Q = "Select * from medicine where expDate< curDate()";
			PreparedStatement ps = con.prepareStatement(Q);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Medicine m =new Medicine();
				m.setMedId(rs.getString(1));
				m.setName(rs.getString(2));
				m.setCompany(rs.getString(3));
				m.setPricePerUnit(rs.getDouble(4));
				m.setMfgDate(rs.getDate(5).toLocalDate());
				m.setExpDate(rs.getDate(6).toLocalDate());
				ls.add(m);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to Print.");
		}
		if(ls==null) {
			throw new NoRecordFoundException("No Records found.");
		}else {
			return ls;
		}
	}

}
