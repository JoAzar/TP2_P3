package test;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import model.Usuario;

public class UsuarioTest {

    @Test(expected = IllegalArgumentException.class)
    public void crearUsuarioNuloTest() {
        new Usuario(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void crearUsuarioSinGustosTest() {
        Map<String, Integer> gustos = new HashMap<>();
        new Usuario("Juan", gustos);
    }

    @Test
    public void calculoSimilaridadCorrectoTest() {
        Map<String, Integer> gustos1 = new HashMap<>();
        gustos1.put("rock", 2);
        gustos1.put("jazz", 3);
        gustos1.put("folclore", 2);
        gustos1.put("urbano", 5);

        Map<String, Integer> gustos2 = new HashMap<>();
        gustos2.put("rock", 3);
        gustos2.put("jazz", 4);
        gustos2.put("folclore", 1);
        gustos2.put("urbano", 5);

        Usuario u1 = new Usuario("Juan", gustos1);
        Usuario u2 = new Usuario("Pedro", gustos2);

        int similaridad = u1.calculoSimilaridad(u2);
        assertEquals(3, similaridad);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void calcularSimilaridadConUnGustoFaltante() {
        Map<String, Integer> gustos1 = new HashMap<>();
        gustos1.put("rock", 2);
        gustos1.put("jazz", 3);
        gustos1.put("folklore", 2);
        gustos1.put("urbano", 5);

        Map<String, Integer> gustos2 = new HashMap<>();
        gustos2.put("rock", 3);
        gustos2.put("jazz", 4);
        gustos2.put("folclore", 1);
        gustos2.put("urbano", 5);

        Usuario u1 = new Usuario("Juan", gustos1);
        Usuario u2 = new Usuario("Pedro", gustos2);

        u1.calculoSimilaridad(u2);    	
    }
}
