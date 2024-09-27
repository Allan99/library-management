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
import model.dao.BookDao;
import model.entities.AvailableBook;

public class BookDaoJDBC implements BookDao{
	
	Connection conn = null;
	
	public BookDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(AvailableBook book) {
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("INSERT INTO book "
					+ "(title, author, genre, image, date) "
					+ "VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getGenre());
			ps.setString(4, book.getImage());
			ps.setDate(5, new java.sql.Date(book.getDate().getTime()));
			
			int rowsAffected = ps.executeUpdate();
			
			conn.commit();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					book.setId(id);
				}
			}
			
		}catch(Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(AvailableBook book) {
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement("UPDATE book "
					+ "SET title=?, author=?, genre=?, image=?, date=? "
					+ "WHERE id=?;");
			
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getGenre());
			ps.setString(4, book.getImage());
			ps.setDate(5, new java.sql.Date(book.getDate().getTime()));
			ps.setInt(6, book.getId());
			
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
			
			ps = conn.prepareStatement("DELETE FROM book "
					+ "WHERE id=?");
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			conn.commit();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public AvailableBook findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement("SELECT id, title, author, genre, image, date "
					+ "FROM book "
					+"WHERE id=?");
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				AvailableBook book = instantiateBook(rs);
				return book;
			}
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private AvailableBook instantiateBook(ResultSet rs) throws SQLException {
		AvailableBook book = new AvailableBook();
		book.setId(rs.getInt("id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setGenre(rs.getString("genre"));
		book.setImage(rs.getString("image"));
		book.setDate(rs.getDate("date"));
		return book;
	}
	

	@Override
	public List<AvailableBook> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement("SELECT id, title, author, genre, image, date "
					+ "FROM book");
			
			rs = ps.executeQuery();
			
			List<AvailableBook> allBooks = new ArrayList<AvailableBook>();
			
			while(rs.next()) {
				AvailableBook book = instantiateBook(rs);
				allBooks.add(book);
			}
			return allBooks;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
