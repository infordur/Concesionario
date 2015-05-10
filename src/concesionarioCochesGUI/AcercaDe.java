package concesionarioCochesGUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

/**
 * Ventana Acerca De que muestra información sobre el autor y la version del programa
 * @author Pablo Durán
 *
 */

public class AcercaDe extends JDialog {


	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setModal(true);
		setBounds(100, 100, 244, 118);
		
		JTextArea txtrAutorPabloDurn = new JTextArea();
		txtrAutorPabloDurn.setToolTipText("");
		txtrAutorPabloDurn.setText("Autor: Pablo Dur\u00E1n\r\nversion 1.0");
		txtrAutorPabloDurn.setEditable(false);
		getContentPane().add(txtrAutorPabloDurn, BorderLayout.CENTER);

	}

}
