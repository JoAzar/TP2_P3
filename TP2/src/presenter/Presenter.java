package presenter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Arista;
import model.Grafo;
import model.Similaridad;
import model.Usuario;
import view.View;
import view.ViewListener;

public class Presenter implements ViewListener {
	private View vista;
	private List<Usuario> usuarios;
    
    public void inicializarInterfazPresenter(View vista) {
        this.vista = vista;
        this.usuarios = new ArrayList<>();
        this.vista.crearListener(this);
    }
    
    // [Boton agregarUsuario] Se le pide la info del usuario, crea un objeto y lo guarda.
    @Override
    public void agregarUsuario() {
        
        String nombre = JOptionPane.showInputDialog("Nombre del usuario:");    	
        int tango = Integer.parseInt(JOptionPane.showInputDialog("Interés en el Tango (1-5):"));
        int folklore = Integer.parseInt(JOptionPane.showInputDialog("Interés en el Folklore (1-5):"));
        int rock = Integer.parseInt(JOptionPane.showInputDialog("Interés en el Rock (1-5):"));
        int urbano = Integer.parseInt(JOptionPane.showInputDialog("Interés en el género Urbano (1-5):"));
        
        validarDatos(nombre, tango, folklore, rock, urbano);

        Usuario u = new Usuario(nombre, tango, folklore, rock, urbano);
        usuarios.add(u);
        vista.agregarUsuarioALista(u.getNombre());
    }

    // [Boton ejecutar] Acá iria para que se calcule lo de los grupos
    @Override
    public void ejecutarAlgoritmo() {
    	if (usuarios.size() < 2) {
            JOptionPane.showMessageDialog(null, "Debe haber al menos 2 usuarios para ejecutar el algoritmo.");
            return;
        }

        // Se crea el grafo completo
        Grafo grafo = new Grafo(usuarios.size());
        
        // Todo esto de abajo NO queda, lo puse para probar no más!!
        StringBuilder sb = new StringBuilder("Similitud entre usuarios en porcentaje:\n\n");

        // Crear aristas
        for (int i = 0; i < usuarios.size(); i++) {
            for (int j = i + 1; j < usuarios.size(); j++) {
                int peso = Similaridad.calcularSimilaridad(usuarios.get(i), usuarios.get(j));
                grafo.agregarArista(i, j, peso);
                
                // Calcular similitud en porcentaje
                double similitud = 100 - (peso / 16.0) * 100;

                sb.append(usuarios.get(i).getNombre())
                  .append(" - ")
                  .append(usuarios.get(j).getNombre())
                  .append(" --> ")
                  .append(String.format("%.1f", similitud)).append("% similitud.\n");
            }
        }
        
        sb.append("\nResultados del algoritmo similaridad:\n");
        sb.append("(Cuanto más chico más parecidos, cuanto más grande más distintos)\n\n");
        for (Arista a : grafo.getArista()) {
            sb.append(usuarios.get(a.getOrigen()).getNombre())
              .append(" - ")
              .append(usuarios.get(a.getDestino()).getNombre())
              .append(" --> ")
              .append(" Similaridad: ").append(a.getPeso()).append(".\n");
        }

        vista.mostrarResultado(sb.toString());
    }
    
    // Esto se puede terminar sacando después.
    // Es para que no se guarden usuarios con datos invalidos pero hay que perfeccionarlo.
    private boolean validarDatos(String nombre, int tango, int folklore, int rock, int urbano) {
    	if (nombre == null || nombre.length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre.");
            return false;
        }
    	else if (tango < 1 || tango > 5) {
            JOptionPane.showMessageDialog(null, "Ingrese un número entre 1 y 5.");
            return false;
        }
    	else if (folklore < 1 || folklore > 5) {
            JOptionPane.showMessageDialog(null, "Ingrese un número entre 1 y 5.");
            return false;
        }
    	else if (rock < 1 || rock > 5) {
            JOptionPane.showMessageDialog(null, "Ingrese un número entre 1 y 5.");
            return false;
        }
    	else if (urbano < 1 || urbano > 5) {
            JOptionPane.showMessageDialog(null, "Ingrese un número entre 1 y 5.");
            return false;
        }
    	
    	return true;
    }
}
