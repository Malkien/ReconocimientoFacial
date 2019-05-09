package Principal;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	public static PreparedStatement   creaPreparedStatement(String txt) throws SQLException {
		return DriverManager.getConnection(
                Constantes.conexionCadena,Constantes.usuarioConexion, Constantes.passwordConexion).prepareStatement(txt);
	}
	
	
}
