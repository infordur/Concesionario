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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Clase que define una ventana con los botones de VentanaPadre en la que se elimina un coche.
 * @author Pablo Durán
 *
 */
public class EliminarCoche extends VentanaPadre {

	/**
	 * Create the dialog.
	 */
	public EliminarCoche(final Concesionario concesionario) {
		super();
		tfMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				tfMatricula.setForeground(Color.RED);
			}
			@Override
			public void focusGained(FocusEvent e) {
				tfMatricula.setForeground(Color.BLACK);
			}
		});
		setTitle("Eliminar Coche");
		buttonAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Coche coche = concesionario.get(tfMatricula.getText());
					datosCoche(coche);
					confirmarEliminar(concesionario);
					
				} catch (MatriculaNoValodaException | CocheNoExistenteException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			/**
			 * Método que pregunta a un usuario por medio de una ventana si desea eliminar el coche indicado.
			 * @param concesionario Elemento en el cual se buscarán los objetos a borrar
			 * @throws MatriculaNoValodaException
			 * @throws CocheNoExistenteException
			 */
			private void confirmarEliminar(Concesionario concesionario)
					throws MatriculaNoValodaException,
					CocheNoExistenteException {
				int opcion=JOptionPane.showOptionDialog(contentPanel, "¿Está seguro de que desea eliminar el coche?", "Verificar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				switch(opcion){
				case JOptionPane.YES_OPTION:
					concesionario.eliminar(tfMatricula.getText());
					break;
				}
			}

			/**
			 * Método que recupera los datos del coche del concesionario y marca los elementos del coche correspondiente en la ventana.
			 * @param coche Coche que muestra
			 */
			private void datosCoche(Coche coche) {
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
		});
		buttonAnnadir.setText("Eliminar");
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		radioRojo.setEnabled(false);
		radioAzul.setEnabled(false);
		radioPlata.setEnabled(false);
		buttonAlante.setVisible(false);
		buttonAtras.setVisible(false);

	}
}
