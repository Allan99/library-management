package model.dao;

import model.dao.impl.StudentDaoJDBC;

public class DaoFactory {
	
	public static StudentDao createStudentDao() {
		return new StudentDaoJDBC();
	}

}
