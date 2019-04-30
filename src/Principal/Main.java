package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import Visual.Login;

public class Main {

	public static void main(String[] args) {
            Connection conexion=null;
            try {
            	conexion=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/reconocimientobbdd","root", "");
            	
            }catch(SQLException ex) {
            	ex.printStackTrace();
            	System.err.println("SQL ERROR");
            }

    		Login login=new Login();
    		login.setVisible(true);
            
            
	}
        
        
	public static void guardarImagen(String ruta, Connection conexion) throws Exception{
		 
		
	}
}
