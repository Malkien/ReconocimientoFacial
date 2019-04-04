package Personas;

public class Usuario{
	private byte nivelSeguridad;
	private byte numeroPuesto;
	private String nombreUsuario;
	private String contraseña;
	private FichaPersonal identidad;
	/**
	 * Constructor de la Clase usuario
	 * @param nivelSeguridad Hasta el nivel de seguridad al que puede acceder
	 * @param numeroPuesto El numero del puesto de trabajo
	 * @param nombreUsuario El nombre de Usuario para loguearse
	 * @param contraseña La contraseña
	 * @param identidad La persona con la que se corresponde en la BBDD
	 */
	public Usuario(byte nivelSeguridad, byte numeroPuesto, String nombreUsuario, String contraseña, FichaPersonal identidad) {
		setNivelSeguridad(nivelSeguridad);
		this.numeroPuesto = numeroPuesto;
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.identidad = identidad;
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
	 * Getter de identidad
	 * @return devuelve identidad
	 */
	public FichaPersonal getIdentidad() {
		return identidad;
	}
	/**
	 * Setter de identidad
	 * @param identidad
	 */
	public void setIdentidad(FichaPersonal identidad) {
		this.identidad = identidad;
	}
}
