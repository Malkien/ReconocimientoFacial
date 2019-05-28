package Excepciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class SuperException extends Exception{
	public SuperException(String txt) {
		super(txt);
		FileWriter logAbsolute=null;
		BufferedWriter logEscribir=null;
		try {
			File log=new File("./log/errorlog.txt");
			if(!log.exists()) {
				log.createNewFile();
			}
			logAbsolute=new FileWriter(log.getAbsoluteFile(), true);
			logEscribir=new BufferedWriter(logAbsolute);
			logEscribir.write(LocalDateTime.now()+"\t"+txt);
			logEscribir.newLine();
			logEscribir.flush();
		}catch(Exception e) {
			
		}finally {
			try {
				if(logAbsolute!=null) {
					logAbsolute.close();
				}
				if(logEscribir!=null) {
					logEscribir.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
