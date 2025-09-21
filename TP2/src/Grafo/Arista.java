package Grafo;

import Usuario.Usuario;

public class Arista implements Comparable<Arista> {
    private Usuario u1, u2;
    private int peso;

    public Arista(Usuario u1, Usuario u2, int peso) {
        this.u1 = u1;
        this.u2 = u2;
        this.peso = peso;
    }

    public Usuario getU1() { return u1; }
    public Usuario getU2() { return u2; }
    public int getPeso() { return peso; }

    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.peso, otra.peso);
    }

    @Override
    public String toString() {
        return u1 + " --(" + peso + ")--> " + u2;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Arista otra = (Arista) obj;
        return (u1.equals(otra.u1) && u2.equals(otra.u2)) || (u1.equals(otra.u2) && u2.equals(otra.u1));
    }

    @Override
    public int hashCode() {
        return u1.hashCode() + u2.hashCode();
    }
}
