package com.ipartek.migracion.excepciones;

public class FileReaderException extends Exception {

	public static final String RUTA_ERRONEA = "Error, la ruta introducida no es valida";
	public static final String RUTA_NULL = "Error, debes introducir la ruta al fichero  a migrar";
	public static final String ERROR_DURANTE_EL_CIERRE = "Error, no se ha podido cerrar el fichero";

	public FileReaderException(String msg){
		super(msg);
	}
}
