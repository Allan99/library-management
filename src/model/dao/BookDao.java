package model.dao;

import java.util.List;

import model.entities.AvailableBook;

public interface BookDao {
	
	void insert(AvailableBook book);
	void update(AvailableBook book);
	void deleteById(Integer id);
	AvailableBook findById(Integer id);
	List<AvailableBook> findAll();
}
