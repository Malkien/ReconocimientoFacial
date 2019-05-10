package Principal;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Excepciones.PreparedStatementException;

public class Conexion {
	public static PreparedStatement creaPreparedStatement(String txt) throws PreparedStatementException {
		try {
			return DriverManager.getConnection(
					Constantes.conexionCadena,Constantes.usuarioConexion, Constantes.passwordConexion).prepareStatement(txt);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new PreparedStatementException();
		}
	}
	
	
}
