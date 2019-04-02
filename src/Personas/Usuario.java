package Personas;
import java.io.File;

public class Usuario extends Persona{
	private byte nivelSeguridad;
	private byte numeroPuesto;
	private String nombreUsuario;
	private String contraseña;
	private File imagenCara;
	/**
	 * Constructor de la Clase usuario
	 * @param nombre El nombre
	 * @param apellidos Los apellidos
	 * @param dni El DNI
	 * @param telefono El telefono
	 * @param email El email
	 * @param nivelSeguridad Hasta el nivel de seguridad al que puede acceder
	 * @param numeroPuesto El numero del puesto de trabajo
	 * @param nombreUsuario El nombre de Usuario para loguearse
	 * @param contraseña La contraseña
	 * @param imagenCara Una imagen de su cara
	 */
	public Usuario(String nombre, String apellidos, String dni, int telefono, String email, byte nivelSeguridad,
			byte numeroPuesto, String nombreUsuario, String contraseña, File imagenCara) {
		super(nombre, apellidos, dni, telefono, email);
		setNivelSeguridad(nivelSeguridad);
		this.numeroPuesto = numeroPuesto;
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.imagenCara = imagenCara;
	}
	/**
	 * Getter de nivelSeguridad
	 * @return devuelve nivelSeguridad
	 */
	public byte getNivelSeguridad() {
		return nivelSeguridad;
	}
	/**
	 * Setter de nivelSeguridad
	 * Comprueba que el numero esté entre 0 o 5, si no te coge como default el nivel 0. 
	 * @param nivelSeguridad
	 */
	public void setNivelSeguridad(byte nivelSeguridad) {
		if(nivelSeguridad>0&&nivelSeguridad<=5) {
			this.nivelSeguridad = nivelSeguridad;
		}else {
			this.nivelSeguridad=0;
		}
	}
	/**
	 * Getter de numeroPuesto
	 * @return devuelve numeroPuesto
	 */
	public byte getNumeroPuesto() {
		return numeroPuesto;
	}
	/**
	 * Setter de numeroPuesto
	 * @param numeroPuesto
	 */
	public void setNumeroPuesto(byte numeroPuesto) {
		this.numeroPuesto = numeroPuesto;
	}
	/**
	 * Getter de nombreUsuario
	 * @return devuelve nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	/**
	 * Setter de nombreUsuario
	 * @param nombreUsuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;//Agregar comprobacion cuando halla bbdd para que no se pueda repetir los nombres
	}
	/**
	 * Getter de contraseña
	 * @return devuelve la contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}
	/**
	 * Setter de contraseña
	 * @param contraseña
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	/**
	 * Getter de imagenCara
	 * @return devuelve imagenCara
	 */
	public File getImagenCara() {
		return imagenCara;
	}
	/**
	 * Setter de imagenCara
	 * @param imagenCara
	 */
	public void setImagenCara(File imagenCara) {
		this.imagenCara = imagenCara;
	}
}
