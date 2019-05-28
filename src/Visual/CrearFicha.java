package Visual;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import Excepciones.DniException;
import Excepciones.EmailException;
import Excepciones.PreparedStatementException;
import Personas.FichaPersonal;
import Principal.Conexion;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearFicha extends JPanel{
	private CrearFicha esta=this;
	private JTextField textoNombre;
	private JTextField textoApellidos;
	private JTextField textoDNI;
	private JTextField textoTelefono;
	private JTextField textoDireccion;
	private JTextField textoNvConfidencialidad;
	private JTextField textoEmail;
	
	public CrearFicha() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel etiquetaTitulo = new JLabel("A\u00F1adir Ficha Personal");
		GridBagConstraints gbc_etiquetaTitulo = new GridBagConstraints();
		gbc_etiquetaTitulo.gridwidth = 5;
		gbc_etiquetaTitulo.insets = new Insets(0, 0, 5, 5);
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
		
		JLabel etiquetaApellidos = new JLabel("Apellidos: ");
		GridBagConstraints gbc_etiquetaApellidos = new GridBagConstraints();
		gbc_etiquetaApellidos.anchor = GridBagConstraints.WEST;
		gbc_etiquetaApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaApellidos.gridx = 1;
		gbc_etiquetaApellidos.gridy = 2;
		add(etiquetaApellidos, gbc_etiquetaApellidos);
		
		textoApellidos = new JTextField();
		GridBagConstraints gbc_textoApellidos = new GridBagConstraints();
		gbc_textoApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textoApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoApellidos.gridx = 2;
		gbc_textoApellidos.gridy = 2;
		add(textoApellidos, gbc_textoApellidos);
		textoApellidos.setColumns(10);
		
		JLabel etiquetaDNI = new JLabel("DNI: ");
		GridBagConstraints gbc_etiquetaDNI = new GridBagConstraints();
		gbc_etiquetaDNI.anchor = GridBagConstraints.WEST;
		gbc_etiquetaDNI.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaDNI.gridx = 1;
		gbc_etiquetaDNI.gridy = 3;
		add(etiquetaDNI, gbc_etiquetaDNI);
		
		textoDNI = new JTextField();
		GridBagConstraints gbc_textoDNI = new GridBagConstraints();
		gbc_textoDNI.insets = new Insets(0, 0, 5, 5);
		gbc_textoDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoDNI.gridx = 2;
		gbc_textoDNI.gridy = 3;
		add(textoDNI, gbc_textoDNI);
		textoDNI.setColumns(10);
		
		JLabel etiquetaTelefono = new JLabel("Telefono: ");
		GridBagConstraints gbc_etiquetaTelefono = new GridBagConstraints();
		gbc_etiquetaTelefono.anchor = GridBagConstraints.WEST;
		gbc_etiquetaTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaTelefono.gridx = 1;
		gbc_etiquetaTelefono.gridy = 4;
		add(etiquetaTelefono, gbc_etiquetaTelefono);
		
		textoTelefono = new JTextField();
		GridBagConstraints gbc_textoTelefono = new GridBagConstraints();
		gbc_textoTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_textoTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoTelefono.gridx = 2;
		gbc_textoTelefono.gridy = 4;
		add(textoTelefono, gbc_textoTelefono);
		textoTelefono.setColumns(10);
		
		JLabel etiquetaDireccion = new JLabel("Direccion: ");
		GridBagConstraints gbc_etiquetaDireccion = new GridBagConstraints();
		gbc_etiquetaDireccion.anchor = GridBagConstraints.WEST;
		gbc_etiquetaDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaDireccion.gridx = 1;
		gbc_etiquetaDireccion.gridy = 5;
		add(etiquetaDireccion, gbc_etiquetaDireccion);
		
		textoDireccion = new JTextField();
		GridBagConstraints gbc_textoDireccion = new GridBagConstraints();
		gbc_textoDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_textoDireccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoDireccion.gridx = 2;
		gbc_textoDireccion.gridy = 5;
		add(textoDireccion, gbc_textoDireccion);
		textoDireccion.setColumns(10);
		
		JLabel etiquetaNvConfidencialidad = new JLabel("Nivel de Confidencialidad: ");
		GridBagConstraints gbc_etiquetaNvConfidencialidad = new GridBagConstraints();
		gbc_etiquetaNvConfidencialidad.anchor = GridBagConstraints.WEST;
		gbc_etiquetaNvConfidencialidad.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaNvConfidencialidad.gridx = 1;
		gbc_etiquetaNvConfidencialidad.gridy = 6;
		add(etiquetaNvConfidencialidad, gbc_etiquetaNvConfidencialidad);
		
		textoNvConfidencialidad = new JTextField();
		GridBagConstraints gbc_textoNvConfidencialidad = new GridBagConstraints();
		gbc_textoNvConfidencialidad.insets = new Insets(0, 0, 5, 5);
		gbc_textoNvConfidencialidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoNvConfidencialidad.gridx = 2;
		gbc_textoNvConfidencialidad.gridy = 6;
		add(textoNvConfidencialidad, gbc_textoNvConfidencialidad);
		textoNvConfidencialidad.setColumns(10);
		
		JLabel etiquetaEmail = new JLabel("Email: ");
		GridBagConstraints gbc_etiquetaEmail = new GridBagConstraints();
		gbc_etiquetaEmail.anchor = GridBagConstraints.WEST;
		gbc_etiquetaEmail.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaEmail.gridx = 1;
		gbc_etiquetaEmail.gridy = 7;
		add(etiquetaEmail, gbc_etiquetaEmail);
		
		textoEmail = new JTextField();
		GridBagConstraints gbc_textoEmail = new GridBagConstraints();
		gbc_textoEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textoEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoEmail.gridx = 2;
		gbc_textoEmail.gridy = 7;
		add(textoEmail, gbc_textoEmail);
		textoEmail.setColumns(10);
		
		JButton botonAdd = new JButton("A\u00F1adir");
		botonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PreparedStatement insertarFicha=null;
				boolean insertadoOK=false;
				try {
					FichaPersonal.comprobarEmail(textoEmail.getText());
					FichaPersonal.comprobarDni(textoDNI.getText());
					
					insertarFicha = Conexion.creaPreparedStatement("INSERT INTO fichapersonal VALUES("
							+ "nombre=?,apellidos=?,dni=?,telefono=?,direccion=?,nvSeguridad=?,email=?);");
					insertarFicha.setString(1, textoNombre.getText());
					insertarFicha.setString(2, textoApellidos.getText());
					insertarFicha.setString(3, textoDNI.getText());
					insertarFicha.setInt(4, Integer.parseInt(textoTelefono.getText()));
					insertarFicha.setString(5, textoDireccion.getText());
					insertarFicha.setInt(6, Integer.parseInt(textoNvConfidencialidad.getText()));
					insertarFicha.setString(7,textoEmail.getText());
					insertarFicha.executeQuery();
					insertadoOK=true;
				}catch(EmailException ex) {
					textoEmail.setBackground(Color.RED);
					ex.printStackTrace();
				} catch (DniException e1) {
					textoEmail.setBackground(Color.RED);
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
						insertarFicha.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(insertadoOK) {
					esta.setVisible(false);
				}
			}
		});
		GridBagConstraints gbc_botonAdd = new GridBagConstraints();
		gbc_botonAdd.insets = new Insets(0, 0, 0, 5);
		gbc_botonAdd.gridx = 2;
		gbc_botonAdd.gridy = 8;
		add(botonAdd, gbc_botonAdd);
	}
}
