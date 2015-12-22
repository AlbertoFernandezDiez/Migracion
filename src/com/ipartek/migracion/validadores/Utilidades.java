package com.ipartek.migracion.validadores;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

	private Pattern dniPattern, emailPattern;
	private Matcher dniMatcher, emailMatcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String DNI_PATTERN = "[0-9]{8}[A-Z]";

	/**
	 * Metodo que se encarga de comprobar la validez de un email, cumple el patron <code>EMAIL_PATTERN</code>
	 * 
	 * @param email
	 *            <code>String</code> La direccion de email a validar
	 * @return <code>boolean</code><br>
	 *         <ul>
	 *         <li><code>true</code> si el correo es valido</li>
	 *         <li><code>false</code> si el correo no es valido (formato
	 *         incorrecto)</li>
	 *         </ul>
	 */
	public static boolean validarEmail(String email) throws NullPointerException {
		Matcher emailMatcher = null;
		Pattern emailPattern = null;
		if (email == null) {
			throw new NullPointerException("El email no puede ser null");
		} else {

			emailPattern = Pattern.compile(EMAIL_PATTERN);

			emailMatcher = emailPattern.matcher(email);
		}
		return emailMatcher.matches();
	}

	/**
	 * Metodo que se encarga de comprobar la validez de un DNI de España
	 * 
	 * @param dni
	 *            <code>String</code> La direccion de DNI a validar
	 * @return <code>boolean</code><br>
	 *         <ul>
	 *         <li><code>true</code> si el DNI es valido</li>
	 *         <li><code>false</code> si el DNI no es valido</li>
	 *         </ul>
	 * @throws UtilidadesException
	 *             si el dni no cumple el formata:<br>
	 *             <ul>
	 *             <li>8 numeros</li>
	 *             <li>1 letra</li>
	 *             </ul>
	 * @throws NullPointerException
	 *             en caso de que el parametro recibido sea null
	 */
	public static boolean validarDNI(String dni) throws NullPointerException, UtilidadesException {
		dni = dni.trim();
		dni = dni.toUpperCase();

		if (dni == null) {
			throw new NullPointerException();
		}
		
		Pattern dniPattern = Pattern.compile(DNI_PATTERN);
		Matcher dniMatcher = dniPattern.matcher(dni);

		if (!dniMatcher.matches()) {
			throw new UtilidadesException(UtilidadesException.MSG_FORMATO_DNI_NO_VALIDO);
		}
		int numero = Integer.parseInt(dni.substring(0, 8));

		char letra = calculaLetra(numero);

		return dni.equals(String.valueOf(numero) + letra);
	}

	/**
	 * Metodo que calcula la letra asociada a el numero de DNI que recibe por
	 * parametor
	 * 
	 * @param dni
	 *            <code>char</code> el numero de deni del que queremos calcular
	 *            la letra
	 * @return <code>char</code> la letra asociada al numero recibido
	 */
	private static char calculaLetra(int dni) {
		String juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKET";
		int modulo = dni % 23;
		char letra = juegoCaracteres.charAt(modulo);
		return letra;
	}

	/**
	 * Metodo que calcula los a�os pasados a partir de una fecha
	 * 
	 * @param fecha
	 *            <code>Date</code> La fecha a partir de la cual se calculan los
	 *            a�os
	 * @return <code>int</code> con los a�os que han pasado desde la fecha dada
	 *         (simpre va a ser >= 0)
	 * 
	 * @throws NullPointerException
	 *             en caso de que el parametro recibido sea null
	 * @throws UtilidadesException
	 *             en caso de que la fecha recibida por parametro sea posterior
	 *             a la actual
	 */
	public static int calcularEdad(Date fecha) throws NullPointerException, UtilidadesException {
		if (fecha == null) {
			throw new NullPointerException();
		}
		if (System.currentTimeMillis() < fecha.getTime()) {
			throw new UtilidadesException(UtilidadesException.MSG_FECHA_FUTURA);
		}

		int diferencia = -1;

		Calendar calendarA = Calendar.getInstance(), calendarP = Calendar.getInstance();

		calendarA.setTimeInMillis(System.currentTimeMillis());
		calendarP.setTimeInMillis(fecha.getTime());

		diferencia = calendarA.get(Calendar.YEAR) - calendarP.get(Calendar.YEAR);

		return diferencia;
	}

}
