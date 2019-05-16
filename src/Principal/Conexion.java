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
			//Si da error comprueba que se ha creado una clase Constantes, ya que el repositorio no las guarda, hay que crearla en el ordenador local
		}catch(SQLException e) {
			e.printStackTrace();
			throw new PreparedStatementException();
		}
	}
	
	
}
