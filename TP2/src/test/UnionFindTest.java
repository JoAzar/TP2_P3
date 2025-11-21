package test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.UnionFind;

public class UnionFindTest {

    private UnionFind<String> uf;
    private List<String> vertices;

    @Before
    public void setUp() {
        vertices = Arrays.asList("A", "B", "C", "D");
        uf = new UnionFind<>(vertices);
    }

    @Test
    public void inicializacionCadaElementoEsSuPropioPadreTest() {
        Map<String, String> padre = uf.getPadre();

        for (String elem : vertices) {
            assertEquals(elem, padre.get(elem));
        }
    }

    @Test
    public void findDevuelveElMismoElementoSiEsRaizTest() {
        assertEquals("A", uf.find("A"));
        assertEquals("B", uf.find("B"));
    }

    @Test
    public void unionSinRaizUneCorrectamenteTest() {
        uf.unionSinRaiz("A", "B");
        
        assertEquals("B", uf.find("A"));
        assertEquals("B", uf.find("B"));
    }

    @Test
    public void verificarUnionSinRaizCreaCadenasTest() {
        uf.unionSinRaiz("A", "B");
        uf.unionSinRaiz("B", "C");

        assertEquals("C", uf.find("A"));
        assertEquals("C", uf.find("B"));
        assertEquals("C", uf.find("C"));
    }

    @Test
    public void verificarUnionConRaizUneDirectamenteTest() {
        uf.unionConRaiz("A", "B");

        assertEquals("B", uf.find("A"));
        assertEquals("B", uf.find("B"));
    }

    @Test
    public void unionesRepetidasNoRompenTest() {
        uf.unionSinRaiz("A", "B");
        uf.unionSinRaiz("A", "B");

        assertEquals("B", uf.find("A"));
    }

    @Test
    public void unionDeDosRamasSeparadasTest() {
        uf.unionSinRaiz("A", "B");
        uf.unionSinRaiz("C", "D");
        uf.unionSinRaiz("B", "D");

        assertEquals("D", uf.find("A"));
        assertEquals("D", uf.find("B"));
        assertEquals("D", uf.find("C"));
        assertEquals("D", uf.find("D"));
    }
}