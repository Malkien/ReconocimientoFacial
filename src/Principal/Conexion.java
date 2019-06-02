package Principal;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Excepciones.PreparedStatementException;
import Personas.Usuario;
import Visual.CrearFicha;
import Visual.MostrarFicha;
import Visual.Ventana;
/**
 * Las conexiones del programa
 * @author malki
 *
 */
public class Conexion {
	/**
	 * Funcion para crear un preparedStatement
	 * @param txt EL texto
	 * @return El preparedStatement
	 * @throws PreparedStatementException El error de preparedStatement
	 */
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
	/**
	 * Funcion para añadir imagen
	 * @param imagen la imagen(String)
	 * @param cara si es cara
	 * @param fichaPersonal la fichapersonal(dni)
	 */
	public static void realizarInsertImagen(String imagen, boolean cara, String fichaPersonal) {
		PreparedStatement insertImagen=null;
		try {
			insertImagen=creaPreparedStatement("INSERT INTO imagen(imagen,cara,fichapersonal) VALUES(?,?,?);");
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
	

