package BBDD;

import java.io.File;

public abstract class ElementoIdentificacion {
	private File imagenCara;
	private File mapaDeBits;
	/**
	 * Constructor de la Clase
	 * @param imagenCara imagen de la cara
	 * @param mapaDeBits imagen del contorno y rasgos fuertes
	 */
	public ElementoIdentificacion(File imagenCara, File mapaDeBits) {
		this.imagenCara = imagenCara;
		this.mapaDeBits = mapaDeBits;
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
	/**
	 * Getter de mapaDeBits
	 * @return devuelve mapaDeBits
	 */
	public File getMapaDeBits() {
		return mapaDeBits;
	}
	/**
	 * Setter de mapaDeBits
	 * @param mapaDeBits
	 */
	public void setMapaDeBits(File mapaDeBits) {
		this.mapaDeBits = mapaDeBits;
	}
	
}
