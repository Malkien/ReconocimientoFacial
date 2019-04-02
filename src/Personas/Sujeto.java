package Personas;

public class Sujeto extends Persona{
	byte nivelConfidencialidad;//nivel de confidencialidad que hay que tener para que se pueda saber su identidad despues del un escaner coincidente
	/**
	 * Constructor de Sujeto
	 * @param nombre El nombre
	 * @param apellidos Los apellidos
	 * @param dni El DNI
	 * @param telefono El teléfono
	 * @param email El email
	 * @param nivelConfidencialidad El nivel de confidencialidad
	 */
	public Sujeto(String nombre, String apellidos, String dni, int telefono, String email,
			byte nivelConfidencialidad) {
		super(nombre, apellidos, dni, telefono, email);
		this.nivelConfidencialidad = nivelConfidencialidad;
	}
	/**
	 * Getter de nivelConfidencialidad
	 * @return devuelve nuvelConfidencialidad
	 */
	public byte getNivelConfidencialidad() {
		return nivelConfidencialidad;
	}
	/**
	 * Setter de nivelConfidencialidad
	 * @param nivelConfidencialidad
	 */
	public void setNivelConfidencialidad(byte nivelConfidencialidad) {
		if(nivelConfidencialidad>0&&nivelConfidencialidad<=5) {
			this.nivelConfidencialidad = nivelConfidencialidad;
		}else {
			this.nivelConfidencialidad=0;
		}
	}
	
}
