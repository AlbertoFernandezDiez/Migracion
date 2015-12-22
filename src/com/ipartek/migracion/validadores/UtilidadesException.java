package com.ipartek.migracion.validadores;

public class UtilidadesException extends Exception {
	
	public static final String MSG_FECHA_FUTURA = "La fecha no es valida, no puede ser posterior a la fecha actual";
	public static final String MSG_FORMATO_DNI_NO_VALIDO = "Un dni debe tener 8 numeros y una letra";
	
	public UtilidadesException (String msg){
		super(msg);
	}
}
