package Excepciones;

public class EncontrarUsuarioException extends Exception{

	public EncontrarUsuarioException() {
		super("Usuario no encontrado");
	}
}
