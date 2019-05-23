package Visual;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Componentes.Etiqueta;
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

public class Entrenamiento extends JPanel{
	private File archivoCogido;
	private JTextArea areaTexto;
	private Entrenamiento thisRef;
	private BufferedImage imagen;
	public Entrenamiento(EleccionPantalla eleccion) {
		thisRef=this;
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
		
		JLabel imagenOriginal = new JLabel();
		GridBagConstraints gbc_imagenOriginal = new GridBagConstraints();
		gbc_imagenOriginal.gridheight = 2;
		gbc_imagenOriginal.gridwidth = 2;
		gbc_imagenOriginal.insets = new Insets(0, 0, 0, 5);
		gbc_imagenOriginal.fill = GridBagConstraints.BOTH;
		gbc_imagenOriginal.gridx = 1;
		gbc_imagenOriginal.gridy = 2;
		add(imagenOriginal, gbc_imagenOriginal);
		
		
		
		JLabel imagenResultado = new JLabel();
		GridBagConstraints gbc_imagenResultado = new GridBagConstraints();
		gbc_imagenResultado.gridheight = 2;
		gbc_imagenResultado.gridwidth = 2;
		gbc_imagenResultado.insets = new Insets(0, 0, 0, 5);
		gbc_imagenResultado.fill = GridBagConstraints.BOTH;
		gbc_imagenResultado.gridx = 3;
		gbc_imagenResultado.gridy = 2;
		add(imagenResultado, gbc_imagenResultado);
		
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
					try {
						imagen=ImageIO.read(archivoCogido);
						Image image=imagen.getScaledInstance(imagenOriginal.getWidth(), imagenOriginal.getHeight(), Image.SCALE_SMOOTH);
						imagen.getGraphics().drawImage(image, imagenOriginal.getWidth(), imagenOriginal.getHeight() , null);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(eleccion.login.ventana, "No es una imagen", "No es una imagen", JOptionPane.ERROR_MESSAGE);
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
					imagenResultado.setIcon(new ImageIcon(ImageUtils.sobel(imagen)));
					Image image=imagen.getScaledInstance(imagenOriginal.getWidth(), imagenOriginal.getHeight(), Image.SCALE_SMOOTH);
					imagenResultado.getGraphics().drawImage(image, 0, 0 , null);
					//Asegurate de que todas las imagenes en bd se han reescalado al mismo tamaño
					//coger de base de datos todas las imagenes de caras y meterlas en un array (todas sobel)
					PreparedStatement selectImagen=Conexion.creaPreparedStatement("SELECT image FROM imagen");
					ResultSet selectResultados=selectImagen.executeQuery();
					Map<Integer,BufferedImage> listadoImagenes=new HashMap<Integer,BufferedImage>();
					while(selectResultados.next()) {
						//Para cada imagen del array: Convertirla en bufferedImage
						listadoImagenes.put(selectResultados.getInt("id"),ImageUtils.textoAImagen(selectResultados.getString("imagen")));
					}
					//Comparas pixel a pixel con la actual y miras el porcentaje de pixels que son blancos o gris claro en las dos imágenes (el mismo px en las dos imagenes) 
					//De eso, te sale un porcentaje de pixels que son blancos en las dos.
					//Guardas la imagen con el porcentaje mayor que encuentras y estimas que la imagen que tu le has pasaado
					//es lo mismo (cara o no) que la imagen a la que más se parece
					//Se lo preguntas al usuario : Creo que es una cara ¿Acierto? Tu le dices si o no , y guardas eso en base de datos con el es cara o no es cara.
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	
}
