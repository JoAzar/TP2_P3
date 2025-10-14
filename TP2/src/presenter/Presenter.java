package presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Grafo;
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
    
    // [Boton agregar otra persona] Se le pide la info del usuario, crea un objeto y lo guarda.
    @Override
    public void agregarUsuario(String nombre, int tango, int folclore, int rock, int urbano) {
        Usuario u = new Usuario(nombre, mapDeGustos(tango, folclore, rock, urbano));
        usuarios.add(u);
    }
    
	// [Boton ejecutar] Acá iria para que se calcule lo de los grupos y lo muestre por la view
    @Override
    public void ejecutarAlgoritmo() {

        // Se crea el grafo completo
    	Grafo grafoCompleto = crearGrafoCompleto();
    	
    	Map<Integer, List<Usuario>> grupos = grafoCompleto.crearComponentesConexas();
    	
    	StringBuilder resultado = new StringBuilder();
        
    }

    // Crea Map de gustos.
    private Map<String, Integer> mapDeGustos(int tango, int folclore, int rock, int urbano) {
    	Map<String, Integer> gustos = new HashMap<>();
    	
    	gustos.put("tango", tango);
    	gustos.put("folclore", folclore);
    	gustos.put("rock", rock);
    	gustos.put("urbano", urbano);
    	
    	return gustos;
	}
    
    
    // Chequeo para saber si hay usuarios suficientes para ejecutar el algoritmo
    @Override
	public boolean hayUsuariosSuficientes() {
    	if(usuarios.size() >= 2) {
    		return true;
    	}
    	return false;
    }
    
	public Grafo crearGrafoCompleto() {
		Grafo grafo = new Grafo();

	    for (Usuario u : usuarios) {
	        grafo.agregarVertice(u);
	    }
	    
	    grafo.crearGrafoCompleto();
	    
		return grafo;
	}
    
}
