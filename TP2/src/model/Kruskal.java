package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal<T> {
	
	public List<Arista<T>> crearAGM(Grafo<T> grafo) {
        List<Arista<T>> arbolGeneradorMinimo = new ArrayList<>();
        List<Arista<T>> aristas = new ArrayList<>(grafo.getAristas());
        Collections.sort(aristas);
        UnionFind<T> unionFind = new UnionFind<>(grafo.getVertices());
        for(Arista<T> arista : aristas) {
            T origen = arista.getOrigen();
            T destino = arista.getDestino();
            T raizOrigen = unionFind.find(origen);
            T raizDestino = unionFind.find(destino);
            if(!raizOrigen.equals(raizDestino)) {
            	arbolGeneradorMinimo.add(arista);
            	unionFind.unionConRaiz(raizOrigen, raizDestino);
            }
            if(arbolGeneradorMinimo.size() == grafo.getVertices().size() - 1) break;
        }
        return arbolGeneradorMinimo;
    }

}