package Componentes;

import java.awt.Image;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BotonAtras extends JButton{
	public BotonAtras() {
		setIcon(new ImageIcon(BotonAtras.class.getResource("/imagenesPrograma/atras.png")));
	}


}
