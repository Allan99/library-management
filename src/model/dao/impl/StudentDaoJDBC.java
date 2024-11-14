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
import model.dao.StudentDao;
import model.entities.Student;

public class StudentDaoJDBC implements StudentDao {

	private Connection conn;
	
	public StudentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Student student) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO student " + "(number, password) " + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, student.getNumber());
			ps.setString(2, student.getPassword());
			ps.setString(3, student.getImage());

			int rowsAffected = ps.executeUpdate();

			conn.commit();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					student.setId(id);
				}
			} else {
				throw new DbException("Unexpected error! no rows affected");
			}

		} catch (Exception e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void update(Student student) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE student "
					+ "SET number = ?, password = ?, image = ? "
					+ "WHERE id = ?");
			
			ps.setString(1, student.getNumber());
			ps.setString(2, student.getPassword());
			ps.setString(3, student.getImage());
			ps.setInt(4, student.getId());
			
			ps.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM student "
					+"WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Student findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement("SELECT * "
					+"FROM student "
					+"WHERE id = ?");
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Student student = instantiateStudent(rs);
				return student;
			}
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Student instantiateStudent(ResultSet rs) throws SQLException {
		Student student = new Student();
		student.setId(rs.getInt("id"));
		student.setNumber(rs.getString("number"));
		student.setPassword(rs.getString("password"));
		student.setImage(rs.getString("image"));
		student.setFirstName(rs.getString("Name"));
		student.setLastName(rs.getString("LastName"));
		student.setGender(rs.getString("gender"));
		return student;
	}

	@Override
	public List<Student> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement("SELECT id, number, password "
					+"FROM student "
					+"ORDER BY number");
			rs = ps.executeQuery();
			
			List<Student> allStudents = new ArrayList<Student>();
			
			while(rs.next()) {
				Student student = instantiateStudent(rs);
				allStudents.add(student);
			}
			return allStudents;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Student findStudentLogin(String studentNumber, String studentPassword) {
		String sql = "SELECT * FROM student WHERE number = ? AND password = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, studentNumber);
			ps.setString(2, studentPassword);
			rs = ps.executeQuery();
			conn.commit();

			if (rs.next()) {
				int id = rs.getInt(1);
				String number = rs.getString(2);
				String password = rs.getString(3);
				String image = rs.getString(4);
				String firstName = rs.getString(5);
				String lastName = rs.getString(6);
				String gender = rs.getString(7);
				return new Student(id, firstName, lastName, gender, number, password, image);
			}
			return null;

		} catch (Exception e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Student findStudentByNumber(String studentNumber) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM student WHERE number = ?");
			
			ps.setString(1, studentNumber);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Student student = instantiateStudent(rs);
				return student;
			}
			return null;
		}catch(Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
