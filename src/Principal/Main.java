package Principal;
import java.io.File;
import Personas.*;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Usuario pepe=new Usuario("Pepe", "Pelayo, ", "77290698f", 698745123, "asdada@aasd", (byte)8, (byte)125, "PepePe", "pepepe1",new File("asd"));
		System.out.println(pepe.getNivelSeguridad());
	}

}
