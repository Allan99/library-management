package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.StudentDao;
import model.entities.Student;

public class StudentDaoJDBC implements StudentDao {

	private Connection conn = null;

	@Override
	public void insert(Student student) {
		conn = DB.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO student " + "(number, password) " + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, student.getNumber());
			ps.setString(2, student.getPassword());

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
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Student findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findStudentLogin(String studentNumber, String studentPassword) {
		conn = DB.getConnection();
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
				return new Student(id, number, password);
			}
			return null;

		} catch (Exception e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
