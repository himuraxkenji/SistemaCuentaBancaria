package ar.edu.undec.entidades;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import ar.edu.undec.excepciones.ClienteException;
import ar.edu.undec.excepciones.ClienteIncompletoException;
import ar.edu.undec.excepciones.ClienteMenorEdadException;
import ar.edu.undec.implementations.DateTimeImplementation;
import ar.edu.undec.interfaces.IDateTime;
import ar.edu.undec.sistemaBancario.MockitoExtension;



class ClienteUnitTest {
	
	private IDateTime fechaSistema;

    @Before
    public void antesDeIniciar() {
    	
    	final LocalDate fecha = Mockito.mock(LocalDate.class);
    	
    	Mockito.when(fecha.now()).thenReturn(LocalDate.of(2019, 06, 19));
    	
    	final IDateTime fechaSistema = Mockito.mock(IDateTime.class);
        
    	Mockito.when(fechaSistema.getDate()).thenReturn(fecha);
    	
    	
//    	final DateTimeImplementation fechaS = Mockito.mock(DateTimeImplementation.class);
//    	
//    	Mockito.when(fechaS.getDate()).thenReturn(LocalDate.of(2019, 06, 19));
//    	
//    	final IDateTime fecha = Mockito.mock(IDateTime.class);
//    	
//    	//final LocalDate fechaSis = Mockito.mock(DateTimeImplementation.class);
//    	
//    	Mockito.when(fecha.getDate()).thenReturn(fechaS.getDate());
//        
//    	//Mockito.when(fechaSis).thenReturn(fecha.getDate());
//    	
    	this.fechaSistema = fechaSistema ;
    }

	
	@Test
	public void factoryCliente_TodoslosAtributosCorrectos_InstanciaCorrecta() throws ClienteException{
		LocalDate fechaDeNacimiento = LocalDate.of(1993, 7, 10);
		Cliente unCliente = Cliente.factoryCliente(fechaSistema,"Sakika", "91479524", "Quality 4929" , "904-735-7945", fechaDeNacimiento);
		assertNotNull(unCliente);
	}
	
	@Test
	public void factoryCliente_FaltanAtributos_InstanciaIncompleta() {
		LocalDate fechaDeNacimiento = null;
		assertThrows(ClienteIncompletoException.class, () -> Cliente.factoryCliente(fechaSistema,"", "", "" , "", fechaDeNacimiento));
	}
	
	@Test
	public void factoryCliente_EdadMenorA18_InstanciaIncompleta() {
		
		LocalDate fechaDeNacimiento = LocalDate.of(2013, 7, 10);
				
		assertThrows(ClienteMenorEdadException.class, () -> Cliente.factoryCliente(fechaSistema,"Ikuma Shibutani", "91462524", "Thead 1980" , "660-879-6425", fechaDeNacimiento));
	}
		
}
