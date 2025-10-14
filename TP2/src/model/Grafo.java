package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private List<Usuario> vertices;
    private List<Arista> aristas;

    public Grafo() {
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarVertice(Usuario usuario) {
    	if(usuario == null) throw new IllegalArgumentException("El usuario no puede ser nulo");
    	if(!vertices.contains(usuario)) vertices.add(usuario);
    }

    public void agregarArista(Usuario origen, Usuario destino, int peso) {
        if(!existeArista(origen, destino)) aristas.add(new Arista(origen, destino, peso));
    }
    
    // Chequeo si existe una arista entre dos vertices
    private boolean existeArista(Usuario origen, Usuario destino) {
        for(Arista a : aristas)
            if(a.getOrigen().equals(origen) && a.getDestino().equals(destino)) return true;
        return false;
    }
    
    public void crearGrafoCompleto() {
	    for (int i = 0; i < vertices.size(); i++) {
	        for (int j = i + 1; j < vertices.size(); j++) {
	            Usuario u1 = vertices.get(i);
	            Usuario u2 = vertices.get(j);
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
    	
    	// Sacamos la arista mas grande
    	Collections.sort(agm, Collections.reverseOrder());
    	agm.remove(0);
    	
    	Map<Usuario, Usuario> padre = inicializarPadres();
    	for(Arista a : agm) {
    		union(a.getOrigen(), a.getDestino(), padre);
    	}
    	
    	Map<Integer, List<Usuario>> componentesConexas = enumerarComponentes(crearComponentes(padre));
    	    	
    	
    	return componentesConexas;
    }
    
    // No sé si va acá o en el Presenter
    public List<Arista> crearAGM() {
        List<Arista> agm = new ArrayList<>();
        
        Collections.sort(aristas);
        // union find
        Map<Usuario, Usuario> padre = inicializarPadres();
        
        for(Arista a : aristas) {
        	Usuario u1 = a.getOrigen();
        	Usuario u2 = a.getDestino();
        	
        	Usuario raiz1 = find(u1, padre);
        	Usuario raiz2 = find(u2, padre);
        	
        	if(raiz1 != raiz2) {
        		agm.add(a);
        		union(raiz1, raiz2, padre);
        	}
        	
        	if(agm.size() == vertices.size() - 1) {
        		break;
        	}
        }
        return agm;
    }
    
    private Usuario find(Usuario user, Map<Usuario, Usuario> padre) {
    	while(padre.get(user) != user) {
    		user = padre.get(user);
    	}
    	return user;
    }
    
    private void union(Usuario u1, Usuario u2, Map<Usuario, Usuario> padre) {
    	Usuario raiz1 = find(u1, padre);
    	Usuario raiz2 = find(u2, padre);
    	padre.put(raiz1, raiz2);
    }
    
    private Map<Usuario, Usuario> inicializarPadres(){
    	Map<Usuario, Usuario> padre = new HashMap<>();
    	for(Usuario u : vertices) {
    		padre.put(u, u);
    	}
    	return padre;
    }
    
    private Map<Usuario, List<Usuario>> crearComponentes(Map<Usuario, Usuario> padre){
        Map<Usuario, List<Usuario>> comp = new HashMap<>();
        
        for (Usuario u : vertices) {
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
	
    // Getters
	public List<Usuario> getVertices() {return new ArrayList<Usuario>(vertices);}
    public List<Arista> getAristas() {return new ArrayList<Arista>(aristas);}


	
}