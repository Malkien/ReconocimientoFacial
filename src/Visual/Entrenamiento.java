package Visual;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Componentes.Etiqueta;
import Principal.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class Entrenamiento extends JPanel{
	private File archivoCogido;
	private JTextArea areaTexto;
	private Image imagen;
	public Entrenamiento(EleccionPantalla eleccion) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Etiqueta etiquetaTitulo = new Etiqueta("Entrenamiento");
		GridBagConstraints gbc_etiquetaTitulo = new GridBagConstraints();
		gbc_etiquetaTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_etiquetaTitulo.gridwidth = 6;
		gbc_etiquetaTitulo.gridx = 0;
		gbc_etiquetaTitulo.gridy = 0;
		add(etiquetaTitulo, gbc_etiquetaTitulo);
		
		JButton botonAbrirImagen = new JButton("Abrir Imagen");
		GridBagConstraints gbc_botonAbrirImagen = new GridBagConstraints();
		gbc_botonAbrirImagen.gridwidth = 2;
		gbc_botonAbrirImagen.insets = new Insets(0, 0, 5, 5);
		gbc_botonAbrirImagen.gridx = 1;
		gbc_botonAbrirImagen.gridy = 1;
		add(botonAbrirImagen, gbc_botonAbrirImagen);
		
		JButton botonConvertir = new JButton("Convertir");
		GridBagConstraints gbc_botonConvertir = new GridBagConstraints();
		gbc_botonConvertir.gridwidth = 2;
		gbc_botonConvertir.insets = new Insets(0, 0, 5, 5);
		gbc_botonConvertir.gridx = 3;
		gbc_botonConvertir.gridy = 1;
		add(botonConvertir, gbc_botonConvertir);
		
		JPanel lienzo = new JPanel();
		GridBagConstraints gbc_lienzo = new GridBagConstraints();
		gbc_lienzo.gridheight = 2;
		gbc_lienzo.gridwidth = 2;
		gbc_lienzo.insets = new Insets(0, 0, 0, 5);
		gbc_lienzo.fill = GridBagConstraints.BOTH;
		gbc_lienzo.gridx = 1;
		gbc_lienzo.gridy = 2;
		add(lienzo, gbc_lienzo);
		
		
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		
		eleccion.login.ventana.getJMenuBar().setVisible(true);
		eleccion.login.ventana.getJMenuBar().getComponents()[0].setVisible(false);
		eleccion.login.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);//Poner en ventana completa
		
		botonAbrirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser elegir=new JFileChooser();
				FileNameExtensionFilter filter=
				new FileNameExtensionFilter("JPEG,GIF,PNG and TXT","jpg", "gif","png","txt");
				elegir.setFileFilter(filter);
				int opcionElegida=elegir.showOpenDialog(eleccion.login.ventana);
				if(opcionElegida==JFileChooser.APPROVE_OPTION) {
					//En el caso de que se haya dado a aceptar, abrimos el archivo
					archivoCogido=elegir.getSelectedFile();
					System.out.println(archivoCogido.getAbsolutePath());
					String[] rutaSeparada=archivoCogido.getAbsolutePath().split("\\.");
					String extension=rutaSeparada[rutaSeparada.length-1];
					if(extension.equals("txt")) {
						try {
							BufferedReader reader=
							new BufferedReader(new FileReader(archivoCogido));
							String linea="";
							String contenido="";
							do {
								 contenido+=linea+"\n";
								 linea=reader.readLine();
							}while(linea!=null);
							imagen=ImageUtils.textoAImagen(contenido);
						} catch (FileNotFoundException e) {
							JOptionPane.showMessageDialog
							(eleccion.login.ventana, "archivo no encontrado",
									"archivo no encontrado",
									JOptionPane.ERROR_MESSAGE);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
					}
					//El fichero es una imagen.
					JLabel labelImagen=new JLabel();
					try {
						imagen=ImageIO.read(archivoCogido);
						imagen=imagen.getScaledInstance(lienzo.getWidth(), lienzo.getHeight(), Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(eleccion.login.ventana, "No es una imagen", "No es una imagen", JOptionPane.ERROR_MESSAGE);
					}
					labelImagen.setIcon(new ImageIcon(imagen));
					lienzo.add(labelImagen);
					labelImagen.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							labelImagen.setIcon(new ImageIcon(imagen));
					}});
		}}});
		
	}
	
}
