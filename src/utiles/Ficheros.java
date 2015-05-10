package utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

import concesionarioCoches.Concesionario;

public class Ficheros {

	private static final Pattern PATTERN_EXTENSION = Pattern
			.compile("^((\\w)+(\\.obj))$");
	private static File archivo=new File("");

	/**
	 * @return the archivo
	 */
	private static File getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo
	 *            the archivo to set
	 */
	private static void setArchivo(String archivo) {
		Ficheros.archivo = new File(archivo);
	}


	public static void guardar(Object objeto)
			throws FileNotFoundException, IOException {
		if (archivo.getPath().equalsIgnoreCase("")) {
			((Concesionario) objeto).setModificado(false);
			guardarComo(objeto, Teclado.leerCadena("Nombre del archivo: "));
		} else {
			try (ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(archivo))) {
				oos.writeObject(objeto);
				((Concesionario) objeto).setModificado(false);
			}
		}

	}

	public static Object abrirArchivo(Object objeto)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		String fichero = Teclado.leerCadena("Nombre del archivo: ");
		fichero = validarArchivo(fichero);
		Object aux;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				fichero))) {
			setArchivo((validarArchivo(fichero)));
			aux = in.readObject();
			return aux;
		}
	}
	
	public static void nuevoArchivo() {
		setArchivo((validarArchivo("")));
	}

	private static String validarArchivo(String archivo) {
		if (PATTERN_EXTENSION.matcher(archivo).matches()) {
			return archivo;
		} else {
			archivo +=".obj";
		}
		return archivo;
	}

	private static boolean deseaContinuar(File archivo) {
		char opcion;
		do {
			opcion = Teclado.leerCaracter("El archivo " + archivo
					+ " ya existe. ¿Desea Sobreescribirlo?(S/N)");
			opcion = Character.toUpperCase(opcion);
		} while (!(opcion == 'S' || opcion == 'N'));
		if (opcion == 'S') {
			return true;
		}
		return false;
	}


	public static void guardarComo(Object objeto, String nombreArchivo
			) throws FileNotFoundException, IOException {
		File archivo = new File(validarArchivo(nombreArchivo));
		Boolean archivoEx = archivo.exists();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				archivo));
		if (!archivoEx) {
			oos.writeObject(objeto);
		} else {
			deseaContinuar(archivo);
		}
	}
}
