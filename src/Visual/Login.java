package Visual;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import Componentes.BotonLoguear;
import Componentes.BotonSalir;
import Componentes.Etiqueta;
import Excepciones.EncontrarFichaPersonalException;
import Excepciones.EncontrarUsuarioException;
import Excepciones.PuestoException;
import Personas.FichaPersonal;
import Personas.Usuario;
import Principal.Conexion;
import Principal.Constantes;

import java.awt.Font;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import javax.swing.JPasswordField;
/**
 * JPanel de Login
 * @author malki
 *
 */
public class Login extends JPanel{
	private Ventana ventana;//La ventana
	private JTextField textoUsuario;//Donde se pondr� el nombre de usuario
	private JPasswordField textoPassword;//Donde se pondr� la contrase�a
	
	
	/**
	 * Se pone una imagen y se reescala, para que sea igual a la ventana
	 */
	public void paintComponent(Graphics g) {
		Image img=null;
		
		try {
			img = ImageIO.read(new File("src/imagenesPrograma/fondoLogin.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}
	
	/**
	 * Constructor de Login
	 * @param ventana La ventana donde va
	 */
	public Login(Ventana ventana) {
		this.ventana=ventana;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		Etiqueta lblLogin = new Etiqueta("Login");
		lblLogin.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 25));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.gridwidth = 5;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		add(lblLogin, gbc_lblLogin);
		
		Etiqueta lblUsuario = new Etiqueta("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		add(lblUsuario, gbc_lblUsuario);
		
		textoUsuario = new JTextField();
		GridBagConstraints gbc_textoUsuario = new GridBagConstraints();
		gbc_textoUsuario.gridwidth = 2;
		gbc_textoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textoUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoUsuario.gridx = 2;
		gbc_textoUsuario.gridy = 1;
		add(textoUsuario, gbc_textoUsuario);
		textoUsuario.setColumns(10);
		
		Etiqueta lblPassword = new Etiqueta("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		add(lblPassword, gbc_lblPassword);
		
		textoPassword = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		add(textoPassword, gbc_passwordField);
		
		BotonLoguear botonLoguear = new BotonLoguear(ventana,"Entrar",this);
		GridBagConstraints gbc_botonLoguear = new GridBagConstraints();
		gbc_botonLoguear.insets = new Insets(0, 0, 0, 5);
		gbc_botonLoguear.gridx = 2;
		gbc_botonLoguear.gridy = 3;
		add(botonLoguear, gbc_botonLoguear);
		
		BotonSalir botonSalir = new BotonSalir("Salir");
		GridBagConstraints gbc_botonSalir = new GridBagConstraints();
		gbc_botonSalir.gridx = 4;
		gbc_botonSalir.gridy = 3;
		add(botonSalir, gbc_botonSalir);
		
		
	}
	/**
	 * Funcion para loguearse(sacar los datos de la BBDD)
	 * @return devuelve el usuario logueado si lo encuentra
	 * @throws PuestoException Si el puesto no es el correcto(Si se han equivocado al ponerlo desde BBDD)
	 * @throws EncontrarUsuarioException Si no se encuentra el usuario
	 * @throws EncontrarFichaPersonalException Si no se encuentra la ficha del usuario(Otro fallo de BBDD)
	 * @throws SQLException Error en el SQL
	 */
	public Usuario loguear() throws PuestoException, EncontrarUsuarioException, EncontrarFichaPersonalException, SQLException {
		Usuario userLogueado=null;            	
        PreparedStatement comprobar=Conexion.creaPreparedStatement("select * from usuario "
                        + "where nombreUsuario=? and password=? ");
        if(Constantes.debugMode) {

            comprobar.setString(1,"admin");
            comprobar.setString(2, "admin");
        }else {
	        comprobar.setString(1, this.textoUsuario.getText());
	        comprobar.setString(2, String.copyValueOf(textoPassword.getPassword()));
        }
        ResultSet encontrado=comprobar.executeQuery();
        
        if(encontrado.next()) {
	    	userLogueado=new Usuario(encontrado.getByte("nivelseguridad"),
	    			encontrado.getString("puesto"),
	    			encontrado.getString("nombreusuario"),
	    			encontrado.getString("password"),
	    			null);
        	
        }else {
        	throw new EncontrarUsuarioException();
        }
        
        PreparedStatement comprobarFicha=Conexion.creaPreparedStatement("select * from fichapersonal "
                        + "where dni=? ");
    	comprobarFicha.setString(1,encontrado.getString("fichapersonal"));
    	
    	ResultSet encontrarFicha=comprobarFicha.executeQuery();
    	encontrarFicha.next();
		
		try {
			userLogueado.setIdentidad(new FichaPersonal(encontrarFicha.getString("nombre"),
						encontrarFicha.getString("apellidos"),
						encontrarFicha.getString("dni"),
						encontrarFicha.getInt("telefono"),
						encontrarFicha.getString("email"),
						encontrarFicha.getByte("nivelConfidencialidad"),
						encontrarFicha.getString("direccion")));
		} catch (Exception e) {
			throw new EncontrarFichaPersonalException();
		}finally {//Cerramos las conexiones
			comprobar.close();
			encontrado.close();
			comprobarFicha.close();
			encontrarFicha.close();
		}
        return userLogueado;
        
		
	}
	/**
	 * Si se encuentra usuario cambia la pantalla
	 * @param usuarioLogueado
	 */
	public void cambiarPantalla(Usuario usuarioLogueado) {
		if(usuarioLogueado!=null) {
			setVisible(false);
			EleccionPantalla elegir=new EleccionPantalla(ventana,usuarioLogueado);
			ventana.getContentPane().add(elegir);
		}
	}
	
}
