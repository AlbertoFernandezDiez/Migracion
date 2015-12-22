package com.ipartek.migracion.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.migracion.excepciones.FileReaderException;
import com.ipartek.migracion.excepciones.FileWriterException;
import com.ipartek.migracion.excepciones.PersonaException;
import com.ipartek.migracion.modelo.MyFileReader;
import com.ipartek.migracion.modelo.MyFileWriter;
import com.ipartek.migracion.modelo.PersonaDAO;
import com.ipartek.migracion.pojo.Errores;
import com.ipartek.migracion.pojo.Persona;
import com.ipartek.migracion.validadores.UtilidadesException;

public class Main {

	private static final String NOMBRE_INFORME = "Informe.html";
	private static final String NOMBRE_ERRORES = "Errores.html";
	private static final String CABECERA_ERRORES = "<!DOCTYPE html>"
			+ "<html lang='es'><head>  <title>Informe Migracion</title> "
			+ " <meta charset='utf-8'>  <meta name='viewport' content='width=device-width, initial-scale=1'>"
			+ "  <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'> "
			+ " <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script> "
			+ " <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>"
			+ "</head><body><div class='container'>  <h2>Errores</h2>  "
			+ "<p>Listado de los errores ocurridos durante la migracion de BD</p>"
			+ " <table class='table table-hover'>" + "<thead><tr><th>Nº Error</th>"
			+ "<th>Linea</th><th>Motivo</th> </tr></thead><tbody>";
	private static final String FIN_ERRORES = "    </tbody>  </table></div></body></html>";

	private static final String CABECERA_INFORME = "<!DOCTYPE html><html lang='en'><head>"
			+ "<title>Bootstrap Example</title>  <meta charset='utf-8'> "
			+ "<meta name='viewport' content='width=device-width, initial-scale=1'> "
			+ "<link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'> "
			+ " <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script> "
			+ " <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'>"
			+ "</script></head><body><div class='container'><h2>Informe Migracion</h2><ul class='list-group'>";
	private static final String FIN_INFORME = " </ul></div></body></html>";

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		System.out.println("\n\tIniciando proceso de migracion\n");

		String path = "Data/personas.txt";
		Persona p = null;
		if (args.length > 0) {
			path = args[0];
		}

		MyFileReader mfr = null;
		try {
			mfr = new MyFileReader(path);
		} catch (FileReaderException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

		int correctos = 0;
		int erroneos = 0;

		ArrayList<Errores> errores = new ArrayList<Errores>();
		String linea = null;
		PersonaDAO pDao = new PersonaDAO();
		while ((linea = mfr.leerLinea()) != null) {
			try {
				p = new Persona(linea);
				pDao.insert(p);
				correctos++;
			}catch(Exception e){
				errores.add(new Errores(e.getMessage(), linea));
				erroneos++;
			}
		}

		File dirInformes = new File(path + " - Informes");

		if (!dirInformes.exists()) {
			dirInformes.mkdirs();
			informeMigracion(dirInformes.getAbsolutePath(), correctos, erroneos);

			informeErrores(dirInformes.getAbsolutePath(), errores);
		} else {

			informeMigracion(path, correctos, erroneos);

			informeErrores(path, errores);
		}

		System.out.println("\n\tTerminado proceso de migracion en:\t " + (System.currentTimeMillis() - time) + "ms");

	}

	private static void informeErrores(String path, ArrayList<Errores> errores) {
		try {
			MyFileWriter mfw = new MyFileWriter(path, NOMBRE_ERRORES);

			mfw.escribirLinea(CABECERA_ERRORES);
			Errores aux = null;
			for (int i = 0; i < errores.size(); i++) {
				aux = errores.get(i);

				mfw.escribirLinea("<tr>");
				mfw.escribirLinea("<td>" + i + 1 + "</td>");
				mfw.escribirLinea("<td>" + aux.getLinea() + "</td>");
				mfw.escribirLinea("<td>" + aux.getError() + "</td>");
				mfw.escribirLinea("</tr>");

			}

			mfw.escribirLinea(FIN_ERRORES);

			mfw.cerrarFichero();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileWriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void informeMigracion(String path, int correcto, int error) {
		try {
			MyFileWriter mfw = new MyFileWriter(path, NOMBRE_INFORME);

			mfw.escribirLinea(CABECERA_INFORME);

			mfw.escribirLinea("<li  class='list-group-item'>" + "Correctos:\t" + correcto + "</li>");
			mfw.escribirLinea("<li  class='list-group-item'>" + "<a href=" + NOMBRE_ERRORES + ">Erroneos:\t" + error
					+ "</a></li>");

			mfw.escribirLinea(FIN_INFORME);

			mfw.cerrarFichero();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileWriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
