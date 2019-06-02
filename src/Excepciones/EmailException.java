package Excepciones;
/**
 * Excepcion de email
 * @author malki
 *
 */
public class EmailException extends SuperException{
	/**
	 * Constructor
	 */
	public EmailException() {
		super("Error al introducir email");
	}
}
