package com.ipartek.migracion.excepciones;

public class FileReaderException extends Exception {

	public static final String RUTA_ERRONEA = null;
	public static final String RUTA_NULL = null;
	public static final String ERROR_DURANTE_EL_CIERRE = null;

	public FileReaderException(String msg){
		super(msg);
	}
}
