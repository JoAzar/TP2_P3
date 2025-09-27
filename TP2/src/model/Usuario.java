package model;

public class Usuario {
	
	private final String nombre;
    private final int iTango, iFolklore, iRock, iUrbano;
    
    // Constructor
    public Usuario (String nombre, int iTango, int iFolklore, int iRock, int iUrbano){
    	this.nombre = validarNombre(nombre);
        this.iTango = validarInteres(iTango);
        this.iFolklore = validarInteres(iFolklore);
        this.iRock = validarInteres(iRock);
        this.iUrbano = validarInteres(iUrbano);
    }

    // Se valida que el nombre pasado por parametro sea correcto.
    private String validarNombre(String n) {
		if(n == null || n.isEmpty()) {
			throw new IllegalArgumentException("Error, nombre de usuario: {"+n+"} inválido");
		}
		return n;
	}
    
    // Se valida que el interes ingresado esté dentro de los limites (1-5)
	private int validarInteres(int i){
        if(i < 1 || i > 5) {
        	throw new IllegalArgumentException("Error en el parámetro interés de usuario: {"+ i +"} no es un parámetro válido");
        }
        return i;
    }
	
	// Getters
	public int getTango() {
		return iTango;
	}
	
	public int getFolklore() {
		return iFolklore;
	}
	
	public int getRock() {
		return iRock;
	}
	
	public int getUrbano() {
		return iUrbano;
	}

	public String getNombre() {
		return nombre;
	}
}
