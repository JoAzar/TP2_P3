package model;

public class Arista<T> implements Comparable<Arista<T>>{
    private T _origen, _destino;
    private int _peso;

    public Arista(T origen, T destino, int peso) {
        _origen = origen;
        _destino = destino;
        _peso = peso;
    }
    
    @Override
    public int compareTo(Arista<T> otraArista) {
    	return Integer.compare(_peso, otraArista._peso);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Arista)) return false;
        Arista<?> a = (Arista<?>) obj;
        return (_origen.equals(a._origen) && _destino.equals(a._destino)) || (_origen.equals(a._destino) && _destino.equals(a._origen));
    }

    @Override
    public int hashCode() {
    	return _origen.hashCode() + _destino.hashCode();
    }
    
    public T getOrigen() {
    	return _origen;
    }
    
    public T getDestino() {
    	return _destino;
    }
    
    public int getPeso() {
    	return _peso;
    }
    
}