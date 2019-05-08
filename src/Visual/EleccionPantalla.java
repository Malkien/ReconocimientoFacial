package Visual;

import javax.swing.JPanel;

import Componentes.BotonEleccion;
import Personas.Usuario;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Color;

public class EleccionPantalla extends JPanel{
	private Usuario usuario;
	public Login login;
	public EleccionPantalla(Login login,Usuario usuario) {
		this.usuario=usuario;
		this.login=login;
		setBackground(new Color(173, 169, 183));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{35, 0, 0, 0, 35, 0};
		gridBagLayout.rowHeights = new int[]{95, 0, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titulo = new JLabel("Menu Funcionalidad");
		GridBagConstraints gbc_titulo = new GridBagConstraints();
		gbc_titulo.insets = new Insets(0, 0, 5, 5);
		gbc_titulo.gridx = 2;
		gbc_titulo.gridy = 0;
		add(titulo, gbc_titulo);
		
		BotonEleccion botonEntrenar = new BotonEleccion("Entrenamiento",this, usuario,Usuario.Seguridad.ENTRENAR);
		GridBagConstraints gbc_botonEntrenar = new GridBagConstraints();
		gbc_botonEntrenar.fill = GridBagConstraints.BOTH;
		gbc_botonEntrenar.insets = new Insets(0, 0, 5, 5);
		gbc_botonEntrenar.gridx = 1;
		gbc_botonEntrenar.gridy = 1;
		add(botonEntrenar, gbc_botonEntrenar);
		
		BotonEleccion botonReconocimiento = new BotonEleccion("Reconocimiento",this, usuario,Usuario.Seguridad.RECONOCER);
		GridBagConstraints gbc_botonReconocimiento = new GridBagConstraints();
		gbc_botonReconocimiento.fill = GridBagConstraints.BOTH;
		gbc_botonReconocimiento.insets = new Insets(0, 0, 5, 5);
		gbc_botonReconocimiento.gridx = 2;
		gbc_botonReconocimiento.gridy = 1;
		add(botonReconocimiento, gbc_botonReconocimiento);
		
		BotonEleccion botonAdministrar = new BotonEleccion("Administrar", this, usuario,Usuario.Seguridad.ADMINISTRAR);
		GridBagConstraints gbc_botonAdministrar = new GridBagConstraints();
		gbc_botonAdministrar.fill = GridBagConstraints.BOTH;
		gbc_botonAdministrar.insets = new Insets(0, 0, 5, 5);
		gbc_botonAdministrar.gridx = 3;
		gbc_botonAdministrar.gridy = 1;
		add(botonAdministrar, gbc_botonAdministrar);
	}
	
}
