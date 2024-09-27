package model.dao;

import db.DB;
import model.dao.impl.BookDaoJDBC;
import model.dao.impl.StudentDaoJDBC;

public class DaoFactory {
	
	public static StudentDao createStudentDao() {
		return new StudentDaoJDBC(DB.getConnection());
	}
	
	public static BookDao createBookDao() {
		return new BookDaoJDBC(DB.getConnection());
	}

}
