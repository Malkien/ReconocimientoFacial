package Visual;

import javax.swing.JPanel;

import Componentes.BotonAtras;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Administrar extends JPanel{
	public Administrar(EleccionPantalla eleccion) {
		
		eleccion.login.ventana.getJMenuBar().setVisible(true);
		eleccion.login.ventana.getJMenuBar().getComponents()[0].setVisible(false);
		eleccion.login.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);//Poner en ventana completa
	}

}
