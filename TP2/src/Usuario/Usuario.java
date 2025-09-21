package Usuario;

import java.util.*;

public class Usuario {
	private String nombre;
    private int interesTango, interesFolklore, interesRock, interesUrbano;

    public Usuario (String nombre, int interesTango, int interesFolklore, int interesRock, int interesUrbano){
    	this.nombre=validar(nombre);
        this.interesTango = validar(interesTango);
        this.interesFolklore = validar(interesFolklore);
        this.interesRock = validar(interesRock);
        this.interesUrbano = validar(interesUrbano);
    }

    private String validar(String n) {
		if(n.isEmpty() || n.equals(null)) {
			throw new IllegalArgumentException("Error, nombre de usuario: {"+n+"} inválido");
		}
		return n;
	}

	private int validar(int i){
        if(i < 1 || i > 5) {
        	throw new IllegalArgumentException("Error en el parámetro interés de usuario: {"+ i + "} no es un parámetro válido");
        }
        return i;
    }
	public String getNombre() { return nombre;}
    public int getTango() { return interesTango; }
    public int getFolklore() { return interesFolklore; }
    public int getRock() { return interesRock; }
    public int getUrbano() { return interesUrbano; }

    @Override
    public String toString() {
        return "[Tango=" + interesTango + 
               ", Folklore=" + interesFolklore +
               ", Rock=" + interesRock +
               ", Urbano=" + interesUrbano + "]";
    }

	public List<Integer> getIntereses() {
		List <Integer> intereses= new ArrayList<>(Arrays.asList(interesTango, interesFolklore, interesRock, interesUrbano));
		return intereses;
	}
}
