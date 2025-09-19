package Algoritmo;

import Usuario.Usuario;

public class AlgoritmoSimilar {
    public static int calcular(Usuario u1, Usuario u2) {
        return Math.abs(u1.getTango() - u2.getTango()) +
               Math.abs(u1.getFolklore() - u2.getFolklore()) +
               Math.abs(u1.getRock() - u2.getRock()) +
               Math.abs(u1.getUrbano() - u2.getUrbano());
    }
}

