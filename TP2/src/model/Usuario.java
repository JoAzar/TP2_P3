package model;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
	    private String _nombre;
	    private Map<String, Integer> _gustos; //ej: {"rock": 5, "pop": 2, "jazz": 3}

	    public Usuario(String nombre, Map<String, Integer> gustos) {

	    	// Me parece que isBlank no es valido para la version q estamos usando
    	    if (nombre == null /*|| nombre.isBlank()*/) throw new IllegalArgumentException("El nombre del usuario no puede ser nulo");
    	    if (gustos == null || gustos.isEmpty()) throw new IllegalArgumentException("El usuario debe tener al menos un gusto musical");
	        _nombre = nombre;
	        _gustos = gustos;
	    }
	    
		// Calculo similaridad
		public int calculoSimilaridad(Usuario u2) {
		    int t = Math.abs(_gustos.get("tango") - u2.getGustos().get("tango"));
		    int f = Math.abs(_gustos.get("folclore") - u2.getGustos().get("folclore"));
		    int r = Math.abs(_gustos.get("rock") - u2.getGustos().get("rock"));
		    int u = Math.abs(_gustos.get("urbano") - u2.getGustos().get("urbano"));
		    return t + f + r + u;
		}
	    
	    // Getters
	    public String getNombre() {return _nombre;}
	    
	    public Map<String, Integer> getGustos() {return new HashMap<>(_gustos);}

	    @Override
	    public String toString() {
	        return _nombre;
	    }
}
