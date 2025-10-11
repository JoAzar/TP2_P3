package Test;

import org.junit.Test;
import model.Usuario;
import model.Grafo;
import usuario;

public class Grafotest {

	@Test(expected = IllegalArgumentException.class)
	public void agregarVerticeNulo() {
		Grafo grafo = new Grafo();
		grafo.agregarVertice(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void crearUsuarioInvalido(){
        Usuario usuario1= new Usuario(null, null);
	}


	@Test(expected = IllegalArgumentException.class)
    public void crearUsuarioParámetrosGustoInválidos(){
       Map<String, Integer> gustos= new HashMap<String, Integer>("rock": -2, "jazz": 3, "folklore":0, "urbano":5);
	   Usuario usuario1 = new usuario("juan", gustos);
	}


	@Test
	public void calcularAfinidadTest(){
		Map<String, Integer> gustos1= new HashMap<String, Integer>("rock": 2, "jazz": 3, "folklore":2, "urbano":5);
		Map<String, Integer> gustos2= new HashMap<String, Integer>("rock": 3, "jazz": 4, "folklore":1, "urbano":5);

		Usuario usuario1= new Usuario("Juan", gustos1);
		Usuario usuario2= new Usuario("Pedro", gustos2);

		int afinidad= usuario1.afinidadCon(usuario2);
		assertTrue(afinidad==3);
	}

	@Test
	public void algoritmoAGMTest(){
		
		Grafo test= new Grafo().algoritmoAGM();
	}
	
	
}
