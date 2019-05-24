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
	public static void realizarInsertImagen(String imagen, boolean cara, String fichaPersonal) {
		PreparedStatement insertImagen=null;
		try {
			insertImagen=creaPreparedStatement("INSERT INTO imagen(imagen,cara,FichaPersonal) VALUES(imagen=?,cara=?,FicahPersonal=?);");
			insertImagen.setString(1, imagen);
			insertImagen.setBoolean(2,cara);
			insertImagen.setString(3, fichaPersonal);
			insertImagen.executeUpdate();
		}catch(PreparedStatementException e) {
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				insertImagen.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
