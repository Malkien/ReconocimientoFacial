package Visual;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;


import Componentes.Etiqueta;
import Excepciones.PreparedStatementException;
import Personas.FichaPersonal;
import Principal.Conexion;
import Principal.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

public class Reconocimiento extends JPanel{
	private File archivoCogido;
	private JTextArea areaTexto;
	private Reconocimiento thisRef;
	private BufferedImage imagen;
	private Map<Integer,BufferedImage> listadoImagenes=new HashMap<Integer,BufferedImage>();
	private BufferedImage imagenSobel;
	private FichaPersonal personaSimilitud;
	private Ventana ventana;
	public Reconocimiento(Ventana ventana,EleccionPantalla eleccion) {
		this.ventana=ventana;
		thisRef=this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Etiqueta etiquetaTitulo = new Etiqueta("Reconocimiento");
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
		
		JButton botonComparar = new JButton("Comparar");
		
		GridBagConstraints gbc_botonComparar = new GridBagConstraints();
		gbc_botonComparar.insets = new Insets(0, 0, 5, 5);
		gbc_botonComparar.gridx = 2;
		gbc_botonComparar.gridy = 1;
		add(botonComparar, gbc_botonComparar);
		
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
		botonComparar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Asegurate de que todas las imagenes en bd se han reescalado al mismo tamaño
				//coger de base de datos todas las imagenes de caras y meterlas en un array (todas sobel)
				try {
					PreparedStatement selectImagen=Conexion.creaPreparedStatement("SELECT image FROM imagen");
					ResultSet selectResultados=selectImagen.executeQuery();
					while(selectResultados.next()) {
						//Para cada imagen del array: Convertirla en bufferedImage
						listadoImagenes.put(selectResultados.getInt("id"),ImageUtils.textoAImagen(selectResultados.getString("imagen")));
					}
					selectImagen.close();
					selectResultados.close();
				}catch(SQLException e) {
					
				}
				float porcentaje=70;
				BufferedImage imagenSimilar = null;
				int idImagenSimilar=0;
				float porcParecido=0;
				
				for (int i = 0; i < listadoImagenes.size(); i++) {
					try {
						porcParecido = ImageUtils.compareImage2(imagenSobel, listadoImagenes.get(i));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(porcParecido>porcentaje) {
						porcentaje=porcParecido;
						imagenSimilar = listadoImagenes.get(i);
						idImagenSimilar=i;
					}
				}
				
				//Comprobaciones de la imagen
				if(imagenSimilar!=null) {
					try {
						PreparedStatement imagenEncontrada=Conexion.creaPreparedStatement("SELECT * FROM imagen WHERE id = id=?");
						imagenEncontrada.setInt(1, idImagenSimilar);
						ResultSet imagenResultado=imagenEncontrada.executeQuery();
						
						if(imagenResultado.next()&&imagenResultado.getBoolean("cara")) {
							
							int acertado=JOptionPane.showConfirmDialog(ventana,"Después del analisis hemos deducido que es una persona, ¿He acertado?"
									, "Coincidencia",JOptionPane.YES_NO_OPTION);
							if(acertado==0) {
								Conexion.realizarInsertImagen(ImageUtils.imagenAtexto(imagenSobel), imagenResultado.getBoolean("cara"), imagenResultado.getString("FichaPersonal"));
							}else if(acertado==1){
								noEsCara(ventana,imagenSobel);
							}
							//TODO si llega aquí cree que es una cara, y te dice quien cree que es. Le das tres opciones: No es una cara, es esta persona, no es esta persona.
							//en función de eso, metes en bd.
						}else {
							int esCara=JOptionPane.showConfirmDialog(ventana,"Según el registro no es una cara, ¿Acierto?", "Añadir imagen",JOptionPane.YES_NO_OPTION);
							if(esCara==1) {
								
							}else if(esCara==0) {
								
							}
							//Cree que no es cara, te lanza dialogo diciendo que cree que no es una cara, y te da opcion de decir si es correcto o no. Si en realidad es una cara, lanza dialogo para que lo asocie con una ficha o cree una nueva.
						}
					} catch (PreparedStatementException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						//cerrar las conexiones
					}
					
				}else {
					noEsCara(ventana,imagenSobel);
				
				}
				//Comparas pixel a pixel con la actual y miras el porcentaje de pixels que son blancos o gris claro en las dos imágenes (el mismo px en las dos imagenes) 
				//De eso, te sale un porcentaje de pixels que son blancos en las dos.
				//Guardas la imagen con el porcentaje mayor que encuentras y estimas que la imagen que tu le has pasaado
				//es lo mismo (cara o no) que la imagen a la que más se parece
				//Se lo preguntas al usuario : Creo que es una cara ¿Acierto? Tu le dices si o no , y guardas eso en base de datos con el es cara o no es cara.
				
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