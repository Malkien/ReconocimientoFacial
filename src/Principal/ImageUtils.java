package Principal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;



public class ImageUtils {

	public static BufferedImage sobel(BufferedImage image) throws IOException {
	

        int x = image.getWidth();
        int y = image.getHeight();

        int maxGval = 0;
        int[][] edgeColors = new int[x][y];
        int maxGradient = -1;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                int val00 = getGrayScale(image.getRGB(i - 1, j - 1));
                int val01 = getGrayScale(image.getRGB(i - 1, j));
                int val02 = getGrayScale(image.getRGB(i - 1, j + 1));

                int val10 = getGrayScale(image.getRGB(i, j - 1));
                int val11 = getGrayScale(image.getRGB(i, j));
                int val12 = getGrayScale(image.getRGB(i, j + 1));

                int val20 = getGrayScale(image.getRGB(i + 1, j - 1));
                int val21 = getGrayScale(image.getRGB(i + 1, j));
                int val22 = getGrayScale(image.getRGB(i + 1, j + 1));

                int gx =  ((-1 * val00) + (0 * val01) + (1 * val02)) 
                        + ((-2 * val10) + (0 * val11) + (2 * val12))
                        + ((-1 * val20) + (0 * val21) + (1 * val22));

                int gy =  ((-1 * val00) + (-2 * val01) + (-1 * val02))
                        + ((0 * val10) + (0 * val11) + (0 * val12))
                        + ((1 * val20) + (2 * val21) + (1 * val22));

                double gval = Math.sqrt((gx * gx) + (gy * gy));
                int g = (int) gval;

                if(maxGradient < g) {
                    maxGradient = g;
                }

                edgeColors[i][j] = g;
            }
        }

        double scale = 255.0 / maxGradient;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

                image.setRGB(i, j, edgeColor);
            }
        }

       return image;
    
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

public static int  getGrayScale(int rgb) {
    int r = (rgb >> 16) & 0xff;
    int g = (rgb >> 8) & 0xff;
    int b = (rgb) & 0xff;

    //from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
    int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
    //int gray = (r + g + b) / 3;

    return gray;
}

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
		
		for(int i=0;i<ret.width();i++){
			for(int j=0;j<ret.height();j++){
					ret.setRGB(i,j,imagen[i][j]);
			}
		}
		
		return ret;
	}
	public static String metodoHarris(Image imagen) {
		return "";
	}
	
	public static void Harris(Mat Scene, Mat Object, int thresh) {

	    // This function implements the Harris Corner detection. The corners at intensity > thresh
	    // are drawn.
	    Mat Harris_scene = new Mat();
	    Mat Harris_object = new Mat();

	    Mat harris_scene_norm = new Mat(), harris_object_norm = new Mat(), harris_scene_scaled = new Mat(), harris_object_scaled = new Mat();
	    int blockSize = 9;
	    int apertureSize = 5;
	    double k = 0.1;
	    Imgproc.cornerHarris(Scene, Harris_scene,blockSize, apertureSize,k);
	    Imgproc.cornerHarris(Object, Harris_object, blockSize,apertureSize,k);

	    Core.normalize(Harris_scene, harris_scene_norm, 0, 255, Core.NORM_MINMAX, CvType.CV_32FC1, new Mat());
	    Core.normalize(Harris_object, harris_object_norm, 0, 255, Core.NORM_MINMAX, CvType.CV_32FC1, new Mat());

	    Core.convertScaleAbs(harris_scene_norm, harris_scene_scaled);
	    Core.convertScaleAbs(harris_object_norm, harris_object_scaled);

	    for( int j = 0; j < harris_scene_norm.rows() ; j++){
	        for( int i = 0; i < harris_scene_norm.cols(); i++){
	            if ((int) harris_scene_norm.get(j,i)[0] > thresh){
	                Imgproc.circle(harris_scene_scaled, new Point(i,j), 5 , new Scalar(0), 2 ,8 , 0);
	            }
	        }
	    }

	    for( int j = 0; j < harris_object_norm.rows() ; j++){
	        for( int i = 0; i < harris_object_norm.cols(); i++){
	            if ((int) harris_object_norm.get(j,i)[0] > thresh){
	                Imgproc.circle(harris_object_scaled, new Point(i,j), 5 , new Scalar(0), 2 ,8 , 0);
	            }
	        }
	    }
	 }
	public float compareImage(BufferedImage biA, BufferedImage biB) {

	    float percentage = 0;
	    try {
	        //take buffer data from both image files //
	        DataBuffer dbA = biA.getData().getDataBuffer();
	        int sizeA = dbA.getSize();
	        DataBuffer dbB = biB.getData().getDataBuffer();
	        int sizeB = dbB.getSize();
	        int count = 0;
	        //compare data-buffer objects //
	        if (sizeA == sizeB) {

	            for (int i = 0; i < sizeA; i++) {
	            	if (dbA.getElem(i) == dbB.getElem(i)) {
	                    count = count + 1;
	                }

	            }
	            percentage = (count * 100) / sizeA;
	        } else {
	            System.out.println("Both the images are not of same size");
	        }

	    } catch (Exception e) {
	        System.out.println("Failed to compare image files ...");
	    }
	    return percentage;
	}
	public static float compareImage2(BufferedImage biA, BufferedImage biB) throws Exception {
		float porcentaje = 0;
		System.out.println("Comparando imagenes. Tamaño A: "+biA.getWidth()+","+biA.getHeight()+" Tamaño b: "+biB.getWidth()+","+biB.getHeight());
		if(biA.getWidth()!=biB.getWidth()||biA.getHeight()!=biB.getHeight()) {
			throw new Exception();
		}
		int count = 0;
		for(int i= 0; biA.getWidth()>i;i++) {
			for(int y= 0; biA.getHeight()>y;y++) {
					if(biA.getRGB(i,y) >= new Color(200,200,200).getRGB()&&biB.getRGB(i, y)==new Color(200,200,200).getRGB()) {
							count = count + 1;
						}
					}
				
			}
		porcentaje = (count * 100) / (biA.getWidth()*biA.getHeight());
		System.out.println("Resultado: "+porcentaje);
		return porcentaje;
	}
	public static BufferedImage resize(BufferedImage bufferedImage) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(500, 500, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, 500, 500, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }
}
