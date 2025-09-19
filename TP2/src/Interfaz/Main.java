package Interfaz;

import Grafo.Grafo;
import Usuario.Usuario;
import JMapViewer; 

public class Main {
    
    public static void main(String[] args) {
        Usuario u1= new Usuario(4,4,4,4);
        Usuario u2= new Usuario(1,5,1,3);
        Usuario u3= new Usuario(3,2,4,5);
        Usuario u4= new Usuario(56,4,2,4);  
        Grafo g = new Grafo(4);
        g.AgregarVertice(u1);
        g.AgregarVertice(u2);
        g.AgregarVertice(u3);
        g.AgregarVertice(u4);
         

    }
}
