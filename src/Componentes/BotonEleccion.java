package Componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Personas.Usuario;
import Personas.Usuario.Seguridad;
import Visual.Administrar;
import Visual.EleccionPantalla;
import Visual.Entrenamiento;
import Visual.Reconocimiento;
import Visual.Ventana;

public class BotonEleccion extends BotonDefault{
	private EleccionPantalla eleccion;
	private Usuario usuario;
	private Usuario.Seguridad seguridad;
	private Ventana ventana;
	public BotonEleccion(Ventana ventana,String txt, EleccionPantalla eleccion,Usuario usuario, Usuario.Seguridad seguridad) {
		super(txt);
		this.eleccion=eleccion;
		this.usuario=usuario;
		this.seguridad=seguridad;
		this.ventana=ventana;
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
			}else {
				JOptionPane.showMessageDialog(eleccion,"No tienes permisos", "Permisos Insuficientes",JOptionPane.ERROR_MESSAGE);
			}
		break;
		case ENTRENAR:
			if(seguridad==Seguridad.ENTRENAR) {
				cambiarPantalla();
			}else {
				JOptionPane.showMessageDialog(eleccion,"No tienes permisos", "Permisos Insuficientes",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void cambiarPantalla() {
		eleccion.setVisible(false);
		if(seguridad==Seguridad.ADMINISTRAR) {
			Administrar administrar=new Administrar(ventana,eleccion,usuario,eleccion.atras);
			ventana.getContentPane().add(administrar);
			//EleccionPantalla elegir=new EleccionPantalla(ventana,usuario, atras);
		}else if(seguridad==Seguridad.RECONOCER) {
			Reconocimiento reconocer=new Reconocimiento(ventana,eleccion,usuario,eleccion.atras);
			ventana.getContentPane().add(reconocer);
		}else if(seguridad==Seguridad.ENTRENAR) {
			Entrenamiento entrenar=new Entrenamiento(ventana,eleccion,usuario,eleccion.atras);
			ventana.getContentPane().add(entrenar);
		}
	}

}
