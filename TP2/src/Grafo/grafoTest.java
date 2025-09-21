package Grafo;

import static org.junit.Assert.*;

import org.junit.Test;

import Usuario.Usuario;

public class grafoTest {

	@Test (expected=IllegalArgumentException.class)
	public void agregarNodoNulo() {
		Grafo g= new Grafo(1);
		Usuario u= new Usuario("a",1, 1, 1, 1);
		g.AgregarVertice(u);
	}
	@Test
	public void test() {
		
	}
	
	

}
