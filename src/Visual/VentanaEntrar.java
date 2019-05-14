package Visual;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import Personas.Usuario;
import javax.swing.JMenuBar;

import Componentes.BotonAtras;

import javax.swing.JButton;
import java.awt.Cursor;

public class VentanaEntrar extends JFrame{
	
	public VentanaEntrar() {
		Login login=new Login(this);
		getContentPane().add(login,BorderLayout.CENTER);
		setSize(500,350);
		setVisible(true);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setVisible(false);
		JButton btnNewButton = new JButton("New button");
		menuBar.add(btnNewButton);
		
		BotonAtras btnNewButton_1 = new BotonAtras();
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(btnNewButton_1);

	}
}
