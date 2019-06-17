package ar.edu.undec.entidades;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import ar.edu.undec.excepciones.ClienteException;
import ar.edu.undec.excepciones.ClienteIncompletoException;
import ar.edu.undec.excepciones.ClienteMenorEdadException;
import ar.edu.undec.implementations.DateTimeImplementation;
import ar.edu.undec.interfaces.IDateTime;
import ar.edu.undec.sistemaBancario.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ClienteUnitTest {
	
	@Mock
	IDateTime fechaSistema;
	
	@Test
	public void factoryCliente_TodoslosAtributosCorrectos_InstanciaCorrecta() throws ClienteException{
		LocalDate fechaDeNacimiento = LocalDate.of(1993, 7, 10);
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		Cliente unCliente = Cliente.factoryCliente(fechaSistema,"Sakika", "91479524", "Quality 4929" , "904-735-7945", fechaDeNacimiento);
		assertNotNull(unCliente);
	}
	
	@Test
	public void factoryCliente_FaltanAtributos_InstanciaIncompleta() {
		LocalDate fechaDeNacimiento = null;
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
		
		assertThrows(ClienteIncompletoException.class, () -> Cliente.factoryCliente(fechaSistema,"", "", "" , "", fechaDeNacimiento));
	}
	
	@Test
	public void factoryCliente_EdadMenorA18_InstanciaIncompleta() {
		
		LocalDate fechaDeNacimiento = LocalDate.of(2013, 7, 10);
		Mockito.when(fechaSistema.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
				
		assertThrows(ClienteMenorEdadException.class, () -> Cliente.factoryCliente(fechaSistema,"Ikuma Shibutani", "91462524", "Thead 1980" , "660-879-6425", fechaDeNacimiento));
	}
		
}
