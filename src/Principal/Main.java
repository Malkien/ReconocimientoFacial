package Principal;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Personas.*;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
            BufferedImage c1 = null;
            try {
            	File rutaImagen=new File("./koala.jpg");
            	c1=ImageIO.read(rutaImagen);
            }catch(Exception ex) {
            	System.err.println("Error al leer la imagen");
            }
            FichaPersonal p1=new FichaPersonal("asd", "asds", "77154258f",658741587,"asdad",c1,(byte)8,"av.PPP");
            Usuario usu1=new Usuario((byte)2, (byte)123, "sdfas", "asda", p1);
            System.out.println(usu1.getNivelSeguridad()+" "+p1.getDni()+" "+p1.getEmail()+" "+p1.getNivelConfidencialidad());
	}
        public static void CrearPersona(){
            
        }

}
