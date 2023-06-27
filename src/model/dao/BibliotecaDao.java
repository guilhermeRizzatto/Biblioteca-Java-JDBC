package model.dao;

import java.util.List;

import model.entities.Biblioteca;

public interface BibliotecaDao {
	
	void insert(Biblioteca obj);
	void update(Biblioteca obj);
	void deleteById(Integer id);
	Biblioteca findById(Integer id);
	List<Biblioteca> findAll();

}
