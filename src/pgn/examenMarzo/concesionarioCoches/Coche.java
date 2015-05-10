package pgn.examenMarzo.concesionarioCoches;

import java.util.regex.Pattern;

/**
 * Clase Coche
 * @author Pablo Durán
 *
 */
public class Coche {

	private static final Pattern PATTERN_MATRICULA=Pattern.compile("^([\\d]){4}"
			+ "[- ]?"
			+ "([A-Z&&[^AEIOUÑQ]]){3}");
	Color color;
	Modelo modelo;
	String Matricula;
	
	/**
	 * Constructor de la Clase Coche
	 * @param color
	 * @param modelo
	 * @param matricula
	 */
	public Coche(Color color, Modelo modelo, String matricula) {
	setColor(color);
	setModelo(modelo);
	setMatricula(matricula);
	}
	
	/**
	 * Constructor de la Clase Coche solo con matrícula
	 * @param matricula
	 */
	public Coche(String matricula){
		setMatricula(matricula);
	}

	/**
	 * Método que instancia un coche si los valores no son nulos
	 * @param color
	 * @param modelo
	 * @param matricula
	 * @return
	 */
	static Coche instanciarCoche(Color color, Modelo modelo, String matricula){
		if(color==null||modelo==null|| matricula==null||!(Coche.validarMatricula(matricula))){
			return null;
		}
		else{
			return new Coche(color, modelo, matricula);
		}
	}
	
	/**
	 * @return the color
	 */
	Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	private void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the modelo
	 */
	private Modelo getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	private void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the matricula
	 */
	String getMatricula() {
		return Matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	private void setMatricula(String matricula) {
			Matricula = matricula;
	}

	private static boolean validarMatricula(String matricula) {
		return PATTERN_MATRICULA.matcher(matricula).matches();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Matricula == null) ? 0 : Matricula.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (Matricula == null) {
			if (other.Matricula != null)
				return false;
		} else if (!Matricula.equals(other.Matricula))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coche [color=" + color + ", modelo="  +modelo +", "+modelo.getCasa()+", Matricula="
				+ Matricula + "]";
	}
	
	
	
}
