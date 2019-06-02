package Visual;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Componentes.BotonDefault;
import Excepciones.DniException;
import Excepciones.PreparedStatementException;
import Principal.Conexion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
/**
 * JPanel para Crear Usuario
 * @author malki
 *
 */
public class CrearUsuario extends JPanel{
	private CrearUsuario esta=this;//Este panel
	private JTextField textoNombre;//El nombre
	private JTextField textoFichaPersonal;//La ficha personal
	private JPasswordField textoPassword;//La contraseña
	/**
	 * Constructor de CrearUsuario
	 */
	public CrearUsuario() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel etiquetaTitulo = new JLabel("A\u00F1adir Usuario");
		GridBagConstraints gbc_etiquetaTitulo = new GridBagConstraints();
		gbc_etiquetaTitulo.gridwidth = 5;
		gbc_etiquetaTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_etiquetaTitulo.gridx = 0;
		gbc_etiquetaTitulo.gridy = 0;
		add(etiquetaTitulo, gbc_etiquetaTitulo);
		
		JLabel etiquetaNombre = new JLabel("Nombre: ");
		GridBagConstraints gbc_etiquetaNombre = new GridBagConstraints();
		gbc_etiquetaNombre.anchor = GridBagConstraints.WEST;
		gbc_etiquetaNombre.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaNombre.gridx = 1;
		gbc_etiquetaNombre.gridy = 1;
		add(etiquetaNombre, gbc_etiquetaNombre);
		
		textoNombre = new JTextField();
		GridBagConstraints gbc_textoNombre = new GridBagConstraints();
		gbc_textoNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textoNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoNombre.gridx = 2;
		gbc_textoNombre.gridy = 1;
		add(textoNombre, gbc_textoNombre);
		textoNombre.setColumns(10);
		
		JLabel etiquetaPassword = new JLabel("Password:");
		GridBagConstraints gbc_etiquetaPassword = new GridBagConstraints();
		gbc_etiquetaPassword.anchor = GridBagConstraints.WEST;
		gbc_etiquetaPassword.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaPassword.gridx = 1;
		gbc_etiquetaPassword.gridy = 2;
		add(etiquetaPassword, gbc_etiquetaPassword);
		
		textoPassword = new JPasswordField();
		GridBagConstraints gbc_textoPassword = new GridBagConstraints();
		gbc_textoPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textoPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoPassword.gridx = 2;
		gbc_textoPassword.gridy = 2;
		add(textoPassword, gbc_textoPassword);
		
		JLabel etiquetaPuesto = new JLabel("Puesto:");
		GridBagConstraints gbc_etiquetaPuesto = new GridBagConstraints();
		gbc_etiquetaPuesto.anchor = GridBagConstraints.WEST;
		gbc_etiquetaPuesto.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaPuesto.gridx = 1;
		gbc_etiquetaPuesto.gridy = 3;
		add(etiquetaPuesto, gbc_etiquetaPuesto);
		
		JComboBox comboPuesto = new JComboBox();
		GridBagConstraints gbc_comboPuesto = new GridBagConstraints();
		gbc_comboPuesto.insets = new Insets(0, 0, 5, 5);
		gbc_comboPuesto.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboPuesto.gridx = 2;
		gbc_comboPuesto.gridy = 3;
		add(comboPuesto, gbc_comboPuesto);
		comboPuesto.addItem("ADMINISTRAR");
		comboPuesto.addItem("RECONOCER");
		comboPuesto.addItem("ENTRENAR");
		
		
		
		JLabel etiquetaNivelSeguridad = new JLabel("Nivel de Seguridad:");
		GridBagConstraints gbc_etiquetaNivelSeguridad = new GridBagConstraints();
		gbc_etiquetaNivelSeguridad.anchor = GridBagConstraints.WEST;
		gbc_etiquetaNivelSeguridad.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaNivelSeguridad.gridx = 1;
		gbc_etiquetaNivelSeguridad.gridy = 4;
		add(etiquetaNivelSeguridad, gbc_etiquetaNivelSeguridad);
		
