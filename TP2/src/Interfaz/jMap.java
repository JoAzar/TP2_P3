package Interfaz;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.*;

import java.util.Arrays;

import javax.swing.*;

public class jMap{
	private JMapViewer map;
    public jMap(double lat, double lon) {
    	map = new JMapViewer();
        map.setDisplayPosition(new Coordinate(lat, lon), 10);
        JFrame frame = new JFrame("Grafo Usuarios");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(map);
        frame.setVisible(true);
    }
    //REPRESENTAR EL GRAFO EN UN MAPA 
    public void agregarNodo(double lat, double lon, double radio) {
        MapMarkerCircle nodo = new MapMarkerCircle(new Coordinate(lat, lon), radio);
        nodo.setBackColor(java.awt.Color.RED); // color del nodo
        map.addMapMarker(nodo);
    }

    public void agregarArista(double lat1, double lon1, double lat2, double lon2) {
    map.addMapPolygon(new MapPolygonImpl(Arrays.asList(
        new Coordinate(lat1, lon1),
        new Coordinate(lat2, lon2),
        new Coordinate(lat1, lon1)
    )));
}
}
