package Principal;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageUtils {
	public static String imagenAtexto(BufferedImage im) {
		String ret="";
		for (int i = 0; i < im.getWidth(); i++) {
			for (int j = 0; j < im.getHeight(); j++) {
				ret+=im.getRGB(i, j)+",";
			}
			ret+="-";
		}
		return ret;
	}
	
	public static BufferedImage textoAImagen(String image) {
		String[] filas=image.split("-");
		String[][] imagen =new String[filas.length][];
		for (int i = 0; i < filas.length; i++) {
			imagen[i]=filas[i].split(",");
		}
		BufferedImage ret=new BufferedImage(imagen.length,imagen[0].length,BufferedImage.TYPE_INT_RGB);
		
		return ret;
	}
	public static String metodoHarris(Image imagen) {
		return "";
	}
}
