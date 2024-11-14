package model.dao;

import db.DB;
import model.dao.impl.BookDaoJDBC;
import model.dao.impl.StudentDaoJDBC;
import model.dao.impl.TakeDaoJDBC;

public class DaoFactory {
	
	public static StudentDao createStudentDao() {
		return new StudentDaoJDBC(DB.getConnection());
	}
	
	public static BookDao createBookDao() {
		return new BookDaoJDBC(DB.getConnection());
	}
	
	public static TakeDao createTakeDao() {
		return new TakeDaoJDBC(DB.getConnection());
	}

}
