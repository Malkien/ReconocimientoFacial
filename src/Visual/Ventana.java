package Visual;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import Personas.Usuario;
import javax.swing.JMenuBar;

import Componentes.BotonAtras;

import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;

public class Ventana extends JFrame{
	
	public Ventana() {
		Login login=new Login(this);
		getContentPane().add(login,BorderLayout.CENTER);
		setSize(500,350);
		setVisible(true);
		setLocationRelativeTo(null);

	}
}
