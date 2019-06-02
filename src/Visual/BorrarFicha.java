package Visual;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Componentes.BotonDefault;
import Excepciones.DniException;
import Excepciones.PreparedStatementException;
import Personas.FichaPersonal;
import Principal.Conexion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrarFicha extends JPanel{
	private BorrarFicha esta=this;
	private JTextField textoDNI;
	
	public BorrarFicha() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel etiquetaTitulo = new JLabel("Borrar Ficha Personal");
		GridBagConstraints gbc_etiquetaTitulo = new GridBagConstraints();
		gbc_etiquetaTitulo.gridwidth = 5;
		gbc_etiquetaTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_etiquetaTitulo.gridx = 0;
		gbc_etiquetaTitulo.gridy = 0;
		add(etiquetaTitulo, gbc_etiquetaTitulo);
		
		JLabel etiquetaDNI = new JLabel("DNI: ");
		GridBagConstraints gbc_etiquetaDNI = new GridBagConstraints();
		gbc_etiquetaDNI.anchor = GridBagConstraints.WEST;
		gbc_etiquetaDNI.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaDNI.gridx = 1;
		gbc_etiquetaDNI.gridy = 2;
		add(etiquetaDNI, gbc_etiquetaDNI);
		
		BotonDefault botonAdd = new BotonDefault("Borrar");
		botonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PreparedStatement BorrarFicha=null;
				boolean insertadoOK=false;
				try {
					FichaPersonal.comprobarDni(textoDNI.getText());
					
					BorrarFicha = Conexion.creaPreparedStatement("DELETE FROM fichapersonal WHERE dni=?;");
					BorrarFicha.setString(1,textoDNI.getText());
					PreparedStatement BorrarUsuarios = Conexion.creaPreparedStatement("DELETE FROM usuario WHERE fichapersonal=?;");
					BorrarFicha.setString(1,textoDNI.getText());
					BorrarFicha.executeUpdate();
					insertadoOK=true;
				
				} catch (PreparedStatementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DniException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						BorrarFicha.close();
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
		
		textoDNI = new JTextField();
		GridBagConstraints gbc_textoDNI = new GridBagConstraints();
		gbc_textoDNI.insets = new Insets(0, 0, 5, 5);
		gbc_textoDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textoDNI.gridx = 2;
		gbc_textoDNI.gridy = 2;
		add(textoDNI, gbc_textoDNI);
		textoDNI.setColumns(10);
		GridBagConstraints gbc_botonAdd = new GridBagConstraints();
		gbc_botonAdd.insets = new Insets(0, 0, 0, 5);
		gbc_botonAdd.gridx = 2;
		gbc_botonAdd.gridy = 4;
		add(botonAdd, gbc_botonAdd);
	}
}
