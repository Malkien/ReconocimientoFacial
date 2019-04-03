package Personas;

public abstract class Persona {
	private String nombre;
	private String apellidos;
	private String dni;
	private int telefono;
	private String email;
	private Cara cara;//implementar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private byte nivelConfidencialidad;//nivel de confidencialidad que hay que tener para que se pueda saber su identidad despues del un escaner coincidente
	/**
	 * Constructor de Persona
	 * @param nombre El nombre
	 * @param apellidos Los apellidos
	 * @param dni El DNI
	 * @param telefono El teléfono
	 * @param email El email
	 */
	public Persona(String nombre, String apellidos, String dni, int telefono, String email, byte nivelConfidencialidad) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		setDni(dni);
		this.telefono = telefono;
		setEmail(email);
	}
	/**
	 * Getter de nombre
	 * @return devuelve el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setter de nombre
	 * @param nombre 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Getter de Apellidos
	 * @return devuelve los apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * Setter de Apellidos
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * Getter de dni
	 * @return devuelve en dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * Setter de dni. Si la longitud del dni no es igual a 9 le da valor nulo
	 * @param dni Ten en cuenta que tiene que tener 8 numeros y 1 letra.
	 */
	public void setDni(String dni) {
		if(dni.length()!=9) {
			//Poner excepcion
		}else {
			this.dni=compruebaDni(dni);
		}
	}
	/**
	 * Comprueba si el dni introducido es valido, comprobando la coerencia con la letra.
	 * Ten en cuenta de que todas las posiciones excepto la última son números.
	 * @param dni El DNI
	 * @return devuelve el dni comprobado
	 */
	private String compruebaDni(String dni) {
		String letras="TRWAGMYFPDXBNJZSQVHLCKET";
		String numeros="";
		for (int i = 0; i < dni.length()-1; i++) {
			numeros+=dni.charAt(i);
		}
		if((dni.charAt(8)+"").equalsIgnoreCase(letras.charAt(Integer.parseInt(numeros)%23)+"")) {
			return dni;
		}
		return "ErrorAlPonerDNI";
	}
	/**
	 * Getter de teléfono
	 * @return devuelve el teléfono
	 */
	public int getTelefono() {
		return telefono;
	}
	/**
	 * Setter de teléfono
	 * @param telefono 
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	/**
	 * Getter de Email
	 * @return devuelve el email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter de Email.
	 * Comprueba que el email contenga un arroba.
	 * @param email
	 */
	public void setEmail(String email) {
		boolean tieneArroba=false;
		for (int i = 0; i < email.length(); i++) {
			if(email.charAt(i)=='@') {
				tieneArroba=true;
			}
		}
		if(tieneArroba==true) {
			this.email = email;
		}else {
			this.email="";
		}
	}
	/**
	 * Getter de nivelConfidencialidad
	 * @return devuelve nuvelConfidencialidad
	 */
	public byte getNivelConfidencialidad() {
		return nivelConfidencialidad;
	}
	/**
	 * Setter de nivelConfidencialidad
	 * @param nivelConfidencialidad
	 */
	public void setNivelConfidencialidad(byte nivelConfidencialidad) {
		if(nivelConfidencialidad>0&&nivelConfidencialidad<=5) {
			this.nivelConfidencialidad = nivelConfidencialidad;
		}else {
			this.nivelConfidencialidad=0;
		}
	}
	
}
