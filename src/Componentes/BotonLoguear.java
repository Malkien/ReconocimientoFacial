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
import javax.swing.JPanel;

import Excepciones.EncontrarFichaPersonalException;
import Excepciones.EncontrarUsuarioException;
import Excepciones.PuestoException;
import Personas.FichaPersonal;
import Personas.Usuario;
import Visual.Login;

public class BotonLoguear extends JButton{
	private Connection conexion;
	private Login login;
	public BotonLoguear(String txt,Connection conexion, Login login) {
		super(txt);
		this.login=login;
		this.conexion=conexion;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				loguear();
			}
			
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				switch (arg0.getKeyChar()) {
				case KeyEvent.VK_ENTER:
					loguear();
					break;
				}
			}
		});
		setBackground(new Color(235, 206, 235));
        setBorderPainted(true);
		setForeground(new Color(0, 0, 0));
		setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
	private void loguear() {
		Usuario usuarioLogueado=null;
		try {
			usuarioLogueado=login.loguear();
		}catch(SQLException ex) {
        	ex.printStackTrace();
        	System.err.println("SQL ERROR");
        } catch (PuestoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncontrarUsuarioException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(login,"Usuario o contraseña incorrectos", "Usuario o contraseña incorrectos",JOptionPane.ERROR_MESSAGE);
		} catch (EncontrarFichaPersonalException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(login,"Fallo en la BBDD del sistema", "BBDD caida",JOptionPane.ERROR_MESSAGE);
		}
		if(usuarioLogueado!=null) {
			login.setVisible(false);
			
		}
		
	}
}
