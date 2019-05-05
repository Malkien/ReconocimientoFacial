package Componentes;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotonSalir extends JButton{
	public BotonSalir(String txt) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
			
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				switch (arg0.getKeyChar()) {
				case KeyEvent.VK_ENTER:
					System.exit(0);
					break;
				}
			}
		});
		setBackground(new Color(135, 206, 235));
        setBorderPainted(true);
		setForeground(new Color(0, 0, 0));
		setText(txt);
		setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
	
}
