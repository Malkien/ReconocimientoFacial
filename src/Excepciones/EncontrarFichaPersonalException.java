package Excepciones;
/**
 * Excepcion de encontrarfichapersonal
 * @author malki
 *
 */
public class EncontrarFichaPersonalException extends SuperException{
	/**
	 * Constructor
	 */
	public EncontrarFichaPersonalException() {
		super("Error al encontrar la Ficha Personal");
	}
}
