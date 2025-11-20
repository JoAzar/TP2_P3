package test;

import static org.junit.Assert.*;
import java.util.*;
import java.util.function.BiFunction;

import org.junit.Test;
import model.Grafo;
import model.Kruskal;
import model.UnionFind;
import model.Usuario;
import model.AgrupacionDeUsuarios;
import model.Arista;

public class GrafoTest {
    private final BiFunction<Usuario, Usuario, Integer> similitud =
            (u1, u2) -> u1.calculoSimilaridad(u2);

    @Test(expected = IllegalArgumentException.class)
    public void agregarVerticeNuloTest() {
        Grafo<Usuario> grafo = new Grafo<>();
        grafo.agregarVertice(null);
    }

    @Test
    public void agregarVerticeTest() {
        Grafo<Usuario> grafo = new Grafo<>();
        Map<String, Integer> gustos = new HashMap<>();
        gustos.put("rock", 5);
        gustos.put("tango", 3);
        gustos.put("folclore", 2);
        gustos.put("urbano", 5);
        Usuario usuario = new Usuario("Lucas", gustos);

        grafo.agregarVertice(usuario);
        assertTrue(grafo.getVertices().contains(usuario));
        assertEquals(1, grafo.getVertices().size());
    }

    @Test
    public void agregarAristaTest() {
        Grafo<Usuario> grafo = new Grafo<>();

        Map<String, Integer> gustos = new HashMap<>();
        gustos.put("rock", 5);
        gustos.put("tango", 3);
        gustos.put("folclore", 2);
        gustos.put("urbano", 5);

        Usuario u1 = new Usuario("Juan", gustos);
        Usuario u2 = new Usuario("Pedro", gustos);

        grafo.agregarVertice(u1);
        grafo.agregarVertice(u2);
        grafo.agregarArista(u1, u2, 10);

        assertEquals(1, grafo.getAristas().size());
        Arista<Usuario> arista = grafo.getAristas().get(0);
        assertEquals(u1, arista.getOrigen());
        assertEquals(u2, arista.getDestino());
        assertEquals(10, arista.getPeso());
    }

    @Test
    public void agregarAristaDuplicadaTest() {
        Grafo<Usuario> grafo = new Grafo<>();

        Map<String, Integer> gustos = new HashMap<>();
        gustos.put("rock", 5);
        gustos.put("tango", 3);
        gustos.put("folclore", 2);
        gustos.put("urbano", 5);

        Usuario u1 = new Usuario("Juan", gustos);
        Usuario u2 = new Usuario("Pedro", gustos);

        grafo.agregarVertice(u1);
        grafo.agregarVertice(u2);

        grafo.agregarArista(u1, u2, 10);
        grafo.agregarArista(u1, u2, 20);

        assertEquals(1, grafo.getAristas().size());
    }
    
    @Test
    public void crearGrafoCompletoTest() {
        Grafo<Usuario> grafo = crearGrafoCompleto();

        assertEquals(6, grafo.getAristas().size());
    }

    @Test
    public void kruskalAGMTest() {
        Grafo<Usuario> grafo = crearGrafoCompleto();
        Kruskal<Usuario> kruskal = new Kruskal<>();
        
        List<Arista<Usuario>> agm = kruskal.crearAGM(grafo);

        assertEquals(4 - 1, agm.size());
    }

    @Test
    public void crearComponentesConexasTest() {
        Grafo<Usuario> grafo = crearGrafoCompleto();
        Kruskal<Usuario> kruskal = new Kruskal<>();
        UnionFind<Usuario> unionf = new UnionFind<>(grafo.getVertices());
        
        List<Arista<Usuario>> agm = kruskal.crearAGM(grafo);
        
        for(Arista<Usuario> a : agm) {
        	unionf.unionSinRaiz(a.getOrigen(), a.getDestino());
        }
        AgrupacionDeUsuarios agrupador = new AgrupacionDeUsuarios();
        Map<Integer, List<Usuario>> grupos = agrupador.crearComponentesConexas(grafo, 2);

        assertEquals(2, grupos.size());

        // Chequear que todos los usuarios esten
        Set<Usuario> todos = new HashSet<>();
        for (List<Usuario> grupo : grupos.values()) {
            todos.addAll(grupo);
        }
        assertEquals(new HashSet<>(grafo.getVertices()), todos);
    }
    
    private Grafo<Usuario> crearGrafoCompleto() {
        Grafo<Usuario> grafo = new Grafo<>();

        Map<String, Integer> g1 = new HashMap<>();
        g1.put("rock", 2);
        g1.put("tango", 3);
        g1.put("folclore", 2);
        g1.put("urbano", 5);

        Map<String, Integer> g2 = new HashMap<>();
        g2.put("rock", 3);
        g2.put("tango", 4);
        g2.put("folclore", 1);
        g2.put("urbano", 5);

        Map<String, Integer> g3 = new HashMap<>();
        g3.put("rock", 5);
        g3.put("tango", 2);
        g3.put("folclore", 3);
        g3.put("urbano", 1);

        Map<String, Integer> g4 = new HashMap<>();
        g4.put("rock", 1);
        g4.put("tango", 5);
        g4.put("folclore", 4);
        g4.put("urbano", 2);

        Usuario u1 = new Usuario("Marcos", g1);
        Usuario u2 = new Usuario("Marta", g2);
        Usuario u3 = new Usuario("Lucas", g3);
        Usuario u4 = new Usuario("Ana", g4);

        grafo.agregarVertice(u1);
        grafo.agregarVertice(u2);
        grafo.agregarVertice(u3);
        grafo.agregarVertice(u4);

        grafo.crearGrafoCompleto(similitud);

        return grafo;
    }
    
}
