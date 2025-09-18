package Grafo;

import java.util.ArrayList;
import java.util.List;
import Usuario.Usuario;

public class Grafo {
    private List<Usuario> vertices;

    public Grafo(int capacidadInicial) {
        vertices = new ArrayList<>(capacidadInicial);
    }

    public void AgregarVertice(Usuario u) {
        vertices.add(u);
        System.out.println("Agregado: " + u);
    }
}
