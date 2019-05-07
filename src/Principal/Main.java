package Principal;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Personas.FichaPersonal;
import Visual.Login;
import Visual.Ventana;

public class Main {

	public static void main(String[] args) {
            Connection conexion=null;
            try {
            	conexion=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/reconocimientobbdd","root", "");
            	
            	/*
            	ArrayList<BufferedImage> imagenes=new ArrayList<BufferedImage>();
            	imagenes.add(new BufferedImage(20,20,BufferedImage.TYPE_INT_RGB));
            	
            	try {
					FichaPersonal fichaUsuario=new FichaPersonal("nombre",
							"apellidos",
							"dni",
							123123,
							"emai@.l",
							imagenes,
							(byte)1,
							"asdasd");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
            	
            }catch(SQLException ex) {
            	ex.printStackTrace();
            	System.err.println("SQL ERROR");
            }
            Ventana ventana=new Ventana();
    		ventana.setVisible(true);
            
            
	}
        
        
	public static void guardarImagen(String ruta, Connection conexion) throws Exception{
		 
		
	}
}
