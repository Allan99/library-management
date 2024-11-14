package model.dao;

import java.util.List;

import model.entities.Student;

public interface StudentDao {
	
	void insert(Student student);
	void update(Student student);
	void deleteById(Integer id);
	Student findById(Integer id);
	Student findStudentLogin(String studentNumber, String studentPassword);
	Student findStudentByNumber(String studentNumber);
	List<Student> findAll();
}
