package Excepciones;

import java.sql.SQLException;

public class PreparedStatementException extends SQLException{
	public PreparedStatementException() {
		super("Error al abrir el PreparedStatement");
	}

}
