package Componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
/**
 * Visual de los botones
 * @author malki
 *
 */
public class BotonDefault extends JButton{
	/**
	 * Constructor
	 * @param txt texto
	 */
	public BotonDefault(String txt) {
		super(txt);
		setBackground(new Color(235, 206, 235));
        setBorderPainted(true);
		setForeground(new Color(0, 0, 0));
		setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
}
