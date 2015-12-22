package com.ipartek.migracion.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.ipartek.migracion.excepciones.FileReaderException;
import com.ipartek.migracion.excepciones.FileWriterException;

/**
 * Clase que permite leer ficheros linea a linea dada una ruta
 * 
 * @author Curso
 *
 */
public class MyFileWriter {

	private File myFile;
	private FileWriter fw;
	private PrintWriter pw;

	/**
	 * Constructor de la clase
	 * 
	 * @param path
	 *            <code>String</code> ruta del fichero a leer
	 * @throws IOException
	 * @throws FileWriterException 
	 */
	public MyFileWriter(String path, String name) throws IOException, FileWriterException {

		abrirFichero(path, name);

	}

	/**
	 * Metodo que nos permite abrir un fichero para iniciar su escritura desde el
	 * comienzo del mismo
	 * 
	 * @param path
	 *            <code>String</code> ruta del fichero a leer
	 * @throws FileReaderException
	 */
	private void abrirFichero(String path, String name) throws  FileWriterException  {
		File aux = new File(path);
		myFile = new File(path);

		if (!aux.exists()) {
			throw new FileWriterException(FileWriterException.RUTA_ERRONEA);
		}

		if (aux.isDirectory()) {
			myFile = new File(aux, name);
		} else {
			myFile = new File(aux.getParentFile(), name);
		}

		try {
			fw = new FileWriter(myFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fw == null){
			throw new FileWriterException(FileWriterException.IMPOSIBLE_ESCRIBIR);
		}
		
		pw = new PrintWriter(fw, true);
	}

	/**
	 * Metodo que cierra el PrintWriter, y fileWriter. Usar
	 * <strong>solo</strong> para leer parcialmente un fichero.
	 * 
	 * @return <code>boolean</code>
	 *         <ul>
	 *         <li><code>true</code> si se ha cerrado correctamente</li>
	 *         <li><code>false</code> si no ha sido posible cerrar todo</li>
	 */
	public boolean cerrarFichero() {
		boolean resultado = true;

		if (pw != null) {
			pw.close();
		}

		try {

			if (fw != null) {
				fw.close();
			}
		} catch (IOException e) {

			resultado = false;
		}

		return resultado;

	}

	/**
	 * Metodo que escribe una linea en el fichero
	 * @param linea <code>String</code> la linea a escribir
	 */
	public void escribirLinea(String linea) {

		pw.println(linea);

	}
}
