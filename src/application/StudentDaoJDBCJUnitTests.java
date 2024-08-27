package application;

import java.util.List;

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
	
	@Test
	public void testUpdate() {
		StudentDao studentDao = DaoFactory.createStudentDao();

		Student student = studentDao.findStudentLogin("9034588", "fff444555");
		student.setNumber("123456");
		studentDao.update(student);
	}
	
	@Test
	public void testFindAll() {
		StudentDao studentDao = DaoFactory.createStudentDao();

		List<Student> students = studentDao.findAll();
		
		for(Student student: students) {
			System.out.println(student);
		}
	}
	
	@Test
	public void testFindById() {
		StudentDao studentDao = DaoFactory.createStudentDao();

		System.out.println(studentDao.findById(2));
	}
	
	@Test
	public void testDeleteById() {
		StudentDao studentDao = DaoFactory.createStudentDao();
		
		studentDao.deleteById(2);
	}
}
