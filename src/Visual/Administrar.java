package Visual;

import javax.swing.JPanel;

import Componentes.BotonDefault;
import Componentes.Etiqueta;
import Personas.Usuario;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * JPanel de Administrar
 * @author malki
 *
 */
public class Administrar extends JPanel{
	private Ventana ventana;//La ventana
	private Usuario usuario;//el usuario
	public Administrar(Ventana ventana,EleccionPantalla eleccion, Usuario usuario) {
		this.ventana=ventana;
		this.usuario=usuario;
		setBackground(new Color(173, 169, 183));
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);//Poner en ventana completa
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{63, 14, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Etiqueta EtiquetaTitulo = new Etiqueta("Administracion");
		GridBagConstraints gbc_EtiquetaTitulo = new GridBagConstraints();
		gbc_EtiquetaTitulo.gridwidth = 5;
		gbc_EtiquetaTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_EtiquetaTitulo.gridx = 0;
		gbc_EtiquetaTitulo.gridy = 0;
		add(EtiquetaTitulo, gbc_EtiquetaTitulo);
		
		Etiqueta EtiquetaAñadir = new Etiqueta("A\u00F1adir");
		GridBagConstraints gbc_EtiquetaAñadir = new GridBagConstraints();
		gbc_EtiquetaAñadir.insets = new Insets(0, 0, 5, 5);
		gbc_EtiquetaAñadir.gridx = 1;
		gbc_EtiquetaAñadir.gridy = 1;
		add(EtiquetaAñadir, gbc_EtiquetaAñadir);
		
		Etiqueta EtiquetaBorrar = new Etiqueta("Borrar");
		GridBagConstraints gbc_EtiquetaBorrar = new GridBagConstraints();
		gbc_EtiquetaBorrar.insets = new Insets(0, 0, 5, 5);
		gbc_EtiquetaBorrar.gridx = 3;
		gbc_EtiquetaBorrar.gridy = 1;
		add(EtiquetaBorrar, gbc_EtiquetaBorrar);
		
		BotonDefault botonAddFicha = new BotonDefault("A\u00F1adir Ficha Personal");
		botonAddFicha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame ventanaFicha= new JFrame();
				CrearFicha ficha=new CrearFicha();
				ventanaFicha.setSize(400, 400);
				ventanaFicha.setLocationRelativeTo(null);
				ventanaFicha.getContentPane().add(ficha);
				ventanaFicha.setVisible(true);
			}
		});
		GridBagConstraints gbc_botonAddFicha = new GridBagConstraints();
		gbc_botonAddFicha.fill = GridBagConstraints.BOTH;
		gbc_botonAddFicha.insets = new Insets(0, 0, 5, 5);
		gbc_botonAddFicha.gridx = 1;
		gbc_botonAddFicha.gridy = 2;
		add(botonAddFicha, gbc_botonAddFicha);
		
		BotonDefault botonBorrarFicha = new BotonDefault("Borrar Ficha Personal");
		botonBorrarFicha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame ventanaFicha= new JFrame();
				BorrarFicha ficha=new BorrarFicha();
				ventanaFicha.setSize(400, 400);
				ventanaFicha.setLocationRelativeTo(null);
				ventanaFicha.getContentPane().add(ficha);
				ventanaFicha.setVisible(true);
			}
		});
		GridBagConstraints gbc_botonBorrarFicha = new GridBagConstraints();
		gbc_botonBorrarFicha.fill = GridBagConstraints.BOTH;
		gbc_botonBorrarFicha.insets = new Insets(0, 0, 5, 5);
		gbc_botonBorrarFicha.gridx = 3;
		gbc_botonBorrarFicha.gridy = 2;
		add(botonBorrarFicha, gbc_botonBorrarFicha);
		
		BotonDefault botonAddUsuario = new BotonDefault("A\u00F1adir Usuario");
		GridBagConstraints gbc_botonAddUsuario = new GridBagConstraints();
		gbc_botonAddUsuario.fill = GridBagConstraints.BOTH;
		gbc_botonAddUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_botonAddUsuario.gridx = 1;
		gbc_botonAddUsuario.gridy = 3;
		add(botonAddUsuario, gbc_botonAddUsuario);
		botonAddUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame ventanaFicha= new JFrame();
				CrearUsuario ficha=new CrearUsuario();
				ventanaFicha.setSize(400, 400);
				ventanaFicha.setLocationRelativeTo(null);
				ventanaFicha.getContentPane().add(ficha);
				ventanaFicha.setVisible(true);
			}
		});
		
		BotonDefault botonBorrarUsuario = new BotonDefault("BorrarUsuario");
		botonBorrarUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame ventanaFicha= new JFrame();
				BorrarUsuario ficha=new BorrarUsuario();
				ventanaFicha.setSize(400, 400);
				ventanaFicha.setLocationRelativeTo(null);
				ventanaFicha.getContentPane().add(ficha);
				ventanaFicha.setVisible(true);
			}
		});
		GridBagConstraints gbc_botonBorrarUsuario = new GridBagConstraints();
		gbc_botonBorrarUsuario.fill = GridBagConstraints.BOTH;
		gbc_botonBorrarUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_botonBorrarUsuario.gridx = 3;
		gbc_botonBorrarUsuario.gridy = 3;
		add(botonBorrarUsuario, gbc_botonBorrarUsuario);
		
	}

}
