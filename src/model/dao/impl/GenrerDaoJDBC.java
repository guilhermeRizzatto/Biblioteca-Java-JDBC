package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.GenrerDao;
import model.entities.Genrer;

public class GenrerDaoJDBC implements GenrerDao {

	private Connection conn;
	
	public GenrerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Genrer obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO genres "
					+ "(Name_Genrer) "
					+ "VALUES " 
					+ "(?)");
	
			st.setString(1, obj.getName_genrer());			
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected <= 0) {
				throw new DbException("Unexpected error! No rows affected!");					
			}		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void update(Genrer obj, String oldName) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE genres "
					+ "SET Name_Genrer = ? "
					+ "WHERE Name_Genrer = ?");
	
			st.setString(1, obj.getName_genrer());		
			st.setString(2, oldName);
			
			st.executeUpdate();
		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}				
	}

	@Override
	public void deleteByName(String name) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM genres WHERE Name_Genrer = ? ");					
						
			st.setString(1, name);
					
			st.executeUpdate();
		
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}							
	}

	@Override
	public Genrer findByName(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM genres "
					+"WHERE Name_Genrer = ?");
			
			st.setString(1, name);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Genrer genrer = new Genrer();
				genrer.setName_genrer(rs.getString("Name_Genrer"));				
				return genrer;
			}
			return null;			
		}catch(SQLException e)		{
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Genrer> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM genres "
					+"ORDER BY Name_Genrer");
			
			rs = st.executeQuery();
			
			List<Genrer> list = new ArrayList<>();
			
			while(rs.next()) {
				Genrer genrer = new Genrer();
				genrer.setName_genrer(rs.getString("Name_Genrer"));					
				list.add(genrer);
			}
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	

}
