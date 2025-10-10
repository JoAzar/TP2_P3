package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {
	
	private final int numVertices;
    private final List<Arista> aristas;
    
    public Grafo(int numVertices) {
    	this.numVertices = numVertices;
    	this.aristas = new ArrayList<>();
    }
    
    public void agregarArista(int origen, int destino, int peso) {
    	aristas.add(new Arista(origen, destino, peso));
    }
    //ups hay  clase grafo repetida ¡CUAL VORRAMOS?
    
    // Getters
    public int getNumVertices() {
    	return numVertices;
    }
    
    public List<Arista> getArista(){
    	return Collections.unmodifiableList(aristas);
    }
}
