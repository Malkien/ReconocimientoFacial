package Visual;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.jdbc.Blob;

import Componentes.Etiqueta;
import Excepciones.PreparedStatementException;
import Personas.FichaPersonal;
import Principal.Conexion;
import Principal.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.Icon;
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
import java.awt.Graphics;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JTextPane;

public class EntrenamientoReconocimiento extends JPanel{
	private File archivoCogido;
	private JTextArea areaTexto;
	private EntrenamientoReconocimiento thisRef;
	private BufferedImage imagen;
	private Map<Integer,BufferedImage> listadoImagenes=new HashMap<Integer,BufferedImage>();
	private BufferedImage imagenSobel;
	private FichaPersonal personaSimilitud;
	private Ventana ventana;
	public EntrenamientoReconocimiento(Ventana ventana,EleccionPantalla eleccion, String txt) {
		this.ventana=ventana;
		thisRef=this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Etiqueta etiquetaTitulo = new Etiqueta(txt);
		GridBagConstraints gbc_etiquetaTitulo = new GridBagConstraints();
		gbc_etiquetaTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_etiquetaTitulo.gridwidth = 5;
		gbc_etiquetaTitulo.gridx = 0;
		gbc_etiquetaTitulo.gridy = 0;
		add(etiquetaTitulo, gbc_etiquetaTitulo);
		
		JButton botonAbrirImagen = new JButton("Abrir Imagen");
		GridBagConstraints gbc_botonAbrirImagen = new GridBagConstraints();
		gbc_botonAbrirImagen.insets = new Insets(0, 0, 5, 5);
		gbc_botonAbrirImagen.gridx = 1;
		gbc_botonAbrirImagen.gridy = 1;
		add(botonAbrirImagen, gbc_botonAbrirImagen);
		
		JButton botonConvertir = new JButton("Convertir");
		
		GridBagConstraints gbc_botonConvertir = new GridBagConstraints();
		gbc_botonConvertir.insets = new Insets(0, 0, 5, 5);
		gbc_botonConvertir.gridx = 3;
		gbc_botonConvertir.gridy = 1;
		add(botonConvertir, gbc_botonConvertir);
		
		JLabel imagenOriginal = new JLabel();
		GridBagConstraints gbc_imagenOriginal = new GridBagConstraints();
		gbc_imagenOriginal.gridheight = 2;
		gbc_imagenOriginal.insets = new Insets(0, 0, 0, 5);
		gbc_imagenOriginal.fill = GridBagConstraints.BOTH;
		gbc_imagenOriginal.gridx = 1;
		gbc_imagenOriginal.gridy = 2;
		add(imagenOriginal, gbc_imagenOriginal);
		
		JLabel imagenComparada = new JLabel();
		GridBagConstraints gbc_imagenComparada = new GridBagConstraints();
		gbc_imagenComparada.gridheight = 2;
		gbc_imagenComparada.insets = new Insets(0, 0, 0, 5);
		gbc_imagenComparada.gridx = 2;
		gbc_imagenComparada.gridy = 2;
		add(imagenComparada, gbc_imagenComparada);
		
		
		
		JLabel imagenResultado = new JLabel();
		GridBagConstraints gbc_imagenResultado = new GridBagConstraints();
		gbc_imagenResultado.gridheight = 2;
		gbc_imagenResultado.insets = new Insets(0, 0, 0, 5);
		gbc_imagenResultado.fill = GridBagConstraints.BOTH;
		gbc_imagenResultado.gridx = 3;
		gbc_imagenResultado.gridy = 2;
		add(imagenResultado, gbc_imagenResultado);
		
		ventana.getJMenuBar().setVisible(true);
		ventana.getJMenuBar().getComponents()[0].setVisible(false);
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);//Poner en ventana completa
		
		botonAbrirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser elegir=new JFileChooser();
				FileNameExtensionFilter filter=
				new FileNameExtensionFilter("JPEG,GIF,PNG and TXT","jpg", "gif","png","txt");
				elegir.setFileFilter(filter);
				int opcionElegida=elegir.showOpenDialog(ventana);
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
							(ventana, "archivo no encontrado",
									"archivo no encontrado",
									JOptionPane.ERROR_MESSAGE);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
					}
					//El fichero es una imagen.
					try {
						BufferedImage aux=ImageIO.read(archivoCogido);
						 imagen=ImageUtils.toBufferedImage(aux.getScaledInstance(imagenOriginal.getWidth(), imagenOriginal.getHeight(), Image.SCALE_SMOOTH));
						//imagen.getGraphics().drawImage(image, imagenOriginal.getWidth(), imagenOriginal.getHeight() , null);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(ventana, "No es una imagen", "No es una imagen", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					imagenOriginal.setIcon(new ImageIcon(imagen));
					imagenOriginal.setVisible(false);
					imagenOriginal.setVisible(true);
					
		}}});
		botonConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					imagenSobel= new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics g = imagenSobel.createGraphics();
					g.drawImage(imagen, 0, 0, null);
					imagenResultado.setIcon(new ImageIcon(ImageUtils.sobel(imagenSobel)));
					Image image=imagenSobel.getScaledInstance(imagenOriginal.getWidth(), imagenOriginal.getHeight(), Image.SCALE_SMOOTH);
					imagenResultado.getGraphics().drawImage(image, 0, 0 , null);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	public static void noEsCara(Ventana ventana,BufferedImage imagenSobel) {
		int respuestaAñadirImagen=JOptionPane.showConfirmDialog(ventana,"No se encuentra coincidencia \n ¿Quieres añadir la imagen al registro?", "Añadir imagen",JOptionPane.YES_NO_OPTION);
		if(respuestaAñadirImagen==1) {
			
		}else if(respuestaAñadirImagen==0) {
			int esCara=JOptionPane.showConfirmDialog(ventana, "¿Es una cara?","¿Es una cara?",JOptionPane.YES_NO_OPTION);
			if(esCara==1) {
				
				Conexion.realizarInsertImagen(ImageUtils.imagenAtexto(imagenSobel), false, null);
				
			}else if(esCara==0) {
				int existeFicha=JOptionPane.showConfirmDialog(ventana, "¿Tiene ficha creada dentro de la base de datos?","¿Tiene ficha?",JOptionPane.YES_NO_OPTION);
				if(existeFicha==1) {
				}else if(existeFicha==0) {
					Conexion.realizarInsertImagen(ImageUtils.imagenAtexto(imagenSobel), true, 
								JOptionPane.showInputDialog(ventana, "Introduce el DNI \n Compruebe que es correcto","Vincular"));
				}
				
			}
			
		}
	}
	
}
