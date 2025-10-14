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
	private View _vista;
	private List<Usuario> _usuarios;
    
    public void inicializarInterfazPresenter(View vista) {
        _vista = vista;
        _usuarios = new ArrayList<>();
        _vista.crearListener(this);
    }
    
    // [Boton agregar otra persona] Se le pide la info del usuario, crea un objeto y lo guarda.
    @Override
    public void agregarUsuario(String nombre, int tango, int folclore, int rock, int urbano) {
        Usuario u = new Usuario(nombre, mapDeGustos(tango, folclore, rock, urbano));
        _usuarios.add(u);
    }
    
	// [Boton ejecutar] Acá iria para que se calcule lo de los grupos y lo muestre por la view
    @Override
    public void ejecutarAlgoritmo() {

        // Se crea el grafo completo
    	Grafo grafoCompleto = crearGrafoCompleto();
    	
    	Map<Integer, List<Usuario>> grupos = grafoCompleto.crearComponentesConexas();
    	
    	StringBuilder resultado = new StringBuilder();
        
    }
    
    // [Boton reiniciar] Reinicia todo
    @Override
    public void reiniciarSistema() {
    	_usuarios.clear();
    	
    	// Debería limpiar lso resultados también
    	
    	
    }
    
	public Grafo crearGrafoCompleto() {
		Grafo grafo = new Grafo();

	    for (Usuario u : _usuarios) {
	        grafo.agregarVertice(u);
	    }
	    
	    grafo.crearGrafoCompleto();
	    
		return grafo;
	}

    private Map<String, Integer> mapDeGustos(int tango, int folclore, int rock, int urbano) {
    	Map<String, Integer> gustos = new HashMap<>();
    	
    	gustos.put("tango", tango);
    	gustos.put("folclore", folclore);
    	gustos.put("rock", rock);
    	gustos.put("urbano", urbano);
    	
    	return gustos;
	}
    
    @Override
	public boolean hayUsuariosSuficientes() {
    	if(_usuarios.size() >= 2) {
    		return true;
    	}
    	return false;
    }
    
    @Override
    public boolean sePuedeHabilitarReinicio() {
    	if(_usuarios.size() == 1) {
    		return true;
    	}
    	return false;
    }
    
}
