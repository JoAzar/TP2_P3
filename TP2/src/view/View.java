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
		setBounds(110, 110, 700, 520); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		_panelGrupos = new JTextArea();
	    _panelGrupos.setBackground(Color.BLACK);
	    JScrollPane scroll = new JScrollPane(_panelGrupos);
	    scroll.setBounds(270, 10, 410, 480);
	    getContentPane().add(scroll);
	    _panelGrupos.setEditable(false);
	    _panelGrupos.setForeground(Color.WHITE);
	    _panelGrupos.setFont(new Font("Consolas", Font.PLAIN, 14));
	    _panelGrupos.setLineWrap(true);
	    _panelGrupos.setWrapStyleWord(true);
	    
		_fieldNombre = new JTextField();
		_fieldNombre.setBounds(46, 68, 162, 20);
		getContentPane().add(_fieldNombre);
		_fieldNombre.setColumns(10);
		
		JTextPane textNombre = new JTextPane();
		noPermitirSenialarSobreElPanel(textNombre);
		configurarTamaniosDeVentanaNombreDeUsuario(textNombre);
		getContentPane().add(textNombre);
		
		panelResultado = new JPanel();
		configurarTamaniosDeVentanapanelDeResultado(panelResultado);
		getContentPane().add(panelResultado);
		
		JTextPane textoIntereses = new JTextPane();
		configurarTamaniosDeVentanaTextoDeInteres(textoIntereses);
		noPermitirSenialarSobreElPanel(textoIntereses);
		getContentPane().add(textoIntereses);
		
		JSeparator rayaQueSeparaElTexto = new JSeparator();
		rayaQueSeparaElTexto.setBounds(0, 114, 260, 11);
		getContentPane().add(rayaQueSeparaElTexto);
		
		JTextPane textoSeleccionTango = new JTextPane();
		configurarTamaniosDeVentanaTextoDeSeleccionTango(textoSeleccionTango);
		noPermitirSenialarSobreElPanel(textoSeleccionTango);
		getContentPane().add(textoSeleccionTango);
		
		JSpinner spinnerTango = new JSpinner();
		spinnerTango.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerTango.setBounds(34, 231, 52, 20);
		getContentPane().add(spinnerTango);
		
		JTextPane textoSeleccionFolclore = new JTextPane();
		noPermitirSenialarSobreElPanel(textoSeleccionFolclore);
		configurarTamaniosDeVentanaTextoDeSeleccionFolclore(textoSeleccionFolclore);
		getContentPane().add(textoSeleccionFolclore);
		
		JSpinner spinnerFolclore = new JSpinner();
		spinnerFolclore.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerFolclore.setBounds(34, 299, 52, 20);
		getContentPane().add(spinnerFolclore);
		
		JTextPane textoSeleccionUrbano = new JTextPane();
		configurarTamaniosDeVentanaTextoDeSeleccionUrbano(textoSeleccionUrbano);
		noPermitirSenialarSobreElPanel(textoSeleccionUrbano);
		getContentPane().add(textoSeleccionUrbano);
		
		JSpinner spinnerUrbano = new JSpinner();
		spinnerUrbano.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerUrbano.setBounds(154, 299, 52, 20);
		getContentPane().add(spinnerUrbano);
		
		JTextPane textoSeleccionRock = new JTextPane();
		configurarTamaniosDeVentanaTextoDeSeleccionRock(textoSeleccionRock);
		noPermitirSenialarSobreElPanel(textoSeleccionRock);
		getContentPane().add(textoSeleccionRock);
		
		JSpinner spinnerRock = new JSpinner();
		spinnerRock.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerRock.setBounds(154, 231, 52, 20);
		getContentPane().add(spinnerRock);
		
		JButton btnNuevoUsuario = new JButton("Agregar persona");
		noPermitirSenialarSobreElPanel(textoSeleccionUrbano);
		botonDeAgregarNuevaPersonaInicial(btnNuevoUsuario);
		getContentPane().add(btnNuevoUsuario);
		esperarElIngresoDeNombreDeUsuario(btnNuevoUsuario);
			
		JButton btnEjecutar = new JButton("Ejecutar");
		noPermitirSenialarSobreElPanel(btnEjecutar);
		botonParaEjecutarPrograma(btnEjecutar);
		getContentPane().add(btnEjecutar);
		
		JButton btnReiniciar = new JButton("Reiniciar");
		noPermitirSenialarSobreElPanel(btnReiniciar);
		botonParaReiniciarPrograma(btnReiniciar);
		getContentPane().add(btnReiniciar);
		
		JButton btnPromedio = new JButton("Ver promedio");
		noPermitirSenialarSobreElPanel(btnReiniciar);
		botonParaPromediarPrograma(btnPromedio);
		getContentPane().add(btnPromedio);
						
		asociarValoresDeGustosMusicalesConUsuario(btnNuevoUsuario, btnEjecutar, btnReiniciar, spinnerTango, spinnerRock, spinnerFolclore, spinnerUrbano);
		reiniciarAsociacionDeValoresDeGustosMusicalesConUsuario(btnNuevoUsuario, btnEjecutar, btnReiniciar, spinnerTango, spinnerRock, spinnerFolclore, spinnerUrbano);
		asociarUsuarios(btnEjecutar);
		calcularElPromedioDeGustosMusicalesDeUsuarios(btnPromedio);
		mostrar();
	}
	
	private void asociarValoresDeGustosMusicalesConUsuario(JButton botonDeNuevoUsuario, JButton botonDeReiniciar, JButton botonDeEjecutar, JSpinner spinnerTango, JSpinner spinnerUrbano, JSpinner spinnerRock, JSpinner spinnerFolclore) {
		botonDeNuevoUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombre = _fieldNombre.getText().trim();
		        int tango = (Integer) spinnerTango.getValue();
		        int urbano = (Integer) spinnerUrbano.getValue();
		        int rock = (Integer) spinnerRock.getValue();
		        int folclore = (Integer) spinnerFolclore.getValue();
		        if(_listener != null) _listener.agregarUsuarioALaListaDeUsuarios(nombre, tango, urbano, rock, folclore);
		        _fieldNombre.setText("");
		        botonDeNuevoUsuario.setEnabled(false);
		        reestablecerBotones(spinnerTango, spinnerUrbano, spinnerRock, spinnerFolclore);
		        actualizarVistaUsuarios();
		        if(_listener.hayUsuariosSuficientes()) botonDeEjecutar.setEnabled(true);
		        if(_listener.sePuedeHabilitarReinicio()) botonDeReiniciar.setEnabled(true);
		    }
		});
	}
	
	private void reiniciarAsociacionDeValoresDeGustosMusicalesConUsuario(JButton botonDeNuevoUsuario, JButton botonDeReiniciar, JButton botonDeEjecutar, JSpinner spinnerTango, JSpinner spinnerUrbano, JSpinner spinnerRock, JSpinner spinnerFolclore) {
		botonDeReiniciar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	_listener.reiniciarSistema();
		    	 _fieldNombre.setText("");
		         botonDeNuevoUsuario.setEnabled(true);
		         botonDeEjecutar.setEnabled(false);
		         botonDeReiniciar.setEnabled(false);
		         reestablecerBotones(spinnerTango, spinnerUrbano, spinnerRock, spinnerFolclore);
		    }
		 });
	}
	
	private void asociarUsuarios(JButton botonDeEjecutar) {
		botonDeEjecutar.addActionListener(e -> _listener.ejecutarAlgoritmo());
	}
	
	public void calcularElPromedioDeGustosMusicalesDeUsuarios(JButton botonDePromedio) {
		botonDePromedio.addActionListener(e -> {if(_listener != null) _listener.calcularPromedioInteres();});
	}
	
	private void noPermitirSenialarSobreElPanel(JTextPane panelSeleccionado) {
		panelSeleccionado.setEditable(false);   
		panelSeleccionado.setHighlighter(null);
		panelSeleccionado.setFocusable(false);
	}
	
	private void noPermitirSenialarSobreElPanel(JButton panelSeleccionado) {
		panelSeleccionado.setFocusable(false);
	}
	
	private void configurarTamaniosDeVentanaNombreDeUsuario(JTextPane panelSeleccionado) {
		panelSeleccionado.setBackground(new Color(192, 192, 192));
		panelSeleccionado.setFont(new Font("Arial", Font.BOLD, 13));
		panelSeleccionado.setText("Nombre de usuario:");
		panelSeleccionado.setBounds(62, 43, 146, 20);
	}
	
	private void configurarTamaniosDeVentanapanelDeResultado(JPanel panelResultado) {
		int altura = 450;
		int anchura = 400;
		int margen = 10;
		int comienzoDelPanel = 270;
		panelResultado.setBackground(new Color(0, 0, 0));
		panelResultado.setBounds(comienzoDelPanel, margen, anchura, altura);
	}
	
	private void configurarTamaniosDeVentanaTextoDeInteres(JTextPane textoDeInteres) {
		textoDeInteres.setBackground(new Color(192, 192, 192));		
		textoDeInteres.setFont(new Font("Arial", Font.BOLD, 13));
		textoDeInteres.setText("Interés en géneros musicales");
		textoDeInteres.setBounds(10, 140, 240, 44);
    }
	
	private void configurarTamaniosDeVentanaTextoDeSeleccionTango(JTextPane textoDeSeleccionTango) {
		textoDeSeleccionTango.setBounds(34, 206, 52, 20);
		textoDeSeleccionTango.setText("Tango");
		textoDeSeleccionTango.setFont(new Font("Arial", Font.BOLD, 13));
		textoDeSeleccionTango.setBackground(new Color(192, 192, 192));
	}
	
	private void configurarTamaniosDeVentanaTextoDeSeleccionFolclore(JTextPane textoDeSeleccionFolclore) {
		textoDeSeleccionFolclore.setText("Folclore");
		textoDeSeleccionFolclore.setFont(new Font("Arial", Font.BOLD, 13));
		textoDeSeleccionFolclore.setBackground(Color.LIGHT_GRAY);
		textoDeSeleccionFolclore.setBounds(22, 272, 76, 20);
	}
	
	private void configurarTamaniosDeVentanaTextoDeSeleccionUrbano(JTextPane textoDeSeleccionUrbano) {
		textoDeSeleccionUrbano.setText("Urbano");
		textoDeSeleccionUrbano.setFont(new Font("Arial", Font.BOLD, 13));
		textoDeSeleccionUrbano.setEditable(false);
		textoDeSeleccionUrbano.setBackground(Color.LIGHT_GRAY);
		textoDeSeleccionUrbano.setBounds(144, 272, 73, 20);
	}
	
	private void configurarTamaniosDeVentanaTextoDeSeleccionRock(JTextPane textoDeSeleccionRock) {
		textoDeSeleccionRock.setEditable(false);
		textoDeSeleccionRock.setText("Rock");
		textoDeSeleccionRock.setFont(new Font("Arial", Font.BOLD, 13));
		textoDeSeleccionRock.setBackground(Color.LIGHT_GRAY);
		textoDeSeleccionRock.setBounds(131, 206, 119, 20);
	}
	
	private void botonDeAgregarNuevaPersonaInicial(JButton botonNuevoUsuario) {
		botonNuevoUsuario.setEnabled(false);
		botonNuevoUsuario.setFont(new Font("Arial", Font.BOLD, 12));
		botonNuevoUsuario.setBounds(46, 351, 162, 23);
	}
	
	private void botonParaEjecutarPrograma(JButton botonDeEjecutar) {
		botonDeEjecutar.setEnabled(false);
		botonDeEjecutar.setFont(new Font("Arial", Font.BOLD, 12));
		botonDeEjecutar.setBounds(46, 381, 162, 23);
	}
	
	private void botonParaReiniciarPrograma(JButton botonDeReiniciar) {
		botonDeReiniciar.setFont(new Font("Arial", Font.BOLD, 12));
		botonDeReiniciar.setEnabled(false);
		botonDeReiniciar.setBounds(46, 411, 162, 23);
	}
	
	private void botonParaPromediarPrograma(JButton botonDePromedio) {
		botonDePromedio.setFont(new Font("Arial", Font.BOLD, 12));
		botonDePromedio.setBounds(46, 441, 162, 23);
	}
	
	private void esperarElIngresoDeNombreDeUsuario(JButton botonNuevoUsuario) {
		_fieldNombre.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        botonNuevoUsuario.setEnabled(!_fieldNombre.getText().trim().isEmpty()); 
		    }
		});
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

	public void mostrarPromedioInteres(Map<String, Double> promedios) {
		StringBuilder mensaje = new StringBuilder("Promedio de intereses por género\n");
	    for(Map.Entry<String, Double> entry : promedios.entrySet()) {
	        mensaje.append(entry.getKey())
	               .append(": ")
	               .append(String.format("%.2f", entry.getValue()))
	               .append("\n");
	    }
	    javax.swing.JOptionPane.showMessageDialog(
	        null, 
	        mensaje.toString(), 
	        "Promedio de Interes de todos los usuarios", 
	        javax.swing.JOptionPane.INFORMATION_MESSAGE
	    );
	}
    
}
