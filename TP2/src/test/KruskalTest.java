package test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import model.Arista;
import model.Grafo;
import model.Kruskal;

public class KruskalTest {

    @Test
    public void AGMConGrafoSimpleTest() {
        Grafo<String> grafo = new Grafo<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        
        grafo.agregarArista("A", "B", 3);
        grafo.agregarArista("A", "C", 1);
        grafo.agregarArista("B", "C", 2);

        Kruskal<String> k = new Kruskal<>();
        List<Arista<String>> agm = k.crearAGM(grafo);
        
        assertEquals(2, agm.size());
        
        List<Integer> pesos = Arrays.asList(agm.get(0).getPeso(), agm.get(1).getPeso());

        assertTrue(pesos.contains(1));
        assertTrue(pesos.contains(2));
    }
    
    @Test
    public void verificarSiEvitaCiclosTest() {
        Grafo<String> grafo = new Grafo<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        
        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("B", "C", 1);
        grafo.agregarArista("C", "D", 1);
        grafo.agregarArista("D", "A", 1);
        grafo.agregarArista("A", "C", 5);

        Kruskal<String> k = new Kruskal<>();
        List<Arista<String>> agm = k.crearAGM(grafo);
        
        assertEquals(3, agm.size());
        
        for (Arista<String> a : agm) {
            assertNotEquals(5, a.getPeso());
        }
    }
    
    @Test
    public void AGMDeGrafoCompletoTest() {
        Grafo<String> grafo = new Grafo<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", 10);
        grafo.agregarArista("A", "C", 5);
        grafo.agregarArista("B", "C", 1);

        Kruskal<String> k = new Kruskal<>();
        List<Arista<String>> agm = k.crearAGM(grafo);

        assertEquals(2, agm.size());
        
        assertEquals(1, agm.get(0).getPeso());
        assertEquals(5, agm.get(1).getPeso());
    }
    
}