package view;

import javax.swing.*;

import model.Usuario;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class View extends JFrame{
	private ViewListener _listener;
	private JTextField _fieldNombre;
	private JTextArea _panelGrupos;
    private JPanel panelResultado;

	
	public View() {
		getContentPane().setBackground(new Color(192, 192, 192));
		inicializar();
	}
	
	private void inicializar() {
		setTitle("Grupos de similaridad por gustos musicales");
		setBounds(100, 100, 700, 500); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//ESTE ES EL PANEL DE LOS GRUPOS LUEGO DE TOCAR EL BOTON para ver grupos
		_panelGrupos = new JTextArea();
	    _panelGrupos.setEditable(false);
	    _panelGrupos.setBackground(Color.BLACK);
	    
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
		
		panelResultado = new JPanel();
		panelResultado.setBackground(new Color(255, 255, 255));
		panelResultado.setBounds(260, 0, 424, 461);
		getContentPane().add(panelResultado);
		
		JTextPane textoIntereses = new JTextPane();
		textoIntereses.setBackground(new Color(192, 192, 192));
		textoIntereses.setEditable(false);
		textoIntereses.setFont(new Font("Arial", Font.BOLD, 13));
		textoIntereses.setText("¿Qué tan interesado está por los siguientes géneros musicales?");
		textoIntereses.setBounds(10, 140, 240, 44);
		getContentPane().add(textoIntereses);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 114, 260, 11);
		getContentPane().add(separator);
		
		JTextPane textTango = new JTextPane();
		textTango.setText("Tango:");
		textTango.setFont(new Font("Arial", Font.BOLD, 13));
		textTango.setBackground(new Color(192, 192, 192));
		textTango.setBounds(34, 206, 52, 20);
		getContentPane().add(textTango);
		
		JSpinner spinnerTango = new JSpinner();
		spinnerTango.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerTango.setBounds(34, 231, 52, 20);
		getContentPane().add(spinnerTango);
		
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
		btnEjecutar.setEnabled(false);
		btnEjecutar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEjecutar.setBounds(46, 381, 162, 23);
		getContentPane().add(btnEjecutar);
		
		// BOTON REINICIAR
		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.setFont(new Font("Arial", Font.BOLD, 12));
		btnReiniciar.setEnabled(false);
		btnReiniciar.setBounds(46, 411, 162, 23);
		getContentPane().add(btnReiniciar);
		
		//LISTENERS					
		btnNuevoUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombre = _fieldNombre.getText().trim();
		        int tango = (Integer) spinnerTango.getValue();
		        int urbano = (Integer) spinnerUrbano.getValue();
		        int rock = (Integer) spinnerRock.getValue();
		        int folclore = (Integer) spinnerFolclore.getValue();

		        if(_listener != null) _listener.agregarUsuarioALaListaDeUsuarios(nombre, tango, urbano, rock, folclore);
		        
		        _fieldNombre.setText("");
		        btnNuevoUsuario.setEnabled(false);
		        reestablecerBotones(spinnerTango, spinnerUrbano, spinnerRock, spinnerFolclore);
		        actualizarVistaUsuarios();
		        if(_listener.hayUsuariosSuficientes()) btnEjecutar.setEnabled(true);
		        if(_listener.sePuedeHabilitarReinicio()) btnReiniciar.setEnabled(true);
		       
		    }
		});
		
		btnReiniciar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	_listener.reiniciarSistema();
		    	 _fieldNombre.setText("");
		         btnNuevoUsuario.setEnabled(true);
		         btnEjecutar.setEnabled(false);
		         btnReiniciar.setEnabled(false);
		         reestablecerBotones(spinnerTango, spinnerUrbano, spinnerRock, spinnerFolclore);
		    }
		 });
		btnEjecutar.addActionListener(e -> _listener.ejecutarAlgoritmo());
		mostrar();
	}
	
	private void actualizarVistaUsuarios() {
	    JPanel panelUsuarios = new JPanel();
	    panelUsuarios.setLayout(new BoxLayout(panelUsuarios, BoxLayout.Y_AXIS));
	    List<Usuario> usuarios = _listener.getUsuarios();
	    panelUsuarios.removeAll();
	    for(Usuario usuario : usuarios) {
	        JLabel label = new JLabel(usuario.getNombre());
	        label.setFont(new Font("Arial", Font.PLAIN, 14));
	        panelUsuarios.add(label);
	    }
	    panelResultado.removeAll();
	    panelResultado.add(panelUsuarios);
	    panelResultado.revalidate();
	    panelResultado.repaint();
	}


	private void mostrar() {
		setVisible(true);
	}
	
	private void reestablecerBotones(JSpinner spinnerTango, JSpinner spinnerUrbano, JSpinner spinnerRock,
			JSpinner spinnerFolclore) {
		spinnerTango.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerUrbano.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerRock.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerFolclore.setModel(new SpinnerNumberModel(1, 1, 5, 1));
	}
	
	public void avisoReinicioCorrecto(String mensaje) {
	    JOptionPane.showMessageDialog(this, mensaje);
	}
	
    public void crearListener(ViewListener listener) {
        this._listener = listener;
    }
    
    public void mostrarGrupos(Map<Integer, List<Usuario>> gruposDeUsuarios) {
        panelResultado.removeAll();
        JPanel panelDeGrupos = new JPanel();
        configuracionDeTamanioDelPanelExternoDeGrupos(panelDeGrupos);

        for(Map.Entry<Integer, List<Usuario>> entry : gruposDeUsuarios.entrySet()) {
            JPanel grupoPanel = new JPanel();
            configuracionDeTamanioDelPanelInternoDeGrupo(grupoPanel);
            grupoPanel.setBorder(BorderFactory.createTitledBorder("Grupo " + entry.getKey()));
            for(Usuario usuario : entry.getValue()) {
                grupoPanel.add(new JLabel(usuario.getNombre()));
            }
            panelDeGrupos.add(grupoPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panelDeGrupos);
        scrollPane.setPreferredSize(new Dimension(400, 450));
        panelResultado.add(scrollPane);
        panelResultado.revalidate();
        panelResultado.repaint();
    }

    public void configuracionDeTamanioDelPanelExternoDeGrupos(JPanel panelDeGrupos) {
        panelDeGrupos.setLayout(new BoxLayout(panelDeGrupos, BoxLayout.Y_AXIS));
        panelDeGrupos.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }

    public void configuracionDeTamanioDelPanelInternoDeGrupo(JPanel grupoPanel) {
        grupoPanel.setLayout(new BoxLayout(grupoPanel, BoxLayout.Y_AXIS));
        grupoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    
}
