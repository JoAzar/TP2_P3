package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private List<Usuario> vertice;
    private List<Arista> arista;

    public Grafo() {
        vertice = new ArrayList<>();
        arista = new ArrayList<>();
    }

    public void agregarVertice(Usuario usuario) {
    	if(usuario == null) throw new IllegalArgumentException("El usuario no puede ser nulo");
    	if(!vertice.contains(usuario)) vertice.add(usuario);
    }

    public void agregarArista(Usuario origen, Usuario destino, int peso) {
        if(!existeArista(origen, destino)) arista.add(new Arista(origen, destino, peso));
    }
    
    // Chequeo si existe una arista entre dos vertices
    private boolean existeArista(Usuario origen, Usuario destino) {
        for(Arista a : arista)
            if(a.getOrigen().equals(origen) && a.getDestino().equals(destino)) return true;
        return false;
    }
    
    // No sé si va acá o en el Presenter
    public List<Arista> generarAGM() {
        List<Arista> agm = new ArrayList<>();
        List<Arista> aristas = new ArrayList<>(getAristas());

        return null;
    }
	
    // Getters
	public List<Usuario> getVertices() {return new ArrayList<Usuario>(vertice);}
    public List<Arista> getAristas() {return new ArrayList<Arista>(arista);}


	
}