		JComboBox comboSeguridad = new JComboBox();
		GridBagConstraints gbc_comboSeguridad = new GridBagConstraints();
		gbc_comboSeguridad.insets = new Insets(0, 0, 5, 5);
		gbc_comboSeguridad.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboSeguridad.gridx = 2;
		gbc_comboSeguridad.gridy = 4;
		add(comboSeguridad, gbc_comboSeguridad);
		comboSeguridad.addItem(1);
		comboSeguridad.addItem(2);
		comboSeguridad.addItem(3);
		comboSeguridad.addItem(4);
		comboSeguridad.addItem(5);
		
		
		JLabel etiquetaFichaPersonal = new JLabel("Ficha Personal:");
		GridBagConstraints gbc_etiquetaFichaPersonal = new GridBagConstraints();
		gbc_etiquetaFichaPersonal.anchor = GridBagConstraints.WEST;
		gbc_etiquetaFichaPersonal.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaFichaPersonal.gridx = 1;
		gbc_etiquetaFichaPersonal.gridy = 5;
		add(etiquetaFichaPersonal, gbc_etiquetaFichaPersonal);
		
		textoFichaPersonal = new JTextField();
		GridBagConstraints gbc_textoFichaPersonal = new GridBagConstraints();
		gbc_textoFichaPersonal.insets = new Insets(0, 0, 5, 5);
		gbc_textoFichaPersonal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoFichaPersonal.gridx = 2;
		gbc_textoFichaPersonal.gridy = 5;
		add(textoFichaPersonal, gbc_textoFichaPersonal);
		textoFichaPersonal.setColumns(10);
		
		BotonDefault botonAdd = new BotonDefault("A\u00F1adir");
		botonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PreparedStatement insertarUsuario=null;
				boolean insertadoOK=false;
				try {
					PreparedStatement comprobarDNI=Conexion.creaPreparedStatement("SELECT * FROM fichapersonal WHERE dni=?");
					comprobarDNI.setString(1, textoFichaPersonal.getText());
					ResultSet comprobadoDNI=comprobarDNI.executeQuery();
					if(comprobadoDNI.next()) {
					insertarUsuario = Conexion.creaPreparedStatement("INSERT INTO usuario(nombreusuario,password,puesto,nivelseguridad,fichapersonal) "
							+ "VALUES(?,?,?,?,?);");
					insertarUsuario.setString(1, textoNombre.getText());
					
					insertarUsuario.setString(2, String.copyValueOf(textoPassword.getPassword()));
					insertarUsuario.setString(3, (String) comboPuesto.getSelectedItem());
					insertarUsuario.setInt(4, (int) comboSeguridad.getSelectedItem()); 
					insertarUsuario.setString(5, textoFichaPersonal.getText());
					insertarUsuario.executeUpdate();
					insertadoOK=true;
					}else {
						throw new DniException();
					}
				} catch (DniException e1) {
					textoFichaPersonal.setBackground(Color.RED);
					JOptionPane.showMessageDialog(esta, "Error no se encienta Ficha Personal", "Error", JOptionPane.ERROR_MESSAGE);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PreparedStatementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						insertarUsuario.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(insertadoOK) {
					JOptionPane.showMessageDialog(esta, "Añadido correctamente", "Añadido", JOptionPane.ERROR_MESSAGE);
					SwingUtilities.getWindowAncestor(esta).dispose();
				}
			}
		});
		
		GridBagConstraints gbc_botonAdd = new GridBagConstraints();
		gbc_botonAdd.insets = new Insets(0, 0, 0, 5);
		gbc_botonAdd.gridx = 2;
		gbc_botonAdd.gridy = 6;
		add(botonAdd, gbc_botonAdd);
	}
}
