package application;

import org.junit.Test;

import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.entities.Student;

public class StudentDaoJDBCJUnitTests {

	@Test
	public void testInsert() {
		StudentDao studentDao = DaoFactory.createStudentDao();

		Student student = new Student(1, "9034588", "fff444555");

		studentDao.insert(student);
	}

}
