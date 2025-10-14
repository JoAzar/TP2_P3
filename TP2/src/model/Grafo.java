package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private List<Usuario> _vertices;
    private List<Arista> _aristas;

    public Grafo() {
        _vertices = new ArrayList<>();
        _aristas = new ArrayList<>();
    }

    public void agregarVertice(Usuario usuario) {
    	if(usuario == null) {
    		throw new IllegalArgumentException("El usuario no puede ser nulo");
    	}
    	if(!_vertices.contains(usuario)) {
    		_vertices.add(usuario);
    	}
    }

    public void agregarArista(Usuario origen, Usuario destino, int peso) {
        if(!existeArista(origen, destino)) {
        	_aristas.add(new Arista(origen, destino, peso));
        }
    }
    
    private boolean existeArista(Usuario origen, Usuario destino) {
        for(Arista a : _aristas) {
        	if(a.getOrigen().equals(origen) && a.getDestino().equals(destino)) {
        		return true;
        	}
        }
        return false;
    }
    
    public void crearGrafoCompleto() {
	    for (int i = 0; i < _vertices.size(); i++) {
	        for (int j = i + 1; j < _vertices.size(); j++) {
	            Usuario u1 = _vertices.get(i);
	            Usuario u2 = _vertices.get(j);
	            int peso = u1.calculoSimilaridad(u2);
	            agregarArista(u1, u2, peso);
	        }
	    }
    }
    
    public Map<Integer, List<Usuario>> crearComponentesConexas(){
    	List<Arista> agm = crearAGM();
    	
    	if(agm.isEmpty()) {
    		return new HashMap<>();
    	}

    	Collections.sort(agm, Collections.reverseOrder());
    	agm.remove(0);
    	
    	Map<Usuario, Usuario> padre = inicializarPadres();
    	for(Arista a : agm) {
    		unionSinRaiz(a.getOrigen(), a.getDestino(), padre);
    	}
    	
    	Map<Integer, List<Usuario>> componentesConexas = enumerarComponentes(crearComponentes(padre));
    	    	
    	
    	return componentesConexas;
    }
    
    public List<Arista> crearAGM() {
        List<Arista> agm = new ArrayList<>();
        
        Collections.sort(_aristas);

        Map<Usuario, Usuario> padre = inicializarPadres();
        
        for(Arista a : _aristas) {
        	Usuario u1 = a.getOrigen();
        	Usuario u2 = a.getDestino();
        	
        	Usuario raiz1 = find(u1, padre);
        	Usuario raiz2 = find(u2, padre);
        	
        	if(raiz1 != raiz2) {
        		agm.add(a);
        		unionConRaiz(raiz1, raiz2, padre);
        	}
        	
        	if(agm.size() == _vertices.size() - 1) {
        		break;
        	}
        }
        return agm;
    }
    
    private Map<Usuario, List<Usuario>> crearComponentes(Map<Usuario, Usuario> padre){
        Map<Usuario, List<Usuario>> comp = new HashMap<>();
        
        for (Usuario u : _vertices) {
            Usuario raiz = find(u, padre);
            if (!comp.containsKey(raiz)) {
                comp.put(raiz, new ArrayList<>());
            }
            comp.get(raiz).add(u);
        }
        
        return comp;
    }
    
    private Map<Integer, List<Usuario>> enumerarComponentes(Map<Usuario, List<Usuario>> componentes){
    	Map<Integer, List<Usuario>> comp = new HashMap<>();
        int numGrupo = 1;
        
        for (List<Usuario> lista : componentes.values()) {
            comp.put(numGrupo, lista);
            numGrupo++;
        }
        
        return comp;
    }
    
    private Usuario find(Usuario user, Map<Usuario, Usuario> padre) {
    	while(padre.get(user) != user) {
    		user = padre.get(user);
    	}
    	return user;
    }
    
    private void unionSinRaiz(Usuario u1, Usuario u2, Map<Usuario, Usuario> padre) {
    	Usuario raiz1 = find(u1, padre);
    	Usuario raiz2 = find(u2, padre);
    	padre.put(raiz1, raiz2);
    }
    
    private void unionConRaiz(Usuario r1, Usuario r2, Map<Usuario, Usuario> padre) {
    	padre.put(r1, r2);
    }
    
    private Map<Usuario, Usuario> inicializarPadres(){
    	Map<Usuario, Usuario> padre = new HashMap<>();
    	for(Usuario u : _vertices) {
    		padre.put(u, u);
    	}
    	return padre;
    }

	public List<Usuario> getVertices() {
		return new ArrayList<Usuario>(_vertices);
	}
	
    public List<Arista> getAristas() {
    	return new ArrayList<Arista>(_aristas);
    }
	
}