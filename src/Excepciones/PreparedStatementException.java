package Excepciones;

import java.sql.SQLException;
/**
 * Excepcion de preparedStatement
 * @author malki
 *
 */
public class PreparedStatementException extends SQLException{
	/**
	 * Constructor
	 */
	public PreparedStatementException() {
		super("Error al abrir el PreparedStatement");
	}

}
