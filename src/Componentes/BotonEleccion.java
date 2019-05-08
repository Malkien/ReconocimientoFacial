package Componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import Personas.Usuario;
import Personas.Usuario.Seguridad;
import Visual.Administrar;
import Visual.EleccionPantalla;
import Visual.Entrenamiento;
import Visual.Login;
import Visual.Reconocimiento;

public class BotonEleccion extends JButton{
	private EleccionPantalla eleccion;
	private Usuario usuario;
	private Usuario.Seguridad seguridad;
	public BotonEleccion(String txt, EleccionPantalla eleccion,Usuario usuario, Usuario.Seguridad seguridad) {
		super(txt);
		this.eleccion=eleccion;
		this.usuario=usuario;
		this.seguridad=seguridad;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				comprobarPuesto();
			}
			
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				switch (arg0.getKeyChar()) {
				case KeyEvent.VK_ENTER:
					comprobarPuesto();
					break;
				}
			}
		});
		setBackground(new Color(235, 206, 235));
        setBorderPainted(true);
		setForeground(new Color(0, 0, 0));
		setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
	
	private void comprobarPuesto() {
		switch(usuario.getPuesto()) {
		case ADMINISTRAR:
			if(seguridad==Seguridad.ADMINISTRAR) {
				cambiarPantalla();
			}else if(seguridad==Seguridad.RECONOCER) {
				cambiarPantalla();
			}else if(seguridad==Seguridad.ENTRENAR) {
				cambiarPantalla();
			}
		break;
		case RECONOCER:
			if(seguridad==Seguridad.RECONOCER) {
				cambiarPantalla();
			}else if(seguridad==Seguridad.ENTRENAR) {
				cambiarPantalla();
			}
		break;
		case ENTRENAR:
			if(seguridad==Seguridad.ENTRENAR) {
				cambiarPantalla();
			}
		}
	}
	private void cambiarPantalla() {
		eleccion.setVisible(false);
		if(seguridad==Seguridad.ADMINISTRAR) {
			Administrar administrar=new Administrar();
			eleccion.login.ventana.getContentPane().add(administrar);
		}else if(seguridad==Seguridad.RECONOCER) {
			Reconocimiento reconocer=new Reconocimiento();
			eleccion.login.ventana.getContentPane().add(reconocer);
		}else if(seguridad==Seguridad.ENTRENAR) {
			Entrenamiento entrenar=new Entrenamiento();
			eleccion.login.ventana.getContentPane().add(entrenar);
		}
	}
	

}
