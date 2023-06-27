package model.dao;

import db.DB;
import model.dao.impl.BibliotecaDaoJDBC;
import model.dao.impl.GenrerDaoJDBC;
import model.dao.impl.LivroDaoJDBC;

public class DaoFactory {
	
	public static BibliotecaDao createBibliotecaDao() {
		return new BibliotecaDaoJDBC(DB.getConnection());
	}
	
	public static LivroDao createLivroDao() {
		return new LivroDaoJDBC(DB.getConnection());
	}
	
	public static GenrerDao createGenrerDao() {
		return new GenrerDaoJDBC(DB.getConnection());
	}

}
