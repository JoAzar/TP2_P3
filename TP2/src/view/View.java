package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class View extends JFrame{
	private ViewListener _listener;
	private JTextField _fieldNombre;
	
	private JTextArea _panelGrupos;

	
	public View() {
		getContentPane().setBackground(new Color(192, 192, 192));
		initialize();
	}
	
	private void initialize() {
		setTitle("Grupos por similaridad musical.");
		setBounds(100, 100, 700, 500); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		// Texto del NOMBRE y campo para completar.
		_fieldNombre = new JTextField();
		_fieldNombre.setBounds(46, 68, 162, 20);
		getContentPane().add(_fieldNombre);
		_fieldNombre.setColumns(10);
		
		JTextPane textNombre = new JTextPane();
		textNombre.setBackground(new Color(192, 192, 192));
		textNombre.setEditable(false);
		textNombre.setFont(new Font("Arial", Font.BOLD, 13));
		textNombre.setText("Ingrese su nombre:");
		textNombre.setBounds(62, 43, 146, 20);
		getContentPane().add(textNombre);
		
		// PANEL DERECHO donde se muestra los grupos.
		JPanel panelResultado = new JPanel();
		panelResultado.setBackground(new Color(255, 255, 255));
		panelResultado.setBounds(260, 0, 424, 461);
		getContentPane().add(panelResultado);
		
		// Texto de INTERESES
		JTextPane textoIntereses = new JTextPane();
		textoIntereses.setBackground(new Color(192, 192, 192));
		textoIntereses.setEditable(false);
		textoIntereses.setFont(new Font("Arial", Font.BOLD, 13));
		textoIntereses.setText("  ¿Qué tan interesado está por los\r\n     siguientes géneros musicales?");
		textoIntereses.setBounds(10, 140, 240, 44);
		getContentPane().add(textoIntereses);
		
		// Separa el ingreso del nombre con los intereses.
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 114, 260, 11);
		getContentPane().add(separator);
		
		// Texto TANGO
		JTextPane textTango = new JTextPane();
		textTango.setText("Tango:");
		textTango.setFont(new Font("Arial", Font.BOLD, 13));
		textTango.setBackground(new Color(192, 192, 192));
		textTango.setBounds(34, 206, 52, 20);
		getContentPane().add(textTango);
		
		// BOTON para seleccionar 1-5 de TANGO
		JSpinner spinnerTango = new JSpinner();
		spinnerTango.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerTango.setBounds(34, 231, 52, 20);
		getContentPane().add(spinnerTango);
		
		// Texto FOLCLORE
		JTextPane textFolclore = new JTextPane();
		textFolclore.setEditable(false);
		textFolclore.setText("  Folclore:");
		textFolclore.setFont(new Font("Arial", Font.BOLD, 13));
		textFolclore.setBackground(Color.LIGHT_GRAY);
		textFolclore.setBounds(22, 272, 76, 20);
		getContentPane().add(textFolclore);
		
		// BOTON para seleccionar 1-5 de FOLKLORE
		JSpinner spinnerFolclore = new JSpinner();
		spinnerFolclore.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerFolclore.setBounds(34, 299, 52, 20);
		getContentPane().add(spinnerFolclore);
		
		// Texto de URBANO
		JTextPane textUrbano = new JTextPane();
		textUrbano.setText("  Urbano:");
		textUrbano.setFont(new Font("Arial", Font.BOLD, 13));
		textUrbano.setEditable(false);
		textUrbano.setBackground(Color.LIGHT_GRAY);
		textUrbano.setBounds(144, 272, 73, 20);
		getContentPane().add(textUrbano);
		
		// BOTON para seleccionar 1-5 de URBANO
		JSpinner spinnerUrbano = new JSpinner();
		spinnerUrbano.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerUrbano.setBounds(154, 299, 52, 20);
		getContentPane().add(spinnerUrbano);
		
		// Texto de ROCK NACIONAL
		JTextPane textRock = new JTextPane();
		textRock.setEditable(false);
		textRock.setText("Rock nacional:");
		textRock.setFont(new Font("Arial", Font.BOLD, 13));
		textRock.setBackground(Color.LIGHT_GRAY);
		textRock.setBounds(131, 206, 119, 20);
		getContentPane().add(textRock);
		
		// BOTON para seleccionar 1-5 de ROCK NACIONAL
		JSpinner spinnerRock = new JSpinner();
		spinnerRock.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerRock.setBounds(154, 231, 52, 20);
		getContentPane().add(spinnerRock);
		
		// BOTON para agregar NUEVO USUARIO
		JButton btnNuevoUsuario = new JButton("Agregar otra persona");
		btnNuevoUsuario.setEnabled(false);    // Desactivado por defecto.
		btnNuevoUsuario.setFont(new Font("Arial", Font.BOLD, 12));
		btnNuevoUsuario.setBounds(46, 351, 162, 23);
		getContentPane().add(btnNuevoUsuario);
		
		// Habilita el botón de agregar otra persona solo si hay un caracter (no admite espacios vacíos)
		_fieldNombre.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        btnNuevoUsuario.setEnabled(!_fieldNombre.getText().trim().isEmpty()); 
		    }
		});
		
		// BOTON de EJECUTAR
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setEnabled(false);    // Desactivado por defecto
		btnEjecutar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEjecutar.setBounds(46, 381, 162, 23);
		getContentPane().add(btnEjecutar);
		
		// De momento no va hasta q este el algoritmo
		//btnEjecutar.addActionListener(e -> listener.ejecutarAlgoritmo());
		
		
		btnNuevoUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombre = _fieldNombre.getText().trim();
		        int tango = (Integer) spinnerTango.getValue();
		        int urbano = (Integer) spinnerUrbano.getValue();
		        int rock = (Integer) spinnerRock.getValue();
		        int folclore = (Integer) spinnerFolclore.getValue();

		        // Guarda el usuario
		        if (_listener != null) {
		            _listener.agregarUsuario(nombre, tango, urbano, rock, folclore);
		        }
		    	
		        // Formatea la pantalla
		        _fieldNombre.setText("");
		        btnNuevoUsuario.setEnabled(false);
		        reestablecerBotones(spinnerTango, spinnerUrbano, spinnerRock, spinnerFolclore);

		        // Se habilita el boton de ejecutar si hay al menos 2 usuarios.
		        if (_listener.hayUsuariosSuficientes()) {
		            btnEjecutar.setEnabled(true);
		        }
		    }
		});
        
		mostrar();
	}

	private void mostrar() {
		setVisible(true);
	}
	
	// Reestablece el valor de los botones de intereses
	private void reestablecerBotones(JSpinner spinnerTango, JSpinner spinnerUrbano, JSpinner spinnerRock,
			JSpinner spinnerFolclore) {
		spinnerTango.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerUrbano.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerRock.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerFolclore.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		
	}
	
	public void mostrarResultado(String texto) {
	    _panelGrupos.setText(texto);
	}
	
    public void crearListener(ViewListener listener) {
        this._listener = listener;
    }
}
