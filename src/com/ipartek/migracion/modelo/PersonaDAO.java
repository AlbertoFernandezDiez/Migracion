package com.ipartek.migracion.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.migracion.pojo.Persona;

public class PersonaDAO implements Persistable<Persona> {

	@Override
	public List<Persona> getAll() throws SQLException {

		List<Persona> lista = new ArrayList<Persona>();

		DbConnection conex = new DbConnection();

		String sql = "select * from `persona` order by `id` desc limit 500;";

		PreparedStatement ps = conex.getConnection().prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		Persona p = null;
		while (rs.next()) {
			lista.add(mapeo(rs));
		}

		// Cerramos los recursos en orden inverso
		rs.close();
		ps.close();
		conex.desconectar();

		return lista;
	}

	@Override
	public Persona getById(int id) throws SQLException {
		DbConnection conex = new DbConnection();

		String sql = "select * from `persona` where `id` = ?;";
		

		PreparedStatement ps = conex.getConnection().prepareStatement(sql);

		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();

		Persona p = null;
		if (rs.next()) {
			p = mapeo(rs);
		}
		
		return p;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		int resultado = -1;
		DbConnection conex = new DbConnection();

		String sql = "DELETE FROM `iparsex`.`persona` WHERE  `id`=?;";

		PreparedStatement ps = conex.getConnection().prepareStatement(sql);

		ps.setInt(1, id);
		
		ps.setInt(1, id);
		resultado = ps.executeUpdate();
		
		ps.close();
		conex.desconectar();
		
		return resultado == 1;
	}

	@Override
	public boolean update(Persona persistable) throws SQLException  {
		int resultado = -1;
		
		if (persistable != null){
		
		DbConnection conex = new DbConnection();

		String sql = "UPDATE `iparsex`.`persona` SET `nombre`=?, `dni`=?, "
				+ "`fecha_nacimiento`=?, `observaciones`=?, `pass`=?, `email`=?"
				+ " WHERE  `id`=?;";

		PreparedStatement ps = conex.getConnection().prepareStatement(sql);

		ps.setString(1, persistable.getNombre());
		ps.setString(2, persistable.getDni());
		ps.setDate(3, persistable.getFechaNacimiento());
		ps.setString(4, persistable.getObservaciones());
		ps.setString(5, persistable.getPass());
		ps.setString(6, persistable.getEmail());
		ps.setInt(7, persistable.getId());

		resultado = ps.executeUpdate();
		}
		
		return resultado == 1;
	}

	@Override
	public int insert(Persona persistable) throws SQLException {
		int resultado = -1;
		if (persistable !=  null){
		DbConnection conex = new DbConnection();

		String sql = "INSERT INTO `iparsex`.`persona` (`nombre`, `dni`, `fecha_nacimiento`,"
				+ " `observaciones`, `pass`, `email`) VALUES (?, ?, ?, ?, ?, ?);";

		PreparedStatement ps = conex.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, persistable.getNombre());
		ps.setString(2, persistable.getDni());
		ps.setDate(3, persistable.getFechaNacimiento());
		ps.setString(4, persistable.getObservaciones());
		ps.setString(5, persistable.getPass());
		ps.setString(6, persistable.getEmail());

		resultado = ps.executeUpdate();
		if (resultado == 1) {
		
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				resultado = rs.getInt(1);
				persistable.setId(resultado);
			}else{
				throw new SQLException("Creating user failed, no ID obtained");
			}

			// Cerramos los recursos en orden inverso
			rs.close();
		}else{
			resultado = -1;
		}

		ps.close();
		conex.desconectar();
		}
		
		return resultado;
	}

	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Persona mapeo(ResultSet rs) throws SQLException {
		Persona p = new Persona();
		p.setId(rs.getInt("id"));
		p.setNombre(rs.getString("nombre"));
		p.setDni(rs.getString("dni"));
		p.setEmail(rs.getString("email"));
		p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
		p.setObservaciones(rs.getString("observaciones"));
		p.setPass(rs.getString("pass"));

		return p;
	}
}
