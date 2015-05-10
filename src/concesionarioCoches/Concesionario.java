/**
 * Paquete que contiene los archivos del programa
 */
package concesionarioCoches;

import java.io.Serializable;
/**
 * Importación de la clase ArrayList
 */
import java.util.ArrayList;

/**
 * Concesionario que almacena coches, los coches no pueden tener igual matricula
 * y deben de contener todos los campos especificados en la clase coche
 * 
 * @author Pablo Durán
 * @version 1.0
 * 
 */
public class Concesionario implements Serializable{
	/**
	 * Campo que controla si el concesionario ha sido modificado
	 */
	private boolean modificado = false;
	/**
	 * Lista de coches parametrizada de tipo coche
	 */
	public ArrayList<Coche> almacen = new ArrayList<Coche>();
	/**
	 * Nombre del concesionario
	 */
	private final String nombre = "IES Gran Capitán";

	/**
	 * Metodo para añadir un coche a la lista
	 * 
	 * @param matricula
	 *            matricula del coche
	 * @param color
	 *            color del coche
	 * @param modelo
	 *            modelo del coche
	 * @return true si el coche pudo añadirse a la lista o false si no pudo
	 *         añadirse
	 * @throws ModeloNoValidoException
	 * @throws ColorNoValidoException
	 * @throws MatriculaNoValodaException
	 * @throws CocheYaExistenteException
	 */
	public boolean annadir(String matricula, Color color, Modelo modelo)
			throws MatriculaNoValodaException, ColorNoValidoException,
			ModeloNoValidoException, CocheYaExistenteException {
		Coche coche = new Coche(matricula, color, modelo);
		if (almacen.contains(coche)){
			throw new CocheYaExistenteException("El coche ya existe");
		}
		setModificado(true);
		return almacen.add(coche);
	}

	/**
	 * @param modificado the modificado to set
	 */
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	/**
	 * @return the modificado
	 */
	public boolean isModificado() {
		return modificado;
	}

	/**
	 * Metodo que elimina un coche de la lista
	 * 
	 * @param matricula
	 *            matricula del coche a eliminar
	 * @return devuelve true si el coche se elimino y false si no se pudo
	 *         eliminar
	 * @throws MatriculaNoValodaException
	 * @throws CocheNoExistenteException
	 */
	public boolean eliminar(String matricula) throws MatriculaNoValodaException,
			CocheNoExistenteException {
		Coche coche = new Coche(matricula);
		if (almacen.contains(coche)){
			setModificado(true);
			return almacen.remove(coche);
		}else
			throw new CocheNoExistenteException("El coche no existe");

	}

	/**
	 * Metodo que devuelve el tamaño de la lista
	 * 
	 * @return el tamaño de la lista con un int
	 */
	public int size() {
		return almacen.size();
	}

	/**
	 * Devuelve el Coche de la lista indicando la matricula
	 * 
	 * @param matricula
	 *            matricula del coche a buscar
	 * @return Coche contenido en el almacén. null si no existe
	 * @throws MatriculaNoValodaException
	 * @throws CocheNoExistenteException
	 */
	public Coche get(String matricula) throws MatriculaNoValodaException,
			CocheNoExistenteException {
		Coche coche = new Coche(matricula);
		int index = almacen.indexOf(coche);
		if (index != -1) {
			return almacen.get(index);
		}
		throw new CocheNoExistenteException("El coche no existe");
	}

	/**
	 * Devuelve el coche depenediendo el índice indicado
	 * @param i
	 * @return
	 */
	public Coche get(int i) {
		if(almacen.isEmpty()){
			return null;
		}
		else if(i<0 || i> almacen.size()-1){
			return null;
		}
		else{
			return almacen.get(i);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Concesionario " + nombre + "[almacen=" + almacen + "]";
	}

	/**
	 * Metodo que devuelve una lista de coches de un mismo color
	 * 
	 * @param color
	 *            color seleccionado en un menu
	 * @return devuelve un ArrayList de coches con todos los coches del mismo
	 *         color
	 */
	public ArrayList<Coche> getCochesColor(Color color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if (coche.getColor() == color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}



}
