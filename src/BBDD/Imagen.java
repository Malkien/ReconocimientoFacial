package BBDD;

import Personas.FichaPersonal;
import java.awt.image.BufferedImage;

public class Imagen {
	private BufferedImage imagenCara;
        private FichaPersonal fichaPersonal;
        /**
         * Constructor de la Clase
         * @param imagenCara La imagen
         * @param fichaPersonal La Ficha Personal
         */
    public Imagen(BufferedImage imagenCara, FichaPersonal fichaPersonal) {
        this.imagenCara = imagenCara;
        this.fichaPersonal = fichaPersonal;
    }
    /**
     * Getter de imagenCara
     * @return devuelve imagenCara
     */
    public BufferedImage getImagenCara() {
        return imagenCara;
    }
    /**
     * Setter de imagenCara
     * @param imagenCara 
     */
    public void setImagenCara(BufferedImage imagenCara) {
        this.imagenCara = imagenCara;
    }
    /**
     * Getter de fichaPersonal
     * @return devuelve fichaPersonal
     */
    public FichaPersonal getFichaPersonal() {
        return fichaPersonal;
    }
    /**
     * Setter de fichaPersonal
     * @param fichaPersonal 
     */
    public void setFichaPersonal(FichaPersonal fichaPersonal) {
        this.fichaPersonal = fichaPersonal;
    }
        
	
}
