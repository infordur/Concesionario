package concesionarioCochesGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCoches.Coche;
import concesionarioCoches.CocheYaExistenteException;
import concesionarioCoches.Color;
import concesionarioCoches.ColorNoValidoException;
import concesionarioCoches.Concesionario;
import concesionarioCoches.MatriculaNoValodaException;
import concesionarioCoches.ModeloNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 * Clase que define una ventana con los botones de VentanaPadre en la que se
 * realiza busquedas de coches por su color.
 * 
 * @author Pablo Durán
 *
 */
public class MostrarColor extends VentanaPadre {

	private int indiceCoches = 0;
	private ArrayList<Coche> concesionarioColor;
	private JButton buttonReset;
	private JTextField textConcesionarioVacio;

	/**
	 * Create the dialog.
	 */
	public MostrarColor(final Concesionario concesionario) {
		super();
		setTitle("Mostrar por Color");
		buttonAtras.setLocation(333, 181);
		buttonAlante.setLocation(375, 181);
		buttonAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				concesionarioColor = concesionario
						.getCochesColor(devolverColor());
				if(!concesionarioColor.isEmpty()){
				datosCoche(concesionarioColor.get(indiceCoches));
				comprobarBoton();
				buttonAnnadir.setVisible(false);
				}
				else{
					textConcesionarioVacio.setText("No hay coches de color "+devolverColor());
				}
			}
		});
		buttonAnnadir.setText("Buscar");
		radioPlata.setSelected(true);

		buttonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datosCoche(concesionarioColor.get(--indiceCoches));
				comprobarBoton();
			}
		});

		buttonAlante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datosCoche(concesionarioColor.get(++indiceCoches));
				comprobarBoton();
			}
		});

		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		tfMatricula.setEnabled(false);
		tfMatricula.setEditable(false);

		buttonReset = new JButton("Reset");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAnnadir.setVisible(true);
				tfMatricula.setText("");
			}
		});
		buttonReset.setBounds(234, 181, 89, 23);
		contentPanel.add(buttonReset);
		
		textConcesionarioVacio = new JTextField();
		textConcesionarioVacio.setEditable(false);
		textConcesionarioVacio.setBounds(10, 198, 214, 20);
		contentPanel.add(textConcesionarioVacio);
		textConcesionarioVacio.setColumns(10);
	}

	/**
	 * Método que recupera los datos del coche del concesionario y marca los
	 * elementos del coche correspondiente en la ventana.
	 * 
	 * @param coche
	 *            Coche que muestra
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

/**
	 * Método que comprueba y oculta los botones de ">" y "<" dependiendo de si hay elementos delante o atrás del elemento mostrado(actual).
	 */
	private void comprobarBoton() {
		if ((indiceCoches - 1) == -1) {
			buttonAtras.setEnabled(false);
		} else {
			buttonAtras.setEnabled(true);
		}
		if ((indiceCoches + 1) >= concesionarioColor.size()) {
			buttonAlante.setEnabled(false);
		} else {
			buttonAlante.setEnabled(true);
		}
	}

	/**
	 * Método que devuelve un color dependiendo del radioButton seleccionado
	 * 
	 * @return
	 */
	private Color devolverColor() {
		if (radioAzul.isSelected()) {
			return Color.AZUL;
		} else if (radioPlata.isSelected()) {
			return Color.PLATA;
		} else {
			return Color.ROJO;
		}

	}
}
