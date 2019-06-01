package Componentes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import Visual.CrearFicha;

public class BotonAdministrar extends BotonDefault{

	public BotonAdministrar(String txt) {
		super(txt);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame ventanaFicha= new JFrame();
				if(true) {

					CrearFicha ficha=new CrearFicha();
				}
				ventanaFicha.setSize(400, 400);
				ventanaFicha.setLocationRelativeTo(null);
				ventanaFicha.setVisible(true);
			}
		});
		
	}
}
