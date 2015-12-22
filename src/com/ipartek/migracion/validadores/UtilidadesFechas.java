package com.ipartek.migracion.validadores;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilidadesFechas {

	private static final String FECHA_CORTA = "dd-MM-yyyy";
	private static final String FECHA_LARGA = "";
	private static final String FECHA_HORA = "HH:mm";
	public static final String[] DIAS_SEMANA = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
	public static final String[] MESES = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};


	/**
	 * Metodo que dada una marca de tiempo nos devuelve los años que han pasado
	 * 
	 * @param myTimeStamp
	 *            <code>Timestamp</code> la fecha a partir de la cual se tiene
	 *            que calcular
	 * @return <code>int</code> con los a�os que han pasado desde la fecha dada
	 *         (siempre va a ser >= 0)
	 * @throws NullPointerException
	 *             en caso de que el parametro recibido sea null
	 * @throws UtilidadesException
	 *             en caso de que la fecha recibida por parametro sea posterior
	 *             a la actual
	 */
	public static int getAnos(Timestamp fecha) throws NullPointerException, UtilidadesException {
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

	/**
	 * Metodo que dada una marca de tiempo nos devuelve la fecha en formato largo <code>dd de mmmmmmm de aaaa</code>
	 * 
	 * @param myTimeStamp <code>TimeStamp</code> la marca de tiempo que queremos formatear
	 * @return <code>String</code> la fecha en el formato largo <code>dd de mmmmmmm de aaaa</code>
	 * @throws NullPointerException
	 * 				En caso de que el parametro recibido sea null 
	 */
	public static String fechaLarga(Timestamp myTimeStamp) throws NullPointerException {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(myTimeStamp.getTime());
		
		String fecha = calendar.get(GregorianCalendar.DAY_OF_MONTH) + " de " + MESES[calendar.get(GregorianCalendar.MONTH)] + " de " + calendar.get(GregorianCalendar.YEAR);
		
		return fecha;
	}

	/**
	 * Metodo que dada una marca de tiempo nos devuelve la fecha en formato <code>FECHA_CORTA</code>
	 * 
	 * @param myTimeStamp <code>TimeStamp</code> la marca de tiempo que queremos formatear
	 * @return <code>String</code> La hora de la marca de tiempo con formato <code>FECHA_CORTA</code>
	 * @throws NullPointerException
	 * 				En caso de que el parametro recibido sea null 
	 */
	public static String fechaCorta(Timestamp myTimeStamp) throws NullPointerException {
		SimpleDateFormat sdf = new SimpleDateFormat(FECHA_CORTA);
		return sdf.format(new Date(myTimeStamp.getTime()));
	}

	/**
	 * Metodo que dada una marca de tiempo nos dice la hora de dich marca de tiempo
	 * 
	 * @param myTimeStamp <code>TimeStamp</code> la marca de tiempo de la que queremos extraer la hora
	 * @return <code>String</code> La hora de la marca de tiempo con formato <code>FECHA_HORA</code>
	 * @throws NullPointerException
	 * 				En caso de que el parametro recibido sea null 
	 */
	public static String fechaHora(Timestamp myTimeStamp) throws NullPointerException {
		SimpleDateFormat sdf = new SimpleDateFormat(FECHA_HORA);
		return sdf.format(new Date(myTimeStamp.getTime()));
	}

	/**
	 * Metodo que dada una marca de tiempo nos devuelve la el dia de la semana:<br>
	 * <ul>
	 *  <li>Domingo</li>
	 * 	<li>Lunes</li>
	 * 	<li>Martes</li>
	 * 	<li>....</li>
	 * 	<li>Sabado</li>
	 * </ul>
	 * @param myTimeStamp <code>TimeStamp</code> la marca de tiempo de la que queremos extraer la hora
	 * @return <code>String</code> El dia de la semana de la marca de tiempo con formato <code>FECHA_HORA</code>
	 * @throws NullPointerException
	 * 				En caso de que el parametro recibido sea null 
	 */
	public static Object getDiaSemana(Timestamp myTimeStamp) throws NullPointerException {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(myTimeStamp.getTime());
		
		return DIAS_SEMANA[calendar.get(GregorianCalendar.DAY_OF_WEEK)];
	}

	/**
	 * Metodo que dada una edad calcula la fecha de <it>nacimiento</it>
	 * @param edad <code>int</code> la edad 
	 * @return <code>java.sql.Date</code> la fecha de <it>nacimiento</it>
	 */
	public static java.sql.Date calcularFecha(int edad) {
		// TODO Auto-generated method stub
		return null;
	}

}
