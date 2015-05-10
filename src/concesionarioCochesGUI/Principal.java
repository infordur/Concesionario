package concesionarioCochesGUI;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import utiles.Ficheros;
import utiles.Filtro;
import concesionarioCoches.Coche;
import concesionarioCoches.CocheYaExistenteException;
import concesionarioCoches.Color;
import concesionarioCoches.ColorNoValidoException;
import concesionarioCoches.Concesionario;
import concesionarioCoches.MatriculaNoValodaException;
import concesionarioCoches.Modelo;
import concesionarioCoches.ModeloNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

/**
 * Ventana principal que ejecuta el programa
 * 
 * @author Pablo Durán
 *
 */
public class Principal {

	protected final JPanel contentPanel = new JPanel();
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnFicheros;
	private JMenu mnCoches;
	private JMenu mnAyuda;
	private Concesionario concesionario = new Concesionario();
	private NuevoCoche nuevoCoche;
	private EliminarCoche eliminarCoche;
	private MostrarCoches mostrarCoches;
	private MostrarColor mostrarColor;
	private MostrarMatricula mostrarMatricula;
	private Filtro filtro = new Filtro(".obj", "Objeto");
	private JFileChooser guardarComoFile;
	private AcercaDe acercaDe;
	private VerAyuda verAyuda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setTitle(Ficheros.archivo.getName());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFicheros = new JMenu("Ficheros");
		mnFicheros.setMnemonic('f');
		menuBar.add(mnFicheros);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevoArchivo();
			}

		});
		mnFicheros.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.SHIFT_MASK));
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		mnFicheros.add(mntmAbrir);
		mnFicheros.addSeparator();
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}

		});
		mnFicheros.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como");
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				InputEvent.CTRL_MASK));
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		mnFicheros.add(mntmGuardarComo);

		JMenuItem mnSalir = new JMenuItem("Salir");
		mnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (concesionario.isModificado()) {
					Object[] options = { "OK", "CANCEL" };
					int respuesta = JOptionPane.showOptionDialog(null,
							"No has guardado, ¿Desea Guardar?",
							"¿Desea Guardar?", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);
					if (respuesta == 0)
						guardarComo();
					else {
						System.exit(0);
					}
				}
				System.exit(0);
			}
		});
		mnSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		mnFicheros.addSeparator();
		mnFicheros.add(mnSalir);

		JMenu mnCoches = new JMenu("Coches");
		mnCoches.setMnemonic('c');
		menuBar.add(mnCoches);

		JMenuItem mnAnnadir = new JMenuItem("A\u00F1adir");
		mnAnnadir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mnAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoCoche();
			}
		});

		mnCoches.add(mnAnnadir);

		JMenuItem Eliminar = new JMenuItem("Eliminar");
		Eliminar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarCoche();
			}
		});
		mnCoches.add(Eliminar);

		JMenuItem mntmMostrarConcesionario = new JMenuItem(
				"Mostrar Concesionario");
		mntmMostrarConcesionario.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarConcesionario();
			}
		});
		mnCoches.add(mntmMostrarConcesionario);

		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('b');
		mnCoches.add(mnBuscar);

		JMenuItem mntmPorMatrcula = new JMenuItem("por Matr\u00EDcula");
		mntmPorMatrcula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				InputEvent.SHIFT_MASK));
		mntmPorMatrcula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarMatricula();
			}
		});
		mnBuscar.add(mntmPorMatrcula);

		JMenuItem mntmPorColor = new JMenuItem("por Color");
		mntmPorColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.SHIFT_MASK));
		mntmPorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarColor();
			}
		});
		mnBuscar.add(mntmPorColor);

		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic('a');
		menuBar.add(mnAyuda);

		JMenuItem mntmVerAyuda = new JMenuItem("Ver Ayuda");
		mntmVerAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.SHIFT_MASK));
		mntmVerAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verAyuda();
			}
		});
		mnAyuda.add(mntmVerAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.SHIFT_MASK));
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acercaDe();
			}
		});
		mnAyuda.add(mntmAcercaDe);
	}

	/**
	 * Abre un archivo abriendo una ventana de explorador (JFileChooser)
	 */
	protected void abrir() {
		JFileChooser abrir = new JFileChooser();
		abrir.addChoosableFileFilter(filtro);
		abrir.setAcceptAllFileFilterUsed(false);
		if (abrir.showDialog(abrir, "Abrir Archivo") == abrir.APPROVE_OPTION) {
			try {
				Ficheros.archivo = abrir.getSelectedFile();
				Ficheros.validarArchivo(abrir.getSelectedFile());
				concesionario = (Concesionario) abrirArchivo();
				concesionario.setModificado(false);
				frame.setTitle(Ficheros.archivo.getName());
			} catch (ClassNotFoundException | IOException ex) {
				JOptionPane.showMessageDialog(contentPanel,
						"No se ha podido abrir el archivo", "Error",
						JOptionPane.ERROR_MESSAGE);
				Ficheros.setArchivo("Sin_titulo");
			}
		}
	}

	/**
	 * Guarda un archivo abriendo una ventana de explorador (JFileChooser)
	 */
	private void guardarComo() {
		guardarComoFile = new JFileChooser();
		guardarComoFile.addChoosableFileFilter(filtro);
		guardarComoFile.setAcceptAllFileFilterUsed(false);
		if (guardarComoFile.APPROVE_OPTION == guardarComoFile.showDialog(
				guardarComoFile, "Guardar Archivo")) {
			try {
				guardarComoFile.setAcceptAllFileFilterUsed(false);
				Ficheros.validarArchivo(guardarComoFile.getSelectedFile());
				if (Ficheros.archivo.exists()) {
					int opcion = JOptionPane
							.showOptionDialog(
									contentPanel,
									"¿Esta seguro de que desea sobreescribir el archivo?",
									"Verificar",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, null,
									null);
					switch (opcion) {
					case JOptionPane.YES_OPTION:
						Ficheros.guardarComo(concesionario, Ficheros.archivo);
						break;
					case JOptionPane.NO_OPTION:
						break;
					}
				} else {
					Ficheros.guardarComo(concesionario, Ficheros.archivo);
				}
			} catch (IOException ex) {
			}
		}
		frame.setTitle(Ficheros.archivo.getName());
	}

	/**
	 * Método que inicializa la ventana MostrarMatricula y la pone visible
	 */
	protected void mostrarMatricula() {
		mostrarMatricula = new MostrarMatricula(concesionario);
		mostrarMatricula.setVisible(true);

	}

	/**
	 * Método que inicializa la ventana MostrarColor y la pone visible
	 */
	protected void mostrarColor() {
		mostrarColor = new MostrarColor(concesionario);
		mostrarColor.setVisible(true);

	}

	/**
	 * Método que inicializa la ventana mostrarConcesionario y la pone visible
	 */
	protected void mostrarConcesionario() {
		mostrarCoches = new MostrarCoches(concesionario);
		mostrarCoches.setVisible(true);
	}

	/**
	 * Método que inicializa la ventana eliminarCoche y la pone visible
	 */
	protected void eliminarCoche() {
		eliminarCoche = new EliminarCoche(concesionario);
		eliminarCoche.setVisible(true);

	}

	/**
	 * Método que inicializa la ventana nuevoCoche y la pone visible
	 */
	private void nuevoCoche() {
		nuevoCoche = new NuevoCoche(concesionario);
		nuevoCoche.setVisible(true);
	}

	/**
	 * Método que inicializa la ventana verAyuda y la pone visible
	 */
	protected void verAyuda() {
		verAyuda = new VerAyuda();
		verAyuda.setVisible(true);

	}

	/**
	 * Método que inicializa la ventana acercaDe y la pone visible
	 */
	protected void acercaDe() {
		acercaDe = new AcercaDe();
		acercaDe.setVisible(true);

	}

	/**
	 * Crea un nuevo concesionario, en caso de que el concesionario haya sido
	 * modificado sin guardarse anteriormente muestra una ventana que pregunta
	 * si deseas guardar. Si la respuesta es si, invoca a guardarComo() que pide
	 * un nombre y crea un archivo con la información. En caso de que la
	 * respuesta sea no mantiene los cambios hechos
	 * 
	 */
	public void nuevoArchivo() {
		if (concesionario.isModificado()) {
			int opcion = JOptionPane
					.showOptionDialog(
							contentPanel,
							"No has guardado, ¿Desea Guardar?",
							"¿Desea Guardar?",
							JOptionPane.YES_NO_CANCEL_OPTION,	JOptionPane.QUESTION_MESSAGE, null, null,null);
			switch (opcion) {
			case JOptionPane.YES_OPTION:
				guardarComo();
				break;
			case JOptionPane.NO_OPTION:
				Ficheros.setArchivo("Sin_titulo");
				frame.setTitle(Ficheros.archivo.getName());
				concesionario = new Concesionario();
				break;
			}
		}
		else {
            Ficheros.setArchivo("Sin_titulo");
            concesionario = new Concesionario();
            frame.setTitle(Ficheros.archivo.getName());
            concesionario.setModificado(false);
        }

	}

	/**
	 * Abre un archivo
	 * 
	 * @return Objeto con la información del objeto abierto
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object abrirArchivo() throws IOException,
			ClassNotFoundException {
		Object aux;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				Ficheros.archivo))) {
			aux = in.readObject();
			return aux;
		}
	}

	/**
	 * Guarda un archivo, si es la primera vez que guardas abre la opción
	 * guardarComo que pregunta por un nombre Si el archivo ha sido abierto o
	 * guardado lo sobreescribe
	 */
	private void guardar() {
		if (Ficheros.archivo.getName().equalsIgnoreCase("Sin_titulo")) {
			guardarComo();
		} else {
			try {
				Ficheros.guardar(concesionario);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
