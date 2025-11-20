package model;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Grafo<T> {
    private List<T> _vertices;
    private List<Arista<T>> _aristas;

    public Grafo() {
        _vertices = new ArrayList<>();
        _aristas = new ArrayList<>();
    }

    public void agregarVertice(T vertice) {
    	if(vertice == null) {
    		throw new IllegalArgumentException("El vértice no puede ser nulo");
    	}
    	if(!_vertices.contains(vertice)) {
    		_vertices.add(vertice);
    	}
    }

    public void agregarArista(T origen, T destino, int peso) {
        if(!existeArista(origen, destino)) {
        	_aristas.add(new Arista<>(origen, destino, peso));
        }
    }
    
    private boolean existeArista(T origen, T destino) {
        for(Arista<T> a : _aristas) {
        	if(a.getOrigen().equals(origen) && a.getDestino().equals(destino)) {
        		return true;
        	}
        }
        return false;
    }
    
	public void crearGrafoCompleto(BiFunction<T, T, Integer> generadorDePeso) {
	    for (int i = 0; i < _vertices.size(); i++) {
	        for (int j = i + 1; j < _vertices.size(); j++) {
	            T primerVer = _vertices.get(i);
	            T segundoVer = _vertices.get(j);
	            int peso = generadorDePeso.apply(primerVer, segundoVer);
	            agregarArista(primerVer, segundoVer, peso);
	        }
	    }
    }

	public List<T> getVertices() {
		return new ArrayList<>(_vertices);
	}
	
    public List<Arista<T>> getAristas() {
    	return new ArrayList<>(_aristas);
    }
	
}