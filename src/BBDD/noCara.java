package BBDD;

import java.io.File;

public class noCara extends ElementoIdentificacion{
	private String objeto;
	/**
	 * Constructor de la Clase
	 * @param imagenCara La imagen de la Cara
	 * @param mapaDeBits El mapa de bits
	 * @param objeto Que objeto es
	 */
	public noCara(File imagenCara, File mapaDeBits, String objeto) {
		super(imagenCara, mapaDeBits);
		this.objeto = objeto;
	}
	/**
	 * Getter de objeto
	 * @return devuelve objeto
	 */
	public String getObjeto() {
		return objeto;
	}
	/**
	 * Setter de objeto
	 * @param objeto
	 */
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	
}
