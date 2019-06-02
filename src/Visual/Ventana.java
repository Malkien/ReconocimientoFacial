package Visual;

import java.awt.BorderLayout;
import javax.swing.JFrame;
/**
 * El JFrame personalizado
 * @author malki
 *
 */
public class Ventana extends JFrame{
	/**
	 * Constructor de Ventana
	 */
	public Ventana() {

		Login login=new Login(this);
		getContentPane().add(login,BorderLayout.CENTER);
		setSize(500,350);//Dimensines de la ventana
		setVisible(true);//Que se vea
		setLocationRelativeTo(null);//para que aparezca en el centro

	}
}
