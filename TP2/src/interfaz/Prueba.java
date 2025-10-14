package interfaz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Grafo;
import model.Usuario;
// ESTO ERA PARA VER SI ANDABA (ANDA) SE PUEDE BORRAR
public class Prueba {
    public static void main(String[] args) {
        Map<String, Integer> gustos1 = new HashMap<>();
        gustos1.put("rock", 5);
        gustos1.put("jazz", 2);
        gustos1.put("folclore", 1);
        gustos1.put("urbano", 4);
        Usuario u1 = new Usuario("Lucas", gustos1);

        Map<String, Integer> gustos2 = new HashMap<>();
        gustos2.put("rock", 4);
        gustos2.put("jazz", 3);
        gustos2.put("folclore", 2);
        gustos2.put("urbano", 5);
        Usuario u2 = new Usuario("Maria", gustos2);

        Map<String, Integer> gustos3 = new HashMap<>();
        gustos3.put("rock", 1);
        gustos3.put("jazz", 5);
        gustos3.put("folclore", 4);
        gustos3.put("urbano", 1);
        Usuario u3 = new Usuario("Marcos", gustos3);

        Map<String, Integer> gustos4 = new HashMap<>();
        gustos4.put("rock", 2);
        gustos4.put("jazz", 4);
        gustos4.put("folclore", 5);
        gustos4.put("urbano", 2);
        Usuario u4 = new Usuario("Marta", gustos4);

        Grafo grafo = new Grafo();
        grafo.agregarVertice(u1);
        grafo.agregarVertice(u2);
        grafo.agregarVertice(u3);
        grafo.agregarVertice(u4);

        grafo.crearGrafoCompleto();

        Map<Integer, List<Usuario>> grupos = grafo.crearComponentesConexas();

        for (Map.Entry<Integer, List<Usuario>> entry : grupos.entrySet()) {
            System.out.println("Grupo " + entry.getKey() + ":");
            for (Usuario u : entry.getValue()) {
                System.out.println("  " + u.getNombre());
            }
            System.out.println();
        }
    }
}
