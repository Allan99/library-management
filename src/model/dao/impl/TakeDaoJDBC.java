package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.TakeDao;
import model.entities.Take;

public class TakeDaoJDBC implements TakeDao{
	
	private Connection conn;
	
	public TakeDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Take take) {
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("INSERT INTO public.take(\r\n"
					+ "            \"StudentNumber\", \"FirstName\", \"LastName\", \"Gender\", \"BookTitle\", \r\n"
					+ "            \"Image\", \"Date\", \"CheckReturn\")\r\n"
					+ "    VALUES (?, ?, ?, ?, ?, \r\n"
					+ "            ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, take.getStudentNumber());
			ps.setString(2, take.getFirstName());
			ps.setString(3, take.getLastName());
			ps.setString(4, take.getGender());
			ps.setString(5, take.getBookTitle());
			ps.setString(6, take.getImage());
			ps.setDate(7, new java.sql.Date(take.getDate().getTime()));
			ps.setString(8, take.getCheckReturn());
			
			int rowsAffected = ps.executeUpdate();
			
			conn.commit();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					take.setId(id);
				}
			}else {
				throw new DbException("Unexpected error! no rows affected");
			}
			
		}catch(Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM take"
					+ " WHERE id = ?");
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public Take findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement("SELECT * FROM take WHERE id = ?");
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Take take = instantiateTake(rs);
				return take;
			}
			return null;
			
		}catch(Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Take instantiateTake(ResultSet rs) throws SQLException {
		Take take = new Take();
		take.setStudentNumber(rs.getString("StudentNumber"));
		take.setFirstName(rs.getString("FirstName"));
		take.setLastName(rs.getString("LastName"));
		take.setGender(rs.getString("Gender"));
		take.setBookTitle(rs.getString("BookTitle"));
		take.setImage(rs.getString("Image"));
		take.setDate(rs.getDate("Date"));
		take.setCheckReturn(rs.getString("CheckReturn"));
		return take;
	}

	@Override
	public List<Take> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM take");
			
			ps.executeQuery();
			
			List<Take> allTakes = new ArrayList<Take>();
			
			while(rs.next()) {
				Take take = instantiateTake(rs);
				allTakes.add(take);
			}
			
			return allTakes;
			
		}catch(Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
