package concesionarioCochesGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCoches.Coche;
import concesionarioCoches.Concesionario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que define una ventana con los botones de VentanaPadre en la que se muestran todos los coches del concesionario
 * @author Pablo Durán
 *
 */
public class MostrarCoches extends VentanaPadre {


	private int indiceCoches = 0;
	private Concesionario concesionario;

	/**
	 * Create the dialog.
	 * 
	 * @param concesionario
	 */
	public MostrarCoches(final Concesionario concesionario) {
		super();
		setTitle("Mostrar Concesionario");
		this.concesionario = concesionario;
		comprobarBoton();
		if(concesionario.size()!=0){
			datosCoche(concesionario.get(indiceCoches));
			comprobarBoton();
		}
		else{
			JOptionPane.showMessageDialog(contentPanel, "No hay coches en el concesionario", "Error", JOptionPane.ERROR_MESSAGE);
		}
		buttonAlante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datosCoche(concesionario.get(++indiceCoches));
				comprobarBoton();
			}
		});
		buttonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datosCoche(concesionario.get(--indiceCoches));
				comprobarBoton();
			}
		});
		tfMatricula.setEnabled(false);
		tfMatricula.setEditable(false);
		comboBoxModelo.setEnabled(false);
		buttonAnnadir.setVisible(false);
		radioRojo.setEnabled(false);
		radioAzul.setEnabled(false);
		radioPlata.setEnabled(false);
		comboBoxMarca.setEnabled(false);
	}

	/**
	 * Método que recupera los datos del coche del concesionario y marca los elementos del coche correspondiente en la ventana.
	 * @param coche Coche que muestra
	 */
	private void datosCoche(Coche coche) {
		if (indiceCoches < 0 || indiceCoches > concesionario.size()) {
			indiceCoches = 0;
		} else {
			tfMatricula.setText(coche.getMatricula());
			switch (coche.getColor()) {
			case PLATA:
				radioPlata.setSelected(true);
				break;
			case ROJO:
				radioRojo.setSelected(true);
				break;
			case AZUL:
				radioAzul.setSelected(true);
				break;
			}
			comboBoxMarca.addItem(coche.getModelo().getMarca());
			comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
			comboBoxModelo.addItem(coche.getModelo());
			comboBoxModelo.setSelectedItem(coche.getModelo());
		}
	}

	/**
	 * Método que comprueba y oculta los botones de ">" y "<" dependiendo de si hay elementos delante o atrás del elemento mostrado(actual).
	 */
	private void comprobarBoton() {
		if(concesionario.get(indiceCoches-1)==null){
			buttonAtras.setEnabled(false);
		}
		else{
			buttonAtras.setEnabled(true);
		}
		if(concesionario.get(indiceCoches+1)==null){
			buttonAlante.setEnabled(false);
		}
		else{
			buttonAlante.setEnabled(true);
		}
	}
}
