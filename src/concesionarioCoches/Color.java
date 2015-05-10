package concesionarioCoches;

/**
 * Se limitarán los colores a tres: plata, rojo y azul. Para solicitar el color
 * al dar de alta al coche podrá implementarse un método pedirColor que mediante
 * la gestión de un menú, devolverá el color indicado
 * 
 * @author Pablo Durán
 * 
 */
public enum Color {
	/**
	 * Color de coche, primer elemento de la enumeración
	 */
	PLATA,
	/**
	 * Color de coche, segundo elemento de la enumeración
	 */
	ROJO,
	/**
	 * Color de coche, tercer elemento de la enumeración
	 */
	AZUL;

	private static final Color[] VALUES = Color.values();

	/**
	 * Genera un un menú con los elementos de la enumeración como opciones
	 * 
	 * @return menú con las opciones
	 */
	public static String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[getValues().length + 1];
		int i = 0;
		for (Color color : getValues()) {
			opcionesMenu[i++] = color.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}

	/**
	 * Devuelve un array con los elementos de la enumeración
	 * @return array
	 */
	public static Color[] getValues() {
		return VALUES;
	}

}
