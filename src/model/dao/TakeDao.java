package model.dao;

import java.util.List;

import model.entities.Take;

public interface TakeDao {
	
	void insert(Take take);
	void deleteById(Integer id);
	Take findById(Integer id);
	List<Take> findAll();

}
