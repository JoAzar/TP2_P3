
import static org.junit.Assert.*;

import org.junit.Test;

import Usuario.Usuario;
import Grafo.Grafo;
public class AlgoritmoTest {
    @Test
    public void similaridadTest(){
        Usuario u1= new Usuario("messi",4,4,4,4);
        Usuario u2= new Usuario("josé ricardo", 1,5,1,3);
        Grafo g= new Grafo(2);
        g.AgregarVertice(u1);
        g.AgregarVertice(u2);
        assertTrue(g.calcularSimilaridad(u1, u2)==8);
    }
}
