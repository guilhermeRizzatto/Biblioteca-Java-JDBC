package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.BibliotecaDao;
import model.entities.Biblioteca;

public class BibliotecaDaoJDBC implements BibliotecaDao{
	
	private Connection conn;
	
	public BibliotecaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Biblioteca obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO bibliotecas "
					+ "(Name, Location) "
					+ "VALUES " 
					+ "(?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getLocation());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void update(Biblioteca obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE bibliotecas "
					+ "SET Name = ?, Location = ? "
					+ "WHERE Id = ?");
			
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getLocation());
			st.setInt(3, obj.getId());
			
			st.executeUpdate();
			
			
				
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}				
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM bibliotecas WHERE ID = ? ");					
						
			st.setInt(1, id);
					
			st.executeUpdate();
		
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}							
	}

	@Override
	public Biblioteca findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM bibliotecas "
					+"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Biblioteca biblioteca = new Biblioteca();
				biblioteca.setId(rs.getInt("Id"));
				biblioteca.setName(rs.getString("Name"));
				biblioteca.setLocation(rs.getString("Location"));
				
				return biblioteca;
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
	public List<Biblioteca> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM bibliotecas "
					+"ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Biblioteca> list = new ArrayList<>();
			
			while(rs.next()) {
				Biblioteca biblioteca = new Biblioteca();
				biblioteca.setId(rs.getInt("Id"));
				biblioteca.setName(rs.getString("Name"));
				biblioteca.setLocation(rs.getString("Location"));
				list.add(biblioteca);
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
