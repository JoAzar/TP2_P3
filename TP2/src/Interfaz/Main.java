package Interfaz;

import Grafo.Grafo;
import Usuario.Usuario;
import org.openstreetmap.gui.jmapviewer.*;
import javax.swing.*;

public class Main {
    
    public static void main(String[] args) {
        //EXPERIMENTACION (Esto tendría que ir en TEST no te parece?!?)
        Usuario u1= new Usuario("messi",4,4,4,4);
        Usuario u2= new Usuario("josé ricardo", 1,5,1,3);
        Usuario u3= new Usuario("abc",3,2,4,5);
        Usuario u4= new Usuario("abc",1,4,2,4);  
        Grafo g = new Grafo(4);
        g.AgregarVertice(u1);
        g.AgregarVertice(u2);
        g.AgregarVertice(u3);
        g.AgregarVertice(u4);
        
        //BONDIOLA

        jMap mapaGrafo= new jMap(-36, -52);
        mapaGrafo.agregarNodo(-36, -52, 0.01);
        mapaGrafo.agregarNodo(-36.2, -52.2, 0.01);
        mapaGrafo.agregarArista(-36, -52, -36.2, -52.2);
        mapaGrafo.agregarNodo(-35.8, -52.2, 0.01);
        mapaGrafo.agregarArista(-35.8, -52.2, -36.2, -52.2);
        mapaGrafo.agregarArista(-35.8, -52.2, -36, -52);

        //prueba similaridad
        System.out.println("Similaridad u1 con u2: "+g.calcularSimilaridad(u1, u2));
        System.out.println("Similaridad u1 con u3: "+ g.calcularSimilaridad(u1, u3));
        //Alguien está leyendo esto?
        System.out.println("Similaridad u1 con u3: "+g.calcularSimilaridad(u1, u4));

    }
}
