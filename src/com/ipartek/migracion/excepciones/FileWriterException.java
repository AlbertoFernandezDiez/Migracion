package com.ipartek.migracion.excepciones;

public class FileWriterException extends Exception {


	public static final String RUTA_ERRONEA = "La ruta introducida no existe";
	public static final String IMPOSIBLE_ESCRIBIR = "No ha sido posible escribir en el fichero";

	public FileWriterException(String msg) {
		super(msg);
	}

}
