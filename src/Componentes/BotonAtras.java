package Componentes;

import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotonAtras extends JButton{
	public BotonAtras(JPanel panel) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		setIcon(new ImageIcon(BotonAtras.class.getResource("/imagenesPrograma/atras.png")));
		
	}
	public static void hola() {
		
	}

}
