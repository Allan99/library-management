package application;

import org.junit.Test;

import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.entities.Student;

public class StudentDaoJDBCJUnitTests {

	@Test
	public void testInsert() {
		StudentDao studentDao = DaoFactory.createStudentDao();

		for (int i = 0; i < 3; i++) {

			Student student = new Student(1, "9034588", "fff444555");

			studentDao.insert(student);
		}
	}

	@Test
	public void testFindStudentLogin() {
		StudentDao studentDao = DaoFactory.createStudentDao();

		for (int i = 0; i < 3; i++) {
			String number = "9034588";
			String password = "fff444";

			Student found = studentDao.findStudentLogin(number, password);

			System.out.print(found + " ");
			System.out.println(i);
		}
	}
}
