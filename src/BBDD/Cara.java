package BBDD;

import java.io.File;

public class Cara extends ElementoIdentificacion{
	private int colorOjos;
	/**
	 * Constructor de la Clase
	 * @param imagenCara La imagen de la Cara
	 * @param mapaDeBits El mapa de bits
	 * @param colorOjos El color de los ojos que tiene
	 */
	public Cara(File imagenCara, File mapaDeBits, int colorOjos) {
		super(imagenCara, mapaDeBits);
		this.colorOjos = colorOjos;
	}
	/**
	 * Getter de colorOjos
	 * @return devuelve colorOjos
	 */
	public int getColorOjos() {
		return colorOjos;
	}
	/**
	 * Setter de colorOjos
	 * @param colorOjos
	 */
	public void setColorOjos(int colorOjos) {
		this.colorOjos = colorOjos;
	}
	
}
