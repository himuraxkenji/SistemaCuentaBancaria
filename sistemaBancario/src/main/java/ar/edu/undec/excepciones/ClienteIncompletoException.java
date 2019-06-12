package ar.edu.undec.excepciones;

public class ClienteIncompletoException extends ClienteException {
	public ClienteIncompletoException() {
		super("Faltan datos del cliente");
	}

}
