package test;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import model.Grafo;
import model.Usuario;
import model.Arista;

public class GrafoTest {

    @Test(expected = IllegalArgumentException.class)
    public void agregarVerticeNuloTest() {
        Grafo grafo = new Grafo();
        grafo.agregarVertice(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void crearUsuarioNuloTest() {
        new Usuario(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void crearUsuarioSinGustosTest() {
        Map<String, Integer> gustos = new HashMap<>();
        new Usuario("Juan", gustos);
    }

    /**
    @Test
    
    public void calcularAfinidadTest() {
        Map<String, Integer> gustos1 = new HashMap<>();
        gustos1.put("rock", 2);
        gustos1.put("jazz", 3);
        gustos1.put("folklore", 2);
        gustos1.put("urbano", 5);

        Map<String, Integer> gustos2 = new HashMap<>();
        gustos2.put("rock", 3);
        gustos2.put("jazz", 4);
        gustos2.put("folklore", 1);
        gustos2.put("urbano", 5);

        Usuario usuario1 = new Usuario("Juan", gustos1);
        Usuario usuario2 = new Usuario("Pedro", gustos2);

        int afinidad = usuario1.afinidadCon(usuario2);
        assertEquals(3, afinidad);
    }
    **/

    @Test
    public void agregarVerticeTest() {
        Grafo grafo = new Grafo();
        Map<String, Integer> gustos = new HashMap<>();
        gustos.put("rock", 5);
        Usuario usuario = new Usuario("Lucas", gustos);

        grafo.agregarVertice(usuario);
        assertTrue(grafo.getVertices().contains(usuario));
        assertEquals(1, grafo.getVertices().size());
    }

    @Test
    public void agregarAristaTest() {
        Grafo grafo = new Grafo();

        Map<String, Integer> gustos = new HashMap<>();
        gustos.put("metal", 5);

        Usuario u1 = new Usuario("Juan", gustos);
        Usuario u2 = new Usuario("Pedro", gustos);

        grafo.agregarVertice(u1);
        grafo.agregarVertice(u2);
        grafo.agregarArista(u1, u2, 10);

        assertEquals(1, grafo.getAristas().size());
        Arista arista = grafo.getAristas().get(0);
        assertEquals(u1, arista.getOrigen());
        assertEquals(u2, arista.getDestino());
        assertEquals(10, arista.getPeso());
    }

    @Test
    public void AgregarAristaDuplicadaTest() {
        Grafo grafo = new Grafo();

        Map<String, Integer> gustos = new HashMap<>();
        gustos.put("metal", 5);

        Usuario u1 = new Usuario("Juan", gustos);
        Usuario u2 = new Usuario("Pedro", gustos);

        grafo.agregarVertice(u1);
        grafo.agregarVertice(u2);

        grafo.agregarArista(u1, u2, 10);
        grafo.agregarArista(u1, u2, 20);

        assertEquals(1, grafo.getAristas().size());
    }
}
