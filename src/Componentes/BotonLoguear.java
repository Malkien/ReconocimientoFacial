package Componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Excepciones.EncontrarFichaPersonalException;
import Excepciones.EncontrarUsuarioException;
import Excepciones.PreparedStatementException;
import Excepciones.PuestoException;
import Personas.Usuario;
import Visual.EleccionPantalla;
import Visual.Login;
import Visual.Ventana;

public class BotonLoguear extends BotonDefault{
	private Login login;
	private Ventana ventana;
	public BotonLoguear(Ventana ventana,String txt, Login login) {
		super(txt);
		this.login=login;
		this.ventana=ventana;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				loguear(ventana);
			}
			
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				switch (arg0.getKeyChar()) {
				case KeyEvent.VK_ENTER:
					loguear(ventana);
					break;
				}
			}
		});
		setBackground(new Color(235, 206, 235));
        setBorderPainted(true);
		setForeground(new Color(0, 0, 0));
		setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
	private void loguear(Ventana ventana) {
		Usuario usuarioLogueado=null;
		try {
			usuarioLogueado=login.loguear();
		}catch(PreparedStatementException e) {
			JOptionPane.showMessageDialog(login,"Error al conectar a la Base de datos(Error en el PreparedStatement)\nComprueba la hora romance!!!", "Adios!!! Fallo en la conexion",JOptionPane.ERROR_MESSAGE);
		}catch(SQLException ex) {
        	ex.printStackTrace();
        	System.err.println("SQL ERROR");
        } catch (PuestoException e) {

        	JOptionPane.showMessageDialog(login, "Alguno de la BBDD la ha cagado al poner el Puesto","Esto no deberia de pasar..",JOptionPane.ERROR_MESSAGE);
		} catch (EncontrarUsuarioException e) {
			
			JOptionPane.showMessageDialog(login,"Usuario o contraseña incorrectos", "Usuario o contraseña incorrectos",JOptionPane.ERROR_MESSAGE);
		} catch (EncontrarFichaPersonalException e) {
			
			JOptionPane.showMessageDialog(login,"Fallo en la BBDD del sistema", "BBDD caida",JOptionPane.ERROR_MESSAGE);
		}
		login.cambiarPantalla(usuarioLogueado);
		
	}
}
