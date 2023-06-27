package model.dao;

import java.util.List;

import model.entities.Genrer;
import model.entities.Livro;

public interface LivroDao {
	
	void insert(Livro obj);
	void update(Livro obj);
	void deleteById(Integer id);
	Livro findById(Integer id);
	List<Livro> findAll();
	List<Livro> findByGenrer(Genrer genrer);
}
