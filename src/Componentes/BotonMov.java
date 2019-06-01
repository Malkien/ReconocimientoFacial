package Componentes;

import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class BotonMov extends JButton{
	public BotonMov(LinkedList<JPanel> panel, boolean direccion) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		setIcon(new ImageIcon(BotonMov.class.getResource("/imagenesPrograma/adelante.png")));
		if(direccion) {
			setIcon(new ImageIcon(BotonMov.class.getResource("/imagenesPrograma/atras.png")));
		}
		
	}
}
