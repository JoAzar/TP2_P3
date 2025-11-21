package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind<T> {
	private Map<T, T> padre = new HashMap<>();
	
	public UnionFind(List<T> vertices) {
		for(T ver : vertices) {
			padre.put(ver, ver);
		}
	}
	
	public T find(T ver) {
    	while(padre.get(ver) != ver) {
    		ver = padre.get(ver);
    	}
    	return ver;
	}
	
    public void unionSinRaiz(T primerUsuario, T segundoUsuario) {
    	T primeraRaiz = find(primerUsuario);
    	T segundaRaiz = find(segundoUsuario);
    	padre.put(primeraRaiz, segundaRaiz);
    }
    
    public void unionConRaiz(T primeraRaiz, T segundaRaiz) {
    	padre.put(primeraRaiz, segundaRaiz);
    }
    
    public Map<T, T> getPadre(){
    	return new HashMap<>(padre);
    }
}
