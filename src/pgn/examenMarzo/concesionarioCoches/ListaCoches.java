package pgn.examenMarzo.concesionarioCoches;

import java.util.ArrayList;

/**
 * Lista de Coches
 * @author Pablo Durán
 *
 */
public class ListaCoches {
private  ArrayList<Coche> listaCoches;

public ListaCoches(){
	setListaCoches(new ArrayList<Coche>());
}


/**
 * @param listaCoches the listaCoches to set
 */
private void setListaCoches(ArrayList<Coche> listaCoches) {
	this.listaCoches = listaCoches;
}


/**
 * Método que añade un coche que no sea null o ya exista al arrayList
 * @param color
 * @param modelo
 * @param matricula
 * @return
 */
public boolean annadir(Color color, Modelo modelo,String matricula){
	color=color.pedirColor();
	modelo=modelo.pedirModelo();
	Coche coche=Coche.instanciarCoche(color, modelo, matricula);
	if(coche==null|| listaCoches.contains(coche)){
		return false;
	}
	else{
		return listaCoches.add(coche);
	}
}

/**
 * Método que elimina un coche del arrayList
 * @param coche
 * @return
 */
public boolean eliminar(Coche coche){
	return listaCoches.remove(coche);
}

/**
 * Método que muestra el número de coches que hay en el concesionario
 * @return
 */
public int mostrarCantidadCoches(){
	return listaCoches.size();
}

/**
 * Método que muestra el coche de la matrícula introducida
 * @param coche
 * @return
 */
public Coche mostrarCocheMatricula(String matricula){
	for (Coche coche : listaCoches) {
		if(matricula.equals(coche.getMatricula())){
			return coche;
		}
	}
	return null;
}

/**
 * Método que muestra los coches de un color específico
 * @param color
 * @return
 */
public ArrayList mostrarCocheColor(Color color){
	ArrayList cocheColor=new ArrayList();
	for (Coche coche : listaCoches) {
		if(coche.getColor()==color){
			cocheColor.add(coche);
		}
	}
	return cocheColor;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return ""+listaCoches;
}


}
