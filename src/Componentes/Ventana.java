package Componentes;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame{

	public Ventana() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		//Las dimensiones de la ventana
		setSize(500,350);
		//Donde la ventana se habre en la pantalla
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./icono/icono.svg"));
		
	}
}
