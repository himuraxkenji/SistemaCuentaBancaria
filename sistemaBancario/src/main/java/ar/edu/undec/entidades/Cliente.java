package ar.edu.undec.entidades;

import java.time.LocalDate;
import java.time.Period;

import ar.edu.undec.excepciones.ClienteException;
import ar.edu.undec.excepciones.ClienteIncompletoException;
import ar.edu.undec.excepciones.ClienteMenorEdadException;
import ar.edu.undec.interfaces.IDateTime;

public class Cliente {
	
	private String nombreYApellido;
	private String dni;
	private String domicilio;
	private String telefono;
	private LocalDate fechaDeNacimiento;
	private final IDateTime fechaSistema;
	
	private Cliente(final IDateTime fechaSistema , String nombreYApellido, String dni, String domicilio, String telefono,LocalDate fechaDeNacimiento) {
		this.fechaSistema = fechaSistema;
		this.nombreYApellido = nombreYApellido;
		this.dni = dni;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	public static Cliente factoryCliente(final IDateTime fechaSistema, String nombreYApellido, String dni,String domicilio, String telefono,LocalDate fechaDeNacimiento) throws ClienteException {
		int edad  = 0;
		
		if(nombreYApellido == null || dni == null || domicilio == null || telefono == null || fechaDeNacimiento == null)
			throw new ClienteIncompletoException();
		
		edad = Period.between(fechaDeNacimiento, fechaSistema.getDate()).getYears();

		if(edad < 18)
			throw new ClienteMenorEdadException();
		
		return  new Cliente(fechaSistema, nombreYApellido, dni, domicilio, telefono, fechaDeNacimiento);
		
	}

	public String getNombreYApellido() {
		return nombreYApellido;
	}

	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	
}
