package model;

import java.util.Map;

public class Usuario {
	    private String _nombre;
	    private Map<String, Integer> _gustos; //ej: {"rock": 5, "pop": 2, "jazz": 3}

	    public Usuario(String nombre, Map<String, Integer> gustos) {

    	    if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre del usuario no puede ser nulo o vacío");
    	    if (gustos == null || gustos.isEmpty()) throw new IllegalArgumentException("El usuario debe tener al menos un gusto musical");
	        _nombre = nombre;
	        _gustos = gustos;
	    }

	    public String getNombre() {return _nombre;}
	    public Map<String, Integer> getGustos() {return _gustos;}

	    public int afinidadCon(Usuario otro) {
	        int total = 0;
	        int contador = 0;
	        for(String genero : _gustos.keySet()) {
	            if(otro.getGustos().containsKey(genero)) {
	                int diff = Math.abs(_gustos.get(genero) - otro.getGustos().get(genero));
	                total += (5 - diff); //más parecido → mayor afinidad
	                contador++;
	            }
	        }
	        return contador > 0 ? total / contador : 0;
	    }

	    @Override
	    public String toString() {
	        return _nombre;
	    }
}
