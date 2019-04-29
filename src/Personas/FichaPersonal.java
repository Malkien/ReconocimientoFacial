package Personas;

import java.awt.image.BufferedImage;


public class FichaPersonal{
	private String nombre;
	private String apellidos;
	private String dni;
	private String direccion;
	private int telefono;
	private String email;
	private BufferedImage imagen;
	private byte nivelConfidencialidad;//nivel de confidencialidad que hay que tener para que se pueda saber su identidad despues del un escaner coincidente
	/**
	 * Constructor de Persona
	 * @param nombre El nombre
	 * @param apellidos Los apellidos
	 * @param dni El DNI
	 * @param telefono El telefono
	 * @param email El email
     * @param imagen La imagen
     * @param nivelConfidencialidad El nivel de confidencialidad
     * @param direccion La direccion
	 */
	public FichaPersonal(String nombre, String apellidos, String dni, int telefono, String email,BufferedImage imagen, byte nivelConfidencialidad, String direccion) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		setDni(dni);
		this.telefono = telefono;
		setEmail(email);
        this.imagen=imagen;
        this.direccion=direccion;
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
            try{
		if(dni.length()!=9) {
                    throw new Exception();
		}else {
                    this.dni=compruebaDni(dni);
		}
            }catch(Exception e){
                System.err.println("Error al introducir el DNI");
            }
	}
	/**
	 * Comprueba si el dni introducido es valido, comprobando la coerencia con la letra.
	 * Ten en cuenta de que todas las posiciones excepto la ultima son numeros.
	 * @param dni El DNI
	 * @return devuelve el dni comprobado o una excepcion
	 */
	private String compruebaDni(String dni) throws Exception {
		String letras="TRWAGMYFPDXBNJZSQVHLCKET";
		String numeros="";
		for (int i = 0; i < dni.length()-1; i++) {
			numeros+=dni.charAt(i);
		}
		if((dni.charAt(8)+"").equalsIgnoreCase(letras.charAt(Integer.parseInt(numeros)%23)+"")) {
			return dni;
		}
                throw new Exception();
	}
	/**
	 * Getter de telefono
	 * @return devuelve el telefono
	 */
	public int getTelefono() {
		return telefono;
	}
	/**
	 * Setter de telefono
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
	 * Comprueba que el email contenga un arroba y un punto.
	 * @param email
	 */
	public void setEmail(String email){
		boolean tieneArroba=false;
		boolean tienePunto=false;
		for (int i = 0; i < email.length(); i++) {
                    if(email.charAt(i)=='@') {
                        tieneArroba=true;
                    }else if(email.charAt(i)=='.'){
                        tienePunto=true;
                    }
		}
                try{
                    if(tieneArroba&&tienePunto) {
                        this.email = email;
                    }else {
                        throw new Exception();
                    }
                }catch(Exception e){
                    System.err.println("Error al introducir email");
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
	/**
	 * Setter de imagen
	 * @param imagen 
	 */
	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}
	/**
	 * Getter de imagen
	 * @return devuelve la imagen
	 */
	public BufferedImage getImagen() {
		return imagen;
	}
	/**
	 * Setter de direccion
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion=direccion;
	}
	/**
	 * Getter de direccion
	 * @return devuelve la direccion
	 */
	public String getDireccion() {
		return direccion;
	}
}
