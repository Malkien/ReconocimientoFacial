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
	public static void sacarDatosFicha(String dni, Ventana ventana, Usuario usuario) {
		try {
			PreparedStatement preparedDatos=creaPreparedStatement("SELECT * FROM fichapersonal WHERE dni=?");
			preparedDatos.setString(1, dni);
			ResultSet resultadosDatos=preparedDatos.executeQuery();
			resultadosDatos.next();
			MostrarFicha mostrarFicha=new MostrarFicha(
					resultadosDatos.getString("nombre"),
					resultadosDatos.getString("apellidos"),
					resultadosDatos.getString("dni"),
					resultadosDatos.getInt("telefono"),
					resultadosDatos.getByte("nivelConfidencialidad"),
					resultadosDatos.getString("direccion")
					);
			if(resultadosDatos.getByte("nivelConfidencialidad")<=usuario.getNivelSeguridad()) {
				JFrame ventanaFicha= new JFrame();
				ventanaFicha.setSize(400, 400);
				ventanaFicha.setLocationRelativeTo(null);
				ventanaFicha.getContentPane().add(mostrarFicha);
				ventanaFicha.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(ventana, "El nivel de confidencialidad de la ficha \n es más alto que tu nivel de acceso", 
						"Confidencial", JOptionPane.ERROR_MESSAGE);
			}
			preparedDatos.close();
			resultadosDatos.close();
		} catch (PreparedStatementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
