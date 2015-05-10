package concesionarioCochesGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCoches.Coche;
import concesionarioCoches.CocheNoExistenteException;
import concesionarioCoches.Concesionario;
import concesionarioCoches.MatriculaNoValodaException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
/**
 * Clase que define una ventana con los botones de VentanaPadre en la que se hacen busquedas de coches por matrículas
 * @author Pablo Durán
 *
 */
public class MostrarMatricula extends VentanaPadre {

	private final JPanel contentPanel = new JPanel();
	private int indiceCoches = 0;
	private Concesionario concesionario;


	/**
	 * Create the dialog.
	 */
	public MostrarMatricula(final Concesionario concesionario) {
		super();
		setTitle("Mostrar por Matr\u00EDcula");
		this.concesionario = concesionario;
		buttonAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					datosCoche(concesionario.get(tfMatricula.getText()));
					
				} catch (MatriculaNoValodaException | CocheNoExistenteException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonAlante.setVisible(false);
		buttonAtras.setVisible(false);
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		radioRojo.setEnabled(false);
		radioAzul.setEnabled(false);
		radioPlata.setEnabled(false);
	
			
		buttonAnnadir.setText("Buscar");
	}

	/**
	 * Método que recupera los datos del coche del concesionario y marca los elementos del coche correspondiente en la ventana.
	 * @param coche Coche que muestra
	 */
	private void datosCoche(Coche coche) {
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
