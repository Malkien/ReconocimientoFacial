package Componentes;

import java.awt.Font;

import javax.swing.JLabel;
/**
 * Etiqueta Personalizada
 * @author malki
 *
 */
public class Etiqueta extends JLabel{
	/**
	 * Constructor de etiqueta
	 * @param txt el texto
	 */
	public Etiqueta(String txt) {
		super(txt);
		setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
		
	}
	
}
