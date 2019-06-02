package Excepciones;
/**
 * Excepcion encontrarusuario
 * @author malki
 *
 */
public class EncontrarUsuarioException extends SuperException{
	/**
	 * Constructor
	 */
	public EncontrarUsuarioException() {
		super("Usuario no encontrado");
	}
}
