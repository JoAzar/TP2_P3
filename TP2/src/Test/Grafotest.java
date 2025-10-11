package Test;

import org.junit.Test;
import model.Usuario;
import model.Grafo;

public class Grafotest {

	@Test(expected = IllegalArgumentException.class)
	public void agregarVerticeNulo() {
		Grafo grafo = new Grafo();
		grafo.agregarVertice(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void crearUsuarioInvalido(){
        Grafo grafo= new Grafo();
        new Usuario(null, null);
	}

}
