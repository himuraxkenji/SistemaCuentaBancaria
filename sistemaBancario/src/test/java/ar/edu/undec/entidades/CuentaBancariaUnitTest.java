package ar.edu.undec.entidades;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ar.edu.undec.excepciones.ClienteException;
import ar.edu.undec.excepciones.ClienteMenorEdadException;
import ar.edu.undec.interfaces.IDateTime;

class CuentaBancariaUnitTest {
	
	private Cliente unCliente;
	
	@Mock
	IDateTime fechaSistema;
	IDateTime fechaCreacionCuenta;
	
	@Before
	void inicial() throws ClienteException {
		LocalDate fechaDeNacimiento = LocalDate.of(1993, 7, 10);
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		Cliente unCliente = Cliente.factoryCliente(fechaSistema,"Sakika", "91479524", "Quality 4929" , "904-735-7945", fechaDeNacimiento);
	}
	
	
	
	@Test
	void crearCuentaBancaria_TodosLosAtributos_InstanciaCorrecta() {
		// Arrange 
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		
		// Act 
		CuentaBancaria unaCuenta = CuentaBancaria.factoryCuentaBancaria(unCliente, fechaCreacionCuenta);
		
		// Assert
		
		assertNotNull(unaCuenta);
		
	}
	

}
