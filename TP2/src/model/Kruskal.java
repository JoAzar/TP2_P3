package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal<T> {
	
	public List<Arista<T>> crearAGM(Grafo<T> grafo){
        List<Arista<T>> agm = new ArrayList<>();
        List<Arista<T>> aristas = new ArrayList<>(grafo.getAristas());
        Collections.sort(aristas);
        
        UnionFind<T> unionf = new UnionFind<>(grafo.getVertices());
        for(Arista<T> arista : aristas) {
        	T primerUsuario = arista.getOrigen();
        	T segundoUsuario = arista.getDestino();
        	T primerRaiz = unionf.find(primerUsuario);
        	T segundaRaiz = unionf.find(segundoUsuario);
        	
        	if(primerRaiz != segundaRaiz) {
        		agm.add(arista);
        		unionf.unionConRaiz(primerRaiz, segundaRaiz);
        	}
        	if(agm.size() == grafo.getVertices().size() - 1) break;
        }
        return agm;
	}

}