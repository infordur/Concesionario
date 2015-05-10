package concesionarioCochesGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

import concesionarioCoches.Concesionario;
import concesionarioCoches.Marca;
import concesionarioCoches.Modelo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * Clase que define una ventana genérica(común) para las ventanas NuevoCoche/EliminarCoche/MostrarColor/MostrarMatricula/MostrarConcesionario
 * @author Pablo Durán
 *
 */
public class VentanaPadre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final JPanel contentPanel = new JPanel();
	protected JTextField tfMatricula;
	protected final ButtonGroup buttonGroup = new ButtonGroup();
	protected JButton buttonAtras;
	protected JButton buttonAlante;
	protected JComboBox<Marca> comboBoxMarca = new JComboBox<Marca>();
	protected JComboBox<Modelo> comboBoxModelo = new JComboBox<Modelo>();
	protected JRadioButton radioPlata;
	protected JRadioButton radioAzul;
	protected JRadioButton radioRojo;
	protected JButton buttonAnnadir;
	protected JLabel lblModelo;
	protected JLabel lblMatricula;
	protected JPanel buttonPane;
	protected JButton cancelButton;
	protected JLabel lblMatrcula;
	protected JLabel lblMarca;
	private JPanel panel;

	/**
	 * Create the dialog.
	 */
	public VentanaPadre() {
		setModal(true);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblMatrcula = new JLabel("Matr\u00EDcula");
		lblMatrcula.setBounds(132, 24, 107, 14);
		contentPanel.add(lblMatrcula);

		tfMatricula = new JTextField();
		tfMatricula.setBounds(201, 21, 86, 20);
		contentPanel.add(tfMatricula);
		tfMatricula.setColumns(10);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Color", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(52, 49, 346, 46);
		contentPanel.add(panel);
		panel.setLayout(null);

		radioPlata = new JRadioButton("Plata");
		radioPlata.setBounds(6, 16, 109, 23);
		panel.add(radioPlata);
		buttonGroup.add(radioPlata);

		radioAzul = new JRadioButton("Azul");
		radioAzul.setBounds(120, 16, 109, 23);
		panel.add(radioAzul);
		buttonGroup.add(radioAzul);

		radioRojo = new JRadioButton("Rojo");
		radioRojo.setBounds(231, 16, 109, 23);
		panel.add(radioRojo);
		buttonGroup.add(radioRojo);

		comboBoxMarca.setBounds(127, 125, 66, 20);
		contentPanel.add(comboBoxMarca);

		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(84, 128, 46, 14);
		contentPanel.add(lblMarca);

		comboBoxModelo.setBounds(279, 125, 66, 20);
		contentPanel.add(comboBoxModelo);

		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(223, 128, 46, 14);
		contentPanel.add(lblModelo);

		buttonAtras = new JButton("<");
		buttonAtras.setBounds(302, 181, 43, 23);
		contentPanel.add(buttonAtras);

		buttonAlante = new JButton(">");
		buttonAlante.setBounds(355, 181, 43, 23);
		contentPanel.add(buttonAlante);

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
				buttonAnnadir = new JButton("A\u00F1adir");
				buttonPane.add(buttonAnnadir);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}
}
