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
=======
	private LinkedList adelante;
	public Ventana() {
		adelante=null;
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		BotonMov botonAtras=new BotonMov();
		menuBar.add(botonAtras);

		Login login=new Login(this,botonAtras);
>>>>>>> parent of f091510... solucion
		getContentPane().add(login,BorderLayout.CENTER);
		setSize(500,350);
		setVisible(true);
		setLocationRelativeTo(null);
<<<<<<< HEAD
		
<<<<<<< HEAD
=======
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		BotonMov botonAtras=new BotonMov(atras, true);
		menuBar.add(botonAtras);
		BotonMov botonAdelante=new BotonMov(adelante,false);
		menuBar.add(botonAdelante);
>>>>>>> parent of 3e46111... Inicio de implementacion de atras
=======
>>>>>>> parent of f091510... solucion

	}
}
