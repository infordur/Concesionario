package concesionarioCochesGUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.Button;
import javax.swing.JScrollPane;
import java.awt.TextArea;

/**
 * Ventana que muestra una ayuda para el usuario
 * @author Pablo Durán
 *
 */
public class VerAyuda extends JDialog {

	/**
	 * Create the dialog.
	 */
	public VerAyuda() {
		setTitle("ver Ayuda");
		setModal(true);
		setBounds(100, 100, 611, 414);
		
		TextArea textArea = new TextArea();
		textArea.setText("A\u00F1adir Coche:\r\nA\u00F1ade un coche al concesionario:\r\n\t-Todos los campos han de ser v\u00E1lidos:\r\n\t\tMatr\u00EDcula: ha de contener 4 d\u00EDgitos y 3 letras en may\u00FAscula, no se admitir\u00E1n (A,E,I,O,U,Q,\u00D1).\r\n\t\tColor: ha de seleccionarse un color entre los posibles.\r\n\t\tMarca y Modelo: ha de seleccionarse una marca y un modelo.\r\n-------------------------------------------------------------------------------------------------------------------------------------------\r\nEliminar Coche:\r\nElimina un coche del concesionario:\r\n\tPara eliminar el coche se ha de introducir una matr\u00EDcula v\u00E1lida que exista en el concesionario.\r\n-------------------------------------------------------------------------------------------------------------------------------------------\r\nMostrar Concesionario\r\nMuestra todos los coches que se encuentren en el concesionario:\r\n\tCon el bot\u00F3n \">\" se pasar\u00E1 al siguiente coche.\r\n\tCon el bot\u00F3n \"<\" se pasar\u00E1 al coche anterior.\r\n-------------------------------------------------------------------------------------------------------------------------------------------\r\nBuscar Coche\r\n\tBuscar por Matr\u00EDcula:\r\n\t\t-Ha de introducirse una matr\u00EDcula v\u00E1lida y darle al bot\u00F3n buscar\r\n\tBuscar por Color:\r\n\t\t-Ha de seleccionarse un color de los disponibles y dale al bot\u00F3n buscar.\r\n\t\t-Con los botones \">\" y \"<\" se pasar\u00E1 entre los distintos coches de ese color.\r\n\t\t-Con el bot\u00F3n \"limpiar\" se borrar\u00E1 la busqueda.\r\n\r\n\t");
		textArea.setEditable(false);
		getContentPane().add(textArea, BorderLayout.CENTER);
		

	}

}
