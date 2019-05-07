package Componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Excepciones.EncontrarFichaPersonalException;
import Excepciones.EncontrarUsuarioException;
import Excepciones.PuestoException;
import Personas.FichaPersonal;
import Personas.Usuario;
import Personas.Usuario.Seguridad;

public class BotonLoguear extends JButton{
	public BotonLoguear(String txt, String usuario, String password) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Connection conexion=null;
				try {
	            	conexion=DriverManager.getConnection(
	                        "jdbc:mysql://localhost:3306/reconocimientobbdd","root", "");
					Usuario usuarioLogueado=loguear(conexion,usuario,password);
					PreparedStatement comprobarFicha=
	                        conexion.prepareStatement("select * from FichaPersonal "
	                                + "where dni=? ");
	            	comprobarFicha.setString(1,usuarioLogueado.getIdentidad().getDni());
	            	
	            	ResultSet encontrarFicha=comprobarFicha.executeQuery();
	            	encontrarFicha.next();
	            	
	            	FichaPersonal fichaUsuario=new FichaPersonal(encontrarFicha.getString("nombre"),
	            			encontrarFicha.getString("apellidos"),
	            			encontrarFicha.getString("dni"),
	            			encontrarFicha.getInt("telefono"),
	            			encontrarFicha.getString("email"),
	            			(ArrayList<BufferedImage>) encontrarFicha.getArray("imagenes"),
	            			encontrarFicha.getByte("nivelConfidencialidad"),
	            			encontrarFicha.getString("direccion"));
	            	System.out.println("OK");
				}catch(SQLException ex) {
	            	ex.printStackTrace();
	            	System.err.println("SQL ERROR");
	            } catch (PuestoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EncontrarUsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						conexion.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				switch (arg0.getKeyChar()) {
				case KeyEvent.VK_ENTER:
					loguear(usuario,password);
					break;
				}
			}
		});
		setBackground(new Color(135, 206, 235));
        setBorderPainted(true);
		setForeground(new Color(0, 0, 0));
		setText(txt);
		setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
	
		public Usuario loguear(Connection conexion,String usuario,String password) throws SQLException, PuestoException, EncontrarUsuarioException {
			Usuario userLogueado=null;            	
            PreparedStatement comprobar=
                    conexion.prepareStatement("select * from USUARIO "
                            + "where nombreUsuario=? and password=? ");
            comprobar.setString(1, usuario);
            comprobar.setString(2, password);
            ResultSet encontrado=comprobar.executeQuery();
            Seguridad puesto=null;
            if(encontrado.next()) {
            	switch(encontrado.getString("puesto")) {
            	case "ADMINISTRAR":
            		puesto=Seguridad.ADMINISTRAR;
            	break;	
            	case "RECONOCER":
            		puesto=Seguridad.RECONOCER;
            	break;
            	case "ENTRENAR":
            		puesto=Seguridad.ENTRENAR;
        		break;
        		default:
        			throw new PuestoException();
        	}
        	userLogueado=new Usuario(encontrado.getByte("nivelseguridad"),
        			puesto,
        			encontrado.getString("nombreusuario"),
        			encontrado.getString("password"),
        			null);
            	
            }else {
            	throw new EncontrarUsuarioException();
            }
            return userLogueado;
            
			
		}
}
