package com.ipartek.migracion.excepciones;

public class PersonaException extends Exception {

	public static final String CAMPOS_INSUFICIENTES = "La linea no cuenta con el numero de campos requerido";
	public static final String EMAIL_NO_VALIDO = "El email introducido no es valido";
	public static final String DNI_NO_VALIDO = "El DNI introducido no es valido";

	public PersonaException(String msg) {
		super(msg);
	}

}
