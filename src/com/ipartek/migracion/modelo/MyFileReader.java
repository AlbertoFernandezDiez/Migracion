package com.ipartek.migracion.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.ipartek.migracion.excepciones.FileReaderException;

/**
 * Clase que permite leer ficheros linea a linea dada una ruta
 * 
 * @author Curso
 *
 */
public class MyFileReader {

	private File myFile;
	private FileReader fr;
	private BufferedReader br;

	/**
	 * Constructor de la clase
	 * 
	 * @param path
	 *            <code>String</code> ruta del fichero a leer
	 * @throws FileNotFoundException
	 */
	public MyFileReader(String path) throws FileReaderException, FileNotFoundException {

		abrirFichero(path);

	}

	/**
	 * Metodo que nos permite abrir un fichero para iniciar su lectura desde el comienzo del mismo
	 * @param path  <code>String</code> ruta del fichero a leer
	 * @throws FileReaderException
	 * @throws FileNotFoundException
	 */
	public void abrirFichero(String path) throws FileReaderException, FileNotFoundException {
		if (path == null) {
			throw new FileReaderException(FileReaderException.RUTA_NULL);
		}

		myFile = new File(path);

		if (!myFile.exists()) {
			throw new FileReaderException(FileReaderException.RUTA_ERRONEA);
		}

		fr = new FileReader(myFile);
		br = new BufferedReader(fr);
	}

	/**
	 * Metodo que cierra el buffer, y fileReader.
	 * Usar <strong>solo</strong> para leer parcialmente un fichero.
	 * 
	 * @return <code>boolean</code>
	 *         <ul>
	 *         <li><code>true</code> si se ha cerrado correctamente</li>
	 *         <li><code>false</code> si no ha sido posible cerrar todo</li>
	 */
	public boolean cerrarFichero() {
		boolean resultado = true;

		try {
			if (br != null) {
				br.close();
			}
			if (fr != null) {
				fr.close();
			}
		} catch (IOException e) {
			resultado = false;
		}

		return resultado;

	}

	/**
	 * Metodo que nos devuelve el contenido de una linea del fichero en orden secuencial
	 * @return <code>String</code> el contenido de la linea, si el fichero se ha terminado<br> devuelve <code>null</code> y cierra el fichero automaticamente
	 */
	public String leerLinea(){
		String linea = null;
		try {
			linea = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (linea == null){
			cerrarFichero();
		}
		
		return linea;
		
	}
}
