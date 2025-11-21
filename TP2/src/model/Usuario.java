package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario {
	private String _nombre;
	private Map<String, Integer> _gustos;

	public Usuario(String nombre, Map<String, Integer> gustos) {
		if (nombre == null) throw new IllegalArgumentException("El nombre del usuario no puede ser nulo");
		if (gustos == null || gustos.isEmpty() || gustos.size() != 4) throw new IllegalArgumentException("El usuario debe tener al menos un gusto musical");
		_nombre = nombre;
		_gustos = gustos;
	}
	
	public int calculoSimilaridad(Usuario usuario) {
	    int similaridad = 0;
	    String[] generos = {"rock", "tango", "folclore", "urbano"};
	    for(String genero : generos) {
	        Integer miValor = _gustos.get(genero);
	        Integer otroValor = usuario.getGustos().get(genero);
	        if(miValor == null || otroValor == null) throw new IllegalArgumentException("Usuario sin valor definido para el género " + genero);
	        similaridad += Math.abs(miValor - otroValor);
	    }
	    return similaridad;
	}
	
	public Map<String, Double> promedioInteres(List<Usuario> usuarios) {
	    Map<String, Integer> sumaPorGenero = new HashMap<>();
	    Map<String, Integer> cantidadPorGenero = new HashMap<>();
	    for(Usuario u : usuarios) {
	        for(Map.Entry<String, Integer> entry : u.getGustos().entrySet()) {
	            String genero = entry.getKey();
	            Integer valor = entry.getValue();
	            sumaPorGenero.merge(genero, valor, Integer::sum);
	            cantidadPorGenero.merge(genero, 1, Integer::sum);
	        }
	    }
	    Map<String, Double> promedioPorGenero = new HashMap<>();
	    for(String genero : sumaPorGenero.keySet()) {
	        double promedio = (double) sumaPorGenero.get(genero) / cantidadPorGenero.get(genero);
	        promedioPorGenero.put(genero, promedio);
	    }
	    return promedioPorGenero;
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