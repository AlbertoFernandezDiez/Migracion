package com.ipartek.migracion.modelo;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para dar la habilidad de que sean Persistables los DAOs.<br>
 * Se encarga de que se implementen las operaciones mas basicas de CRUD(Create,
 * read, update, delete).
 * 
 * @author Curso
 * @param
 * 			<P>
 *            Clase generica la que luego sustituimos por nuestra clase
 *            concreta, por ejemplo <code>Persona</code>
 *
 */
public interface Persistable<P> {

	/**
	 * Listado de todos los objetos de la consulta, ordenado por id descendente,
	 * limitado a 500
	 * 
	 * @return <code> List&lt;P&gt;</code> si existen datos, en caso contrario
	 *         <code>List</code> inicializada
	 * @throws SQLException 
	 */
	public List<P> getAll() throws SQLException;

	/**
	 * Busca un objeto en la tabla por su identificador
	 * 
	 * @param id
	 *            <code>int</code> identificador del objeto
	 * @return Objeto generico, <code>null</code> si no existe
	 * @throws SQLException 
	 */
	public P getById(int id) throws SQLException;

	/**
	 * Elimina objetos de la tabla
	 * 
	 * @param id
	 *            <code>int</code> identificador del objeto
	 * @return <code>true</code> si elimina, <code>false</code> en caso
	 *         contrario
	 * @throws SQLException 
	 */
	public boolean delete(int id) throws SQLException;

	/**
	 * Modifica el objeto solicitado
	 * 
	 * @param persistable
	 *            <code>P</code> Objeto con los valores a modificar
	 * @return <code>true</code> si modifica, <code>false</code> en caso
	 *         contrario
	 * @throws SQLException 
	 */
	public boolean update(P persistable) throws SQLException;

	/**
	 * Inserta un nuevo objeto
	 * 
	 * @param persistable
	 *            <code>P</code> objeto a insertar
	 * 
	 * @return <code>int</code> identificador del objeto insertado, -1 en caso
	 *         contrario
	 * @throws SQLException 
	 */
	public int insert(P persistable) throws SQLException;

}
