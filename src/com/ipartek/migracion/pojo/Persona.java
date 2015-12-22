package com.ipartek.migracion.pojo;

import java.sql.Date;

import com.ipartek.migracion.excepciones.PersonaException;
import com.ipartek.migracion.validadores.UtilidadesFechas;

public class Persona {
	
	private int id;
	private String nombre, email, dni, observaciones,pass;
	private java.sql.Date fechaNacimiento;
	
	
	public Persona() {
		super();
		this.id = -1;
		this.nombre = "";
		this.email ="";
		this.dni ="";
		this.observaciones ="";
		this.pass ="";
		this.fechaNacimiento = new Date(System.currentTimeMillis());
	}

	public Persona (String line) throws PersonaException{
		String[] campos = line.split(",");
		
	
		
		if (campos.length != 7){
			throw new PersonaException(PersonaException.CAMPOS_INSUFICIENTES);
		}
		
		String nombre = campos[0] + " " + campos[1] + " "+ campos[2];
		
		int edad =  Integer.parseInt(campos[3]);
		Date fecha = UtilidadesFechas.calcularFecha(edad); 
		
		
		
	}
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public java.sql.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", email=" + email + ", dni=" + dni + ", observaciones="
				+ observaciones + ", pass=" + pass + ", fechaNacimiento=" + fechaNacimiento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((observaciones == null) ? 0 : observaciones.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Persona other = (Persona) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.toString().equals(other.fechaNacimiento.toString()))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (observaciones == null) {
			if (other.observaciones != null)
				return false;
		} else if (!observaciones.equals(other.observaciones))
			return false;
		return true;
	}

}
