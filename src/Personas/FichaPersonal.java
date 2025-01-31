package Personas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Excepciones.DniException;
import Excepciones.EmailException;

/**
 * La Ficha Personal
 * @author malki
 *
 */
public final class FichaPersonal{
	private String nombre;//El nombre
	private String apellidos;//el apellido
	private String dni;//el dni
	private String direccion;//la direccion
	private int telefono;//el telefono
	private String email;//el email
	private byte nivelConfidencialidad;//nivel de confidencialidad que hay que tener para que se pueda saber su identidad despues del un escaner coincidente
	/**
	 * Constructor de Persona
	 * @param nombre El nombre
	 * @param apellidos Los apellidos
	 * @param dni El DNI
	 * @param telefono El telefono
	 * @param email El email
     * @param nivelConfidencialidad El nivel de confidencialidad
     * @param direccion La direccion
	 * @throws Exception 
	 */
	public FichaPersonal(String nombre, String apellidos, String dni, int telefono, String email, byte nivelConfidencialidad, String direccion) throws Exception {
		this.nombre = nombre;
		this.apellidos = apellidos;
		setDni(dni);
		this.telefono = telefono;
		setEmail(email);
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
	public final void setDni(String dni) throws DniException {
        	comprobarDni(dni);
        	this.dni=dni;
    	
	}
	/**
	 * Comprueba si el dni introducido es valido, comprobando la coerencia con la letra, y la longitud.
	 * Ten en cuenta de que todas las posiciones excepto la ultima son numeros.
	 * @param dni El DNI
	 */
	public static boolean comprobarDni(String dni) throws DniException {
		String letras="TRWAGMYFPDXBNJZSQVHLCKET";
		String numeros="";
		if(dni.length()!=9) {
            throw new DniException();
    	}else {
            for (int i = 0; i < dni.length()-1; i++) {
    			numeros+=dni.charAt(i);
    		}
    		if((dni.charAt(8)+"").equalsIgnoreCase(letras.charAt(Integer.parseInt(numeros)%23)+"")) {
    			return true;
    		}else {
                throw new DniException();
    		}
    	}
		
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
	 * @throws Exception 
	 */
	public final void setEmail(String email) throws EmailException{
		comprobarEmail(email);
		this.email=email;
		
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
	/**
	 * Comprueba si el email tiene un punto y un arroba
	 * @param email
	 * @throws EmailException
	 */
	public static void comprobarEmail(String email) throws EmailException {
		boolean tieneArroba=false;
		boolean tienePunto=false;
		for (int i = 0; i < email.length(); i++) {
            if(email.charAt(i)=='@') {
                tieneArroba=true;
            }else if(email.charAt(i)=='.'){
            	tienePunto=true;
            }
		}
        if(!tieneArroba||!tienePunto) {
            throw new EmailException();
        }
	}
}
