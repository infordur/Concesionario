package utiles;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Clase que controla Filtros de extensiones
 * @author Pablo
 *
 */
public class Filtro extends FileFilter {
	private String extension;
	private String description;

	public Filtro(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}

	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		return file.getName().endsWith(extension);
	}

	public String getDescription() {
		return description + String.format(" (*%s)", extension);
	}
}
