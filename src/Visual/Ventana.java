package Visual;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import Personas.Usuario;
import javax.swing.JMenuBar;

import Componentes.BotonMov;

import javax.swing.JButton;
import java.awt.Cursor;
import java.util.LinkedList;

import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class Ventana extends JFrame{
<<<<<<< HEAD
	public Ventana() {
		Login login=new Login(this);
=======
	private LinkedList atras;
	private LinkedList adelante;
	public Ventana() {
		Login login=new Login(this);
		atras=null;
		adelante=null;
>>>>>>> parent of 3e46111... Inicio de implementacion de atras
		getContentPane().add(login,BorderLayout.CENTER);
		setSize(500,350);
		setVisible(true);
		setLocationRelativeTo(null);
		
<<<<<<< HEAD
=======
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		BotonMov botonAtras=new BotonMov(atras, true);
		menuBar.add(botonAtras);
		BotonMov botonAdelante=new BotonMov(adelante,false);
		menuBar.add(botonAdelante);
>>>>>>> parent of 3e46111... Inicio de implementacion de atras

	}
}
