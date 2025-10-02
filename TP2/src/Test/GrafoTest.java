
import static org.junit.Assert.*;

import org.junit.Test;

import Usuario.Usuario;
import Grafo.Grafo;

public class GrafoTest {

	@Test (expected=IllegalArgumentException.class)
	public void agregarNodoNulo() {
		Grafo g= new Grafo(1);
		Usuario u= new Usuario("a",1, 1, 1, 1);
		g.AgregarVertice(u);
	}
	@Test (expected= IllegalArgumentException.class)
	public void eliminarNodoInexistente() {
        Grafo g= new Grafo(1);
        Usuario u= new Usuario("Hola", 1, 1, 1, 1);
        g.eliminarVertice(u);
		
	}
    @Test (expected= IllegalArgumentException.class)
    public void agregarNodoIllegal(){
        Grafo g= new Grafo(1);
        Usuario u= new Usuario("", -1, -1, -1, -1);
        g.agregarVertice(u);

    }
	
	

}