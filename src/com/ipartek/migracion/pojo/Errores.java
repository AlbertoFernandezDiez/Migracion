package com.ipartek.migracion.pojo;

/**
 * Clase en la que almacenamos el numero la linea que ha causado el error y el motivo del mismo
 * @author Alberto
 *
 */
public class Errores {
	
	private String linea;
	private String error;

	public Errores(String message, String linea) {
		this.linea = linea;
		this.error = message;
	}

	public String getLinea() {
		return linea;
	}

	public String getError() {
		return error;
	}

	@Override
	public String toString() {
		return "Errores [linea=" + linea + "\nerror=" + error + "]";
	}

	
}
