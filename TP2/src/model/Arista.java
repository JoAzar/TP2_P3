package model;

public class Arista implements Comparable<Arista>{
    private Usuario _origen, _destino;
    private int _peso;

    public Arista(Usuario origen, Usuario destino, int peso) {
        _origen = origen;
        _destino = destino;
        _peso = peso;
    }
    
    @Override
    public int compareTo(Arista otraArista) {
    	return Integer.compare(_peso, otraArista._peso);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Arista)) return false;
        Arista a = (Arista) obj;
        return _origen.equals(a._origen) && _destino.equals(a._destino);
    }

    @Override
    public int hashCode() {
    	return _origen.hashCode() + _destino.hashCode();
    }
    
    public Usuario getOrigen() {
    	return _origen;
    }
    
    public Usuario getDestino() {
    	return _destino;
    }
    
    public int getPeso() {
    	return _peso;
    }
    
}