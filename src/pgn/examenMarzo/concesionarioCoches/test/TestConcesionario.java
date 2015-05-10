package pgn.examenMarzo.concesionarioCoches.test;

import pgn.examenMarzo.concesionarioCoches.Coche;
import pgn.examenMarzo.concesionarioCoches.Color;
import pgn.examenMarzo.concesionarioCoches.ListaCoches;
import pgn.examenMarzo.concesionarioCoches.Modelo;
import pgn.examenMarzo.utiles.Menu;
import pgn.examenMarzo.utiles.Teclado;

/**
 * Examen Marzo Corregido
 * 
 * @author Pablo Durán
 *
 */
public class TestConcesionario {
	static Menu menu;
	static Color color;
	static Modelo modelo;
	static ListaCoches listaCoches = new ListaCoches();

	public static void main(String[] args) {
		menu = new Menu("**Menu Concersionario", new String[] { "Añadir Coche",
				"Eliminar Coche", "Mostrar coche Matrícula",
				"Mostrar Concesionario",
				"Mostrar Cantidad coches Concesionario",
				"Mostrar Coches color", "Salir" });

		int opcion;
		do {
			opcion = menu.gestionar();
			realizarOpcion(opcion);
		} while (opcion != 7);
	}

	/**
	 * Método que realiza una acción dependiendo del numero introducido
	 * @param opcion
	 */
	private static void realizarOpcion(int opcion) {
		switch (opcion) {
		case 1:
			annadirCoche();
			break;
		case 2:
			eliminarCoche();
			break;
		case 3:
			System.out.println(listaCoches.mostrarCocheMatricula(Teclado
					.leerCadena("Matrícula")));
			break;
		case 4:
			mostrarConcesionario();
			break;
		case 5:
			System.out.println("Coches en el concesionario: "
					+ listaCoches.mostrarCantidadCoches());
			break;
		case 6:
			System.out
					.println(listaCoches.mostrarCocheColor(color.pedirColor()));
			break;
		case 7:
			System.out.println("Adios");
		default:
			break;
		}

	}

	/**
	 * Método que muestra un mensaje si no hay un mensaje si no hay coches en el
	 * concesionario o una lista de coches si los hay
	 */
	private static void mostrarConcesionario() {
		if (listaCoches.mostrarCantidadCoches() == 0) {
			System.out.println("No hay coches en el concesionario");
		} else {
			System.out.println(listaCoches);
		}

	}

	/**
	 * Método que muestra un mensaje si no se puede eliminar el coche y otro si
	 * se ha podido eliminar
	 */
	private static void eliminarCoche() {
		if (listaCoches.eliminar(new Coche(Teclado.leerCadena("Matrícula")))) {
			System.out.println("Coche eliminado con exito");
		} else {
			System.err.println("El coche indicado no se ha podido eliminar");
		}
	}

	/**
	 * Método que muestra un mensaje un mensaje si el coche se ha podido añadir
	 * con exito y otro si no se ha podido
	 */
	private static void annadirCoche() {
		if (listaCoches.annadir(color, modelo, Teclado.leerCadena("Matrícula"))) {
			System.out.println("El coche se ha añadido con exito");
		} else {
			System.err.println("No se ha podido añadir el coche");
		}
	}

}
