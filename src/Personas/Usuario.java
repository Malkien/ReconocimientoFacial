package Personas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import Excepciones.PuestoException;
import Personas.Usuario.Seguridad;

public final class Usuario{
	
	private Seguridad puesto;
	private String nombreUsuario;
	private String contrasena;
	private FichaPersonal identidad;
	private byte nivelSeguridad;
	public enum Seguridad{
		ADMINISTRAR,
		RECONOCER,
		ENTRENAR
	};
	
	/**
	 * Constructor de la Clase usuario
	 * @param nivelSeguridad Hasta el nivel de seguridad al que puede acceder
	 * @param numeroPuesto El numero del puesto de trabajo
	 * @param nombreUsuario El nombre de Usuario para loguearse
	 * @param contrasena La contrasena
	 * @param identidad La persona con la que se corresponde en la BBDD
	 * @throws PuestoException 
	 */
	public Usuario(byte nivelSeguridad, String puesto, String nombreUsuario, String contrasena, FichaPersonal identidad) throws PuestoException {
		setNivelSeguridad(nivelSeguridad);
		setPuesto(puesto);
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
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
	 * Comprueba que el numero este entre 0 o 5, si no te coge como default el nivel 0. 
	 * @param nivelSeguridad
	 */
	public final void setNivelSeguridad(byte nivelSeguridad) {
		char a='s';
		
		if(nivelSeguridad>0&&nivelSeguridad<=5) {
			this.nivelSeguridad = nivelSeguridad;
		}else {
			this.nivelSeguridad=0;
		}
	}
	/**
	 * Getter de Puesto
	 * @return devuelve Puesto
	 */
	public Seguridad getPuesto() {
		return puesto;
	}
	/**
	 * Setter de Puesto
	 * @param Puesto
	 * @throws PuestoException 
	 */
	public final void setPuesto(String puesto) throws PuestoException {
		switch(puesto) {
	    	case "ADMINISTRAR":
	    		this.puesto=Seguridad.ADMINISTRAR;
	    	break;	
	    	case "RECONOCER":
	    		this.puesto=Seguridad.RECONOCER;
	    	break;
	    	case "ENTRENAR":
	    		this.puesto=Seguridad.ENTRENAR;
			break;
			default:
				throw new PuestoException();
		}
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
	 * Getter de contrasena
	 * @return devuelve la contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}
	/**
	 * Setter de contraseña
	 * @param contraseña
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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
