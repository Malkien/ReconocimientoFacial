package Visual;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class Reconocimiento extends JPanel{
	private Ventana ventana;
	public Reconocimiento(Ventana ventana) {
		this.ventana=ventana;
		JLabel lblReconocer = new JLabel("Reconocer");
		add(lblReconocer);
	}

}
