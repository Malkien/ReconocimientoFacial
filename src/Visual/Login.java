package Visual;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import Componentes.Etiqueta;
import Componentes.Ventana;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Toolkit;
import javax.swing.JSlider;
import javax.swing.JTree;
import javax.swing.JSpinner;

public class Login extends Ventana{
	private JTextField textoUsuario;
	private JTextField textoPassword;
	public Login() {
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Etiqueta lblLogin = new Etiqueta("LOGIN");
		lblLogin.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 25));

		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.gridwidth = 5;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		panel.add(lblLogin, gbc_lblLogin);
		
		Etiqueta lblUsuario = new Etiqueta("Usuario: ");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		panel.add(lblUsuario, gbc_lblUsuario);
		
		textoUsuario = new JTextField();
		GridBagConstraints gbc_textoUsuario = new GridBagConstraints();
		gbc_textoUsuario.gridwidth = 2;
		gbc_textoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textoUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoUsuario.gridx = 2;
		gbc_textoUsuario.gridy = 1;
		panel.add(textoUsuario, gbc_textoUsuario);
		textoUsuario.setColumns(10);
		
		Etiqueta lblPassword = new Etiqueta("Password: ");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		panel.add(lblPassword, gbc_lblPassword);
		
		textoPassword = new JTextField();
		GridBagConstraints gbc_textoPassword = new GridBagConstraints();
		gbc_textoPassword.gridwidth = 2;
		gbc_textoPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textoPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoPassword.gridx = 2;
		gbc_textoPassword.gridy = 2;
		panel.add(textoPassword, gbc_textoPassword);
		textoPassword.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(SystemColor.activeCaption);
		btnEntrar.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnEntrar = new GridBagConstraints();
		gbc_btnEntrar.gridwidth = 2;
		gbc_btnEntrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEntrar.gridx = 0;
		gbc_btnEntrar.gridy = 3;
		panel.add(btnEntrar, gbc_btnEntrar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(SystemColor.activeCaption);
		btnSalir.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.gridwidth = 2;
		gbc_btnSalir.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalir.gridx = 3;
		gbc_btnSalir.gridy = 3;
		panel.add(btnSalir, gbc_btnSalir);
	}

}
