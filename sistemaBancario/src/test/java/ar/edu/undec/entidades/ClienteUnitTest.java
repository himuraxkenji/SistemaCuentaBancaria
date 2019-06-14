package ar.edu.undec.entidades;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.exceptions.base.MockitoException;

import ar.edu.undec.excepciones.ClienteException;
import ar.edu.undec.excepciones.ClienteIncompletoException;
import ar.edu.undec.excepciones.ClienteMenorEdadException;
import ar.edu.undec.sistemaBancario.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class ClienteUnitTest {
	
	
	@Test
	public void factoryCliente_TodoslosAtributosCorrectos_InstanciaCorrecta() throws ClienteException{
		LocalDate fechaDeNacimiento = LocalDate.of(1993, 7, 10);
		Cliente unCliente = Cliente.factoryCliente("Sakika", "91479524", "Quality 4929" , "904-735-7945", fechaDeNacimiento);
		assertNotNull(unCliente);
	}
	
	@Test
	public void factoryCliente_FaltanAtributos_InstanciaIncompleta() {
		LocalDate fechaDeNacimiento = null;
		assertThrows(ClienteIncompletoException.class, () -> Cliente.factoryCliente("", "", "" , "", fechaDeNacimiento));
	}
	
	@Test
	public void factoryCliente_EdadMenorA18_InstanciaIncompleta() {
		int treceAnios = LocalDate.now().getYear() - 14;
		LocalDate fechaDeNacimiento = LocalDate.of(treceAnios, 7, 10);
		
		
		assertThrows(ClienteMenorEdadException.class, () -> Cliente.factoryCliente("Ikuma Shibutani", "91462524", "Thead 1980" , "660-879-6425", fechaDeNacimiento));
	}
		
}
