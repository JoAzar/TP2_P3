package test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import model.UnionFind;

public class UnionFindTest {

    private UnionFind<String> unionf;
    private List<String> vertices;

    @Before
    public void setUp() {
        vertices = Arrays.asList("A", "B", "C", "D");
        unionf = new UnionFind<>(vertices);
    }

    @Test
    public void inicializacionCadaElementoEsSuPropioPadreTest() {
        Map<String, String> padre = unionf.getPadre();

        for (String v : vertices) {
            assertEquals(v, padre.get(v));
        }
    }

    @Test
    public void findDevuelveElMismoElementoSiEsRaizTest() {
        assertEquals("A", unionf.find("A"));
        assertEquals("B", unionf.find("B"));
    }

    @Test
    public void unionSinRaizUneCorrectamenteTest() {
        unionf.unionSinRaiz("A", "B");
        
        assertEquals("B", unionf.find("A"));
        assertEquals("B", unionf.find("B"));
    }

    @Test
    public void verificarUnionSinRaizCreaCadenasTest() {
        unionf.unionSinRaiz("A", "B");
        unionf.unionSinRaiz("B", "C");

        assertEquals("C", unionf.find("A"));
        assertEquals("C", unionf.find("B"));
        assertEquals("C", unionf.find("C"));
    }

    @Test
    public void verificarUnionConRaizUneDirectamenteTest() {
        unionf.unionConRaiz("A", "B");

        assertEquals("B", unionf.find("A"));
        assertEquals("B", unionf.find("B"));
    }

    @Test
    public void unionesRepetidasNoRompenTest() {
        unionf.unionSinRaiz("A", "B");
        unionf.unionSinRaiz("A", "B");

        assertEquals("B", unionf.find("A"));
    }

    @Test
    public void unionDeDosRamasSeparadasTest() {
        unionf.unionSinRaiz("A", "B");
        unionf.unionSinRaiz("C", "D");
        unionf.unionSinRaiz("B", "D");

        assertEquals("D", unionf.find("A"));
        assertEquals("D", unionf.find("B"));
        assertEquals("D", unionf.find("C"));
        assertEquals("D", unionf.find("D"));
    }
}