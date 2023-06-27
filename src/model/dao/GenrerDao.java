package model.dao;

import java.util.List;

import model.entities.Genrer;

public interface GenrerDao {
	
	void insert(Genrer obj);
	void update(Genrer obj,String name);
	void deleteByName(String name);
	Genrer findByName(String name);
	List<Genrer> findAll();

}
