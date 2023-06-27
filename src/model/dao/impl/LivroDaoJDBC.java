package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.LivroDao;
import model.entities.Genrer;
import model.entities.Livro;

public class LivroDaoJDBC implements LivroDao{
	
	private Connection conn;
	
	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Livro obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO livros "
					+ "(Title, Autor, Publisher, Release_Date, Genrer, BibliotecaId) "
					+ "VALUES " 
					+ "(?, ?, ? , ?, ?, ?)" , Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getTitle());
			st.setString(2, obj.getAutor());
			st.setString(3, obj.getPublisher());
			st.setObject(4, obj.getReleaseDate());
			st.setString(5, obj.getGenrer().getName_genrer());
			st.setInt(6, obj.getBibliotecaId());
			
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
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(null);
		}		
	}

	@Override
	public void update(Livro obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE livros "
					+ "SET Title = ?, Autor = ?, Publisher = ?, Release_Date = ?, Genrer = ?, BibliotecaId = ? "
					+ "WHERE Id = ?");
			
			
			st.setString(1, obj.getTitle());
			st.setString(2, obj.getAutor());
			st.setString(3, obj.getPublisher());
			st.setObject(4, obj.getReleaseDate());
			st.setString(5, obj.getGenrer().getName_genrer());
			st.setInt(6, obj.getBibliotecaId());
			st.setInt(7, obj.getId());
						
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
			st = conn.prepareStatement("DELETE FROM livros WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Livro findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT livros.* ,genres.* "
					+ "FROM livros INNER JOIN genres "
					+ "ON livros.Genrer = genres.Name_Genrer "
					+ "WHERE livros.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Genrer genrer = instantiateGenrer(rs);
				Livro obj = instantiateLivro(rs, genrer);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Livro instantiateLivro(ResultSet rs, Genrer genrer) throws SQLException {
		Livro obj = new Livro();
		obj.setId(rs.getInt("Id"));
		obj.setTitle(rs.getString("Title"));
		obj.setAutor(rs.getString("Autor"));
		obj.setPublisher(rs.getString("Publisher"));
		obj.setReleaseDate(rs.getDate("Release_Date").toLocalDate());
		obj.setGenrer(genrer);
		obj.setBibliotecaId(rs.getInt("BibliotecaId"));		
		return obj;
	}
	
	private Genrer instantiateGenrer(ResultSet rs) throws SQLException {
		Genrer genrer = new Genrer();
		genrer.setName_genrer(rs.getString("Name_Genrer"));		
		return genrer;
	}
	
	@Override
	public List<Livro> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT livros.* ,genres.* "
					+ "FROM livros INNER JOIN genres "
					+ "ON livros.Genrer = genres.Name_Genrer "
					+ "ORDER BY livros.Title");
			
			rs = st.executeQuery();
			
			List<Livro> list = new ArrayList<>();
			Map<String, Genrer> map = new HashMap<>();
			
			while (rs.next()) {
				
				Genrer genrer = map.get(rs.getString("Genrer"));
				
				if (genrer == null) {
						genrer = instantiateGenrer(rs);
						map.put(rs.getString("Genrer"), genrer);
				}
				
				Livro obj = instantiateLivro(rs, genrer);
				list.add(obj);
			}
			return list;			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Livro> findByGenrer(Genrer genrer) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT livros.* ,genres.* "
					+ "FROM livros INNER JOIN genres "
					+ "ON livros.Genrer = genres.Name_Genrer "
					+ "WHERE livros.Genrer = ? "
					+ "ORDER BY livros.Title");
			
			st.setString(1, genrer.getName_genrer());
			
			rs = st.executeQuery();
			
			List<Livro> list = new ArrayList<>();
			Map<String, Genrer> map = new HashMap<>();
			
			while (rs.next()) {
				
				Genrer genr = map.get(rs.getString("Genrer"));
				
				if (genr == null) {
						genr = instantiateGenrer(rs);
						map.put(rs.getString("Genrer"), genr);
				}
				
				Livro obj = instantiateLivro(rs, genr);
				list.add(obj);
			}
			return list;			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
