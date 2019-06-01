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
import Excepciones.EmailException;
import Excepciones.PreparedStatementException;
import Personas.FichaPersonal;
import Principal.Conexion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class BorrarUsuario extends JPanel{
	private BorrarUsuario esta=this;
	private JTextField textoNombre;
	
	public BorrarUsuario() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel etiquetaTitulo = new JLabel("Borrar Usuario");
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
		
		BotonDefault botonAdd = new BotonDefault("Borrar");
		botonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PreparedStatement borrarUsuario=null;
				boolean insertadoOK=false;
				try {
					
					borrarUsuario = Conexion.creaPreparedStatement("DELETE FROM usuario WHERE nombreusuario=?");
					borrarUsuario.setString(1, textoNombre.getText());
					borrarUsuario.executeUpdate();
					insertadoOK=true;
				} catch (PreparedStatementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						borrarUsuario.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(insertadoOK) {
					JOptionPane.showMessageDialog(esta, "Borrado correctamente", "Borrado", JOptionPane.ERROR_MESSAGE);
					SwingUtilities.getWindowAncestor(esta).dispose();
				}
			}
		});
		
		GridBagConstraints gbc_botonAdd = new GridBagConstraints();
		gbc_botonAdd.insets = new Insets(0, 0, 0, 5);
		gbc_botonAdd.gridx = 2;
		gbc_botonAdd.gridy = 2;
		add(botonAdd, gbc_botonAdd);
	}
}
