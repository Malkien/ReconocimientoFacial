package Visual;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.JFrame;

import Personas.Usuario;

public class Ventana extends JFrame{
	private Usuario usuario;
	private Login panel;
	

	
	public Ventana() {
		Login login=new Login(this);
		getContentPane().add(login,BorderLayout.CENTER);
		setSize(500,350);
		setVisible(true);
		setLocationRelativeTo(null);
		this.panel=login;

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Login getPanel() {
		return panel;
	}

	public void setPanel(Login panel) {
		this.panel = panel;
	}
}
