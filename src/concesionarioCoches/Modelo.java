package concesionarioCoches;

/**
 * Enumeración de modelos de coches. . Se limitarán los modelos de coches a
 * siete: Córdoba (marca Seat),Toledo (marca Seat),Ibiza (marca Seat), Serie 1
 * (marca BMW), Serie 2 (marca BMW),Serie 3 (marca BMW) y Serie 5 (marca BMW).
 * 
 * @author Pablo Durán
 *
 */
public enum Modelo {
	/**
	 * Modelo de un coche, primer elemento de la enumeracion
	 */
	SERIE1(Marca.BMW),
	/**
	 * Modelo de un coche, segundo elemento de la enumeracion
	 */
	SERIE2(Marca.BMW),
	/**
	 * Modelo de un coche, tercer elemento de la enumeracion
	 */
	SERIE3(Marca.BMW),
	/**
	 * Modelo de un coche, cuarto elemento de la enumeracion
	 */
	SERIE5(Marca.BMW),
	/**
	 * Modelo de un coche, quinto elemento de la enumeracion
	 */
	CORDOBA(Marca.SEAT),
	/**
	 * Modelo de un coche, sexto elemento de la enumeracion
	 */
	IBIZA(Marca.SEAT),
	/**
	 * Modelo de un coche, setptimo y último elemento de la enumeracion
	 */
	TOLEDO(Marca.SEAT);
	private Marca marca;

	/**
	 * Constructor de la enumeración
	 * 
	 * @param marca
	 */
	private Modelo(Marca marca) {
		this.marca = marca;
	}

	/**
	 * Devuelve la marca del modelo
	 * 
	 * @return
	 */
	public Marca getMarca() {
		return marca;
	}

	public String toString() {
		return name() + ", " + getMarca();

	}

	// Para el menú-------------------------------------------------
	private static final Modelo[] VALUES = Modelo.values();

	/**
	 * Genera un un menú con los elementos de la enumeración como opciones
	 * 
	 * @return menú con opciones
	 */
	static String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[VALUES.length + 1];
		int i = 0;
		for (Modelo modelo : VALUES) {
			opcionesMenu[i++] = modelo.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}

	/**
	 * Devuelve un array con los modelos de los coches
	 * 
	 * @return array
	 */
	public static Modelo[] getValues() {
		return VALUES;
	}
	// -------------------------------------------------

}
