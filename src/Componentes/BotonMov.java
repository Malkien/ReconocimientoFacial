package Componentes;

import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

import Visual.Login;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class BotonMov extends JButton{
	private LinkedList<JPanel> panel;
	public BotonMov() {
		panel = new LinkedList<JPanel>(); 
		setIcon(new ImageIcon(BotonMov.class.getResource("/imagenesPrograma/atras.png")));
		
		
	}
	public void addContenido(JPanel nuevo) {
		panel.addFirst(nuevo);
	}
	public void click(JPanel frame) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				panel.getFirst().setVisible(true);
				panel.removeFirst();
			}
		});
	}
}
