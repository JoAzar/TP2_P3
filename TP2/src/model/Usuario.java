package model;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
	private String _nombre;
	private Map<String, Integer> _gustos;

	public Usuario(String nombre, Map<String, Integer> gustos) {
		if (nombre == null) {
			throw new IllegalArgumentException("El nombre del usuario no puede ser nulo");
		}
		if (gustos == null || gustos.isEmpty() || gustos.size() != 4) {
			throw new IllegalArgumentException("El usuario debe tener al menos un gusto musical");
		}
		_nombre = nombre;
		_gustos = gustos;
	}
	    
	public int calculoSimilaridad(Usuario u2) {
		int total = 0;
		String[] generos = {"rock", "tango", "folclore", "urbano"};
		
		for (String g : generos){
			Integer miValor = _gustos.get(g);
			Integer otroValor = u2.getGustos().get(g);
			
			if (miValor == null || otroValor == null) {
				throw new IllegalArgumentException("Usuario sin valor definido para el género " + g);
			}
			
			total += Math.abs(miValor - otroValor);
		}
		    return total;
	}
	
	public String getNombre() {
		return _nombre;
	}
	
	public Map<String, Integer> getGustos() {
		return new HashMap<>(_gustos);
	}

	@Override
	public String toString() {
		return _nombre;
	}
}
