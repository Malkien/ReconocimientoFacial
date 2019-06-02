package Visual;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Componentes.BotonDefault;
import Componentes.Etiqueta;
import Excepciones.PreparedStatementException;
import Personas.FichaPersonal;
import Personas.Usuario;
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

import java.awt.Color;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class Reconocimiento extends JPanel{
	private File archivoCogido;
	private JTextArea areaTexto;
	private Reconocimiento thisRef;
	private BufferedImage imagen;
	private Map<Integer,BufferedImage> listadoImagenes=new HashMap<Integer,BufferedImage>();
	private BufferedImage imagenSobel;
	private FichaPersonal personaSimilitud;
	private Ventana ventana;
	private Usuario usuario;
	public Reconocimiento(Ventana ventana,EleccionPantalla eleccion,Usuario usuario) {
		this.ventana=ventana;
		thisRef=this;
		this.usuario=usuario;
		setBackground(new Color(173, 169, 183));
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);//Poner en ventana completa
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
		
		BotonDefault botonAbrirImagen = new BotonDefault("Abrir Imagen");
		GridBagConstraints gbc_botonAbrirImagen = new GridBagConstraints();
		gbc_botonAbrirImagen.insets = new Insets(0, 0, 5, 5);
		gbc_botonAbrirImagen.gridx = 1;
		gbc_botonAbrirImagen.gridy = 1;
		add(botonAbrirImagen, gbc_botonAbrirImagen);
		
		BotonDefault botonComparar = new BotonDefault("Buscar");
		
		GridBagConstraints gbc_botonComparar = new GridBagConstraints();
		gbc_botonComparar.insets = new Insets(0, 0, 5, 5);
		gbc_botonComparar.gridx = 2;
		gbc_botonComparar.gridy = 1;
		add(botonComparar, gbc_botonComparar);
		
		BotonDefault botonConvertir = new BotonDefault("Convertir");
		
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
						 imagen=ImageUtils.resize(ImageUtils.toBufferedImage(aux.getScaledInstance(imagenOriginal.getWidth(), imagenOriginal.getHeight(), Image.SCALE_SMOOTH)));
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
					PreparedStatement selectImagen=Conexion.creaPreparedStatement("SELECT * FROM imagen");
					ResultSet selectResultados=selectImagen.executeQuery();
					while(selectResultados.next()) {
						//Para cada imagen del array: Convertirla en bufferedImage
						listadoImagenes.put(selectResultados.getInt("id"),ImageUtils.textoAImagen(selectResultados.getString("imagen")));
					}
					selectImagen.close();
					selectResultados.close();
				}catch(SQLException e) {
					
				}
				float porcentaje=95;
				BufferedImage imagenSimilar = null;
				int idImagenSimilar=0;
				float porcParecido=0;
				Iterator it=listadoImagenes.entrySet().iterator();

				while(it.hasNext()) {
					Map.Entry<Integer,BufferedImage> actual = (Entry<Integer, BufferedImage>) it.next();
					try {
						porcParecido = ImageUtils.compareImage2(imagenSobel, actual.getValue());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(porcParecido>porcentaje) {
						System.out.println(porcParecido);
						porcentaje=porcParecido;
						imagenSimilar = actual.getValue();
						idImagenSimilar= actual.getKey();
					}
				}
				
				//Comprobaciones de la imagen
				if(imagenSimilar!=null) {
					try {
						PreparedStatement imagenEncontrada=Conexion.creaPreparedStatement("SELECT * FROM imagen WHERE id = ?");
						imagenEncontrada.setInt(1, idImagenSimilar);
						ResultSet imagenResultado=imagenEncontrada.executeQuery();
						
						if(imagenResultado.next()&&imagenResultado.getBoolean("cara")) {
							PreparedStatement buscarPersona=Conexion.creaPreparedStatement("SELECT * FROM fichapersonal WHERE dni=?");
							buscarPersona.setString(1, imagenResultado.getString("fichapersonal"));
							ResultSet encontradaPersona=buscarPersona.executeQuery();
							encontradaPersona.next();
							int seguridad=encontradaPersona.getInt("nivelConfidencialidad");
							if(seguridad<=usuario.getNivelSeguridad()) {
								String nombre=encontradaPersona.getString("nombre");
								String dni=encontradaPersona.getString("dni");
								int correcto=JOptionPane.showConfirmDialog(ventana,"Después del analisis hemos deducido que es "+nombre+" con DNI: "+dni, "Coincidencia",JOptionPane.YES_NO_OPTION);
								if(correcto==0) {

									JFrame ventanaFicha= new JFrame();
									MostrarFicha mostrarFicha=new MostrarFicha(
											nombre,
											encontradaPersona.getString("apellidos"),
											dni,
											encontradaPersona.getInt("telefono"),
											seguridad,
											encontradaPersona.getString("direccion"),
											encontradaPersona.getString("email")
											);
									ventanaFicha.setSize(400, 400);
									ventanaFicha.setLocationRelativeTo(null);
									ventanaFicha.getContentPane().add(mostrarFicha);
									ventanaFicha.setVisible(true);
									//Conexion.sacarDatosFicha(imagenResultado.getString("id"), ventana,usuario);
									
								}
							}else {
								JOptionPane.showConfirmDialog(ventana,"Persona Coincidente Confidencial para tu rango", "Nivel insuficiente",JOptionPane.YES_NO_OPTION);
							}
							
						}else {
							JOptionPane.showMessageDialog(ventana, "No es una cara","Coincidencia sin Ficha",JOptionPane.OK_OPTION);
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
					JOptionPane.showMessageDialog(ventana, "No hay coincidencia con la BBDD","Sin coincidencia",JOptionPane.OK_OPTION);
				
				}
				//Comparas pixel a pixel con la actual y miras el porcentaje de pixels que son blancos o gris claro en las dos imágenes (el mismo px en las dos imagenes) 
				//De eso, te sale un porcentaje de pixels que son blancos en las dos.
				//Guardas la imagen con el porcentaje mayor que encuentras y estimas que la imagen que tu le has pasaado
				//es lo mismo (cara o no) que la imagen a la que más se parece
				//Se lo preguntas al usuario : Creo que es una cara ¿Acierto? Tu le dices si o no , y guardas eso en base de datos con el es cara o no es cara.
				
			}
		});
	}
	
}