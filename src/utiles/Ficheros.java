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

	/**
	 * Patrón para validar la extensión de un archivo
	 */
	private static final Pattern PATTERN_EXTENSION = Pattern
			.compile("^((\\w)+(\\.obj))$");
	/**
	 * Archivo con nombre por defecto (Sin_titulo)
	 */
	public static File archivo=new File("Sin_titulo");

	/**
	 * Getter de archivo
	 * @return the archivo
	 */
	private static File getArchivo() {
		return archivo;
	}

	/**
	 * Setter de archivo
	 * @param archivo
	 *            the archivo to set
	 */
	public static void setArchivo(String archivo) {
		Ficheros.archivo = new File(archivo);
	}

	/**
	 * Valida el nombre del archivo dependiendo de un patrón
	 * @param archivo Archivo que deseas validar
	 * @return Archivo con la extensión (.obj)
	 */
	public static File validarArchivo(File archivo) {
		if (PATTERN_EXTENSION.matcher(archivo.getName()).matches()) {
			return archivo;
		} else {
			setArchivo(archivo.getAbsolutePath() + ".obj");
			return archivo;
		}
	}

	/**
	 * Guarda la información en un archivo al cual se le define el nombre
	 * @param objeto Objeto que deseas guardar
	 * @param nombre Nombre del archivo
	 * @throws IOException
	 */
	public static void guardarComo(Object objeto, File nombre)
			throws IOException {
		archivo = nombre;
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(archivo))) {
			out.writeObject(objeto);
		}
	}

	/**
	 * Guarda la informacion del parámetro en un archivo
	 * @param objeto Objeto que desees guardar
	 * @throws IOException
	 */
	public static void guardar(Object objeto) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(archivo))) {
			out.writeObject(objeto);
		}
	}


}
