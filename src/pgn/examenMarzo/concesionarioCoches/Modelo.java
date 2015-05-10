package pgn.examenMarzo.concesionarioCoches;

import pgn.examenMarzo.utiles.Menu;

/**
 * Enum de Modelos
 * @author Pablo Durán
 *
 */
public enum Modelo {
	CORDOBA("Seat"), TOLEDO("Seat"), IBIZA("Seat"), SERIE1("BMW"), SERIE2("BMW"), SERIE3(
			"BMW"), SERIE5("BMW");

	private String casa;
	private static Menu menuModelo = new Menu("**Modelo Coche", new String[] {
			"CORDOBA", "TOLEDO", "IBIZA", "SERIE1", "SERIE2", "SERIE3",
			"SERIE5" });

	Modelo(String casa) {
		this.casa = casa;
	}
	
	String getCasa(){
		return casa;
	}

	/**
	 * Método que recoge la opción de un menú, la comprara con los elementos de
	 * la enumeracion y devuelve un valor de la enumeracion
	 * 
	 * @return
	 */
	public static Modelo pedirModelo() {
		int opcion = menuModelo.gestionar();
		for (Modelo modelo : Modelo.values()) {
			if (opcion == (modelo.ordinal() + 1))
				return modelo;
		}
		return null;
	}

}
