package concesionarioCochesGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCoches.CocheYaExistenteException;
import concesionarioCoches.Color;
import concesionarioCoches.ColorNoValidoException;
import concesionarioCoches.Concesionario;
import concesionarioCoches.Marca;
import concesionarioCoches.MatriculaNoValodaException;
import concesionarioCoches.Modelo;
import concesionarioCoches.ModeloNoValidoException;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
/**
 * Clase que define una ventana con los botones de VentanaPadre en la que se añaden coches al concesionario
 * @author Pablo Durán
 *
 */
public class NuevoCoche extends VentanaPadre {

	/**
	 * Create the dialog.
	 */
	public NuevoCoche(final Concesionario concesionario) {
		super();
		tfMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!tfMatricula.getText().matches("^(\\d){4}[ -]?[[B-Z]&&[^QEIOU]]{3}$")){
					tfMatricula.setForeground(java.awt.Color.RED);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				tfMatricula.setForeground(java.awt.Color.BLACK);
			}
		});
		setTitle("Nuevo Coche");
		buttonAlante.setVisible(false);
		 buttonAtras.setVisible(false);
		
		comboBoxMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo(comboBoxMarca)));
			}
	});
		comboBoxMarca.setModel(new DefaultComboBoxModel(Marca.values()));
		comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo(comboBoxMarca)));
		
		buttonAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						tfMatricula.setBackground(java.awt.Color.WHITE);
						concesionario.annadir(tfMatricula.getText(), getColor(), (Modelo) comboBoxModelo.getSelectedItem());
					} catch (ColorNoValidoException | MatriculaNoValodaException | ModeloNoValidoException | CocheYaExistenteException e1) {
						JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
	}

	/**
	 * Devuelve un array de Modelos dependiendo de la opcion del comboBoxMarca seleccionado
	 * @param comboBoxMarca
	 * @return array de modelos
	 */
	private Object[] getModelo(JComboBox<Marca> comboBoxMarca) {
		Marca marca = (Marca) comboBoxMarca.getSelectedItem();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo model : Modelo.values()) {
			if (model.getMarca() == marca) {
				modelos.add(model);
			}
		}
		return modelos.toArray();
	}

	/**
	 * Devuelve un color dependiendo del raddioButton seleccionado
	 * @return Color del raddioButton seleccionado
	 */
	private Color getColor() {
		if (radioPlata.isSelected()) {
			return Color.PLATA;
		} else if (radioRojo.isSelected()) {
			return Color.ROJO;
		} else if (radioAzul.isSelected()) {
			return Color.AZUL;
		} else {
			return null;
		}
	}
}
