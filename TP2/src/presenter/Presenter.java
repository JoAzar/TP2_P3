package presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Arista;
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
    
	// [Boton ejecutar] Acá iria para que se calcule lo de los grupos
    @Override
    public void ejecutarAlgoritmo() {

        // Se crea el grafo completo
    	Grafo grafoCompleto = crearGrafoCompleto();
        
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

	    // Calculo de peso para las aristas
	    for (int i = 0; i < usuarios.size(); i++) {
	        for (int j = i + 1; j < usuarios.size(); j++) {
	            Usuario u1 = usuarios.get(i);
	            Usuario u2 = usuarios.get(j);
	            int peso = u1.calculoSimilaridad(u2);
	            grafo.agregarArista(u1, u2, peso);
	        }
	    }
	    
		return grafo;
	}
    
}
