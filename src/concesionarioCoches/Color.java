package concesionarioCoches;

/**
 * Se limitar�n los colores a tres: plata, rojo y azul. Para solicitar el color
 * al dar de alta al coche podr� implementarse un m�todo pedirColor que mediante
 * la gesti�n de un men�, devolver� el color indicado
 * 
 * @author Pablo Dur�n
 * 
 */
public enum Color {
	/**
	 * Color de coche, primer elemento de la enumeraci�n
	 */
	PLATA,
	/**
	 * Color de coche, segundo elemento de la enumeraci�n
	 */
	ROJO,
	/**
	 * Color de coche, tercer elemento de la enumeraci�n
	 */
	AZUL;

	private static final Color[] VALUES = Color.values();

	/**
	 * Genera un un men� con los elementos de la enumeraci�n como opciones
	 * 
	 * @return men� con las opciones
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
	 * Devuelve un array con los elementos de la enumeraci�n
	 * @return array
	 */
	public static Color[] getValues() {
		return VALUES;
	}

}
