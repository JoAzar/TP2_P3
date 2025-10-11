package Interfaz;

import model.Grafo;
import model.Usuario;
import java.util.*;

public class Main {
    
    public static void main(String[] args) {

        Usuario primer_usuario = new Usuario("Red", Map.of("rock", 5, "pop", 2, "jazz", 1));
        Usuario segundo_usuario = new Usuario("Orion", Map.of("rock", 4, "pop", 2, "metal", 5));
        Usuario tercer_usuario = new Usuario("Luna", Map.of("rock", 1, "pop", 5, "jazz", 4));
        Grafo g = new Grafo();
        g.agregarVertice(primer_usuario);
        g.agregarVertice(segundo_usuario);
        g.agregarVertice(tercer_usuario);

        //Conectamos usuarios según su afinidad
        List<Usuario> usuarios = g.getVertices();
        for (int i = 0; i < usuarios.size(); i++) {
            for (int j = i + 1; j < usuarios.size(); j++) {
                int afinidad = usuarios.get(i).afinidadCon(usuarios.get(j));
                if (afinidad > 0) {
                    g.agregarArista(usuarios.get(i), usuarios.get(j), afinidad);
                }
            }
        }
    }

}
