package pgn.examenMarzo.concesionarioCoches;

import pgn.examenMarzo.utiles.Menu;

/**
 * Enum Color
 * @author Pablo Durán
 *
 */
public enum Color {
	PLATA,
	ROJO,
	AZUL;
	
	static Menu menuColor=new Menu("**Color Coche",new String[]{"PLATA","ROJO","AZUL","Salir"});
	
	public static Color pedirColor(){
		int opcion=menuColor.gestionar();
		for (Color color : Color.values()) {
			if(opcion==color.ordinal()+1){
				return color;
			}
		}
		return null;

	}
}
