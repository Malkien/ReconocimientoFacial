package Visual;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

import Componentes.BotonLoguear;
import Componentes.BotonSalir;
import Componentes.Etiqueta;
import java.awt.Font;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel{
	private JTextField textoUsuario;
	private JTextField textoPassword;
	

	public void paintComponent(Graphics g) {
		Image img=null;
		try {
			img = ImageIO.read(new File("src/fondo/fondoLogin.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}
	
	
	public Login(Ventana ventana) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Etiqueta lblLogin = new Etiqueta("Login");
		lblLogin.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 25));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.gridwidth = 5;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		add(lblLogin, gbc_lblLogin);
		
		Etiqueta lblUsuario = new Etiqueta("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		add(lblUsuario, gbc_lblUsuario);
		
		textoUsuario = new JTextField();
		GridBagConstraints gbc_textoUsuario = new GridBagConstraints();
		gbc_textoUsuario.gridwidth = 2;
		gbc_textoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textoUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoUsuario.gridx = 2;
		gbc_textoUsuario.gridy = 1;
		add(textoUsuario, gbc_textoUsuario);
		textoUsuario.setColumns(10);
		
		Etiqueta lblPassword = new Etiqueta("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		add(lblPassword, gbc_lblPassword);
		
		textoPassword = new JTextField();
		textoPassword.setColumns(10);
		GridBagConstraints gbc_textoPassword = new GridBagConstraints();
		gbc_textoPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoPassword.gridwidth = 2;
		gbc_textoPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textoPassword.gridx = 2;
		gbc_textoPassword.gridy = 2;
		add(textoPassword, gbc_textoPassword);
		
		BotonLoguear botonEntrar = new BotonLoguear("Entrar",textoUsuario.getText(),textoPassword.getText());
		botonEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		botonEntrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 3;
		add(botonEntrar, gbc_btnNewButton);
		
		BotonSalir botonSalir = new BotonSalir("Salir");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 3;
		add(botonSalir, gbc_btnNewButton_1);
	}
	
	

}
