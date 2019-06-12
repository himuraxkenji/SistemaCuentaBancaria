package ar.edu.undec.excepciones;

public class ClienteMenorEdadException extends ClienteException {
	public ClienteMenorEdadException() {
		super("El cliente es menor de edad");
	}

}
