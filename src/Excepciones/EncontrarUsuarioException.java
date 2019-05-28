package Excepciones;

public class EncontrarUsuarioException extends SuperException{

	public EncontrarUsuarioException() {
		super("Usuario no encontrado");
	}
}
