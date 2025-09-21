package Grafo;
import Algoritmo.AlgoritmoSimilar;
import java.util.ArrayList;
import java.util.List;
import Usuario.Usuario;

public class Grafo {
    private List<Usuario> vertices;
    private List<Arista> aristas;

    public Grafo(int capacidadInicial) {
        vertices = new ArrayList<>(capacidadInicial);
    }

    public void AgregarVertice(Usuario u) {
        vertices.add(u);
        System.out.println("Agregado: " + u);
        for(int i=0; i<vertices.size(); i++) {
        	Usuario u2= vertices.get(i);
        	if(!existeArista(u, u2)) {
        		aristas.add(new Arista(u, vertices.get(i), AlgoritmoSimilar.calcularSimilaridad(u, u2)));
        	}
        }
    }

	private boolean existeArista(Usuario u1, Usuario u2) {
		return aristas.contains(new Arista(u1,u2, AlgoritmoSimilar.calcularSimilaridad(u1, u2)));
	}
}
