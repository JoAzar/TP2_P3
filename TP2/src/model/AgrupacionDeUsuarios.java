package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgrupacionDeUsuarios {
	
	public Map<Integer, List<Usuario>> crearComponentesConexas(Grafo<Usuario> grafo, int cantGrupos){
		Kruskal<Usuario> kruskal = new Kruskal<>();
		List<Arista<Usuario>> agm = kruskal.crearAGM(grafo);
		
		if(agm.isEmpty()) {
			return new HashMap<>();
		}
		
		agm.sort(Collections.reverseOrder());
		
		for(int i = 0; i < cantGrupos-1 && i < agm.size(); i++) {
			agm.remove(0);
		}
		
		UnionFind<Usuario> unionf = new UnionFind<>(grafo.getVertices());
		
		for(Arista<Usuario> arista : agm) {
			unionf.unionSinRaiz(arista.getOrigen(), arista.getDestino());
		}
		
		return enumerarComponentes(crearComponentes(unionf, grafo));
	}
	
	private Map<Usuario, List<Usuario>> crearComponentes(UnionFind<Usuario> unionf, Grafo<Usuario> grafo){
		Map<Usuario, List<Usuario>> comp = new HashMap<>();
		
		for(Usuario u : grafo.getVertices()) {
			Usuario raiz = unionf.find(u);
			comp.computeIfAbsent(raiz, k -> new ArrayList<>()).add(u);
		}
		return comp;
	}
	
	private Map<Integer, List<Usuario>> enumerarComponentes(Map<Usuario, List<Usuario>> componentes){
		Map<Integer, List<Usuario>> resultado = new HashMap<>();
		int grupo = 1;
		
		for(List<Usuario> lista : componentes.values()) {
			resultado.put(grupo++, lista);
		}
		return resultado;
	}

}
