package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View {
	private JFrame frame;
	private JTextArea panelGrupos;
	private DefaultListModel<String> listaModel;
	private JList<String> listaUsuarios;
	private JButton btnAgregarUsuario;
	private JButton btnEjecutar;
	private ViewListener listener;
	
	public View() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("No se como llamarlo");
		frame.setBounds(100, 100, 700, 500); //posición X e Y, tamaño ancho | alto
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new BorderLayout());
	}

	private void mostrar() {
		frame.setVisible(true);
	}
	
	
	public void crearFrame() {
		crearFrameView();
		mostrar();
	}
	
	private void crearFrameView() {
		// Inicializar panel izquierdo
        listaModel = new DefaultListModel<>();   // Guarda los nombres de los usuarios
        listaUsuarios = new JList<>(listaModel);    // Muestra los usuarios en IU
        JScrollPane scrollLista = new JScrollPane(listaUsuarios);
        
        // Panel que muestra la lista de usuarios al costado
        JPanel panelIzquierda = new JPanel(new BorderLayout());
        panelIzquierda.add(scrollLista, BorderLayout.CENTER);
        listaUsuarios.setBackground(Color.LIGHT_GRAY);
        ajustarVistaListaUsuarios();

        // Botones (tambien al costado)
        btnAgregarUsuario = new JButton("Agregar usuario");
        btnEjecutar = new JButton("Ejecutar");

        JPanel panelBotones = new JPanel(new GridLayout(2, 1));
        panelBotones.add(btnAgregarUsuario);
        panelBotones.add(btnEjecutar);
        panelIzquierda.add(panelBotones, BorderLayout.SOUTH);

        frame.add(panelIzquierda, BorderLayout.WEST);

        // Panel que muestra los grupos resultantes
        panelGrupos = new JTextArea();
        panelGrupos.setEditable(false);
        JScrollPane scrollGrupos = new JScrollPane(panelGrupos);
        frame.add(scrollGrupos, BorderLayout.CENTER);

        // Listeners
        btnAgregarUsuario.addActionListener(e -> {
            if (listener != null) listener.agregarUsuario();
        });

        btnEjecutar.addActionListener(e -> {
            if (listener != null) listener.ejecutarAlgoritmo();
        });
        
        
        frame.revalidate();
	    frame.repaint();
	}
	
	// Ajusta los colores y estilos de la lista de usuarios.
	private void ajustarVistaListaUsuarios() {
		listaUsuarios.setCellRenderer(new DefaultListCellRenderer() {
		    @Override
		    public Component getListCellRendererComponent(JList<?> list,
		                                                  Object value,
		                                                  int index,
		                                                  boolean isSelected,
		                                                  boolean cellHasFocus) {
		        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        label.setHorizontalAlignment(SwingConstants.CENTER); // Centra el nombre del usuario
		        label.setFont(new Font("Arial", Font.BOLD, 18));   // Tamaño y font
		        label.setForeground(Color.MAGENTA);             // Color del texto
		        label.setBackground(Color.WHITE);      // Color del fondo
		        return label;
		    }
		});	
	}
	
	// Agrega el nombre ingresado a la lista para mostrarlo después por pantalla.
	public void agregarUsuarioALista(String nombre) {
        listaModel.addElement(nombre);
    }
	
	public void mostrarResultado(String texto) {
	    panelGrupos.setText(texto);
	}
	
    public void crearListener(ViewListener listener) {
        this.listener = listener;
    }
}
