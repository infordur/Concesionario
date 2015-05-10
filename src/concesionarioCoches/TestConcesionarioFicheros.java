package concesionarioCoches;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import utiles.Ficheros;
import utiles.Menu;
import utiles.Teclado;

/**
 * 
 * @author Pablo Durán
 *
 */
public class TestConcesionarioFicheros {
	static Menu menu = new Menu("Concesionario de coches", new String[] {
			"Alta Coche", "Baja Coche", "Mostrar Coche",
			"Mostrar concesionario", "Contar coches del concesionario",
			"Mostrar coches de un color", "Ficheros", "Salir" });
	static Menu menuFicheros = new Menu("Menu Ficheros", new String[] {
			"Nuevo", "Abrir", "Guardar", "Guardar como", "Salir" });
	private static Menu menuColores = new Menu("Colores de los coches",
			Color.generarOpcionesMenu());
	private static Menu menuModelos = new Menu("Modelos de los coches",
			Modelo.generarOpcionesMenu());
	static Concesionario concesionario = new Concesionario();

	public static void main(String[] args) {

		int opcion;
		do {
			opcion = menu.gestionar();
			realizarOpcion(opcion);
		} while (opcion != 8);

	}

	private static void realizarOpcion(int opcion) {
		switch (opcion) {
		case 1:
			annadirCoche(); // Añade Coche
			break;
		case 2:
			eliminarCoche(); // Eliminar Coche
			break;
		case 3:
			getCoche(); // Muestra un coche
			break;
		case 4:
			System.out.println(concesionario); // Muestra el concesionario
			break;
		case 5:
			System.out.println("Número de coches en el concesionario: "
					+ concesionario.size());
			break;
		case 6:
			System.out.println(concesionario.getCochesColor(pedirColor()));
			break;
		case 7:
			realizarOpcionFichero();
			break;
		case 8:
			System.out.println("Aaaaaaaaaaaaaaaaaaaaadios"); // Salir
			return;
		}

	}

	private static void realizarOpcionFichero() {
		char caracter;
		do {
			switch (menuFicheros.gestionar()) {
			case 1:
				nuevo(); //nuevo archivo
				break;
			case 2:
				abrir(); //abrir archivo
				break;
			case 3:
				guardar(); //guardar archivo
				break;
			case 4:
				guardarComo(); //guardar como
				break;
			default:
				return;
			}
		} while (true);

	}

	private static void nuevo() {
		char caracter;
		try {
			if (!concesionario.isModificado()) {
				concesionario = new Concesionario();
				Ficheros.nuevoArchivo();
			} else {
				do {
					caracter = Character
							.toUpperCase(Teclado
									.leerCaracter("No has guardado el archivo. ¿Desea guardarlo?(S/N)"));
				} while (!(caracter == 'S' || caracter == 'N'));
				if (caracter == 'S') {
					Ficheros.guardar(concesionario);
					concesionario = new Concesionario();
					Ficheros.nuevoArchivo();
				} else {
					concesionario = new Concesionario();
					Ficheros.nuevoArchivo();
				}
			}
		} catch (IOException e3) {
			System.out.println(e3.getMessage());
		}
	}

	private static void guardarComo() {
		try {
			Ficheros.guardarComo(concesionario, Teclado.leerCadena("Nombre del archivo: "));
			System.out.println("Archivo guardado.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void guardar() {
		try {
			Ficheros.guardar(concesionario);
			System.out.println("Archivo guardado.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void abrir() {
		char caracter;
		try {
			if (!concesionario.isModificado()) {
				concesionario = (Concesionario) Ficheros.abrirArchivo(concesionario);
			} else {
				do {
					caracter = Character.toUpperCase(Teclado	.leerCaracter("No has guardado el archivo. ¿Desea guardarlo?(S/N)"));
				} while (!(caracter == 'S' || caracter == 'N'));
				if (caracter == 'S') {
					Ficheros.guardar(concesionario);
					concesionario = (Concesionario) Ficheros.abrirArchivo(concesionario);
				} else {
					concesionario = (Concesionario) Ficheros.abrirArchivo(concesionario);
				}
			}
		} catch (ClassNotFoundException | IOException e2) {
			System.out.println(e2.getMessage());
		}
	}

	/**
	 * Metodo que devuelve un coche introduciendo la matricula correspondiente
	 */
	private static void getCoche() {
		Coche coche;
		try {
			coche = concesionario.get(Teclado
					.leerCadena("Introduce la matrícula"));
			System.out.println(coche);
		} catch (MatriculaNoValodaException e) {
			System.out.println(e.getMessage());
		} catch (CocheNoExistenteException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Metodo que elimina un coche de la lista
	 */
	private static void eliminarCoche() {
		try {
			concesionario
					.eliminar(Teclado.leerCadena("Introduce la matrícula"));
			System.out.println("Coche eliminado");
		} catch (MatriculaNoValodaException | CocheNoExistenteException e) {
			System.out.println(e.getMessage() + " No se ha podido eliminar");
		}
	}

	/**
	 * Metodo que añade un coche a la lista
	 */
	private static void annadirCoche() {
		try {
			concesionario.annadir(Teclado.leerCadena("Introduce la matrícula"),
					pedirColor(), pedirModelo());
			System.out.println("Coche añadido con éxito");

		} catch (MatriculaNoValodaException e) {
			System.out.println(e.getMessage());
		} catch (ColorNoValidoException e) {
			System.out.println(e.getMessage());
		} catch (ModeloNoValidoException e) {
			System.out.println(e.getMessage());
		} catch (CocheYaExistenteException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo que solicita un modelo de coche
	 * 
	 * @return devuelve un modelo de coche
	 */
	private static Modelo pedirModelo() {
		int opcion = menuModelos.gestionar();
		Modelo[] arrModelos = Modelo.getValues();
		if (opcion == arrModelos.length + 1)
			return null;
		return arrModelos[opcion - 1];
	}

	/**
	 * Metodo que solicita un color de coche
	 * 
	 * @return devuelve un color de coche
	 */
	private static Color pedirColor() {
		int opcion = menuColores.gestionar();
		Color[] arrColores = Color.getValues();
		if (opcion == arrColores.length + 1)
			return null;
		return arrColores[opcion - 1];
	}

}
